/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.pay;

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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author U Computers
 */
public class lecFeesMiddle implements Printable, ActionListener {

    static String dateee = null;
    static ArrayList<String> getmyArr = new ArrayList<String>();
    static ArrayList<String> getgetArr = new ArrayList<String>();
    static ArrayList<String> getremArr = new ArrayList<String>();

    static String paidAdv = null;
    static String total = null;
    static String amttoPay = null;
    static String lec = null;

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

        g.drawString("TOTAL AMOUNT PER MONTH  " + total + "", 80, 140);
        g.drawString("TOTAL ADVANCES TAKEN  " + paidAdv + "", 80, 160);
        g.drawString("REMAINING AMOUNT  " + amttoPay + "", 80, 180);
        g.drawString("SUBJECT NAME         TOTALAMOUNT       TOTALCARDS    REMAININGCARDS", 80, 200);
        int y = 20;
        for (int a = 0; a < getgetArr.size(); a++) {
            String remamt=String.valueOf(Integer.parseInt(getgetArr.get(a))-Integer.parseInt(getremArr.get(a)));
            g.drawString("" + getmyArr.get(a * 2) + "                       " + getmyArr.get((a * 2) + 1) + "                                    " + getgetArr.get(a) + "                          " + remamt + "", 80, 200 + y);

        }

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

    public static void setReceipt(ArrayList<String> myArr, ArrayList<String> getArr, double paidAdvance, double totalTo, double amtToPay, String lecturer, String datee, ArrayList<String> rmArr) {
        getmyArr.clear();
        getgetArr.clear();
        getremArr.clear();
        getmyArr.addAll(myArr);
        getgetArr.addAll(getArr);
        getremArr.addAll(rmArr);
        paidAdv = String.valueOf(paidAdvance);
        total = String.valueOf(totalTo);
        amttoPay = String.valueOf(amtToPay);
        lec = String.valueOf(lecturer);
        dateee = datee;

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        JFrame f = new JFrame("RECEIPT PRINTER");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });
        JButton printButton = new JButton("PRINT RECEIPT ");
        printButton.addActionListener(new lecFeesMiddle());
        f.add("Center", printButton);
        f.pack();
        f.setVisible(true);
    }
}
