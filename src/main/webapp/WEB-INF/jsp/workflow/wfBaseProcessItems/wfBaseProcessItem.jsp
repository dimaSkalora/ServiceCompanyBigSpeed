<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFBaseProcessItem</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/wfBaseProcessItems/createOrUpdate" />
        <form:form method="POST" modelAttribute="wfBaseProcessItem" action="${createOrUpdateUrl}">
            <fieldset>
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="workflow.wfBaseProcessItem.id"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                        <td>
                            <form:label path="baseProcessId.id">
                                <spring:message code="workflow.wfBaseProcessItem.baseProcessId"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="baseProcessId.id">
                                <form:option  value="${!empty wfBaseProcessItem.id ? wfBaseProcessItem.baseProcessId.id : ''}"
                                              label="${!empty wfBaseProcessItem.id ? wfBaseProcessItem.baseProcessId.name : ''}"/>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${getAllWFBaseProcesses}" var="wfBaseProcess">
                                    <jsp:useBean id="wfBaseProcess" scope="page" type="org.speed.big.company.service.model.workflow.WFBaseProcess"/>
                                    <form:option  value="${wfBaseProcess.id}" label="${wfBaseProcess.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="stateFromId.id">
                                <spring:message code="workflow.wfBaseProcessItem.stateFromId"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="stateFromId.id">
                                <form:option  value="${!empty wfBaseProcessItem.id ? wfBaseProcessItem.stateFromId.id : ''}"
                                              label="${!empty wfBaseProcessItem.id ? wfBaseProcessItem.stateFromId.name : ''}"/>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${getAllWFProcessStates}" var="wfProcessStateFrom">
                                    <jsp:useBean id="wfProcessStateFrom" scope="page" type="org.speed.big.company.service.model.workflow.WFProcessState"/>
                                    <form:option  value="${wfProcessStateFrom.id}" label="${wfProcessStateFrom.name} ${wfProcessStateFrom.roleId.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <form:label path="stateToId.id">
                                <spring:message code="workflow.wfBaseProcessItem.stateToId"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="stateToId.id">
                                <form:option  value="${!empty wfBaseProcessItem.id ? wfBaseProcessItem.stateToId.id : ''}"
                                              label="${!empty wfBaseProcessItem.id ? wfBaseProcessItem.stateToId.name : ''}"/>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${getAllWFProcessStates}" var="wfProcessStateTo">
                                    <jsp:useBean id="wfProcessStateTo" scope="page" type="org.speed.big.company.service.model.workflow.WFProcessState"/>
                                    <form:option  value="${wfProcessStateTo.id}" label="${wfProcessStateTo.name} ${wfProcessStateTo.roleId.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>

                </table>
            </fieldset>

            <footer>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/ServiceCompanyBigSpeed/wfBaseProcessItems/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/ServiceCompanyBigSpeed/wfBaseProcessItems"><spring:message code="app.ok"/></a>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty wfBaseProcessItem.id}">
                        <input type="submit" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty wfBaseProcessItem.id}">
                        <input type="submit" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>

</body>
</html>
