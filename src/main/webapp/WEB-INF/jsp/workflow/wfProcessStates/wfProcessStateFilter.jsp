<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFProcessState Filter</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">

        <c:url var="createOrUpdateUrl" value="/workflow/wfProcessStates/filter" />
        <form method="post" action="${createOrUpdateUrl}" >
            <table border="0" cellpadding="8" cellspacing="0">
                <tr>
                    <td><spring:message code="workflow.wfProcessState.id"/></td>
                    <td><input type="number" name="id" value="${param.id}"></td>
                    <td><spring:message code="workflow.wfProcessState.name"/></td>
                    <td><input type="text" name="name" value="${param.name}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcessState.roleId"/></td>
                    <td><input type="number" name="roleId" value="${param.roleId}"></td>
                    <td><spring:message code="workflow.wfProcessState.wfGroupId"/></td>
                    <td><input type="number" name="wfGroupId" value="${param.wfGroupId}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfProcessState.description"/></td>
                    <td><input type="text" name="description" value="${param.description}"></td>
                </tr>
                <div class="text-right">
                    <a class="btn btn-outline-primary" role="button" href="workflow/wfProcessStates">
                        <spring:message code="app.workflow.wfProcessStates"/>
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
