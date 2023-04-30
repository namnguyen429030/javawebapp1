/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.servlet;

import controller.DAO.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Mosena
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO ad=new AccountDAO();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Account a=ad.findAccount(username);
        String message;
        if(a==null){
            message="Your account does not exist";
        }
        else if(!ad.checkPassword(a, password)){
            message="Wrong password";
        }
        else if(!ad.checkRole(a)){
            message="Your account has been banned";
        }
        else{
            int permission=a.getRole().getPermissionLevel();
            HttpSession session=request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("permission", permission);
            session.setAttribute("firstName", a.getFirstName());
            response.sendRedirect("home.jsp");
            return;
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
