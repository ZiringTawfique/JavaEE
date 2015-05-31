/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.HistoryBean;
import beans.UserBean;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author disturbedv1
 */
public class historyServlet extends HttpServlet {
    HistoryBean historyBean = lookupHistoryBeanBean();
    UserBean userBean;
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
        
        else if(request.getParameter("updatebutton") != null){
            userBean.setAddress(request.getParameter("address"));
            userBean.setEmail(request.getParameter("email"));
            try {
                userBean.UpdateInfo();
                System.out.println(userBean.getAddress());
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(historyServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
            rd.forward(request, response);
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

}
