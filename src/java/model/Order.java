/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;
/**
 *
 * @author Mosena
 */
public class Order {
    private final static String PREFIX="ORD";
    private String orderID;
    private String username;
    private ArrayList<Product> orderList;
    private ArrayList<String> condition;
    private ArrayList<Integer> number;
    private ArrayList<Date> date;

    public Order(){
        
    }

    public Order(String username, ArrayList<Product> orderList, 
            ArrayList<String> condition,ArrayList<Integer> number,
            ArrayList<Date>date) {
        this.orderID = PREFIX+UUID.randomUUID().toString();
        this.username = username;
        this.orderList = orderList;
        this.condition = condition;
        this.number=number;
        this.date=date;
    }

    public Order(String orderID, String username, ArrayList<Product> orderList
            , ArrayList<String> condition, ArrayList<Integer> number, 
            ArrayList<Date> date) {
        this.orderID = orderID;
        this.username = username;
        this.orderList = orderList;
        this.condition = condition;
        this.number = number;
        this.date=date;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Product> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Product> orderList) {
        this.orderList = orderList;
    }
    
    public void addOrder(Product p) {
        this.orderList.add(p);
    }

    public ArrayList<String> getCondition() {
        return condition;
    }

    public void setCondition(ArrayList<String> condition) {
        this.condition = condition;
    }

    public void addCondition(String c) {
        this.condition.add(c);
    }
    
    public ArrayList<Integer> getNumber() {
        return number;
    }

    public void setNumber(ArrayList<Integer> number) {
        this.number = number;
    }

    public void addNumber(int n) {
        this.number.add(n);
    }
    
    public ArrayList<Date> getDate() {
        return date;
    }

    public void setDate(ArrayList<Date> date) {
        this.date = date;
    }
    
    public void addDate(Date d) {
        this.date.add(d);
    }
    
}
