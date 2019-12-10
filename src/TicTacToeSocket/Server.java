/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToeSocket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Bao Anh Luu
 */
public class Server {

    Thread receiveThr;
    ServerSocket ssck;
    Socket sck;
    DataOutputStream dos;
    DataInputStream dis;
    View vs = new View();
    Scanner sc = new Scanner(System.in);
    int size;
    JButton[][] btn;
    boolean flag = true;
    boolean win = false;
    int[][] table;

    int checkDiag1(int i, int j) {
        int i1 = i, j1 = j;
        int count = 1;
        while (count < 5 && i1 < size - 1 && j1 < size - 1) {
            if (table[i1][j1] == table[i1 + 1][j1 + 1]) {
                count++;
                i1++;
                j1++;
            } else {
                break;
            }
        };
        i1 = i;
        j1 = j;
        while (count < 5 && i1 > 0 && j1 > 0) {
            if (table[i1][j1] == table[i1 - 1][j1 - 1]) {
                count++;
                i1--;
                j1--;
            } else {
                break;
            }
        }
        return count;
    }

    int checkDiag2(int i, int j) {
        int i1 = i, j1 = j;
        int count = 1;
        while (count < 5 && i1 > 0 && j1 < size - 1) {
            if (table[i1][j1] == table[i1 - 1][j1 + 1]) {
                count++;
                i1--;
                j1++;
            } else {
                break;
            }
        }
        i1 = i;
        j1 = j;
        while (count < 5 && i1 < size - 1 && j1 > 0) {
            if (table[i1][j1] == table[i1 + 1][j1 - 1]) {
                count++;
                i1++;
                j1--;
            } else {
                break;
            }
        }
        return count;
    }

    int checkHorizon(int i, int j) {
        int j1 = j;
        int count = 1;
        while (count < 5 && j1 < size - 1) {
            if (table[i][j1] == table[i][j1 + 1]) {
                count++;
                j1++;
            } else {
                break;
            }
        }
        j1 = j;
        while (count < 5 && j1 > 0) {
            if (table[i][j1] == table[i][j1 - 1]) {
                count++;
                j1--;
            } else {
                break;
            }
        }
        return count;
    }

    int checkVerti(int i, int j) {
        int i1 = i;
        int count = 1;
        while (count < 5 && i1 < size - 1) {
            if (table[i][j] == table[i1 + 1][j]) {
                count++;
                i1++;
            } else {
                break;
            }
        }
        i1 = i;
        while (count < 5 && i1 > 0) {
            if (table[i][j] == table[i1 - 1][j]) {
                count++;
                i1--;
            } else {
                break;
            }
        }
        return count;
    }

    public boolean checkWin(int i, int j, boolean flag) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (!btn[x][y].getText().equals("") && table[x][y] == 0) {
                    table[x][y] = flag ? 1 : 2;
                }
            }
        }
        if (checkDiag1(i, j) == 5 || checkDiag2(i, j) == 5
                || checkHorizon(i, j) == 5 || checkVerti(i, j) == 5) {
            return true;
        }
        return false;
    }

    private void initThread() {
        receiveThr = new Thread() {
            @Override
            public void run() {
                while (true) {
                    int ij, i, j;
                    try {
                        ij = dis.readInt();
                        i = ij / size;
                        j = ij % size;
                        btn[i][j].setText("<html><span style = 'color:"
                                + (flag ? " blue'>X" : " red'>O") + "</span></html>");
                        win = checkWin(i, j, flag);
                        if (!win) {
                            flag = !flag;
                        }
                        if (win) {
                            int x = JOptionPane.showConfirmDialog(vs, (flag ? "X" : "O") + " Win!\nAgain?",
                                    null, JOptionPane.YES_NO_OPTION);
                            if (x == 0) {
                                //v.getPanDisplay().removeAll();
                                vs.getPanDisplay().setVisible(false);
                                win = false;
                            } else {
                                win = false;
                                vs.dispose();
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
    }

    public void control() {
        vs.setVisible(true);
        vs.getPanDisplay().setVisible(false);

        vs.getButDraw().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vs.getPanDisplay().removeAll();

                size = Integer.parseInt(vs.getTxtSize().getText());
                btn = new JButton[size][size];

                table = new int[size][size];
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        table[i][j] = 0;
                    }
                }

                vs.getPanDisplay().setLayout(new GridLayout(size, size, 0, 0));
                vs.getPanDisplay().setSize(size * 30, size * 30);
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        btn[i][j] = new JButton("");
                        btn[i][j].setPreferredSize(new Dimension(30, 30));
                        vs.getPanDisplay().add(btn[i][j]);
                        btn[i][j].setMargin(new Insets(2, 2, 2, 2));
                        vs.pack();
                        vs.getPanDisplay().setVisible(true);
                        final int ii = i;
                        final int jj = j;
                        btn[i][j].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = btn[ii][jj].getText();
                                if (s1.length() == 0 && win == false && flag == false) {
                                    btn[ii][jj].setText("<html><span style = 'color:"
                                            + (flag ? " blue'>X" : " red'>O") + "</span></html>");
                                    win = checkWin(ii, jj, flag);
                                    if (!win) {
                                        flag = !flag;
                                    }
                                    try {
                                        dos.writeInt(ii * size + jj);
                                    } catch (Exception ex) {
                                        Logger.getLogger(SocketObject.Server.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                if (win) {
                                    int x = JOptionPane.showConfirmDialog(vs, (flag ? "X" : "O") + " Win!\nAgain?",
                                            null, JOptionPane.YES_NO_OPTION);
                                    if (x == 0) {
                                        //v.getPanDisplay().removeAll();
                                        vs.getPanDisplay().setVisible(false);
                                        win = false;
                                    } else {
                                        win = false;
                                        vs.dispose();
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void initServer() {
        try {
            ssck = new ServerSocket(1111);
            sck = ssck.accept();
            dos = new DataOutputStream(sck.getOutputStream());
            dis = new DataInputStream(sck.getInputStream());
            control();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Server() {
        initServer();
        initThread();
        receiveThr.start();
    }

    public static void main(String[] args) {
        new Server();
    }
}
