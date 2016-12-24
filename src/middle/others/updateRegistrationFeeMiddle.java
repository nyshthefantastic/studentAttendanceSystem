/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.others;

import common.dbconnct;
import common.message;
import common.validations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author U Computers
 */
public class updateRegistrationFeeMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    message mess = null;

    public updateRegistrationFeeMiddle() {

        con = dbconnct.connect();

    }

    public void updateRegFee(String regFee) {

        boolean valid = validateRegister(regFee);

        if (valid == true) {
            try {
                String q = "UPDATE regfees SET amount='" + regFee + "'WHERE rid= '" + 1 + "'";

                pst = con.prepareStatement(q);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }

            mess = new message();
            mess.messageBox("UPDATE SUCCESSFUL");

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
