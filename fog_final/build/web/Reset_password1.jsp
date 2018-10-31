
<%@page import="core.db.dbconn"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@include file="header.jsp" %>
<%@include file="navigation.jsp" %>
<%@include file="flash.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <form action="Reset_password1" method="post">

            <div class="container">
                <div class="row background space20">
                    <div class="span6" >
                        <h3 class="form-signin-heading">Please give correct answer of your question:</h3>
                        <%String str = request.getParameter("Question");
                        String v = request.getParameter("answer");
                            out.println("Question:" + str);
                            String uname = request.getParameter("uname");
                            System.out.println("una:" + uname);
                           

                            session.setAttribute("uname", uname);
                          /*  String sql = "select Answer from users where question='" + str + "' AND uname='" + uname + "'";
                            System.out.println("sql==" + sql);
                            Statement st = dbconn.connect();
                            ResultSet rs = st.executeQuery(sql);
                            String ans = "";
                            while (rs.next()) {
                                ans = rs.getString("answer");

                            }*/
                            System.out.println("ans=" + v);
                        %>
                        <input type="text"   name="answer"  class="input-block-level validate[required,min[10]]" placeholder="Answer ">

                        <input type="hidden" name="ans" value=<%=v%>>
                               <input type="submit" name="submit" value="submit" class="btn btn-large btn-primary">

                    </div>

                </div>

            </div>
        </form>
    </body>
</html>
<%@include file="footer.jsp" %>