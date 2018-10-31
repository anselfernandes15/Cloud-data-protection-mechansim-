<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<div class="container">
    <div class="row background space20">
        <h1>User Log details</h1>
       <form  action="viewlogs1.jsp">
<%

  String  uid =(String)session.getAttribute("ul");
//session.setAttribute("ul",uid);
String d1=(String)request.getParameter("d1");
String  sql=null;
String d2=(String)request.getParameter("d2");
      Statement connect = dbconn.connect();

ResultSet rs1=connect.executeQuery("select * from users  where uid="+uid);
if(!rs1.first())
       {%>
       <h3> No Users are Registered yet</h3>
 <%}
else
       {

    %>
    Enter date in YYYY-MM-DD format<br>
From Date <input type="date" name="d1" class="validate[date]" value="<%=d1%>"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To Date <input type="date" name="d2" value="<%=d2%>" class="validate[date]">
  <br>
  <input class="btn btn-primary"type="submit" value="View Details">
  </form>

<%

int i=1;

System.out.println("uid="+uid);
System.out.println("d1="+d1);
System.out.println("d2="+d2);
if(d1!=null && d2!=null)
{
 sql= "select * from logdetails join users on logdetails.userid=users.uid where users.uid="+uid+ " and logdetails.date between  '"+d1+"' and '"+d2+"'";
 
System.out.println(sql);
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
<%}  }
 }
}
 %>
 
  <%%>

                </table>


<h3 class="form-signin-heading"> <a href="adminhome.jsp">Back</a> </h3>
    </div>

    <!-- Example row of columns -->

</div>
<%@include file="footer.jsp" %>