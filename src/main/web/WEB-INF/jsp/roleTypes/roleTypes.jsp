<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title>Role Types</title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="roleTypes/roleType">add roleType</a></li>
        </ul>
        <ul>
            <li><a href="roleTypes/filter" class="btnSearchBy" role="button">Search by Role Type</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-striped table-dark">
            <thead>
            <tr>
                <th><spring:message code="roleType.id"/></th>
                <th><spring:message code="roleType.name"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${roleTypes}" var="roleType">
                <jsp:useBean id="roleType" scope="page" type="org.speed.big.company.service.model.RoleType"/>
                <tr>
                    <td><a href="roleTypes/get/${roleType.id}"/><c:out value="${roleType.id}"/></td>
                    <td><c:out value="${roleType.name}"/></td>
                    <td><a href="roleTypes/update/${roleType.id}"/><spring:message code="app.update"/></td>
                    <td><a href="roleTypes/delete?id=${roleType.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
