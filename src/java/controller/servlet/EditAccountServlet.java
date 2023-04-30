/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.servlet;

import controller.DAO.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;


/**
 *
 * @author Mosena
 */
public class EditAccountServlet extends HttpServlet {
    AccountDAO ad=new AccountDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        Account a=ad.findAccount(username);
        request.setAttribute("account", a);
        request.getRequestDispatcher("editProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message;
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String username=request.getParameter("username");
        Account a=ad.findAccount(username);
        String password=request.getParameter("password");
        String newPassword=request.getParameter("newPassword");
        String rePassword=request.getParameter("re-password");
        String email=request.getParameter("email");
        String phoneNumber=request.getParameter("phonenumber");
        if(!ad.checkPassword(a,password)){
            message="• Wrong password";
            request.setAttribute("message0", message);
        }
        else if(!ad.validatePassword(newPassword)){
            message="• Your password must contain at least 8 characters";
            request.setAttribute("message1", message);
            message="• And at least 1 uppercase, lowercase letter and special character which is.,?!@#$%";
            request.setAttribute("message1a",message);
        }
        else if(!ad.samePassword(newPassword, rePassword)){
            message="• You must re-enter the same password";
            request.setAttribute("message2", message);
        }
        else if(ad.updateAccount(username, password, email, 
                phoneNumber,firstName,lastName)){
            message="• Edit successfully";
            request.setAttribute("message5", message);
        }
        request.setAttribute("account", a);
        request.getRequestDispatcher("editProfile.jsp").forward(request, response);
    }

}
