<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFBaseProcessItem Data</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#wfBaseProcessItem"><spring:message code="app.workflow.wfBaseProcessItems"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#wfProcessStateFrom"><spring:message code="app.workflow.wfProcessStatuses"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#wfProcessStateTo"><spring:message code="app.workflow.wfProcessStatuses"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#wfBaseProcess"><spring:message code="app.workflow.wfBaseProcesses"/></a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="wfBaseProcessItem">
                <form:form method="POST" modelAttribute="wfBaseProcessItemData">
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="workflow.wfBaseProcessItem.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                                </td>
                                <td>
                                    <form:label path="baseProcessId.id">
                                        <spring:message code="workflow.wfBaseProcessItem.baseProcessId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="baseProcessId.id" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="stateFromId.id">
                                        <spring:message code="workflow.wfBaseProcessItem.stateFromId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="stateFromId.id" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="stateToId.id">
                                        <spring:message code="workflow.wfBaseProcessItem.stateToId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="stateToId.id" readonly="true"/>
                                </td>
                            </tr>

                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/workflow/wfBaseProcessItems" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfProcessStateFrom">
                <form:form method="POST" modelAttribute="wfProcessStateFrom" >
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="workflow.wfProcessState.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="name">
                                        <spring:message code="workflow.wfProcessState.name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="roleId.id">
                                        <spring:message code="workflow.wfProcessState.roleId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="roleId.id" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="wfGroupId.id">
                                        <spring:message code="workflow.wfProcessState.wfGroupId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfGroupId.id" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="description">
                                        <spring:message code="workflow.wfProcessState.description"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="description" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/workflow/wfBaseProcessItems" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfProcessStateTo">
                <form:form method="POST" modelAttribute="wfProcessStateTo" >
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="workflow.wfProcessState.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="name">
                                        <spring:message code="workflow.wfProcessState.name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="roleId.id">
                                        <spring:message code="workflow.wfProcessState.roleId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="roleId.id" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="wfGroupId.id">
                                        <spring:message code="workflow.wfProcessState.wfGroupId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfGroupId.id" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="description">
                                        <spring:message code="workflow.wfProcessState.description"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="description" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/workflow/wfBaseProcessItems" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfBaseProcess">
                <form:form method="POST" modelAttribute="wfBaseProcess" >
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
                                    <form:label path="wfServiceId.id">
                                        <spring:message code="workflow.wfBaseProcess.wfServiceId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfServiceId.id" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="wfBaseProcessTypeId.id">
                                        <spring:message code="workflow.wfBaseProcess.wfBaseProcessTypeId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="wfBaseProcessTypeId.id" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/workflow/wfBaseProcessItems" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
