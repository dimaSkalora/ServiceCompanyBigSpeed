<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFPackages</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a class="btn btn-primary" role="button" href="workflow/wfPackages/wfPackage">add WFPackage</a></li>
        </ul>
        <ul>
            <li><a href="workflow/wfPackages/wfPackageFilter" class="btnSearchBy">Search by WFPackage</a> </li>
        </ul>
    </div>
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><spring:message code="workflow.wfPackage.id"/></th>
                <th><spring:message code="workflow.wfPackage.name"/></th>
                <th><spring:message code="workflow.wfPackage.dateRegistration"/></th>
                <th><spring:message code="workflow.wfPackage.customerName"/></th>
                <th><spring:message code="workflow.wfPackage.customerAddress"/></th>
                <th><spring:message code="workflow.wfPackage.customerAddressJur"/></th>
                <th><spring:message code="workflow.wfPackage.customerPhone"/></th>
                <th><spring:message code="workflow.wfPackage.customerEmail"/></th>
                <th><spring:message code="workflow.wfPackage.contractNumber"/></th>
                <th><spring:message code="workflow.wfPackage.description"/></th>
                <th><spring:message code="workflow.wfPackage.userAdd"/></th>
                <th><spring:message code="workflow.wfPackage.dateAdd"/></th>
                <th><spring:message code="workflow.wfPackage.userEdit"/></th>
                <th><spring:message code="workflow.wfPackage.dateEdit"/></th>
                <th><spring:message code="workflow.wfPackage.wfServiceId"/></th>
                <th><spring:message code="workflow.wfPackage.wfPackageStatusId"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${wfPackages}" var="wfPackage">
                <jsp:useBean id="wfPackage" scope="page" type="org.speed.big.company.service.model.workflow.WFPackage"/>
                <tr>
                    <td><a href="workflow/wfPackages/getData/${wfPackage.id}"/><c:out value="${wfPackage.id}"/></td>
                    <td><c:out value="${wfPackage.name}"/></td>
                    <td><c:out value="${wfPackage.dateRegistration}"/></td>
                    <td><c:out value="${wfPackage.customerName}"/></td>
                    <td><c:out value="${wfPackage.customerAddress}"/></td>
                    <td><c:out value="${wfPackage.customerAddressJur}"/></td>
                    <td><c:out value="${wfPackage.customerPhone}"/></td>
                    <td><c:out value="${wfPackage.customerEmail}"/></td>
                    <td><c:out value="${wfPackage.contractNumber}"/></td>
                    <td><c:out value="${wfPackage.description}"/></td>
                    <td><c:out value="${wfPackage.userAdd}"/></td>
                    <td><c:out value="${wfPackage.dateAdd}"/></td>
                    <td><c:out value="${wfPackage.userEdit}"/></td>
                    <td><c:out value="${wfPackage.dateEdit}"/></td>
                    <td><c:out value="${wfPackage.wfServiceId.id}"/></td>
                    <td><c:out value="${wfPackage.wfPackageStatusId.id}"/></td>
                    <td><a href="workflow/wfPackages/update/${wfPackage.id}"/><spring:message code="app.update"/></td>
                    <td><a href="workflow/wfPackages/delete?id=${wfPackage.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
