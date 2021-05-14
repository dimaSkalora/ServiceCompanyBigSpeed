<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFBaseProcessItems</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="workflow/wfBaseProcessItems/wfBaseProcessItem">add WFBaseProcessItem</a></li>
        </ul>
        <ul>
            <li><a href="workflow/wfBaseProcessItems/newFilter" class="btnSearchBy">Search by WFBaseProcessItem</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><spring:message code="workflow.wfBaseProcessItem.id"/></th>
                <th><spring:message code="workflow.wfBaseProcessItem.stateFromId"/></th>
                <th><spring:message code="workflow.wfBaseProcessItem.stateToId"/></th>
                <th><spring:message code="workflow.wfBaseProcessItem.baseProcessId"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${wfBaseProcessItems}" var="wfBaseProcessItem">
                <jsp:useBean id="wfBaseProcessItem" scope="page" type="org.speed.big.company.service.model.workflow.WFBaseProcessItem"/>
                <tr>
                    <td><a href="workflow/wfBaseProcessItems/getData/${wfBaseProcessItem.id}"/><c:out value="${wfBaseProcessItem.id}"/></td>
                    <td><c:out value="${wfBaseProcessItem.stateFromId.id}"/></td>
                    <td><c:out value="${wfBaseProcessItem.stateToId.id}"/></td>
                    <td><c:out value="${wfBaseProcessItem.baseProcessId.id} ${wfBaseProcessItem.baseProcessId.name}"/></td>
                    <td><a href="workflow/wfBaseProcessItems/update/${wfBaseProcessItem.id}"/><spring:message code="app.update"/></td>
                    <td><a href="workflow/wfBaseProcessItems/delete?id=${wfBaseProcessItem.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
