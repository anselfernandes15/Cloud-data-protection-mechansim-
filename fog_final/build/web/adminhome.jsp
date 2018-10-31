<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<% session.setAttribute("user","admin");%>
                                   
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>
<div class="container">
    <div class="row background space20">

  <h1>Active user details :</h1>
        <%
       //  List<String> users = (List<String>)session.getAttribute("users");
    //    for(int i=0; null!=users && i < users.size(); i++) {
     //       out.println("<br>" + users.get(i));
     //   }
         
        System.out.println("Active user details :");
        
/*for (Enumeration e = session.getAttributeNames(); e.hasMoreElements(); ) {
    String attribName = (String) e.nextElement();
     Object attribValue = session.getAttribute(attribName);
     System.out.println("n="+attribName);
     System.out.println("v="+attribValue);
     String v=""+attribValue;
    if(attribName.equals("userid") && v!="1")
     
       // Object attribValue = session.getAttribute(attribName);

        {*/
       // ServletContext cx = request.getSession().getServletContext();
  //String s1=cx.getInitParameter("un");
     //   String s2=cx.getInitParameter("lt");
       // Object attribute = session.getAttribute("un");
            //     out.println("Name:"+ attribute.toString());
            String a = (String)session.getAttribute("un");
            System.out.println(a);
           if(a!=null)
{               String []b=a.split(",");
                if(b.length>1){
            for(int i=1;i<b.length;i++)
                {
                String []xx=b[i].split("#");
               if(!xx[0].equals("admin") && xx[0]!="") out.println("Name:"+xx[0]+"   Login Time:"+xx[1]+"Session id:"+xx[2]+"<br>");}
 }     }
        //try
          //      {
     //Statement connect = dbconn.connect();
      //ResultSet rs = connect.executeQuery("select * from login where status='login' order by logintime desc");
        //   while(rs.next())
          //     {
            //   if(!rs.getString("uname").equals("admin")){
       %>
    

               <%   // } }
      //}
        //catch(Exception pe){}



//}}
try
                {
            Statement connect = dbconn.connect();
            int i = 1;
            String user = (String) session.getAttribute("user");
            if (user != null && user.equals("admin")) {
                String status = "block";
                String edit = "Edit", f = (String) session.getAttribute("edit");
                ResultSet rs = connect.executeQuery("select * from users where status!='admin' order by date desc");
                if (!rs.first()) {%>
        <h3> No Users are Registered yet</h3>
        <%} else {
     if (f != null && f.equals("true")) {%>
        <h3 color="green">The Details are Updated</h3> <%f = "false";
            }

        %>
        <table border="1" length="80%" width="80%" align="center">
            
            <thead>
                <tr><th colspan="7"><h1 align="center">User Details</th></tr>
                <th class="th1"  >S.No. </th> <th class="th1" width="30%">  User ID  </th>
                <th class="th1" >UserName  </th><th class="th1" width="70%">  Location  </th>
                <th class="th1" width="50%">   Date  </th>
                <th class="th1" >  Phone.No. </th><th class="th1" >  Status </th>
            </thead>
            <%do{%>

            <tr class="tr1" >
            <td class="td1"><%=i++%></td><td class="td1"><%=rs.getInt(1)%></td><td class="td1"><a  href="viewpie.jsp?uid=<%=rs.getString("uid")%>"><%=rs.getString(2)%></a></td>
            <td  class="td1"><%=rs.getString(6)%></td>
            <td  class="td1"><%=rs.getDate(7)%></td><td class="td1"><%=rs.getString(8)%></td>
           <td class="td1"><%=rs.getString(9)%></td>
            </tr>
            <%}while (rs.next());
                }
            } else {
            %>
            <h3 class="form-signin-heading">Please <a href="login.jsp">Login</a> Again </h3>
            <%}
            }
        catch(Exception pe){}
            %>

        </table>



    </div>

    <!-- Example row of columns -->

</div>
<!-- /END THE FEATURETTES -->
<%@include file="footer.jsp" %>