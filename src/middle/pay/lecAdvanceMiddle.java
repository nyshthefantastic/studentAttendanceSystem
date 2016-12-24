/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.pay;

import common.dbconnct;
import common.message;
import common.validations;
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
public class lecAdvanceMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    double totalAmt = 0;
    double currentAdvance = 0;
    Date date = null;
     message mess=null;

    public lecAdvanceMiddle() {

        con = dbconnct.connect();

    }

    public boolean checkAdvance(String lecturer, String amount) {
         boolean valid = validateRegister(amount);

        if (valid == true) {
        double totalAmount = getAmt(lecturer, amount);
        double totaladvance = checkAdTable(lecturer);
        
        if (totalAmount - totaladvance > Double.parseDouble(amount)) {
             mess = new message();
                      mess.messageBox("CAN TAKE AN ADVANCE UPTO Rs."+(totalAmount - totaladvance)+" ");

            return true;

        } else {

            return false;
        }
    }
          return false;
    }
     public boolean validateRegister(String cardnum) {
        validations v = new validations();
        boolean fieldEmpty = v.checkEmptyField(cardnum);
        boolean fieldnumerics = v.fieldNumberValidatiion(cardnum);

        if ((fieldEmpty == true) && (fieldnumerics == true)) {
            return true;
        } else {
            return false;
        }

    }
    private double checkAdTable(String lecturer) {

        date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int monthh = localDate.getMonthValue();
        int yearr = Calendar.getInstance().get(Calendar.YEAR);

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

    private double getAmt(String lecturer, String amount) {
        date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int monthh = localDate.getMonthValue();
       
        int yearr = Calendar.getInstance().get(Calendar.YEAR);

        try {
            String load = "SELECT * FROM lecturerfunds WHERE lecturerName='" + lecturer + "' AND month='" + monthh + "' AND year='" + yearr + "'";
         
            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
           
            while (rs.next()) {
                double Amount = Double.parseDouble(rs.getString("amount"));
               
                totalAmt = totalAmt + Amount;
            }
          
            return totalAmt;

        } catch (Exception e) {
            System.out.println(e);
        }
        return totalAmt;

    }

    public void payAdvance(String lecturer, String amount) {

        date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int monthh = localDate.getMonthValue();

        int yearr = Calendar.getInstance().get(Calendar.YEAR);
        try {
            String q = "INSERT INTO lectureradvance(lecturername,month,year,amount) VALUES ('" + lecturer + "','" + String.valueOf(monthh) + "','" + String.valueOf(yearr) + "','" + amount + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
