<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="adminUserList" />
<head>
    <title><fmt:message key="title.admin.user.list" bundle="${bundle}"/></title>
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
            <c:if test="${empty userList}">
                <fmt:message key="label.users.empty" bundle="${bundle}"/>
            </c:if>
            <c:if test="${not empty userList}">
                <table>
                    <tr>
                        <th><fmt:message key="label.users.id" bundle="${bundle}"/></th>
                        <th><fmt:message key="label.users.name" bundle="${bundle}"/></th>
                        <th><fmt:message key="label.users.surname" bundle="${bundle}"/></th>
                        <th><fmt:message key="label.users.email" bundle="${bundle}"/></th>
                        <th><fmt:message key="label.users.rank" bundle="${bundle}"/></th>
                        <th><fmt:message key="label.users.status" bundle="${bundle}"/></th>
                    </tr>
                    <c:forEach items="${userList}" var="user">
                        <tr>
                            <td>${user.getIdUser()}</td>
                            <td>${user.getName()}</td>
                            <td>${user.getSurname()}</td>
                            <td>${user.getEmail()}</td>
                            <td>${user.getUserRoleDTO().rank}</td>
                            <td>${user.getUserStatusDTO().status}</td>
                            <td>
                                <form action="/adminAccountList" method="get">
                                    <input type="hidden" name="userChoose" value="${user.getIdUser()}">
                                    <input type="submit" value="<fmt:message key="label.accounts" bundle="${bundle}"/>" >
                                </form>
                            </td>
                            <td>
                                <form action="/userList" method="post">
                                    <input type="hidden" name="userChoose" value="${user.getIdUser()}">
                                    <c:if test="${user.getUserStatusDTO().status eq 'blocked'}">
                                        <input type="hidden" name="status" value="active">
                                        <input type="submit" value="<fmt:message key="button.unblock" bundle="${bundle}"/>" >
                                    </c:if>
                                    <c:if test="${user.getUserStatusDTO().status eq 'active'}">
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