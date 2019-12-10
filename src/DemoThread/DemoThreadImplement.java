/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoThread;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bao Anh Luu
 */
public class DemoThreadImplement implements Runnable {

    String st = "ABCDEF";

    @Override
    public void run() {
        while (true) {
            st = moveLeft(st);
            System.out.println(st);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Logger.getLogger(DemoThreadExtend.class.getName()).log(Level.SEVERE, null, ie);
            }
        }
    }

    String moveLeft(String st) {
        return st.charAt(st.length() - 1) + st.substring(0, st.length() - 1);
    }

    public static void main(String[] args) {
        Thread dti1 = new Thread(new DemoThreadImplement());
        //Thread dti2 = new Thread(new DemoThreadImplement());
        dti1.start();
        //dti2.start();
        while (true) {
            System.out.println("abc");
            try {
                sleep(500);
            } catch (InterruptedException ie) {
                Logger.getLogger(DemoThreadExtend.class.getName()).log(Level.SEVERE, null, ie);
            }
        }
    }
}
