package com.myproject.pocketclinic;

import Priaid.Diagnosis.Client.*;
import Priaid.Diagnosis.Model.*;
import java.util.ArrayList;
import java.util.HashSet;
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
            HashSet<String> SpecialisationList = new HashSet<String>();
            List<HealthItem> OSymptoms = ODC.loadSymptoms();
            for (HealthItem x : OSymptoms)
            {
                /*System.out.println(x.ID + " " + x.Name);*/
                ArrayList<Integer> temp = new ArrayList();
                temp.add(x.ID);
                List<HealthDiagnosis> ODiagnosis = ODC.loadDiagnosis(temp,
                                                     Gender.Male,
                                                     0);
                for (HealthDiagnosis y:ODiagnosis)
                {
                    List<MatchedSpecialisation> temp2 = y.Specialisation;
                    for (MatchedSpecialisation z:temp2)
                    {
                        SpecialisationList.add(z.Name);
                    }
                }
                /*ODiagnosis = ODC.loadDiagnosis(temp,
                                                Gender.Female,
                                                0);
                for (HealthDiagnosis y:ODiagnosis)
                {
                    List<MatchedSpecialisation> temp2 = y.Specialisation;
                    for (MatchedSpecialisation z:temp2)
                    {
                        SpecialisationList.add(z.Name);
                    }
                }*/
            }
            for (String xyz: SpecialisationList)
            {
                System.out.println(xyz);
            }
            
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
