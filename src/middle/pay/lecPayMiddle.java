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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author U Computers
 */
public class lecPayMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String lecnamecheck;
    Date date = null;
    message mess = null;
    double total = 0;
    private double currentAdvance;
    static ArrayList<String> myArr = new ArrayList<String>();
    static ArrayList<String> getArr = new ArrayList<String>();
     static ArrayList<String> remArr = new ArrayList<String>();

    public lecPayMiddle() {
        con = dbconnct.connect();

    }

    public void pay(String lecturer, String year, String month, String date) {
        getArr.clear();
        myArr.clear();
        remArr.clear();
        boolean checkIfPaid = checkAlreadyPaid(lecturer, year, month);
        if (checkIfPaid == false) {
            double paidAdvance = checkAdvance(lecturer, year, month);
            double totalTo = totalToPay(lecturer, year, month);
            double amtToPay = totalTo - paidAdvance;
            payToLec(lecturer, year, month, date, String.valueOf(amtToPay));
            subjectwiseFees(lecturer, month, year);
            for (int a = 0; a < (myArr.size() - 1); a = a + 2) {
                String sub = myArr.get(a);
                getCards(lecturer, sub);
                getRemainCards( lecturer,  sub, month, year);
            }
            lecFeesMiddle lfm=new lecFeesMiddle();
            lfm.setReceipt(myArr,getArr,paidAdvance,totalTo,amtToPay,lecturer,date,remArr);
        }

    }
private void getRemainCards(String lecturer, String sub,String monthh,String yearr){

  try {
            String load = "SELECT COUNT(amount) AS amountt FROM lecturerfunds WHERE lecturerName= '" + lecturer + "'AND subjectName='" + sub + "'AND month='" + monthh + "'AND year='" + yearr + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            if (rs.next()) {

                remArr.add(rs.getString("amountt"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }



}
    private void getCards(String lecturer, String sub) {
      
        try {
            String load = "SELECT cardNum FROM cards WHERE lecName= '" + lecturer + "'AND subName='" + sub + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            if (rs.next()) {

                getArr.add(rs.getString("cardNum"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void subjectwiseFees(String lecturer, String monthh, String yearr) {
 
        try {
            String load = "SELECT subjectName,SUM(amount) AS amountt FROM lecturerfunds WHERE lecturerName='" + lecturer + "' AND month='" + monthh + "' AND year='" + yearr + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();

            while (rs.next()) {

                myArr.add(rs.getString("subjectName"));
                myArr.add(rs.getString("amountt"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void payToLec(String lecturer, String yearr, String monthh, String datee, String amountt) {

        try {
            String q = "INSERT INTO lecturerpaid(lecName,month,year,amount,date) VALUES ('" + lecturer + "','" + String.valueOf(monthh) + "','" + String.valueOf(yearr) + "','" + amountt + "','" + datee + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
        mess = new message();
        mess.messageBox("PAID RS. " + amountt + " to lecturer.");

    }

    private double totalToPay(String lecturer, String yearr, String monthh) {

        try {
            String load = "SELECT * FROM lecturerfunds WHERE lecturerName='" + lecturer + "' AND month='" + monthh + "' AND year='" + yearr + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();

            while (rs.next()) {
                double Amount = Double.parseDouble(rs.getString("amount"));
                total = total + Amount;
            }
            return total;

        } catch (Exception e) {
            System.out.println(e);
        }
        return total;

    }

    private double checkAdvance(String lecturer, String yearr, String monthh) {

        try {
            String load = "SELECT * FROM lectureradvance WHERE lecturerName='" + lecturer + "' AND month='" + monthh + "' AND year='" + yearr + "'";

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

    private boolean checkAlreadyPaid(String lecturer, String yearr, String monthh) {
        mess = new message();
        try {
            String load = "SELECT * FROM lecturerpaid WHERE lecName='" + lecturer + "' AND year='" + yearr + "' AND month= '" + monthh + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();

            while (rs.next()) {

                lecnamecheck = rs.getString("lecName");
                if (lecnamecheck.equalsIgnoreCase(lecturer)) {
                    mess.messageBox("ALREADY PAID FOR THIS MONTH");
                    return true;

                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }
}
