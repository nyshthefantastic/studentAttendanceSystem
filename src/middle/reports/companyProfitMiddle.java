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
public class companyProfitMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Date date = null;
    double monthlyIncome = 0;
    double lecpay = 0;
    double pettty = 0;
    private double currentAdvance = 0;

    public companyProfitMiddle() {

        con = dbconnct.connect();

    }
//-------------------NONTHLY--------------------------------------

    public Double[] calculateCompanyMonthlyProfits(String yearr,String monthh) {
      
        Double[] myStringArray = new Double[5];
       
        myStringArray[0] = studentMonthlyprofits(monthh, yearr);
        myStringArray[1] = paidToLecturer(monthh, yearr);
        myStringArray[2] = getPettyCash(monthh, yearr);
        myStringArray[3] = getAdvance(monthh, yearr);
        myStringArray[4] = ((myStringArray[0] - myStringArray[1]) - myStringArray[2]) - myStringArray[3];
        return myStringArray;
    }

    private double getAdvance(String monthh, String yearr) {

        try {
            String load = "SELECT * FROM lectureradvance WHERE month='" + monthh + "' AND year='" + yearr + "'";

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

    private double getPettyCash(String monthh, String yearr) {

        try {
            String load = "SELECT * FROM pettycash WHERE month='" + monthh + "' AND year='" + yearr + "'";

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

    private double paidToLecturer(String monthh, String yearr) {

        try {
            String load = "SELECT * FROM lecturerpaid WHERE month='" + monthh + "' AND year='" + yearr + "'";

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

    private double studentMonthlyprofits(String monthh, String yearr) {

        try {
            String load = "SELECT * FROM studenthistory  WHERE month='" + monthh + "' AND year='" + yearr + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                double inco = Double.parseDouble(rs.getString("amount"));
                monthlyIncome = monthlyIncome + inco;

            }

            return monthlyIncome;
        } catch (Exception e) {
            System.out.println(e);
        }
        return monthlyIncome;

    }

}
