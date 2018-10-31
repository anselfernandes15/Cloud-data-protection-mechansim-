<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<div class="container">
    <div class="row background space20">


        <%

            Statement connect = dbconn.connect();
            int i = 1;
            String user = (String) session.getAttribute("user");
            if (user != null && user.equals("admin")) {
                String status = "block";
                String edit = "Edit", f = (String) session.getAttribute("edit");
                ResultSet rs = connect.executeQuery("select * from users where status!='admin'");
                if (!rs.first()) {%>
        <h3> No Users are Registered yet</h3>
        <%} else {
     if (f != null && f.equals("true")) {%>
        <h3 color="green">The Details are Updated</h3> <%f = "false";
            }

        %>
        <table border="1" length="80%" width="80%">
            <thead>
                <th class="th1"  >S.No. </th> <th class="th1" width="30%">  User ID  </th>
                <th class="th1" >UserName  </th><th class="th1" width="70%">  Location  </th>
                <th class="th1" > User Key </th><th class="th1" width="50%">   Date  </th>
                <th class="th1" >  Phone.No. </th><th class="th1" width="50%">Status </th>
                <th class="th1" width="50%">Action </th>
            </thead>
            <%do{%>

            <tr class="tr1" >
            <td class="td1"><%=i++%></td><td class="td1"><%=rs.getInt(1)%></td><td class="td1"><%=rs.getString(2)%></td>
            <td  class="td1"><%=rs.getString(6)%></td><td  class="td1"><%=rs.getString(5)%></td>
            <td  class="td1"><%=rs.getDate(7)%></td><td class="td1"><%=rs.getString(8)%></td>
            <td class="td1"><% String behave=new core.db.UserBehaviourAlg().getBehave(rs.getInt(1));
            out.print((behave.equals("validate"))?"valid":"invalid"); %></td>
            <td  class="td1"> <a class="btn btn-primary btn-small" href="manageusers?id=<%=rs.getString(1)%>">
                    <%String s1 = "";
                        if (rs.getString(9).equals("active")) {
                           s1 = "block the user";

                        } else {
                            s1 = "active the user";

                        }
                    %>
                    <%=s1%>
                </a>

                    <% if(behave.equals("invalidate")){ %><a class="btn btn-primary btn-small "href="ChangeStatus.jsp?uid=<%=rs.getString("uid")%>">Change Status</a> <% } %>
                <a class="btn btn-primary btn-small"href="deleteuser?id=<%=rs.getString(1)%>">Delete</a></td>

            </tr>
            <%}while (rs.next());
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