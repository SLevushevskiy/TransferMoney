<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!-- tagLib -->
<%@ include file="/WEB-INF/tags/tagLib.jspf" %>

<!DOCTYPE HTML>
<html>
<c:set var="fromUrl" scope="request" value="accountList" />
<head>
    <title><fmt:message key="title.authorisation" bundle="${bundle}"/></title>
    <meta charset="utf-8" />
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
                                <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="localization.en" bundle="${bundle}"/></option>
                                <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="localization.ru" bundle="${bundle}"/></option>
                            </select>
                        </form>
                    <p>&nbsp;|&nbsp;<a href="${pageContext.servletContext.contextPath}/registration"><fmt:message key="title.registration" bundle="${bundle}"/>&nbsp;<span class="fontawesome-arrow-right"></span></a>
                    </p>
                    </div>
                </fieldset>
            </div>
        </aside><!-- .right-sidebar -->
    </div><!-- .middle-->
</div>
</body>
</html>
