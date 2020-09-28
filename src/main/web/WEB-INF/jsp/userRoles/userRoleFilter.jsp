<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title>User Role Filter</title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">

        <c:url var="createOrUpdateUrl" value="/userRoles/filterUserRole" />
        <form method="post" action="${createOrUpdateUrl}" >
            <table border="0" cellpadding="8" cellspacing="0">
                <tr>
                    <td><spring:message code="userRole.id"/></td>
                    <td><input type="number" name="id" value="${param.id}"></td>
                </tr>
                <tr>
                    <td><spring:message code="userRole.userId"/></td>
                    <td><input type="number" name="userId" value="${param.userId}"></td>
                    <td><spring:message code="userRole.roleId"/></td>
                    <td><input type="number" name="roleId" value="${param.roleId}"></td>
                </tr>
                <tr>
                    <td><spring:message code="userRole.userId"/></td>
                    <td><input type="datetime-local" name="dateTime" value="${param.dateTime}"></td>
                    <td><spring:message code="userRole.comment"/></td>
                    <td><input type="text" name="comment" value="${param.comment}"></td>
                </tr>

                <div class="text-right">
                    <a class="btn btn-outline-primary" role="button" href="userRoles">
                        <spring:message code="app.userRoles"/>
                    </a>
                    <button class="btn btn-primary" type="submit"> <spring:message code="app.search"/>
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </button>
                </div>
            </table>
        </form>
    </div>
</div>

</body>
</html>
