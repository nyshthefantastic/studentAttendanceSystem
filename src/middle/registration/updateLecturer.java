/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.registration;

import common.dbconnct;
import common.message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author U Computers
 */
public class updateLecturer {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;

    public updateLecturer() {

        con = dbconnct.connect();
    }

    public void searchLecturer(JTable table, String Query, String searchTerm) {

        try {

            stmt = con.createStatement();
            rs = stmt.executeQuery(Query);

            //To remove previously added rows
            while (table.getRowCount() > 0) {
                ((DefaultTableModel) table.getModel()).removeRow(0);
            }
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                ((DefaultTableModel) table.getModel()).insertRow(rs.getRow() - 1, row);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
        }

    }

    public String[] populateFields(String value) {

        String[] sta = new String[5];

        try {
            String load = "SELECT * FROM lecturerregister WHERE registerNo='" + value + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            if (rs.next()) {
                sta[0] = rs.getString("registerNo");
                sta[1] = rs.getString("name");
                sta[2] = rs.getString("address");

                sta[3] = rs.getString("mobileNum");
                sta[4] = rs.getString("email");

                return sta;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return sta;
    }

    public void updateStudent(String regNo, String Name, String address, String mobileNum, String email) {
        lecturerMiddle lM = new lecturerMiddle();

        boolean valid = lM.validateRegister(Name, address, mobileNum, email);

        if (valid == true) {
            try {
                String q = "UPDATE lecturerregister SET name='" + Name + "',address='" + address + "',mobileNum='" + mobileNum + "',email='" + email + "'WHERE RegisterNo= '" + regNo + "'";

                pst = con.prepareStatement(q);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }

            mess = new message();
            mess.messageBox("UPDATE SUCCESSFUL");
        }
    }

}
