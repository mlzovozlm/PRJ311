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
public class Customer {
    String CustomerID, ContactName;

    public String getCustomerID() {
        return CustomerID;
    }

    public Customer(String CustomerID, String ContactName) {
        this.CustomerID = CustomerID;
        this.ContactName = ContactName;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }
    
}
