<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFProcess</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="wfProcesses/wfProcess">add WFProcess</a></li>
        </ul>
        <ul>
            <li><a href="wfProcesses/newFilter" class="btnSearchBy">Search by WFProcess</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><spring:message code="workflow.wfProcess.id"/></th>
                <th><spring:message code="workflow.wfProcess.startDate"/></th>
                <th><spring:message code="workflow.wfProcess.finalDate"/></th>
                <th><spring:message code="workflow.wfProcess.description"/></th>
                <th><spring:message code="workflow.wfProcess.dateEdit"/></th>
                <th><spring:message code="workflow.wfProcess.userEdit"/></th>
                <th><spring:message code="workflow.wfProcess.wfPackageId"/></th>
                <th><spring:message code="workflow.wfProcess.wfBaseProcessId"/></th>
                <th><spring:message code="workflow.wfProcess.wfProcessStatusId"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${wfProcesses}" var="wfProcess">
                <jsp:useBean id="wfProcess" scope="page" type="org.speed.big.company.service.model.workflow.WFProcess"/>
                <tr>
                    <td><a href="wfProcesses/getData/${wfProcess.id}"/><c:out value="${wfProcess.id}"/></td>
                    <td><c:out value="${wfProcess.startDate}"/></td>
                    <td><c:out value="${wfProcess.finalDate}"/></td>
                    <td><c:out value="${wfProcess.description}"/></td>
                    <td><c:out value="${wfProcess.dateEdit}"/></td>
                    <td><c:out value="${wfProcess.userEdit}"/></td>
                    <td><c:out value="${wfProcess.wfPackageId}"/></td>
                    <td><c:out value="${wfProcess.wfBaseProcessId}"/></td>
                    <td><c:out value="${wfProcess.wfProcessStatusId}"/></td>
                    <td><a href="wfProcesses/update/${wfProcess.id}"/><spring:message code="app.update"/></td>
                    <td><a href="wfProcesses/delete?id=${wfProcess.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
