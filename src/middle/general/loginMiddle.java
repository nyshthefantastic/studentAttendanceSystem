/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.general;


import common.dbconnct;
import common.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Nuwan-PC
 */
public class loginMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public loginMiddle() {
        con = dbconnct.connect();
    }

    public String verifyLogin(String userNamee, String passWord) {
        try {
            String load = "SELECT * FROM login WHERE userName='" + userNamee + "' AND password='" + passWord + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
               String aLevel=rs.getString("userLevel");
               return aLevel;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        message m = new message();
        m.messageBox("INCORRECT LOGIN DETAILS.PLEASE TRY AGAIN !");

        return null;
    }

}
