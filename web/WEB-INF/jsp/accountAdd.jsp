<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="accountAdd" method="post">
    <input type="hidden" name="accountUserID" value="${sessionScope.user.idUser}">
    <input type="text" name="accountName" placeholder="Имя карты" required/>
    <input type="text" name="amound" required/>

    <input type="submit" value="Зарегестрироваться"/>
</form>
</body>
</html>
