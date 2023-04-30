/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.InStorage;
import model.Product;

/**
 *
 * @author Mosena
 */
public class InStorageDAO extends MyDAO {
    ProductDAO pd=new ProductDAO();
    public ArrayList<InStorage> getInStorage(){
        query="select * from InStorage";
        ArrayList<InStorage> storage=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=pd.findProduct(
                        rs.getString("ProductID"));
                InStorage a=new InStorage(p,
                        rs.getDate("MfgDate"),
                        rs.getInt("Number"),
                        rs.getDate("ExpDate"));
                storage.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return storage;
    }
    public ArrayList<InStorage> getInStorage(int pageNo){
        query="select * from InStorage order by ProductID offset ? rows fetch next ? rows only";
        ArrayList<InStorage> storage=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=pd.findProduct(
                        rs.getString("ProductID"));
                InStorage a=new InStorage(p,
                        rs.getDate("MfgDate"),
                        rs.getInt("Number"),
                        rs.getDate("ExpDate"));
                storage.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return storage;
    }
    
    public ArrayList<InStorage> getInStorageOrderByDesc(int pageNo){
        query="select * from InStorage order by ProductID desc offset ? rows fetch next ? rows only";
        ArrayList<InStorage> storage=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=pd.findProduct(
                        rs.getString("ProductID"));
                InStorage a=new InStorage(p,
                        rs.getDate("MfgDate"),
                        rs.getInt("Number"),
                        rs.getDate("ExpDate"));
                storage.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return storage;
    }
    
    public ArrayList<InStorage> getInStorageOrderByMfgDateAsc(int pageNo){
        query="select * from InStorage order by MfgDate offset ? rows fetch next ? rows only";
        ArrayList<InStorage> storage=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=pd.findProduct(
                        rs.getString("ProductID"));
                InStorage a=new InStorage(p,
                        rs.getDate("MfgDate"),
                        rs.getInt("Number"),
                        rs.getDate("ExpDate"));
                storage.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return storage;
    }
    
    public ArrayList<InStorage> getInStorageOrderByMfgDateDesc(int pageNo){
        query="select * from InStorage order by MfgDate desc offset ? rows fetch next ? rows only";
        ArrayList<InStorage> storage=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=pd.findProduct(
                        rs.getString("ProductID"));
                InStorage a=new InStorage(p,
                        rs.getDate("MfgDate"),
                        rs.getInt("Number"),
                        rs.getDate("ExpDate"));
                storage.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return storage;
    }
    
    public ArrayList<InStorage> getInStorageOrderByNumberAsc(int pageNo){
        query="select * from InStorage order by Number offset ? rows fetch next ? rows only";
        ArrayList<InStorage> storage=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=pd.findProduct(
                        rs.getString("ProductID"));
                InStorage a=new InStorage(p,
                        rs.getDate("MfgDate"),
                        rs.getInt("Number"),
                        rs.getDate("ExpDate"));
                storage.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return storage;
    }
    
    public ArrayList<InStorage> getInStorageOrderByNumberDesc(int pageNo){
        query="select * from InStorage order by Number desc offset ? rows fetch next ? rows only";
        ArrayList<InStorage> storage=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=pd.findProduct(
                        rs.getString("ProductID"));
                InStorage a=new InStorage(p,
                        rs.getDate("MfgDate"),
                        rs.getInt("Number"),
                        rs.getDate("ExpDate"));
                storage.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return storage;
    }
    
    public ArrayList<InStorage> getInStorageOrderByExpDateAsc(int pageNo){
        query="select * from InStorage order by ExpDate offset ? rows fetch next ? rows only";
        ArrayList<InStorage> storage=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=pd.findProduct(
                        rs.getString("ProductID"));
                InStorage a=new InStorage(p,
                        rs.getDate("MfgDate"),
                        rs.getInt("Number"),
                        rs.getDate("ExpDate"));
                storage.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return storage;
    }
    
    public ArrayList<InStorage> getInStorageOrderByExpDateDesc(int pageNo){
        query="select * from InStorage order by ExpDate desc offset ? rows fetch next ? rows only";
        ArrayList<InStorage> storage=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                Product p=pd.findProduct(
                        rs.getString("ProductID"));
                InStorage a=new InStorage(p,
                        rs.getDate("MfgDate"),
                        rs.getInt("Number"),
                        rs.getDate("ExpDate"));
                storage.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return storage;
    }
    
