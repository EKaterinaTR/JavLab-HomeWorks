<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="/T_11CSRF_war/enter/change_password" method="get">
    <input type="hidden" value="${_csrf_token}" name="_csrf_token">
    <input type="submit" value="Сменить пароль">
</form>

</body>
</html>