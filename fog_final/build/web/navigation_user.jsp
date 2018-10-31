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
                    <li><a href="index1.jsp">Home</a></li>
                     <li><a href="editpwd.jsp">Edit Password</a></li>
                   <li><a href="view_files.jsp">View</a></li>
            <li><a href="upload.jsp">Upload File</a></li>
            <%
            int user_id=(Integer)session.getAttribute("userid");
           session.setAttribute("profile",new core.db.UserBehaviourAlg().getBehave(user_id));
            
            %>
                  
                   <li><a href="keyrecovery.jsp">Key Recovery</a></li>
              </ul>
                <ul  class="pull-right nav">
                     
                    <li><a href="logout?msg3='Logout'">Logout</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!-- /.navbar-inner -->
    </div><!-- /.navbar -->

</div> <!-- /.container -->