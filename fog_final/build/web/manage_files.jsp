<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<div class="container">
    <div class="row background space20">


        <%                    Statement connect = dbconn.connect();
            int i = 1;
            String user = (String) session.getAttribute("user");
            if (user != null && user.equals("admin")) {
                String edit = "Edit", f = (String) session.getAttribute("edit");
                ResultSet rs = connect.executeQuery("select * from files ,users where files.uid=users.uid and files.uid!=0");
//if(!rs.first())
                if (rs == null) {%>
        <h3> No Files are Uploaded yet</h3>
        <%} else {
     if (f != null && f.equals("true")) {%>
        <h3 color="green">The Details are Updated</h3> <%session.setAttribute("edit", "false");
            }

        %>
        <table border="1" length="80%" width="80%">
            <thead> 
                <th class="th1"  >S.No. </th>
                <th class="th1" width="30%">  File ID  </th>
                <th class="th1" >File Name  </th><th class="th1" width="70%">  File Size  </th>
                <th class="th1" width="50%">   Date  </th> <th class="th1" width="50%"> Type </th>
                <th class="th1" > Uploaded By </th><th class="th1" width="50%">Action </th>
            </thead>
            <%while (rs.next()) {%>

            <tr class="tr1" >
            <td class="td1"><%=i++%></td><td class="td1"><%=rs.getString("file_id")%></td><td class="td1"><%=rs.getString("file_name")%></td>
            <td  class="td1"><%=rs.getString("file_size")%></td><td  class="td1"><%= rs.getTimestamp("date")%></td>
            <td  class="td1">User File </td><td  class="td1"><%=rs.getString("uname")%></td>
            <td  class="td1">
                <a class="btn btn-primary btn-small"href="deletefile?fid=<%=rs.getString("file_id")%>">Delete</a></td>

            </tr> 
            <%}
                }
                if (false) {
                    ResultSet rs1 = connect.executeQuery("select * from files where uid=1");
                    if (!rs1.first()) {%>
            <h3> No Decoy Files are Uploaded yet</h3>
            <%} else {

                while (rs1.next()) {

            %>

            <tr class="tr1" >
            <td class="td1"><%=i++%></td><td class="td1"><%=rs1.getString("file_id")%></td><td class="td1"><%=rs1.getString("file_name")%></td>
            <td  class="td1"><%=rs1.getString("file_size")%></td><td  class="td1"><%= rs1.getTimestamp("date")%></td>
            <td  class="td1">Decoy File </td><td  class="td1">Admin</td>
            <td  class="td1">
                <a class="btn btn-primary btn-small "href="editfile.jsp?fid=<%=rs1.getString("file_id")%>"><%=edit%></a> 
                <a class="btn btn-primary btn-small"href="deletefile?fid=<%=rs1.getString("file_id")%>">Delete</a></td>  


            </tr> 
            <%}
                    }
                }
            } else {
            %> 
            <h3 class="form-signin-heading">Please <a href="login.jsp">Login</a> Again </h3>
            <%}%>    

        </table>    



    </div>

    <!-- Example row of columns -->

</div>
<%@include file="footer.jsp" %>