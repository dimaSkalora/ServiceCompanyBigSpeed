<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFProcessStates</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="workflow/wfProcessStates/wfProcessState">add WFProcessState</a></li>
        </ul>
        <ul>
            <li><a href="workflow/wfProcessStates/newFilter" class="btnSearchBy">Search by WFProcessState</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><spring:message code="workflow.wfProcessState.id"/></th>
                <th><spring:message code="workflow.wfProcessState.name"/></th>
                <th><spring:message code="workflow.wfProcessState.roleId"/></th>
                <th><spring:message code="workflow.wfProcessState.wfGroupId"/></th>
                <th><spring:message code="workflow.wfProcessState.description"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${wfProcessStates}" var="wfProcessState">
                <jsp:useBean id="wfProcessState" scope="page" type="org.speed.big.company.service.model.workflow.WFProcessState"/>
                <tr>
                    <td><a href="workflow/wfProcessStates/getData/${wfProcessState.id}"/><c:out value="${wfProcessState.id}"/></td>
                    <td><c:out value="${wfProcessState.name}"/></td>
                    <td><c:out value="${wfProcessState.roleId.id} ${wfProcessState.roleId.name}"/></td>
                    <td><c:out value="${wfProcessState.wfGroupId.id} ${wfProcessState.wfGroupId.name}"/></td>
                    <td><c:out value="${wfProcessState.description}"/></td>
                    <td><a href="workflow/wfProcessStates/update/${wfProcessState.id}"/><spring:message code="app.update"/></td>
                    <td><a href="workflow/wfProcessStates/delete?id=${wfProcessState.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
