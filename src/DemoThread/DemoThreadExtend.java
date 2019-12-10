/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoThread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bao Anh Luu
 */
public class DemoThreadExtend extends Thread {

    @Override
    public void run() {
        while (true) {
            System.out.println("Welcome!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Logger.getLogger(DemoThreadExtend.class.getName()).log(Level.SEVERE, null, ie);
            }
        }
    }

    public static void main(String[] args) {
        DemoThreadExtend dte1 = new DemoThreadExtend();
        DemoThreadExtend dte2 = new DemoThreadExtend();
        dte1.start();
        
        dte2.start();
        
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
