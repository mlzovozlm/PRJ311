/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SumByDynamicThread;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bao Anh Luu
 */
public class Main {

    Thread[] t;
    static int sum = 0;

    public static void main(String[] args) {
        System.out.println("Size: ");
        int n = Integer.parseInt((new Scanner(System.in)).nextLine());
        int[] a = new int[n];
        Random rd = new Random();
        
        for (int i = 0; i < n; i++) {
            a[i] = rd.nextInt(10);
            System.out.print(a[i] + " ");
        }
        
        Thread[] t = new Thread[4];
        for (int i = 0; i < t.length; i++) {
            final int jj = i;
            t[i] = new Thread() {
                @Override
                public void run() {
                    for (int j = jj; j < a.length; j += t.length) {
                        sum += a[j];
                    }
                }
            };
        }
        
        for (int i = 0; i < t.length; i++) {
            t[i].start();
        }
        
        try {
            for (int i = 0; i < t.length; i++) {
                t[i].join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Sum :" + sum);
    }
}
