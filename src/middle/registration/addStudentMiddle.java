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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author U Computers
 */
public class addStudentMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public addStudentMiddle() {

        con = dbconnct.connect();

    }

    public void addStudent(String stuNum, String subNamee) {
        Date date = new Date();
        String check = checkStudent(stuNum);

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int monthh = localDate.getMonthValue();

        int yearr = Calendar.getInstance().get(Calendar.YEAR);
        String datee = String.valueOf(localDate);

        if (check != null) {
            boolean alreadyEnr = checkEnrollement(stuNum, subNamee);
            if (alreadyEnr == false) {
                try {
                    String q = "INSERT INTO studentsubject(studentId,name,subName) VALUES ('" + stuNum + "','" + check + "','" + subNamee + "')";

                    pst = con.prepareStatement(q);
                    pst.execute();

                } catch (Exception e) {
                    System.out.println(e);
                }
                String regAmt = getRegAmt();
                updateStudentHistory(stuNum, subNamee, monthh, yearr, datee, regAmt);
                updateCompanyFunds(subNamee, stuNum, monthh, yearr, datee, regAmt);
               
            } else {
                message m = new message();
                m.messageBox("STUDENT ALREADY ENROLLED !");
            }
        } 
    }

    private boolean checkEnrollement(String stuNum, String subname) {

        try {
            String load = "SELECT * FROM studentsubject WHERE studentId='" + stuNum + "' AND subName='" + subname + "' ";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;

    }

    private String getRegAmt() {

        try {
            String load = "SELECT * FROM regfees";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                String reg = rs.getString("amount");
                return reg;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    private void updateStudentHistory(String stuNum, String subNamee, int monthh, int yearr, String datee, String regAmount) {

        try {
            String q = "INSERT INTO studenthistory(stuId,subName,month,year,amount,date) VALUES ('" + stuNum + "','" + subNamee + "','" + String.valueOf(monthh) + "','" + String.valueOf(yearr) + "','" + regAmount + "','" + datee + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void updateCompanyFunds(String subNamee, String stuName, int monthh, int yearr, String datee, String regAmount) {

        try {
            String q = "INSERT INTO companyfunds(subjectName,studentId,month,year,amount,date) VALUES ('" + subNamee + "','" + stuName + "','" + String.valueOf(monthh) + "','" + String.valueOf(yearr) + "','" + regAmount + "','" + datee + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

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
