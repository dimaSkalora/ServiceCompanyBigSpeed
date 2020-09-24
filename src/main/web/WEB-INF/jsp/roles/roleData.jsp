<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title>Role Data</title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs box taxiOrderAcceptance_nav_tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#role"><spring:message code="app.roles"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#roleType"><spring:message code="app.roleTypes"/></a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="role">
                <form:form method="POST" modelAttribute="roleData">
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="role.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                                    <form:hidden path="id"/>
                                </td>
                                <td>
                                    <form:label path="name">
                                        <spring:message code="role.name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="description">
                                        <spring:message code="role.description"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="description"/>
                                </td>
                                <td>
                                    <form:label path="roleTypeId">
                                        <spring:message code="role.roleTypeId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:select path="roleTypeId">
                                        <form:option  value="${!empty role.id ? role.id : ''}"
                                                      label="${!empty role.id ? role.name : ''}"/>
                                        <%--<form:option  value="" label=""/>--%>
                                        <c:forEach items="${allRoleTypes}" var="roleType">
                                            <jsp:useBean id="roleType" scope="page" type="org.speed.big.company.service.model.RoleType"/>
                                            <form:option  value="${roleType.id}" label="${roleType.name}"/>
                                        </c:forEach>
                                    </form:select>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/CompanySpeedyTaxi/roles" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="roleType">
                <form:form method="POST" modelAttribute="rRoleType" >
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="roleType.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                                    <form:hidden path="id"/>
                                </td>
                                <td>
                                    <form:label path="name">
                                        <spring:message code="roleType.name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/CompanySpeedyTaxi/roles" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
