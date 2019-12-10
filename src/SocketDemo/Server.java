/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketDemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bao Anh Luu
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket ssck = new ServerSocket(1111);
            System.out.println("Server opened!");
            Socket sck = ssck.accept();
            DataOutputStream dos = new DataOutputStream(sck.getOutputStream());
            DataInputStream dis = new DataInputStream(sck.getInputStream());
            
            dos.writeUTF("Testing Testing!");
            String s = dis.readUTF();
            System.out.println("Server Received: " + s);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
