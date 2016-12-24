/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.pay;

import common.dbconnct;
import common.message;
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
public class studentFeesMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    message mess=null;
       boolean def=true;
       Date date = new Date();
    public studentFeesMiddle() {

        con = dbconnct.connect();

    }

    public void fees(String student, String subject) {
     
        String[] det = new String[4];
        String stuName = checkStudent(student);
        if (stuName != null) {
            boolean enrolled = enrollCheck(student, subject);
            if (enrolled != false) {
                boolean bool=checkDefaulters(student,subject);
                if(bool==true){
                    mess=new message();
                    def=mess.alertBox("STUDENT HAS DEFAULTED.TO PROCEED HAVE TO PAY FOR DEFAULTED MONTHS");
                    
                }
                if(def==true){
                    deleteDefaulter(student, subject);
                updateStudentSubject(student, subject);
                det = getDetails(student, subject);

                
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int month = localDate.getMonthValue();

                int year = Calendar.getInstance().get(Calendar.YEAR);

                updateLecturerFunds(det[1], det[0], student, det[3], det[2], month, year);
                updateCompanyFunds(det[1], det[0], student, det[3], det[2], month, year);
                updateStudentHistory(student,det[0], month, year,det[3]);
                }
            }

        }

    }
    private void deleteDefaulter(String student,String subjectt){
    
         try {
            String q = "DELETE FROM defaultertable WHERE studentId='" + student + "' AND subject='" + subjectt + "' ";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
        System.out.println(e);
        }
    
    
    
    }
    private boolean checkDefaulters(String student, String Subject){
        
        
      try {
            String load = "SELECT * FROM defaultertable";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
               String stuId=rs.getString("studentId");
               String subId=rs.getString("subject");
               

                if ((stuId.equals(student))&&(subId.equals(Subject))) {

                  return true;

                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    return false;
    
    }
private void updateStudentHistory(String studentReg,String subjectName,int Month,int Year,String price){
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

  try {
            String q = "INSERT INTO studenthistory(stuId,subName,month,year,amount,date) VALUES ('" + studentReg + "','" + subjectName + "','" + String.valueOf(Month) + "','" + String.valueOf(Year) + "','" + String.valueOf(price) + "','" + String.valueOf(localDate) + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }




}
    private void updateCompanyFunds(String lecturer, String SubjectName, String studentReg, String price, String percentage, int Month, int Year) {

             LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
             String datee=String.valueOf(localDate);
               double amountCompany = Double.parseDouble(price) * (Double.parseDouble(percentage) / 100.0);

        try {
            String q = "INSERT INTO companyfunds(lecturerName,subjectName,studentId,month,year,amount,date) VALUES ('" + lecturer + "','" + SubjectName + "','" + studentReg + "','" + String.valueOf(Month) + "','" + String.valueOf(Year) + "','" + String.valueOf(amountCompany) + "','" + datee + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        
        
        
        
    }

    private void updateLecturerFunds(String lecturer, String SubjectName, String studentReg, String price, String percentage, int Month, int Year) {
          double amountCompany = Double.parseDouble(price) * (Double.parseDouble(percentage) / 100.0);
         double amountLecturer=(Double.parseDouble(price) -amountCompany);
      
        try {
            String q = "INSERT INTO lecturerfunds(lecturerName,subjectName,studentId,month,year,amount) VALUES ('" + lecturer + "','" + SubjectName + "','" + studentReg + "','" + String.valueOf(Month) + "','" + String.valueOf(Year) + "','" + String.valueOf(amountLecturer) + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private String[] getDetails(String studentId, String subject) {
        String[] sta = new String[4];
        try {
            String load = "SELECT * FROM subjectregister";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                sta[0] = rs.getString("Subject");
                sta[1] = rs.getString("Lecturer");
                sta[2] = rs.getString("Percentage");
                sta[3] = rs.getString("Price");

                if ((subject.equals(sta[0]))) {

                    return sta;

                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

    private void updateStudentSubject(String student, String subject) {

        boolean pStatus = true;
        try {
            String q = "UPDATE studentsubject SET payStatus='" + pStatus + "'WHERE studentId= '" + student + "' AND subName='" + subject + "'";

            pst = con.prepareStatement(q);
            pst.executeUpdate();

        } catch (Exception e) {
         System.out.println(e);
        }

    }

    private boolean enrollCheck(String student, String subject) {

        try {
            String load = "SELECT * FROM studentsubject";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                String regNum = rs.getString("studentId");
                String subjectName = rs.getString("subName");

                if ((student.equals(regNum)) && (subject.equals(subjectName))) {

                    return true;

                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        message m = new message();
        m.messageBox("STUDENT IS NOT ENROLLED IN THE SELECTED COURSE !");

        return false;

    }

    private String checkStudent(String student) {

        try {
            String load = "SELECT * FROM studentregister";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                String regNum = rs.getString("registerNo");
                String name = rs.getString("name");

                if (student.equals(regNum)) {

                    return name;

                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        message m = new message();
        m.messageBox("INCORRECT STUDENT NUMBER !");

        return null;

    }

}
