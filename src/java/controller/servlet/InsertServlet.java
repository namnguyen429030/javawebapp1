/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.servlet;

import controller.DAO.AccountDAO;
import controller.DAO.CountryDAO;
import controller.DAO.RoleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Country;
import model.Role;


/**
 *
 * @author Mosena
 */
public class InsertServlet extends HttpServlet {
    RoleDAO rd=new RoleDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CountryDAO cd=new CountryDAO();
        ArrayList<Country> countries=cd.getCountriesDetail();
        ArrayList<Role> roles=rd.getRoles();
        request.setAttribute("countries", countries);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("insert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO ad=new AccountDAO();
        String message;
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String rePassword=request.getParameter("re-password");
        String email=request.getParameter("email");
        int role=Integer.parseInt(request.getParameter("role"));
        String callingCode=request.getParameter("country-code");
        String phoneNumber=request.getParameter("phonenumber");
        if(!ad.validateUsername(username)){
            message="• Your username has already been taken";
            request.setAttribute("message0", message);
        }
        else if(!ad.validatePassword(password)){
            message="• Your password must contain at least 8 characters";
            request.setAttribute("message1", message);
            message="• And at least 1 uppercase, lowercase letter and special character which is.,?!@#$%";
            request.setAttribute("message1a",message);
        }
        else if(!ad.samePassword(password, rePassword)){
            message="• You must re-enter the same password";
            request.setAttribute("message2", message);
        }
        else if(!ad.validateEmail(email)){
            message="• Invalid email";
            request.setAttribute("message3", message);
        }
        else if(!ad.validatePhonenumber(phoneNumber)){
            message="• Invalid phonenumber";
            request.setAttribute("message4", message);
        }
        else{
            if(!phoneNumber.isEmpty()){
                if(phoneNumber.charAt(0)=='0'){
                    phoneNumber=phoneNumber.substring(1);
                }
                phoneNumber="+"+callingCode+phoneNumber;
            }
            else{
                phoneNumber=null;
            }
            if(email.isEmpty()){
                email=null;
            }
            if(ad.addAccount(username, password, role, email, 
                    phoneNumber,firstName,lastName)){
                message="• Add successfully";
                request.setAttribute("message5", message);
            }
        }
        CountryDAO cd=new CountryDAO();
        ArrayList<Country> countries=cd.getCountriesDetail();
        ArrayList<Role> roles=rd.getRoles();
        request.setAttribute("countries", countries);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("insert.jsp").forward(request, response);
    }

}
