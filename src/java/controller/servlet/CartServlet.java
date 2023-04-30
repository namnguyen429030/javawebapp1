/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.servlet;

import controller.DAO.OrderDAO;
import controller.DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.Order;


/**
 *
 * @author Mosena
 */
public class CartServlet extends HttpServlet {
    OrderDAO od=new OrderDAO();
    ProductDAO pd=new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        String userName=(String) session.getAttribute("username");
        ArrayList<Order> cartList=od.findOrderByCondition(userName, "inCart");
        request.setAttribute("cartList", cartList);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId=request.getParameter("orderId");
        String productId=request.getParameter("productId");
        String command=request.getParameter("command");
        switch(command){
            case "remove":
                od.removeOrderItem(orderId, productId);
                System.out.println(orderId+"  "+productId);
                response.sendRedirect("cart");
                break;
            case "order":
                Date date=Date.valueOf(request.getParameter("date"));
                od.updateOrder(orderId, date,"ordered");
                response.sendRedirect("cart");
                break;
        }
    }

}
