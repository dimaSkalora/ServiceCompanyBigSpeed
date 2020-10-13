<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFBaseProcess</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/wfBaseProcesses/createOrUpdate" />
        <form:form method="POST" modelAttribute="wfBaseProcess" action="${createOrUpdateUrl}">
            <fieldset>
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="workflow.wfBaseProcess.id"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                        <td>
                            <form:label path="name">
                                <spring:message code="workflow.wfBaseProcess.name"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="name"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="description">
                                <spring:message code="workflow.wfBaseProcess.description"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="description"/>
                        </td>
                    </tr>
                    <tr>
                         <td>
                             <form:label path="wfServiceId">
                                 <spring:message code="workflow.wfBaseProcess.wfServiceId"/>
                             </form:label>
                         </td>
                         <td>
                             <form:select path="wfServiceId">
                                 <form:option  value="${!empty wfBaseProcess.id ? wfPackage.wfBaseProcess.id : ''}"
                                               label="${!empty wfBaseProcess.id ? wfPackage.wfBaseProcess.name : ''}"/>
                                 <%--<form:option  value="" label=""/>--%>
                                 <c:forEach items="${allWFS}" var="wfService">
                                     <jsp:useBean id="wfService" scope="page" type="org.speed.big.company.service.model.workflow.WFService"/>
                                     <form:option  value="${wfService.id}" label="${wfService.name}"/>
                                 </c:forEach>
                             </form:select>
                         </td>
                         <td>
                             <form:label path="wfBaseProcessTypeId">
                                 <spring:message code="workflow.wfBaseProcess.wfBaseProcessTypeId"/>
                             </form:label>
                         </td>
                         <td>
                             <form:select path="wfBaseProcessTypeId">
                                 <form:option  value="${!empty wfBaseProcess.id ? wfBaseProcess.wfBaseProcessTypeId.id : ''}"
                                               label="${!empty wfBaseProcess.id ? wfBaseProcess.wfBaseProcessTypeId.name : ''}"/>
                                 <%--<form:option  value="" label=""/>--%>
                                 <c:forEach items="${allWFBPT}" var="wfBaseProcessType">
                                     <jsp:useBean id="wfBaseProcessType" scope="page" type="org.speed.big.company.service.model.workflow.WFBaseProcessType"/>
                                     <form:option  value="${wfBaseProcessType.id}" label="${wfBaseProcessType.name}"/>
                                 </c:forEach>
                             </form:select>
                         </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/ServiceCompanyBigSpeed/wfBaseProcesses/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/ServiceCompanyBigSpeed/wfBaseProcesses"><spring:message code="app.ok"/></a>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty wfBaseProcess.id}">
                        <input type="submit" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty wfBaseProcess.id}">
                        <input type="submit" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>

</body>
</html>
