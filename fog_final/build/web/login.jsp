<%@include file="header.jsp" %>
<%@include file="navigation.jsp" %>
<%@include file="flash.jsp" %>

<div class="container">
    <div class="row background space20">
        <%
            String flag = (String) session.getAttribute("ustatus");
            String msg = request.getParameter("msg");
            if (msg == null) {
                session.setAttribute("lcount", 0);
            }
            //out.print("sdfsdfds"+flag);
            if (flag != null && flag.equals("registered")) {%>
        <h3 align="center"> Sign in to continue to your profile</h3>
            <%}%>
        <div class="span6">
            <form class="form-signin" action="OTPVerify.jsp" method="post">
                <h2 class="form-signin-heading">Please Login</h2>
                <input type="text" class="input-block-level validate[required]" placeholder="Username" name="data[User][username]">
                <input type="password" class="input-block-level validate[required]" placeholder="Password" name="data[User][password]">
                <%
                    if (msg != null) {
                        int c = (Integer) session.getAttribute("lcount");
                        c++;
                        session.setAttribute("lcount", c);

                %><%=msg%><%
      }
      String msg1 = request.getParameter("msg1");
      if (msg1 != null) {%><%=msg1%><%
           }%>
                <button class="btn btn-large btn-primary" type="submit">Login</button>
                <a href="Reset_password.jsp" class="form-signin-heading">Forgot / Reset password</a>
            </form>
        </div>
        <div class="span5">
            <img src="img/login.jpg" alt="">
            <br><br><br><br><br><br>
        </div>
    </div>

    <!-- Example row of columns -->

</div>
<br>
<%@include file="footer.jsp" %>