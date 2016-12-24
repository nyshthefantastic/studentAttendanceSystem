package middle.attendance;

import common.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author U Computers
 */
public class attendanceMiddle {
    
       Connection con = null;
    PreparedStatement pst = null;
    Statement stmt=null;
    ResultSet rs = null;
    checkMonths cm=null;
      public attendanceMiddle() {
        
         con = dbconnct.connect();
        
    }
      
      public void attend(String regNumber,String subject ){
         
          generalAttendance(regNumber,subject);
          permenantAttendance(regNumber,subject);
      
      
      
      
      
      
      }
   public void permenantAttendance(String regNumber,String subject){
       Date date = new Date();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int month = localDate.getMonthValue();

                int year = Calendar.getInstance().get(Calendar.YEAR);
   
    try {
                String q = "INSERT INTO permananthistory(subjectName,studentId,Year,Month,Datee) VALUES ('" + subject + "','" + regNumber + "','" + String.valueOf(year) + "','" + String.valueOf(month) + "','" + String.valueOf(localDate) + "')";

                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);
            }

   
   
   
   }   
    public void generalAttendance(String regNumber,String subject){
          try {
                String q = "UPDATE studentsubject SET presentDays=presentDays+'" + 1 +  "'WHERE studentId= '" + regNumber + "'AND subName='" + subject + "'";

                pst = con.prepareStatement(q);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }
    
    
    
    
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
    
    
    
}
