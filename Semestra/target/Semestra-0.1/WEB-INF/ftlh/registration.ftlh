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
        <input id = "log" type="text" name="login" placeholder="Логин">
        <input id = "pass" type="password" name="password" placeholder="Пароль">
    </form>
    <input id="pass2" type="password" name="password2" placeholder="Пароль">
    <button type="button" class="btn btn-outline-primary" onclick="signUp()">Зарегистрироваться</button>
    <p id ="mistake"></p>

    <script>
        async function signUp() {
            if (document.getElementById("pass").value
                == document.getElementById("pass2").value) {
                let response = await fetch("http://localhost:8080" + "<@spring.url '/sign'/>", {
                    method: 'POST',
                    mode: 'cors',
                    cache: 'no-cache',
                    credentials: 'same-origin',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    redirect: 'follow',
                    referrerPolicy: 'no-referrer',
                    body: JSON.stringify( {
                        password:document.getElementById("pass").value,
                    login:document.getElementById("log").value})
                });
                alert("получили json ответ")
                if (response.ok) {
                    alert("статус норм");
                    let json = await response.json();
                    alert("gjkexbkb json")
                    alert(json)
                    if (json.same) {
                        document.getElementById("mistake").innerText = "Логин занят, выберете другой.";
                    }
                    else {
                        document.getElementById("mistake").innerText = "pfh";
                        document.getElementById("mistake").innerText = json.location;

                        location.href =  json.location;
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