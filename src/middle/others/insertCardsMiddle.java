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
public class insertCardsMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    message mess = null;

    public insertCardsMiddle() {

        con = dbconnct.connect();

    }

    public void cardMonthly(String lecname, String subname, String cardnum) {

        boolean checker = checkCard(lecname, subname);
        boolean lecsub = checkSubLec(lecname, subname);
        boolean valid = validateRegister(cardnum);
        if (valid == true) {
            if (lecsub == true) {
                if (checker == true) {
                    updateCardNo(lecname, subname, cardnum);

                } else {
                    insertCardNo(lecname, subname, cardnum);

                }
            } else {
                mess = new message();
                mess.messageBox("LECTURER AND SUBJECT DOES NOT MATCH");

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
    private boolean checkSubLec(String lecname, String subname) {

        try {
            String load = "SELECT * FROM subjectregister WHERE Lecturer= '" + lecname + "'AND Subject='" + subname + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            if (rs.next()) {

                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    private void insertCardNo(String lecname, String subname, String cardnum) {

        try {
            String q = "INSERT INTO cards(lecName,subName,cardNum) VALUES ('" + lecname + "','" + subname + "','" + cardnum + "')";

            pst = con.prepareStatement(q);
            pst.execute();
            mess = new message();
            mess.messageBox("INSERT SUCCESSFUL");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private boolean checkCard(String lecname, String subname) {
        try {
            String load = "SELECT * FROM cards WHERE lecName= '" + lecname + "'AND subName='" + subname + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            if (rs.next()) {

                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private void updateCardNo(String lecname, String subname, String cardnum) {

        try {
            String q = "UPDATE regfees SET amount='" + cardnum + "'WHERE lecName= '" + lecname + "'AND subName='" + subname + "'";
            pst = con.prepareStatement(q);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

        mess = new message();
        mess.messageBox("UPDATE SUCCESSFUL");

    }
}
