<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<head>
    <title>User Role Data</title>
</head>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs box taxiOrderAcceptance_nav_tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#userRole"><spring:message code="app.userRoles"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#user"><spring:message code="app.users"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#role"><spring:message code="app.roles"/></a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="userRole">
                <form:form method="POST" modelAttribute="userRoleData" >
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="userRole.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                                    <form:hidden path="id"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="userId">
                                        <spring:message code="userRole.userId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="text" path="userId" readonly="true" />
                                   <%-- <form:select path="userId">
                                        <form:option  value="${!empty userRole.id ? userRole.id : ''}"
                                                      label="${!empty userRole.id ? userRole.userId.name : ''}"/>
                                        &lt;%&ndash;<form:option  value="" label=""/>&ndash;%&gt;
                                    </form:select>--%>
                                </td>
                                <td>
                                    <form:label path="roleId">
                                        <spring:message code="userRole.roleId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="text" path="roleId" readonly="true" />
                               <%--     <form:select path="roleId">
                                        <form:option  value="${!empty userRole.id ? userRole.id : ''}"
                                                      label="${!empty userRole.id ? userRole.roleId.name : ''}"/>
                                        &lt;%&ndash;<form:option  value="" label=""/>&ndash;%&gt;
                                    </form:select>--%>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="dateTime">
                                        <spring:message code="userRole.dateTime"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="dateTime" readonly="true" />
                                </td>
                                <td>
                                    <form:label path="comment">
                                        <spring:message code="userRole.comment" />
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="text" path="comment" readonly="true" />
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/userRoles" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="user">
                <form:form method="POST" modelAttribute="urUser">
                    <fieldset>
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="user.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                                    <form:hidden path="id"/>
                                </td>
                                <td>
                                    <form:label path="name">
                                        <spring:message code="user.name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name" readonly="true" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="password">
                                        <spring:message code="user.password"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:password path="password" readonly="true" />
                                </td>
                                <td>
                                    <form:label path="email">
                                        <spring:message code="user.email"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="email" readonly="true" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="phone">
                                        <spring:message code="user.phone"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="phone" readonly="true" />
                                </td>
                            </tr>
                            <c:if test="${!empty user.name}">
                                <tr>
                                    <td>
                                        <form:label path="registered">
                                            <spring:message code="user.registered"/>
                                        </form:label>
                                    </td>
                                    <td>
                                        <form:input type="data" path="registered" readonly="true" />
                                    </td>
                                    <td>
                                        <form:label path="enabled">
                                            <spring:message code="user.enabled"/>
                                        </form:label>
                                    </td>
                                    <td>
                                        <form:checkbox path="enabled" readonly="true" />
                                    </td>
                                </tr>
                            </c:if>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/userRoles" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="role">
                <form:form method="POST" modelAttribute="urRole" >
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
                                    <form:input path="name" readonly="true" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="description">
                                        <spring:message code="role.description"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="description" readonly="true" />
                                </td>
                                <td>
                                    <form:label path="roleTypeId">
                                        <spring:message code="role.roleTypeId"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="roleTypeId" readonly="true" />
                                  <%--  <form:select path="roleTypeId">
                                        <form:option  value="${!empty role.id ? role.id : ''}"
                                                      label="${!empty role.id ? role.name : ''}"/>
                                        &lt;%&ndash;<form:option  value="" label=""/>&ndash;%&gt;
                                    </form:select>--%>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/ServiceCompanyBigSpeed/userRoles" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
