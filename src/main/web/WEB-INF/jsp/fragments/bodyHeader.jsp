<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href=""><spring:message code="app.home"/></a>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">List</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="users"><spring:message code="app.users"/></a>
                    <a class="dropdown-item" href="roleTypes"><spring:message code="app.roleTypes"/></a>
                    <a class="dropdown-item" href="roles"><spring:message code="app.roles"/></a>
                    <a class="dropdown-item" href="userRoles"><spring:message code="app.userRoles"/></a>
                 </div>
            </li>
            <a class="navbar-brand" href="restView"><spring:message code="app.rest"/></a>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale}<b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a></li>
                    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a></li>
<%--                    <li><a href="?lang=en">English</a></li>
                    <li><a href="?lang=ru">Русский</a></li>--%>
                </ul>
            </li>
        </ul>
    </div>
</nav>

