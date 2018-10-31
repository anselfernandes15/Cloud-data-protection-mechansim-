<%@include file="header.jsp" %>
<%@include file="navigation_user.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<div class="container">
    <div class="bs-docs-example">
        <%String user = (String) session.getAttribute("user");
       
            if (user != null && !user.equals("admin")) {
               String profile=(String)session.getAttribute("profile");

                session.setAttribute("download", "false");
                Statement connect = dbconn.connect();
                String sql = "";
                int i = 1;
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                int userid = (Integer) session.getAttribute("userid");
                ResultSet rs4 = connect.executeQuery("select * from logdetails where userid=" + userid + " and ipaddress='" + ipAddress + "' and action='viewfiles'");  //3 is max ipaddresses 
                if (!rs4.first()) {
                    connect.executeUpdate("Insert into logdetails (userid,ipaddress,action) values(" + userid + ",'" + ipAddress + "','viewfiles')");
                }
                boolean valid = new core.db.UserBehaviourAlg().getBehave(userid).equals("validate");
                if (profile == null || (profile.equalsIgnoreCase("validate") && valid)) {

                    sql = "select file_name,file_id,file,file_size,hmac,date,uid from files where uid="+userid+" ORDER BY date desc";
                    System.out.println("sql:" + sql);
                } else {
                    sql = "select file_name,file_id,file,file_size,hmac,date,uid from files where uid="+userid+" ORDER BY date desc";
                    System.out.println("sql:" + sql);
                }
         
                ResultSet rs = connect.executeQuery(sql);
                if (!rs.first()) {%>
        <h3> No Files are Uploaded yet</h3>
        <%} else {%>
        <table border="2" length="70%" width="70%">
            <thead>
                <tr>
                    <th>File Name</th>
                    <th>File Size</th>                    
                    <th>Date</th>
            </thead>
            <%
                do {%> 
            <tbody><tr class="tr1" >

                    <td><a class=".a1" href="accessfile.jsp?id=<%=rs.getInt("file_id")%>" ><%=rs.getString("file_name")%></a></td>
                    <td  class="td1"><%=rs.getString("file_size")%> kb</td>                 
                    <td  class="td1"><%= rs.getDate("date")%></td>
                </tr> </tbody>
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

