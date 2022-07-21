package com.Employee.management;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.sql.*;


public class Employee {

    private JFrame frame;
    private JTextField jtxtEmployeeID;
    private JTable table;
    private JTextField jtxtNINumber;
    private JTextField jtxtFirstName;
    private JTextField jtxtSurName;
    private JTextField jtxtGender;
    private JTextField jtxtDOB;
    private JTextField jtxtAge;
    private JTextField jtxtSalary;

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    DefaultTableModel model = new DefaultTableModel();

    /**
     * Launch the application.
     */

    public void updateTable() {
        conn = EmployeeData.ConnectDB();
        if(conn!=null)
        {String sql = "Select EmpID, NINumber, FirstName, SurName, Gender, DOB, Age, Salary";

            try
            {
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                Object[] columnData = new Object[8];
                while(rs.next()) {
                    columnData [0] = rs.getString("EmpID");
                    columnData [1] = rs.getString("NINumber");
                    columnData [2] = rs.getString("FirstName");
                    columnData [3] = rs.getString("SurName");
                    columnData [4] = rs.getString("Gender");
                    columnData [5] = rs.getString("DOB");
                    columnData [6] = rs.getString("Age");
                    columnData [7] = rs.getString("Salary");

                    model.addRow(columnData);
                }
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }}

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Employee window = new Employee();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Employee() {
        initialize();

        conn = EmployeeData.ConnectDB();
        Object col[] = {"EmpID","NINumber","FirstName","SurName","Gender","DOB","Age","Salary"};
        model.setColumnIdentifiers(col);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(0,0,1450,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Employee ID");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(54, 54, 159, 23);
        frame.getContentPane().add(lblNewLabel);

        jtxtEmployeeID = new JTextField();
        jtxtEmployeeID.setFont(new Font("Tahoma", Font.BOLD, 18));
        jtxtEmployeeID.setBounds(276, 54, 203, 38);
        frame.getContentPane().add(jtxtEmployeeID);
        jtxtEmployeeID.setColumns(10);

        JButton btnNewButton = new JButton("Add New");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql = "INSERT INTO employee(EmpID, NINumber, FirstName, SurName, Gender, DOB, Age, Salary)VALUES(?,?,?,?,?,?,?,?)";
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, jtxtEmployeeID.getText());
                    pst.setString(1, jtxtNINumber.getText());
                    pst.setString(1, jtxtFirstName.getText());
                    pst.setString(1, jtxtSurName.getText());
                    pst.setString(1, jtxtGender.getText());
                    pst.setString(1, jtxtDOB.getText());
                    pst.setString(1, jtxtAge.getText());
                    pst.setString(1, jtxtSalary.getText());

                    pst.execute();
                    rs.close();
                    pst.close();
                }
                catch(Exception ev) {
                    JOptionPane.showMessageDialog(null, "System Update Complete");
                }
                DefaultTableModel model =   (DefaultTableModel) table.getModel();
                model  .addRow(new Object[] {
                        jtxtEmployeeID.getText(),
                        jtxtNINumber.getText(),
                        jtxtFirstName.getText(),
                        jtxtSurName.getText(),
                        jtxtGender.getText(),
                        jtxtDOB.getText(),
                        jtxtAge.getText(),
                        jtxtSalary.getText(),
                });
                if(table.getSelectedRow() == -1) {
                    if(table.getSelectedColumn()==0) {
                        JOptionPane.showMessageDialog(null, "Membership Update Confirmed", "Employee Database System",
                                JOptionPane.OK_OPTION);
                    }
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setBounds(68, 477, 184, 53);
        frame.getContentPane().add(btnNewButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(544, 84, 473, 351);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null, null, null, null, ""},
                },
                new String[] {
                        "Emp ID", "NINumber", "FirstName", "SurName", "Gender", "DOB", "Age", "Salary"
                }
        ));
        table.setFont(new Font("Tahoma", Font.BOLD, 14));
        scrollPane.setViewportView(table);

        JLabel lblNiNumber = new JLabel("NI Number");
        lblNiNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNiNumber.setBounds(54, 103, 159, 23);
        frame.getContentPane().add(lblNiNumber);

        jtxtNINumber = new JTextField();
        jtxtNINumber.setFont(new Font("Tahoma", Font.BOLD, 18));
        jtxtNINumber.setColumns(10);
        jtxtNINumber.setBounds(276, 103, 203, 38);
        frame.getContentPane().add(jtxtNINumber);

        jtxtFirstName = new JTextField();
        jtxtFirstName.setFont(new Font("Tahoma", Font.BOLD, 18));
        jtxtFirstName.setColumns(10);
        jtxtFirstName.setBounds(276, 152, 203, 38);
        frame.getContentPane().add(jtxtFirstName);

        jtxtSurName = new JTextField();
        jtxtSurName.setFont(new Font("Tahoma", Font.BOLD, 18));
        jtxtSurName.setColumns(10);
        jtxtSurName.setBounds(276, 201, 203, 38);
        frame.getContentPane().add(jtxtSurName);

        jtxtGender = new JTextField();
        jtxtGender.setFont(new Font("Tahoma", Font.BOLD, 18));
        jtxtGender.setColumns(10);
        jtxtGender.setBounds(276, 250, 203, 38);
        frame.getContentPane().add(jtxtGender);

        jtxtDOB = new JTextField();
        jtxtDOB.setFont(new Font("Tahoma", Font.BOLD, 18));
        jtxtDOB.setColumns(10);
        jtxtDOB.setBounds(276, 299, 203, 38);
        frame.getContentPane().add(jtxtDOB);

        jtxtAge = new JTextField();
        jtxtAge.setFont(new Font("Tahoma", Font.BOLD, 18));
        jtxtAge.setColumns(10);
        jtxtAge.setBounds(276, 348, 203, 38);
        frame.getContentPane().add(jtxtAge);

        jtxtSalary = new JTextField();
        jtxtSalary.setFont(new Font("Tahoma", Font.BOLD, 18));
        jtxtSalary.setColumns(10);
        jtxtSalary.setBounds(276, 397, 203, 38);
        frame.getContentPane().add(jtxtSalary);

        JLabel lblNewLabel_1_1 = new JLabel("FirstName");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(54, 152, 159, 23);
        frame.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("SurName");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_2.setBounds(54, 201, 159, 23);
        frame.getContentPane().add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("Gender");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_3.setBounds(54, 250, 159, 23);
        frame.getContentPane().add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_4 = new JLabel("DOB");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_4.setBounds(54, 299, 159, 23);
        frame.getContentPane().add(lblNewLabel_1_4);

        JLabel lblNewLabel_1_5 = new JLabel("Age");
        lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_5.setBounds(54, 348, 159, 23);
        frame.getContentPane().add(lblNewLabel_1_5);

        JLabel lblNewLabel_1_6 = new JLabel("Salary");
        lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_6.setBounds(54, 397, 159, 23);
        frame.getContentPane().add(lblNewLabel_1_6);

        JButton btnPrint = new JButton("Print");
        btnPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MessageFormat header = new MessageFormat("Printing in Progress");
                MessageFormat footer = new MessageFormat("Page {0,number,integer}");
                try
                {
                    table.print();
                }
                catch(java.awt.print.PrinterException ev) {
                    System.err.format("No Printer Found", ev.getMessage());
                }
            }
        });
        btnPrint.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnPrint.setBounds(295, 477, 184, 53);
        frame.getContentPane().add(btnPrint);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jtxtEmployeeID.setText(null);
                jtxtNINumber.setText(null);
                jtxtFirstName.setText(null);
                jtxtSurName.setText(null);
                jtxtGender.setText(null);
                jtxtDOB.setText(null);
                jtxtAge.setText(null);
                jtxtSalary.setText(null);
            }
        });
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnReset.setBounds(520, 477, 184, 53);
        frame.getContentPane().add(btnReset);

        JButton btnExit = new JButton("EXIT");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame = new JFrame("Exit");
                if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit","Employee Database System",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnExit.setBounds(750, 477, 184, 53);
        frame.getContentPane().add(btnExit);

        JLabel lblNewLabel_1 = new JLabel("Employee Database Management System");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel_1.setBounds(221, 11, 606, 23);
        frame.getContentPane().add(lblNewLabel_1);
    }
}


