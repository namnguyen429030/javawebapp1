/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.servlet;

import controller.DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Product;


/**
 *
 * @author Mosena
 */
public class ProductServlet extends HttpServlet {
    ProductDAO pd=new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int count=pd.countProducts();
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
        if(request.getParameter("productId")!=null){
            request.setAttribute("productId", 
                    request.getParameter("productId"));
        }
        ArrayList<Product> list=pd.getProducts(index);
        request.setAttribute("noPage", noPage);
        request.setAttribute("end", noPage);
        request.setAttribute("list", list);
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId=request.getParameter("productId");
        String command=request.getParameter("command");
        ArrayList<Product>list=new ArrayList<>();
        int index=Integer.parseInt(request.getParameter("index"));
        switch(command){
            case "paging":
                response.sendRedirect("products?currentPage="+index);
                break;
            case "add":
                String productName=request.getParameter("productName");
                int price=Integer.parseInt(request
                        .getParameter("price"));
                int expireTime=Integer.parseInt(request
                        .getParameter("expireTime"));
                if(pd.validateProductId(productId)){
                    String message="Invalid product's ID";
                    response.sendRedirect("products?currentPage="+index
                            +"&message="+message);
                }
                else{
                    pd.addProduct(productId, productName, price, 
                            expireTime);
                    response.sendRedirect("products?currentPage="+index);
                }
                break;
            case "delete":
                pd.deleteProduct(productId);
                response.sendRedirect("products?currentPage="+index);
                break;
            case "edit":
                response.sendRedirect("products?data=edit&productId="+productId+
                        "&currentPage="+index);
                break;
            case "done":
                productName=request.getParameter("productName");
                price=Integer.parseInt(request
                        .getParameter("price"));
                expireTime=Integer.parseInt(request
                        .getParameter("expireTime"));
                pd.updateProduct(productId, productName, 
                        price, expireTime);
                response.sendRedirect("products?currentPage="+index);
                break;
            case "sort":
                String sortBy=request.getParameter("sortBy");
                String order=request.getParameter("order");
                switch(sortBy){
                    case "productId":
                        switch(order){
                            case "asc":
                                list=pd.getProducts(index);
                                break;
                            case "desc":
                                list=pd.getProductOrderByDesc(index);
                                break;
                        }
                        break;
                    case "productName":
                        switch(order){
                            case "asc":
                                list=pd.getProductOrderByProductNameAsc(index);
                                break;
                            case "desc":
                                list=pd.getProductOrderByProductNameDesc(index);
                                break;
                        }
                        break;
                    case "price":
                        switch(order){
                            case "asc":
                                list=pd.getProductOrderByPriceAsc(index);
                                break;
                            case "desc":
                                list=pd.getProductOrderByPriceDesc(index);
                                break;
                        }
                        break;
                    case "expireTime":
                        switch(order){
                            case "asc":
                                list=pd.getProductOrderByExpireTimeAsc(index);
                                break;
                            case "desc":
                                list=pd.getProductOrderByExpireTimeDesc(index);
                                break;
                        }
                        break;
                }
                request.setAttribute("data", "show");
                int count=pd.countProducts();
                int noPage=(int) Math.ceil((double)count/10);
                request.setAttribute("end", noPage);
                request.setAttribute("list", list);
                request.setAttribute("currentPage", index);
                request.setAttribute("noPage", noPage);
                request.getRequestDispatcher("products.jsp").forward(request, response);
                break;
        }
    }
}
