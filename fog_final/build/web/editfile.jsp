<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@ page import="core.db.dbconn" %>
<div class="container">
    <div class="row background space20">
        <div class="span6">
<form class="form-signin" action="editfile" enctype="multipart/form-data" >
                    
                <%
                     
String user=(String)session.getAttribute("user");
String msg=(String)session.getAttribute("error");
if(user!=null&&user.equals("admin"))
{
Statement connect = dbconn.connect();
String fid=request.getParameter("fid");
 request.setAttribute("fid",fid);
session.setAttribute("fid",fid);

     ResultSet rs=connect.executeQuery("select * from files where file_id="+fid);
          if(rs.next())
             {
%>


            <h3 class="form-signin-heading">Please Enter The File Details</h3>
            <input type="text" class="input-block-level validate[required,min[500]]" value='<%=rs.getString(2)%>' name="filename">
            <input type="file" class="input-block-level "  name="file">
                
                <%}

if(msg!=null&&msg.equals("true"))                             
        {%>
        <h5><font color='red'>The File already exists</h5></font>
        <%}%>
         <select name="type" class="input-block-level validate[required]" > 
                    
                        <option selected >Choose the Type</option>
                        <option  value=".txt" >Text File(.txt)</option>
                     <option  value=".htm">Html File(htm)</option>
                     <option  value=".doc">Word Document(.doc)</option>
                     <option  value=".docx">Microsoft Word(.docx)</option>
                     <option  value=".jpg">Image(.jpg)</option>
                     <option  value=".png">Image(.png)</option>
                      <option  value=".gif">Image(.gif)</option>
                  <option  value=".pdf">Pdf File(.pdf)</option>
                  
                  </select>
               
                <button class="btn btn-large btn-primary" type="submit">Edit</button>
                <a href="manage_files.jsp" class="btn btn-large btn-primary" >Cancel</a>
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