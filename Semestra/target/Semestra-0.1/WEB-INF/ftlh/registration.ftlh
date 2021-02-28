<#ftl encoding="UTF-8"/>
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
</head>
<body>
<div>
    <form id="reg" method="post" action="sign">
        <input id="log" type="text" name="login" placeholder="Логин">
        <input id="pass" type="password" name="password" placeholder="Пароль"onkeyup="checkPasswordMatch();">
        <input type="email" name="email" placeholder="Email">
    </form>
    <input id="pass2" type="password" name="password2" placeholder="Пароль"  onkeyup="checkPasswordMatch();">
    <input id="submit" type="submit" value="зарегистрироваться" form="reg">
    <p id="mistake"></p>

    <script>
        function checkPasswordMatch() {
            if (document.getElementById("pass").value
                == document.getElementById("pass2").value) {
                document.getElementById("mistake").innerText = "";
                document.getElementById("submit").disabled = false;

            } else {
                document.getElementById("mistake").innerText = "Пароли не совпадают";
                document.getElementById("submit").disabled = true;
            }
        }

    </script>

</div>
</body>
</html>