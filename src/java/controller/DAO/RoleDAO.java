/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;

/**
 *
 * @author Mosena
 */
public class RoleDAO extends MyDAO{
    public ArrayList<Role> getRoles(){
        ArrayList<Role>list=new ArrayList<>();
        query="select * from Role";
        try{
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                Role r=new Role(rs.getInt("RoleID"),
                        rs.getString("RoleName"),
                        rs.getInt("PermissionLevel"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
    public Role findRole(int roleId){
        query="select * from Role where RoleID=?";
        Role r=null;
        try{
            ps=con.prepareStatement(query);
            ps.setInt(1, roleId);
            rs=ps.executeQuery();
            while(rs.next()){
                r=new Role(rs.getInt("RoleID")
                        ,rs.getString("RoleName"),
                        rs.getInt("PermissionLevel"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public String findRoleName(int roleId){
        query="select RoleName from Role where RoleID=?";
        String roleName="";
        try{
            ps=con.prepareStatement(query);
            ps.setInt(1, roleId);
            while(rs.next()){
                roleName=rs.getString(roleName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roleName;
    }
    
    public void addRole(int roleId,String roleName,int permissionLevel){
        query="insert into Role values (?,?,?)";
        try{
            ps=con.prepareStatement(query);
            ps.setInt(1, roleId);
            ps.setString(2, roleName);
            ps.setInt(3, permissionLevel);
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
    }
    public boolean deleteRole(String roleId){
        query="Delete from Account where RoleID=?";
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, roleId);
            int rows=ps.executeUpdate();
            if(rows==0){
                return false;
            }
        } catch (SQLException ex) { 
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
    public boolean updateRole(int roleId,String roleName,int permissionLevel){
        query="update Role set RoleName=?,PermissionLevel=? where RoleID=?";
        try{
            ps=con.prepareStatement(query);
            ps.setInt(1, roleId);
            ps.setInt(2, permissionLevel);
            ps.setString(3,roleName);
            int rows=ps.executeUpdate();
            if(rows==0){
                return false;
            }
        }
        catch(SQLException ex){
            return false;
        }
        finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
}
