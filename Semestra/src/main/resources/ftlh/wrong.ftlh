<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><@spring.message"wrong_page.title"/></title>
</head>
<body>
<div>
    <h2><a href="?lang=ru">РУ</a> </h2>
    <h2><a href="?lang=en">EN</a> </h2>
</div>
<div>
    <h2><@spring.message"wrong_page.text"/></h2>
    <form method="get" action="enter">
        <input type="submit" value="<@spring.message"wrong_page.go_to_enter"/>">
    </form>

    <form method="get" action="sign">
        <input type="submit" value="<@spring.message"wrong_page.go_to_registration"/>">
    </form>
</div>
</body>
</html>