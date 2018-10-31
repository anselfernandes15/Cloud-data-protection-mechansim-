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
import java.util.ArrayList;
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

public class ManageUsers extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        boolean errored = false;
       String status="",sql="";
       ArrayList eid=new ArrayList();
       String emailid="";
       int id=Integer.parseInt(request.getParameter("id"));
               out.print("14<br>"+id); 
      try {
            Statement connect = dbconn.connect();// out.print("15<br>"); 
             ResultSet rs=connect.executeQuery("select * from users where uid="+id +" order by status");
             if(rs.next())
                 status=rs.getString("status");
              else   errored=true;
            if(status.equalsIgnoreCase("active"))
             sql  = "Update users set status='block' where uid="+id;
           
            else //if(status.equalsIgnoreCase("block"))
               sql  = "Update users set status='active' where uid="+id;
            
                int rows = connect.executeUpdate(sql);
                 ResultSet rs1=connect.executeQuery("select * from users where uid="+id +" order by status");
                if(rs1.next()){
                 status=rs1.getString("status");
                 emailid=rs1.getString("email");
                 eid.add(emailid);
            if(status.equalsIgnoreCase("block")){
            SendMail.sendMail("cloudprotection8@gmail.com",eid, "blocked account","your account has been block please change the password to active it");}

            else{ //if(status.equalsIgnoreCase("block"))
              SendMail.sendMail("cloudprotection8@gmail.com",eid, "activated account","your account has been activated");}}
            if (!(rows > 0)) {
                errored=true; //out.print("18<br>"); 
            }
                //these lines are commented to delete records from log
       //   String sql2 = "delete from logdetails WHERE userid ='" + id + "'";
 //   System.out.println("sql update = " + sql2);
  //  Statement st2 = dbconn.connect();
   // int rs2 = st2.executeUpdate(sql2);
    // if (!(rs2 > 0)) {
    //            errored=true; //out.print("18<br>");
       //     }
            if (errored) {
                session.setAttribute("flash_message", "Failed to save. Try again");
                session.setAttribute("flash_type", "error");
                response.sendRedirect("manage_users.jsp");
            }
           
            session.setAttribute("managed",id);
            
           response.sendRedirect("manage_users.jsp");
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
