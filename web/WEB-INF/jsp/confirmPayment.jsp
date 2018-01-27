<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="confirmPayment" />
<head>
    <title><fmt:message key="title.payment.confirm" bundle="${bundle}"/></title>
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
            <table class="amound">
                <tr>
                    <td></td>
                    <th><fmt:message key="label.account.id" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.account.name" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.account.amound" bundle="${bundle}"/></th>
                </tr>
                <tr>
                    <td><span class="icon fa-credit-card-alt"></span></td>
                    <th>${payment.getAccountDTO().idAccount}</th>
                    <th>${payment.getAccountDTO().getAccountNameDTO().name}</th>
                    <th>${payment.getAccountDTO().amound}</th>
                </tr>
            </table>
            <ul>
                <li>${payment.getDescription()}</li>
                <li>${payment.getTotal()}</li>
                <li>${payment.getDatePayment()}</li>
                <li>${payment.getPaymentStatusDTO().status}</li>
            </ul>
            <form action="confirmPayment" method="post">
                <label><fmt:message key="label.confirm" bundle="${bundle}"/></label>
                <input type="password" name="password" placeholder="<fmt:message key="registration.password" bundle="${bundle}"/>" pattern="^[a-zA-Z0-9]{6,15}$"
                       oninvalid="this.setCustomValidity('Invalid password format! The password contains invalid characters OR length! Example: 123qwe, qwerty1')" oninput="this.setCustomValidity('')" required/>
                <input type="password" name="confirm" placeholder="<fmt:message key="registration.password.confirm" bundle="${bundle}"/>" pattern="^[a-zA-Z0-9]{6,15}$"
                       oninvalid="this.setCustomValidity('Invalid password format! The password contains invalid characters OR length! Example: 123qwe, qwerty1')" oninput="this.setCustomValidity('')" required/>
                <input type="submit" value="<fmt:message key="registration.help.done" bundle="${bundle}"/>"/>
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
