/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consumer;

import core.db.dbconn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;
import java.util.UUID;
/**
 *
 * @author 
 */
public class DeleteFile extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        boolean errored = false;
       String status=null,sql="";
       int id=Integer.parseInt(request.getParameter("fid").toString());
              // out.print("14<br>"+id); 
     
      try {
         
          Statement connect = dbconn.connect();// out.print("15<br>"); 
            sql  = "delete from files where file_id="+id;
           
               int rows = connect.executeUpdate(sql);
            if (!(rows > 0)) {
                errored=true; //out.print("18<br>"); 
            }
          
            if (errored) {
                session.setAttribute("flash_message", "Failed to save. Try again");
                session.setAttribute("flash_type", "error");
                response.sendRedirect("manage_files.jsp");
            }
           response.sendRedirect("manage_files.jsp");
          } catch (Exception e) {
            e.printStackTrace();
            out.println("error"+e);
            

        } finally {
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
        processRequest(request, response);
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
        processRequest(request, response);
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
