package com.myproject.pocketclinic;

import java.sql.Blob;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CUser_Doctor
{
    // data
    private int _user_id;
    private int _bestcases;
    private int _solvedcases;
    private int _visibility;
    
    // constructors
    private CUser_Doctor(int user_id,
                         int bestcases,
                         int solvedcases,
                         int visibility)
    {
        this._user_id = user_id;
        this._bestcases = bestcases;
        this._solvedcases = solvedcases;
        this._visibility = visibility;
    }
    
    public CUser_Doctor(CUser_Doctor parent)
    {
        this._user_id = parent._user_id;
        this._bestcases = parent._bestcases;
        this._solvedcases = parent._solvedcases;
        this._visibility = parent._visibility;
    }
    
    //public attribute access methods
    public final int get_user_id()
    {
        return _user_id;
    }
    
    public final int get_bestcases()
    {
        return _bestcases;
    }
    
    public final int get_solvedcases()
    {
        return _solvedcases;
    }
    
    public final int get_visibility()
    {
        return _visibility;
    }
    
    // core methods
    public static boolean Register(int user_id,
                                   Byte bestcases,
                                   int solvedcases,
                                   int visibility)
    {
        try
        {        
            // Insert new record / Register in User Table
            String sql = "INSERT INTO `user_doctor` " + 
                         "(`user_id`, `bestcases`, `solvedcases`, `visibility`) " +
                         "VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, user_id);
            stmt.setByte(2, bestcases);
            stmt.setInt(3, solvedcases);
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
    
    public static CUser_Doctor Retrieve(int user_id)
    {
        CUser_Doctor ret = null;
        try
        {
            String sql = "SELECT * FROM `user_doctor` WHERE `user_id` = ?";
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
            ret = new CUser_Doctor(user_id,
                                   rs.getByte("bestcases"),
                                   rs.getInt("solvedcases"),
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

    public final boolean commit(int bestcases,
                                int solvedcases,
                                int visibility)
    {
        try
        {
            int sel = 0;
            String sql = "UPDATE `user_doctor` SET ";
            if(!(this._bestcases == bestcases))
            {
                sql += "`bestcases` = ?, ";
                sel = sel | 1;
            }
            if(!(this._solvedcases == solvedcases))
            {
                sql += "`solvedcases` = ?, ";
                sel = sel | (1 << 1);
            }
            if(!(this._visibility == visibility))
            {
                sql += "`solvedcases` = ?, ";
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
                if(bestcases < 0)
                    stmt.setNull(++count, java.sql.Types.INTEGER);
                else
                    stmt.setInt(++count, bestcases);
                this._bestcases = bestcases;
            }
            if((sel & (1 << 1)) != 0)
            {
                if(solvedcases < 0)
                    stmt.setNull(++count, java.sql.Types.INTEGER);
                else
                    stmt.setInt(++count, solvedcases);
                this._solvedcases = solvedcases;
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
            String sql = "DELETE FROM `user_doctor` WHERE `user_id` = ?";
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
