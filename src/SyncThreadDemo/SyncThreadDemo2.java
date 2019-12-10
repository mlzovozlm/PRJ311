/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SyncThreadDemo;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bao Anh Luu
 */
public class SyncThreadDemo2 {

    //need edit
    static int flag = 0;
    XsumY xy = new XsumY();
    Random rd = new Random();
    boolean wait = true;
    Thread t1 = new Thread() {
        @Override
        public synchronized void run() {
            while (true) {
                try {
                    if (flag % 3 == 0) {
                        int x = rd.nextInt(10);
                        System.out.println("A = " + x);
                        xy.x = x;
                        flag++;
                        //t1.notify();
                        //t1.wait();
                    }
                    sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SyncThreadDemo2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    Thread t2 = new Thread() {
        @Override
        public synchronized void run() {
            while (true) {
                try {
                    sleep(1000);
                    if (flag % 3 == 1) {
                        int y = rd.nextInt(10);
                        System.out.println("B = " + y);
                        xy.y = y;
                        flag++;
                        //t2.notify();
                        //t2.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(SyncThreadDemo2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    Thread t3 = new Thread() {
        @Override
        public synchronized void run() {
            while (true) {
                try {
                    sleep(2000);
                    if (flag % 3 == 2) {
                        System.out.println("A + B = " + (xy.x + xy.y));
                        flag++;
                        //t3.notify();
                        //t3.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(SyncThreadDemo2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };

    public SyncThreadDemo2() {
        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) {
        new SyncThreadDemo2();
    }
}
