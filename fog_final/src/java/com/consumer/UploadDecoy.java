package com.consumer;

import core.db.dbconn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadDecoy extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        boolean errored = false;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(factory);
            if (!ServletFileUpload.isMultipartContent(request)) {
                errored = true;

            }
            if (errored) {
                session.setAttribute("flash_message", "Failed to save. Try again.");
                session.setAttribute("flash_type", "error");
                response.sendRedirect("uploaddecoy.jsp");
            }

            List items = sfu.parseRequest(request);
            FileItem title = (FileItem) items.get(0);
            String filetitle = title.getString();
            FileItem type = (FileItem) items.get(2);
            String filetype = title.getString();
            filetitle.concat(filetype);
            
            FileItem file = (FileItem) items.get(1);
            String hmac = core.db.Hmac.hmac(filetitle + file.getSize());
            
            Connection con = null;
            con = dbconn.getConn();
            PreparedStatement pre = con.prepareStatement("insert into files(file_name,file,file_size,hmac,uid,file_type,status) values(?,?,?,?,1,?,'verified')");

            
            pre.setBinaryStream(2, file.getInputStream(), (int) file.getSize());
            pre.setString(1, filetitle);
            pre.setInt(3, (int) file.getSize());
            pre.setString(4, hmac);
            
            pre.setString(5, file.getName().substring(file.getName().lastIndexOf(".")));
            int rows = pre.executeUpdate();
            if (!(rows > 0)) {
                errored = true;
            }
            if (errored) {
                session.setAttribute("flash_message", "Failed to save. Try again");
                session.setAttribute("flash_type", "error");
                response.sendRedirect("uploaddecoy.jsp");

            }
            session.setAttribute("file", "uploaded");
            response.sendRedirect("uploaddecoy.jsp");
        } catch (Exception e) {

            out.println("error" + e);

        } finally {
            out.close();
        }
    }
 
}
