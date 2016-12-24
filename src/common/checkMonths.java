/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author U Computers
 */
public class checkMonths {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Date date = null;
    LocalDate localDate = null;

    public checkMonths() {

        con = dbconnct.connect();

    }

    public boolean checkRenew() {

        date = new Date();
        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();

        try {
            String load = "SELECT * FROM monthtable ";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                int storedMonth = Integer.parseInt(rs.getString("sMonth"));
                if (storedMonth == month) {
                    return true;
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    public void changeMonths() {
        date = new Date();
        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();

        try {
            String q = "UPDATE monthtable SET sMonth='" + month + "'";

            pst = con.prepareStatement(q);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    public void checkDefaulters(){
    
    
     try {
            String load = "SELECT * FROM studentsubject ";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
               int noOfDays=Integer.parseInt(rs.getString("presentDays"));
                String payS=rs.getString("payStatus");
                String stuId=rs.getString("studentId");
                String subName=rs.getString("subName");
                if ((payS=="false")&&(noOfDays>1)) {
                    adddefaulter(stuId,subName);
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    
    
    
    
    }
    private void adddefaulter(String studId,String subName){
         LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int month = localDate.getMonthValue();

                int year = Calendar.getInstance().get(Calendar.YEAR);
    
       try {
                String q = "INSERT INTO defaultertable(studentId,subject,Year,month) VALUES ('" + studId + "','" + subName + "','" + year + "','" + month + "')";

                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);
            }
    
    
    
    
    }

    public void updateMonthlyStudentSubject() {
        String pS=null;
        int pDay=0;
        try {
            String q = "UPDATE studentsubject SET payStatus='" + pS + "',presentDays='"+pDay+"'";

            pst = con.prepareStatement(q);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
