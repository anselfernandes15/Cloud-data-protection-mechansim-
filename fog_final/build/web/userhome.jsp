<%@include file="header.jsp"%>
<%@include file="navigation_user.jsp" %>
<%@include file="flash.jsp" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="core.db.dbconn"%>


<div class="container">    

<%
                    Object uname = session.getAttribute("user");
                    System.out.println("uname = " + uname);
                    if(session.getAttribute("key_count")==null) session.setAttribute("key_count", 0);
                    Statement st = dbconn.connect();
                    
                    String sql = "SELECT question,answer,userkey from users where uname='" + uname + "'";
                    

                    ResultSet rs = st.executeQuery(sql);
                    String question_table = null;
                    while (rs.next()) {
                        question_table = rs.getString(1);
                    }
                %>

                <h3 class="form-signin-heading">Welcome <%=uname%> !!</h3>
    <hr class="featurette-divider">

    <div class="featurette">
        
        <h2 class="featurette-heading">This is your space. <span class="muted">Explore it.</span></h2>
        <p class="lead">You can upload your files into our database. View your uploaded files.</p>
    </div>
<center><img class="featurette-image pull-left" src="img/pic2.jpg"></center>
    <hr class="featurette-divider">

    <div class="featurette">
        
        <h2 class="featurette-heading">It's secure. <span class="muted">Just remember your Security Ques's answer.</span></h2>
        <p class="lead">Remember you can only access your files with your own key. </p>
    </div>

    <hr class="featurette-divider">

    <div class="featurette">
        <img class="featurette-image pull-right" width="150px" height="150px" src="img/green-office.jpg">
        <h2 class="featurette-heading">And lastly, this one. <span class="muted">Go green!!.</span></h2>
        <p class="lead">We belive in a greener earth, so switch off your systems after using it.</p>
    </div>
</div>

<br><br>
<%@include file="footer.jsp" %>