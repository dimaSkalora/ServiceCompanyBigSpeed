<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WorkFlow Group</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="wfGroups/wfGroup">add WorkFlow Group</a></li>
        </ul>
        <ul>
            <li><a href="wfGroups/newFilter" class="btnSearchBy" role="button">Search by WorkFlow Group</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-striped table-dark">
            <thead>
            <tr>
                <th><spring:message code="workflow.wfGroup.id"/></th>
                <th><spring:message code="workflow.wfGroup.name"/></th>
                <th><spring:message code="workflow.wfGroup.description"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${wfGroups}" var="wfGroup">
                <jsp:useBean id="wfGroup" scope="page" type="org.speed.big.company.service.model.workflow.WFGroup"/>
                <tr>
                    <td><a href="wfGroups/get/${wfGroup.id}"/><c:out value="${wfGroup.id}"/></td>
                    <td><c:out value="${wfGroup.name}"/></td>
                    <td><c:out value="${wfGroup.description}"/></td>
                    <td><a href="wfGroups/update/${wfGroup.id}"/><spring:message code="app.update"/></td>
                    <td><a href="wfGroups/delete?id=${wfGroup.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
