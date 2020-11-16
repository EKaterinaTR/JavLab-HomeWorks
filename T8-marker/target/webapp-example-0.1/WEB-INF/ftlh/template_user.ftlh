<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>

<table>
    <tr>
        <th>Name</th>
        <th>Age</th>
    </tr>
    <#list users as listUser>
        <tr>
            <td>${listUser.firstName}</td>
            <td>${listUser.age}</td>
        </tr>
    </#list>
</table>

</body>
</html>
