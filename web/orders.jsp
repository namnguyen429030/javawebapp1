<%-- 
    Document   : orders
    Created on : Feb 18, 2023, 8:24:31 PM
    Author     : Mosena
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Order management</title>
        <style>
            .search-form .form-group {
                margin-bottom: 0;
            }
            .search-form .form-control {
                display: inline-block;
                width: auto;
                vertical-align: middle;
            }
            .search-form .input-group-btn {
                display: inline-block;
                vertical-align: middle;
            }
            .current-page {
                background-color: yellow;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <c:if test="${permission!=0 && permission!=-1 && permission!=-2}">
            <c:redirect url="home.jsp"/>
        </c:if>
        <a href="home.jsp"><span class="glyphicon glyphicon-home">Home</span></a>
        <form action="search" method="GET" class="search-form" style="display: flex;justify-content: center">
            <div class="form-group">
                <div class="input-group">
                    <select class="form-control" name="type">
                        <option value="orderId">Search by ID</option>
                        <option value="usernameOrder">Search by username</option>
                        <option value="condition">Search by condition</option>
                    </select>
                    <input type="text" class="form-control" name="search" placeholder="Search">
                    <span class="input-group-btn">
                        <button class="form-control" type="submit">
                            Search
                        </button>
                    </span>
                </div>
            </div>
            <input type="hidden" name="searchFor" value="order">
        </form>
        <a href="insert" onclick="event.preventDefault();document.getElementById('add-form').style.display='block'" style="display: flex;justify-content: right; margin: 5px ">
            <span class="glyphicon glyphicon-plus">Add</span>
        </a>
        <div class="container my-3">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <form class="p-3 bg-light" action="orders" method="POST" id="add-form" style="display: none">
                        <div class="form-group">
                            <h5>Select Products:</h5>
                            <div class="form-check">
                                <c:forEach items="${pdList}" var="p">
                                    <input class="form-check-input" type="checkbox" name="product" value="${p.productID}">
                                    <label class="form-check-label" for="product">${p.productName}</label><br>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <h5>Enter Quantity:</h5>
                            <c:forEach items="${pdList}" var="p">
                                <label for="quantity">${p.productName}:</label>
                                <input class="form-control" type="text" name="quantity" value="0" required><br>
                            </c:forEach>
                        </div>
                        <div class="form-group">
                            <label for="username">Name:</label>
                            <input class="form-control" type="text" name="username"required>
                        </div>
                        <div class="form-group">
                            <label for="date">Date:</label>
                            <input class="form-control" type="date" name="date"required>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <input type="submit" value="Add">
                            </div>
                            <div class="col-md-6">
                                <button onclick="event.preventDefault();document.getElementById('add-form').reset();document.getElementById('add-form').style.display='none'">Cancel</button>
                            </div>
                        </div>
                        <input type="hidden" name="index" value="${currentPage}">
                        <input type="hidden" name="command" value="add">
                    </form>
                </div>
            </div>
        </div>
        <table class="table table-striped table-bordered table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Product</th>
                    <th>Number</th>
                    <th>Date</th>
                    <th>Condition</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="x">
                    <c:forEach items="${x.orderList}" var="product" varStatus="status">
                        <tr>
                            <td>
                                ${x.orderID}
                            </td>
                            <td>
                                ${x.username}
                            </td>
                            <td>
                                ${product.productName}
                            </td>
                            <form id="edit-form-${x.orderID}-${product.productID}" method="POST" action="orders">
                                <td>
                                    <c:if test="${data=='edit' && orderId==x.orderID && productId==product.productID}">
                                        <input type="text" value="${x.number.get(status.index)}" name="number">
                                    </c:if>
                                    <c:if test="${data=='show' || orderId!=x.orderID || productId!=product.productID}">
                                        ${x.number.get(status.index)}
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${data=='edit' && orderId==x.orderID && productId==product.productID}">
                                        <input type="date" value="${x.date.get(status.index)}" name="date">
                                    </c:if>
                                    <c:if test="${data=='show' || orderId!=x.orderID || productId!=product.productID}">
                                        ${x.date.get(status.index)}
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${data=='edit' && orderId==x.orderID && productId==product.productID}">
                                        <input type="text" value="${x.condition.get(status.index)}" name="condition">
                                    </c:if>
                                    <c:if test="${data=='show' || orderId!=x.orderID || productId!=product.productID}">
                                        ${x.condition.get(status.index)}
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${data=='edit' && orderId==x.orderID && productId==product.productID}">
                                        <a href="" onclick="event.preventDefault(); document.getElementById('edit-form-${x.orderID}-${product.productID}').submit();">
                                            <span class="glyphicon glyphicon-ok">Done</span>
                                        </a>
                                        <input type="hidden" name="command" value="done">
                                        <input type="hidden" name="orderId" value="${x.orderID}">
                                        <input type="hidden" name="productId" value="${product.productID}">
                                        <input type="hidden" name="index" value="${currentPage}">
                                    </c:if>
                                    <c:if test="${data=='show' || orderId!=x.orderID || productId!=product.productID}">
                                        <a href="" onclick="event.preventDefault(); document.getElementById('edit-form-${x.orderID}-${product.productID}').submit();">
                                            <span class="bi bi-gear">Edit</span>
                                        </a>
                                        <input type="hidden" name="command" value="edit">
                                        <input type="hidden" name="orderId" value="${x.orderID}">
                                        <input type="hidden" name="productId" value="${product.productID}">
                                        <input type="hidden" name="index" value="${currentPage}">
                                    </c:if>
                                </td>
                            </form>
                            <td>
                                <a href="" onclick="event.preventDefault(); document.getElementById('delete-form-${x.orderID}-${product.productID}').submit();">
                                    Delete
                                </a>
                                <form id="delete-form-${x.orderID}-${product.productID}" method="POST" action="orders">
                                    <input type="hidden" name="command" value="delete">
                                    <input type="hidden" name="orderId" value="${x.orderID}">
                                    <input type="hidden" name="productId" value="${product.productID}">
                                    <input type="hidden" name="index" value="${currentPage}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </tbody>
        </table>
        <div style="display: flex; justify-content: center">
            <c:if test="${currentPage<noPage}">
                <form action="orders" method="post">
                    <button type="submit" name="index" value="${noPage}">Last</button>
                    <input type="hidden" name="command" value="paging">
                </form>
                <form action="orders" method="post">
                    <button type="submit" name="index" value="${currentPage+1}">>></button>
                    <input type="hidden" name="command" value="paging">
                </form>
            </c:if>
            <c:if test="${noPage>7}">
                <c:forEach begin="1" end="3" var="i">
                    <form action="orders" method="post">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                </c:forEach>
                ...
                <c:if test="${currentPage>3 && currentPage<end-2}">
                    <form action="orders" method="post">
                        <button type="submit" name="index" value="${currentPage}">${currentPage}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                    ...
                </c:if>
                <c:forEach begin="${end-2}" end="${end}" var="i">
                    <form action="orders" method="post">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                </c:forEach>
            </c:if>
            <c:if test="${noPage<7}">
                <c:forEach begin="1" end="${noPage}" var="i">
                    <form action="orders" method="post">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                </c:forEach>
            </c:if>
            <c:if test="${currentPage>1}">
                <form action="orders" method="post">
                    <button type="submit" name="index" value="${currentPage-1}"><<</button>
                    <input type="hidden" name="command" value="paging">
                </form>
                <form action="orders" method="post">
                    <button type="submit" name="index" value="1">First</button>
                    <input type="hidden" name="command" value="paging">
                </form>
            </c:if>
        </div>
    </body>
</html>
