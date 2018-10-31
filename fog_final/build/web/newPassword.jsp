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
        <script type="text/javascript">  
function validatepf(){  
var firstpassword=document.newp.pwd.value;  
var secondpassword=document.newp.repwd.value;  
  
if(firstpassword==secondpassword){  
return true;  
}  
else{  
alert("password must be same!");  
return false;  
}  
}  
</script>
    </head>
    <body>
        <form name="newp" action="NewPassword" method="post" onsubmit="return validatepf()">
            <div class="container">
                <div class="row background space20">
                    <div class="span6" >
                        <h3 class="form-signin-heading">Thank u ,now set new password..</h3>
                      
                        <input type="password" name="pwd" class="input-block-level validate[required]" placeholder="New Password"><br>
                      
                        <input type="password" class="input-block-level validate[required]" placeholder="Re-enter Password" name="repwd">
                        </br>
                        <input type="submit" class="btn btn-large btn-primary" name="submit" value="submit">
                        <input type="reset" name="Reset" class="btn btn-large btn-primary" value="Reset">
                    </div>

                </div>

            </div>
        </form>
    </body>
</html>
<%@include file="footer.jsp" %>