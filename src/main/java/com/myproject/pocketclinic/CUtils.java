package com.myproject.pocketclinic;

import static com.myproject.pocketclinic.CUser.szUserTables;
import java.security.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CUtils
{
    public static String sha256(String base)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++)
            {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception e)
        {
           e.printStackTrace();
        }
        return null;
    }
    
    public static boolean IsNumeric(String str)  
    {  
        try  
        {  
            double d = Double.parseDouble(str);  
        }  
        catch(NumberFormatException e)  
        {  
            return false;
        }  
        return true;  
    }
    
    public static boolean IsValidUser_Username(String s)
    {
        if(s.length() < 8 || s.length() > 32)
            return false;
        return s.matches("^[a-zA-Z0-9\\._-]+$");
    }
    
    public static boolean IsValidUser_Password(String s)
    {
        if(s.length() < 8 || s.length() > 32)
            return false;
        return s.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$");
    }
    
    public static boolean IsValidUser_Name(String s)
    {
        if(s.length() < 3 || s.length() > 127)
            return false;
        return s.matches("^[a-zA-Z]([a-zA-Z\\s])+[a-zA-Z]$");
    }
    
    public static boolean IsValidUser_Email(String s)
    {
        if(s.length() < 3 || s.length() > 254)
            return false;
        return s.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
    
    public static boolean IsValidUser_DOB(String s)
    {
        if(s.length() != 10)
                return false;
        return s.matches("^(?:(?:31(\\\\/|-|\\\\.)(?:0?[13578]|1[02]))\\\\1|" +
                "(?:(?:29|30)(\\\\/|-|\\\\.)(?:0?[1,3-9]|1[0-2])\\\\2))" +
                "(?:(?:1[6-9]|[2-9]\\\\d)?\\\\d{2})$|^(?:29(\\\\/|-|\\\\.)" +
                "0?2\\\\3(?:(?:(?:1[6-9]|[2-9]\\\\d)?(?:0[48]|[2468][048]|" + 
                "[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))" + 
                "$|^(?:0?[1-9]|1\\\\d|2[0-8])(\\\\/|-|\\\\.)(?:(?:0?[1-9])|" + 
                "(?:1[0-2]))\\\\4(?:(?:1[6-9]|[2-9]\\\\d)?\\\\d{2})$");
    }
    
    public static boolean IsValidUser_SelectedType(int type)
    {
        if(type < 0 || type >= szUserTables.length)
            return false;
        try
        {
            if(!pocketclinic.con_obj
                    .getMetaData()
                        .getTables(null, null, CUser.szUserTables[type], null)
                            .next())
                return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog
            (
                null,
                "Something went wrong!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }
}

