<%@include file="header.jsp"%>
<%@include file="navigation_user.jsp" %>
<%@include file="flash.jsp" %>
<!-- Carousel
================================================== 

 String ustatus="false";
session.setAttribute("ustatus","false");
        %>
-->
<div id="myCarousel" class="carousel slide" data-interval="3000">
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

 
</div>
<div class="container">
    <div class="span6">
        <form class="form-signin" action="access" method="post">

            <% //String msg=(String)request.getAttribute("error");
                String user = (String) session.getAttribute("user");
                if (user != null && !user.equals("admin")) {
                    String msg = request.getParameter("keymsg");
                    String download = (String) session.getAttribute("download");
                    if (download != null && download.equals("true")) {
                        response.sendRedirect("view_files.jsp");
                    }
                    if (msg == null) {
                        session.setAttribute("kcount", 0);
                    }
                    session.setAttribute("fid", request.getParameter("id"));
//  session.setAttribute("filetype",request.getParameter("type"));}
%>
            <h2 class="form-signin-heading">Please Enter Authentication Key </h2>
            <input type="password" class="input-block-level validate[required]" placeholder="Your Key" name="key">
 <select name="q1">
                    <option value="-1">-Enter security question set1-</option>
                    <option value="What Is Your Favourite Fruit">What is your favourite fruit?</option>
                    <option value="What Is Your First Teacher Name">What is your first teacher's name?</option>
                    <option value="What Is Your Pet Name">What is your pet's name?</option>
                    <option value="What Is Your Favourite Color">What is your mother's maiden name?</option>
                </select>

                <input type="password" class="input-block-level validate[required,min[10]]" placeholder="Answer " name="a1">
  <select name="q2">
                    <option value="-1">-Enter security question set2-</option>
                    <option value="What is your mothers middle name?">What is your mothers middle name?</option>
                    <option value="What is the last 3 digit of your mobile no ?">What is the last 3 digit of your mobile no ?</option>
                    <option value="What is the name of your birth place ?">What is the name of your birth place ?</option>
                    <option value="Which vegetable you do not like at all ?">Which vegetable you do not like at all ?</option>
                </select>

                <input type="password" class="input-block-level validate[required,min[10]]" placeholder="Answer " name="a2">

            <%
                if (msg != null) {
                    int c = (Integer) session.getAttribute("kcount");
                    c++;
                    session.setAttribute("kcount", c);
            %><h3><font color="red"><%=msg%></font></h3><%
                }

            } else {
            %> 
            <h3 class="form-signin-heading">Please <a href="login.jsp">Login</a> Again </h3>
            <%}%>
            <button class="btn btn-large btn-primary" type="submit">Submit</button>
        </form>
    </div>

</div>
<!-- /END THE FEATURETTES -->
<%@include file="footer.jsp" %>