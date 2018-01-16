<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8" />
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
            <ul>
            <li><a href="${pageContext.servletContext.contextPath}/paymentAdd"><span class="icon fa-plus-circle"> Совершить операцию   </span></a></li>
            </ul>

            <table>
                <tr>
                    <td><span class="icon fa-credit-card-alt"></span></td>
                    <th>${accountName}</th>
                    <th>${accountChoose}</th>
                    <th>${amound}</th>
                </tr>
                <br>
                <tr>
                    <th></th>
                    <th>Номер платежа</th>
                    <th>Тип</th>
                    <th>Описание</th>
                    <th>Сумма</th>
                    <th>Остаток</th>
                    <th>Дата</th>
                    <th>Статус</th>
                </tr>
                <c:forEach items="${paymentList}" var="payment">
                    <tr>
                        <td><span class="icon fa-money"></span></td>
                        <td>${payment.getIdPayment()}</td>
                        <td>${payment.getPaymentTypeDTO().type}</td>
                        <td>${payment.getDescription()}</td>
                        <td>${payment.getTotal()}</td>
                        <td>${payment.getAccountDTO().amound}</td>
                        <td>${payment.getDatePayment()}</td>
                        <td>${payment.getPaymentStatusDTO().status}</td>
                    </tr>
                </c:forEach>
            </table>
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