<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Service Company Big Speed</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="users"><spring:message code="app.users"/></a></li>
            <li><a href="roleTypes"><spring:message code="app.roleTypes"/></a></li>
            <li><a href="roles"><spring:message code="app.roles"/></a></li>
            <li><a href="userRoles"><spring:message code="app.userRoles"/></a></li>
            <li><a href="wfPackageStatuses"><spring:message code="app.workflow.wfPackageStatuses"/></a></li>
            <li><a href="wfServices"><spring:message code="app.workflow.wfServices"/></a></li>
            <li><a href="wfPackages"><spring:message code="app.workflow.wfPackages"/></a></li>
            <li><a href="wfBaseProcessTypes"><spring:message code="app.workflow.wfBaseProcessTypes"/></a></li>
            <li><a href="wfBaseProcesses"><spring:message code="app.workflow.wfBaseProcesses"/></a></li>
            <li><a href="wfProcessStatuses"><spring:message code="app.workflow.wfProcessStatuses"/></a></li>
            <li><a href="wfProcesses"><spring:message code="app.workflow.wfProcesses"/></a></li>
            <li><a href="wfGroups"><spring:message code="app.workflow.wfGroups"/></a></li>
        </ul>
    </div>
</div>
</body>
</html>
