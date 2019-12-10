/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketObject;

import java.io.*;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bao Anh Luu
 */
public class Client {

    Thread t1, t2;
    Socket sck;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    Scanner sc = new Scanner(System.in);

    private void initThread() {
        t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    String s = sc.nextLine();
                    System.out.println("ID: ");
                    String id = sc.nextLine();
                    System.out.println("Name: ");
                    String name = sc.nextLine();
                    System.out.println("GPA: ");
                    double gpa = Double.parseDouble(sc.nextLine());
                    Student std = new Student(id, name, gpa);
                    System.out.println("Client: " +  std);
                    try {
                        oos.writeObject(std);
                        sleep(100);
                    } catch (Exception ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        t2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    Student std = null;
                    try {
                        std = (Student) ois.readObject();
                        System.out.println("Server: " + std);
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
    }

    private void initClient() {
        try {
            sck = new Socket("localhost", 1111);
            oos = new ObjectOutputStream(sck.getOutputStream());
            ois = new ObjectInputStream(sck.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Client() {
        initClient();
        initThread();
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        new Client();
    }
}
