/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.others;

import common.dbconnct;
import common.message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author U Computers
 */
public class userAccountsMiddle {
    
    
      Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;

    public userAccountsMiddle() {

        con = dbconnct.connect();
    }
    public void addUser(String uname,String pwrd,String aLevel){
    
         try {
                String q = "INSERT INTO login(userName,password,userLevel) VALUES ('" + uname + "','" + pwrd + "','" + aLevel + "')";

                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);
            }

            mess = new message();
            mess.messageBox("USER SUCCESSFULLY ADDED");
    
    
    
    }
}
