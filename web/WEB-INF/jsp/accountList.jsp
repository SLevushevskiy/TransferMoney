<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML>

<html>
<head>
    <title>Transfer Money</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="assets/css/main.css" />
</head>
<body>

<!-- Header -->
<div id="header">

    <div class="top">

        <!-- Logo -->
        <div id="logo">
            <a href="${pageContext.servletContext.contextPath}/logOut"><span id="outSign" class="icon fa-sign-out"></span></a>

            <h1 id="title">${sessionScope.user.name} ${sessionScope.user.surname}</h1></br>
            <p>${sessionScope.user.email}</p>
        </div>

        <!-- Nav -->
        <nav id="nav">

            <ul>
                <li><a href="#top" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Главная</span></a></li>
                <li><a href="${pageContext.servletContext.contextPath}/accountList" id="accounts-link" class="skel-layers-ignoreHref"><span class="icon fa-credit-card">Счета</span></a></li>
                <li><a href="#payments" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-money">Платежи</span></a></li>
                <li><a href="${pageContext.servletContext.contextPath}/main" id="contact-link" class="skel-layers-ignoreHref"><span class="icon fa-user">Личный кабинет</span></a></li>
            </ul>
        </nav>

    </div>

    <div class="bottom">

        <!-- Social Icons -->
        <ul class="icons">
            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
            <li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
            <li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
            <li><a href="#" class="icon fa-envelope"><span class="label">Email</span></a></li>
        </ul>

    </div>

</div>

<!-- Main -->
<div id="main">

    <!-- Intro -->
    <section id="top" class="one dark cover">
        <div class="container">
            <table>
                <c:forEach items="${accountList}" var="account">
                <tr>
                    <td><span class="icon fa-credit-card-alt"></span></td>
                    <td>${account.getAccountNameDTO().name}</td>
                    <td>${account.getIdAccount()}</td>
                    <td>${account.getAmound()}</td>
                    <td>${account.getEndDate()}</td>
                    <td>${account.getAccountStatusDTO().status}</td>
                </tr>
                </c:forEach>
            </table>
            <ul>
                <li><a href="${pageContext.servletContext.contextPath}/accountAdd"><span class="icon fa-plus-circle"> Добавить счет   </span></a></li>
            </ul>
        </div>
    </section>
</div>

<!-- Footer -->
<div id="footer">

    <!-- Copyright -->
    <ul class="copyright">
        <li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
    </ul>

</div>

<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery.scrolly.min.js"></script>
<script src="assets/js/jquery.scrollzer.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="assets/js/main.js"></script>

</body>
</html>