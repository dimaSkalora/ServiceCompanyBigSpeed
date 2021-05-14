<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WorkFlow Service</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="workflow/wfServices/wfService">add WorkFlow Service</a></li>
        </ul>
        <ul>
            <li><a href="workflow/wfServices/wfsFilter" class="btnSearchBy" role="button">Search by WorkFlow Service</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-striped table-dark">
            <thead>
            <tr>
                <th><spring:message code="workflow.wfService.id"/></th>
                <th><spring:message code="workflow.wfService.name"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${wfServices}" var="wfService">
                <jsp:useBean id="wfService" scope="page" type="org.speed.big.company.service.model.workflow.WFService"/>
                <tr>
                    <td><a href="workflow/wfServices/get/${wfService.id}"/><c:out value="${wfService.id}"/></td>
                    <td><c:out value="${wfService.name}"/></td>
                    <td><a href="workflow/wfServices/update/${wfService.id}"/><spring:message code="app.update"/></td>
                    <td><a href="workflow/wfServices/delete?id=${wfService.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
