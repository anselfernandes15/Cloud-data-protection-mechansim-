<%@page import="core.db.dbconn"%>
<%@page import="java.sql.*"%>
<%@include file="header.jsp" %>
<%@include file="navigation_tpa.jsp" %>
<%@include file="flash.jsp" %>
<% session.setAttribute("user","TPA");%>
                                   
<div id="myCarousel" class="carousel slide" data-interval="3000">
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
  
   
</div>
<!-- /.carousel -->

<div class="container">
                   
    <div class="row background space20">
        
        <%String user = (String) session.getAttribute("user");
            if (user != null&&user.equals("TPA")) {
                Statement connect = dbconn.connect();int i=1;
                 String msg1=(String)session.getAttribute("file");
               
                 if(msg1!=null&&msg1.equals("true"))
                {%><font color="green">File verified Successfully</font><%}
            
                String sql = "select * from files where status='verify' and file_size!=0 and uid!=1";
                ResultSet rs = connect.executeQuery(sql);
                if (!rs.first()) {%>
        <h3> No Files are Uploaded yet</h3>
        <%} else {%>
        <table  length="80%" width="80%">
            <thead><th class="th1" > S.No. </th> 
            <th class="th1" width="30%">  File Name  </th>
            <th class="th1" width="50%">   Date  </th>
            <th class="th1" width="50%">Action </th>
            </thead>
            <%
               do{%> 

            <tr class="tr1" ><td class="td1"><%=i++%></td>
                <td class="td1"><%=rs.getString("file_name")%></td><td class="td1"><%=rs.getString("date")%></td>
                
                <td  class="td1">
                    <a class="btn btn-primary btn-small"href="view_filestpa.jsp?fid=<%=rs.getInt(1)%>">Verify</a>
          </tr>
            <%} while (rs.next()); 
                 
                }
            } else {
            %> 
            <h3 class="form-signin-heading">Please <a href="login.jsp">Login</a> Again </h3>
            <%}%>



        </table>    



    </div>

    <!-- Example row of columns -->

</div>
<!-- /END THE FEATURETTES -->
<%@include file="footer.jsp" %>