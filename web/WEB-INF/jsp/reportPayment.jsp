<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8" />
<html>
<head>
    <title>Title</title>
</head>
<body>

<ul>
    <li>${payment.getIdPayment()}</li>
    <li>${payment.getPaymentNameDTO().paymentName}</li>
    <c:if test="${not empty mobileNum}">
        <li>${mobileNum}</li>
    </c:if>
    <c:if test="${not empty accountId}">
        <li>${accountId}</li>
    </c:if>
    <li>${payment.getDescription()}</li>
    <li>${payment.getTotal()}</li>
    <li>${payment.getDatePayment()}</li>
    <li>${payment.getPaymentStatusDTO().status}</li>
</ul>

</body>
</html>
