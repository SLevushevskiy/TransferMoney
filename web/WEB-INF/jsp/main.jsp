<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="controller" method="post">
    <input type="text"  value="${sessionScope.user.idUser}">
    <input type="text"  value="${sessionScope.user.name}">
    <input type="text" value="${sessionScope.user.surname}" >
    <input type="text" value="${sessionScope.user.email}" >
    <input type="text" value="${sessionScope.user.userRoleDTO.rank}" >
    <input type="text" value="${sessionScope.user.userStatusDTO.status}" >
    <a href="${pageContext.servletContext.contextPath}/accountAdd">Зарегестрировать счет</a>
</form>
</body>
</html>
