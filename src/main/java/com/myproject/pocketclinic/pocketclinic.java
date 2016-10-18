package com.myproject.pocketclinic;

import java.sql.*;
import javax.swing.JOptionPane;

public class pocketclinic
{
    // Connection Object
    public static Connection con_obj = null;
    
    // Database User Specification
    private static final String szHost = "dyndomain.net";
    private static final String szPort = "3306";
    private static final String szUsername = "root";
    private static final String szPassword = "protected";
    private static final String szDB = "db_pocketclinic";
    
    public static void main(String[] args)
    {
        /* Set up JDBC */
        try
        {
            // Connect to Database Host
            Class.forName("com.mysql.cj.jdbc.Driver");
            con_obj = DriverManager.getConnection
            (
                "jdbc:mysql://" + szHost + ":" + szPort + "/" + szDB + "?allowMultiQueries=true",
                szUsername,
                szPassword
            );
            
            Runtime.getRuntime().addShutdownHook(new Thread()
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
            });
            
            java.awt.EventQueue.invokeLater(() ->{new JFrame_Login().setVisible(true);});
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}