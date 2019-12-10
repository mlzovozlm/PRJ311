/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoNorthWindSQL;

import java.awt.Component;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Bao Anh Luu
 */
public class Control {

    Frame f1;
    DefaultTableModel dfmd = new DefaultTableModel();
    Connection con;

    public Control(Frame f1) {
        this.f1 = f1;
        try {
            con = (new DBConnection().getConnection());
        } catch (Exception e) {
            System.out.println("Fail!");
        }
    }

    public String sqlSelect() {
        String sqlSelect = "select oi.Id, c.LastName, p.Package, oi.UnitPrice, oi.Quantity"
                + " from OrderItem oi"
                + " inner join [Order] o on oi.OrderId = o.Id"
                + " inner join Customer c on o.CustomerId = c.Id"
                + " inner join Product p on p.Id = oi.Id";
        return sqlSelect;
    }

    public void updateFilter() {
        dfmd.setRowCount(0);
        String sql = sqlSelect()
                + " WHERE " + f1.getCbxSearch().getSelectedItem().toString()
                + " LIKE \'%" + f1.getTxtSearch().getText() + "%\'";
        if (f1.getChkSearchAll().isSelected()) {
            for (int i = 0; i < dfmd.getColumnCount(); i++) {
                sql += " or " + f1.getCbxSearch().getItemAt(i).toString()
                        + " LIKE \'%" + f1.getTxtSearch().getText() + "%\'";
            }
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= dfmd.getColumnCount(); i++) {
                    v.add(rs.getString(i));
                }
                dfmd.addRow(v);
            }
            f1.getTblTable().setModel(dfmd);
        } catch (SQLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTitle() { //
        Vector name = new Vector();
        name.add("ID");
        name.add("LastName");
        name.add("Package");
        name.add("UnitPrice");
        name.add("Quantity");
        dfmd.setColumnIdentifiers(name);
        f1.getTblTable().setModel(dfmd);
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width < 75) {
                width = 75;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public void control() {
        loadTitle();
        f1.getTblTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int column = 0; column < f1.getTblTable().getColumnCount(); column++) {
            f1.getTblTable().getColumnModel().getColumn(column).setPreferredWidth(75);
        }
        f1.setVisible(true);
        f1.setDefaultCloseOperation(3);

        f1.getButLoad().addActionListener((e) -> {
            String sql = sqlSelect();
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                dfmd.setRowCount(0);
                while (rs.next()) {
                    Vector v = new Vector();
                    for (int i = 1; i <= dfmd.getColumnCount(); i++) {
                        v.add(rs.getString(i));
                    }
                    dfmd.addRow(v);
                }
                resizeColumnWidth(f1.getTblTable());

                f1.getLblTotal().setText(Integer.toString(dfmd.getRowCount()));
                f1.getTblTable().setModel(dfmd);
            } catch (SQLException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        f1.getChkSearchAll().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateFilter();
            }
        });

        f1.getTxtSearch().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFilter();
            }
        });
    }

    public static void main(String[] args) {
        (new Control(new Frame())).control();
    }
}