    public int countInStorage(){
        query="select count(*) from InStorage";
        try{
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }
    public boolean deleteInStorage(String productId,Date mfgDate){
        query="Delete from InStorage where ProductID=? and MfgDate=?";
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, productId);
            ps.setDate(2, mfgDate);
            int rows=ps.executeUpdate();
            if(rows==0){
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
    public boolean addInStorage(String productId,Date mfgDate,int number){
        if(isExisted(productId, mfgDate)){
            query="update InStorage set Number=Number+? where ProductID=? and MfgDate=?";
            try{
                ps=con.prepareStatement(query);
                ps.setInt(1, number);
                ps.setString(2, productId);
                ps.setDate(3, mfgDate);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
            }
        }
        else{
            ProductDAO pd=new ProductDAO();
            Product p=pd.findProduct(productId);
            query="insert into InStorage values(?,?,?,?)";
            try{
                ps=con.prepareStatement(query);
                ps.setString(1, productId);
                ps.setDate(2, mfgDate);
                ps.setInt(3, number);
                Calendar c=Calendar.getInstance();
                c.setTime(mfgDate);
                c.add(Calendar.DAY_OF_MONTH, p.getExpireTime());
                Date expDate=new Date(c.getTime().getTime());
                ps.setDate(4, expDate);
                ps.executeUpdate();
            }
            catch(SQLException ex){
                Logger.getLogger(InStorageDAO.class.getName())
                        .log(Level.SEVERE, null, ex);
                return false;
            }
            finally{
                if(rs!=null){
                    try {
                        rs.close();
                    }
                    catch (SQLException ex) {
                        Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean updateInStorage(String productId,Date mfgDate,int number){
        query="update InStorage set Number=? where "
                + "ProductId=? and MfgDate=?";
        ProductDAO pd=new ProductDAO();
        Product p=pd.findProduct(productId);
        try{
            ps=con.prepareStatement(query);
            ps.setInt(1, number);
            ps.setString(2, productId);
            ps.setDate(3, mfgDate);
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
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isExisted(String productId,Date mfgDate){
        query="select * from InStorage where ProductID=? and MfgDate=?";
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, productId);
            ps.setDate(2, mfgDate);
            rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return false;
    }
    
    public int getQuantity(String productId,Date mfgDate){
        String subQuery="select * from InStorage where ProductID=? and MfgDate=?";
        PreparedStatement subPs=null;
        ResultSet subRs=null;
        int quantity=0;
        try{
            subPs=con.prepareStatement(subQuery);
            subPs.setString(1, productId);
            subPs.setDate(2, mfgDate);
            subRs=subPs.executeQuery();
            if(subRs.next()){
                quantity=subRs.getInt("Number");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(subPs!=null){
                try {
                    subPs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(subRs!=null){
                try {
                    subRs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return quantity;
    }
    
    public int getQuantity(String productId){
        query="select sum(Number) from InStorage "
                + "where ExpDate>getDate() and ProductID=? "
                + "group by ProductID";
        int count=0;
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, productId);
            rs=ps.executeQuery();
            if(rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return count;
    }
    
    public void sellProduct(String productId,int quantity){
        String subQuery="update InStorage set Number=Number-? where "
                + "ProductId=? and MfgDate=?";
        PreparedStatement subPs=null;
        try{
            while(quantity!=0){
                Date first=findFirstProduct(productId);
                int offset=getQuantity(productId, first);
                subPs=con.prepareStatement(subQuery);
                subPs.setInt(1, quantity);
                subPs.setString(2, productId);
                subPs.setDate(3, first);
                subPs.executeUpdate();
                if(offset>=quantity){
                    quantity=0;
                }
                else{
                    quantity-=offset;
                    updateInStorage(productId, first, 0);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(subPs!=null){
                try {
                    subPs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public Date findFirstProduct(String productId){
        String subQuery="select * from InStorage where ProductId=? and Number!=0"
                + " order by ExpDate";
        PreparedStatement subPs=null;
        ResultSet subRs=null;
        try{
            subPs=con.prepareStatement(subQuery);
            subPs.setString(1, productId);
            subRs=subPs.executeQuery();
            if(subRs.next()){
              return subRs.getDate("MfgDate");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(subPs!=null){
                try {
                    subPs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(subRs!=null){
                try {
                    subRs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InStorageDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
