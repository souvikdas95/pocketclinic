package com.myproject.pocketclinic;

import java.sql.Blob;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CCase_Solution
{
    // data
    private int _case_id;
    private int _doctor_id;
    private int _issue_id;
    private String _solution;
    
    // constructors
    private CCase_Solution(int case_id,
                           int doctor_id,
                           int issue_id,
                           String solution)
    {
        this._case_id = case_id;
        this._doctor_id = doctor_id;
        this._issue_id = issue_id;
        this._solution = solution;
    }
    
    public CCase_Solution(CCase_Solution parent)
    {
        this._case_id = parent._case_id;
        this._doctor_id = parent._doctor_id;
        this._issue_id = parent._issue_id;
        this._solution = parent._solution;
    }
    
    //public attribute access methods
    public final int get_case_id()
    {
        return _case_id;
    }
    
    public final int get_doctor_id()
    {
        return _doctor_id;
    }
    
    public final int get_issue_id()
    {
        return _issue_id;
    }
    
    public final String get_solution()
    {
        return _solution;
    }
    
    // core methods
    public static boolean Register(int case_id,
                                   int doctor_id,
                                   int issue_id,
                                   String solution)
    {
        try
        {        
            // Insert new record / Register in User Table
            String sql = "INSERT INTO `case_solution` " + 
                         "(`case_id`, `doctor_id`, `issue_id`, `solution`) " +
                         "VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, case_id);
            stmt.setInt(2, doctor_id);
            stmt.setInt(3, issue_id);
            stmt.setString(4, solution);
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
    
    public static CCase_Solution Retrieve(int case_id, int doctor_id)
    {
        CCase_Solution ret = null;
        try
        {
            String sql = "SELECT * FROM `case_solution` WHERE `case_id` = ? AND `doctor_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, case_id);
            stmt.setInt(2, doctor_id);
            ResultSet rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            if(!rs.next())
            {
                if(!rs.isClosed())
                    rs.close();
                return ret;
            }
            ret = new CCase_Solution(case_id,
                                     doctor_id,
                                     rs.getInt("issue_id"),
                                     rs.getString("solution"));
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

    public final boolean commit(int issue_id,
                                String solution)
    {
        try
        {
            int sel = 0;
            String sql = "UPDATE `case_solution` SET ";
            if(!(this._issue_id == issue_id))
            {
                sql += "`bestsolution_doctor_id` = ?, ";
                sel = sel | 1;
            }
            if(!(this._solution.equals(solution)))
            {
                sql += "`end_date` = ?, ";
                sel = sel | (1 << 1);
            }
            if(sel == 0)
                return true;
            sql = sql.substring(0, sql.length() - 2);
            sql += " WHERE `case_id` = ? AND `doctor_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            byte count = 0;
            if((sel & 1) != 0)
            {
                if(issue_id == 0)
                    stmt.setNull(++count, java.sql.Types.INTEGER);
                else
                    stmt.setInt(++count, issue_id);
                this._issue_id = issue_id;
            }
            if((sel & (1 << 1)) != 0)
            {
                if(solution == null)
                    stmt.setNull(++count, java.sql.Types.DATE);
                else
                    stmt.setString(++count, solution);
                this._solution = solution;
            }
            stmt.setInt(++count, this._case_id);
            stmt.setInt(++count, this._doctor_id);
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
    
    public static final boolean Remove(int case_id, int doctor_id)
    {
        try
        {
            String sql = "DELETE FROM `case_solution` WHERE `case_id` = ? AND `doctor_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(0, case_id);
            stmt.setInt(1, doctor_id);
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
