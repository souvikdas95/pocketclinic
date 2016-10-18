package com.myproject.pocketclinic;

import java.security.*;
import java.text.SimpleDateFormat;

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
}

