<%-- 
    Document   : products
    Created on : Feb 18, 2023, 8:24:37 PM
    Author     : Mosena
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Product management</title>
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
            .select-container {
                display: inline-flex;
                align-items: center;
            }

            .select-container select {
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <c:if test="${permission!=0 && permission!=-1 && permission!=-2}">
            <c:redirect url="home.jsp"/>
        </c:if>
        <div class="row">
            <div class="col-md-6">
                <a href="home.jsp"><span class="glyphicon glyphicon-home">Home</span></a>
            </div>
            <div class="col-md-6">
                <form action="products" method="post" id="sort-by-form">
                    <div class="select-container">
                        <select name="sortBy" onchange="document.getElementById('order').style.display='block'">
                            <option selected hidden>Sort</option>
                            <option value="productId">
                                Sort by ID
                            </option>
                            <option value="productName">
                                Sort by name
                            </option>
                            <option value="price">
                                Sort by price
                            </option>
                            <option value="expireTime">
                                Sort by expire time
                            </option>
                        </select>
                        <select name="order" id="order" style="display: none" onchange="document.getElementById('sort-by-form').submit()">
                            <option selected hidden>Order</option>
                            <option value="asc">
                                Ascending
                            </option>
                            <option value="desc">
                                Descending
                            </option>
                        </select>
                    </div>
                    <input type="hidden" name="command" value="sort">
                    <input type="hidden" name="index" value="${currentPage}">
                </form>
            </div>
        </div>
        <a href="" onclick="event.preventDefault();document.getElementById('add-form').style.display='block'" style="display: flex;justify-content: right; margin: 5px ">
            <span class="glyphicon glyphicon-plus">Add</span>
        </a>
        <c:if test="${message!=null}">
            <div style="color: #008cba; text-align: right"> 
                <p>${message}</p>
            </div>
        </c:if>
        <div class="container" style="display: flex;justify-content: center;">
            <div class="form-container">
                <form style="display: none" action="products" method="POST" id="add-form">
                    <div class="form-group">
                        <input type="text" name="productId" placeholder="ID" required>
                    </div>
                    <div class="form-group">
                        <input type="text" name="productName" placeholder="Name" required>
                    </div>
                    <div class="form-group">
                        <input type="text" name="price" placeholder="Price" required>
                    </div>
                    <div class="form-group">
                        <input type="text" name="expireTime" placeholder="Expire time" required>
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
        <table class="table table-striped table-bordered table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Date</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="x">
                    <tr>
                        <td>
                            ${x.productID}
                        </td>
                        <form id="edit-form-${x.productID}" method="POST" action="products">
                            <td>
                                <c:if test="${data=='show' || productId!=x.productID}">
                                     ${x.productName}
                                </c:if>
                                <c:if test="${data=='edit' && productId==x.productID}">
                                     <input type="text" value="${x.productName}" name="productName">
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${data=='show' || productId!=x.productID}">
                                    ${x.price}VND
                                </c:if>
                                <c:if test="${data=='edit' && productId==x.productID}">
                                     <input type="text" value="${x.price}" name="price">
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${data=='show' || productId!=x.productID}">
                                    ${x.expireTime}
                                </c:if>
                                <c:if test="${data=='edit' && productId==x.productID}">
                                     <input type="text" value="${x.expireTime}" name="expireTime">
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${data=='show' || productId!=x.productID}">
                                    <a href="" onclick="event.preventDefault(); document.getElementById('edit-form-${x.productID}').submit();">
                                        <span class="bi bi-gear">Edit</span>
                                    </a>
                                    <input type="hidden" name="command" value="edit">
                                    <input type="hidden" name="productId" value="${x.productID}">
                                    <input type="hidden" name="index" value="${currentPage}">
                                </c:if>
                                <c:if test="${data=='edit' && productId==x.productID}">
                                    <a href="" onclick="event.preventDefault(); document.getElementById('edit-form-${x.productID}').submit();">
                                        <span class="glyphicon glyphicon-ok">Done</span>
                                    </a>
                                    <input type="hidden" name="command" value="done">
                                    <input type="hidden" name="productId" value="${x.productID}">
                                    <input type="hidden" name="index" value="${currentPage}">
                                </c:if>
                            </td>
                        </form>
                        <td>
                            <a href="" onclick="event.preventDefault(); document.getElementById('delete-form-${x.productID}').submit();">
                                Delete
                            </a>
                            <form id="delete-form-${x.productID}" method="POST" action="products">
                                <input type="hidden" name="command" value="delete">
                                <input type="hidden" name="productId" value="${x.productID}">
                                <input type="hidden" name="index" value="${currentPage}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div style="display: flex; justify-content: center">
            <c:if test="${currentPage<noPage}">
                <form action="accounts" method="post">
                    <button type="submit" name="index" value="${noPage}">Last</button>
                    <input type="hidden" name="command" value="paging">
                </form>
                <form action="accounts" method="post">
                    <button type="submit" name="index" value="${currentPage+1}">>></button>
                    <input type="hidden" name="command" value="paging">
                </form>
            </c:if>
            <c:if test="${noPage>7}">
                <c:forEach begin="1" end="3" var="i">
                    <form action="accounts" method="post">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                </c:forEach>
                ...
                <c:if test="${currentPage>3 && currentPage<end-2}">
                    <form action="accounts" method="post">
                        <button type="submit" name="index" value="${currentPage}" style="background-color: yellow;font-weight: bold">${currentPage}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                    ...
                </c:if>
                <c:forEach begin="${end-2}" end="${end}" var="i">
                    <form action="accounts" method="post">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                </c:forEach>
            </c:if>
            <c:if test="${noPage<7}">
                <c:forEach begin="1" end="${noPage}" var="i">
                    <form action="accounts" method="post">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                </c:forEach>
            </c:if>
            <c:if test="${currentPage>1}">
                <form action="accounts" method="post">
                    <button type="submit" name="index" value="${currentPage-1}"><<</button>
                    <input type="hidden" name="command" value="paging">
                </form>
                <form action="accounts" method="post">
                    <button type="submit" name="index" value="1">First</button>
                    <input type="hidden" name="command" value="paging">
                </form>
            </c:if>
        </div>
    </body>
</html>
