<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFProcess Filter</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">

        <c:url var="createOrUpdateUrl" value="/workflow/wfProcesses/filter" />
        <form method="post" action="${createOrUpdateUrl}" >
            <table border="0" cellpadding="8" cellspacing="0">
                <tr>
                    <td><spring:message code="workflow.wfProcess.id"/></td>
                    <td><input type="number" name="id" value="${param.id}"></td>
                    <td><spring:message code="workflow.wfProcess.startDate"/></td>
                    <td><input type="datetime-local" name="startDate" value="${param.startDate}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcess.finalDate"/></td>
                    <td><input type="datetime-local"name="finalDate" value="${param.finalDate}"></td>
                    <td><spring:message code="workflow.wfProcess.description"/></td>
                    <td><input type="text" name="description" value="${param.description}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcess.dateEdit"/></td>
                    <td><input type="datetime-local" name="dateEdit" value="${param.dateEdit}"></td>
                    <td><spring:message code="workflow.wfProcess.userEdit"/></td>
                    <td><input type="text" name="userEdit" value="${param.userEdit}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcess.wfPackageId"/></td>
                    <td><input type="number" name="wfPackageId" value="${param.wfPackageId}"></td>
                    <td><spring:message code="workflow.wfProcess.wfBaseProcessId"/></td>
                    <td><input type="number" name="wfBaseProcessId" value="${param.wfBaseProcessId}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcess.wfProcessStatusId"/></td>
                    <td><input type="number" name="wfProcessStatusId" value="${param.wfProcessStatusId}"></td>
                </tr>
                <div class="text-right">
                    <a class="btn btn-outline-primary" role="button" href="workflow/wfProcesses">
                        <spring:message code="app.workflow.wfProcesses"/>
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
