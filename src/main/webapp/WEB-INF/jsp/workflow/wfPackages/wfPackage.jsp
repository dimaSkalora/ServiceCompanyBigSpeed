<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFPackage</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/workflow/wfPackages/createOrUpdate" />
        <form:form method="POST" modelAttribute="wfPackage" action="${createOrUpdateUrl}">
            <fieldset>
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="workflow.wfPackage.id"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                        <td>
                            <form:label path="name">
                                <spring:message code="workflow.wfPackage.name"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="name"/>
                            <form:errors path="name" cssStyle="color:red"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="dateRegistration">
                                <spring:message code="workflow.wfPackage.dateRegistration"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="date" path="dateRegistration"/>
                            <form:errors path="dateRegistration" cssStyle="color:red"/>
                        </td>
                        <td>
                            <form:label path="customerName">
                                <spring:message code="workflow.wfPackage.customerName"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="customerName"/>
                        </td>
                        <form:errors path="customerName" cssStyle="color:red"/>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="customerAddress">
                                <spring:message code="workflow.wfPackage.customerAddress"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="customerAddress"/>
                            <form:errors path="customerAddress" cssStyle="color:red"/>
                        </td>
                        <td>
                            <form:label path="customerAddressJur">
                                <spring:message code="workflow.wfPackage.customerAddressJur"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="customerAddressJur"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="customerPhone">
                                <spring:message code="workflow.wfPackage.customerPhone"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="customerPhone"/>
                            <form:errors path="customerPhone" cssStyle="color:red"/>
                        </td>
                        <td>
                            <form:label path="customerEmail">
                                <spring:message code="workflow.wfPackage.customerEmail"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="customerEmail"/>
                            <form:errors path="customerEmail" cssStyle="color:red"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="contractNumber">
                                <spring:message code="workflow.wfPackage.contractNumber"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="contractNumber"/>
                            <form:errors path="contractNumber" cssStyle="color:red"/>
                        </td>
                        <td>
                            <form:label path="description">
                                <spring:message code="workflow.wfPackage.description"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="description"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="userAdd">
                                <spring:message code="workflow.wfPackage.userAdd"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="userAdd"/>
                            <form:errors path="userAdd" cssStyle="color:red"/>
                        </td>
                        <td>
                            <form:label path="dateAdd">
                                <spring:message code="workflow.wfPackage.dateAdd"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="datetime-local" path="dateAdd"/>
                            <form:errors path="dateAdd" cssStyle="color:red"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="userEdit">
                                <spring:message code="workflow.wfPackage.userEdit"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="userEdit"/>
                            <form:errors path="userEdit" cssStyle="color:red"/>
                        </td>
                        <td>
                            <form:label path="dateEdit">
                                <spring:message code="workflow.wfPackage.dateEdit"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="datetime-local" path="dateEdit"/>
                            <form:errors path="dateEdit" cssStyle="color:red"/>
                        </td>
                    </tr>
                    <tr>
                         <td>
                             <form:label path="wfServiceId.id">
                                 <spring:message code="workflow.wfPackage.wfServiceId"/>
                             </form:label>
                         </td>
                         <td>
                             <form:select path="wfServiceId">
                                 <form:option  value="${!empty wfPackage.id ? wfPackage.wfServiceId.id : ''}"
                                               label="${!empty wfPackage.id ? wfPackage.wfServiceId.name : ''}"/>
                                 <%--<form:option  value="" label=""/>--%>
                                 <c:forEach items="${allWFS}" var="wfService">
                                     <jsp:useBean id="wfService" scope="page" type="org.speed.big.company.service.model.workflow.WFService"/>
                                     <form:option  value="${wfService.id}" label="${wfService.name}"/>
                                 </c:forEach>
                             </form:select>
                         </td>
                         <td>
                             <form:label path="wfPackageStatusId.id">
                                 <spring:message code="workflow.wfPackage.wfPackageStatusId"/>
                             </form:label>
                         </td>
                         <td>
                             <form:select path="wfPackageStatusId">
                                 <form:option  value="${!empty wfPackage.id ? wfPackage.wfPackageStatusId.id : ''}"
                                               label="${!empty wfPackage.id ? wfPackage.wfPackageStatusId.name : ''}"/>
                                 <%--<form:option  value="" label=""/>--%>
                                 <c:forEach items="${allWFPS}" var="wfPackageStatus">
                                     <jsp:useBean id="wfPackageStatus" scope="page" type="org.speed.big.company.service.model.workflow.WFPackageStatus"/>
                                     <form:option  value="${wfPackageStatus.id}" label="${wfPackageStatus.name}"/>
                                 </c:forEach>
                             </form:select>
                         </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/ServiceCompanyBigSpeed/workflow/wfPackages/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/ServiceCompanyBigSpeed/workflow/wfPackages"><spring:message code="app.ok"/></a>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty wfPackage.id}">
                        <input type="submit" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty wfPackage.id}">
                        <input type="submit" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>

</body>
</html>
