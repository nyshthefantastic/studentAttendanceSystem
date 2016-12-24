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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author U Computers
 */
public class studentMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;

    public studentMiddle() {

        con = dbconnct.connect();
    }

    public void registerStudent(String Name, String address, String dob, String school, String grade, String mobileNum, String fixed) {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String datee = String.valueOf(localDate);
        int yearr = Calendar.getInstance().get(Calendar.YEAR);
        boolean valid = validateRegister(Name, address, dob, school, grade, mobileNum, fixed);

        if (valid == true) {
            try {
                String q = "INSERT INTO studentregister(name,address,dob,school,grade,mobileNum,fixed,regYear) VALUES ('" + Name + "','" + address + "','" + dob + "','" + school + "','" + grade + "','" + mobileNum + "','" + fixed + "','" + yearr + "')";

                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);
            }

            mess = new message();
            mess.messageBox("REGISTRATION SUCCESSFUL");
            String regAmt = getRegAmt();
            String getreg = getStudentId(Name);
            printStudentReceiptMiddle psrm = new printStudentReceiptMiddle();
            psrm.setReceipt(Name, getreg, datee, regAmt);
        }
    }

    private String getStudentId(String namee) {

        try {
            String load = "SELECT registerNo FROM studentregister WHERE name='" + namee + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                String reg = rs.getString("registerNo");
                return reg;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

    private String getRegAmt() {

        try {
            String load = "SELECT * FROM regfees";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                String reg = rs.getString("amount");
                return reg;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public boolean validateRegister(String Name, String address, String dob, String school, String grade, String mobileNum, String fixed) {

        validations v = new validations();
        boolean fieldEmpty = v.checkEmptyField(Name, address, dob, school, grade, mobileNum, fixed);

        boolean fieldText = v.checkFieldText(Name, school);
        boolean fieldContactNo = v.validateContactNumber(mobileNum, fixed);

        boolean fieldnumerics = v.fieldNumberValidatiion(grade);

        if ((fieldEmpty == true) && (fieldText == true) && (fieldContactNo == true) && (fieldnumerics == true)) {
            return true;
        } else {
            return false;
        }

    }

}
