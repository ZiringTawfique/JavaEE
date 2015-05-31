/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
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

public class loginServlet extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        boolean error = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);
        
        try{
            if(userBean.authenticate() == true){
                //System.out.println("HELLO");
                request.getSession().setAttribute("userBean",userBean);
                RequestDispatcher rd = request.getRequestDispatcher("productPage.jsp");
                rd.forward(request, response);
                //response.forward("firstPage.jsp?name="+username);
            }
            else{
                request.setAttribute("invalidLogin",true);
                //System.out.println("invalidLogin is set now ");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        }catch(Exception e){
                request.setAttribute("message",e);
                //response.sendRedirect("error.jsp");
        }  
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {      
        }
    }

}
