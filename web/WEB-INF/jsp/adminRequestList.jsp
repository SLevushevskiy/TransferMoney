<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="adminRequestList" />
<head>
    <title><fmt:message key="title.account.list.user" bundle="${bundle}"/></title>
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
            <c:if test="${empty accountList}">
                <fmt:message key="title.account.list.user" bundle="${bundle}"/>
            </c:if>
            <c:if test="${not empty accountList}">
            <form action="adminRequestList" method="get">
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
                                <form action="/adminRequestList" method="post">
                                    <input type="hidden" name="accountChoose" value="${account.getIdAccount()}">
                                        <input type="hidden" name="status" value="active">
                                        <input type="submit" value="<fmt:message key="button.unblock" bundle="${bundle}"/>" >
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
