/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.reports;

import common.dbconnct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author U Computers
 */
public class detailedAttendanceReportMiddle {
    
    
    
     Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
     Statement stmt=null;
    public detailedAttendanceReportMiddle() {
        con = dbconnct.connect();
        
    }
     public void searchAttendance(JTable table, String Query, String searchTerm) {

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
}
