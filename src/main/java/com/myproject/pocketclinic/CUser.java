package com.myproject.pocketclinic;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CUser
{
    // Data Structure
    private final int id;
    
    private String username;
    private String password;
    private String name;
    private String email;
    private java.sql.Date DOB;
    private int type;
    private byte status;

    // Current User Identifier
    public static CUser cur_user = null;
    
    // "type" bit query - fixed tables
    public static final String szUserTables[] =
    {
        "patient",
        "doctor",
        "enthusiast",
        "insuranceagent",
        "admin",
    };
    
    public CUser(int id, String username,
                           String password,
                           String name,
                           String email,
                           java.sql.Date DOB,
                           int type,
                           byte status)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        this.type = type;
        this.status = status;
    }
    
    public CUser(CUser parent)
    {
        this.id = parent.id;
        this.username = parent.username;
        this.password = parent.password;
        this.name = parent.name;
        this.email = parent.email;
        this.DOB = parent.DOB;
        this.type = parent.type;
        this.status = parent.status;
    }
    
    public final int get_id()
    {
        return id;
    }
    
    public final String get_username()
    {
        return username;
    }
    
    public final String get_password()
    {
        return password;
    }
    
    public final String get_name()
    {
        return name;
    }
    
    public final String get_email()
    {
        return email;
    }
    
    public final java.sql.Date get_DOB()
    {
        return DOB;
    }
    
    public final int get_type()
    {
        return type;
    }
    
    public final byte get_status()
    {
        return status;
    }
    
    public final boolean set_password(String password)
    {
        this.password = password;
        return true;
    }
    
    public final boolean set_name(String name)
    {
        this.name = name;
        return true;
    }
    
    public final boolean set_email(String email)
    {
        this.email = email;
        return true;
    }
    
    public final boolean set_DOB(java.sql.Date DOB)
    {
        this.DOB = DOB;
        return true;
    }
    
    public final boolean set_type(int type)
    {
        this.type = type;
        return true;
    }
    
    public final boolean logout()
    {
        try
        {
            String sql = "UPDATE `user` SET " +
                         "`status` = ? WHERE `id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setByte(1, (byte)0);
            stmt.setInt(2, this.get_id());
            stmt.executeUpdate();
            stmt.close();
            status = 0;
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
    
    public final boolean canlogin()
    {
        try
        {
            String sql = "SELECT `status` FROM `user` WHERE " +
                         "`id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            if(!rs.next() || rs.getByte("status") != (byte)0)
            {
                if(!rs.isClosed())
                    rs.close();
                return false;
            }
            if(!rs.isClosed())
                rs.close();
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
    
    public final boolean login()
    {
        try
        {
            String sql = "UPDATE `user` SET " +
                         "`status` = ? WHERE `id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setByte(1, (byte)1);
            stmt.setInt(2, this.get_id());
            stmt.executeUpdate();
            stmt.close();
            status = 1;
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

    public static boolean IsValidUsername(String s)
    {
        if(s.length() < 8 || s.length() > 32)
            return false;
        return s.matches("^[a-zA-Z0-9\\._-]+$");
    }
    
    public static boolean IsValidPassword(String s)
    {
        if(s.length() < 8 || s.length() > 32)
            return false;
        return s.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$");
    }
    
    public static boolean IsValidName(String s)
    {
        if(s.length() < 3 || s.length() > 127)
            return false;
        return s.matches("^[a-zA-Z]([a-zA-Z\\s])+[a-zA-Z]$");
    }
    
    public static boolean IsValidEmail(String s)
    {
        if(s.length() < 3 || s.length() > 254)
            return false;
        return s.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
    
    public static boolean IsValidDOB(String s)
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
    
    public static boolean IsValidSelectedType(int type)
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
    
    public static CUser RetrieveUser(int id)
    {
        CUser ret = null;
        try
        {
            String sql = "SELECT * FROM `user` WHERE " +
                         "`id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            if(!rs.next())
            {
                if(!rs.isClosed())
                    rs.close();
                return ret;
            }
            ret = new CUser(rs.getInt("id"), rs.getString("username"),
                                             rs.getString("password"),
                                             rs.getString("name"),
                                             rs.getString("email"),
                                             rs.getDate("DOB"),
                                             rs.getInt("type"),
                                             rs.getByte("status"));
            if(!rs.isClosed())
                rs.close();
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
        }
        return ret;
    }
    
    public static CUser RetrieveUser(String username, String password)
    {
        try
        {
            String sql = "SELECT * FROM `user` WHERE " +
                         "`username` = ? AND " + 
                         "`password` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            if(!rs.next())
            {
                JOptionPane.showMessageDialog
                (
                    null,
                    "Wrong Username or Password!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                if(!rs.isClosed())
                    rs.close();
                return null;
            }
            CUser ret = new CUser(rs.getInt("id"), username,
                                                   password,
                                                   rs.getString("name"),
                                                   rs.getString("email"),
                                                   rs.getDate("DOB"),
                                                   rs.getInt("type"),
                                                   rs.getByte("status"));
            if(!rs.isClosed())
                rs.close();
            
            return ret;
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
            return null;
        }
    }
    
    public static boolean RegisterUser(String username, String password,
                                                       String name,
                                                       String email,
                                                       java.sql.Date DOB,
                                                       int type)
    {
        try
        {
            // Check if Email ID or Username already exists
            String sql = "SELECT * FROM `user` WHERE " + 
                         "`username` = ? OR " + 
                         "`email` = ? LIMIT 1";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            if(rs.next())
            {
                JOptionPane.showMessageDialog
                (
                    null,
                    "Account already exists!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                if(!rs.isClosed())
                    rs.close();
                return false;
            }
            if(!rs.isClosed())
                rs.close();
            
            // Insert new record / Register in User Table
            sql = "INSERT INTO `user` " + 
                  "(`username`, `password`, `name`, `email`, `DOB`, `type`, `status`) " +
                  "VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, email);
            stmt.setDate(5, DOB);
            stmt.setInt(6, (1 << type));
            stmt.setByte(7, (byte)0);
            stmt.executeUpdate();
            stmt.close();
            
            // Retrieve User ID
            sql = "SELECT `id` FROM `user` WHERE " + 
                         "`username` = ? OR " + 
                         "`email` = ? LIMIT 1";
            stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, email);
            rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            if(!rs.next())
            {
                JOptionPane.showMessageDialog
                (
                    null,
                    "Something went wrong!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                if(!rs.isClosed())
                    rs.close();
                return false;
            }
            int id = rs.getInt("id");
            if(!rs.isClosed())
                rs.close();
            
            // Register in Subuser Tables (Minimum Initialization)
            stmt = pocketclinic.con_obj.prepareStatement(sql);
            sql = "INSERT INTO `" + CUser.szUserTables[type] + "` " + 
                  "(`id`) VALUES (?)";
            stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
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
