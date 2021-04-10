<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><@spring.message "enter_page.title"/></title>
</head>
<body>
<div>
    <h2><a href="?lang=ru">РУ</a> </h2>
    <h2><a href="?lang=en">EN</a> </h2>
</div>
<div>
    <form method="post" action="enter">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="text" name="login" placeholder="<@spring.message "enter_page.placeholder_login"/>">
        <input type="password" name="password" placeholder="<@spring.message "enter_page.placeholder_password"/>">
        <input type="submit" value="<@spring.message "enter_page.submit"/>">
    </form>
    <a href="sign"><@spring.message "enter_page.link_to_registration"/></a>
</div>
</body>
</html>