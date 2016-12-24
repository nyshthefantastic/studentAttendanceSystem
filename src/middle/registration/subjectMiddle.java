/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.registration;

import common.dbconnct;
import common.message;
import common.validations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author U Computers
 */
public class subjectMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;

    public subjectMiddle() {

        con = dbconnct.connect();
    }

    public void registerSubject(String subject, String lecturer, String day, String from, String to, String hall, String percentage, String price) {

        boolean valid = validateSubject(subject, lecturer, day, from, to, hall, percentage, price);
        
        if (valid == true) {
            try {
      //      String q = "INSERT INTO subjectregister(subject,lecturer,day,from,to,hall,percentage,price) VALUES ('" + subject + "','" + lecturer + "','" + day + "','" + from + "','" + to + "','" + hall + "','" + percentage + "','" + price + "')";
             /*   String q = "INSERT INTO subjectregister(subject,lecturer,day,from,to,hall,percentage,price) VALUES (?,?,?,?,?,?,?,?)";
                
               
                pst.setString(1, subject);
                pst.setString(2, lecturer);
                pst.setString(3, day);
                pst.setString(4, from);
                pst.setString(5, to);
                pst.setString(6, hall);
                pst.setString(7, percentage);
                pst.setString(8, price);*/
                 String q = "INSERT INTO subjectregister(Subject,Lecturer,Day,FromTim,ToTim,Hall,Percentage,Price) VALUES ('" + subject + "','" + lecturer + "','" + day + "','" + from + "','" + to + "','" + hall + "','" + percentage + "','" + price + "')";
                  pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                
                System.out.println(e);
            }

            mess = new message();
            mess.messageBox("REGISTRATION SUCCESSFUL");
            
        }
    }

    public boolean validateSubject(String subject, String lecturer, String day, String from, String to, String hall, String percentage, String price) {
        validations v = new validations();
        boolean fieldEmpty = v.checkEmptyField(subject, from, to, percentage, price);
        boolean fieldText = v.checkFieldTextt(subject);

        if ((fieldEmpty == true) && (fieldText == true)) {
            return true;
        } else {
            return false;
        }

    }

}
