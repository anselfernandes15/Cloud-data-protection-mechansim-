package com.consumer;

import core.db.dbconn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        boolean errored = false;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            int uid = Integer.parseInt(session.getAttribute("userid").toString());
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(factory);
            if (!ServletFileUpload.isMultipartContent(request)) {
                errored = true;
            }
            if (errored) {
                session.setAttribute("flash_message", "Failed to save. Try again");
                session.setAttribute("flash_type", "error");
                response.sendRedirect("upload.jsp");
            }
            String profile = (String) session.getAttribute("profile");
            System.out.println("Profile="+profile);
            if (profile != null && profile.equals("decoy") || new core.db.UserBehaviourAlg().getBehave(uid).equals("invalidate")) {
                System.out.println("in upload if");
                session.setAttribute("file", "uploaded");
                response.sendRedirect("upload.jsp");
            } else {
                System.out.println("in else part of upload.java**********");
                List items = sfu.parseRequest(request);
                FileItem title = (FileItem) items.get(0);
                String filetitle = title.getString();
                FileItem file = (FileItem) items.get(1);
                FileItem type = (FileItem) items.get(2);
                String filetype = title.getString();
                filetitle.concat(filetype);
                String hmac = core.db.Hmac.hmac(filetitle + file.getSize());
                Connection con = null;
                con = dbconn.getConn();
                String sql = "insert into files(file_name,file,file_size,hmac,uid,file_type) values(?,?,?,?,?,?)";
                System.out.println("sql file upload = " + sql);
                PreparedStatement pre = con.prepareStatement(sql);
                pre.setBinaryStream(2, file.getInputStream(), (int) file.getSize());
                pre.setString(1, filetitle);
                pre.setInt(3, (int) file.getSize());
                pre.setString(4, hmac);
                pre.setInt(5, uid);
                pre.setString(6, file.getName().substring(file.getName().lastIndexOf(".")));
                int rows = pre.executeUpdate();
                if (!(rows > 0)) {
                    errored = true;
                }
                if (errored) {
                    session.setAttribute("flash_message", "Failed to save. Try again");
                    session.setAttribute("flash_type", "error");
                    response.sendRedirect("upload.jsp");

                }
                session.setAttribute("file", "uploaded");
                response.sendRedirect("upload.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}