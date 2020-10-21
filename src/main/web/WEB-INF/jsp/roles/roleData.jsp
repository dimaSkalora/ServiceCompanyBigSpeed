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
        <ul class="nav nav-tabs">
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
                                    <form:input path="name" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="description">
                                        <spring:message code="role.description"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="description" readonly="true"/>
                                </td>
                                <td>
                                    <form:label path="roleTypeId">
                                        <spring:message code="role.roleTypeId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="roleTypeId" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/roles" class="btnLogin"><spring:message code="app.ok"/></a>
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
                                    <form:input path="name" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/roles" class="btn btn-primary" role="button"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
