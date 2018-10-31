<%-- 
    Document   : ChangeStatus
    Created on : 10 Mar, 2014, 6:25:18 PM
    Author     : eiosys
--%>
<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@ page import="core.db.dbconn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form class="form-signin" action="editfile" enctype="multipart/form-data"></form>
<%    String status1 = "Inactive";
    String status2 = "active";

    String uid = request.getParameter("uid");
    request.setAttribute("uid", uid);
    session.setAttribute("uid", uid);

    Statement connect = dbconn.connect();
    /*    
     String sql=  "update users set status='" + status +"' WHERE uid ='" +uid+ "'";
     System.out.println("sql=" + sql);
    
     int rs = connect.executeUpdate(sql);
     connect.executeUpdate(sql);
     if (rs >1) {
     response.sendRedirect("manage_files.jsp?msg2=<h5><font color='red'>The Username already exists</h5></font>");
     }
     */
    String sql2 = "delete from logdetails WHERE userid ='" + uid + "'";
    System.out.println("sql update = " + sql2);
    Statement st2 = dbconn.connect();
    int rs2 = st2.executeUpdate(sql2);

    String sql1 = "select status from  users WHERE uid ='" + uid + "'";
    System.out.println("sql1 = " + sql1);
    ResultSet rs1 = connect.executeQuery(sql1);
    while (rs1.next()) {
        String ans = rs1.getString("status");
        System.out.println("ans = " + ans);
        if (ans.equals("active")) {
            String sql = "update users set status='" + status1 + "' WHERE uid ='" + uid + "'";
            System.out.println("sql update = " + sql);
            Statement st = dbconn.connect();
//            int rs = st.executeUpdate(sql);
            //connect.executeUpdate(sql);
//            if (rs > 0) {
//                response.sendRedirect("manage_users.jsp?msg2=<h5><font color='red'>The Username already exists</h5></font>");
                response.sendRedirect("manage_users.jsp");
//            }

        } else if (ans.equals("Inactive")) {
            String sql = "update users set status='" + status2 + "' WHERE uid ='" + uid + "'";
            System.out.println("sql update = " + sql);
            Statement st = dbconn.connect();
//            int rs = st.executeUpdate(sql);
            //connect.executeUpdate(sql);
//            if (rs > 0) {
//                response.sendRedirect("manage_users.jsp?msg2=<h5><font color='red'>The Username already exists</h5></font>");
                response.sendRedirect("manage_users.jsp");
//            }
        }
        
         
    }


%>

</form>
