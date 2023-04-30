<%-- 
    Document   : editProfile
    Created on : Mar 14, 2023, 10:09:18 AM
    Author     : Mosena
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit profile</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            body {
                background-color: #f5f5f5;
            }
            .form-container {
                margin-top: 20px;
                max-width: 500px;
                margin-left: auto;
                margin-right: auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
            }
            h1 {
                text-align: center;
                font-size: 2.5em;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-group label {
                font-size: 1.2em;
            }
            .form-group input[type="text"], .form-group input[type="password"],
            .form-group input[type="email"], .form-group input[type="tel"]{
                font-size: 1.2em;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
                width: 100%;
                box-sizing: border-box;
            }
            .btn-register {
                background-color: #008cba;
                color: #fff;
                font-size: 1.2em;
                padding: 10px 20px;
                border-radius: 5px;
                border: none;
                cursor: pointer;
                transition: all 0.3s;
            }
            .btn-register:hover {
                background-color: #005b84;
            }
        </style>
    </head>
    <body>
        <c:if test="${username==null || permission==2}">
            <c:redirect url="index.html"/>
        </c:if>
        <div class="container">
            <div class="form-container">
                <c:if test="${message5!=null}">
                    <div style="color: #008cba; text-align: center"> 
                        <p>${message5}</p>
                    </div>
                </c:if>
                <a href="home.jsp   "><span class="glyphicon glyphicon-home"></span></a>
                <h1>Edit</h1>
                <form action="editaccount" method="post">
                    <div class="form-group">
                        <label for="firstName">First name</label>
                        <input type="text" name="firstName" id="firstName" value="${account.firstName}" required>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last name</label>
                        <input type="text" name="lastName" id="lastName" value="${account.lastName}" required>
                    </div>
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" name="username" id="username" value="${account.username}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" name="password" id="password" required>
                        <c:if test="${message0!=null}">
                            <div style="color: red;"> 
                                <p>${message0}</p>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="password">New password:</label>
                        <input type="password" name="newPassword" id="newPassword" required>
                        <c:if test="${message1!=null}">
                            <div style="color: red;"> 
                                <p>${message1}</p>
                            </div>
                        </c:if>
                        <c:if test="${message1a!=null}">
                            <div style="color: red;"> 
                                <p>${message1a}</p>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="re-password">Re-enter password:</label>
                        <input type="password" name="re-password" id="re-password" required>
                        <c:if test="${message2!=null}">
                            <div style="color: red;"> 
                                <p>${message2}</p>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" name="email" id="email" value="${account.email}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="phonenumber">Phone number:</label>
                        <input type="tel" name="phonenumber" id="phonenumber" value="${account.phonenumber}" readonly>
                    </div>
                    <button type="submit" class="btn btn-primary btn-register">Done</button>
                </form>
            </div>
        </div>
    </body>
</html>
