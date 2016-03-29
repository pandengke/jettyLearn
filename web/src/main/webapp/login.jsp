<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/28
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to login Infomation System</title>
</head>
<body>

<form action="/web/user_login" method="get" enctype="text/plain">
    <input type="text" name="uname" size="20"/>
    <input type="text" name="upassword" size="20"/>
    <input type="submit" name="login">
</form>
<%
    out.println("abc");
%>
</body>
</html>
