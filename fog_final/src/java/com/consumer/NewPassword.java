/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consumer;

import core.db.dbconn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eiosys
 */
public class NewPassword extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            HttpSession session = request.getSession(true);
           String uname=(String) session.getAttribute("uname");
           System.out.println("u_name"+uname);
            String pwd = request.getParameter("pwd");
            System.out.println("pwd:" + pwd);
            String repwd = request.getParameter("repwd");
            System.out.println("repwd:" + repwd);
            if(pwd.equals(repwd)){
                System.out.println("inside if");
                 Statement st= dbconn.connect();
                int rs = st.executeUpdate("UPDATE users SET `pwd` =DES_ENCRYPT('"+pwd+"') WHERE uname='"+uname+"'");
                System.out.println("rs:"+rs);
                if(rs!=0){
                    System.out.println("inside second if");
                    System.out.println("in if");
                    
                session.setAttribute("flash_message", "Sucessful");
//                session.setAttribute("flash_type", "sucess");
                response.sendRedirect("login.jsp");
            
                }else{
                    session.setAttribute("flash_message", "Not saved sucessfully");
                session.setAttribute("flash_type", "Failure");
    response.sendRedirect("newPassword.jsp");
                }
                
                
            } else{
                session.setAttribute("flash_message", "password missmatch");
                session.setAttribute("flash_type", "Failure");
    response.sendRedirect("newPassword.jsp");
    }
}
catch(Exception e){
    e.printStackTrace();

}
finally {            
            out.close();
        }
}



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, ex);
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

}