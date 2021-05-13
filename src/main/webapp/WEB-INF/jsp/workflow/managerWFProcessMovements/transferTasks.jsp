<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <title>transferTasks</title>
</head>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="mwfProcessMovementsUrl" value="/workflow/managerWFProcessMovements/transferOnState" />
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
                            <select name="processStateToId">
                                <c:forEach items="${processStateList}" var="processState">
                                    <jsp:useBean id="processState" scope="page" type="org.speed.big.company.service.model.workflow.WFProcessState"/>
                                    <option value="${processState.id}" label="${processState.name}"/>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>
                                <spring:message code="workflow.wfBaseProcessItem.baseProcessId"/>
                            </label>
                        </td>
                        <td>
                            <input type="text" name="description" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input <%--type="hidden"--%> name="wfProcessMovementId" value="${wfProcessMovementId}"/>
                        </td>
                        <td>

                        </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                <div class="text-left">
                    <button class="btn btn-primary" type="submit" name="wfProcessMovementId2" value="${wfProcessMovementId}">
                        <spring:message code="workflow.managerWFProcessMovements.inWork"/>
                    </button>
                </div>
            </footer>
        </form>
    </div>
</div>
</body>
</html>
