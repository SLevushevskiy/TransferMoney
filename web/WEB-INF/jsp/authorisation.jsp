<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8"/>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" var="bundle"/>

<html>
<head>
    <title><fmt:message key="title.authorisation" bundle="${bundle}"/></title>
    <link rel="stylesheet" href="assets/css/style.css" media="screen" type='text/css' />
    <link rel="stylesheet" href="assets/css/window.css" media="screen" type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
</head>

<body>

<div class="wrapper">

    <header>
        <div class="wrapper">
            <a href="#"><img src="assets/images/logo.jpg" class="logo" alt="" title=""/><h1>Transfer Money</h1></a>
        </div>
    </header><!--  end header section  -->
    <!-- WindowError -->
    <%@ include file="/WEB-INF/tags/windowError.jspf" %>

    <div class="middle">
        <aside class="right-sidebar">
            <div id="login-form">
                <h2><fmt:message key="title.authorisation" bundle="${bundle}"/></h2>
                <fieldset>
                    <form action="authorisation" method="post" >
                        <input id="fstLine" type="email" name="email" placeholder="<fmt:message key="authorisation.email" bundle="${bundle}"/>"  required/>
                        <input id="lstLine" type="password" name="password" placeholder="<fmt:message key="authorisation.password" bundle="${bundle}"/>" required/>
                        <input type="submit" value="<fmt:message key="authorisation.button" bundle="${bundle}"/>"/>
                    </form>
                    <div class="help">
                        <form id="reg" action="localization" method="GET">
                            <input type="hidden" name="from" value="authorisation">
                            <select id="language" name="local" onchange="submit()">
                                <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
                                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
                            </select>
                        </form>
                    <p><a href="${pageContext.servletContext.contextPath}/registration"><fmt:message key="title.registration" bundle="${bundle}"/>&nbsp;<span class="fontawesome-arrow-right"></span></a>
                    </p>
                    </div>
                </fieldset>
            </div>
        </aside><!-- .right-sidebar -->
    </div><!-- .middle-->
</div>
</body>
</html>
