<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@ page import="core.db.dbconn" %>
<div class="container">
    <div class="row background space20">
        <div class="span6">
<form class="form-signin" action="edituser" enctype="multipart/form-data" >
                    
                <%
                     
String user=(String)session.getAttribute("user");String msg=(String)session.getAttribute("error");
if(user!=null&&user.equals("admin"))
{
Statement connect = dbconn.connect();
int uid=Integer.parseInt(request.getParameter("id").toString());
request.setAttribute("uid",uid);
session.setAttribute("uid",uid);
ResultSet rs=connect.executeQuery("select * from users where uid="+uid);
             if(rs.next())
             {
%>


            <h3 class="form-signin-heading">Please Enter The User Details</h3>
            <input type="text" class="input-block-level validate[required,min[500]]" value='<%=rs.getString("uname")%>' name="data[User][username]">
                <input type="text"  class="input-block-level validate[required,min[500]]" value='<%=rs.getString("location")%>' name="data[User][location]">
               <input type="text" class="input-block-level validate[required,min[0]]" value='<%=rs.getInt("age")%>' name="data[User][age]">
               <input type="text" class="input-block-level validate[required,min[10]]" value='<%=rs.getString("pwd")%>' name="data[User][pwd]">
                <input type="text" class="input-block-level validate[required,min[10]]" value='<%=rs.getString("phno")%>' name="data[User][phone_number]">
                <input type="text" class="input-block-level validate[required]" value='<%=rs.getString("userkey")%>' name="data[User][key]">
               
                <%}

if(msg!=null&&msg.equals("true"))                             
        {%>
        <h5><font color='red'>The UserName already exists</h5></font>
        <%}%>
       
                <button class="btn btn-large btn-primary" type="submit">Edit</button>
                <a href="manage_users.jsp"class="btn btn-large btn-primary" >Cancel</a>
                <% 
}   
else
{
 %> 
 <h3 class="form-signin-heading">Please <a href="login.jsp">Login</a> Again </h3>
  <%}%>


</form>
  </div>
   <div class="span5">
           <h1>Hello, world!</h1>
            <p>This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
            
        </div>
    </div>

    <!-- Example row of columns -->
   
</div>
<%@include file="footer.jsp" %>