<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Transfer Money</title>
    <link rel="stylesheet" href="/assets/css/style.css" media="screen" type="text/css" />
    <script src="assets/js/confirmPass.js"></script>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
</head>

<body>


<div class="wrapper">

    <header>        <div class="wrapper">
            <a href="#"><img src="assets/images/logo.jpg" class="logo" alt="" title=""/><h1>Transfer Money</h1></a>
        </div>
    </header><!--  end header section  -->
    <div id="zatemnenie">

        <div id="okno">
            <ul>
                <li>
                    Для регистрации на сайте вам нужно ввести имя и фамилию с большой буквы.
                </li>
                <li>
                    Действующий email адресс.
                </li>
                <li>
                    Придумайте пароль длинной от 6 до 15 символов.
                </li>
                <li>
                    Пароль должен содержать цифры и буквы латинского алфавита.
                </li>
            </ul>

            <a href="#" class="close">Приступить к регистрации</a>
        </div>
    </div>

    <div class="middle">
        <aside class="right-sidebar">
            <div id="login-form">
                <h2>Регистрация</h2>
                <fieldset>
                    <form action="registration" onsubmit=" return passwordmatch(this);" method="post">
                        <input id="fstLine" type="text" name="name" placeholder="Введите ваше имя" pattern="^[А-ЯЁA-Z]{1}[а-яёa-z]+$"
                               oninvalid="this.setCustomValidity('Invalid name! This should start with a capital letter and not contain extraneous characters! Example: John, Игорь')" oninput="this.setCustomValidity('')" required/>
                        <input type="text" name="surname" placeholder="Введите вашу фамилию" pattern="^[А-ЯЁA-Z]{1}[а-яёa-z]+$"
                               oninvalid="this.setCustomValidity('Invalid surname! This should start with a capital letter and not contain extraneous characters! Example: Carter, Иванов')" oninput="this.setCustomValidity('')" required/>
                        <input type="email" name="email" placeholder="Введите почту"  required/>
                        <input type="password" name="password" placeholder="Введите пароль" pattern="^[a-zA-Z0-9]{6,15}$"
                               oninvalid="this.setCustomValidity('Invalid password format! The password contains invalid characters OR length! Example: 123qwe, qwerty1')" oninput="this.setCustomValidity('')" required/>
                        <input id="lstLine" type="password" name="confirm" placeholder="Подтвердите пароль" pattern="^[a-zA-Z0-9]{6,15}$"
                               oninvalid="this.setCustomValidity('Invalid password format! The password contains invalid characters OR length! Example: 123qwe, qwerty1')" oninput="this.setCustomValidity('')" required/>
                        <input type="submit" value="Зарегестрироваться"/>
                    </form>
                    <p><a href="#zatemnenie">Помощь</a>  &nbsp;|&nbsp;<a href="${pageContext.servletContext.contextPath}/authorisation">Войти&nbsp;<span class="fontawesome-arrow-right"></span></a>

                    </p>
                </fieldset>
            </div>
        </aside><!-- .right-sidebar -->
    </div><!-- .middle-->
</div>
</body>
</html>
