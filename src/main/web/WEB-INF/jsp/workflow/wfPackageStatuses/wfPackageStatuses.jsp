<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WorkFlow Package Status</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="wfPackageStatuses/wfPackageStatus">add WorkFlow Package Status</a></li>
        </ul>
        <ul>
            <li><a href="wfPackageStatuses/wfPackageStatusFilter" class="btnSearchBy" role="button">Search by WorkFlow Package Status</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-striped table-dark">
            <thead>
            <tr>
                <th><spring:message code="wfPackageStatus.id"/></th>
                <th><spring:message code="wfPackageStatus.name"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${wfPackageStatuses}" var="wfPackageStatus">
                <jsp:useBean id="wfPackageStatus" scope="page" type="org.speed.big.company.service.model.workflow.WFPackageStatus"/>
                <tr>
                    <td><a href="wfPackageStatuses/get/${wfPackageStatus.id}"/><c:out value="${wfPackageStatus.id}"/></td>
                    <td><c:out value="${wfService.name}"/></td>
                    <td><a href="wfPackageStatuses/update/${wfPackageStatus.id}"/><spring:message code="app.update"/></td>
                    <td><a href="wfPackageStatuses/delete?id=${wfPackageStatus.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
