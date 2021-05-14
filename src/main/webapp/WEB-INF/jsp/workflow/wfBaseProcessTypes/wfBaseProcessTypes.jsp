<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WorkFlow Base Process Type</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="workflow/wfBaseProcessTypes/wfBaseProcessType">add WorkFlow Base Process Type</a></li>
        </ul>
        <ul>
            <li><a href="workflow/wfBaseProcessTypes/newFilter" class="btnSearchBy" role="button">Search by WorkFlow Base Process Type</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-striped table-dark">
            <thead>
            <tr>
                <th><spring:message code="workflow.wfBaseProcessType.id"/></th>
                <th><spring:message code="workflow.wfBaseProcessType.name"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${wfBaseProcessTypes}" var="wfBaseProcessType">
                <jsp:useBean id="wfBaseProcessType" scope="page" type="org.speed.big.company.service.model.workflow.WFBaseProcessType"/>
                <tr>
                    <td><a href="workflow/wfBaseProcessTypes/get/${wfBaseProcessType.id}"/><c:out value="${wfBaseProcessType.id}"/></td>
                    <td><c:out value="${wfBaseProcessType.name}"/></td>
                    <td><a href="workflow/wfBaseProcessTypes/update/${wfBaseProcessType.id}"/><spring:message code="app.update"/></td>
                    <td><a href="workflow/wfBaseProcessTypes/delete?id=${wfBaseProcessType.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
