<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<div class="container">
    <div class="row background space20">

        <%

          
            int i = 1;
            String user = (String) session.getAttribute("user");
            if (user != null && user.equals("admin")) {

               %>
            
        <%@include file="search1.jsp"%>
        <table>

        </table>
            <%} else {
            %>
            <h3 class="form-signin-heading">Please <a href="login.jsp">Login</a> Again </h3>
            <%}%>

        </table>



    </div>

    <!-- Example row of columns -->

</div>
<%@include file="footer.jsp" %>