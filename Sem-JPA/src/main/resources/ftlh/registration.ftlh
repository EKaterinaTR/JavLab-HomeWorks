<#ftl encoding="UTF-8"/>
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ru">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><@spring.message "registration_page.title"/></title>
</head>
<body>
<div>
    <h2><a href="?lang=ru">РУ</a> </h2>
    <h2><a href="?lang=en">EN</a> </h2>
</div>
<div>
    <@spring.bind "userDTO"/>
    <form id="reg" method="post" action="sign">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input id="log" type="text" name="login" placeholder="<@spring.message "registration_page.placeholder_login"/>"/>
        <label><@spring.message "registration_page.placeholder_password"/></label>
        <@spring.formPasswordInput "userDTO.password" "onkeyup=\"checkPasswordMatch();\""/>
        <@spring.showErrors "<br>" "error"/>
        <@spring.formInput "userDTO.email" "placeholder=\"EMAIL\""/>
        <@spring.showErrors "<br>" "error"/>

    </form>
    <input id="pass2" type="password" name="password2" placeholder="<@spring.message "registration_page.placeholder_password"/>"
           onkeyup="checkPasswordMatch();">
    <input id="submit" type="submit" value="<@spring.message "registration_page.submit"/>" form="reg">
    <p id="mistake"></p>

    <script>
        function checkPasswordMatch() {
            if (document.getElementById("password").value
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