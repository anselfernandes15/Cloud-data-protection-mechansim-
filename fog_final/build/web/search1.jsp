<%-- 
    Document   : search1
    Created on : 14 May, 2015, 9:09:54 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="JavaScript">
function valid()
{
var a = document.f.id.value;
var b = document.f.th.value;
if(a=="")
{
alert("Enter Username");
document.f.id.focus();
return false;
}
if(b=="")
{
alert("Enter Threshold value");
document.f.th.focus();
return false;
}
}
</script>
<form  name="f" method="get" onSubmit="return valid();" action="processuba.jsp">
    <table><tr>
        <th align="left">User Name</th><td><input type="text" placeholder="Enter User Name " name="id"></td></tr>
        <tr><th align="left">Threshold Value</th><td><input type="text" placeholder="Enter Threshold Value " name="th"></td></tr>
        <tr><td colspan="2"><input type="submit" value="Search"></td></tr></table>
</form>

