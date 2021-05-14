<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFPackage Filter</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">

        <c:url var="createOrUpdateUrl" value="/workflow/wfPackages/filter" />
        <form method="post" action="${createOrUpdateUrl}" >
            <table border="0" cellpadding="8" cellspacing="0">
                <tr>
                    <td><spring:message code="workflow.wfPackage.id"/></td>
                    <td><input type="number" name="id" value="${param.id}"></td>
                    <td><spring:message code="workflow.wfPackage.name"/></td>
                    <td><input type="text" name="name" value="${param.name}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfPackage.dateRegistration"/></td>
                    <td><input type="date" name="dateRegistration" value="${param.dateRegistration}"></td>
                    <td><spring:message code="workflow.wfPackage.customerName"/></td>
                    <td><input type="text" name="customerName" value="${param.customerName}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfPackage.customerAddress"/></td>
                    <td><input type="text" name="customerAddress" value="${param.customerAddress}"></td>
                    <td><spring:message code="workflow.wfPackage.customerAddressJur"/></td>
                    <td><input type="text" name="customerAddressJur" value="${param.customerAddressJur}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfPackage.customerPhone"/></td>
                    <td><input type="text" name="customerPhone" value="${param.customerPhone}"></td>
                    <td><spring:message code="workflow.wfPackage.customerEmail"/></td>
                    <td><input type="text" name="customerEmail" value="${param.customerEmail}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfPackage.contractNumber"/></td>
                    <td><input type="text" name="contractNumber" value="${param.contractNumber}"></td>
                    <td><spring:message code="workflow.wfPackage.description"/></td>
                    <td><input type="text" name="description" value="${param.description}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfPackage.userAdd"/></td>
                    <td><input type="text" name="userAdd" value="${param.userAdd}"></td>
                    <td><spring:message code="workflow.wfPackage.dateAdd"/></td>
                    <td><input type="datetime-local" name="dateAdd" value="${param.dateAdd}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfPackage.userEdit"/></td>
                    <td><input type="text" name="userEdit" value="${param.userEdit}"></td>
                    <td><spring:message code="workflow.wfPackage.dateEdit"/></td>
                    <td><input type="datetime-local" name="dateEdit" value="${param.dateEdit}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfPackage.wfServiceId"/></td>
                    <td><input type="number" name="wfServiceId" value="${param.wfServiceId}"></td>
                    <td><spring:message code="workflow.wfPackage.wfPackageStatusId"/></td>
                    <td><input type="number" name="wfPackageStatusId" value="${param.wfPackageStatusId}"></td>
                </tr>
                <div class="text-right">
                    <a class="btn btn-outline-primary" role="button" href="workflow/wfPackages">
                        <spring:message code="app.workflow.wfPackages"/>
                    </a>
                    <button class="btn btn-primary" type="submit"> <spring:message code="app.search"/>
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </button>
                </div>
            </table>
        </form>
    </div>
</div>

</body>
</html>
