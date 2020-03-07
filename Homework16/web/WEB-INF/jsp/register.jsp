<%--
  Created by IntelliJ IDEA.
  User: abdul
  Date: 02.03.2020
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>registration</title>
</head>

<body>
<div class="register-photo">
    <div class="form-container">
        <form method="post" action="/Homework16_war_exploded/register">
            <h2 class="text-center"><strong>Регистрация</strong>.</h2>
            <input class="form-control" type="text" name="email" placeholder="e-mail"><br>
            <div class="form-group"><input class="form-control" type="password" name="password" placeholder="Пароль"></div>
            <div class="form-group">
                <button class="btn btn-primary btn-block" type="submit">Зарегистрироваться</button>
            </div>
        </form>
    </div>
</div>
</body>

</html>