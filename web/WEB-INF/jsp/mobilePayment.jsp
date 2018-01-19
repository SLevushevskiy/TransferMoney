<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8" />
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
<%@ include file="/WEB-INF/tags/header.jspf" %>


<!-- Main -->
<div id="main">

    <!-- Intro -->
    <section id="top" class="one dark cover">
        <div class="container">
            <form action="mobilePayment" method="post">
                <input type="hidden" name="paymentName" value="3" />
                <input type="text" value="Пополнить мобильный" disabled/>
                <select name="accountChoose">
                    <c:forEach items="${accountList}" var="accountN">
                        <option value="${accountN.getIdAccount()}">${accountN.getAccountNameDTO().name} (${accountN.getIdAccount()})  ${accountN.getAmound()}</option>
                    </c:forEach>
                </select>
                <input type="tel" name="mobileNum" placeholder="Введите номер телефона" />
                <input type="text" name="paymentTotal" placeholder="Сумма" />
                <input type="text" name="paymentDescription" placeholder="Описание" />
                <input type="submit" value="Готово "/>
            </form>
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
