<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFProcessMovement</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/wfProcessMovements/createOrUpdate" />
        <form:form method="POST" modelAttribute="wfProcessMovement" action="${createOrUpdateUrl}">
            <fieldset>
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="workflow.wfProcessMovement.id"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                        <td>
                            <form:label path="startDateTime">
                                <spring:message code="workflow.wfProcessMovement.startDateTime"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="datetime-local" path="startDateTime"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="finalDateTime">
                                <spring:message code="workflow.wfProcessMovement.finalDateTime"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="datetime-local" path="finalDateTime"/>
                        </td>
                        <td>
                            <form:label path="isCompleted">
                                <spring:message code="workflow.wfProcessMovement.isCompleted"/>
                            </form:label>
                        </td>
                        <td>
                            <form:checkbox path="isCompleted"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="description">
                                <spring:message code="workflow.wfProcessMovement.description"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="description"/>
                        </td>
                        <td>
                            <form:label path="dateEdit">
                                <spring:message code="workflow.wfProcessMovement.dateEdit"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="datetime-local" path="dateEdit"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="userEdit">
                                <spring:message code="workflow.wfProcessMovement.userEdit"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="userEdit"/>
                        </td>
                        <td>
                            <form:label path="userId">
                                <spring:message code="workflow.wfProcessMovement.userId"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="userId">
                                <form:option  value="${!empty wfProcessMovement.id ? wfProcessMovement.userId.id : ''}"
                                              label="${!empty wfProcessMovement.id ? wfProcessMovement.userId.name : ''}"/>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${allUsers}" var="user">
                                    <jsp:useBean id="user" scope="page" type="org.speed.big.company.service.model.User"/>
                                    <form:option  value="${user.id}" label="${user.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                         <td>
                             <form:label path="wfPackageId">
                                 <spring:message code="workflow.wfProcessMovement.wfPackageId"/>
                             </form:label>
                         </td>
                         <td>
                             <form:select path="wfPackageId">
                                 <form:option  value="${!empty wfProcessMovement.id ? wfProcessMovement.wfPackageId.id : ''}"
                                               label="${!empty wfProcessMovement.id ? wfProcessMovement.wfPackageId.name : ''}"/>
                                 <%--<form:option  value="" label=""/>--%>
                                 <c:forEach items="${allWFPackages}" var="wfPackage">
                                     <jsp:useBean id="wfPackage" scope="page" type="org.speed.big.company.service.model.workflow.WFPackage"/>
                                     <form:option  value="${wfPackage.id}" label="${wfPackage.name}"/>
                                 </c:forEach>
                             </form:select>
                         </td>
                         <td>
                             <form:label path="wfStateId">
                                 <spring:message code="workflow.wfProcessMovement.wfStateId"/>
                             </form:label>
                         </td>
                         <td>
                             <form:select path="wfStateId">
                                 <form:option  value="${!empty wfProcessMovement.id ? wfProcessMovement.wfStateId.id : ''}"
                                               label="${!empty wfProcessMovement.id ? wfProcessMovement.wfStateId.name : ''}"/>
                                 <%--<form:option  value="" label=""/>--%>
                                 <c:forEach items="${allWFProcessStates}" var="wfState">
                                     <jsp:useBean id="wfState" scope="page" type="org.speed.big.company.service.model.workflow.WFProcessState"/>
                                     <form:option  value="${wfState.id}" label="${wfState.name}"/>
                                 </c:forEach>
                             </form:select>
                         </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="wfProcessId">
                                <spring:message code="workflow.wfProcessMovement.wfProcessId"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="wfProcessId">
                                <form:option  value="${!empty wfProcessMovement.id ? wfProcessMovement.wfProcessId.id : ''}"
                                              label="${!empty wfProcessMovement.id ? wfProcessMovement.wfProcessId.name : ''}"/>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${allWFProcesses}" var="wfProcess">
                                    <jsp:useBean id="wfProcess" scope="page" type="org.speed.big.company.service.model.workflow.WFProcess"/>
                                    <form:option  value="${wfProcess.id}" label="${wfProcess.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <form:label path="wfBaseProcessId">
                                <spring:message code="workflow.wfProcessMovement.wfBaseProcessId"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="wfBaseProcessId">
                                <form:option  value="${!empty wfProcessMovement.id ? wfProcessMovement.wfBaseProcessId.id : ''}"
                                              label="${!empty wfProcessMovement.id ? wfProcessMovement.wfBaseProcessId.name : ''}"/>
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
                            <form:label path="isLast">
                                <spring:message code="workflow.wfProcessMovement.isLast"/>
                            </form:label>
                        </td>
                        <td>
                            <form:checkbox path="isLast"/>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/ServiceCompanyBigSpeed/wfProcessMovements/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/ServiceCompanyBigSpeed/wfProcessMovements"><spring:message code="app.ok"/></a>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty wfProcessMovement.id}">
                        <input type="submit" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty wfProcessMovement.id}">
                        <input type="submit" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>

</body>
</html>
