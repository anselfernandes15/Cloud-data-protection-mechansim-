<%@page import="core.db.UserBehaviourAlg"%>
<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<%@ page import="java.sql.*" %>
<%@page  import="core.db.dbconn" %>



<div class="container">
    <div class="row background space20">


        <%
        int id=0;
          String value=request.getParameter("id").toLowerCase();
          //session.setAttribute("uba",value);
 try {
       
            Statement st3 = dbconn.connect();

            ResultSet r1 = null;
session.setAttribute("ubauser",value);

                      String   sql = "select uid  from users where uname='" + value+"'";
                        System.out.println("sql: " + sql);
                      r1 = st3.executeQuery(sql);
                        if (r1.next())
                        {
             id=r1.getInt(1);
              System.out.println("user id: " + id);
session.setAttribute("ubaid",id);
                        }
                        }
        catch (Exception em) {
            em.printStackTrace();
        }
//System.out.println(value);
         int th=Integer.parseInt(request.getParameter("th"));
         session.setAttribute("ubath",th);
    String user1=(String)session.getAttribute("user");
    System.out.println(user1);
if(user1.equals("admin"))
       {
    System.out.println("In. UBA");
   core.db.UBA alg=new core.db.UBA();
   // int id=41;
boolean x=alg.getBehavior(value,id,th);

}
               %>
<%@include file="viewpie.jsp"%>
         </div>

    <!-- Example row of columns -->

</div>
<%@include file="footer.jsp" %>