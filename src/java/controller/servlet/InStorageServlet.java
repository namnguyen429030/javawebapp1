/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.servlet;

import controller.DAO.InStorageDAO;
import controller.DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.InStorage;
import model.Product;

/**
 *
 * @author Mosena
 */
public class InStorageServlet extends HttpServlet {
    InStorageDAO isd=new InStorageDAO();
    ProductDAO pd=new ProductDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Product> pdList=pd.getProducts();
        int count=isd.countInStorage();
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
                && request.getParameter("mfgDate")!=null){
            request.setAttribute("productId", 
                    request.getParameter("productId"));
            request.setAttribute("mfgDate", 
                    request.getParameter("mfgDate"));
        }
        ArrayList<InStorage> list=isd.getInStorage(index);
        request.setAttribute("noPage", noPage);
        request.setAttribute("end", noPage);
        request.setAttribute("list", list);
        request.setAttribute("pdList", pdList);
        request.getRequestDispatcher("instorage.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId=request.getParameter("productId");
        Date mfgDate=null;
        if(request.getParameter("mfgDate")!=null){
            mfgDate=Date.valueOf(request.getParameter("mfgDate"));
        }
        String command=request.getParameter("command");
        ArrayList<InStorage>list=new ArrayList<>();
        int index=Integer.parseInt(request.getParameter("index"));
        switch(command){
            case "paging":
                response.sendRedirect("instorage?currentPage="+index);
                break;
            case "add":
                int number=Integer.parseInt(request.getParameter("number"));
                isd.addInStorage(productId, mfgDate, number);
                response.sendRedirect("instorage?currentPage="+index);
                break;
            case "delete":
                isd.deleteInStorage(productId,mfgDate);
                response.sendRedirect("instorage?currentPage="+index);
                break;
            case "edit":
                response.sendRedirect("instorage?data=edit&productId="+productId+
                        "&mfgDate="+mfgDate+
                        "&currentPage="+index);
                break;
            case "done":
                number=Integer.parseInt(request
                        .getParameter("number"));
                isd.updateInStorage(productId, mfgDate, number);
                response.sendRedirect("instorage?currentPage="+index);
                break;
            case "sort":
                String sortBy=request.getParameter("sortBy");
                String order=request.getParameter("order");
                switch(sortBy){
                    case "productId":
                        switch(order){
                            case "asc":
                                list=isd.getInStorage(index);
                                break;
                            case "desc":
                                list=isd.getInStorageOrderByDesc(index);
                                break;
                        }
                        break;
                    case "expDate":
                        switch(order){
                            case "asc":
                                list=isd.getInStorageOrderByExpDateAsc(index);
                                break;
                            case "desc":
                                list=isd.getInStorageOrderByExpDateDesc(index);
                                break;
                        }
                        break;
                    case "mfgDate":
                        switch(order){
                            case "asc":
                                list=isd.getInStorageOrderByMfgDateAsc(index);
                                break;
                            case "desc":
                                list=isd.getInStorageOrderByMfgDateDesc(index);
                                break;
                        }
                        break;
                    case "number":
                        switch(order){
                            case "asc":
                                list=isd.getInStorageOrderByNumberAsc(index);
                                break;
                            case "desc":
                                list=isd.getInStorageOrderByNumberDesc(index);
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
                request.getRequestDispatcher("instorage.jsp").forward(request, response);
                break;
        }
    }

}
