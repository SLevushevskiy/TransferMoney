<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="adminAccountList" />
<head>
    <title><fmt:message key="title.account.list.user" bundle="${bundle}"/></title>
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
            <form action="adminAccountList" method="get">
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
                                <form action="/adminAccountList" method="post">
                                    <input type="hidden" name="accountChoose" value="${account.getIdAccount()}">
                                    <c:if test="${account.getAccountStatusDTO().status eq 'blocked' or account.getAccountStatusDTO().status eq 'reqActive'}">
                                        <input type="hidden" name="status" value="active">
                                        <input type="submit" value="<fmt:message key="button.unblock" bundle="${bundle}"/>" >
                                    </c:if>
                                    <c:if test="${account.getAccountStatusDTO().status eq 'active'}">
                                        <input type="hidden" name="status" value="blocked">
                                        <input type="submit" value="<fmt:message key="button.block" bundle="${bundle}"/>" >
                                    </c:if>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
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