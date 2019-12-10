/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoSwingTimer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.Timer;

/**
 *
 * @author Bao Anh Luu
 */
public class TimerFrame extends javax.swing.JFrame {

    Timer t = null;
    static boolean pause = false;
    ButtonGroup butGroup = new ButtonGroup();

    /**
     * Creates new form TimerFrame
     */
    private String moveLeft(String s, int n) {
        return s.substring(s.length() - n, s.length()) + s.substring(0, s.length() - n);
    }

    private String moveRight(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

    private void initTimer() {
        butGroup.add(radLeft);
        butGroup.add(radRight);
        t = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radLeft.isSelected()) {
                    lblDisplay.setText(moveLeft(lblDisplay.getText(), 1));
                }
                if (radRight.isSelected()) {
                    lblDisplay.setText(moveRight(lblDisplay.getText(), 1));
                }
            }
        });
    }

    public TimerFrame() {
        initComponents();
        initTimer();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDisplay = new javax.swing.JLabel();
        butRun = new javax.swing.JButton();
        radLeft = new javax.swing.JRadioButton();
        radRight = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDisplay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDisplay.setText("Testing Testing ");

        butRun.setText("Run");
        butRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRunActionPerformed(evt);
            }
        });

        radLeft.setSelected(true);
        radLeft.setText("Left");

        radRight.setText("Right");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(radRight, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(157, 157, 157)
                            .addComponent(butRun))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(radLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(lblDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radLeft)
                    .addComponent(radRight))
                .addGap(14, 14, 14)
                .addComponent(butRun)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    boolean runPause(boolean pause) {
        if (pause == false) {
            t.start();
            //t.setInitialDelay(t.getInitialDelay() + 500);
            //t.setDelay(t.getDelay() + 500);
            butRun.setText("Pause");
            return true;
        } else {
            t.stop();
            butRun.setText("Run");
            return false;
        }
    }
    private void butRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRunActionPerformed
        pause = runPause(pause);
    }//GEN-LAST:event_butRunActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TimerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butRun;
    private javax.swing.JLabel lblDisplay;
    private javax.swing.JRadioButton radLeft;
    private javax.swing.JRadioButton radRight;
    // End of variables declaration//GEN-END:variables
}
