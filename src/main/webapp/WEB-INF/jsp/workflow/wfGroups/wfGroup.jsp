<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WorkFlow Group</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/wfGroups/createOrUpdate" />
        <form:form method="POST" modelAttribute="wfGroup" action="${createOrUpdateUrl}">
            <fieldset>
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="workflow.wfGroup.id"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                        <td>
                            <form:label path="name">
                                <spring:message code="workflow.wfGroup.name"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="name"/>
                            <form:errors path="name" cssStyle="color:red"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="workflow.wfGroup.description"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="description"/>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/ServiceCompanyBigSpeed/wfGroups/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/ServiceCompanyBigSpeed/wfGroups"><spring:message code="app.ok"/></a>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty wfGroup.id}">
                        <input type="submit" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty wfGroup.id}">
                        <input type="submit" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>

</body>
</html>