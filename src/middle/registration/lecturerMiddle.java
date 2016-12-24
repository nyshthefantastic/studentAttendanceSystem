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
public class lecturerMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;

    public lecturerMiddle() {

        con = dbconnct.connect();
    }

    public void registerStudent(String Name, String address, String mobileNum, String email) {

        boolean valid = validateRegister(Name, address, mobileNum, email);

        if (valid == true) {
            try {
                String q = "INSERT INTO lecturerregister(name,address,mobileNum,email) VALUES ('" + Name + "','" + address + "','" + mobileNum + "','" + email + "')";

                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);
            }

            mess = new message();
            mess.messageBox("REGISTRATION SUCCESSFUL");
        }
    }

    public boolean validateRegister(String Name, String address, String mobileNum, String email) {
        validations v = new validations();
        boolean fieldEmpty = v.checkEmptyField(Name, address, mobileNum, email);
        boolean fieldText = v.checkFieldText(Name);
        boolean fieldContactNo = v.validateContactNumber(mobileNum);

        if ((fieldEmpty == true) && (fieldText == true) && (fieldContactNo == true)) {
            return true;
        } else {
            return false;
        }

    }

}
