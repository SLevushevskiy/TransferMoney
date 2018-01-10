<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transfer Money</title>
    <style>
        body {
            background: url(/assets/images/error.jpg) no-repeat center fixed;
        }
    </style>
</head>
<body>
<c:if test="${sessionScope.blocked}">
    <script>
        alert("Ваш аккаунт заблокирован обратитесь к администратору. \nSergey Levushevskiy s.levushevskiy@yandex.ru ");
    </script>
</c:if>
</body>
</html>
