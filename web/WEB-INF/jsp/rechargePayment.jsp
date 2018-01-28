<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="rechargePayment" />
<head>
    <title><fmt:message key="title.payment.recharge" bundle="${bundle}"/></title>
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
            <h2><fmt:message key="title.payment.recharge" bundle="${bundle}"/></h2>
            <form action="rechargePayment" method="post">
                <input type="hidden" name="paymentName" value="1" />
                <select name="accountChoose" required>
                    <option disabled selected ><fmt:message key="label.account.choose" bundle="${bundle}"/></option>
                    <c:forEach items="${accountList}" var="accountN">
                        <option value="${accountN.getIdAccount()}">${accountN.getAccountNameDTO().name} (${accountN.getIdAccount()})  ${accountN.getAmound()}</option>
                    </c:forEach>
                </select>
                <input type="text" name="paymentTotal" placeholder="<fmt:message key="label.payment.total" bundle="${bundle}"/>" pattern="\d+(\.\d{0,2})?"
                       oninvalid="this.setCustomValidity('Total with format #.##')" oninput="this.setCustomValidity('')"  required/>
                <input type="text" name="paymentDescription" placeholder="<fmt:message key="label.payment.description" bundle="${bundle}"/>" pattern="^[а-яА-ЯёЁa-zA-Z0-9 ]+$"
                       oninvalid="this.setCustomValidity('Please use only latin or cyryllic letter and numbers')" oninput="this.setCustomValidity('')" required/>
                <input type="submit" value="<fmt:message key="label.payment.submit" bundle="${bundle}"/> "/>
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
