/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTableSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bao Anh Luu
 */
public class DBConnect {

     public Connection getConnection()throws Exception {
        String url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName;
                //+ " integratedSecurity=true";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
        //return DriverManager.getConnection(url);
    }
    private final String serverName = "localhost";
    private final String dbName = "Northwind";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "Alo123!@#";
}

