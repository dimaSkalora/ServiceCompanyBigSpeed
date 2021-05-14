<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WorkFlow Process Status</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="workflow/wfProcessStatuses/wfProcessStatus">add WorkFlow Process Status</a></li>
        </ul>
        <ul>
            <li><a href="workflow/wfProcessStatuses/wfProcessStatusFilter" class="btnSearchBy" role="button">Search by WorkFlow Process Status</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-striped table-dark">
            <thead>
            <tr>
                <th><spring:message code="workflow.wfProcessStatus.id"/></th>
                <th><spring:message code="workflow.wfProcessStatus.name"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${wfProcessStatuses}" var="wfProcessStatus">
                <jsp:useBean id="wfProcessStatus" scope="page" type="org.speed.big.company.service.model.workflow.WFProcessStatus"/>
                <tr>
                    <td><a href="workflow/wfProcessStatuses/get/${wfProcessStatus.id}"/><c:out value="${wfProcessStatus.id}"/></td>
                    <td><c:out value="${wfProcessStatus.name}"/></td>
                    <td><a href="workflow/wfProcessStatuses/update/${wfProcessStatus.id}"/><spring:message code="app.update"/></td>
                    <td><a href="workflow/wfProcessStatuses/delete?id=${wfProcessStatus.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
