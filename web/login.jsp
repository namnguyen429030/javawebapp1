<%-- 
    Document   : login
    Created on : Feb 18, 2023, 1:34:38 PM
    Author     : Mosena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login | Gio cha Bay Ngoc</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="message.js"></script>
        <style>
        body {
          background-color: #f5f5f5;
        }

        .container {
          margin-top: 30px;
        }

        .login-form {
          background-color: #fff;
          padding: 20px;
          box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
        }

        h2 {
          margin-top: 0;
        }

        .form-group {
          margin-bottom: 20px;
        }

        label {
          font-weight: bold;
        }

        .btn-login {
          background-color: #ffc107;
          color: #fff;
          border: none;
        }

        .btn-login:hover {
          background-color: #e0a800;
        }
      </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">
                    <div class="login-form">
                        <div class="row">
                            <div class="col-xs-1">
                            <a href="index.html"><span class="glyphicon glyphicon-home"></span></a>
                            </div>
                            <div class="col-xs-11">
                                <h2>Login</h2>
                            </div>
                        </div>
                    <form action="login" method="post">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div style="color: red; text-align: center;">
                            <p>${message}</p>
                        </div>
                        <button type="submit" class="btn btn-lg btn-block btn-login">Login</button>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
