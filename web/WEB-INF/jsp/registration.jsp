<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="accountList" />
<head>
    <title><fmt:message key="title.registration" bundle="${bundle}"/></title>
    <link href='/assets/css/style.css' rel='stylesheet' type='text/css'>
    <link href='/assets/css/window.css' rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <script src="assets/js/confirmPass.js"></script>
</head>

<body>
<div class="wrapper">
    <header>
        <div class="wrapper">
            <a href="#"><img src="assets/images/logo.jpg" class="logo" alt="" title=""/><h1>Transfer Money</h1></a>
        </div>
    </header><!--  end header section  -->
    <div id="zatemnenie">
        <div id="okno">
            <ul>
                <c:forEach items="${errorContainer}" var="error">
                    <li>${error.value}</li>
                </c:forEach>
                <li></li>
                <li>
                    <fmt:message key="registration.help.name" bundle="${bundle}"/>
                </li>
                <li>
                    <fmt:message key="registration.help.email" bundle="${bundle}"/>
                </li>
                <li>
                    <fmt:message key="registration.help.password" bundle="${bundle}"/>
                </li>
                <li>
                    <fmt:message key="registration.help.password.manual" bundle="${bundle}"/>
                </li>
            </ul>
            <a href="#" class="close"><fmt:message key="registration.help.done" bundle="${bundle}"/></a>
        </div>
    </div>

    <div class="middle">
        <aside class="right-sidebar">
            <div id="login-form">
                <h2><fmt:message key="title.registration" bundle="${bundle}"/></h2>
                <fieldset>
                    <form action="registration" onsubmit=" return passwordmatch(this);" method="post">
                        <input id="fstLine" type="text" name="name" placeholder="<fmt:message key="registration.name" bundle="${bundle}"/>" pattern="^[А-ЯЁA-Z]{1}[а-яёa-z]+$"
                               oninvalid="this.setCustomValidity('Invalid name! This should start with a capital letter and not contain extraneous characters! Example: John, Игорь')"
                               oninput="this.setCustomValidity('')" value="${invalidUser.getName()}" required/>
                        <input type="text" name="surname" placeholder="<fmt:message key="registration.surname" bundle="${bundle}"/>" pattern="^[А-ЯЁA-Z]{1}[а-яёa-z]+$"
                               oninvalid="this.setCustomValidity('Invalid surname! This should start with a capital letter and not contain extraneous characters! Example: Carter, Иванов')"
                               oninput="this.setCustomValidity('')" value="${invalidUser.getSurname()}" required/>
                        <input type="email" name="email" placeholder="<fmt:message key="registration.email" bundle="${bundle}"/>" value="${invalidUser.getEmail()}" required/>
                        <input type="password" name="password" placeholder="<fmt:message key="registration.password" bundle="${bundle}"/>" pattern="^[a-zA-Z0-9]{6,15}$"
                               oninvalid="this.setCustomValidity('Invalid password format! The password contains invalid characters OR length! Example: 123qwe, qwerty1')" oninput="this.setCustomValidity('')" required/>
                        <input id="lstLine" type="password" name="confirm" placeholder="<fmt:message key="registration.password.confirm" bundle="${bundle}"/>" pattern="^[a-zA-Z0-9]{6,15}$"
                               oninvalid="this.setCustomValidity('Invalid password format! The password contains invalid characters OR length! Example: 123qwe, qwerty1')" oninput="this.setCustomValidity('')" required/>
                        <input type="submit" value="<fmt:message key="registration.button" bundle="${bundle}"/>"/>
                    </form>
                    <div class="help">
                    <form id="reg" action="localization" method="GET">
                        <input type="hidden" name="from" value="registration">
                        <select id="language" name="local" onchange="submit()">
                            <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="localization.en" bundle="${bundle}"/></option>
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="localization.ru" bundle="${bundle}"/></option>
                        </select>
                    </form>
                    <p>
                        &nbsp;|&nbsp;
                        <a href="#zatemnenie">
                        <fmt:message key="registration.href.help" bundle="${bundle}"/></a>&nbsp;|&nbsp;
                        <a href="${pageContext.servletContext.contextPath}/authorisation">
                            <fmt:message key="registration.href.logIn" bundle="${bundle}"/>&nbsp;
                            <span class="fontawesome-arrow-right"></span></a>
                    </p>
                    </div>
                </fieldset>
            </div>
        </aside><!-- .right-sidebar -->
    </div><!-- .middle-->
</div>
</body>
</html>
