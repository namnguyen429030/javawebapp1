/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author Mosena
 */
public class ProductDAO extends MyDAO {
    
    public ArrayList<Product> getProducts(){
        ArrayList<Product> list=new ArrayList<>();
        query="select * from Product";
        try{
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=new Product(rs.getString("ProductID")
                        ,rs.getString("ProductName")
                        ,rs.getInt("Price")
                        ,rs.getInt("ExpireTime")
                        ,rs.getBytes("Image"));
                list.add(p);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Product> getProducts(int pageNo){
        ArrayList<Product> list=new ArrayList<>();
        query="select * from Product order by ProductID offset ? rows fetch next ? rows only ";
        try{
            ps=con.prepareStatement(query);
            ps.setInt(1,(pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=new Product(rs.getString("ProductID")
                        ,rs.getString("ProductName")
                        ,rs.getInt("Price")
                        ,rs.getInt("ExpireTime")
                        ,rs.getBytes("Image"));
                list.add(p);
            }
        }
        catch(SQLException ex){
            return list;
        }
        finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public Product findProduct(String productID){
        query="select * from Product where ProductID = ?";
        Product p=null;
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, productID);
            rs=ps.executeQuery();
            if(rs.next()){
                p=new Product(rs.getString("ProductID")
                        ,rs.getString("ProductName"),
                        rs.getInt("Price")
                        ,rs.getInt("ExpireTime")
                        ,rs.getBytes("Image"));
            }
        }
        catch(SQLException ex){
            
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return p;
    }
    
    public ArrayList<Product> getProductOrderByDesc(int pageNo){
        query="select * from Product order by ProductID desc offset ? rows fetch next ? rows only";
        ArrayList<Product> list=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("Price"),
                        rs.getInt("ExpireTime")
                        ,rs.getBytes("Image"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return list;
    }
    
    public ArrayList<Product> getProductOrderByProductNameAsc(int pageNo){
        query="select * from Product order by ProductName offset ? rows fetch next ? rows only";
        ArrayList<Product> list=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("Price"),
                        rs.getInt("ExpireTime")
                        ,rs.getBytes("Image"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return list;
    }
    
    public ArrayList<Product> getProductOrderByProductNameDesc(int pageNo){
        query="select * from Product order by ProductName desc offset ? rows fetch next ? rows only";
        ArrayList<Product> list=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("Price"),
                        rs.getInt("ExpireTime")
                        ,rs.getBytes("Image"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return list;
    }

    public ArrayList<Product> getProductOrderByPriceAsc(int pageNo){
        query="select * from Product order by Price offset ? rows fetch next ? rows only";
        ArrayList<Product> list=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("Price"),
                        rs.getInt("ExpireTime")
                        ,rs.getBytes("Image"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return list;
    }
    
    public ArrayList<Product> getProductOrderByPriceDesc(int pageNo){
        query="select * from Product order by Price desc offset ? rows fetch next ? rows only";
        ArrayList<Product> list=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("Price"),
                        rs.getInt("ExpireTime")
                        ,rs.getBytes("Image"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return list;
    }
    
    public ArrayList<Product> getProductOrderByExpireTimeAsc(int pageNo){
        query="select * from Product order by ExpireTime offset ? rows fetch next ? rows only";
        ArrayList<Product> list=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("Price"),
                        rs.getInt("ExpireTime")
                        ,rs.getBytes("Image"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return list;
    }
    
    public ArrayList<Product> getProductOrderByExpireTimeDesc(int pageNo){
        query="select * from Product order by ExpireTime desc offset ? rows fetch next ? rows only";
        ArrayList<Product> list=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("Price"),
                        rs.getInt("ExpireTime")
                        ,rs.getBytes("Image"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return list;
    }
    
    public boolean deleteProduct(String productID){
        query="Delete from Product where ProductID=?";
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, productID);
            int rows=ps.executeUpdate();
            if(rows==0){
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    public void addProduct(String productID,String productName,int price,int expireTime){
        query="insert into Product(ProductID,ProductName,Price,ExpireTime) values(?,?,?,?)";
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, productID);
            ps.setString(2, productName);
            ps.setInt(3, price);
            ps.setInt(4, expireTime);
            ps.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(AccountDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
     public boolean updateProduct(String productID,String productName,int price
                                                    ,int expireTime){
        query="update Product set ProductName=?, Price=?, ExpireTime=? where "
                + "ProductID=?";
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, productName);
            ps.setInt(2, price);
            ps.setInt(3, expireTime);
            ps.setString(4, productID);
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
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    
    public int countProducts(){
        query="Select count(*) from Product";
        try{
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public boolean validateProductId(String productId){
        return findProduct(productId)!=null;
    }
}
