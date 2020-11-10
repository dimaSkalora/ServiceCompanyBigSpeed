<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFProcessMovement Filter</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">

        <c:url var="createOrUpdateUrl" value="/wfProcessMovements/filter" />
        <form method="post" action="${createOrUpdateUrl}" >
            <table border="0" cellpadding="8" cellspacing="0">
                <tr>
                    <td><spring:message code="workflow.wfProcessMovement.id"/></td>
                    <td><input type="number" name="id" value="${param.id}"></td>
                    <td><spring:message code="workflow.wfProcessMovement.startDateTime"/></td>
                    <td><input type="datetime-local" name="startDateTime" value="${param.startDateTime}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcessMovement.finalDateTime"/></td>
                    <td><input type="datetime-local"name="finalDateTime" value="${param.finalDateTime}"></td>
                    <td><spring:message code="workflow.wfProcessMovement.isCompleted"/></td>
                    <td><input type="checkbox" name="isCompleted" checked = "checked"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcessMovement.description"/></td>
                    <td><input type="text" name="description" value="${param.description}"></td>
                    <td><spring:message code="workflow.wfProcessMovement.dateEdit"/></td>
                    <td><input type="datetime-local" name="dateEdit" value="${param.dateEdit}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcessMovement.userEdit"/></td>
                    <td><input type="text" name="userEdit" value="${param.userEdit}"></td>
                    <td><spring:message code="workflow.wfProcessMovement.userId"/></td>
                    <td><input type="number" name="userId" value="${param.userId}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcessMovement.wfPackageId"/></td>
                    <td><input type="number" name="wfPackageId" value="${param.wfPackageId}"></td>
                    <td><spring:message code="workflow.wfProcessMovement.wfStateId"/></td>
                    <td><input type="number" name="wfStateId" value="${param.wfStateId}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcessMovement.wfProcessId"/></td>
                    <td><input type="number" name="wfProcessId" value="${param.wfProcessId}"></td>
                    <td><spring:message code="workflow.wfProcessMovement.wfBaseProcessId"/></td>
                    <td><input type="number" name="wfBaseProcessId" value="${param.wfBaseProcessId}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcessMovement.isLast"/></td>
                    <td><input type="checkbox" name="isLast" checked = "checked"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcessMovement.startDateTimeFrom"/></td>
                    <td><input type="datetime-local"name="startDateTimeFrom" value="${param.startDateTimeFrom}"></td>
                    <td><spring:message code="workflow.wfProcessMovement.startDateTimeTo"/></td>
                    <td><input type="datetime-local"name="startDateTimeTo" value="${param.startDateTimeTo}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcessMovement.finalDateTimeFrom"/></td>
                    <td><input type="datetime-local"name="finalDateTimeFrom" value="${param.finalDateTimeFrom}"></td>
                    <td><spring:message code="workflow.wfProcessMovement.finalDateTimeTo"/></td>
                    <td><input type="datetime-local"name="finalDateTimeTo" value="${param.finalDateTimeTo}"></td>
                </tr>
                
                <div class="text-right">
                    <a class="btn btn-outline-primary" role="button" href="wfProcessMovements">
                        <spring:message code="app.workflow.wfProcessMovements"/>
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
