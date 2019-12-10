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
public class DemoThread {

    public static void main(String[] args) {
        Thread th1 = new Thread(new Runnable() {
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
        }
        );
        Thread th2 = new Thread() {
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
        };
        th1.start();
        th1.setPriority(10);
        th2.start();
        th2.setPriority(1);
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
