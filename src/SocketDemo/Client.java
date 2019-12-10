/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketDemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bao Anh Luu
 */
public class Client {
    public static void main(String[] args) {
        try { 
            Socket sck = new Socket("127.0.0.1", 1111);
            System.out.println("Connected!");
            DataOutputStream dos = new DataOutputStream(sck.getOutputStream());
            DataInputStream dis = new DataInputStream(sck.getInputStream());
            String s = dis.readUTF();
            System.out.println("Client Received: " + s);
            dos.writeUTF("......");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
