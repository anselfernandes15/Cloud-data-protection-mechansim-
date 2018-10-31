<%@page import="core.db.dbconn"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@include file="header.jsp" %>
<%@include file="navigation.jsp" %>
<%@include file="flash.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <form action="Reset_password" method="post">
        <div class="container">
            <div class="row background space20">
        <div class="span6" >
        <h3 class="form-signin-heading">Please enter your username..</h3>
        <input type="text" name="uname">
       <input type="submit" name="submit" value="submit" class="btn btn-large btn-primary">
     
        </div>
              
            </div>
            
             </div>
        </form>
    </body>
</html>
<%@include file="footer.jsp" %>