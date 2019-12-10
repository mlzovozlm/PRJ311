/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrameTable;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bao Anh Luu
 */
public class Control {

    Frame f1;
    DefaultTableModel dfmd = new DefaultTableModel();

    public Control(Frame f1) {
        this.f1 = f1;
    }

    private void loadTitle() {
        //Object[] name = {"Code","Name","Gender","Address"};
        Vector name = new Vector();
        name.add("Code");
        name.add("Name");
        name.add("Gender");
        name.add("Address");
        dfmd.setColumnIdentifiers(name);
        f1.getTblTable().setModel(dfmd);
    }

    public void control() {
        f1.setVisible(true);
        f1.setDefaultCloseOperation(3);
        loadTitle();
        f1.getButLoad().addActionListener((e) -> {
            try {
                RandomAccessFile raf = new RandomAccessFile("input.txt", "r");
                String s = "";
                while ((s = raf.readLine()) != null) {
//                    String[] temp = s.split("<>");
//                    dfmd.addRow(temp);
                    Vector temp = new Vector();
                    StringTokenizer s0 = new StringTokenizer(s, "<>");
                    while (s0.hasMoreElements()) {
                        temp.add(s0.nextElement());
                    }
                    dfmd.addRow(temp);
                }
                //f1.getTblTable().setModel(dfmd);
            } catch (Exception ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    public static void main(String[] args) {
        (new Control(new Frame())).control();
    }
}
