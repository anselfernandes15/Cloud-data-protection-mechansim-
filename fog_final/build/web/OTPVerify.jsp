
<%@page import="com.consumer.SendSMS"%>
<%@page import="com.consumer.SessionIdentifierGenerator"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="core.db.dbconn"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Statement"%>
<%@page  import="com.consumer.SendSMS"%>
<%@page  import="com.consumer.SendMail"%>
<%@include file="header.jsp" %>
<%@include file="navigation.jsp" %>
<%@include file="flash.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    function validateof(){  
var otpv=document.otpf.otp.value; 
if (otpv==null || otpv==""){  
  alert("OTP can't be blank,enter otp");
   return false;  }
    }
</script>
<%
    String uname = null, pwd = null;
    String contact = "";
    String emailid="";
     ArrayList eid=new ArrayList();
    Statement connect = dbconn.connect();
    SessionIdentifierGenerator OT = new SessionIdentifierGenerator();
    String OTP = OT.nextSessionId().substring(0, 6);
    uname = request.getParameter("data[User][username]");
    pwd = request.getParameter("data[User][password]");
    String s = "select uid,phno,email from users where  uname= '" + uname + "'&& DES_DECRYPT(pwd)='" + pwd + "'";
    System.out.println("s:"+s);
    ResultSet result1 = connect.executeQuery(s);
    if (result1.next()) {
        System.out.println("Hi");
        contact = result1.getString("phno");
        emailid=result1.getString("email");
        eid.add(emailid);
         SendSMS.sendMSG(contact, OTP);
         System.out.println(OTP);
         SendMail.sendMail("cloudprotection8@gmail.com",eid,"OTP","your OTP is "+OTP);
         application.setAttribute("DOTP", OTP);
%>

<form name="otpf" class="form-signin" action="login" method="post" onsubmit="return validateof()">      
   <input type="hidden" name="data[User][username]" value="<%=uname%>">
    <input type="hidden" name="data[User][password]" value="<%=pwd%>">
    <input type="password" name="otp" placeholder="Enter OTP received on your mobile.">
    <button class="btn btn-large btn-primary" type="submit">Submit</button>

</form>
<%
}else{
        session.setAttribute("flash_message", "Unauthorised acess");
        session.setAttribute("flash_type", "fail");
        response.sendRedirect("login.jsp");

    }

%>