/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTableSQL;

/**
 *
 * @author Bao Anh Luu
 */
public class Order {
    String OrderID, CustomerID, ShipCountry, EmployeeID;

    public Order(String OrderID, String CustomerID, String ShipCountry, String EmployeeID) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.ShipCountry = ShipCountry;
        this.EmployeeID = EmployeeID;
    }

    public Order() {
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getShipCountry() {
        return ShipCountry;
    }

    public void setShipCountry(String ShipCountry) {
        this.ShipCountry = ShipCountry;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }
    
    @Override
    public String toString() {
        return "Selected:\n" + "OrderID = " + OrderID + "\nCustomerID = " + CustomerID + "\nShipCountry = " + ShipCountry + "\nEmployeeID = " + EmployeeID + "\n";
    }
    
}
