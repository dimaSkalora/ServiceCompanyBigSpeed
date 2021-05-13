<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFBaseProcesses</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="workflow/wfBaseProcesses/wfBaseProcess">add WFBaseProcess</a></li>
        </ul>
        <ul>
            <li><a href="workflow/wfBaseProcesses/newFilter" class="btnSearchBy">Search by WFBaseProcess</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><spring:message code="workflow.wfBaseProcess.id"/></th>
                <th><spring:message code="workflow.wfBaseProcess.name"/></th>
                <th><spring:message code="workflow.wfBaseProcess.description"/></th>
                <th><spring:message code="workflow.wfBaseProcess.wfServiceId"/></th>
                <th><spring:message code="workflow.wfBaseProcess.wfBaseProcessTypeId"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${wfBaseProcesses}" var="wfBaseProcess">
                <jsp:useBean id="wfBaseProcess" scope="page" type="org.speed.big.company.service.model.workflow.WFBaseProcess"/>
                <tr>
                    <td><a href="workflow/wfBaseProcesses/getData/${wfBaseProcess.id}"/><c:out value="${wfBaseProcess.id}"/></td>
                    <td><c:out value="${wfBaseProcess.name}"/></td>
                    <td><c:out value="${wfBaseProcess.description}"/></td>
                    <td><c:out value="${wfBaseProcess.wfServiceId.id}"/></td>
                    <td><c:out value="${wfBaseProcess.wfBaseProcessTypeId.id}"/></td>
                    <td><a href="workflow/wfBaseProcesses/update/${wfBaseProcess.id}"/><spring:message code="app.update"/></td>
                    <td><a href="workflow/wfBaseProcesses/delete?id=${wfBaseProcess.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
