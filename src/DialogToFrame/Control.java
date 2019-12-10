/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DialogToFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Bao Anh Luu
 */
public class Control extends javax.swing.JDialog {

    Frame1st f1;
    Frame2nd f2;
    int OnOff;

    public Control() {
    }

    public Control(Frame1st f1, Frame2nd f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public void control() {
        f1.setVisible(true);
        f1.getMniOn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnOff = 1;
            }
        });
        f1.getMniOff().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnOff = 0;
            }
        });
        f1.getButLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f2.setVisible(true);
            }
        });
        f2.getButOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1 = f2.getTxtUserName().getText();
                String s2 = f2.getTxtPassWord().getText();
                if (OnOff == 1) {
                    f1.getTxtDisplay().setText("UserName: " + s1 + " PassWord: " + s2);
                } else {
                    f1.getTxtDisplay().setText("");
                }
                int x = JOptionPane.showConfirmDialog(f2, "Close?");
                if (x == 0) {
                    f2.dispose();
                }
            }
        });
        f1.getButExit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        (new Control(new Frame1st(), new Frame2nd())).control();

    }
}
