<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFBaseProcess Data</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#wfBaseProcess"><spring:message code="app.workflow.wfBaseProcesses"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#wfService"><spring:message code="app.workflow.wfServices"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#wfBaseProcessType"><spring:message code="app.workflow.wfBaseProcessTypes"/></a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="wfBaseProcess">
                <form:form method="POST" modelAttribute="wfbpData">
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
                        <a href="/ServiceCompanyBigSpeed/wfBaseProcesses" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfService">
                <form:form method="POST" modelAttribute="wfbpWFService" >
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
                        <a href="/ServiceCompanyBigSpeed/wfBaseProcesses" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="wfBaseProcessType">
                <form:form method="POST" modelAttribute="wfbpWFBaseProcessType" >
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="workflow.wfBaseProcessType.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                                </td>
                                <td>
                                    <form:label path="name">
                                        <spring:message code="workflow.wfBaseProcessType.name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/wfBaseProcesses" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
