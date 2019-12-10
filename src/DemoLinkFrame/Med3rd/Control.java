/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoLinkFrame.Med3rd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Bao Anh Luu
 */
public class Control {
    
    Frame1st f1;
    Frame2nd f2;
    
    public Control() {
    }
    
    public Control(Frame1st f1, Frame2nd f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public void control() {
        f1.setVisible(true);
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
                f1.getTxtDisplay().setText("UserName: " + s1 + " PassWord: " + s2);
                f2.dispose();
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
