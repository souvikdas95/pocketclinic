package com.myproject.pocketclinic;

import java.sql.Blob;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CUser
{
    // data
    private int _id;
    private String _username;
    private String _password;
    private String _name;
    private String _email;
    private java.sql.Date _DOB;
    private boolean _gender;
    private boolean _married;
    private long _phone;
    private java.sql.Date _joindate;
    private int _visibility;
    private int _type;
    private byte _status;

    // Current User Identifier
    public static CUser cur_user = null;
    
    // "type" bit query - fixed tables
    public static final String szUserTables[] =
    {
        "user_patient",
        "user_doctor",
        "user_enthusiast",
        "user_insuranceagent",
        "user_admin",
    };
    
    public static final String szGender[] =
    {
        "male",
        "female"
    };
    
    // constructors
    private CUser(int id,
                 String username,
                 String password,
                 String name,
                 String email,
                 java.sql.Date DOB,
                 Boolean gender,
                 Boolean married,
                 long phone,
                 java.sql.Date joindate,
                 int visibility,
                 int type,
                 byte status)
    {
        this._id = id;
        this._username = username;
        this._password = password;
        this._name = name;
        this._email = email;
        this._DOB = DOB;
        this._gender = gender;
        this._married = married;
        this._phone = phone;
        this._joindate = joindate;
        this._visibility = visibility;
        this._type = type;
        this._status = status;
    }
    
    public CUser(CUser parent)
    {
        this._id = parent._id;
        this._username = parent._username;
        this._password = parent._password;
        this._name = parent._name;
        this._email = parent._email;
        this._DOB = parent._DOB;
        this._gender = parent._gender;
        this._married = parent._married;
        this._phone = parent._phone;
        this._joindate = parent._joindate;
        this._visibility = parent._visibility;
        this._type = parent._type;
        this._status = parent._status;
    }
    
    //public attribute access methods
    public final int get_id()
    {
        return _id;
    }
    
    public final String get_username()
    {
        return _username;
    }
    
    public final String get_password()
    {
        return _password;
    }
    
    public final String get_name()
    {
        return _name;
    }
    
    public final String get_email()
    {
        return _email;
    }
    
    public final java.sql.Date get_DOB()
    {
        return _DOB;
    }
    
    public final Boolean get_gender()
    {
        return _gender;
    }
    
    public final Boolean get_married()
    {
        return _married;
    }
    
    public final long get_phone()
    {
        return _phone;
    }
    
    public final java.sql.Date get_joindate()
    {
        return _joindate;
    }
    
    public final int get_visibility()
    {
        return _visibility;
    }
    
    public final int get_type()
    {
        return _type;
    }
    
    public final byte get_status()
    {
        return _status;
    }
    
    // core methods
    /*public final boolean logout()
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
    }*/
    
    public static boolean Register(String username,
                                 String password,
                                 String name,
                                 String email,
                                 java.sql.Date DOB,
                                 Boolean gender,
                                 int type)
    {
        try
        {        
            // Insert new record / Register in User Table
            String sql = "INSERT INTO `user` " + 
                         "(`username`, `password`, `name`, `email`, `DOB`, `gender`, `type`) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, email);
            stmt.setDate(5, DOB);
            stmt.setBoolean(6, gender);
            stmt.setInt(7, (1 << type));
            stmt.executeUpdate();
            stmt.close();
            
            // Register in Subuser Tables (Minimum Initialization)
            stmt = pocketclinic.con_obj.prepareStatement(sql);
            sql = "INSERT INTO `" + CUser.szUserTables[type] + "` " + 
                  "(`user_id`) SELECT `id` FROM `user` WHERE `username` = ?";
            stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setString(1, username);
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
    
    public static CUser Retrieve(int id)
    {
        CUser ret = null;
        try
        {
            String sql = "SELECT * FROM `user` WHERE `id` = ?";
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
            ret = new CUser(id,
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getDate("DOB"),
                            rs.getBoolean("gender"),
                            rs.getBoolean("married"),
                            rs.getLong("phone"),
                            rs.getDate("joindate"),
                            rs.getInt("visibility"),
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
    
    public static CUser Retrieve(String username, String password)
    {
        CUser ret = null;
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
                if(!rs.isClosed())
                    rs.close();
                return ret;
            }
            ret = new CUser(rs.getInt("id"),
                            username,
                            password,
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getDate("DOB"),
                            rs.getBoolean("gender"),
                            rs.getBoolean("married"),
                            rs.getLong("phone"),
                            rs.getDate("joindate"),
                            rs.getInt("visibility"),
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
    
    public final boolean commit(String password,
                                String name,
                                String email,
                                java.sql.Date DOB,
                                boolean gender,
                                boolean married,
                                long phone,
                                java.sql.Date joindate,
                                int visibility,
                                int type,
                                byte status)
    {
        try
        {
            int sel = 0;
            String sql = "UPDATE `user` SET ";
            if(!(this._password.equals(password)))
            {
                sql += "`password` = ?, ";
                sel = sel | 1;
            }
            if(!(this._name.equals(name)))
            {
                sql += "`name` = ?, ";
                sel = sel | (1 << 1);
            }
            if(!(this._email.equals(email)))
            {
                sql += "`email` = ?, ";
                sel = sel | (1 << 2);
            }
            if(!(this._DOB.equals(DOB)))
            {
                sql += "`DOB` = ?, ";
                sel = sel | (1 << 3);
            }
            if(!(this._gender == gender))
            {
                sql += "`gender` = ?, ";
                sel = sel | (1 << 4);
            }
            if(!(this._married == married))
            {
                sql += "`married` = ?, ";
                sel = sel | (1 << 5);
            }
            if(!(this._phone == phone))
            {
                sql += "`phone` = ?, ";
                sel = sel | (1 << 6);
            }
            if(!(this._joindate.equals(joindate)))
            {
                sql += "`joindate` = ?, ";
                sel = sel | (1 << 7);
            }
            if(!(this._visibility == visibility))
            {
                sql += "`visibility` = ?, ";
                sel = sel | (1 << 8);
            }
            if(!(this._type == type))
            {
                sql += "`type` = ?, ";
                sel = sel | (1 << 9);
            }
            if(!(this._status == status))
            {
                sql += "`status` = ?, ";
                sel = sel | (1 << 10);
            }
            if(sel == 0)
                return true;
            sql = sql.substring(0, sql.length() - 2);
            sql += " WHERE `id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            byte count = 0;
            if((sel & 1) != 0)
            {
                if(password == null)
                    stmt.setNull(++count, java.sql.Types.CHAR);
                else
                    stmt.setString(++count, password);
                this._password = password;
            }
            if((sel & (1 << 1)) != 0)
            {
                if(name == null)
                    stmt.setNull(++count, java.sql.Types.CHAR);
                else
                    stmt.setString(++count, name);
                this._name = name;
            }
            if((sel & (1 << 2)) != 0)
            {
                if(email == null)
                    stmt.setNull(++count, java.sql.Types.CHAR);
                else
                    stmt.setString(++count, email);
                this._email = email;
            }
            if((sel & (1 << 3)) != 0)
            {
                if(DOB == null)
                    stmt.setNull(++count, java.sql.Types.DATE);
                else
                    stmt.setDate(++count, DOB);
                this._DOB = DOB;
            }
            if((sel & (1 << 4)) != 0)
            {
                stmt.setBoolean(++count, gender);
                this._gender = gender;
            }
            if((sel & (1 << 5)) != 0)
            {
                stmt.setBoolean(++count, married);
                this._married = married;
            }
            if((sel & (1 << 6)) != 0)
            {
                if(phone < 0)
                    stmt.setNull(++count, java.sql.Types.NUMERIC);
                else
                    stmt.setLong(++count, phone);
                this._phone = phone;
            }
            if((sel & (1 << 7)) != 0)
            {
                if(joindate == null)
                    stmt.setNull(++count, java.sql.Types.DATE);
                else
                    stmt.setDate(++count, joindate);
                this._joindate = joindate;
            }
            if((sel & (1 << 8)) != 0)
            {
                if(visibility < 0)
                    stmt.setNull(++count, java.sql.Types.INTEGER);
                else
                    stmt.setInt(++count, visibility);
                this._visibility = visibility;
            }
            if((sel & (1 << 9)) != 0)
            {
                stmt.setInt(++count, type);
                this._type = type;
            }
            if((sel & (1 << 10)) != 0)
            {
                stmt.setInt(++count, status);
                this._status = status;
            }
            stmt.setInt(++count, this._id);
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
    
    public static final boolean Remove(int id)
    {
        try
        {
            String sql = "DELETE FROM `user` WHERE `id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(0, id);
            stmt.executeUpdate(sql);
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
