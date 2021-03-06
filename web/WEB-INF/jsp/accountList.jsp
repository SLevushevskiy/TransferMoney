<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="accountList" />
<head>
    <title><fmt:message key="title.account.list" bundle="${bundle}"/></title>
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
            <c:if test="${empty accountList}">
                <fmt:message key="label.account.empty" bundle="${bundle}"/>
            </c:if>
            <c:if test="${not empty accountList}">
                <form action="accountList" method="get">
                    <!-- AccountSort -->
                    <%@ include file="/WEB-INF/tags/accountSort.jspf" %>
                </form>
            <table>
                <tr>
                    <th></th>
                    <th><fmt:message key="label.account.name" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.account.id" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.account.amound" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.account.date" bundle="${bundle}"/></th>
                    <th><fmt:message key="label.account.status" bundle="${bundle}"/></th>
                </tr>
                <c:forEach items="${accountList}" var="account">
                <tr>
                    <td><span class="icon fa-credit-card-alt"></span></td>
                    <td>${account.getAccountNameDTO().name}</td>
                    <td>${account.getIdAccount()}</td>
                    <td>${account.getAmound()}</td>
                    <td>${account.getEndDate()}</td>
                    <td>${account.getAccountStatusDTO().status}</td>
                    <td>
                        <c:if test="${account.getAccountStatusDTO().status eq 'active'}">
                        <form action="/paymentList" method="post">
                            <input type="hidden" name="accountChoose" value="${account.getIdAccount()}">
                            <input type="submit" value="<fmt:message key="button.history.payments" bundle="${bundle}"/>" >
                        </form>
                        </c:if>
                        <c:if test="${account.getAccountStatusDTO().status eq 'blocked'}">
                            <form action="/accountList" method="post">
                                <input type="hidden" name="accountChoose" value="${account.getIdAccount()}">
                                <input type="hidden" name="status" value="reqActive">
                                <input type="submit" value="<fmt:message key="button.unblock" bundle="${bundle}"/>" >
                            </form>
                        </c:if>
                        <c:if test="${account.getAccountStatusDTO().status eq 'reqActive'}">
                            <fmt:message key="label.wait" bundle="${bundle}"/>
                        </c:if>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:if>
            <ul>
                <li><a href="${pageContext.servletContext.contextPath}/accountAdd"><span class="icon fa-plus-circle"><fmt:message key="label.account.add" bundle="${bundle}"/></span></a></li>
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