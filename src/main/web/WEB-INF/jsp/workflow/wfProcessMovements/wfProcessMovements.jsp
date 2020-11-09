<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFProcessMovement</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="wfProcessMovements/wfProcessMovement">add WFProcessMovement</a></li>
        </ul>
        <ul>
            <li><a href="wfProcessMovements/newFilter" class="btnSearchBy">Search by WFProcessMovement</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><spring:message code="workflow.wfProcessMovement.id"/></th>
                <th><spring:message code="workflow.wfProcessMovement.startDateTime"/></th>
                <th><spring:message code="workflow.wfProcessMovement.finalDateTime"/></th>
                <th><spring:message code="workflow.wfProcessMovement.isCompleted"/></th>
                <th><spring:message code="workflow.wfProcessMovement.description"/></th>
                <th><spring:message code="workflow.wfProcessMovement.dateEdit"/></th>
                <th><spring:message code="workflow.wfProcessMovement.userEdit"/></th>
                <th><spring:message code="workflow.wfProcessMovement.userId"/></th>
                <th><spring:message code="workflow.wfProcessMovement.wfPackageId"/></th>
                <th><spring:message code="workflow.wfProcessMovement.wfStateId"/></th>
                <th><spring:message code="workflow.wfProcessMovement.wfProcessId"/></th>
                <th><spring:message code="workflow.wfProcessMovement.wfBaseProcessId"/></th>
                <th><spring:message code="workflow.wfProcessMovement.isLast"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${wfProcessMovements}" var="wfProcessMovement">
                <jsp:useBean id="wfProcessMovement" scope="page" type="org.speed.big.company.service.model.workflow.WFProcessMovement"/>
                <tr>
                    <td><a href="wfProcessMovements/getData/${wfProcessMovement.id}"/><c:out value="${wfProcessMovement.id}"/></td>
                    <td><c:out value="${wfProcessMovement.startDateTime}"/></td>
                    <td><c:out value="${wfProcessMovement.finalDateTime}"/></td>
                    <td><c:out value="${wfProcessMovement.completed}"/></td>
                    <td><c:out value="${wfProcessMovement.description}"/></td>
                    <td><c:out value="${wfProcessMovement.dateEdit}"/></td>
                    <td><c:out value="${wfProcessMovement.userEdit}"/></td>
                    <td><c:out value="${wfProcessMovement.userId}"/></td>
                    <td><c:out value="${wfProcessMovement.wfPackageId}"/></td>
                    <td><c:out value="${wfProcessMovement.wfStateId}"/></td>
                    <td><c:out value="${wfProcessMovement.wfProcessId}"/></td>
                    <td><c:out value="${wfProcessMovement.wfBaseProcessId}"/></td>
                    <td><c:out value="${wfProcessMovement.last}"/></td>
                    <td><a href="wfProcessMovements/update/${wfProcessMovement.id}"/><spring:message code="app.update"/></td>
                    <td><a href="wfProcessMovements/delete?id=${wfProcessMovement.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
