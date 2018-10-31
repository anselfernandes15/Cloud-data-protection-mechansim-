<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>

<div class="container">
    <div class="row background space20">
        <div class="span6">
            <form class="form-signin" action="uploaddecoy" enctype="multipart/form-data" method="post">

                <%
                    String user = (String) session.getAttribute("user");
                    if (user != null && user.equals("admin")) {
                        String file = (String) session.getAttribute("file");
                        if (file != null && file.equals("uploaded")) {
                %>                                                                  
                <h3 class="form-signin-heading">Upload The Other one</h3>
                <%
                        session.setAttribute("file", "");
                    }%>
                <h3 class="form-signin-heading">Select file to upload</h3>

                <input type="text" class="input-block-level validate[required]" placeholder="Filename" name="data[File][filename]">
                <input type="file"  class="input-block-level validate[required]"  name="data[File][file]">
                <select name="type" class="input-block-level validate[required]" > 

                    <option selected >Choose the Type</option>
                    <option  value=".txt" >Text File(.txt)</option>

                    <option  value=".doc">Word Document(.doc)</option>
                    <option  value=".docx">Microsoft Word(.docx)</option>
                    <option  value=".txt" >Excel File(.xls)</option>

                    <option  value=".doc">Microsoft Excel(.xlsx)</option>
                    <option  value=".docx">Microsoft Power Point(.ppt)</option>
                    <option  value=".txt" >Adobe Reader(.pdf)</option>

                    <option  value=".doc">JPEG Image(.jpeg)</option>
                    <option  value=".docx">PNG Image(.png)</option>

                </select>
                <button class="btn btn-large btn-primary" type="submit">Upload</button>
                <a href="uploaddecoy.jsp" class="btn btn-large btn-primary">Cancel</a>


            </form><%} else {
            %> 
            <h3 class="form-signin-heading">Please <a href="login.jsp">Login</a> Again </h3>
            <%}%>
        </div>
        <div class="span5">
            <img src="img/files.png" alt="">
        </div>
    </div>

    <!-- Example row of columns -->

</div>
<%@include file="footer.jsp" %>