package com.myproject.pocketclinic;

import java.sql.Blob;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CRoute_Issue
{
    // data
    private int _route_id;
    private int _issue_id;
    private int _count;
    
    // constructors
    private CRoute_Issue(int route_id,
                         int issue_id,
                         int count)
    {
        this._route_id = route_id;
        this._issue_id = issue_id;
        this._count = count;
    }
    
    public CRoute_Issue(CRoute_Issue parent)
    {
        this._route_id = parent._route_id;
        this._issue_id = parent._issue_id;
        this._count = parent._count;
    }
    
    //public attribute access methods
    public final int get_route_id()
    {
        return _route_id;
    }
    
    public final int get_issue_id()
    {
        return _issue_id;
    }
    
    public final int get_count()
    {
        return _count;
    }

    // core methods
    public static boolean Register(int route_id,
                                   int issue_id,
                                   int count)
    {
        try
        {        
            // Insert new record / Register in User Table
            String sql = "INSERT INTO `route_issue` " + 
                         "(`route_id`, `issue_id`, `count`) " +
                         "VALUES (?, ?, ?)";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, route_id);
            stmt.setInt(2, issue_id);
            stmt.setInt(3, count);
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
    
    public static CRoute_Issue Retrieve(int route_id, int issue_id)
    {
        CRoute_Issue ret = null;
        try
        {
            String sql = "SELECT * FROM `route_issue` WHERE `route_id` = ? AND `issue_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, route_id);
            stmt.setInt(2, issue_id);
            ResultSet rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            if(!rs.next())
            {
                if(!rs.isClosed())
                    rs.close();
                return ret;
            }
            ret = new CRoute_Issue(route_id,
                                   issue_id,
                                   rs.getInt("count"));
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

    public final boolean commit(int count)
    {
        try
        {
            int sel = 0;
            String sql = "UPDATE `route_issue` SET ";
            if(!(this._count == count))
            {
                sql += "`count` = ?, ";
                sel = sel | 1;
            }
            if(sel == 0)
                return true;
            sql = sql.substring(0, sql.length() - 2);
            sql += " WHERE `route_id` = ? AND `issue_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            byte _count = 0;
            if((sel & 1) != 0)
            {
                if(count < 0)
                    stmt.setNull(++_count, java.sql.Types.INTEGER);
                else
                    stmt.setInt(++_count, count);
                this._count = count;
            }
            stmt.setInt(++_count, this._route_id);
            stmt.setInt(++_count, this._issue_id);
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
    
    public static final boolean Remove(int route_id, int issue_id)
    {
        try
        {
            String sql = "DELETE FROM `route_issue` WHERE `route_id` = ? AND `issue_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, route_id);
            stmt.setInt(2, issue_id);
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
