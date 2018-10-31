
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

public class EditUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
      boolean errored = false;
        String uname = null, pwd = null, loc = null, phno = null;
        int age = 0; //out.print("12<br>"); 
         String key=request.getParameter("data[User][key]");out.print("asdfffffff");
       int uid=Integer.parseInt(session.getAttribute("uid").toString());
       
        uname = request.getParameter("data[User][username]");
        pwd = request.getParameter("data[User][pwd]");// out.print("31<br>"); 
        age = Integer.parseInt(request.getParameter("data[User][age]"));
        loc = request.getParameter("data[User][location]"); //out.print("14<br>"); 
        phno = request.getParameter("data[User][phone_number]").trim();
         try {
            Statement connect = dbconn.connect(); //out.print("15<br>"); 
             ResultSet rs=connect.executeQuery("select * from users where uid!="+uid+" and uname='"+uname+"' and location='"+loc+"'");
             if(rs.next())
             {
                 // out.print("17<br>");
                 session.setAttribute("error","true");
                  response.sendRedirect("editusers.jsp?id="+uid);
             }
                 
             else{//saving account
                 //out.print("19<br>"); 
                 ResultSet rs1=connect.executeQuery("select * from users where userkey='"+key+"'");
             if(!rs1.next())
             { //out.print("191<br>"); 
               errored=true;
             }
             else
             {
                 UUID uniqueKey1 = UUID.randomUUID();
         String key2=uniqueKey1.toString();
         key=key2.substring(0, 10);
             }
           String sql = "update users set uname='"+uname+"',pwd=DES_ENCRYPT('"+pwd+"'),age="+age+",userkey='"+key+"',location='"+loc+"',phno='"+phno+"',status='active' where uid="+uid;
            // out.print("dbbberror3");
            int rows = connect.executeUpdate(sql);
            if (!(rows > 0)) {
                errored=true; //out.print("18<br>"); 
            }if (errored) {
                session.setAttribute("flash_message", "Failed to save. Try again");
                session.setAttribute("flash_type", "error");
                response.sendRedirect("manage_users.jsp");
            }
            session.setAttribute("edit","true");
            response.sendRedirect("manage_users.jsp");
             }
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
