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
               String id=request.getParameter("username").toString();
                if(id!=null&&!id.equals("Choose the User"))               
                      response.sendRedirect("logdetails.jsp?id="+id);
               else
                   response.sendRedirect("logdetails.jsp");
                }   
else
{
 %> 
 <h3 class="form-signin-heading">Please <a href="login.jsp">Login</a> Again </h3>
  <%}%>
  
  
    </div>

    <!-- Example row of columns -->
   
</div>
<%@include file="footer.jsp" %>