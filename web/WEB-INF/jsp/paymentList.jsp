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
            <table class="amound">
                <tr>
                    <td></td>
                    <th>ID</th>
                    <th>Name card</th>
                    <th>Amound</th>
                </tr>
                <tr>
                    <td><span class="icon fa-credit-card-alt"></span></td>
                    <th>${accountChoose}</th>
                    <th>${accountName}</th>
                    <th>${amound}</th>
                </tr>
            </table>

            <c:if test="${empty paymentList}">
                Операции еще не выполнялись.
            </c:if>
            <c:if test="${not empty paymentList}">
            <form action="paymentList" method="get">
            <!-- PaymentSort -->
            <%@ include file="/WEB-INF/tags/paymentSort.jspf" %>
                <input type="hidden" name="accountChoose" value="${accountChoose}">
            </form>
            <table>
                <tr>
                    <th></th>
                    <th>Номер платежа</th>
                    <th>Тип</th>
                    <th>Описание</th>
                    <th>Сумма</th>
                    <th>Дата</th>
                    <th>Статус</th>
                </tr>
                <c:forEach items="${paymentList}" var="payment">
                    <tr>
                        <td><span class="icon fa-money"></span></td>
                        <td>${payment.getIdPayment()}</td>
                        <td>${payment.getPaymentNameDTO().paymentName}</td>
                        <td>${payment.getDescription()}</td>
                        <td>${payment.getTotal()}</td>
                        <td>${payment.getDatePayment()}</td>
                        <td>${payment.getPaymentStatusDTO().status}</td>
                    </tr>
                </c:forEach>
                </c:if>
            </table>
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