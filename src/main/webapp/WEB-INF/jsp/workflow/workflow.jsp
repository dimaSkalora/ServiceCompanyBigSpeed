<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title><spring:message code="app.workflow"/></title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="workflow/wfPackageStatuses"><spring:message code="app.workflow.wfPackageStatuses"/></a></li>
            <li><a href="workflow/wfServices"><spring:message code="app.workflow.wfServices"/></a></li>
            <li><a href="workflow/wfPackages"><spring:message code="app.workflow.wfPackages"/></a></li>
            <li><a href="workflow/wfBaseProcessTypes"><spring:message code="app.workflow.wfBaseProcessTypes"/></a></li>
            <li><a href="workflow/wfBaseProcesses"><spring:message code="app.workflow.wfBaseProcesses"/></a></li>
            <li><a href="workflow/wfProcessStatuses"><spring:message code="app.workflow.wfProcessStatuses"/></a></li>
            <li><a href="workflow/wfProcesses"><spring:message code="app.workflow.wfProcesses"/></a></li>
            <li><a href="workflow/wfGroups"><spring:message code="app.workflow.wfGroups"/></a></li>
            <li><a href="workflow/wfProcessStates"><spring:message code="app.workflow.wfProcessStates"/></a></li>
            <li><a href="workflow/wfBaseProcessItems"><spring:message code="app.workflow.wfBaseProcessItems"/></a></li>
            <li><a href="workflow/wfProcessMovements"><spring:message code="app.workflow.wfProcessMovements"/></a></li>
            <li><a href="workflow/managerWFProcessMovements"><spring:message code="app.workflow.managerWFProcessMovements"/></a></li>
        </ul>
    </div>
</div>
</body>
</html>
