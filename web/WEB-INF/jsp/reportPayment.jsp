<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE HTML>
<html>
<head>
    <title>Мобильный платеж</title>
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
            <ul>
                <label>Номер платежа: </label><li>${payment.getIdPayment()}</li>
                <label>Название платежа: </label><li>${payment.getPaymentNameDTO().paymentName}</li>
                <c:if test="${not empty mobileNum}">
                    <label>Номер телефона: </label><li>${mobileNum}</li>
                </c:if>
                <c:if test="${not empty accountId}">
                    <label>Номер счета: </label><li>${accountId}</li>
                </c:if>
                <label>Описание: </label><li>${payment.getDescription()}</li>
                <label>Сумма: </label><li>${payment.getTotal()}</li>
                <label>Дата: </label><li>${payment.getDatePayment()}</li>
                <label>Статус: </label><li>${payment.getPaymentStatusDTO().status}</li>
            </ul>
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
