/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mosena
 */
public class MyDAO extends DBContext{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String query;

    public MyDAO() {
        try {
            con=getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void finalize() {
        try {
            if(con != null) con.close();
        }
        catch(SQLException e) {
        }
    }
}
