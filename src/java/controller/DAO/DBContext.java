/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mosena
 */
public class DBContext {
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        String username="sa";
        String password="sa";
        String url="jdbc:sqlserver://localhost:1433;databasename = Hang Tet";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url,username,password);
    }
}
