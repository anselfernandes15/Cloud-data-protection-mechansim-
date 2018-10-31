<ul class="pull-right nav">
    <%
        String user = (String) session.getAttribute("user1");
       String userType = (String) session.getAttribute("UserType");
        if (user == null) {
    %>

    <li><a href="register.jsp">Register</a></li>
    
    <li><a href="login.jsp" >Login</a></li>
    
    <% } else {%>
    <li><a href="logout">Logout</a></li>
    <% }%>
</ul>