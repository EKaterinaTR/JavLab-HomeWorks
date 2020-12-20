<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.10.2020
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="/T_11CSRF_war/signIn" method="post">
    <input type="hidden" value="${_csrf_token}" name="_csrf_token">
    <input name="login" type="password">
    <input name="password" type="password">
    <input type="submit" value="OK">
</form>

</body>
</html>
