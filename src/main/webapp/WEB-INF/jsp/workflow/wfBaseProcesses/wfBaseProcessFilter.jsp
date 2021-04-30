<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>WFBaseProcess Filter</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">

        <c:url var="createOrUpdateUrl" value="/wfBaseProcesses/filter" />
        <form method="post" action="${createOrUpdateUrl}" >
            <table border="0" cellpadding="8" cellspacing="0">
                <tr>
                    <td><spring:message code="workflow.wfBaseProcess.id"/></td>
                    <td><input type="number" name="id" value="${param.id}"></td>
                    <td><spring:message code="workflow.wfBaseProcess.name"/></td>
                    <td><input type="text" name="name" value="${param.name}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfBaseProcess.description"/></td>
                    <td><input type="text" name="description" value="${param.description}"></td>
                </tr>
                <tr>
                    <td><spring:message code="workflow.wfBaseProcess.wfServiceId"/></td>
                    <td><input type="number" name="wfServiceId" value="${param.wfServiceId}"></td>
                    <td><spring:message code="workflow.wfBaseProcess.wfBaseProcessTypeId"/></td>
                    <td><input type="number" name="wfBaseProcessTypeId" value="${param.wfBaseProcessTypeId}"></td>
                </tr>
                <div class="text-right">
                    <a class="btn btn-outline-primary" role="button" href="wfBaseProcesses">
                        <spring:message code="app.workflow.wfBaseProcesses"/>
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
