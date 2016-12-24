/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import javax.swing.JOptionPane;





/**
 *
 * @author U Computers
 */
public class message {
    
    public void messageBox(String Message){
       JOptionPane.showMessageDialog(null,Message,"ALERT",JOptionPane.WARNING_MESSAGE);
  
    }
    public boolean alertBox(String Message){
        int confirm=JOptionPane.showConfirmDialog(null, Message);
       
        
        if(confirm==0){
            return true;
        }
        return false;

    
    }
    
}
