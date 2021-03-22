<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><@spring.message "profile_page.title"/></title>
</head>
<body>
<div>
    <h2><a href="?lang=ru">РУ</a> </h2>
    <h2><a href="?lang=en">EN</a> </h2>
</div>
<div>
    <img src="${user.linkToImage}"><h1>${user.name}</h1>
    <p>${user.biography}</p>

</div>
</body>
</html>