<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFProcess</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/wfProcesses/createOrUpdate" />
        <form:form method="POST" modelAttribute="wfProcess" action="${createOrUpdateUrl}">
            <fieldset>
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="workflow.wfProcess.id"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                        <td>
                            <form:label path="startDate">
                                <spring:message code="workflow.wfProcess.startDate"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="datetime-local" path="startDate"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="finalDate">
                                <spring:message code="workflow.wfProcess.finalDate"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="datetime-local" path="finalDate"/>
                        </td>
                        <td>
                            <form:label path="description">
                                <spring:message code="workflow.wfProcess.description"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="description"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="dateEdit">
                                <spring:message code="workflow.wfProcess.dateEdit"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="datetime-local" path="dateEdit"/>
                        </td>
                        <td>
                            <form:label path="userEdit">
                                <spring:message code="workflow.wfProcess.userEdit"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="userEdit"/>
                        </td>
                    </tr>
                    <tr>
                         <td>
                             <form:label path="wfPackageId">
                                 <spring:message code="workflow.wfProcess.wfPackageId"/>
                             </form:label>
                         </td>
                         <td>
                             <form:select path="wfPackageId">
                                 <form:option  value="${!empty wfProcess.id ? wfProcess.wfPackageId.id : ''}"
                                               label="${!empty wfProcess.id ? wfProcess.wfPackageId.name : ''}"/>
                                 <%--<form:option  value="" label=""/>--%>
                                 <c:forEach items="${allWFPackages}" var="wfPackage">
                                     <jsp:useBean id="wfPackage" scope="page" type="org.speed.big.company.service.model.workflow.WFPackage"/>
                                     <form:option  value="${wfPackage.id}" label="${wfPackage.name}"/>
                                 </c:forEach>
                             </form:select>
                         </td>
                         <td>
                             <form:label path="wfBaseProcessId">
                                 <spring:message code="workflow.wfProcess.wfBaseProcessId"/>
                             </form:label>
                         </td>
                         <td>
                             <form:select path="wfBaseProcessId">
                                 <form:option  value="${!empty wfProcess.id ? wfProcess.wfBaseProcessId.id : ''}"
                                               label="${!empty wfProcess.id ? wfProcess.wfBaseProcessId.name : ''}"/>
                                 <%--<form:option  value="" label=""/>--%>
                                 <c:forEach items="${allWFBaseProcesses}" var="wfBaseProcess">
                                     <jsp:useBean id="wfBaseProcess" scope="page" type="org.speed.big.company.service.model.workflow.WFBaseProcess"/>
                                     <form:option  value="${wfBaseProcess.id}" label="${wfBaseProcess.name}"/>
                                 </c:forEach>
                             </form:select>
                         </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="wfProcessStatusId">
                                <spring:message code="workflow.wfProcess.wfProcessStatusId"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="wfProcessStatusId">
                                <form:option  value="${!empty wfProcess.id ? wfProcess.wfProcessStatusId.id : ''}"
                                              label="${!empty wfProcess.id ? wfProcess.wfProcessStatusId.name : ''}"/>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${allWFProcessStatuses}" var="wfProcessStatus">
                                    <jsp:useBean id="wfProcessStatus" scope="page" type="org.speed.big.company.service.model.workflow.WFProcessStatus"/>
                                    <form:option  value="${wfProcessStatus.id}" label="${wfProcessStatus.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/ServiceCompanyBigSpeed/wfProcesses/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/ServiceCompanyBigSpeed/wfProcesses"><spring:message code="app.ok"/></a>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty wfProcess.id}">
                        <input type="submit" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty wfProcess.id}">
                        <input type="submit" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>

</body>
</html>
