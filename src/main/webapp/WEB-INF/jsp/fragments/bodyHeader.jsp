<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="home"><spring:message code="app.home"/></a>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">List</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="users"><spring:message code="app.users"/></a>
                    <a class="dropdown-item" href="roleTypes"><spring:message code="app.roleTypes"/></a>
                    <a class="dropdown-item" href="roles"><spring:message code="app.roles"/></a>
                    <a class="dropdown-item" href="userRoles"><spring:message code="app.userRoles"/></a>
                    <a class="dropdown-item" href="workflow/wfPackageStatuses"><spring:message code="app.workflow.wfPackageStatuses"/></a>
                    <a class="dropdown-item" href="workflow/wfServices"><spring:message code="app.workflow.wfServices"/></a>
                    <a class="dropdown-item" href="workflow/wfPackages"><spring:message code="app.workflow.wfPackages"/></a>
                    <a class="dropdown-item" href="workflow/wfBaseProcessTypes"><spring:message code="app.workflow.wfBaseProcessTypes"/></a>
                    <a class="dropdown-item" href="workflow/wfBaseProcesses"><spring:message code="app.workflow.wfBaseProcesses"/></a>
                    <a class="dropdown-item" href="workflow/wfProcessStatuses"><spring:message code="app.workflow.wfProcessStatuses"/></a>
                    <a class="dropdown-item" href="workflow/wfProcesses"><spring:message code="app.workflow.wfProcesses"/></a>
                    <a class="dropdown-item" href="workflow/wfGroups"><spring:message code="app.workflow.wfGroups"/></a>
                    <a class="dropdown-item" href="workflow/wfProcessStates"><spring:message code="app.workflow.wfProcessStates"/></a>
                    <a class="dropdown-item" href="workflow/wfBaseProcessItems"><spring:message code="app.workflow.wfBaseProcessItems"/></a>
                    <a class="dropdown-item" href="workflow/wfProcessMovements"><spring:message code="app.workflow.wfProcessMovements"/></a>
                    <a class="dropdown-item" href="workflow/managerWFProcessMovements"><spring:message code="app.workflow.managerWFProcessMovements"/></a>
                 </div>
            </li>
            <a class="navbar-brand" href="restView"><spring:message code="app.rest"/></a>
            <a class="navbar-brand" href="workflow"><spring:message code="app.workflow"/></a>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <a class="glyphicon glyphicon-log-out" href="logout">==>LOGOUT<==</a>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"> (__${pageContext.response.locale}__)<b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a></li>
                    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a></li>
                    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=uk">Український</a></li>
<%--                    <li><a href="?lang=en">English</a></li>
                    <li><a href="?lang=ru">Русский</a></li>--%>
                </ul>
            </li>
        </ul>
    </div>
</nav>

