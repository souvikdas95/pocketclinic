package com.myproject.pocketclinic;

import Priaid.Diagnosis.Client.DiagnosisClient;
import java.sql.*;
import javax.swing.JOptionPane;

public class pocketclinic
{
    // Connection Object
    public static Connection con_obj = null;
    public static DiagnosisClient dc_obj = null;
    
    // Database User Specification
    private static final String szDB_Host = "localhost";
    private static final String szDB_Port = "3306";
    private static final String szDB_Username = "root";
    private static final String szDB_Password = "protected";
    private static final String szDB_Name = "db_pocketclinic";
    
    // API Medic Configuration
    private static final String szAPIMedic_Username = "souvikdas95@yahoo.co.in";
    private static final String szAPIMedic_Password = "Wg25Lec6AYi94JfBw";
    private static final String szAPIMedic_AuthURL = "https://sandbox-authservice.priaid.ch/login";
    private static final String szAPIMedic_Language = "en-gb";
    private static final String szAPIMedic_HealthURL = "https://sandbox-healthservice.priaid.ch";
    
    public static void main(String[] args)
    {
        /* Set up JDBC */
        try
        {
            // Create API Medic Client
            dc_obj = new DiagnosisClient(szAPIMedic_Username,
                                         szAPIMedic_Password,
                                         szAPIMedic_AuthURL,
                                         szAPIMedic_Language,
                                         szAPIMedic_HealthURL);

            // Connect to Database Host
            Class.forName("com.mysql.cj.jdbc.Driver");
            con_obj = DriverManager.getConnection
            (
                "jdbc:mysql://" + szDB_Host + ":" + szDB_Port + "/" + szDB_Name + "?allowMultiQueries=true",
                szDB_Username,
                szDB_Password
            );
            
            /*Runtime.getRuntime().addShutdownHook(new Thread()
            {
                @Override
                public void run()
                {
                    try
                    {
                        if(con_obj != null)
                        {
                            if(CUser.cur_user != null)
                            {
                                CUser.cur_user.logout();
                                CUser.cur_user = null;
                            }
                            con_obj.close();
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });*/
            
            java.awt.EventQueue.invokeLater(() ->{new JFrame_Login().setVisible(true);});
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}