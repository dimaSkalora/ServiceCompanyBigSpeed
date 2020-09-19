<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title>User Filter</title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">

        <c:url var="createOrUpdateUrl" value="/users/filterCondition" />
        <form method="post" action="${createOrUpdateUrl}" >
            <table border="0" cellpadding="8" cellspacing="0">
                <tr>
                    <td><spring:message code="user.id"/></td>
                    <td><input type="number" name="id" value="${param.id}"></td>
                    <td><spring:message code="user.name"/></td>
                    <td><input type="text" name="name value="${param.name}"></td>
                </tr>
                <tr>
                    <td><spring:message code="user.email"/></td>
                    <td><input type="email" name="email" value="${param.email}"></td>
                    <td><spring:message code="user.password"/></td>
                    <td><input type="text" name="password" value="${param.password}"></td>
                </tr>
                <tr>
                    <td><spring:message code="user.phone"/></td>
                    <td><input type="text" name="phone" value="${param.phone}"></td>
                    <td><spring:message code="user.registered"/></td>
                    <td><input type="date" name="registered" value="${param.registered}"></td>
                </tr>
                <tr>
                    <td><spring:message code="user.enabled"/></td>
                    <td><input type="checkbox" name="enabled" checked = "checked"></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><spring:message code="user.registeredFrom"/></td>
                    <td><input type="date" name="registeredFrom" value="${param.registeredFrom}"></td>
                    <td><spring:message code="user.registeredTo"/></td>
                    <td><input type="date" name="registeredTo" value="${param.registeredTo}"></td>
                </tr>

                <div class="text-right">
                    <a class="btn btn-danger" href="users"><spring:message code="app.users"/>
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
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
