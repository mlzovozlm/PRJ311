/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoThread;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CharMove {

    public static void main(String[] args) {
        //1
        Thread cml = new Thread() {
            @Override
            public void run() {
                String st = "Welcome To Hell ";
                while (true) {
                    System.out.println(st);
                    st = st.substring(st.length() - 2, st.length()) + st.substring(0, st.length() - 2);
                    try {
                        sleep(500);
                    } catch (InterruptedException ie) {
                        Logger.getLogger(DemoThreadExtend.class.getName()).log(Level.SEVERE, null, ie);
                    }
                }
            }
        };
        cml.start();
        //2
        Thread cmr = new Thread() {
            @Override
            public void run() {
                String st = "Welcome To Hell ";
                while (true) {
                    System.out.println(st);
                    st = st.substring(2, st.length()) + st.substring(0, 2);
                    try {
                        sleep(500);
                    } catch (InterruptedException ie) {
                        Logger.getLogger(DemoThreadExtend.class.getName()).log(Level.SEVERE, null, ie);
                    }
                }
            }
        };
        cmr.start();
        //3
        Thread cmln = new Thread() {
            @Override
            public void run() {
                int n = 3;
                String st = "Welcome To Hell ";
                if (n > st.length()) {
                    n %= n;
                }
                while (true) {
                    System.out.println(st);
                    st = st.substring(st.length() - n, st.length()) + st.substring(0, st.length() - n);
                    try {
                        sleep(3000);
                    } catch (InterruptedException ie) {
                        Logger.getLogger(DemoThreadExtend.class.getName()).log(Level.SEVERE, null, ie);
                    }
                }
            }
        };
        cmln.start();
        //4
        Thread cmrn = new Thread() {
            @Override
            public void run() {
                int n = 3;
                String st = "Welcome To Hell ";
                if (n > st.length()) {
                    n %= n;
                }
                while (true) {
                    System.out.println(st);
                    st = st.substring(n, st.length()) + st.substring(0, n);
                    try {
                        sleep(500);
                    } catch (InterruptedException ie) {
                        Logger.getLogger(DemoThreadExtend.class.getName()).log(Level.SEVERE, null, ie);
                    }
                }
            }
        };
        cmrn.start();
        //5
        (new Thread() {
            @Override
            public void run() {
                int n = 3;
                String st = "Welcome To Hell ";
                if (n > st.length()) {
                    n %= n;
                }
                while (true) {
                    if (n > st.length()) {
                        n %= n;
                    }
                    System.out.println(st);
                    st = st.substring(st.length() - n, st.length()) + st.substring(0, st.length() - n);
                    try {
                        sleep(3000);
                    } catch (InterruptedException ie) {
                        Logger.getLogger(DemoThreadExtend.class.getName()).log(Level.SEVERE, null, ie);
                    }
                }
            }
        }).start();
        //6
        (new Thread() {
            @Override
            public void run() {
                int n = 3;
                String st = "Welcome To Hell ";
                if (n > st.length()) {
                    n %= n;
                }
                while (true) {
                    System.out.println(st);
                    st = st.substring(n, st.length()) + st.substring(0, n);
                    try {
                        sleep(500);
                    } catch (InterruptedException ie) {
                        Logger.getLogger(DemoThreadExtend.class.getName()).log(Level.SEVERE, null, ie);
                    }
                }
            }
        }).start();
    }
}
