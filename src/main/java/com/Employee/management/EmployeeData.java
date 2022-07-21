package com.Employee.management;


import java.sql.*;
import javax.swing.*;
public class EmployeeData {

    public static Connection ConnectDB()
    {
        try {
            Class.forName("org.sqlite.jdbc");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:empdata.db");
            JOptionPane.showMessageDialog(null,"Connection Made");
            return conn;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Connection Made");
            return null;
        }
    }

}

