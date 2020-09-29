<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title>User Role</title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="userRoles/userRole">add userRole</a></li>
        </ul>
        <ul>
            <li><a href="userRoles/filter" class="btnSearchBy">Search by User Role</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-sm">
            <thead>
            <tr>
                <th scope="col"><spring:message code="userRole.id"/></th>
                <th scope="col"><spring:message code="userRole.userId"/></th>
                <th scope="col"><spring:message code="userRole.roleId"/></th>
                <th scope="col"><spring:message code="userRole.dateTime"/></th>
                <th scope="col"><spring:message code="userRole.comment"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${userRoles}" var="userRole">
                <jsp:useBean id="userRole" scope="page" type="org.speed.big.company.service.model.UserRole"/>
                <tr>
                    <td scope="row"><a href="userRoles/getData/${userRole.id}"/><c:out value="${userRole.id}"/></td>
                    <td><c:out value="${userRole.userId} [${userRole.userId.name}]"/></td>
                    <td><c:out value="${userRole.roleId} [${userRole.roleId.name}]"/></td>
                    <td><c:out value="${userRole.dateTime}"/></td>
                    <td><c:out value="${userRole.comment}"/></td>
                    <td><a href="userRoles/update/${userRole.id}"/><spring:message code="app.update"/></td>
                    <td><a href="userRoles/delete?id=${userRole.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
