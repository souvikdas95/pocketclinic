package com.myproject.pocketclinic;

import javax.swing.*;
import java.sql.*;

public class JFrame_Register extends JFrame
{
    // Overhead
    public JFrame_Register(JFrame prev)
    {
        this.prev = prev;
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel_Login = new javax.swing.JLabel();
        jLabel_Username = new javax.swing.JLabel();
        jTextField_Username = new javax.swing.JTextField();
        jLabel_Password = new javax.swing.JLabel();
        jPasswordField_Password = new javax.swing.JPasswordField();
        jButton_Back = new javax.swing.JButton();
        jLabel_Name = new javax.swing.JLabel();
        jTextField_Name = new javax.swing.JTextField();
        jLabel_DOB = new javax.swing.JLabel();
        jButton_Submit = new javax.swing.JButton();
        jLabel_Account_Type = new javax.swing.JLabel();
        jComboBox_Account_Type = new javax.swing.JComboBox();
        jLabel_EmailID = new javax.swing.JLabel();
        jTextField_EmailID = new javax.swing.JTextField();
        jDateChooser_DOB = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(400, 389));
        setMinimumSize(new java.awt.Dimension(400, 389));
        setName("Register"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(400, 339));

        jLabel_Login.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_Login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Login.setText("Register");

        jLabel_Username.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_Username.setText("Username");

