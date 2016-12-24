/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.registration;

import client.general.dashboardClient;
import common.dbconnct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import middle.registration.subjectMiddle;
import middle.registration.updateStudent;
import middle.registration.updateSubject;
import client.attendance.attendanceClient;
import client.general.loginClient;

import client.others.insertCards;
import client.others.updateRegistrationfee;
import client.others.userAccountClient;
import client.pay.lecAdvanceClient;
import client.pay.lecPayClient;
import client.pay.pettyCashClient;
import client.pay.studentFeesClient;
import client.registration.addStudentClient;
import client.registration.lecturerClient;
import client.registration.studentClient;
import client.registration.subjectClient;
import client.reports.companyProfitClient;
/**
 *
 * @author U Computers
 */
public class subjectClient extends javax.swing.JFrame {

    /**
     * Creates new form subjectClient
     */
    private static String page=null;
     Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    subjectMiddle sM=null;
    updateSubject uS=null;
    private String reg;
    public subjectClient() {
        initComponents();
         con = dbconnct.connect();
        setregNum();
        loadComboBox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        registerNoText = new javax.swing.JLabel();
        subNameText = new javax.swing.JTextField();
        lecNameText = new javax.swing.JComboBox<>();
        dayText = new javax.swing.JComboBox<>();
        fromText = new javax.swing.JTextField();
        toText = new javax.swing.JTextField();
        hallText = new javax.swing.JComboBox<>();
        percentageText = new javax.swing.JTextField();
        priceText = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 153));
        setForeground(new java.awt.Color(255, 255, 153));
        setMinimumSize(new java.awt.Dimension(1200, 900));
        setPreferredSize(new java.awt.Dimension(1200, 900));
        getContentPane().setLayout(null);

        jLabel1.setText("Subject Code");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(70, 81, 64, 14);

        jLabel2.setText("Subject Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 155, 66, 14);

        jLabel3.setText("Name of Lecturer");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(70, 229, 83, 14);

        jLabel4.setText("Day of the week");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(70, 303, 79, 14);

        jLabel5.setText("Time From");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(70, 377, 49, 14);

        jLabel6.setText("Time to");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(70, 451, 35, 14);

        jLabel7.setText("Hall Number");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(70, 525, 57, 14);

        jLabel8.setText("Percentage to the company");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(70, 601, 133, 14);

        jLabel9.setText("Class Price");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(70, 675, 51, 14);

        registerNoText.setText("******");
        getContentPane().add(registerNoText);
        registerNoText.setBounds(345, 81, 150, 14);
        getContentPane().add(subNameText);
        subNameText.setBounds(345, 152, 150, 20);

        getContentPane().add(lecNameText);
        lecNameText.setBounds(345, 226, 150, 20);

        dayText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN" }));
        getContentPane().add(dayText);
        dayText.setBounds(345, 300, 150, 20);
        getContentPane().add(fromText);
        fromText.setBounds(345, 374, 150, 20);
        getContentPane().add(toText);
        toText.setBounds(345, 448, 150, 20);

        hallText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        getContentPane().add(hallText);
        hallText.setBounds(345, 522, 150, 20);
        getContentPane().add(percentageText);
        percentageText.setBounds(345, 598, 150, 20);

        priceText.setToolTipText("");
        getContentPane().add(priceText);
        priceText.setBounds(345, 672, 150, 20);
        priceText.getAccessibleContext().setAccessibleName("");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("UPDATE SEARCH");

        jLabel12.setText("Register Number");

        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(614, 81, 563, 460);

        jButton2.setText("REGISTER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(70, 764, 158, 51);

        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(345, 764, 150, 51);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/defbackground.jpg"))); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(0, -6, 1210, 890);

        jMenu1.setText("REGISTRATION");

        jMenuItem1.setText("STUDENT");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("LECTURER");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("SUBJECT");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("ADD STUDENT TO SUBJECT");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("ATTENDANCE");

        jMenuItem4.setText("STUDENT ATTENDANCE");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("PAY");

        jMenuItem6.setText("STUDENT FEES");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("ADVANCE PAYMENT");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("LECTURER MONTHLY PAYMENT");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem9.setText("PETTY CASH");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("OTHERS");

        jMenuItem10.setText("COMPANY PROFITS");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuItem12.setText("STUDENT HISTORY");
        jMenu6.add(jMenuItem12);

        jMenuItem13.setText("ADD CARDS");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenuItem14.setText("CHANGE REGISTRATION FEES");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem14);

        jMenuItem11.setText("USER ACCOUNTS");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String subject = subNameText.getText();
        String lecturer = lecNameText.getSelectedItem().toString();
        String day = dayText.getSelectedItem().toString();
        String from = fromText.getText();
        String to = toText.getText();
        String hall = hallText.getSelectedItem().toString();
        String percentage = percentageText.getText();
        String price = priceText.getText();
        
        sM=new subjectMiddle();
      
        sM.registerSubject(subject, lecturer, day, from, to, hall, percentage, price);


    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyReleased
        // TODO add your handling code here:
      
       
        String search=searchText.getText();
        uS=new updateSubject();
        uS.searchSubject(jTable1,"SELECT * FROM subjectregister WHERE registerNo='" + search + "'", search);
        search="";
    }//GEN-LAST:event_searchTextKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        
        
         int column = 0;
        int row = jTable1.getSelectedRow();
        String value = jTable1.getModel().getValueAt(row, column).toString();
       uS=new updateSubject();
       String rec[]=new String[9];
         rec= uS.populateFields(value);
      registerNoText.setText(rec[0]);
        subNameText.setText(rec[1]);
      
        lecNameText.setSelectedItem(rec[2]);
       
       dayText.setSelectedItem(rec[3]);

     

        
     
        fromText.setText(rec[4]);
        toText.setText(rec[5]);
        hallText.setSelectedItem(rec[6]);
        percentageText.setText(rec[7]);
        priceText.setText(rec[8]);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String registerNo=registerNoText.getText();
          String subject = subNameText.getText();
        String lecturer = lecNameText.getSelectedItem().toString();
        String day = dayText.getSelectedItem().toString();
        String from = fromText.getText();
        String to = toText.getText();
        String hall = hallText.getSelectedItem().toString();
        String percentage = percentageText.getText();
        String price = priceText.getText();
        
       uS.updateSubject(registerNo, subject, lecturer, day, from, to, hall, percentage, price);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        studentClient sC=new studentClient();
        sC.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        lecturerClient lC=new lecturerClient();
        lC.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        subjectClient suC=new subjectClient();
        suC.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:

        addStudentClient aSc=new addStudentClient();
        aSc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        attendanceClient aC=new attendanceClient();
        aC.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        studentFeesClient sfc=new studentFeesClient();
        sfc.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        lecAdvanceClient lac=new lecAdvanceClient();
        lac.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        lecPayClient lpc=new lecPayClient();
        lpc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        pettyCashClient pcm=new pettyCashClient();
        pcm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        loginClient lc=new loginClient();
        String access=lc.getAValue();
        if(access.equals("admin")){
            companyProfitClient cpc=new companyProfitClient();
            cpc.setVisible(true);
            this.setVisible(false);

        }

    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        insertCards ic = new insertCards();
        ic.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        updateRegistrationfee urf = new updateRegistrationfee();
        urf.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        loginClient lc=new loginClient();
        String access=lc.getAValue();
        if(access.equals("admin")){
            userAccountClient uac=new userAccountClient();
            uac.setVisible(true);
            this.setVisible(false);

        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed
   private void setregNum() {
        try {
            String load = "SELECT registerNo FROM subjectregister";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();

            while (rs.next()) {
                reg = rs.getString("registerNo");

            }
            int regn = Integer.parseInt(reg) + 1;
            registerNoText.setText(String.valueOf(regn));

        } catch (Exception e) {
            System.out.println(e);
        }

    }
     private void loadComboBox(){
    
     try {
            String load = "SELECT name FROM lecturerregister";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();

            while (rs.next()) {
               lecNameText.addItem( rs.getString("name"));

            }
           

        } catch (Exception e) {
            System.out.println(e);
        }
    
    
    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(subjectClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(subjectClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(subjectClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(subjectClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new subjectClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> dayText;
    private javax.swing.JTextField fromText;
    private javax.swing.JComboBox<String> hallText;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> lecNameText;
    private javax.swing.JTextField percentageText;
    private javax.swing.JTextField priceText;
    private javax.swing.JLabel registerNoText;
    private javax.swing.JTextField searchText;
    private javax.swing.JTextField subNameText;
    private javax.swing.JTextField toText;
    // End of variables declaration//GEN-END:variables
}