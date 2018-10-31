/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consumer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import java.util.Random;

/**
 *
 * @author 
 */
public class Download extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final int BUFFER_SIZE = 4096;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String fid = request.getParameter("fid");
        String orgid = request.getParameter("orgid");
        orgid = (orgid == null) ? fid : orgid;
        System.out.println("orgid " + orgid);
        System.out.println("fid " + fid);

        try {
            Statement conn = core.db.dbconn.connect();

            ArrayList<String> fids = new ArrayList<String>();
            if (fid.equals("0")) {
                ResultSet rs3 = null;
                do {
                    fids.clear();
                    String sql = "select * from files where uid=1 and file_type=(select file_type from files where file_id=" + orgid + ")";
                    //String sql="select * from files where uid=1 and file_type=(select file_type from files where file_id=" + orgid+")";
                    //System.out.println("sql "+sql);
                    rs3 = conn.executeQuery(sql);
                    while (rs3.next()) {
                        fids.add(rs3.getString("file_id"));
                    }
                    System.out.println("size:" + fids.size());

                    //selecting all decoy ids
                    Random r = new Random();
                    int rand = r.nextInt(fids.size()) % fids.size();
                    fid = "" + fids.get(rand);
                } while (fid.equals("0"));
            }
            boolean errored = false;

//            response.setContentType("text/html;charset=UTF-8");
            // PrintWriter out = response.getWriter();
            //  Connection conn=core.db.dbconn.getConn();
            // files f1 is for valid = orgid, files f is for decoy = fid
            String sql = "SELECT f1.file_name,f.file,f.file_type FROM files f,files f1 WHERE f.file_id ='" + fid + "' and f1.file_id='" + orgid + "'";
            System.out.println(sql);
            //   Statement conn=core.db.dbconn.connect();

            ResultSet result = conn.executeQuery(sql);
            if (result.next()) {
                // gets file name and file blob data
                String fileName = result.getString("file_name");
                String file_type = result.getString("file_type");
                Blob blob = result.getBlob("file");
                InputStream inputStream = blob.getBinaryStream();
                int fileLength = inputStream.available();

                System.out.println("fileLength = " + fileLength);

                ServletContext context = getServletContext();

                // sets MIME type for the file download
                fileName += file_type;
                String mimeType = context.getMimeType(fileName);
                if (mimeType == null) {
                    mimeType = getContentType(fileName);
                }
                // set content properties and header attributes for the response
                System.out.println("mimeType " + mimeType);
                System.out.println("fileName " + fileName);
                response.setContentType(mimeType);
                response.setContentLength(fileLength);
                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=" + fileName;
                //String headerValue = "attachment; filename=123.txt";
                System.out.println("headerValue: " + headerValue);
                response.setHeader(headerKey, headerValue);

                // writes the file to the client
                OutputStream outStream = response.getOutputStream();

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                //    Blob bl = resultset.getBlob("file");
                buffer = blob.getBytes(1, (int) blob.length());
                /* while ((bytesRead = inputStream.read(buffer)) != -1) {
                 outStream.write(buffer, 0, bytesRead);
                 }
                 */

                outStream.write(buffer);
                outStream.flush();
                inputStream.close();
                outStream.close();

                session.setAttribute("download", "true");

            } else {
                // no file found
                response.getWriter().print("File not found for the id: " + fid);
            }
            if (errored) {
                session.setAttribute("flash_message", "Failed to save. Try again");
                session.setAttribute("flash_type", "error");
                response.sendRedirect("filedisplay.jsp");

            }

//          response.sendRedirect("view_files.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            //  out.println(e.getMessage()+"error"+e);

        } finally {
            //  out.close();
        }
    }

    String getContentType(String fileName) {
        String extension[] = { // File Extensions
            "txt", //0 - plain text
            "htm", //1 - hypertext
            "jpg", //2 - JPEG image
            "png", //2 - JPEG image
            "gif", //3 - gif image
            "pdf", //4 - adobe pdf
            "doc", //5 - Microsoft Word
            "docx",}; // you can add more
        String mimeType[] = { // mime types
            "text/plain", //0 - plain text
            "text/html", //1 - hypertext
            "image/jpg", //2 - image
            "image/jpg", //2 - image
            "image/gif", //3 - image
            "application/pdf", //4 - Adobe pdf
            "application/msword", //5 - Microsoft Word
            "application/msword", //5 - Microsoft Word
        }, // you can add more
                contentType = "text/html";    // default type
        // dot + file extension
        int dotPosition = fileName.lastIndexOf('.');
        // get file extension
        String fileExtension
                = fileName.substring(dotPosition + 1);
        // match mime type to extension
        for (int index = 0; index < mimeType.length; index++) {
            if (fileExtension.equalsIgnoreCase(extension[index])) {
                contentType = mimeType[index];
                break;
            }
        }
        return contentType;
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
