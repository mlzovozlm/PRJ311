/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketObject;

import java.io.*;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bao Anh Luu
 */
public class Server {

    Thread t1, t2;
    ServerSocket ssck;
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
                    System.out.println("Server: " + std);
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
                        System.out.println("Client: " + std);
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
    }

    private void initServer() {
        try {
            ssck = new ServerSocket(1111);
            sck = ssck.accept();
            oos = new ObjectOutputStream(sck.getOutputStream());
            ois = new ObjectInputStream(sck.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Server() {
        initServer();
        initThread();
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        new Server();
    }
}
