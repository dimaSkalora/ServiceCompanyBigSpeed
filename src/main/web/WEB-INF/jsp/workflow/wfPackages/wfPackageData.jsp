<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFPackage Data</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#wfPackage"><spring:message code="app.workflow.wfPackages"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#wfService"><spring:message code="app.workflow.wfServices"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#wfPackageStatus"><spring:message code="app.workflow.wfPackageStatuses"/></a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="wfPackage">
                <form:form method="POST" modelAttribute="wfpData">
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="workflow.wfPackage.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="name">
                                        <spring:message code="workflow.wfPackage.name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name" readonly="true" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="dateRegistration">
                                        <spring:message code="workflow.wfPackage.dateRegistration"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="dateRegistration" readonly="true" />
                                </td>
                                <td>
                                    <form:label path="customerName">
                                        <spring:message code="workflow.wfPackage.customerName"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="customerName" readonly="true" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="customerAddress">
                                        <spring:message code="workflow.wfPackage.customerAddress"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="customerAddress" readonly="true" />
                                </td>
                                <td>
                                    <form:label path="customerAddressJur">
                                        <spring:message code="workflow.wfPackage.customerAddressJur"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="customerAddressJur" readonly="true" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="customerPhone">
                                        <spring:message code="workflow.wfPackage.customerPhone"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="customerPhone" readonly="true" />
                                </td>
                                <td>
                                    <form:label path="customerEmail">
                                        <spring:message code="workflow.wfPackage.customerEmail"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="customerEmail" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="contractNumber">
                                        <spring:message code="workflow.wfPackage.contractNumber"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="contractNumber" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="description">
                                        <spring:message code="workflow.wfPackage.description"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="description" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="userAdd">
                                        <spring:message code="workflow.wfPackage.userAdd"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="userAdd" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="dateAdd">
                                        <spring:message code="workflow.wfPackage.dateAdd"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="dateAdd" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="userEdit">
                                        <spring:message code="workflow.wfPackage.userEdit"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="userEdit" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="dateEdit">
                                        <spring:message code="workflow.wfPackage.dateEdit"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="dateEdit" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="wfServiceId.id">
                                        <spring:message code="workflow.wfPackage.wfServiceId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfServiceId.id" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="wfPackageStatusId.id">
                                        <spring:message code="workflow.wfPackage.wfPackageStatusId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfPackageStatusId.id" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/wfPackages" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfService">
                <form:form method="POST" modelAttribute="wfpWFService" >
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="workflow.wfService.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="name">
                                        <spring:message code="workflow.wfService.name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/wfPackages" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfPackageStatus">
                <form:form method="POST" modelAttribute="wfpWFPackageStatus" >
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="workflow.wfPackageStatus.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true" />
                                </td>
                                <td>
                                    <form:label path="name">
                                        <spring:message code="workflow.wfPackageStatus.name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name" readonly="true" />
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/wfPackages" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
