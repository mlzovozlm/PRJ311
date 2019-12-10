/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTableSQL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Bao Anh Luu
 */
public class Control {

    Frame f1;
    DefaultTableModel dfmd = new DefaultTableModel();
    Connection con;
    ArrayList<Order> orderList = new ArrayList<>();
    ArrayList<Customer> customerList = new ArrayList<>();

    public Control(Frame f1) {
        this.f1 = f1;
        f1.getChkOrderID().setSelected(true);
        f1.getChkOrderID().setEnabled(false);
        f1.getChkCustomerID().setSelected(true);
        f1.getChkCountry().setSelected(true);
        f1.getChkEmployeeID().setSelected(false);
        f1.getChkContactName().setSelected(false);
        try {
            con = (new DBConnect().getConnection());
        } catch (Exception e) {
            System.out.println("Fail!");
        }
    }

    public Order searchOrder(String xOrderID) {
        Order xOrder = null;
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderID().compareTo(xOrderID) == 0) {
                xOrder = orderList.get(i);
            }
        }
        return xOrder;
    }

    public Customer searchCustomer(String xCustomerID) {
        Customer xCustomer = null;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getCustomerID().compareTo(xCustomerID) == 0) {
                xCustomer = customerList.get(i);
            }
        }
        return xCustomer;
    }

    private void loadTitle() { //
        Vector name = new Vector();
        f1.getCbxFilterBy().removeAllItems();
        if (f1.getChkOrderID().isSelected()) {
            name.add("OrderID");
            f1.getCbxFilterBy().addItem("OrderID");
        }
        if (f1.getChkCustomerID().isSelected()) {
            name.add("CustomerID");
            f1.getCbxFilterBy().addItem("CustomerID");
        }
        if (f1.getChkCountry().isSelected()) {
            name.add("ShipCountry");
            f1.getCbxFilterBy().addItem("ShipCountry");
        }
        if (f1.getChkEmployeeID().isSelected()) {
            name.add("EmployeeID");
            f1.getCbxFilterBy().addItem("EmployeeID");
        }
        if (f1.getChkContactName().isSelected()) {
            name.add("ContactName");
            f1.getCbxFilterBy().addItem("ContactName");
        }
        dfmd.setColumnIdentifiers(name);
        f1.getTblTable().setModel(dfmd);
    }

    public String sqlSelect() {
        String sqlSelect = "SELECT ";
        ArrayList<String> list = new ArrayList();
        if (f1.getChkOrderID().isSelected()) {
            list.add(f1.getChkOrderID().getText());
        }
        if (f1.getChkCustomerID().isSelected()) {
            list.add("o." + f1.getChkCustomerID().getText());
        }
        if (f1.getChkCountry().isSelected()) {
            list.add(f1.getChkCountry().getText());
        }
        if (f1.getChkEmployeeID().isSelected()) {
            list.add(f1.getChkEmployeeID().getText());
        }
        if (f1.getChkContactName().isSelected()) {
            list.add(f1.getChkContactName().getText());
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sqlSelect += list.get(i);
            } else {
                sqlSelect += list.get(i) + ", ";
            }
        }
        sqlSelect += " from dbo.Orders o INNER JOIN dbo.Customers c ON o.CustomerID = c.CustomerID";
        return sqlSelect;
    }

    public void updateFilter() {
        String sql = sqlSelect() + " WHERE " + "o." + f1.getCbxFilterBy().getSelectedItem().toString() + " LIKE \'" + f1.getTxtSearch().getText() + "%\'";
        f1.getTxtSelectInfo().setText("");
        f1.getTxtSeleOrderID().setText("");
        f1.getTxtSeleContactName().setText("");
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
            f1.getTblTable().setModel(dfmd);
        } catch (SQLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadOrderList() {
        String sql = "SELECT OrderID, CustomerID, ShipCountry, EmployeeID from dbo.Orders";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orderList.add(new Order(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadCustomerList() {
        String sql = "SELECT CustomerID, ContactName from dbo.Customers";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customerList.add(new Customer(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void control() {
        f1.setVisible(true);
        f1.setDefaultCloseOperation(3);
        loadTitle();
        loadOrderList();
        loadCustomerList();
        f1.getButSort().setEnabled(false);
        f1.getButLoad().addActionListener((e) -> {
            String sql = sqlSelect();
            f1.getTxtSelectInfo().setText("");
            f1.getTxtSeleOrderID().setText("");
            f1.getTxtSeleContactName().setText("");
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                dfmd.setColumnCount(0);
                dfmd.setRowCount(0);
                loadTitle();
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
            f1.getButSort().setEnabled(true);
        });

        f1.getButSort().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(dfmd);
                f1.getTblTable().setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                for (int i = 0; i < f1.getTblTable().getColumnCount(); i++) {
                    sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
                }
                sorter.setSortKeys(sortKeys);
                f1.getButSort().setEnabled(false);
            }
        });

        f1.getTblTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (f1.getTblTable().getSelectedRow() != -1 && f1.getTblTable().getSelectedColumn() != -1) {
                    String xOrderID = (String) (f1.getTblTable().getValueAt(
                            f1.getTblTable().convertRowIndexToModel(
                                    f1.getTblTable().getSelectedRow()
                            ), 0));
                    Order xOrder = searchOrder(xOrderID);
                    f1.getTxtSelectInfo().setText(xOrder.toString());
                    f1.getTxtSeleOrderID().setText(xOrder.getOrderID());
                    f1.getTxtSeleContactName().setText(
                            searchCustomer((xOrder.getCustomerID())).getContactName()
                    );
                } else {
                    f1.getTxtSelectInfo().setText("");
                    f1.getTxtSeleOrderID().setText("");
                    f1.getTxtSeleContactName().setText("");
                }

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

        f1.getButUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO DO: Update orderList+customerList after SQLQuery
//                try {
//                    String sqlUpdate = "with s as (select o.OrderID, c.ContactName from dbo.Orders o INNER JOIN dbo.Customers c "
//                            + "ON o.CustomerID = c.CustomerID)"
//                            + " UPDATE s"
//                            + " SET ContactName = " + "\'" + f1.getTxtSeleContactName().getText() + "\'"
//                            + " WHERE OrderID = " + f1.getTxtSeleOrderID().getText()
//                            + " " + sqlSelect();
//                    PreparedStatement ps = con.prepareStatement(sqlUpdate);
//                    ResultSet rs = ps.executeQuery();
//                    dfmd.setColumnCount(0);
//                    dfmd.setRowCount(0);
//                    loadTitle();
//                    while (rs.next()) {
//                        Vector v = new Vector();
//                        for (int i = 1; i <= dfmd.getColumnCount(); i++) {
//                            v.add(rs.getString(i));
//                        }
//                        dfmd.addRow(v);
//                    }
//                    f1.getTblTable().setModel(dfmd);
//                } catch (SQLException ex) {
//                    Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        });
    }

    public static void main(String[] args) {
        (new Control(new Frame())).control();
    }
}
