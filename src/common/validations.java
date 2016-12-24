/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author U Computers
 */
public class validations {

    message mess = null;
//------------------------------------REGISTER STUDENT START------------------------------------------------------
    public boolean checkEmptyField(String Name,String address,String dob, String school, String grade, String mobileNum, String fixed) {
        if (Name.isEmpty() | address.isEmpty() | dob.isEmpty() | school.isEmpty() | grade.isEmpty() | mobileNum.isEmpty() | fixed.isEmpty() ) {
            mess = new message();
            mess.messageBox("One or More Fields are Empty !");
            return false;
        } else {
            return true;
        }

    }

    public boolean checkFieldText(String Name, String school) {
        Pattern p = Pattern.compile("^[\\p{L} .'-]+$");
        Matcher m = p.matcher(Name);
        Matcher l = p.matcher(school);
        
        if ((m.find() && m.group().equals(Name)) && (l.find() && l.group().equals(school)) ) {
            return true;
        } else {
            mess = new message();
            mess.messageBox("Name and school should only contain [A-Z | a-z] !");
            return false;

        }

    }

    public boolean validateContactNumber(String mobileNo,String fixed) {
        Pattern p = Pattern.compile("\\d{10}");
        Matcher m = p.matcher(mobileNo);
        Matcher mf = p.matcher(fixed);
        if ((m.find() && m.group().equals(mobileNo)) &&(mf.find() && mf.group().equals(fixed))) {

            return true;
        } else {
            mess = new message();
            mess.messageBox("Contact Number can only contain 10 digits. eg-0771111111");
            return false;
        }

    }
    public boolean validateNIC(String nic){
        
           Pattern p = Pattern.compile("^[0-9]{9}[vVxX]$");
        Matcher m = p.matcher(nic);
        if (m.find() && m.group().equals(nic)) {

            return true;
        } else {
            mess = new message();
            mess.messageBox("NIC number must be in the format of 999999999[v/x]");
            return false;
        }
    
    }
    public boolean validatedob(String ageN){
        int age=Integer.parseInt(ageN);
        if((age>15) &&  (age < 65)){
            return true;
        
        }else{
             mess = new message();
            mess.messageBox("Age cannot be greater than 65 or lesser than 16.please modify date of birth");
            return false;
        }
    
    
    }
    
    public boolean fieldNumberValidatiion(String grade){
    
           Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(grade);
        if ((m.find() && m.group().equals(grade)) ) {

            return true;
        } else {
            mess = new message();
            mess.messageBox("grade can only contain Numerics");
            return false;
        }
    
    
    }
      public boolean salaryValidatiion(double bsal){
    
       
        if ( (bsal >12000)) {

            return true;
        } else {
            mess = new message();
            mess.messageBox("basic salary must be greater than Rs.12000.00");
            return false;
        }
    
    
    }
        
    //------------------------------REGISTER STUDENT END----------------   
      
      //---------------REGISTER LECTURER START------------------
      
       public boolean checkEmptyField(String Name,String address, String mobileNum, String email) {
        if (Name.isEmpty() | address.isEmpty()  | mobileNum.isEmpty() | email.isEmpty() ) {
            mess = new message();
            mess.messageBox("One or More Fields are Empty !");
            return false;
        } else {
            return true;
        }

    }
       
       
         public boolean checkFieldText(String Name) {
        Pattern p = Pattern.compile("^[\\p{L} .'-]+$");
        Matcher m = p.matcher(Name);
       
        
        if ((m.find() && m.group().equals(Name))) {
            return true;
        } else {
            mess = new message();
            mess.messageBox("Name  should only contain [A-Z | a-z] !");
            return false;

        }

    }

    public boolean validateContactNumber(String mobileNo) {
        Pattern p = Pattern.compile("\\d{10}");
        Matcher m = p.matcher(mobileNo);
        
        if ((m.find() && m.group().equals(mobileNo))) {

            return true;
        } else {
            mess = new message();
            mess.messageBox("Contact Number can only contain 10 digits. eg-0771111111");
            return false;
        }

    }
    //-----------------REGISTER SUBJECT --------------------------
     public boolean checkEmptyField(String subject, String from, String to,String percentage,String price) {
        if (subject.isEmpty() | to.isEmpty()  | from.isEmpty() | percentage.isEmpty() | price.isEmpty() ) {
            mess = new message();
            mess.messageBox("One or More Fields are Empty !");
            return false;
        } else {
            return true;
        }

    }
       
       
         public boolean checkFieldTextt(String subject) {
        Pattern p = Pattern.compile("^[\\p{L} .'-]+$");
        Matcher m = p.matcher(subject);
       
        
        if ((m.find() && m.group().equals(subject))) {
            return true;
        } else {
            mess = new message();
            mess.messageBox("Subject should only contain [A-Z | a-z] !");
            return false;

        }

    }
         
     //-------
         
            public boolean checkEmptyField(String subject) {
        if (subject.isEmpty()  ) {
            mess = new message();
            mess.messageBox("One Field is Empty !");
            return false;
        } else {
            return true;
        }

    }

}
