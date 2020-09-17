<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title>User</title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">

        <c:url var="createOrUpdateUrl" value="/users/createOrUpdate" />
        <form:form method="POST" modelAttribute="user" action="${createOrUpdateUrl}"
                   class="box login">

            <fieldset class="boxBody">
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
                            <form:input path="name"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="password">
                                <spring:message code="user.password"/>
                            </form:label>
                        </td>
                        <td>
                            <form:password path="password" />
                        </td>
                        <td>
                            <form:label path="email">
                                <spring:message code="user.email"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="email" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="phone">
                                <spring:message code="user.phone"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="phone" />
                        </td>
                    </tr>
                    <c:if test="${!empty user.name}">
                        <tr>
                            <td>
                                <form:label path="registered">
                                    <spring:message code="user.registered"/>
                                </form:label>
                            </td>
                            <td>
                                <form:input path="registered" />
                            </td>
                            <td>
                                <form:label path="enabled">
                                    <spring:message code="user.enabled"/>
                                </form:label>
                            </td>
                            <td>
                                <form:checkbox path="enabled" />
                            </td>
                        </tr>
                    </c:if>
                </table>
            </fieldset>

            <footer>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/ServiceCompanyBigSpeed/users/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/ServiceCompanyBigSpeed/users" class="btnLogin"><spring:message code="app.ok"/></a>
                    <%--<input class ="btnLogin" type="button" value="<spring:message text="OK2"/>" onclick="location.href = '/ServiceCompanyBigSpeed/users'"/>--%>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty user.id}">
                        <input type="submit" class="btnLogin" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty user.id}">
                        <input type="submit" class="btnLogin" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>

</body>
</html>
