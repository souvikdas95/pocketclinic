package com.myproject.pocketclinic;

import java.sql.Blob;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CUser_Patient
{
    // data
    private int _user_id;
    private byte _bloodgroup;
    private int _total_cases;
    private int _visibility;
    
    // constructors
    private CUser_Patient(int user_id,
                          byte bloodgroup,
                          int total_cases,
                          int visibility)
    {
        this._user_id = user_id;
        this._bloodgroup = bloodgroup;
        this._total_cases = total_cases;
        this._visibility = visibility;
    }
    
    public CUser_Patient(CUser_Patient parent)
    {
        this._user_id = parent._user_id;
        this._bloodgroup = parent._bloodgroup;
        this._total_cases = parent._total_cases;
        this._visibility = parent._visibility;
    }
    
    //public attribute access methods
    public final int get_user_id()
    {
        return _user_id;
    }
    
    public final byte get_bloodgroup()
    {
        return _bloodgroup;
    }
    
    public final int get_total_cases()
    {
        return _total_cases;
    }
    
    public final int get_visibility()
    {
        return _visibility;
    }
    
    // core methods
    public static boolean Register(int user_id,
                                   Byte bloodgroup,
                                   int total_cases,
                                   int visibility)
    {
        try
        {        
            // Insert new record / Register in User Table
            String sql = "INSERT INTO `user_patient` " + 
                         "(`user_id`, `bloodgroup`, `total_cases`, `visibility`) " +
                         "VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, user_id);
            stmt.setByte(2, bloodgroup);
            stmt.setInt(3, total_cases);
            stmt.setInt(4, visibility);
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
    
    public static CUser_Patient Retrieve(int user_id)
    {
        CUser_Patient ret = null;
        try
        {
            String sql = "SELECT * FROM `user_patient` WHERE `user_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            if(!rs.next())
            {
                if(!rs.isClosed())
                    rs.close();
                return ret;
            }
            ret = new CUser_Patient(user_id,
                                    rs.getByte("bloodgroup"),
                                    rs.getInt("total_cases"),
                                    rs.getInt("visibility"));
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

    public final boolean commit(byte bloodgroup,
                                int total_cases,
                                int visibility)
    {
        try
        {
            int sel = 0;
            String sql = "UPDATE `user_patient` SET ";
            if(!(this._bloodgroup == bloodgroup))
            {
                sql += "`bloodgroup` = ?, ";
                sel = sel | 1;
            }
            if(!(this._total_cases == total_cases))
            {
                sql += "`total_cases` = ?, ";
                sel = sel | (1 << 1);
            }
            if(!(this._visibility == visibility))
            {
                sql += "`total_cases` = ?, ";
                sel = sel | (1 << 2);
            }
            if(sel == 0)
                return true;
            sql = sql.substring(0, sql.length() - 2);
            sql += " WHERE `user_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            byte count = 0;
            if((sel & 1) != 0)
            {
                if(bloodgroup < 0)
                    stmt.setNull(++count, java.sql.Types.TINYINT);
                else
                    stmt.setByte(++count, bloodgroup);
                this._bloodgroup = bloodgroup;
            }
            if((sel & (1 << 1)) != 0)
            {
                if(total_cases < 0)
                    stmt.setNull(++count, java.sql.Types.INTEGER);
                else
                    stmt.setInt(++count, total_cases);
                this._total_cases = total_cases;
            }
            if((sel & (1 << 2)) != 0)
            {
                if(visibility < 0)
                    stmt.setNull(++count, java.sql.Types.INTEGER);
                else
                    stmt.setInt(++count, visibility);
                this._visibility = visibility;
            }
            stmt.setInt(++count, this._user_id);
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
    
    public static final boolean Remove(int user_id)
    {
        try
        {
            String sql = "DELETE FROM `user_patient` WHERE `user_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, user_id);
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
