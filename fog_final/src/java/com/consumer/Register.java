package com.consumer;

import core.db.dbconn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        boolean errored = false;
        ArrayList eid=new ArrayList();
        String uname = null, pwd = null, loc = null, phno = null, email = null, qst = null, answer = null,q2=null,ans2=null;
        int age = 0; 
        UUID uniqueKey = UUID.randomUUID();//generates random key
        String key = uniqueKey.toString();
        key = key.substring(0, 16);
        uname = request.getParameter("data[User][username]");
        pwd = request.getParameter("data[User][password]");// out.print("31<br>"); 
        age = Integer.parseInt(request.getParameter("data[User][age]"));
        loc = request.getParameter("data[User][location]"); //out.print("14<br>"); 
        phno = request.getParameter("data[User][phone_number]").trim();
        email = request.getParameter("data[User][email]");
        qst = request.getParameter("data[User][question]");
        answer = request.getParameter("data[User][answer]");
           q2 = request.getParameter("q2");
        ans2 = request.getParameter("a2");
        String ip = request.getRemoteAddr();
        try {
            Statement connect = dbconn.connect(); //out.print("15<br>"); 
            String sql = "select * from users where uname='" + uname + "'";
            System.out.println("sql=" + sql);
            
            ResultSet rs = connect.executeQuery(sql);
            System.out.println("");
            if (rs.next()) {
            
                response.sendRedirect("register.jsp?msg2=<h5><font color='red'>The Username already exists</h5></font>");
            } else {
                ResultSet rs1 = connect.executeQuery("select * from users where userkey='" + key + "'");
                if (rs1.next()) { 
                    UUID uniqueKey1 = UUID.randomUUID();
                    String key2 = uniqueKey1.toString();
                    System.out.println("key2 " + key2);
                    key = key2.substring(0, 16);
                    System.out.println("key substring " + key);
                }
                
                String userInsertQuery = "INSERT INTO users(uname,pwd,age,userkey,location,phno,status,email,question,answer,ipaddress,security_question2,answer2) values('" + uname + "',DES_ENCRYPT('" + pwd + "')," + age + ",'" + key + "','" + loc + "','" + phno + "','active','" + email + "',DES_ENCRYPT('" + qst + "'),DES_ENCRYPT('" + answer + "'),'"+ip+"',DES_ENCRYPT('" + q2 + "'),DES_ENCRYPT('" + ans2 + "'))";
                System.out.println("sql:" + userInsertQuery);

                int rows = connect.executeUpdate(userInsertQuery);
                if (!(rows > 0)) {
                    errored = true;
                    out.print("18<br>");
                }
                if (errored) {
                    session.setAttribute("flash_message", "Failed to save. Try again");
                    session.setAttribute("flash_type", "error");
                    response.sendRedirect("register.jsp");
                } else {
                    eid.add(email);
                    session.setAttribute("ustatus", "registered");
                    session.setAttribute("uname", uname);
                    session.setAttribute("flash_message", "Registration successful !!!");
                    session.setAttribute("flash_type", "success");
                   
                    SendMail.sendMail("cloudprotection8@gmail.com",eid, "Secrete key","you are registered sucessfully ,your key is:"+key);
                
                    response.sendRedirect("login.jsp");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("error" + e);


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
