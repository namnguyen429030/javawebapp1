<%-- 
    Document   : store
    Created on : Mar 7, 2023, 6:36:18 PM
    Author     : Mosena
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Store | Gio cha Bay Ngoc</title>
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
            .product-container {
                display: flex;
                flex-wrap: wrap;
            }

            .product {
                width: calc(100% / 3);
                box-sizing: border-box;
                text-align: center;
                border: 1px solid #ccc;
            }

            .product img {
                max-width: 100%;
                height: auto;
                margin-bottom: 10px;
            }

            .product-info {
                margin-top: 10px;
            }

            .product-name {
                font-weight: bold;
                font-size: 18px;
                margin-bottom: 5px;
            }

            .product-price {
                color: #999;
                font-size: 16px;
                margin-bottom: 10px;
            }

            .product-process-btn {
                background-color: #4CAF50;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .product-process-btn:hover {
                background-color: #45a049;
                margin-top: 10px;
            }
            .current-page {
                background-color: yellow;
                font-weight: bold;
            }
	</style>
    </head>
    <body>
        <c:if test="${username==null || permission==2}">
            <c:redirect url="currentPage.html"/>
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
                <div class="row">
                    <div class="col-md-12">
                        <div class="product-container">
                            <c:forEach items="${productList}" var="product">
                                <div class="product col-md-4">
                                    <form action="store" method="post" id="product-process-form-${product.productID}">
                                        <div class="product-image">
                                            <img src="${product.image}" alt="${product.productName}">
                                        </div>
                                        <div class="product-info">
                                            <div class="product-name">${product.productName}</div>
                                            <div class="product-price">${product.price}</div>
                                            <c:if test="${quantityMap[product.productID] > 0}">
                                                <div class="row">
                                                    <input type="number" name="number" value="0"><br>   
                                                    <button name="command" value="cart" class="btn btn-primary product-process-btn" onclick="document.getElementById('product-process-form-${product.productID}').submit()">Add to cart</button>
                                                </div>
                                            </c:if>
                                            <c:if test="${quantityMap[product.productID] == 0}">
                                                <h4 style="color: red">Out of stock</h4>
                                            </c:if>
                                        </div>
                                        <input type="hidden" name="productId" value="${product.productID}">
                                        <input type="hidden" name="currentPage" value="${currentPage}">
                                    </form>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

            <div style="display: flex; justify-content: center">
                <c:if test="${currentPage<noPage}">
                    <form action="store" method="post">
                        <button type="submit" name="currentPage" value="${noPage}">Last</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                    <form action="store" method="post">
                        <button type="submit" name="currentPage" value="${currentPage+1}">>></button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                </c:if>
                <c:if test="${noPage>7}">
                    <c:forEach begin="1" end="3" var="i">
                        <form action="store" method="post">
                            <button type="submit" name="currentPage" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                            <input type="hidden" name="command" value="paging">
                        </form>
                    </c:forEach>
                    ...
                    <c:if test="${currentPage>3 && currentPage<end-2}">
                        <form action="store" method="post">
                            <button type="submit" name="currentPage" value="${currentPage}">${currentPage}</button>
                            <input type="hidden" name="command" value="paging">
                        </form>
                        ...
                    </c:if>
                    <c:forEach begin="${end-2}" end="${end}" var="i">
                        <form action="store" method="post">
                            <button type="submit" name="currentPage" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                            <input type="hidden" name="command" value="paging">
                        </form>
                    </c:forEach>
                </c:if>
                <c:if test="${noPage<7}">
                    <c:forEach begin="1" end="${noPage}" var="i">
                        <form action="store" method="post">
                            <button type="submit" name="currentPage" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                            <input type="hidden" name="command" value="paging">
                        </form>
                    </c:forEach>
                </c:if>
                <c:if test="${currentPage>1}">
                    <form action="store" method="post">
                        <button type="submit" name="currentPage" value="${currentPage-1}"><<</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                    <form action="store" method="post">
                        <button type="submit" name="currentPage" value="1">First</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                </c:if>
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
