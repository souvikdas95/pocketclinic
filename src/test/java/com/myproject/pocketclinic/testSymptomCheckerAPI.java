package com.myproject.pocketclinic;

import Priaid.Diagnosis.Client.*;
import Priaid.Diagnosis.Model.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class testSymptomCheckerAPI
{
    private static final String USERNAME = "souvikdas95@yahoo.co.in";
    private static final String PASSWORD = "Wg25Lec6AYi94JfBw";
    private static final String AUTHURL = "https://sandbox-authservice.priaid.ch/login";
    private static final String LANGUAGE = "en-gb";
    private static final String HEALTHURL = "https://sandbox-healthservice.priaid.ch";
    
    private static DiagnosisClient ODC;
    
    public static void main(String args[])
    {
        try
        {
            ODC = new DiagnosisClient(USERNAME, PASSWORD, AUTHURL, LANGUAGE, HEALTHURL);
            
            // Load All Symptoms
            List<HealthItem> OSymptoms = ODC.loadSymptoms();
            /*for (HealthItem x : OSymptoms)
            {
                System.out.println(x.ID + " " + x.Name);
            }*/
            
            // Load All Issues / Diseases
            List<HealthItem> OIssues = ODC.loadIssues();
            /*for (HealthItem x : OIssues)
            {
                System.out.println(x.ID + " " + x.Name);
            }*/
            
            // Selected Symptoms
            System.out.println("Assuming Symptoms: Fever, Cough, Running nose, Sneezing . . .");
            List<Integer> OSelectedSymptoms = new ArrayList<Integer>();
            for (HealthItem x : OSymptoms)
            {
                if(x.Name.equalsIgnoreCase("Fever") ||
                   x.Name.equalsIgnoreCase("Cough") ||
                   x.Name.equalsIgnoreCase("Runny nose") ||
                   x.Name.equalsIgnoreCase("Sneezing"))
                    OSelectedSymptoms.add(x.ID);
            }
            List<HealthDiagnosis> ODiagnosis = ODC.loadDiagnosis(OSelectedSymptoms,
                                                                 Gender.Male,
                                                                 1995);
            for (HealthDiagnosis x : ODiagnosis)
            {
                DiagnosedIssue x1 = x.Issue;
                HealthIssueInfo x3 = ODC.loadIssueInfo(x1.ID);
                System.out.print("Issue: " + x1.Name + " (" + x1.Accuracy + "%) (Rank " + x1.Ranking + ") - ");
                List<MatchedSpecialisation> x2 = x.Specialisation;
                for(MatchedSpecialisation y:x2)
                {
                    System.out.print(y.Name + ", ");
                }
                System.out.println("\n\tPossible Symptoms: " + x3.PossibleSymptoms);
                System.out.println("\n\tTreatment Description: " + x3.TreatmentDescription + "\n");
            }
            
            // Load All Body Locations
            List<HealthItem> OLocations = ODC.loadBodyLocations();
            /*for (HealthItem x : OLocations)
            {
                System.out.println(x.ID + " " + x.Name);
            }*/
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
