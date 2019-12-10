/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SyncThreadDemo;

/**
 *
 * @author Bao Anh Luu
 */
public class SyncThreadDemo3 {

    int[] a = new int[30];
    Thread t1 = new Thread() {
        @Override
        public void run() {
            for (int i = 0; i < a.length; i += 2) {
                System.out.println(i);
            }
        }
    };
    Thread t2 = new Thread() {
        @Override
        public void run() {
            for (int i = 1; i < a.length; i += 2) {
                System.out.println(i);
            }
        }
    };

    public void print() {
        synchronized (a) {
            t1.start();
            t2.start();
        }
    }

    public static void main(String[] args) {
        (new SyncThreadDemo1()).print();
    }
}
