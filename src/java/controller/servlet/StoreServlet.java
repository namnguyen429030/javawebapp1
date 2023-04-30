/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.servlet;

import controller.DAO.InStorageDAO;
import controller.DAO.OrderDAO;
import controller.DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import model.Order;
import model.Product;

/**
 *
 * @author Mosena
 */
public class StoreServlet extends HttpServlet {

    ProductDAO pd = new ProductDAO();
    OrderDAO od = new OrderDAO();
    InStorageDAO isd = new InStorageDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int count=pd.countProducts();
        int index=1;
        if(request.getParameter("currentPage")==null){
            request.setAttribute("currentPage", index);
        }
        else{
            index=Integer.parseInt(request.getParameter("currentPage"));
            request.setAttribute("currentPage", index);
        }
        ArrayList<Product> productList=pd.getProducts(index);
        HashMap<String, Integer> quantityMap = new HashMap<>();
        for (Product p : productList) {
            int quantity = isd.getQuantity(p.getProductID());
            quantityMap.put(p.getProductID(), quantity);
        }
        int noPage=(int) Math.ceil((double)count/10);
        request.setAttribute("noPage", noPage);
        request.setAttribute("end", noPage);
        request.setAttribute("quantityMap", quantityMap);
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("store.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        ArrayList<Product> orderList=new ArrayList<>();
        ArrayList<String>condition=new ArrayList<>();
        ArrayList<Integer>number=new ArrayList<>();
        int index=Integer.parseInt(request.getParameter("currentPage"));
        switch (command){
            case "cart":
                String productId=request.getParameter("productId");
                int num=Integer.parseInt(request.getParameter("number"));
                orderList.add(pd.findProduct(productId));
                condition.add("inCart");
                isd.sellProduct(productId, num);
                if(num>0){
                    number.add(num);
                    Order o=new Order(username, orderList, condition, number
                        , null);
                    od.addOrder(o);
                }
                response.sendRedirect("store?currentPage="+index);
                break;
            case "paging":                
                response.sendRedirect("store?currentPage="+index);
                break;
            case "preOrder":
                
                break;
        }
    }

}
