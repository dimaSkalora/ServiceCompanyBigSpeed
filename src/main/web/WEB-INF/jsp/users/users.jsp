<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title>Users</title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="users/user">add user</a></li>
        </ul>
        <ul>
            <li><a href="users/filter" class="btnSearchBy">Search by User</a> </li>
        </ul>
    </div>
    <div>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th><spring:message code="user.name"/></th>
                <th><spring:message code="user.email"/></th>
                <th><spring:message code="user.password"/></th>
                <th><spring:message code="user.phone"/></th>
                <th><spring:message code="user.registered"/></th>
                <th><spring:message code="user.enabled"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" scope="page" type="org.speed.big.company.service.model.User"/>
                <tr>
                    <td><a href="users/get/${user.id}"/><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <td><c:out value="${user.phone}"/></td>
                    <td><c:out value="${user.registered}"/></td>
                    <td><c:out value="${user.enabled}"/></td>
                    <td><a href="users/update/${user.id}"/><spring:message code="app.update"/></td>
                    <td><a href="users/delete?id=${user.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
