/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.AddToCartBean;
import beans.HistoryBean;
import beans.ProductBean;
import beans.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author disturbedv1
 */
public class historyServlet extends HttpServlet {
    @EJB
    private AddToCartBean addToCartBean;
    @EJB
    private ProductBean productBean;
    HistoryBean historyBean = lookupHistoryBeanBean();
    //ProductBean productBean = lookupProductBean();
    UserBean userBean;
    AddToCartBean addToCart;
    int status;
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        userBean = (UserBean)request.getSession().getAttribute("userBean");
        
        if(request.getParameter("historyButton") != null){
            try{
                historyBean.readHistory(userBean.getID());
               
                request.setAttribute("historyBean",historyBean);
                RequestDispatcher rd = request.getRequestDispatcher("history.jsp");
                rd.forward(request, response);
                //response.forward("firstPage.jsp?name="+username);
            }catch(Exception e){
                    request.setAttribute("message",e);
                    response.sendRedirect("error.jsp");
            }
            processRequest(request, response);
        }
        
        else if(request.getParameter("accountButton") != null){
            //request.getSession().getAttribute("userBean");
            
            RequestDispatcher rd = request.getRequestDispatcher("account.jsp");           
            rd.forward(request, response);
        }
        
        else if(request.getParameter("addToCartButton") != null){
            boolean itemBought=true;
            boolean itemNOTBought=true;
            int productQuantity = Integer.parseInt(request.getParameter("quantity"));
            int selectedProductID = Integer.parseInt(request.getParameter("selectedProductId"));
            
            try {
                addToCartBean.setQuantity(productQuantity);
                addToCartBean.setProductID(selectedProductID);
                addToCartBean.getOrderID(userBean.getID());
                status = addToCartBean.addItem();
                productBean.readProduct();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(historyServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(historyServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("productBean",productBean);
            if (status ==1){
                request.setAttribute("itemBought",itemBought);
            }
            else {
                request.setAttribute("itemNOTBought",itemNOTBought);
            }
            RequestDispatcher rd = request.getRequestDispatcher("productPage.jsp");
            rd.forward(request, response);    
        }
         
        else if(request.getParameter("productButton") != null){
            try {
                productBean.readProduct();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(historyServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(historyServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("historyBean",historyBean);
            request.setAttribute("productBean",productBean);
            RequestDispatcher rd = request.getRequestDispatcher("productPage.jsp");
            rd.forward(request, response);
        }
        
        //Account update button
        else if(request.getParameter("updatebutton") != null){
            if(!request.getParameter("address").isEmpty())
            {
                userBean.setAddress(request.getParameter("address"));
            }                
            if(!request.getParameter("email").isEmpty())
            {
                userBean.setEmail(request.getParameter("email"));
            }                
            try {
                userBean.UpdateInfo();
                System.out.println(userBean.getAddress());
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(historyServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
            rd.forward(request, response);
        }
        
        else if (request.getParameter("cartButton") != null){
            try{
                historyBean.ShowCart(userBean.getID());
                request.setAttribute("historyBean",historyBean);
                //System.out.println(historyBean.getHistoryList().get(0).getProductName());
                RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
                rd.forward(request, response);
            }catch(Exception e){
                    request.setAttribute("message",e);
                    response.sendRedirect("error.jsp");
            }
            processRequest(request, response);
        }
        
        //Logout button
        else if(request.getParameter("logoutButton") != null){
            request.logout();
              
            request.getRequestDispatcher("index.jsp").include(request, response);  
              
            HttpSession session=request.getSession();  
            session.invalidate();
        }
        
        //Cart Page buttons
        else if (request.getParameter("finalizeButton") != null){
            try{
                historyBean.FinalizeDeal(userBean.getID(), userBean.getBalance());
                request.setAttribute("historyBean",historyBean);
                userBean.UpdateBean(userBean.getID());
                addToCartBean.initLine();
                RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
                rd.forward(request, response);
            }
            catch(Exception e){
                request.setAttribute("message",e);
                response.sendRedirect("error.jsp");
            }
        }
        
        else if (request.getParameter("emptyButton") != null){
            try {
                historyBean.EmptyCart(userBean.getID());
                System.out.println("EMPTY CART REACHED");
                request.setAttribute("historyBean",historyBean);
                addToCartBean.initLine();
                RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
                rd.forward(request, response);
            }
            catch(Exception e){
                request.setAttribute("message",e);
                response.sendRedirect("error.jsp");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private HistoryBean lookupHistoryBeanBean() {
        try {
            Context c = new InitialContext();
            return (HistoryBean) c.lookup("java:global/JEEProject/HistoryBean!beans.HistoryBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    /*
        private ProductBean lookupProductBean() {
        try {
            Context c = new InitialContext();
            return (ProductBean) c.lookup("java:global/JEEProject/ProductBean!beans.ProductBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
*/
}
