<!-- NAVBAR
    ================================================== -->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="index.jsp"></a>
            <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li><a href="index.jsp">Home</a></li>
                     <%@include file="manage_nav.jsp" %>
                    <!--<li><a href="about.jsp">About</a></li>
                    <li><a href="contact.jsp">Contact</a></li>-->
                </ul>
                <% //String ustatus="false";

String msg3=request.getParameter("msg3");
if(msg3!=null&&msg3.equalsIgnoreCase("logout"))
      { session.setAttribute("user1",null);
session.setAttribute("user",null);
}
        %>
               
            </div><!--/.nav-collapse -->
        </div><!-- /.navbar-inner -->
    </div><!-- /.navbar -->

</div> <!-- /.container -->