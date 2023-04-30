<%-- 
    Document   : instorage
    Created on : Mar 5, 2023, 4:26:36 PM
    Author     : Mosena
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Account management</title>
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
            <c:redirect url="index.html"/>
        </c:if>
        <div class="row">
            <div class="col-md-6">
                <a href="home.jsp"><span class="glyphicon glyphicon-home">Home</span></a>
            </div>
            <div class="col-md-6">
                <form action="instorage" method="post" id="sort-by-form">
                    <div class="select-container">
                        <select name="sortBy" onchange="document.getElementById('order').style.display='block'">
                            <option selected hidden>Sort</option>
                            <option value="productId">
                                Sort by ID
                            </option>
                            <option value="expDate">
                                Sort by expire date
                            </option>
                            <option value="mfgDate">
                                Sort by manufacture date
                            </option>
                            <option value="number">
                                Sort by number
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
                <form style="display: none" action="instorage" method="POST" id="add-form">
                    <div class="form-group">
                        <select name="productId">
                            <option selected hidden>Select ID</option>
                            <c:forEach items="${pdList}" var="p">
                                <option value="${p.productID}">
                                    ${p.productName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="date" name="mfgDate" placeholder="Manufacture date" required>
                    </div>
                    <div class="form-group">
                        <input type="number" name="number" required>
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
                    <th>Quantity</th>
                    <th>MfgDate</th>
                    <th>ExpDate</th>
                    <th>Edit</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="x">
                    <tr>
                        <td>
                            ${x.product.productID}
                        </td>
                        <td>
                            ${x.product.productName}
                        </td>
                        <form id="edit-form-${x.mfgDate}-${x.product.productID}" method="POST" action="instorage">
                            <td>
                                <c:if test="${data=='edit' && mfgDate==x.mfgDate && productId==x.product.productID}">
                                    <input type="number" value="${x.number}" name="number">
                                </c:if>
                                <c:if test="${data=='show' || mfgDate!=x.mfgDate || productId!=x.product.productID}">
                                    ${x.number}
                                </c:if>
                            </td>
                            <td>
                                ${x.mfgDate}
                            </td>
                            <td>
                                ${x.expDate}
                            </td>
                            <td>
                                <c:if test="${data=='show' || mfgDate!=x.mfgDate || productId!=x.product.productID}">
                                    <a href="" onclick="event.preventDefault(); document.getElementById('edit-form-${x.mfgDate}-${x.product.productID}').submit();">
                                        <span class="bi bi-gear">Edit</span>
                                    </a>
                                    <input type="hidden" name="command" value="edit">
                                    <input type="hidden" name="mfgDate" value="${x.mfgDate}">
                                    <input type="hidden" name="productId" value="${x.product.productID}">
                                    <input type="hidden" name="index" value="${currentPage}">
                                </c:if>
                                <c:if test="${data=='edit' && mfgDate==x.mfgDate && productId==x.product.productID}">
                                    <a href="" onclick="event.preventDefault(); document.getElementById('edit-form-${x.mfgDate}-${x.product.productID}').submit();">
                                        <span class="glyphicon glyphicon-ok">Done</span>
                                    </a>
                                    <input type="hidden" name="command" value="done">
                                    <input type="hidden" name="mfgDate" value="${x.mfgDate}">
                                    <input type="hidden" name="productId" value="${x.product.productID}">
                                    <input type="hidden" name="index" value="${currentPage}">
                                </c:if>
                            </td>
                        </form>
                        <td>
                            <a href="" onclick="event.preventDefault(); document.getElementById('delete-form-${x.mfgDate}-${x.product.productID}').submit();">
                                Remove
                            </a>
                            <form id="delete-form-${x.mfgDate}-${x.product.productID}" method="POST" action="instorage">
                                <input type="hidden" name="command" value="delete">
                                <input type="hidden" name="mfgDate" value="${x.mfgDate}">
                                <input type="hidden" name="productId" value="${x.product.productID}">
                                <input type="hidden" name="index" value="${currentPage}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div style="display: flex; justify-content: center">
            <c:if test="${currentPage<noPage}">
                <form action="instorage" method="post">
                    <button type="submit" name="index" value="${noPage}">Last</button>
                    <input type="hidden" name="command" value="paging">
                </form>
                <form action="instorage" method="post">
                    <button type="submit" name="index" value="${currentPage+1}">>></button>
                    <input type="hidden" name="command" value="paging">
                </form>
            </c:if>
            <c:if test="${noPage>7}">
                <c:forEach begin="1" end="3" var="i">
                    <form action="instorage" method="post">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                </c:forEach>
                ...
                <c:if test="${currentPage>3 && currentPage<end-2}">
                    <form action="instorage" method="post">
                        <button type="submit" name="index" value="${currentPage}" style="background-color: yellow;font-weight: bold">${currentPage}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                    ...
                </c:if>
                <c:forEach begin="${end-2}" end="${end}" var="i">
                    <form action="instorage" method="post">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                </c:forEach>
            </c:if>
            <c:if test="${noPage<7}">
                <c:forEach begin="1" end="${noPage}" var="i">
                    <form action="instorage" method="post">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="command" value="paging">
                    </form>
                </c:forEach>
            </c:if>
            <c:if test="${currentPage>1}">
                <form action="instorage" method="post">
                    <button type="submit" name="index" value="${currentPage-1}"><<</button>
                    <input type="hidden" name="command" value="paging">
                </form>
                <form action="instorage" method="post">
                    <button type="submit" name="index" value="1">First</button>
                    <input type="hidden" name="command" value="paging">
                </form>
            </c:if>
        </div>
    </body>
</html>
