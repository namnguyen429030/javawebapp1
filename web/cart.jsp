<%-- 
    Document   : cart
    Created on : Mar 8, 2023, 12:23:01 PM
    Author     : Mosena
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>My cart | Gio cha Bay Ngoc</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<style>
            body {
                background-color: #f5f5f5;
            }
            .container {
                margin-top: 30px;
            }
            .feed {
                background-color: #fff;
                padding: 20px;
                box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
                min-height: 300px;
            }
            h2 {
                margin-top: 0;
            }
            .bar {
                background-color: #eee;
                padding: 10px;
                box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .bar a {
                color: #333;
                font-size: 18px;
                padding: 10px 20px;
                border-radius: 5px;
                text-decoration: none;
                text-align: center;
                transition: all 0.3s ease;
            }
            .bar a:hover {
                color: #fff;
                background-color: #007bff;
                border: 2px solid #007bff;
                border-radius: 5px;
                text-decoration: none;
                cursor: pointer;
            }
            .dropdown {
                position: relative;
                display: inline-block;
            }
            .dropdown-content {
                display: none;
                position: absolute;
                z-index: 1;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                border-radius: 5px;
                padding: 10px;
            }
            .dropdown:hover .dropdown-content {
                display: block;
            }
            .dropdown-content a {
                color: black;
                padding: 5px 10px;
                text-decoration: none;
                display: block;
            }
            .dropdown-content a:hover {
                background-color: #007bff;
            }
            .logo {
                font-size: 24px;
                font-weight: bold;
                margin: 20px 0;
            }
            .about {
                margin-top: 30px;
                padding: 20px;
                background-color: #f7f7f7;
                border-radius: 5px;
            }
            .footer {
                margin-top: 30px;
                padding: 20px;
                background-color: #f7f7f7;
                border-radius: 5px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <c:if test="${username==null || permission==2}">
            <c:redirect url="index.html"/>
        </c:if>
        <c:if test="${permission==0 || permission==-1 || permission==-2}">
            <div class="row">
                <div class="col-sm-12">
                    <div class="bar">
                        <a href="accounts">Account management</a>
                        <a href="orders">Order management</a>
                        <a href="products">Product management</a>
                        <a href="instorage">Storage management</a>
                        <div class="dropdown" style="float:right;">
                            <a href="#" style="font-size: 18px">&#9776;</a>
                            <div class="dropdown-content">
                                <a href="myprofile">My profile</a>
                                <a href="logout">Log out</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="feed">
                            <h2>Feed</h2>
                            <p>This is where your feed will go.</p>
                        </div>
                    </div>
                </div>    
            </div>
        </c:if>
        <c:if test="${permission==1}">
            <div class="row">    
                <div class="col-sm-2">
                    <a class="navbar-brand" href="home.jsp">
                        Gio cha Bay Ngoc
                    </a>
                </div>
                <div class="col-sm-10">
                    <div class="bar" style="display: flex;align-items: center;justify-content: center;">
                        <a href="store">Store</a>
                        <a href="cart">My cart</a>
                        <a href="myOrder">My order</a>
                        <div class="dropdown" style="float:right;">
                            <a href="#" style="font-size: 18px">&#9776;</a>
                            <div class="dropdown-content">
                                <a href="myprofile">My profile</a>
                                <a href="logout">Log out</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <c:forEach items="${cartList}" var="order">
                <div class="row">
                    <div class="col-md-6">
                        <h4>Order: ${order.orderID}</h4>
                    </div>
                    <form action="cart" method="post" id="order-form-${order.orderID}">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="date">Date:</label>
                                <input type="date" class="form-control" id="date" name="date" value="date">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-primary btn-block">Order</button>
                        </div>
                        <input type="hidden" name="command" value="order">
                        <input type="hidden" name="orderId" value="${order.orderID}">
                    </form>
                </div>
                <form action="cart" method="post" id="cart-form-${order.orderID}-${product.productID}">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th>Number</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${order.orderList}" var="product" varStatus="status">
                                <tr>
                                    <td>
                                        ${product.productName}
                                    </td>
                                    <td>
                                        ${order.number.get(status.index)}
                                    </td>
                                    <td>
                                        <a class="btn btn-danger" onclick="event.preventDefault();document.getElementById('cart-form-${order.orderID}-${product.productID}').submit()">Remove</button>
                                    </td>
                                </tr>
                                <input type="hidden" name="command" value="remove">
                                <input type="hidden" name="orderId" value="${order.orderID}">
                                <input type="hidden" name="productId" value="${product.productID}">
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </c:forEach>
            
            <div class="container">
                <div class="footer">
                    <p><span class="glyphicon glyphicon-earphone"></span> 0833240903</p>
                    <p><span class="glyphicon glyphicon-map-marker"></span> 366 Phan Dang Luu, Kien An, Hai Phong</p>
                </div>
            </div>
        </c:if>
    </body>
</html>
