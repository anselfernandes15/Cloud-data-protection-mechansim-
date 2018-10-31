<%@page import="java.util.ArrayList"%>
<%@page import="com.consumer.SendMail"%>
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<%@page import="java.sql.ResultSet"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">



      <table border="1" align="center" width="1000">
 <tr><th colspan="8">USER BEHAVIOUR ANALYSIS</th>
     <th colspan="2" align="right"><a href="adminhome.jsp">Back</a> </th></tr>


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
                             
                      </tr><tr>
        <%
           try {
            core.db.UBA alg = new core.db.UBA();
   alg.getAllBehavior();
      //  if(alg.getBehavior("Reshma",9,1))
         //   System.out.println("Done UBA");

        } catch (Exception e) {
            e.printStackTrace();
        }
           
      //  response.setIntHeader("Refresh", 1);
        String st=""; String p=null; String uid=null;
        try
                {
            uid = request.getParameter("uid");
session.setAttribute("ul",uid);
            //int uid=(Integer)session.getAttribute("ubaid");
           //int t=(Integer)session.getAttribute("ubath");
           String q = "select * from  uba where uid="+uid +" and threshold=1";

            Statement connect = dbconn.connect(); //out.print("15<br>");
 System.out.println("sql:" + q);
//                out.print("dbbberror3");
               ResultSet rs= connect.executeQuery(q);
              if(rs.next())
                  {


  int  id=rs.getInt(2);
 int   th=1;
   p="http://localhost:8080/DataProtectionInCloud/img/"+id+th+".png";
    st=rs.getString(10);

   // out.println(p);
    %>
                     
                     


                      <tr>   <td><%=rs.getString(3)%></td>
                          <td><%=id%></td>
                          <td><%=rs.getDouble(4)%></td>
                          <td><%=rs.getDouble(5)%></td>
                          <td><%=rs.getDouble(6)%></td>
                          <td><%=rs.getDouble(7)%></td>
                          <td><%=rs.getDouble(8)%></td>
                          <td><%=rs.getDouble(9)%></td>
                          <td><%=st%></td>
                          <td>1</td>
                        
                      <%
                  }
                }
         catch (Exception em) {
            em.printStackTrace();
        }



        %>
        </tr>
         <tr>
                          <td colspan="10" align="center">
                            <IMG SRC="<%=p%>" width="500" height="500" BORDER="0" USEMAP="#chart"></td>
                      </tr>
                          <tr>
                          <td colspan="10" align="center">
                            <%if(st.equals("Genuine")){%>
                          <a class="btn btn-primary btn-small " href="showlog.jsp?uid=<%=uid%>">Show Log</a>
                           <%}else if(st.equals("Anomalous")){
 try {
      String status="",sql="",st1="";
            Statement connect = dbconn.connect();// out.print("15<br>");
            
                sql  = "Update users set status='block' where uid="+uid;
              st1="User Blocked";
          
          //  else //if(status.equalsIgnoreCase("block"))
           //    {
  //  sql  = "Update users set status='active' where uid="+uid;
    //  st1="User Active";
//}
     ArrayList eid=new ArrayList();
System.out.println(sql);
                int rows = connect.executeUpdate(sql);
                  ResultSet rs1=connect.executeQuery("select email from users where uid="+uid);
          if(rs1.next())
             {   
              String eid1=rs1.getString("email");
              eid.add(eid1);
                  SendMail.sendMail("testproject201@gmail.com",eid, st1,"user blocked mail sent");
              }
System.out.println(uid+ " blocked");
            }
                  catch (Exception e) {
            e.printStackTrace();
            out.println("error"+e);


        } 
   // response.sendRedirect("blockuser?id="+uid);%>
   
                        &nbsp;&nbsp;&nbsp;&nbsp;    <a class="btn btn-primary btn-small " href="showlog.jsp?uid=<%=uid%>">Show Log</a><%}%>
                          </td>
                      </tr>
        </table>
    </td>
</tr>
</table>

    