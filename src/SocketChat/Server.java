/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    DataOutputStream dos;
    DataInputStream dis;
    Scanner sc = new Scanner(System.in);

    private void initThread() {
        t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    String s = sc.nextLine();
                    System.out.println("Server: " + s);
                    try {
                        dos.writeUTF(s);
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
                    try {
                        String s = dis.readUTF();
                        System.out.println("Client: " + s);
                    } catch (IOException ex) {
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
            dos = new DataOutputStream(sck.getOutputStream());
            dis = new DataInputStream(sck.getInputStream());

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
