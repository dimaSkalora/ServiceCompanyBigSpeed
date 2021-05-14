<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFProcessState</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/workflow/wfProcessStates/createOrUpdate" />
        <form:form method="POST" modelAttribute="wfProcessState" action="${createOrUpdateUrl}">
            <fieldset>
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="workflow.wfProcessState.id"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                        <td>
                            <form:label path="name">
                                <spring:message code="workflow.wfProcessState.name"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="name"/>
                            <form:errors path="name" cssStyle="color:red"/>
                        </td>
                    </tr>
                    <tr>
                         <td>
                             <form:label path="roleId.id">
                                 <spring:message code="workflow.wfProcessState.roleId"/>
                             </form:label>
                         </td>
                         <td>
                             <form:select path="roleId.id">
                                 <form:option  value="${!empty wfProcessState.id ? wfProcessState.roleId.id : ''}"
                                               label="${!empty wfProcessState.id ? wfProcessState.roleId.name : ''}"/>
                                 <%--<form:option  value="" label=""/>--%>
                                 <c:forEach items="${getAllRoles}" var="role">
                                     <jsp:useBean id="role" scope="page" type="org.speed.big.company.service.model.Role"/>
                                     <form:option  value="${role.id}" label="${role.name}"/>
                                 </c:forEach>
                             </form:select>
                         </td>
                         <td>
                             <form:label path="wfGroupId.id">
                                 <spring:message code="workflow.wfProcessState.wfGroupId"/>
                             </form:label>
                         </td>
                         <td>
                             <form:select path="wfGroupId.id">
                                 <form:option  value="${!empty wfProcessState.id ? wfProcessState.wfGroupId.id : ''}"
                                               label="${!empty wfProcessState.id ? wfProcessState.wfGroupId.name : ''}"/>
                                 <%--<form:option  value="" label=""/>--%>
                                 <c:forEach items="${getAllWFGroups}" var="wfGroup">
                                     <jsp:useBean id="wfGroup" scope="page" type="org.speed.big.company.service.model.workflow.WFGroup"/>
                                     <form:option  value="${wfGroup.id}" label="${wfGroup.name}"/>
                                 </c:forEach>
                             </form:select>
                         </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="description">
                                <spring:message code="workflow.wfProcessState.description"/>
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
                <c:set var="jsf_request_uriStr" value="/ServiceCompanyBigSpeed/workflow/wfProcessStates/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/ServiceCompanyBigSpeed/workflow/wfProcessStates"><spring:message code="app.ok"/></a>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty wfProcessState.id}">
                        <input type="submit" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty wfProcessState.id}">
                        <input type="submit" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>

</body>
</html>
