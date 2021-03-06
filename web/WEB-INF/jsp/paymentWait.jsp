<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="paymentWait" />
<head>
    <title><fmt:message key="title.payment.wait" bundle="${bundle}"/></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="assets/css/main.css" />
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
            <c:if test="${empty paymentList}">
                <fmt:message key="label.operation.wait.empty" bundle="${bundle}"/>
            </c:if>
            <c:if test="${not empty paymentList}">
            <form action="paymentWait" method="get">
            <!-- PaymentSort -->
            <%@ include file="/WEB-INF/tags/paymentSort.jspf" %>
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
                                <form action="paymentWait" method="post">
                                    <input type="hidden" name="paymentChoose" value="${payment.getIdPayment()}">
                                    <input type="submit" value="<fmt:message key="label.payment.submit" bundle="${bundle}"/>">
                                </form>
                            </td>
                            <td>
                                    <a href="${pageContext.servletContext.contextPath}/paymentDelete?paymentChoose=${payment.getIdPayment()}">
                                        <span class="icon icon fa-trash-o"></span></a>
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
<script src="assets/js/jquery.scrolly.min.js"></script>
<script src="assets/js/jquery.scrollzer.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="assets/js/main.js"></script>

</body>
</html>