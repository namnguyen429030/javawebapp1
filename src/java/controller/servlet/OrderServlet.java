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
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import model.Order;
import model.Product;


/**
 *
 * @author Mosena
 */
public class OrderServlet extends HttpServlet {
    OrderDAO od=new OrderDAO();
    ProductDAO pd=new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Product> pdList= pd.getProducts();
        int count=od.countOrders();
        int index=1;
        int noPage=(int) Math.ceil((double)count/10);
        if(request.getParameter("currentPage")==null){
            request.setAttribute("currentPage", index);
        }
        else{
            index=Integer.parseInt(request.getParameter("currentPage"));
            request.setAttribute("currentPage",index);
        }
        if(request.getParameter("data")==null){
            request.setAttribute("data", "show");
        }
        else{
            request.setAttribute("data", 
                    request.getParameter("data"));
        }
        if(request.getParameter("productId")!=null 
                && request.getParameter("orderId")!=null){
            request.setAttribute("productId", 
                    request.getParameter("productId"));
            request.setAttribute("orderId", 
                    request.getParameter("orderId"));
        }
        ArrayList<Order> list=od.getOrders(index);
        request.setAttribute("noPage", noPage);
        request.setAttribute("end", noPage);
        request.setAttribute("list", list);
        request.setAttribute("pdList", pdList);
        request.getRequestDispatcher("orders.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId=request.getParameter("orderId");
        String productId=request.getParameter("productId");
        String command=request.getParameter("command");
        int index=Integer.parseInt(request.getParameter("index"));
        switch(command){
            case "add":
                String[] xProducts=request.getParameterValues("product");
                String[] xQuantities=request.getParameterValues("quantity");
                String username=request.getParameter("username");
                Date date=Date.valueOf(request.getParameter("date"));
                ArrayList<String> productIdList=(ArrayList<String>) Arrays
                        .stream(xProducts)
                        .collect(Collectors.toList());
                ArrayList<Product> orderList=new ArrayList<>();
                for(String s:productIdList){
                    Product p=pd.findProduct(s);
                    orderList.add(p);
                }
                ArrayList<Integer> numList=(ArrayList<Integer>) Arrays
                        .stream(xQuantities)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                ArrayList<Date> dateList=new ArrayList<>();
                ArrayList<String> conditions=new ArrayList<>();
                for (Product orderList1 : orderList) {
                    conditions.add("ordered");
                    dateList.add(date);
                }
                Order o=new Order(username, orderList, 
                        conditions, numList,dateList);
                od.addOrder(o);
                response.sendRedirect("orders?currentPage="+index);
                break;

            case "delete":
                od.removeOrderItem(orderId, productId);
                response.sendRedirect("orders?currentPage="+index);
                break;
            case "edit":
                response.sendRedirect("orders?data=edit&orderId="+orderId+
                        "&productId="+productId+
                        "&currentPage="+index);
                break;
            case "done":
                String condition=request.getParameter("condition");
                int number=Integer.parseInt(
                        request.getParameter("number"));
                if(!"".equals(request.getParameter("date"))){
                    date=Date.valueOf(request.getParameter("date"));
                }
                else{
                    date=null;
                }
                od.updateOrder(orderId, productId, number, condition,date);
                response.sendRedirect("orders?currentPage="+index);
                break;
            case "paging":
                response.sendRedirect("orders?currentPage="+index);
                break;
        }
    }

}
