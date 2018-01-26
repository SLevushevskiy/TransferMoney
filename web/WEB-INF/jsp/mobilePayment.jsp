<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="mobilePayment" />
<head>
    <title><fmt:message key="title.payment.mobile" bundle="${bundle}"/></title>
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
            <form action="mobilePayment" method="post">
                <input type="hidden" name="paymentName" value="3" />
                <input type="text" value="<fmt:message key="title.payment.mobile" bundle="${bundle}"/>" disabled/>
                <select name="accountChoose">
                    <option disabled selected ><fmt:message key="label.account.choose" bundle="${bundle}"/></option>
                    <c:forEach items="${accountList}" var="accountN">
                        <option value="${accountN.getIdAccount()}">${accountN.getAccountNameDTO().name} (${accountN.getIdAccount()})  ${accountN.getAmound()}</option>
                    </c:forEach>
                </select>
                <input type="text" name="mobileNum" placeholder="<fmt:message key="label.payment.mobile" bundle="${bundle}"/>" pattern="\^+380[0-9]{9}$\"
                       oninvalid="this.setCustomValidity('Please input number phone with format +380.. ')" oninput="this.setCustomValidity('')" required/>
                <input type="text" name="paymentTotal" placeholder="<fmt:message key="label.payment.total" bundle="${bundle}"/>" required/>
                <input type="text" name="paymentDescription" placeholder="<fmt:message key="label.payment.description" bundle="${bundle}"/>" required/>
                <input type="submit" value="<fmt:message key="window.close" bundle="${bundle}"/>"/>
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
