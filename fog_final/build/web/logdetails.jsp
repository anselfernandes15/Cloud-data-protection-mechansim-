<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<div class="container">
    <div class="row background space20">
       <form  action="viewlogs.jsp">
<%  String user=(String)session.getAttribute("user");
if(user!=null&&user.equals("admin"))
       {Statement connect = dbconn.connect(); 
int f=0,id1=0;
ResultSet rs1=connect.executeQuery("select * from users  where status='active' ");
if(!rs1.first())
       {%>
       <h3> No Users are Registered yet</h3>
 <%}
else
       {
    
    %>

<select  name="username" class="validate[required]" width="20%"> 
                    
                        <option selected >Choose the User</option>
                       <% do  { if(f==0){ id1=rs1.getInt("uid") ; f=1;} %>
                       <option  value=<%=rs1.getInt("uid")%> > <%=rs1.getString("uname")%> </option>
                    <%}while(rs1.next());%>
                  </select>  <input class="btn btn-primary"type="submit" value="View Details">  
  </form>              
                <%
                
int i=1;
String id=(String)request.getParameter("id"), sql="";
if(id==null)
     sql="select * from logdetails join users on logdetails.userid=users.uid where users.uid="+id1;
else
   sql= "select * from logdetails join users on logdetails.userid=users.uid where users.uid="+id;
 ResultSet rs=connect.executeQuery(sql);
     if(!rs.first())
    {    %> <h3> No records are available,Choose The Other one </h3><%}
                               else {
     %>
     <table border="1" length="80%" width="80%">
         <thead> 
            <th class="th1"  >S.No. </th> <th class="th1" width="30%">  User ID  </th>
            <th class="th1" >UserName  </th><th class="th1" width="70%"> Operation  </th>
            <th class="th1" > User Key</th> <th class="th1" > Accessed File  </th><th class="th1" width="50%">   Date  </th>
   <th class="th1" > IP address</th> 
     </thead>
    <%while (rs.next())
  {%>
                 
    <tr class="tr1" >
        <td class="td1"><%=i++%></td><td class="td1"><%=rs.getInt("uid")%></td><td class="td1"><%=rs.getString("uname")%></td>
    <td  class="td1"><%=rs.getString("action")%></td><td  class="td1"><%=rs.getString("ukey")%></td>
     <td  class="td1"><%=rs.getString("fid")%></td><td class="td1"><%=rs.getDate("date")%></td>
    <td class="td1"><%=rs.getString("ipaddress")%></td>
</tr> 
<%}  }  }         }   
else
{
 %> 
 <h3 class="form-signin-heading">Please <a href="login.jsp">Login</a> Again </h3>
  <%}%>
                
                </table>    
                
     
  
    </div>

    <!-- Example row of columns -->
   
</div>
<%@include file="footer.jsp" %>