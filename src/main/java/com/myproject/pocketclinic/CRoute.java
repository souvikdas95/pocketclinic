package com.myproject.pocketclinic;

import java.sql.Blob;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CRoute
{
    // data
    private int _id;
    private String _name;
    private int _p_id;
    private int _level;
    
    // constructors
    private CRoute(int id,
                 String name,
                 int p_id,
                 int level)
    {
        this._id = id;
        this._name = name;
        this._p_id = p_id;
        this._level = level;
    }
    
    public CRoute(CRoute parent)
    {
        this._id = parent._id;
        this._name = parent._name;
        this._p_id = parent._p_id;
        this._level = parent._level;
    }
    
    //public attribute access methods
    public final int get_id()
    {
        return _id;
    }
    
    public final String get_name()
    {
        return _name;
    }
    
    public final int get_p_id()
    {
        return _p_id;
    }
    
    public final int get_level()
    {
        return _level;
    }

    // core methods
    public static boolean Register(String name,
                                   int p_id,
                                   int level)
    {
        try
        {        
            // Insert new record / Register in User Table
            String sql = "INSERT INTO `route` " + 
                         "(`name`, `p_id`, `level`) " +
                         "VALUES (?, ?, ?)";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, p_id);
            stmt.setInt(3, level);
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
    
    public static CRoute Retrieve(int id)
    {
        CRoute ret = null;
        try
        {
            String sql = "SELECT * FROM `case` WHERE `id` = ?";
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
            ret = new CRoute(id,
                             rs.getString("name"),
                             rs.getInt("p_id"),
                             rs.getInt("level"));
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

    public final boolean commit(String name,
                                int p_id,
                                int level)
    {
        try
        {
            int sel = 0;
            String sql = "UPDATE `case` SET ";
            if(!(this._name.equals(name)))
            {
                sql += "`name` = ?, ";
                sel = sel | 1;
            }
            if(!(this._p_id == p_id))
            {
                sql += "`p_id` = ?, ";
                sel = sel | (1 << 1);
            }
            if(!(this._level == level))
            {
                sql += "`level` = ?, ";
                sel = sel | (1 << 2);
            }
            if(sel == 0)
                return true;
            sql = sql.substring(0, sql.length() - 2);
            sql += " WHERE `id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            byte count = 0;
            if((sel & 1) != 0)
            {
                if(name == null)
                    stmt.setNull(++count, java.sql.Types.CHAR);
                else
                    stmt.setString(++count, name);
                this._name = name;
            }
            if((sel & (1 << 1)) != 0)
            {
                if(p_id == 0)
                    stmt.setNull(++count, java.sql.Types.INTEGER);
                else
                    stmt.setInt(++count, p_id);
                this._p_id = p_id;
            }
            if((sel & (1 << 2)) != 0)
            {
                if(level == 0)
                    stmt.setNull(++count, java.sql.Types.INTEGER);
                else
                    stmt.setInt(++count, level);
                this._level = level;
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
            String sql = "DELETE FROM `case` WHERE `id` = ?";
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