        jTextField_Username.setToolTipText("Length: 8-32 Characters(a-z,A-Z,0-9)");
        jTextField_Username.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_UsernameActionPerformed(evt);
            }
        });

        jLabel_Password.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Password.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_Password.setText("Password");

        jPasswordField_Password.setToolTipText("Length: 8-32 Characters(a-z,A-Z,0-9)");
        jPasswordField_Password.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jPasswordField_PasswordActionPerformed(evt);
            }
        });

        jButton_Back.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_Back.setText("Back");
        jButton_Back.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Back.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_BackActionPerformed(evt);
            }
        });

        jLabel_Name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_Name.setText("Name");
        jLabel_Name.setToolTipText("");

        jTextField_Name.setToolTipText("Length: 8-32 Letters incl Spaces & '.'");
        jTextField_Name.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_NameActionPerformed(evt);
            }
        });

        jLabel_DOB.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_DOB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_DOB.setText("DOB");

        jButton_Submit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_Submit.setText("Submit");
        jButton_Submit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Submit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_SubmitActionPerformed(evt);
            }
        });

        jLabel_Account_Type.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Account_Type.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_Account_Type.setText("Account Type");
        jLabel_Account_Type.setToolTipText("");

        jComboBox_Account_Type.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_Account_Type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Patient", "Doctor", "Enthusiast", "Insurance Agent", "Admin" }));
        jComboBox_Account_Type.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_Account_TypeActionPerformed(evt);
            }
        });

        jLabel_EmailID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_EmailID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_EmailID.setText("Email ID");
        jLabel_EmailID.setToolTipText("");

        jTextField_EmailID.setToolTipText("Length: 8-32 Letters incl Spaces & '.'");
        jTextField_EmailID.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_EmailIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_EmailID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jTextField_EmailID, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(64, 64, 64)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPasswordField_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(64, 64, 64)
                                    .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel_Account_Type, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel_DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(64, 64, 64)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton_Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox_Account_Type, 0, 108, Short.MAX_VALUE)
                                        .addComponent(jDateChooser_DOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel_Login)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Username)
                    .addComponent(jTextField_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Password)
                    .addComponent(jPasswordField_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Name)
                    .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_EmailID)
                    .addComponent(jTextField_EmailID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_DOB)
                    .addComponent(jDateChooser_DOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Account_Type)
                    .addComponent(jComboBox_Account_Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Back))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Submit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Payload
    private final JFrame prev;
    
    private void jTextField_UsernameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_UsernameActionPerformed
    {//GEN-HEADEREND:event_jTextField_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_UsernameActionPerformed

    private void jButton_BackActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_BackActionPerformed
    {//GEN-HEADEREND:event_jButton_BackActionPerformed
        // TODO add your handling code here:
        prev.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton_BackActionPerformed

    private void jPasswordField_PasswordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jPasswordField_PasswordActionPerformed
    {//GEN-HEADEREND:event_jPasswordField_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField_PasswordActionPerformed

    private void jTextField_NameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_NameActionPerformed
    {//GEN-HEADEREND:event_jTextField_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NameActionPerformed

    private void jButton_SubmitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_SubmitActionPerformed
    {//GEN-HEADEREND:event_jButton_SubmitActionPerformed
        // Get & Validate User Type
        byte type = (byte) jComboBox_Account_Type.getSelectedIndex();
        if(!CUser.IsValidSelectedType(type))
        {
            JOptionPane.showMessageDialog
            (
                null,
                "Account Type is not Functional!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Get & Validate Username
        String username = jTextField_Username.getText().trim();
        if(!CUser.IsValidUsername(username))
        {
            JOptionPane.showMessageDialog
            (
                null,
                "Invalid Username",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Get & Validate Password
        String password = String.valueOf(jPasswordField_Password.getPassword()).trim();
        if(!CUser.IsValidPassword(password))
        {
            JOptionPane.showMessageDialog
            (
                null,
                "Invalid Password",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        password = CUtils.sha256(password);
        
        // Get & Validate Name
        String name = jTextField_Name.getText().trim();
        if(!CUser.IsValidName(name))
        {
            JOptionPane.showMessageDialog
            (
                null,
                "Invalid Name",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        // Get & Validate EmailID
        String email = jTextField_EmailID.getText().trim();
        if(!CUser.IsValidEmail(email))
        {
            JOptionPane.showMessageDialog
            (
                null,
                "Invalid EmailID",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        // Get & Validate DOB
        /*if(!CUser.IsValidDOB(dateChooserCombo_DOB.getText().trim()))
        {
            JOptionPane.showMessageDialog
            (
                null,
                "Invalid DOB",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }*/
        java.sql.Date DOB = new java.sql.Date(
                    jDateChooser_DOB.getCalendar().getTime().getTime());
        
        // Register User
        if (CUser.RegisterUser(username, password, name, email, DOB, type))
        {
            JOptionPane.showMessageDialog
            (
                null,
                "Registration Successful",
                "Info",
                JOptionPane.INFORMATION_MESSAGE
            );
            prev.setVisible(true);
            this.dispose();
            return;
        }
    }//GEN-LAST:event_jButton_SubmitActionPerformed

    private void jComboBox_Account_TypeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBox_Account_TypeActionPerformed
    {//GEN-HEADEREND:event_jComboBox_Account_TypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Account_TypeActionPerformed

    private void jTextField_EmailIDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_EmailIDActionPerformed
    {//GEN-HEADEREND:event_jTextField_EmailIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_EmailIDActionPerformed

    public static void main(String args[])
    {
        // Background Task
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_Submit;
    private javax.swing.JComboBox jComboBox_Account_Type;
    private com.toedter.calendar.JDateChooser jDateChooser_DOB;
    private javax.swing.JLabel jLabel_Account_Type;
    private javax.swing.JLabel jLabel_DOB;
    private javax.swing.JLabel jLabel_EmailID;
    private javax.swing.JLabel jLabel_Login;
    private javax.swing.JLabel jLabel_Name;
    private javax.swing.JLabel jLabel_Password;
    private javax.swing.JLabel jLabel_Username;
    private javax.swing.JPasswordField jPasswordField_Password;
    private javax.swing.JTextField jTextField_EmailID;
    private javax.swing.JTextField jTextField_Name;
    private javax.swing.JTextField jTextField_Username;
    // End of variables declaration//GEN-END:variables
}
