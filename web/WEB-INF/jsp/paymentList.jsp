<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="paymentList" />
<head>
    <title><fmt:message key="title.payment.list" bundle="${bundle}"/></title>
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
                    <th><fmt:message key="label.account.id" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.account.name" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.account.amound" bundle="${bundle}"/></th>
                </tr>
                <tr>
                    <td><span class="icon fa-credit-card-alt"></span></td>
                    <th>${accountChoose}</th>
                    <th>${accountName}</th>
                    <th>${amound}</th>
                </tr>
            </table>

            <c:if test="${empty paymentList}">
                <fmt:message key="label.operation.empty" bundle="${bundle}"/>
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
                    <th><fmt:message key="label.payment.id" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.payment.type" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.payment.description" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.payment.total" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.payment.date" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.payment.status" bundle="${bundle}"/></th>
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
                        <td>
                        <form action="/savePdfReport" method="get">
                            <input type="hidden" name="payment" value="${payment.getIdPayment()}">
                            <input type="submit" value="<fmt:message key="button.save.report" bundle="${bundle}"/>">
                        </form>
                        </td>
                    </tr>
                </c:forEach>
                </c:if>
            </table>
        </div>
    </section>
</div>

<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<%--<script src="assets/js/jquery.scrolly.min.js"></script>--%>
<%--<script src="assets/js/jquery.scrollzer.min.js"></script>--%>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<%--<script src="assets/js/main.js"></script>--%>

</body>
</html>