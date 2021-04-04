<#ftl encoding="UTF-8"/>

<!DOCTYPE html>
<html lang="ru">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><@spring.message 'book_page.title'/></title>
</head>
<body>
<div>
    <h2><a href="?lang=ru">РУ</a></h2>
    <h2><a href="?lang=en">EN</a></h2>
</div>
<div>
    <a href="profile"><@spring.message 'book_page.link_to_profile'/></a>

</div>
<div>

    <input id="log" type="text" placeholder="<@spring.message 'book_page.placeholder'/>" onkeyup="search();">
    <div></div>
    <#list books as book>
        Name: ${book.name}
        Author: ${book.author}
    </#list>
</div>
    <script>
        function search() {
            //отправка ajax запроса
        }

    </script>


</div>
</body>
</html>