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
import java.io.IOException;
import java.util.ArrayList;
import model.Account;



/**
 *
 * @author Mosena
 */
public class AccountServlet extends HttpServlet {
    AccountDAO ad=new AccountDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int count=ad.countAccounts();
        int index=1;
        int noPage=(int) Math.ceil((double)count/10);
        if(request.getParameter("currentPage")==null){
            request.setAttribute("currentPage", index);
        }
        else{
            index=Integer.parseInt(request.getParameter("currentPage"));
            request.setAttribute("currentPage",index);
        }
        if(request.getParameter("username")!=null){
            request.setAttribute("username", 
                    request.getParameter("username"));
        }
        if(request.getParameter("data")==null){
            request.setAttribute("data", "show");
        }
        else{
            request.setAttribute("data", 
                    request.getParameter("data"));
        }
        ArrayList<Account> list=ad.getAccounts(index);
        request.setAttribute("noPage", noPage);
        request.setAttribute("end", noPage);
        request.setAttribute("list", list);
        request.getRequestDispatcher("accounts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("username");
        String command=request.getParameter("command");
        int index=Integer.parseInt(request.getParameter("index"));
        switch(command){
            case "edit":
                response.sendRedirect("accounts?data=edit&username="+username
                        + "&currentPage="+index);
                break;
            case "paging":
                response.sendRedirect("accounts?currentPage="+index);
                break;
            case "done":
                System.out.println(username);
                String password=request.getParameter("password");
                String email=request.getParameter("email");
                String phonenumber=request.getParameter("phonenumber");
                String firstName=request.getParameter("firstName");
                String lastName=request.getParameter("lastName");
                ad.updateAccount(username, password, email, 
                        phonenumber,firstName,lastName);
                response.sendRedirect("accounts?currentPage="+index);
                break;    
            case "delete":
                ad.deleteAccount(username);
                response.sendRedirect("accounts?currentPage="+index);
                break;
            case "ban":
                ad.changeRole(username, 5);
                response.sendRedirect("accounts?currentPage="+index);
                break;
            case "unban":
                ad.changeRole(username, 4);
                response.sendRedirect("accounts?currentPage="+index);
                break;
        }
    }
}
