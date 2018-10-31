<%@include file="header.jsp"%>
<%@include file="navigation_user.jsp" %>
<%@include file="flash.jsp" %>
<script type="text/javascript">  
function validatepf(){  
var firstpassword=document.editp.data[User][password].value;  
var secondpassword=document.editp.data[User][repassword].value;  
 
if(firstpassword==secondpassword){  
return true;  
}  
else{  
alert("password must be same!");  
return false;  
}  
}  
</script>
<div id="myCarousel" class="carousel slide" data-interval="3000">
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>


</div>

<div class="container">
    <div class="span6">
        <form name="editp" class="form-signin" action="editpwd" method="post" onsubmit="return validatepf()">

            <%                    String user = (String) session.getAttribute("user");
                if (user != null && !user.equals("admin")) {
                    String msg = request.getParameter("emsg");
                    String msg1 = request.getParameter("emsg1");
                    if (msg1 == null) {
                        session.setAttribute("pcount", 0);
                    }

                        if (msg != null) {%><%=msg%><%} else {

            %>
            <h2 class="form-signin-heading">Please Enter New Password</h2>
            <input type="password" class="input-block-level validate[required]" placeholder="Enter New Password" name="data[User][password]">
            <input type="password" class="input-block-level validate[required]" placeholder="Reenter New Password" name="data[User][repassword]">
            <input type="password" class="input-block-level validate[required]" placeholder="Your Key" name="data[User][key]">

            <% String msg0 = request.getParameter("emsg0");
                    if (msg0 != null) {%><%=msg0%><%
                       }
                       if (msg1 != null) {
                           int c = (Integer) session.getAttribute("pcount");
                           c++;
                           session.setAttribute("pcount", c);

            %><%=msg1%><%
    } %>                    

            <button class="btn btn-large btn-primary" type="submit">Edit</button>


            <%}
            } else {
            %> 
            <h3 class="form-signin-heading">Please <a href="login.jsp">Login</a> Again </h3>
            <%}%>
        </form>
    </div>
</div>

<%@include file="footer.jsp" %>