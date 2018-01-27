<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="accountAdd" />
<head>
    <title><fmt:message key="title.account.add" bundle="${bundle}"/></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" href="/assets/css/window.css" media="screen" type="text/css" />
</head>
<body>

<!-- WindowError -->
<%@ include file="/WEB-INF/tags/windowError.jspf" %>
<!-- Header -->
<%@ include file="/WEB-INF/tags/header.jspf" %>
<!-- Main -->
<div id="main">
    <!-- Intro -->
    <section id="top" class="one dark cover">
        <div class="container">
            <h2><fmt:message key="title.account.add" bundle="${bundle}"/></h2>
            <form action="accountAdd" method="post">
                <select name="accountName">
                    <option disabled selected ><fmt:message key="label.account.choose" bundle="${bundle}"/></option>
                    <c:forEach items="${accountNameList}" var="accountN">
                    <option value="${accountN.getIdAccountName()}">${accountN.getName()}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="<fmt:message key="label.account.add" bundle="${bundle}"/>"/>
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
