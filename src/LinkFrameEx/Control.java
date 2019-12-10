/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkFrameEx;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.*;
import java.util.logging.*;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Bao Anh Luu
 */
public class Control {

    Thread t;
    Frame1 f1;
    Frame2 f2;
    Frame3 f3;
    Frame4 f4;
    int n = 500;
    int ColorChoice;
    boolean flag = false;

    Color[] c = {Color.RED, Color.GREEN, Color.BLUE};

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Control() {
    }

    public Control(Frame1 f1, Frame2 f2, Frame3 f3, Frame4 f4) {
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.f4 = f4;
    }

    private void MyColor() {
        String s = f1.getLblDisplay().getText();
        String s1 = "<html>";
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                s1 += "<span style = 'color: red'>" + s.charAt(i) + "</span>";
            } else {
                s1 += "<span style = 'color: blue'>" + s.charAt(i) + "</span>";
            }
        }
    }

    private void changeColor() {
        Color cl = f1.getLblDisplay().getForeground();
        f1.getLblDisplay().setForeground(Color.RED);
        if (cl == Color.RED) {
            f1.getLblDisplay().setForeground(Color.BLUE);
        }
        if (cl == Color.BLUE) {
            f1.getLblDisplay().setForeground(Color.GREEN);
        }
        if (cl == Color.GREEN) {
            f1.getLblDisplay().setForeground(Color.YELLOW);
        }
        if (cl == Color.YELLOW) {
            f1.getLblDisplay().setForeground(Color.RED);
        }
    }

    private void cbxColor() {
        int i = f3.getCbxColor().getSelectedIndex();
        f1.getLblDisplay().setForeground(c[i]);
    }

    private void initThread() {
        t = new Thread() {
            @Override
            public void run() {
                f1.setVisible(true);
                while (true) {
                    if (flag) {
                        //changeColor();
                        cbxColor();
                        String s = f1.getLblDisplay().getText();
                        if (f3.getRadMoveRight().isSelected()) {
                            f1.getLblDisplay().setText(moveRight(s, 1));
                        }
                        if (f3.getRadMoveLeft().isSelected()) {
                            f1.getLblDisplay().setText(moveLeft(s, 1));
                        }
                        n = f4.getSldSpeed().getValue();
                    }
                    try {
                        sleep(n);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        t.start();
    }

    private String moveRight(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

    private String moveLeft(String s, int n) {
        return s.substring(s.length() - n, s.length()) + s.substring(0, s.length() - n);
    }

    public void control() {

        ButtonGroup f2RadGroup = new ButtonGroup();
        f2RadGroup.add(f2.getRadSlider());
        f2RadGroup.add(f2.getRadText());
        ButtonGroup f3RadGroup = new ButtonGroup();
        f3RadGroup.add(f3.getRadMoveLeft());
        f3RadGroup.add(f3.getRadMoveRight());
        f2.getRadText().setSelected(true);
        f3.getRadMoveLeft().setSelected(true);
        f1.getButStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f2.setVisible(true);
                setFlag(true);
                f1.getLblDisplay().setForeground(c[f3.getCbxColor().getSelectedIndex()]);
            }
        });

        f4.getSldSpeed().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //n = f4.getSldSpeed().getValue();
            }
        });

        f2.getButOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (f2.getRadText().isSelected()) {
                    f3.setVisible(true);
                }
                if (f2.getRadSlider().isSelected()) {
                    f4.setVisible(true);
                }
                f2.dispose();
            }
        });

        f3.getCbxColor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.getLblDisplay().setForeground(c[f3.getCbxColor().getSelectedIndex()]);
            }
        });

        f3.getButOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f3.dispose();
            }
        });

        f4.getButOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //n = f4.getSldSpeed().getValue();
                f4.dispose();
            }
        });

        f1.getButExit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        initThread();
    }

    public static void main(String[] args) {
        Control c = new Control(new Frame1(), new Frame2(), new Frame3(), new Frame4());
        c.control();
    }
}
