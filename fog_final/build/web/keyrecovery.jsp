
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="core.db.dbconn"%>
<%@include file="header.jsp" %>
<%--<%@include file="navigation.jsp" %>--%>
<%@include file="navigation_user.jsp" %>
<%@include file="flash.jsp" %>

<script>
    function validateof(){  
var Answerv=document.keyrf.Answer.value; 
if (Answerv==null || Answerv==""){  
  alert("enter answer");
   return false;  }
    }
</script>
<div class="container">
    <div class="row background space20">
        <div class="span6">

            <form name="keyrf" class="form-profile" action="keyretrive.jsp" method = "post" onsubmit="return validateof()">
                <h2 class="form-signin-heading">Key Recovery</h2>
                <%
                    Object uname = session.getAttribute("user");
                    System.out.println("uname = " + uname);
                    if(session.getAttribute("key_count")==null) session.setAttribute("key_count", 0);
                    Statement st = dbconn.connect();
                    //  st1 = dbconn.connect();
                    //  String sql = "select * from file_table ";
                    // String sql = "SELECT u.username , f.original_filename , f.updated_filename from users u , file_table f where u.id=f.user_id";
                    String sql = "SELECT DES_DECRYPT(question) from users where uname='" + uname + "'";
                    // String sql1 = "SELECT userkey from users where uname='" + user + "'";

                    ResultSet rs = st.executeQuery(sql);
                    String question_table = null;
                    while (rs.next()) {
                        question_table = rs.getString(1);
                    }
                %>

                <h3 class="form-signin-heading">User name:<%=uname%></h3>
                <input type="hidden"  value="<%=uname%>" name = "username">

                <h3 class="form-signin-heading"><%=question_table%>?</h3>
                <input type="hidden"  value="<%=question_table%>" name = "Question">

                <input type="password" class="input-block-level " placeholder="Answer" name = "Answer">

                <button type="submit" class="btn btn-primary">Submit  </button>
               

            </form>




        </div>
    </div>       
</div>

<%@include file="footer.jsp"%> 