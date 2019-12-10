/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoBorderLayout;

/**
 *
 * @author Bao Anh Luu
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BorderLayoutDemo extends JPanel {
   protected static final int DELTA_DIMEN = 1;
   protected static final int MAX_DIMEN = 600;
   private static final int DELAY = 20;
   private JPanel rightPanel = new JPanel();
   private JPanel bottomPanel = new JPanel();
   private JPanel centerPanel = new JPanel();
   private int dimen = 100;

   public BorderLayoutDemo() {
      JPanel tempCenterPanel = new JPanel(new BorderLayout());
      tempCenterPanel.add(centerPanel, BorderLayout.CENTER);
      tempCenterPanel.add(bottomPanel, BorderLayout.SOUTH);

      setLayout(new BorderLayout());
      add(rightPanel, BorderLayout.EAST);
      add(tempCenterPanel, BorderLayout.CENTER);

      rightPanel.setMinimumSize(new Dimension(150, 0));
      bottomPanel.setMinimumSize(new Dimension(0, 150));

      rightPanel.setPreferredSize(rightPanel.getMinimumSize());
      bottomPanel.setPreferredSize(bottomPanel.getMinimumSize());

      rightPanel.setBorder(BorderFactory.createLineBorder(Color.red));
      bottomPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
      centerPanel.setBorder(BorderFactory.createLineBorder(Color.green));

      new Timer(DELAY, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            dimen += DELTA_DIMEN;
            if (dimen < MAX_DIMEN) {
               centerPanel.setPreferredSize(new Dimension(dimen, dimen));
               SwingUtilities.getWindowAncestor(BorderLayoutDemo.this).pack();

            } else {
               ((Timer) e.getSource()).stop();
            }
         }
      }).start();
   }

   private static void createAndShowGui() {
      BorderLayoutDemo mainPanel = new BorderLayoutDemo();

      JFrame frame = new JFrame("EmielGui");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}