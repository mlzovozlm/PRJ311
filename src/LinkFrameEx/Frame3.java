/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkFrameEx;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

/**
 *
 * @author Bao Anh Luu
 */
public class Frame3 extends javax.swing.JFrame {

    /**
     * Creates new form Frame3
     */
    public Frame3() {
        initComponents();
    }

    public JButton getButOK() {
        return butOK;
    }

    public void setButOK(JButton butOK) {
        this.butOK = butOK;
    }

    public JRadioButton getRadMoveLeft() {
        return radMoveLeft;
    }

    public JComboBox<String> getCbxColor() {
        return cbxColor;
    }
    
    public void setCbxColor(JComboBox<String> cbxColor) {
        this.cbxColor = cbxColor;
    }
    
    public void setRadMoveLeft(JRadioButton radMoveLeft) {
        this.radMoveLeft = radMoveLeft;
    }

    public JRadioButton getRadMoveRight() {
        return radMoveRight;
    }

    public void setRadMoveRight(JRadioButton radMoveRight) {
        this.radMoveRight = radMoveRight;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        butOK = new javax.swing.JButton();
        radMoveLeft = new javax.swing.JRadioButton();
        radMoveRight = new javax.swing.JRadioButton();
        cbxColor = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        butOK.setText("OK");

        radMoveLeft.setText("Move Left");

        radMoveRight.setText("Move Right");

        cbxColor.setMaximumRowCount(4);
        cbxColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red", "Green", "Blue" }));
        cbxColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(butOK))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radMoveLeft)
                            .addComponent(cbxColor, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addComponent(radMoveRight)))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radMoveLeft)
                    .addComponent(radMoveRight))
                .addGap(18, 18, 18)
                .addComponent(cbxColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(butOK)
                .addGap(90, 90, 90))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxColorActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Frame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Frame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Frame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Frame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Frame3().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butOK;
    private javax.swing.JComboBox<String> cbxColor;
    private javax.swing.JRadioButton radMoveLeft;
    private javax.swing.JRadioButton radMoveRight;
    // End of variables declaration//GEN-END:variables
}
