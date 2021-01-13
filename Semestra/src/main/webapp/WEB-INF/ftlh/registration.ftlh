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
    <form id="reg" method="post" action="">
        <input type="text" name="login" placeholder="Логин">
        <input id = "pass" type="password" name="password" placeholder="Пароль">
    </form>
    <input id="pass2" type="password" name="password2" placeholder="Пароль">
    <button type="button" class="btn btn-outline-primary" onclick="signUp()">Зарегистрироваться</button>
    <p id ="mistake"></p>

    <script>
        async function signUp() {
            if (document.getElementById("pass").value
                == document.getElementById("pass2").value) {
                let response = await fetch("http://localhost:8080" + "<@spring.url '/ex'/>");
                if (response.ok) {
                    let json = await response.json();
                    if (json.same) {
                        document.getElementById("mistake").innerText = "Логин занят, выберете другой.";
                    }
                    else {
                        let response2 = await fetch("http://localhost:8080" + "<@spring.url '/ex2'/>");
                        window.location.href = response2; //дописать тут путь тоже через json

                    }
                } else {
                    alert("Ошибка HTTP: " + response.status);
                }
            } else {
                document.getElementById("mistake").innerText = "Пароли не совпадают";
            }

        }
    </script>

</div>
</body>
</html>