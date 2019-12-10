/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoJDialog;

import javax.swing.JOptionPane;

/**
 *
 * @author Bao Anh Luu
 */
public class Main {

    Frame frame;

    Main(Frame f) {
        this.frame = f;
    }

    void control() {

        //default title and icon
        JOptionPane.showMessageDialog(frame,
                "Eggs are not supposed to be green.");

        //custom title, warning icon
        JOptionPane.showMessageDialog(frame,
                "Eggs are not supposed to be green.",
                "Inane warning",
                JOptionPane.WARNING_MESSAGE);

        //custom title, error icon
        JOptionPane.showMessageDialog(frame,
                "Eggs are not supposed to be green.",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);

        //custom title, no icon
        JOptionPane.showMessageDialog(frame,
                "Eggs are not supposed to be green.",
                "A plain message",
                JOptionPane.PLAIN_MESSAGE);

        //custom title, custom icon
//        JOptionPane.showMessageDialog(frame,
//                "Eggs are not supposed to be green.",
//                "Inane custom dialog",
//                JOptionPane.INFORMATION_MESSAGE,
//                icon);
        //Custom button text
        Object[] options0 = {"Yes, please", "No, thanks", "No eggs, no ham!"};
        int n = JOptionPane.showOptionDialog(frame,
                "Would you like some green eggs to go with that ham?",
                "A Silly Question",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options0,
                options0[2]);

        final JOptionPane optionPane = new JOptionPane(
                "The only way to close this dialog is by\n"
                + "pressing one of the following buttons.\n"
                + "Do you understand?",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION);

        //default icon, custom title
        n = JOptionPane.showConfirmDialog(
                frame,
                "Would you like green eggs and ham?",
                "An Inane Question",
                JOptionPane.YES_NO_OPTION);

        Object[] options1 = {"Yes, please", "No way!"};
        n = JOptionPane.showOptionDialog(frame,
                "Would you like green eggs and ham?",
                "A Silly Question",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                options1, //the titles of buttons
                options1[0]); //default button title
    }

    public static void main(String[] args) {
        new Main(new Frame()).control();
    }
}
