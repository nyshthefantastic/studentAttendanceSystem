/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.registration;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author U Computers
 */
public class printStudentReceiptMiddle implements Printable, ActionListener {

    static String studentName = null;
    static String studentId = null;
    static String subjectName = null;
    static String dateee = null;
    static String registerFee = null;

    public int print(Graphics g, PageFormat pf, int page) throws
            PrinterException {

        if (page > 0) {
            /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now we perform our rendering */
        String newline = System.getProperty("line.separator");
        g.drawString("SINERA EDUCATIONAL INSTITUTE.  ", 120, 100);
        g.drawString("NO 266/3,ROYAL COLLEGE ROAD,HORANA.  ", 80, 120);
        g.drawString("NAME OF THE STUDENT :       " + studentName + "  ", 80, 140);
   
        g.drawString("REGISTRATION NUMBER :       " + studentId + "  ", 80, 180);
        g.drawString("REGISTRATION FEE :          " + registerFee + "  ", 80, 200);
        g.drawString("DATE :                      " + dateee + "  ", 80, 220);


        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                /* The job did not successfully complete */
            }
        }

    }

    public static void setReceipt(String stuName, String stuId,  String datee, String regFees) {
        studentName = stuName;
        studentId = stuId;
        //subjectName = subName;
        dateee = datee;
        registerFee = regFees;
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        JFrame f = new JFrame("RECEIPT PRINTER");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });
        JButton printButton = new JButton("PRINT RECEIPT ");
        printButton.addActionListener(new printStudentReceiptMiddle());
        f.add("Center", printButton);
        f.pack();
        f.setVisible(true);
    }
}
