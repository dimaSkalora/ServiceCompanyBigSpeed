<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Service Company Big Speed</title>
</head>
<body>
&lt;%&ndash;<jsp:include page="fragments/bodyHeader.jsp"/>&ndash;%&gt;

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
            <li><a href="wfProcessStates"><spring:message code="app.workflow.wfProcessStates"/></a></li>
            <li><a href="wfBaseProcessItems"><spring:message code="app.workflow.wfBaseProcessItems"/></a></li>
            <li><a href="wfProcessMovements"><spring:message code="app.workflow.wfProcessMovements"/></a></li>
            <li><a href="managerWFProcessMovements"><spring:message code="app.workflow.managerWFProcessMovements"/></a></li>
        </ul>
    </div>
</div>
</body>
</html>
--%>
<%--<!DOCTYPE html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>ServiceCompanyBigSpeed</title>
    <base href="${pageContext.request.contextPath}/"/>


    <link href="<c:url value="/resources/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <%--<link rel="stylesheet" href="webjars/bootstrap/4.5.0/css/bootstrap.min.css">--%>

    <%--http://stackoverflow.com/a/24070373/548473--%>
    <%--<script type="text/javascript" src="webjars/jquery/3.5.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.5.0/js/bootstrap.min.js" defer></script>--%>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.5.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/bootstrap.min.js"/>" defer></script>
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <h1>Spring Boot JSP Example</h1>
        <h2>Hello ${message}</h2>

        Click on this <strong><a href="next">link</a></strong> to visit another page.
    </div>
</div>

<%--<div>
	<div>
		<h1>Spring Boot JSP Example</h1>
		<h2>Hello ${message}</h2>

		Click on this <strong><a href="next">link</a></strong> to visit another page.
	</div>
</div>--%>
</body>
</html>
