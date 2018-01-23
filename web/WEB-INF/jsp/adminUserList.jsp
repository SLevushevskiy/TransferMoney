<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE HTML>
<html>
<head>
    <title>Список пользователей</title>
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
                Еще нет пользователей...
            </c:if>
            <c:if test="${not empty userList}">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>E-mail</th>
                        <th>Роль</th>
                        <th>Статус</th>
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
                                    <input type="submit" value="Cчета" >
                                </form>
                            </td>
                            <td>
                                <form action="/userList" method="post">
                                    <input type="hidden" name="userChoose" value="${user.getIdUser()}">
                                    <c:if test="${user.getUserStatusDTO().status eq 'blocked'}">
                                        <input type="hidden" name="status" value="active">
                                        <input type="submit" value="Разблокировать" >
                                    </c:if>
                                    <c:if test="${user.getUserStatusDTO().status eq 'active'}">
                                        <input type="hidden" name="status" value="blocked">
                                        <input type="submit" value="Заблокировать" >
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