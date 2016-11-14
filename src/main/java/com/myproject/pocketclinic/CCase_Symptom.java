package com.myproject.pocketclinic;

import java.sql.Blob;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CCase_Symptom
{
    // data
    private int _case_id;
    private int _patient_id;
    private int _symptom_id;
    
    // constructors
    private CCase_Symptom(int case_id,
                           int patient_id,
                           int symptom_id)
    {
        this._case_id = case_id;
        this._patient_id = patient_id;
        this._symptom_id = symptom_id;
    }
    
    public CCase_Symptom(CCase_Symptom parent)
    {
        this._case_id = parent._case_id;
        this._patient_id = parent._patient_id;
        this._symptom_id = parent._symptom_id;
    }
    
    //public attribute access methods
    public final int get_case_id()
    {
        return _case_id;
    }
    
    public final int get_patient_id()
    {
        return _patient_id;
    }
    
    public final int get_symptom_id()
    {
        return _symptom_id;
    }
    
    // core methods
    public static boolean Register(int case_id,
                                   int patient_id,
                                   int symptom_id)
    {
        try
        {        
            // Insert new record / Register in User Table
            String sql = "INSERT INTO `case_symptom` " + 
                         "(`case_id`, `patient_id`, `symptom_id`) " +
                         "VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, case_id);
            stmt.setInt(2, patient_id);
            stmt.setInt(3, symptom_id);
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
    
    public static CCase_Symptom Retrieve(int case_id, int patient_id)
    {
        CCase_Symptom ret = null;
        try
        {
            String sql = "SELECT * FROM `case_symptom` WHERE `case_id` = ? AND `patient_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, case_id);
            stmt.setInt(2, patient_id);
            ResultSet rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            if(!rs.next())
            {
                if(!rs.isClosed())
                    rs.close();
                return ret;
            }
            ret = new CCase_Symptom(case_id,
                                     patient_id,
                                     rs.getInt("symptom_id"));
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

    public final boolean commit(int symptom_id)
    {
        try
        {
            int sel = 0;
            String sql = "UPDATE `case_symptom` SET ";
            if(!(this._symptom_id == symptom_id))
            {
                sql += "`bestsolution_patient_id` = ?, ";
                sel = sel | 1;
            }
            if(sel == 0)
                return true;
            sql = sql.substring(0, sql.length() - 2);
            sql += " WHERE `case_id` = ? AND `patient_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            byte count = 0;
            if((sel & 1) != 0)
            {
                if(symptom_id < 1)
                    stmt.setNull(++count, java.sql.Types.INTEGER);
                else
                    stmt.setInt(++count, symptom_id);
                this._symptom_id = symptom_id;
            }
            stmt.setInt(++count, this._case_id);
            stmt.setInt(++count, this._patient_id);
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
    
    public static final boolean Remove(int case_id, int patient_id)
    {
        try
        {
            String sql = "DELETE FROM `case_symptom` WHERE `case_id` = ? AND `patient_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(0, case_id);
            stmt.setInt(1, patient_id);
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
