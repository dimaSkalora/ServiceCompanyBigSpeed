<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title>UserFromAllRoles</title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <form:form method="POST" modelAttribute="userFromAllRoles" >
            <fieldset>
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="user.id"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                        <td>
                            <form:label path="name">
                                <spring:message code="user.name"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="name" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="password">
                                <spring:message code="user.password"/>
                            </form:label>
                        </td>
                        <td>
                            <form:password path="password" readonly="true"/>
                        </td>
                        <td>
                            <form:label path="email">
                                <spring:message code="user.email"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="email" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="phone">
                                <spring:message code="user.phone"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="phone" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="registered">
                                <spring:message code="user.registered"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="date" path="registered" readonly="true"/>
                        </td>
                        <td>
                            <form:label path="enabled">
                                <spring:message code="user.enabled"/>
                            </form:label>
                        </td>
                        <td>
                            <form:checkbox path="enabled" readonly="true"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <footer>
                <a href="/ServiceCompanyBigSpeed/users" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
            </footer>
        </form:form>
    </div>
    <div class="container">
        <h1>Roles</h1>
        <div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th><spring:message code="role.id"/></th>
                    <th><spring:message code="role.name"/></th>
                    <th><spring:message code="role.description"/></th>
                </tr>
                </thead>
                <c:forEach items="${userFromAllRoles.roleList}" var="role">
                    <jsp:useBean id="role" scope="page" type="org.speed.big.company.service.model.Role"/>
                    <tr>
                        <td><a href="roles/getData/${role.id}"/><c:out value="${role.id}"/></td>
                        <td><c:out value="${role.name}"/></td>
                        <td><c:out value="${role.description}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>
