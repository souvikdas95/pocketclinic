package com.myproject.pocketclinic;

import java.sql.Blob;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CCase
{
    // data
    private int _id;
    private int _patient_id;
    private String _name;
    private java.sql.Date _start_date;
    private java.sql.Date _end_date;
    private String _description;
    private byte[] _photo;
    private int _bestsolution_doctor_id;
    
    // constructors
    private CCase(int id,
                 int patient_id,
                 String name,
                 java.sql.Date start_date,
                 java.sql.Date end_date,
                 String description,
                 byte[] photo,
                 int bestsolution_doctor_id)
    {
        this._id = id;
        this._patient_id = patient_id;
        this._name = name;
        this._start_date = start_date;
        this._end_date = end_date;
        this._description = description;
        this._photo = photo;
        this._bestsolution_doctor_id = bestsolution_doctor_id;
    }
    
    public CCase(CCase parent)
    {
        this._id = parent._id;
        this._patient_id = parent._patient_id;
        this._name = parent._name;
        this._start_date = parent._start_date;
        this._end_date = parent._end_date;
        this._description = parent._description;
        this._photo = parent._photo;
        this._bestsolution_doctor_id = parent._bestsolution_doctor_id;
    }
    
    //public attribute access methods
    public final int get_id()
    {
        return _id;
    }
    
    public final int get_patient_id()
    {
        return _patient_id;
    }
    
    public final String get_name()
    {
        return _name;
    }
    
    public final java.sql.Date get_start_date()
    {
        return _start_date;
    }
    
    public final java.sql.Date get_end_date()
    {
        return _end_date;
    }
    
    public final String get_description()
    {
        return _description;
    }
    
    public final byte[] get_photo()
    {
        return _photo;
    }
    
    public final int get_bestsolution_doctor_id()
    {
        return _bestsolution_doctor_id;
    }
    
    // core methods
    public static boolean Register(int patient_id,
                                 String name,
                                 java.sql.Date start_date,
                                 String description,
                                 byte[] photo)
    {
        try
        {        
            // Convert photo to BLOB
            Blob image = pocketclinic.con_obj.createBlob();
            image.setBytes(1, photo);

            // Insert new record / Register in User Table
            String sql = "INSERT INTO `case` " + 
                         "(`patient_id`, `name`, `start_date`, `description`, `photo`) " +
                         "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(1, patient_id);
            stmt.setString(2, name);
            stmt.setDate(3, start_date);
            stmt.setString(4, description);
            stmt.setBlob(5, image);
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
    
    public static CCase Retrieve(int id)
    {
        CCase ret = null;
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
            ret = new CCase(id,
                            rs.getInt("patient_id"),
                            rs.getString("name"),
                            rs.getDate("start_date"),
                            rs.getDate("end_date"),
                            rs.getString("description"),
                            rs.getBytes("photo"),
                            rs.getInt("bestsolution_doctor_id"));
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

    public final boolean commit(java.sql.Date end_date,
                                int bestsolution_doctor_id)
    {
        try
        {
            int sel = 0;
            String sql = "UPDATE `case` SET ";
            if(!(this._end_date.equals(end_date)))
            {
                sql += "`end_date` = ?, ";
                sel = sel | 1;
            }
            if(!(this._bestsolution_doctor_id == bestsolution_doctor_id))
            {
                sql += "`bestsolution_doctor_id` = ?, ";
                sel = sel | (1 << 1);
            }
            if(sel == 0)
                return true;
            sql = sql.substring(0, sql.length() - 2);
            sql += " WHERE `id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            byte count = 0;
            if((sel & 1) != 0)
            {
                if(end_date == null)
                    stmt.setNull(++count, java.sql.Types.DATE);
                else
                    stmt.setDate(++count, end_date);
                this._end_date = end_date;
            }
            if((sel & (1 << 1)) != 0)
            {
                if(bestsolution_doctor_id == 0)
                    stmt.setNull(++count, java.sql.Types.INTEGER);
                else
                    stmt.setInt(++count, bestsolution_doctor_id);
                this._bestsolution_doctor_id = bestsolution_doctor_id;
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
    
    public static final boolean Remove(int case_id)
    {
        try
        {
            String sql = "DELETE FROM `case` WHERE `case_id` = ?";
            PreparedStatement stmt = pocketclinic.con_obj.prepareStatement(sql);
            stmt.setInt(0, case_id);
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
