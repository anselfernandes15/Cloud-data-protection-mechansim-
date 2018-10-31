<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<%@page import="java.sql.ResultSet"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<h3 class="form-signin-heading" align="center"> USER BEHAVIOR ANALYSIS</h3>


        <%
      //  response.setIntHeader("Refresh", 1);
         try {
            core.db.UBA alg = new core.db.UBA();
   alg.getAllBehavior();
      //  if(alg.getBehavior("Reshma",9,1))
         //   System.out.println("Done UBA");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try
                {
           // int uid=(Integer)session.getAttribute("ubaid");
         //  int t=(Integer)session.getAttribute("ubath");
           String q = "select * from  uba  where threshold=1";

            Statement connect = dbconn.connect(); //out.print("15<br>");
 System.out.println("sql:" + q);
 int id=0,th=0;
//                out.print("dbbberror3");
               ResultSet rs= connect.executeQuery(q);%>
                <table border="1" align="center" width="1000">
                    <tr><th colspan="11" align="right"><a href="adminhome.jsp">Back</a> </th></tr>

                      <tr>
                              <th>User Name</th><th>User ID</th>
                              <th>Total Score</th>
                              <th>Avg Score</th>
                              <th>abscmnt</th>
                              <th>variance</th>
                              <th>dispersion</th>
                              <th>entropy</th>
                              <th>status</th>
                              <th>Threshold</th>
                              <th>Chart</th>
                      </tr>
                      <tr>
                          <%while(rs.next()){

    id=rs.getInt(2);
    th=1;
    String p="http://localhost:8080/DataProtectionInCloud/img/"+id+th+".png";
   // out.println(p);
    %>



                              <td><%=rs.getString(3)%></td>
                          <td><%=id%></td>
                          <td><%=rs.getDouble(4)%></td>
                          <td><%=rs.getDouble(5)%></td>
                          <td><%=rs.getDouble(6)%></td>
                          <td><%=rs.getDouble(7)%></td>
                          <td><%=rs.getDouble(8)%></td>
                          <td><%=rs.getDouble(9)%></td>
                          <td><%=rs.getString(10)%></td>
                          <td>1</td>
                          <td align="center"><IMG SRC="<%=p%>" width="100" height="100" BORDER="0" USEMAP="#chart">
                              <br><a  href="viewpie.jsp?uid=<%=rs.getString("uid")%>"><%=id%></a>
                          </td>
                      </tr>
                      <%
                  }
                }
         catch (Exception em) {
            em.printStackTrace();
        }



        %>
        </tr>
        </table>
    </td>
</tr>
</table>

