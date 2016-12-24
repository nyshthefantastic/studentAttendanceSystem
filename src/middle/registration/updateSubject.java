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
public class updateSubject {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;

    public updateSubject() {

        con = dbconnct.connect();
    }

    public void searchSubject(JTable table, String Query, String searchTerm) {

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

        String[] sta = new String[9];

        try {
            String load = "SELECT * FROM subjectregister WHERE registerNo='" + value + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            if (rs.next()) {
                sta[0] = rs.getString("registerNo");
                sta[1] = rs.getString("Subject");
                sta[2] = rs.getString("Lecturer");
                sta[3] = rs.getString("Day");
                sta[4] = rs.getString("FromTim");
                sta[5] = rs.getString("ToTim");
                sta[6] = rs.getString("Hall");
                sta[7] = rs.getString("Percentage");
                sta[8] = rs.getString("Price");

                return sta;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return sta;
    }

    public void updateSubject(String regNo, String subject, String lecturer, String day, String from, String to, String hall, String percentage, String price) {
        subjectMiddle rm = new subjectMiddle();

        boolean valid = rm.validateSubject(subject, lecturer, day, from, to, hall, percentage, price);

        if (valid == true) {
            try {
               // String q = "UPDATE subjectregister SET Subject'" + subject + "',Lecturer='" + lecturer + "',Day='" + day + "',FromTim='" + from + "',ToTim='" + to + "',Hall='" + hall + "',Percentage='" + percentage + "',Price='" + price + "'WHERE registerNo= '" + regNo + "'";
                  String q = "UPDATE subjectregister SET Subject='" + subject + "',Lecturer='" + lecturer + "',Day='" + day + "',FromTim='" + from + "',ToTim='" + to + "',Hall='" + hall + "',Percentage='" + percentage + "',Price='" + price + "'WHERE registerNo= '" + regNo + "'";

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
