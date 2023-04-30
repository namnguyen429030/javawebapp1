<%-- 
    Document   : home
    Created on : Feb 17, 2023, 12:23:02 PM
    Author     : Mosena
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Home | Gio cha Bay Ngoc</title>
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
            <div class="container">
                <div class="about">
                    <h2>About Us</h2>
                    <p>We are Gio Cha Bay Ngoc, a Vietnamese cuisine store based in Hai Phong. We offer a wide variety of traditional Vietnamese dishes, including spring rolls, banh mi, pho, and more. Our ingredients are fresh and locally sourced, and our dishes are prepared with care and attention to detail. Come visit us today and experience the taste of authentic Vietnamese cuisine!</p>
                </div>
            </div>

            <div class="container">
                <div class="footer">
                    <p><span class="glyphicon glyphicon-earphone"></span> 0833240903</p>
                    <p><span class="glyphicon glyphicon-map-marker"></span> 366 Phan Dang Luu, Kien An, Hai Phong</p>
                </div>
            </div>
        </c:if>
    </body>
</html>
