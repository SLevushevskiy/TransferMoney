<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Transfer Money</title>
    <link rel="stylesheet" href="/assets/css/style.css" media="screen" type="text/css" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
</head>

<body>
<div class="wrapper">

    <header>        <div class="wrapper">
            <a href="#"><img src="assets/images/logo.jpg" class="logo" alt="" title=""/><h1>Transfer Money - онлайн управление вашими деньгами!</h1></a>
        </div>
    </header><!--  end header section  -->

    <div class="middle">

        <aside class="right-sidebar">
            <div id="login-form">
                <h2>Регистрация</h2>
                <fieldset>
                    <form action="registration" method="post">
                        <input id="fstLine" type="text" placeholder="Введите ваше имя" name="name" required/>
                        <input type="text" name="surname" placeholder="Введите вашу фамилию" required/>
                        <input type="email" name="email" placeholder="Введите почту"  required/>
                        <input type="password" name="password" placeholder="Введите пароль" required/>
                        <input id="lstLine" type="password" placeholder="Подтвердите пароль" name="confirm"  required/>
                        <input type="submit" value="Зарегестрироваться"/>
                    </form>
                    <p><a href="#">Забыли пароль?</a>  &nbsp;|&nbsp;<a href="${pageContext.servletContext.contextPath}/authorisation">Войти&nbsp;<span class="fontawesome-arrow-right"></span></a>

                    </p>
                </fieldset>
            </div>
        </aside><!-- .right-sidebar -->
    </div><!-- .middle-->
</div>
</body>
</html>
