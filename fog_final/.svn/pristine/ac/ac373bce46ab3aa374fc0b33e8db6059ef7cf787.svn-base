<%@include file="header.jsp" %>
<%@include file="navigation_user.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<div class="container">
    <div class="row background space20">
        <%@include file="search.jsp"%>
        <table>
    <%
    String value=request.getParameter("id").toLowerCase();
    String user1=(String)session.getAttribute("user");
if(user1!=null&&!user1.equals("admin"))
       {
    core.db.UserBehaviourAlg alg=new core.db.UserBehaviourAlg();
    String user=alg.getBehave((Integer)session.getAttribute("userid"));
    String profile=(String)session.getAttribute("profile");
   Statement st = dbconn.connect(); ResultSet rs=null;
  String sql="";%><%=value%><%
if(profile==null&&user.equals("validate"))  
 sql="select * from files where LCASE(file_name) like '%"+value+"%' ";
else
    sql="select * from files where LCASE(file_name) like '%"+value+"%' and uid=1";
 rs = st.executeQuery(sql);
 if(!rs.first())
      {%> </table>           
      <h3> No Files are found</h3> 
  <%}while(rs.next()){ 
        %>
   <tr class="tr1" >
        <td><a class="a1" href="accessfile.jsp?id=<%=rs.getInt(1)%>" <td class="td1"><%=rs.getString("file_name")%></td>
    <td  class="td1"><%=rs.getString("file_size")%></td><td  class="td1"><%= rs.getTimestamp("date")%></td>
     
</tr>   <%
    }  }   
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