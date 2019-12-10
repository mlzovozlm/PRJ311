/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Bao Anh Luu
 */
public class Control {

    private static final long serialVersionUID = 1L;
    View v;
    int size;
    JButton[][] btn;
    boolean flag = true;
    boolean win = false;
    int[][] table;

    public Control(View v) {
        this.v = v;
    }

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

    public void control() {
        v.setVisible(true);
        v.getPanDisplay().setVisible(false);

        v.getButDraw().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v.getPanDisplay().removeAll();

                size = Integer.parseInt(v.getTxtSize().getText());
                btn = new JButton[size][size];

                table = new int[size][size];
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        table[i][j] = 0;
                    }
                }

                v.getPanDisplay().setLayout(new GridLayout(size, size, 0, 0));
                v.getPanDisplay().setSize(size * 30, size * 30);
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        btn[i][j] = new JButton("");
                        btn[i][j].setPreferredSize(new Dimension(30, 30));
                        btn[i][j].setMinimumSize(new Dimension(30, 30));
                        v.getPanDisplay().add(btn[i][j]);
                        btn[i][j].setMargin(new Insets(2, 2, 2, 2));
                        v.pack();
                        v.getPanDisplay().setVisible(true);
                        final int ii = i;
                        final int jj = j;
                        btn[i][j].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = btn[ii][jj].getText();
                                if (s1.length() == 0 && win == false) {
                                    //btn[ii][jj].setForeground(flag ? Color.BLUE : Color.RED);
                                    //btn[ii][jj].setText(flag ? "X" : "O");
                                    btn[ii][jj].setText("<html><span style = 'color:"
                                            + (flag ? " blue'>X" : " red'>O") + "</span></html>");
                                    win = checkWin(ii, jj, flag);
                                    if (!win) {
                                        flag = !flag;
                                    }
                                }
                                if (win) {
                                    int x = JOptionPane.showConfirmDialog(v, (flag ? "X" : "O") + " Win!\nAgain?",
                                            null, JOptionPane.YES_NO_OPTION);
                                    if (x == 0) {
                                        //v.getPanDisplay().removeAll();
                                        v.getPanDisplay().setVisible(false);
                                        win = false;

                                    } else {
                                        win = false;
                                        v.dispose();

                                    }
                                }
                            }
                        });
                    }
                }
                v.getPanDisplay().setPreferredSize(new Dimension(size * 30 + 5, size * 30 + 5));
            }
        });
    }

    public static void main(String[] args) {
        (new Control(new View())).control();
    }
}
