/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consumer;

import core.db.dbconn;
import core.db.Hmac;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author 
 */
public class EditFile extends HttpServlet {

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
        boolean errored = false;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
           HttpSession session = request.getSession(true);
        try {
             PreparedStatement pre =null;    
            String fid=(String)session.getAttribute("fid");
             Class.forName("com.mysql.jdbc.Driver").newInstance();
          //  out.print("1"+uid+"XXXXXXX"+is+session.getAttributeNames());
            Connection con = null;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "");
           
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu  = new ServletFileUpload(factory);

            if (! ServletFileUpload.isMultipartContent(request)) {
                
              pre=con.prepareStatement("update files set file_name=? where file_id=?");
           pre.setString(1,request.getParameter("filename"));
             pre.setString(2, fid);   
            }
// parse request
            else{
            List items = sfu.parseRequest(request);
            FileItem title = (FileItem) items.get(0);
            String   filetitle =  title.getString();
             FileItem type = (FileItem) items.get(2);
            String   filetype =  type.getString();
            filetitle.concat(filetype);
     FileItem    file= (FileItem)items.get(1);
        String hmac=core.db.Hmac.hmac(filetitle+file.getSize());
           
             pre = con.prepareStatement("update files set file_name=?,file=?,file_size=?,hmac=?) where file_id=?");
            //out.print("21");
           pre.setBinaryStream(2, file.getInputStream(),(int)file.getSize());
   pre.setString(1, filetitle);
            pre.setInt(3, (int)file.getSize());
           pre.setString(4, hmac);
         pre.setString(5, fid);
             } int rows = pre.executeUpdate();            
         
            if (!(rows > 0)) {
                errored=true; //out.print("18<br>"); 
            }if (errored) {
                session.setAttribute("flash_message", "Failed to save. Try again");
                session.setAttribute("flash_type", "error");
                response.sendRedirect("editfile.jsp");
            }
             
            session.setAttribute("edit","true");
            
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
