<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE HTML>
<html>
<head>
    <title>Подтверждение платежа</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" href="/assets/css/window.css" media="screen" type="text/css" />
</head>
<body>

<!-- Header -->
<%@ include file="/WEB-INF/tags/header.jspf" %>
<!-- WindowError -->
<%@ include file="/WEB-INF/tags/windowError.jspf" %>
<!-- Main -->
<div id="main">

    <!-- Intro -->
    <section id="top" class="one dark cover">
        <div class="container">
            <table>
                <tr>
                    <td><span class="icon fa-credit-card-alt"></span></td>
                    <th>${payment.getAccountDTO().idAccount}</th>
                    <th>${payment.getAccountDTO().getAccountNameDTO().name}</th>
                    <th>${payment.getAccountDTO().amound}</th>
                </tr>
            </table>
            <ul>
                <li>${payment.getPaymentNameDTO().paymentName}</li>
                <c:if test="${not empty mobileNum}">
                    <li>${mobileNum}</li>
                </c:if>
                <c:if test="${not empty accountId}">
                    <li>На счет [${accountId}]</li>
                </c:if>
                <li>${payment.getDescription()}</li>
                <li>${payment.getTotal()}</li>
                <li>${payment.getDatePayment()}</li>
                <li>${payment.getPaymentStatusDTO().status}</li>
            </ul>
            <form action="confirmPayment" method="post">
                <label>Для подтверждения платежа введите пароль от учетной записи.</label>
                <input type="password" name="password" placeholder="Введите пароль" pattern="^[a-zA-Z0-9]{6,15}$"
                       oninvalid="this.setCustomValidity('Invalid password format! The password contains invalid characters OR length! Example: 123qwe, qwerty1')" oninput="this.setCustomValidity('')" required/>
                <input type="password" name="confirm" placeholder="Подтвердите пароль" pattern="^[a-zA-Z0-9]{6,15}$"
                       oninvalid="this.setCustomValidity('Invalid password format! The password contains invalid characters OR length! Example: 123qwe, qwerty1')" oninput="this.setCustomValidity('')" required/>
                <input type="submit" value="Готово "/>
            </form>
        </div>
    </section>
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
