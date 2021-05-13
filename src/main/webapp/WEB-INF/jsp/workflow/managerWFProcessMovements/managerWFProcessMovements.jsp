<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>ManagerWFProcessMovements</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="mwfProcessMovementsUrl" value="/managerWFProcessMovements/processMovements" />
        <form method="POST" action="${mwfProcessMovementsUrl}">
            <fieldset>
                <table border="0" cellpadding="8" cellspacing="0">
                    <tr>
                        <td>
                            <label>
                                <spring:message code="workflow.wfBaseProcessItem.baseProcessId"/>
                            </label>
                        </td>
                        <td>
                            <select name="roleId">
                                <option value="" label=""/>
                                <c:forEach items="${getRoleFromUserRoles}" var="role">
                                    <jsp:useBean id="role" scope="page" type="org.speed.big.company.service.model.Role"/>
                                    <option value="${role.id}" label="${role.name}"/>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label>
                                Count Packages
                            </label>
                        </td>
                        <td>
                            <label>
                                ${managerWFProcessMovementsCount}
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>
                                <spring:message code="workflow.wfBaseProcessItem.stateFromId"/>
                            </label>
                        </td>
                        <td>
                            <select name="wfServiceId">
                                <option  value="" label=""/>
                                <c:forEach items="${getWFServiceFromRoles}" var="wfService">
                                    <jsp:useBean id="wfService" scope="page" type="org.speed.big.company.service.model.workflow.WFService"/>
                                    <option value="${wfService.id}" label="${wfService.name}"/>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                <div class="text-left">
                    <button class="btn btn-primary" type="submit" name="indexInWork" value="1">
                        <spring:message code="workflow.managerWFProcessMovements.inWork"/>
                    </button>
                    <button class="btn btn-primary" type="submit" name="indexCompleted" value="2">
                        <spring:message code="workflow.managerWFProcessMovements.completed"/>
                    </button>
                    <button class="btn btn-primary" type="submit" name="indexWaiting" value="3">
                        <spring:message code="workflow.managerWFProcessMovements.waiting"/>
                    </button>
                    <button class="btn btn-primary" type="submit" name="indexArchive" value="4">
                        <spring:message code="workflow.managerWFProcessMovements.archive"/>
                    </button>
                </div>
            </footer>
        </form>
    </div>

    <div class="container">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>transferTasks</th>
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
            <c:forEach items="${managerWFProcessMovements}" var="managerWFProcessMovements">
                <jsp:useBean id="managerWFProcessMovements" scope="page" type="org.speed.big.company.service.model.workflow.WFProcessMovement"/>
                <tr>
                    <td><a href="workflow/managerWFProcessMovements/transferTasks/${managerWFProcessMovements.wfStateId.id}/${managerWFProcessMovements.wfBaseProcessId.id}/${managerWFProcessMovements.id}"/>
                        <c:out value="transferTasks"/></td>
                    <td><a href="workflow/managerWFProcessMovements/wfProcessPackage/${managerWFProcessMovements.wfPackageId.id}"/><c:out value="${managerWFProcessMovements.wfPackageId.id}"/></td>
                    <td><c:out value="${managerWFProcessMovements.startDateTime}"/></td>
                    <td><c:out value="${managerWFProcessMovements.finalDateTime}"/></td>
                    <td><c:out value="${managerWFProcessMovements.completed}"/></td>
                    <td><c:out value="${managerWFProcessMovements.description}"/></td>
                    <td><c:out value="${managerWFProcessMovements.dateEdit}"/></td>
                    <td><c:out value="${managerWFProcessMovements.userEdit}"/></td>
                    <td><c:out value="${managerWFProcessMovements.userId}"/></td>
                    <td><c:out value="${managerWFProcessMovements.wfPackageId}"/></td>
                    <td><c:out value="${managerWFProcessMovements.wfStateId}"/></td>
                    <td><c:out value="${managerWFProcessMovements.wfProcessId}"/></td>
                    <td><c:out value="${managerWFProcessMovements.wfBaseProcessId}"/></td>
                    <td><c:out value="${managerWFProcessMovements.last}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
