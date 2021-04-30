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
                <a class="nav-link" data-toggle="tab" href="#wfProcess"><spring:message code="app.workflow.wfProcesses"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#wfProcessList"><spring:message code="app.workflow.wfProcesses"/></a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="wfPackage">
                <form:form method="POST" modelAttribute="wfPackage">
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
                        <a href="/ServiceCompanyBigSpeed/managerWFProcessMovements" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfProcess">
                <form:form method="POST" modelAttribute="wfProcess">
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
                                <td>
                                    <form:label path="finalDate">
                                        <spring:message code="workflow.wfProcess.finalDate"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="finalDate" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
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
                                <td>
                                    <form:label path="wfPackageId">
                                        <spring:message code="workflow.wfProcess.wfPackageId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfPackageId" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="wfBaseProcessId">
                                        <spring:message code="workflow.wfProcess.wfBaseProcessId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfBaseProcessId" readonly="true"/>
                                </td>
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
                    <fieldset>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th><spring:message code="workflow.wfProcessMovement.id"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.startDateTime"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.finalDateTime"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.isCompleted"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.description"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.dateEdit"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.userEdit"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.userId"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.wfPackageId"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.wfStateId"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.wfProcessId"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.wfBaseProcessId"/></th>
                                <th><spring:message code="workflow.wfProcessMovement.isLast"/></th>
                            </tr>
                            </thead>
                            <c:forEach items="${wfProcessMovements}" var="wfProcessMovement">
                                <jsp:useBean id="wfProcessMovement" scope="page" type="org.speed.big.company.service.model.workflow.WFProcessMovement"/>
                                <tr>
                                    <td><c:out value="${wfProcessMovement.id}"/></td>
                                    <td><c:out value="${wfProcessMovement.startDateTime}"/></td>
                                    <td><c:out value="${wfProcessMovement.finalDateTime}"/></td>
                                    <td><c:out value="${wfProcessMovement.completed}"/></td>
                                    <td><c:out value="${wfProcessMovement.description}"/></td>
                                    <td><c:out value="${wfProcessMovement.dateEdit}"/></td>
                                    <td><c:out value="${wfProcessMovement.userEdit}"/></td>
                                    <td><c:out value="${wfProcessMovement.userId}"/></td>
                                    <td><c:out value="${wfProcessMovement.wfPackageId}"/></td>
                                    <td><c:out value="${wfProcessMovement.wfStateId}"/></td>
                                    <td><c:out value="${wfProcessMovement.wfProcessId}"/></td>
                                    <td><c:out value="${wfProcessMovement.wfBaseProcessId}"/></td>
                                    <td><c:out value="${wfProcessMovement.last}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/managerWFProcessMovements" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfProcessList">
                <form:form method="POST" modelAttribute="wfProcessList" >
                    <fieldset>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th><spring:message code="workflow.wfProcess.id"/></th>
                                <th><spring:message code="workflow.wfProcess.startDate"/></th>
                                <th><spring:message code="workflow.wfProcess.finalDate"/></th>
                                <th><spring:message code="workflow.wfProcess.description"/></th>
                                <th><spring:message code="workflow.wfProcess.dateEdit"/></th>
                                <th><spring:message code="workflow.wfProcess.userEdit"/></th>
                                <th><spring:message code="workflow.wfProcess.wfPackageId"/></th>
                                <th><spring:message code="workflow.wfProcess.wfBaseProcessId"/></th>
                                <th><spring:message code="workflow.wfProcess.wfProcessStatusId"/></th>
                            </tr>
                            </thead>
                            <c:forEach items="${wfProcessList}" var="wfProcess">
                                <jsp:useBean id="wfProcess" scope="page" type="org.speed.big.company.service.model.workflow.WFProcess"/>
                                <tr>
                                    <td><c:out value="${wfProcess.id}"/></td>
                                    <td><c:out value="${wfProcess.startDate}"/></td>
                                    <td><c:out value="${wfProcess.finalDate}"/></td>
                                    <td><c:out value="${wfProcess.description}"/></td>
                                    <td><c:out value="${wfProcess.dateEdit}"/></td>
                                    <td><c:out value="${wfProcess.userEdit}"/></td>
                                    <td><c:out value="${wfProcess.wfPackageId}"/></td>
                                    <td><c:out value="${wfProcess.wfBaseProcessId}"/></td>
                                    <td><c:out value="${wfProcess.wfProcessStatusId}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/managerWFProcessMovements" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
