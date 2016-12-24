/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.pay;

import common.dbconnct;
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
public class pettyCashMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Date date = null;

    public pettyCashMiddle() {

        con = dbconnct.connect();

    }

    public void pettyCash(String reasonn, String amountt) {
          boolean valid = validateRegister(amountt);

        if (valid == true) {
        date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           int monthh = localDate.getMonthValue();

        int yearr = Calendar.getInstance().get(Calendar.YEAR);
        try {
            String q = "INSERT INTO pettycash(reason,amount,date,month,year) VALUES ('" + reasonn + "','" + amountt + "','" + String.valueOf(localDate) + "','" + monthh + "','" + yearr + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
        }
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
}
