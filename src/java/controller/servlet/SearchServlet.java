/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.servlet;

import controller.DAO.AccountDAO;
import controller.DAO.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Mosena
 */
public class SearchServlet extends HttpServlet {
    AccountDAO ad=new AccountDAO();
    OrderDAO od=new OrderDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index;
        if(request.getParameter("index")==null){
            index=1;
        }
        else{
            index=Integer.parseInt(request.getParameter("index"));
        }
        String type=request.getParameter("type");
        String search_content=request.getParameter("search");
        String searchFor=request.getParameter("searchFor");
        ArrayList list=new ArrayList();
        ArrayList pagingList=new ArrayList();
        if(search_content.isEmpty()){
            if(searchFor.equals("account")){
                response.sendRedirect("accounts");
                return;
            }
            else if(searchFor.equals("order")){
                response.sendRedirect("orders");
                return;
            }
        }
        
        switch(type){
            case "username":
                list=ad.findAccountByUsername(search_content);
                pagingList=ad.findAccountByUsername(search_content
                        ,index);
                break;
            case "firstName":
                list=ad.findAccountByFirstName(search_content);
                pagingList=ad.findAccountByFirstName(search_content
                        ,index);
                break;
            case "lastName":
                list=ad.findAccountByLastName(search_content);
                pagingList=ad.findAccountByLastName(search_content
                        ,index);
                break;
            case "role":
                int role=Integer.parseInt(search_content);
                list=ad.findAccountByRole(role);
                pagingList=ad.findAccountByRole(role, index);
                break;
            case "email":
                list=ad.findAccountByEmail(search_content);
                pagingList=ad.findAccountByEmail(search_content
                        ,index);
                break;
            case "phonenumber":
                list=ad.findAccountByPhonenumber(search_content);
                pagingList=ad.findAccountByPhonenumber(search_content
                        ,index);
                break;
            case "orderId":
                list=od.findOrderByOrderID(search_content);
                pagingList=od.findOrderByOrderID(search_content, 
                        index);
                break;
            case "usernameOrder":
                list=od.findOrderByUserName(search_content);
                pagingList=od.findOrderByUserName(search_content,
                        index);
                break;
            case "condition":
                list=od.findOrderByCondition(search_content);
                pagingList=od.findOrderByCondition(search_content, 
                        index);
                break;
        }
        int count=list.size();
        int noPage=(int) Math.ceil((double)count/10);
        request.setAttribute("searchFor", searchFor);
        request.setAttribute("end", noPage);
        request.setAttribute("data", "show");
        request.setAttribute("list", pagingList);
        request.setAttribute("currentPage", index);
        request.setAttribute("noPage", noPage);
        request.setAttribute("search", search_content);
        request.setAttribute("type", type);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
