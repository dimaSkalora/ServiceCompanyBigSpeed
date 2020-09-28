<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title>User Roles</title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/userRoles/createOrUpdate" />
        <form:form method="POST" modelAttribute="userRole" action="${createOrUpdateUrl}">
            <fieldset>
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="userRole.id"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="userId">
                                <spring:message code="userRole.userId"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="userId">
                                <form:option  value="${!empty userRole.id ? userRole.id : ''}"
                                              label="${!empty userRole.id ? userRole.userId.name : ''}"/>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${getAllUser}" var="user">
                                    <jsp:useBean id="user" scope="page" type="org.speed.big.company.service.model.User"/>
                                    <form:option  value="${user.id}" label="${user.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <form:label path="roleId">
                                <spring:message code="userRole.roleId"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="roleId">
                                <form:option  value="${!empty userRole.id ? userRole.id : ''}"
                                              label="${!empty userRole.id ? userRole.roleId.name : ''}"/>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${getAllRole}" var="role">
                                    <jsp:useBean id="role" scope="page" type="org.speed.big.company.service.model.Role"/>
                                    <form:option  value="${role.id}" label="${role.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="dateTime">
                                <spring:message code="userRole.dateTime"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="datetime-local" path="dateTime" />
                        </td>
                        <td>
                            <form:label path="comment">
                                <spring:message code="userRole.comment"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="datetime-local" path="comment" />
                        </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/ServiceCompanyBigSpeed/userRoles/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/ServiceCompanyBigSpeed/userRoles"><spring:message code="app.ok"/></a>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty userRole.id}">
                        <input type="submit" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty userRole.id}">
                        <input type="submit" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>

</body>
</html>
