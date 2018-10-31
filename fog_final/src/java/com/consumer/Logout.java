/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consumer;

import core.db.dbconn;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 
 */
public class Logout extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception
    {
       
     // HttpServletRequest request1 = webAppAccess.getHttpServletRequest();
HttpSession session = request.getSession(false);
if (session != null)
{
  //   ServletContext cx = request.getSession().getServletContext();

/*
                            {
                               String st = (String) session.getAttribute("un");
                               System.out.println("old:"+st.toString());
                              //  String st=attribute.toString();
                                String un=(String) session.getAttribute("user");
                         st= st.replace(un, "");
                           //cx.setAttribute("un", st);
                           System.out.println("new"+st);
        }
 * /
 */
  //  Statement connect = dbconn.connect();
   // Timestamp d1 = new Timestamp(Calendar.getInstance().getTimeInMillis());
  //  java.util.Date d1=new java.util.Date();
      // String st=(String) session.getAttribute("userid");
     //  System.out.println(st);
     //  int uid= (Integer) session.getAttribute("userid");
      // System.out.println(uid);
      // String sql="update login set status='logout' , logouttime='"+d1+"' where userid=" + uid +" and status='login'" ;
       //System.out.println(sql);
     // int roww = connect.executeUpdate(sql);
    session.invalidate();
  
   
        }
    response.sendRedirect("index.jsp");    
        
        
        
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
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
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
