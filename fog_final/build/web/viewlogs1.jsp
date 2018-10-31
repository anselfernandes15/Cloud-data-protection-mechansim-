<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<div class="container">
    <div class="row background space20">


                <%String user=(String)session.getAttribute("user");
if(user!=null&&user.equals("admin"))
       {
               String d1=request.getParameter("d1").toString();
               String d2=request.getParameter("d2").toString();
               System.out.println("55555d1="+d1);
System.out.println("d2="+d2);
                if(d1!=null && d2!=null)
                      response.sendRedirect("showlog.jsp?d1="+d1+"&d2="+d2);
               else
                   response.sendRedirect("showlog.jsp");
                }
else
{
 %>

  <%}%>


    </div>

    <!-- Example row of columns -->

</div>
<%@include file="footer.jsp" %><%--
    Document   : viewlogs1
    Created on : 15 Jun, 2015, 8:46:17 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
