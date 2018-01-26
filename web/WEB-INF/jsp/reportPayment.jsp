<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="reportPayment" />
<head>
    <title><fmt:message key="title.payment.report" bundle="${bundle}"/></title>
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
                <label><fmt:message key="label.payment.id" bundle="${bundle}"/>: </label><li>${payment.getIdPayment()}</li>
                <label><fmt:message key="label.payment.name" bundle="${bundle}"/>: </label><li>${payment.getPaymentNameDTO().paymentName}</li>
                <c:if test="${not empty mobileNum}">
                    <label><fmt:message key="label.payment.phone.num" bundle="${bundle}"/>: </label><li>${mobileNum}</li>
                </c:if>
                <c:if test="${not empty accountId}">
                    <label><fmt:message key="label.payment.account.id" bundle="${bundle}"/>: </label><li>${accountId}</li>
                </c:if>
                <label><fmt:message key="label.payment.description" bundle="${bundle}"/>: </label><li>${payment.getDescription()}</li>
                <label><fmt:message key="label.payment.total" bundle="${bundle}"/>: </label><li>${payment.getTotal()}</li>
                <label><fmt:message key="label.payment.date" bundle="${bundle}"/>: </label><li>${payment.getDatePayment()}</li>
                <label><fmt:message key="label.payment.status" bundle="${bundle}"/>: </label><li>${payment.getPaymentStatusDTO().status}</li>
            </ul>
            <br>
            <form action="savePdfReport" method="get">
                <input type="hidden" name="payment" value="${payment.getIdPayment()}">
                <input type="submit" value="<fmt:message key="button.save.report" bundle="${bundle}"/>">
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
