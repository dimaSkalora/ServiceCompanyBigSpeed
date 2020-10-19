<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFProcess Data</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs box taxiOrderAcceptance_nav_tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#wfProcess"><spring:message code="app.workflow.wfProcesses"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#wfPackage"><spring:message code="app.workflow.wfPackages"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#wfBaseProcess"><spring:message code="app.workflow.wfBaseProcesses"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#wfProcessStatus"><spring:message code="app.workflow.wfProcessStatuses"/></a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="wfProcess">
                <form:form method="POST" modelAttribute="wfProcessData">
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
                                </td>
                                <td>
                                    <form:label path="startDate">
                                        <spring:message code="workflow.wfProcess.startDate"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="startDate" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="finalDate">
                                        <spring:message code="workflow.wfProcess.finalDate"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="finalDate" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="description">
                                        <spring:message code="workflow.wfProcess.description"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="description" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="dateEdit">
                                        <spring:message code="workflow.wfProcess.dateEdit"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="dateEdit" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="userEdit">
                                        <spring:message code="workflow.wfProcess.userEdit"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="userEdit" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="wfPackageId">
                                        <spring:message code="workflow.wfProcess.wfPackageId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfPackageId" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="wfBaseProcessId">
                                        <spring:message code="workflow.wfProcess.wfBaseProcessId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfBaseProcessId" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="wfProcessStatusId">
                                        <spring:message code="workflow.wfProcess.wfProcessStatusId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfProcessStatusId" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/wfProcesses" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfPackage">
                <form:form method="POST" modelAttribute="wfpWFPackage" >
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
                                    <form:label path="wfServiceId">
                                        <spring:message code="workflow.wfPackage.wfServiceId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfServiceId" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="wfPackageStatusId">
                                        <spring:message code="workflow.wfPackage.wfPackageStatusId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfPackageStatusId" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/wfProcesses" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfBaseProcess">
                <form:form method="POST" modelAttribute="wfpWFBaseProcess" >
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
                                </td>
                                <td>
                                    <form:label path="name">
                                        <spring:message code="workflow.wfBaseProcess.name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="description">
                                        <spring:message code="workflow.wfBaseProcess.description"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="description" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="wfServiceId">
                                        <spring:message code="workflow.wfBaseProcess.wfServiceId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfServiceId" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="wfBaseProcessTypeId">
                                        <spring:message code="workflow.wfBaseProcess.wfBaseProcessTypeId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfBaseProcessTypeId" readonly="true"/>                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/wfProcesses" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfProcessStatus">
                <form:form method="POST" modelAttribute="wfpWFProcessStatus" >
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="workflow.wfPackageStatus.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                                </td>
                                <td>
                                    <form:label path="name">
                                        <spring:message code="workflow.wfPackageStatus.name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/wfProcesses" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
