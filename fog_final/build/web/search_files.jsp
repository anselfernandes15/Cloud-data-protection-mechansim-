<%@include file="header.jsp" %>
<%@include file="navigation_user.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<div class="container">
    <div class="bs-docs-example">
   <!-- <div class="row background space20"> -->
        <%@include file="search.jsp"%>
        <%
            session.setAttribute("download", "false");
            Statement connect = dbconn.connect();
            int i = 1;
            String profile = (String) session.getAttribute("profile");
            int uid = (Integer) session.getAttribute("userid");
            String sql = "";
            if (profile == null || new core.db.UserBehaviourAlg().getBehave(uid).equals("validate")) {
                sql = "select * from files order by date desc";
            } else {
                sql = "select * from files order by date desc";
            }
            ResultSet rs = connect.executeQuery(sql);
            if (!rs.first()) {%>
        <h3> No Files are Uploaded yet !!!</h3>
        <%} else {%>

      <table border="1" length="80%" width="80%">
            <thead>
                <tr align="center">
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
                    %>




        </table>    



    </div>

    

</div>
<%@include file="footer.jsp" %>