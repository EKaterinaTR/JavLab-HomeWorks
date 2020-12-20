<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.10.2020
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/T_11CSRF_war/enter/change_password" method="post">
    <input type="hidden" value="${_csrf_token}" name="_csrf_token">
    <input name="login" type="password">
    <input name="last_password" type="password">
    <input name="password" type="password">
    <input type="submit" value="OK">
</form>

</body>
</html>
