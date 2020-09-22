<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title>Role</title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="roles/role">add role</a></li>
        </ul>
        <ul>
            <li><a href="roles/filter" class="btnSearchBy">Search by Role</a> </li>
        </ul>
    </div>
    <div>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th><spring:message code="role.id"/></th>
                <th><spring:message code="role.name"/></th>
                <th><spring:message code="role.description"/></th>
                <th><spring:message code="role.roleTypeId"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${roles}" var="role">
                <jsp:useBean id="role" scope="page" type="org.speed.big.company.service.model.Role"/>
                <tr>
                    <td><a href="roles/getData/${role.id}"/><c:out value="${roleType.id}"/></td>
                    <td><c:out value="${role.name}"/></td>
                    <td><c:out value="${role.description}"/></td>
                    <td><c:out value="${role.roleTypeId}"/></td>
                    <td><a href="roles/update/${role.id}"/><spring:message code="app.update"/></td>
                    <td><a href="roles/delete?id=${role.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
