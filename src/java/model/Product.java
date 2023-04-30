/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mosena
 */
public class Product{
    private String productID;
    private String productName;
    private int price;
    private int expireTime;
    private byte[] image;

    public Product() {
    }


    public Product(String productID, String productName, int price, int expireTime, byte[] image) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.expireTime = expireTime;
        this.image = image;
    }
    public Product(String productID, String productName, int price, int expireTime) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.expireTime = expireTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireDate(int expireTime) {
        this.expireTime = expireTime;
    }
    
}
