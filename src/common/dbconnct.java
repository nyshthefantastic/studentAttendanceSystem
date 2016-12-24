/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.sql.Connection;
import java.sql.DriverManager;




/**
 *
 * @author Yesith
 */
public class dbconnct {
    public static Connection connect()
    {
        Connection dbcon =null;
        
        try {
        Class.forName("com.mysql.jdbc.Driver");
        dbcon = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sineraInstitute","root","abc@123");
        
        
        } 
        catch (Exception e) {
            
            System.out.println(e);
        }
        
        
        
        return dbcon;
    }
    
}
