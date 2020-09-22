<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Service Company Big Speed</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="users"><spring:message code="app.users"/></a></li>
            <li><a href="roleTypes"><spring:message code="app.roleTypes"/></a></li>
        </ul>
    </div>
</div>
</body>
</html>
