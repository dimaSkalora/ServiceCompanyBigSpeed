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
            <li><a href="wfPackageStatuses"><spring:message code="app.workflow.wfPackageStatuses"/></a></li>
            <li><a href="wfServices"><spring:message code="app.workflow.wfServices"/></a></li>
            <li><a href="wfPackages"><spring:message code="app.workflow.wfPackages"/></a></li>
        </ul>
    </div>
</div>
</body>
</html>
