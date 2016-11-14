package com.myproject.pocketclinic;

import java.awt.event.WindowEvent;
import javax.swing.*;
import java.sql.*;

public class JFrame_Login extends javax.swing.JFrame
{
    // Overhead
    public JFrame_Login()
    {
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
        jButton_Register = new javax.swing.JButton();
        jButton_Submit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 400, 275));
        setMaximumSize(new java.awt.Dimension(411, 178));
        setMinimumSize(new java.awt.Dimension(411, 178));
        setName("Login"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(411, 178));

        jLabel_Login.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_Login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Login.setText("Login");

        jLabel_Username.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_Username.setText("Username");

        jTextField_Username.setToolTipText("Length: 8-32 Characters(a-z,A-Z,0-9)");

        jLabel_Password.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Password.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_Password.setText("Password");

        jPasswordField_Password.setToolTipText("Length: 8-32 Characters(a-z,A-Z,0-9)");

        jButton_Register.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_Register.setText("Register");
        jButton_Register.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Register.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_RegisterActionPerformed(evt);
            }
        });

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton_Register, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Username, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(jPasswordField_Password)
                            .addComponent(jButton_Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Login)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Username)
                    .addComponent(jTextField_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Password)
                    .addComponent(jPasswordField_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Register)
                    .addComponent(jButton_Submit))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_SubmitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_SubmitActionPerformed
    {//GEN-HEADEREND:event_jButton_SubmitActionPerformed
        // Get & Validate Username
        String username = jTextField_Username.getText().trim();
        if(!CUtils.IsValidUser_Username(username))
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
        if(!CUtils.IsValidUser_Password(password))
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
        
        // Initialize Current User
        CUser.cur_user = CUser.Retrieve(username, password);
        if(CUser.cur_user == null)
        {
            JOptionPane.showMessageDialog
            (
                null,
                "Incorrect Username or Password!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        /*if(!CUser.cur_user.canlogin())
        {
            JOptionPane.showMessageDialog
            (
                null,
                "User already Logged-In elsewhere!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        if(CUser.cur_user.login())
        {
            JOptionPane.showMessageDialog
            (
                null,
                "Login Successful",
                "Info",
                JOptionPane.INFORMATION_MESSAGE
            );
            CUser.cur_user.logout();
            //new JFrame_Home(this).setVisible(true);
            //this.setVisible(false);
        }*/
        new JFrame_Home().setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton_SubmitActionPerformed

    private void jButton_RegisterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_RegisterActionPerformed
    {//GEN-HEADEREND:event_jButton_RegisterActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new JFrame_Register(this).setVisible(true);
    }//GEN-LAST:event_jButton_RegisterActionPerformed

    public static void main(String args[])
    {
        // Background Task
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Register;
    private javax.swing.JButton jButton_Submit;
    private javax.swing.JLabel jLabel_Login;
    private javax.swing.JLabel jLabel_Password;
    private javax.swing.JLabel jLabel_Username;
    private javax.swing.JPasswordField jPasswordField_Password;
    private javax.swing.JTextField jTextField_Username;
    // End of variables declaration//GEN-END:variables
}
