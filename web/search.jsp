<%-- 
    Document   : search
    Created on : Feb 28, 2023, 4:52:05 PM
    Author     : Mosena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:redirect url="home.jsp"/>
        </c:if>
        <a href="home.jsp"><span class="glyphicon glyphicon-home">Home</span></a>
        <form action="search" method="GET" class="search-form" style="display: flex;justify-content: center">
            <div class="form-group">
                <div class="input-group">
                    <select class="form-control" name="type">
                        <option value="username">Search by username</option>
                        <option value="email">Search by email</option>
                        <option value="phonenumber">Search by phone number</option>
                        <option value="firstName">Search by first name</option>
                        <option value="lastName">Search by last name</option>
                    </select>
                    <input type="text" class="form-control" name="search" placeholder="Search">
                    <span class="input-group-btn">
                        <button class="form-control" type="submit">
                            Search
                        </button>
                    </span>
                </div>
            </div>
            <input type="hidden" name="searchFor" value="account">
        </form>
        <c:if test="${permission==-2 || permission==-1}">
            <a href="insert" style="display: flex;justify-content: right; margin: 5px ">
            <span class="glyphicon glyphicon-plus">Add</span>
            </a>
        </c:if>
        <table class="table table-striped table-bordered table-hover">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Role</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>Phone number</th>
                    <th>Edit</th>
                    <th>Delete</th>
                    <th>Ban</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="x">
                    <tr>
                        <td>
                            ${x.username}
                        </td>
                        <td>
                            ${x.role.roleName}
                        </td>
                        <form id="edit-form-${x.username}" method="POST" action="accounts">
                            <td>
                                <c:if test="${data=='edit' && username==x.username}">
                                    <input type="text" value="${x.firstName}" name="firstName">
                                </c:if>
                                <c:if test="${data=='show' || username!=x.username}">
                                    ${x.firstName}
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${data=='edit' && username==x.username}">
                                    <input type="text" value="${x.lastName}" name="lastName">
                                </c:if>
                                <c:if test="${data=='show' || username!=x.username}">
                                    ${x.lastName}
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${data=='edit' && username==x.username}">
                                    <input type="text" value="${x.password}" name="password">
                                </c:if>
                                <c:if test="${data=='show' || username!=x.username}">
                                    ${x.password}
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${data=='edit' && username==x.username}">
                                    <input type="text" value="${x.email}" name="email">
                                </c:if>
                                <c:if test="${data=='show' || username!=x.username}">
                                    ${x.email}
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${data=='edit' && username==x.username}">
                                    <input type="text" value="${x.phonenumber}" name="phonenumber">
                                </c:if>
                                <c:if test="${data=='show' || username!=x.username}">
                                    ${x.phonenumber}
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${data=='show' || username!=x.username}">
                                    <a href="" onclick="event.preventDefault(); document.getElementById('edit-form-${x.username}').submit();">
                                        <span class="bi bi-gear">Edit</span>
                                    </a>
                                    <input type="hidden" name="command" value="edit">
                                    <input type="hidden" name="username" value="${x.username}">
                                    <input type="hidden" name="index" value="${currentPage}">
                                </c:if>
                                <c:if test="${data=='edit' && username==x.username}">
                                    <a href="" onclick="event.preventDefault(); document.getElementById('edit-form-${x.username}').submit();">
                                        <span class="glyphicon glyphicon-ok">Done</span>
                                    </a>
                                    <input type="hidden" name="command" value="done">
                                    <input type="hidden" name="username" value="${x.username}">
                                    <input type="hidden" name="index" value="${currentPage}">
                                </c:if>
                            </td>
                        </form>
                        <td>
                            <c:if test="${permission<x.role.permissionLevel}">
                                <a href="" onclick="event.preventDefault(); document.getElementById('delete-form-${x.username}').submit();">
                                    Delete
                                </a>
                                <form id="delete-form-${x.username}" method="POST" action="accounts">
                                    <input type="hidden" name="command" value="delete">
                                    <input type="hidden" name="username" value="${x.username}">
                                </form>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${permission<x.role.permissionLevel}">
                                <c:if test="${x.role.permissionLevel!=2}">
                                    <a href="" onclick="event.preventDefault(); document.getElementById('ban-form-${x.username}').submit();">
                                        Ban
                                    </a>
                                    <form id="ban-form-${x.username}" method="POST" action="accounts">
                                        <input type="hidden" name="command" value="ban">
                                        <input type="hidden" name="username" value="${x.username}">
                                    </form>
                                </c:if>
                                <c:if test="${x.role.permissionLevel==2}">
                                    <a href="" onclick="event.preventDefault(); document.getElementById('unban-form-${x.username}').submit();">
                                        Unban
                                    </a>
                                    <form id="unban-form-${x.username}" method="POST" action="accounts">
                                        <input type="hidden" name="command" value="unban">
                                        <input type="hidden" name="username" value="${x.username}">
                                    </form>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div style="display: flex; justify-content: center">
            <c:if test="${currentPage<noPage}">
                <form action="search" method="get">
                    <button type="submit" name="index" value="${noPage}">Last</button>
                    <input type="hidden" name="type" value="${type}">
                    <input type="hidden" name="search" value=${search}>
                </form>
                <form action="search" method="get">
                    <button type="submit" name="index" value="${currentPage+1}">>></button>
                    <input type="hidden" name="type" value="${type}">
                    <input type="hidden" name="search" value=${search}>
                </form>
            </c:if>
            <c:if test="${noPage>7}">
                <c:forEach begin="1" end="3" var="i">
                    <form action="search" method="get">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="type" value="${type}">
                        <input type="hidden" name="search" value=${search}>
                    </form>
                </c:forEach>
                ...
                <c:if test="${currentPage>3 && currentPage<end-2}">
                    <form action="search" method="get">
                        <button type="submit" name="index" value="${currentPage}" style="background-color: yellow;font-weight: bold">${currentPage}</button>
                        <input type="hidden" name="type" value="${type}">
                        <input type="hidden" name="search" value=${search}>
                    </form>
                    ...
                </c:if>
                <c:forEach begin="${end-2}" end="${end}" var="i">
                    <form action="search" method="get">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="type" value="${type}">
                        <input type="hidden" name="search" value=${search}>
                    </form>
                </c:forEach>
            </c:if>
            <c:if test="${noPage<7}">
                <c:forEach begin="1" end="${noPage}" var="i">
                    <form action="search" method="get">
                        <button type="submit" name="index" value="${i}" class="${i == currentPage ? 'current-page' : ''}">${i}</button>
                        <input type="hidden" name="type" value="${type}">
                        <input type="hidden" name="search" value=${search}>
                    </form>
                </c:forEach>
            </c:if>
            <c:if test="${currentPage>1}">
                <form action="search" method="get">
                    <button type="submit" name="index" value="${currentPage-1}"><<</button>
                    <input type="hidden" name="type" value="${type}">
                    <input type="hidden" name="search" value=${search}>
                </form>
                <form action="search" method="get">
                    <button type="submit" name="index" value="1">First</button>
                    <input type="hidden" name="type" value="${type}">
                    <input type="hidden" name="search" value=${search}>
                </form>
            </c:if>
        </div>
    </body>
</html>
