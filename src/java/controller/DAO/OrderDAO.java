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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.Product;

/**
 *
 * @author Mosena
 */
public class OrderDAO extends MyDAO {
    
    public ArrayList<Order> getOrders(){
        ArrayList<Order> list=new ArrayList<>();
        query="select distinct OrderID,UserName,Date from [Order]"
                + " order by OrderID";
        try{
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            String currentID="";
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                if(orderID.equals(currentID)){
                    continue;
                }
                String username=rs.getString("UserName");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(orderID, username, orderList, condList
                        , numList,dateList);
                list.add(o);
                currentID=orderID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> getOrders(int pageNo){
        ArrayList<Order> list=new ArrayList<>();
        query="select distinct OrderID,UserName,Date from [Order]"
                + " order by OrderID "
                + "offset ? rows fetch next ? rows only";
        try{
            ps=con.prepareStatement(query);
            ps.setInt(1,(pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            String currentID="";
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                if(orderID.equals(currentID)){
                    continue;
                }
                String username=rs.getString("UserName");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(orderID, username, orderList, condList
                        , numList,dateList);
                list.add(o);
                currentID=orderID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Product> getOrderList(String orderID){
        ArrayList<Product> list=new ArrayList<>();
        query="select p.ProductID,p.ProductName,p.Price,p.ExpireTime "
                + "from [Order] o join Product p on OrderID=? "
                + "and o.ProductID=p.ProductID";
        ResultSet subRs=null;
        PreparedStatement subPs=null;
        try{
            subPs=con.prepareStatement(query);
            subPs.setString(1, orderID);
            subRs=subPs.executeQuery();
            while(subRs.next()){
                Product p=new Product(subRs.getString("ProductID")
                        , subRs.getString("ProductName"), 
                        subRs.getInt("Price"), 
                        subRs.getInt("ExpireTime"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(subRs!=null){
                try {
                    subRs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(subPs!=null){
                try {
                    subPs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<String> getCondList(String orderID){
        ArrayList<String> list=new ArrayList<>();
        query="select * from [Order] where OrderID=?";
        ResultSet subRs=null;
        PreparedStatement subPs=null;
        try{
            subPs=con.prepareStatement(query);
            subPs.setString(1, orderID);
            subRs=subPs.executeQuery();
            while(subRs.next()){
                String p=subRs.getString("Condition");
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(subRs!=null){
                try {
                    subRs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(subPs!=null){
                try {
                    subPs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Integer> getNumberList(String orderID){
        ArrayList<Integer> list=new ArrayList<>();
        query="select * from [Order] where OrderID=?";
        ResultSet subRs=null;
        PreparedStatement subPs=null;
        try{
            subPs=con.prepareStatement(query);
            subPs.setString(1, orderID);
            subRs=subPs.executeQuery();
            while(subRs.next()){
                int p=subRs.getInt("Number");
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(subRs!=null){
                try {
                    subRs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(subPs!=null){
                try {
                    subPs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Date> getDateList(String orderID){
        ArrayList<Date> list=new ArrayList<>();
        query="select * from [Order] where OrderID=?";
        ResultSet subRs=null;
        PreparedStatement subPs=null;
        try{
            subPs=con.prepareStatement(query);
            subPs.setString(1, orderID);
            subRs=subPs.executeQuery();
            while(subRs.next()){
                Date p=subRs.getDate("Date");
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(subRs!=null){
                try {
                    subRs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(subPs!=null){
                try {
                    subPs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByOrderID(String orderID){
        query="select * from [Order] where OrderID = ?";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, orderID);
            rs=ps.executeQuery();
            String currentID="";
            while(rs.next()){
                if(orderID.equals(currentID)){
                    continue;
                }
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
                currentID=orderID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByOrderID(String orderID,int pageNo){
        query="select * from [Order] where OrderID = ? order by OrderID "
                + "offset ? rows fetch next ? rows only";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, orderID);
            ps.setInt(2,(pageNo-1)*10);
            ps.setInt(3, 10);
            rs=ps.executeQuery();
            String currentID="";
            while(rs.next()){
                if(orderID.equals(currentID)){
                    continue;
                }
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
                currentID=orderID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByUserName(String userName){
        query="select * from [Order] where UserName like ?";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, "%"+userName+"%");
            rs=ps.executeQuery();
            String currentID="";
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                if(orderID.equals(currentID)){
                    continue;
                }
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
                currentID=orderID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByUserName(String userName,int pageNo){
        query="select * from [Order] where UserName like ? order by OrderID "
                + "offset ? rows fetch next ? rows only";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, "%"+userName+"%");
            ps.setInt(2,(pageNo-1)*10);
            ps.setInt(3, 10);
            rs=ps.executeQuery();
            String currentID="";
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                if(orderID.equals(currentID)){
                    continue;
                }
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
                currentID=orderID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByProductID(String productId){
        query="select * from [Order] where ProductID like ?";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, "%"+productId+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByProductID(String productId,int pageNo){
        query="select * from [Order] where ProductID like ? order by OrderID "
                + "offset ? rows fetch next ? rows only";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, "%"+productId+"%");
            ps.setInt(2,(pageNo-1)*10);
            ps.setInt(3, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByNumber(int number){
        query="select * from [Order] where Number=?";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setInt(1, number);
            rs=ps.executeQuery();
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    
    public ArrayList<Order> findOrderByNumber(int number,int pageNo){
        query="select * from [Order] where Number = ? order by OrderID "
                + "offset ? rows fetch next ? rows only";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setInt(1, number);
            ps.setInt(2,(pageNo-1)*10);
            ps.setInt(3, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByCondition(String condition){
        query="select * from [Order] where Condition=?";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, condition);
            rs=ps.executeQuery();
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByCondition(String username,String condition){
        query="select * from [Order] where UserName=? and Condition=?";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, condition);
            rs=ps.executeQuery();
            String currentID="";
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                if(currentID.equals(orderID)){
                    continue;
                }
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(orderID,rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
                currentID=orderID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByCondition(String condition,int pageNo){
        query="select * from [Order] where Condition = ? order by OrderID "
                + "offset ? rows fetch next ? rows only";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, condition);
            ps.setInt(2,(pageNo-1)*10);
            ps.setInt(3, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o); 
           }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    
    public ArrayList<Order> findOrderByCondition(String username,String condition,int pageNo){
        query="select * from [Order] where UserName=? and Condition = ? order by OrderID "
                + "offset ? rows fetch next ? rows only";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, condition);
            ps.setInt(3,(pageNo-1)*10);
            ps.setInt(4, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o); 
           }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByDate(Date date){
        query="select * from [Order] where [Date]=?";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setDate(1, date);
            rs=ps.executeQuery();
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByDate(String username,Date date){
        query="select * from [Order] where UserName=? and [Date]=?";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, username);
            ps.setDate(2, date);
            rs=ps.executeQuery();
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Order> findOrderByDate(Date date,int pageNo){
        query="select * from [Order] where [Date] = ? order by OrderID "
                + "offset ? rows fetch next ? rows only";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setDate(1, date);
            ps.setInt(2,(pageNo-1)*10);
            ps.setInt(3, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o); 
           }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    
    public ArrayList<Order> findOrderByDate(String username,Date date,int pageNo){
        query="select * from [Order] where UserName=? and [Date] = ? order by OrderID "
                + "offset ? rows fetch next ? rows only";
        ArrayList<Order> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, username);
            ps.setDate(2, date);
            ps.setInt(3,(pageNo-1)*10);
            ps.setInt(4, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                String orderID=rs.getString("OrderID");
                ArrayList<Product> orderList=getOrderList(orderID);
                ArrayList<String> condList=getCondList(orderID);
                ArrayList<Integer> numList=getNumberList(orderID);
                ArrayList<Date> dateList=getDateList(orderID);
                Order o=new Order(rs.getString("UserName"), 
                        orderList,condList, 
                        numList,dateList);
                list.add(o); 
           }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public boolean addOrder(Order o){
        query="insert into [Order] values(?,?,?,?,?,?)";
        try{
            ps=con.prepareStatement(query);
            for(int i=0;i<o.getOrderList().size();i++){
                ps.setString(1, o.getOrderID());
                ps.setString(2,o.getUsername());
                ps.setString(3,
                        o.getOrderList().get(i).getProductID());
                ps.setInt(4,o.getNumber().get(i));
                ps.setString(5, o.getCondition().get(i));
                if(o.getDate()!=null){
                    ps.setDate(6, o.getDate().get(i));
                }
                else{
                    ps.setDate(6, null);
                }
                ps.executeUpdate();
            }
        }
        catch(SQLException ex){
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
    public boolean removeOrderItem(String orderId,String productId){
        query="Delete from [Order] where OrderID=? and ProductID=?";
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, orderId);
            ps.setString(2, productId);
            int rows=ps.executeUpdate();
            if(rows==0){
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean deleteOrder(String orderId){
        query="Delete from [Order] where OrderID=?";
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, orderId);
            int rows=ps.executeUpdate();
            if(rows==0){
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean updateOrder(String orderId, String productId
            ,int number, String condition,Date date){
        query="update [Order] set Number=?, Condition=?, Date=? where "
                + "OrderID=? and ProductID=?";
        try{
            ps=con.prepareStatement(query);
            ps.setInt(1, number);
            ps.setString(2, condition);
            ps.setDate(3, date);
            ps.setString(4, orderId);
            ps.setString(5, productId);
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
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean updateOrder(String orderId,Date date,String condition){
        query="update [Order] set  Condition=?, Date=? where OrderID=?";
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, condition);
            ps.setDate(2, date);
            ps.setString(3, orderId);
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
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
    
    public int countOrders(){
        query="Select count(*) from [Order]";
        try{
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }
    
    public int countOrders(String username){
        query="Select count(*) from [Order] where UserName=?";
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, username);
            rs=ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }
}
