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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author U Computers
 */
public class companyDailyMiddle {
     Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Date date = null;
    private double pettty=0;
    private double lecpay=0;
    private double dailyIncome=0;
    private double currentAdvance=0;
    
     public companyDailyMiddle() {

        con = dbconnct.connect();

    }
 //-------------------DAILY-----------------   
     public Double[] calculateCompanyDailyProfits(String datee) {
       
    Double[] myStringArray = new Double[5];
        
        myStringArray[0] = studentMonthlyprofits(datee);
         myStringArray[1] = paidToLecturer(datee);
        myStringArray[2] = getPettyCash(datee);
        myStringArray[3] = getAdvance(datee);
       myStringArray[4] = ((myStringArray[0] - myStringArray[1]) - myStringArray[2]) - myStringArray[3];
        return myStringArray;
    }
 private double getAdvance(String datee) {

        try {
            String load = "SELECT * FROM lectureradvance WHERE month='" + datee + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();

            while (rs.next()) {
                double Amount = Double.parseDouble(rs.getString("amount"));
                currentAdvance = currentAdvance + Amount;
            }

            return currentAdvance;

        } catch (Exception e) {
            System.out.println(e);
        }
        return currentAdvance;

    }
    private double getPettyCash(String datee) {

        try {
            String load = "SELECT * FROM pettycash WHERE date='" + datee + "' ";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                double inco = Double.parseDouble(rs.getString("amount"));
                pettty = pettty + inco;

            }

            return pettty;
        } catch (Exception e) {
            System.out.println(e);
        }
        return pettty;

    }

    private double paidToLecturer(String datee) {

        try {
            String load = "SELECT * FROM lecturerpaid WHERE date='" + datee + "' ";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                double inco = Double.parseDouble(rs.getString("amount"));
                lecpay = lecpay + inco;

            }

            return lecpay;
        } catch (Exception e) {
            System.out.println(e);
        }
        return lecpay;

    }

    private double studentMonthlyprofits(String datee) {

        try {
            String load = "SELECT * FROM studenthistory WHERE date='" + datee +"'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                double inco = Double.parseDouble(rs.getString("amount"));
                dailyIncome = dailyIncome  + inco;

            }

            return dailyIncome ;
        } catch (Exception e) {
            System.out.println(e);
        }
        return dailyIncome ;

    }
    
    
    
}
