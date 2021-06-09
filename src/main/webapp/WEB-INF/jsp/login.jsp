<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale}<b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a></li>
                    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a></li>
                    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=uk">Український</a></li>
                    <%--                    <li><a href="?lang=en">English</a></li>
                                        <li><a href="?lang=ru">Русский</a></li>--%>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<div class="jumbotron">
    <div class="container">
        <div class="lead">
            <a href="https://github.com/dimaSkalora/ServiceCompanyBigSpeed">Service Company Big Speed</a>

        </div>
        <div class="container mt-5 mb-5 d-flex justify-content-center">
            <div class="card px-1 py-4">
                <div class="card-body">
                    <form name='login' action="login" method='POST'>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <!-- <label for="name">Name</label> --> <input class="form-control" type="text" name='username'placeholder="Email"> </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <div class="input-group"> <input class="form-control" type="password" name='password' placeholder="Password"> </div>
                                </div>
                            </div>
                        </div>
                        <input name="submit" type="submit" value="Sign In" class="btn btn-primary btn-block confirm-button"/>
                        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
                    </form>
                    <br>
                        <a class="btn btn-primary btn-block confirm-button"href="register">Register</a>
                        <%--<a class="btn btn-lg btn-success" href="register">Register</a>--%>
                </div>
            </div>
        </div>
        <div>
            <button type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('admin', 'admin')">
                User
            </button>
            <button type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('user1', 'user1')">
                Admin
            </button>
        </div>
        <div>
            Стек технологий: <a href="https://spring.io/projects/spring-boot/">Spring Boot</a>,
            <a href="https://spring.io/projects/spring-data-jpa/">Spring Data JPA</a>,
            <a href="http://projects.spring.io/spring-security/">Spring Security</a>,
            <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
            <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
            <a href="http://www.slf4j.org/">SLF4J</a>,
            <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
            <a href="http://ru.wikipedia.org/wiki/JSP">JSP</a>,
            <a href="http://en.wikipedia.org/wiki/JavaServer_Pages_Standard_Tag_Library">JSTL</a>,
            <a href="http://tomcat.apache.org/">Apache Tomcat</a>,
            <a href="http://www.webjars.org/">WebJars</a>,
            <a href="http://www.postgresql.org/">PostgreSQL</a>,
            <a href="http://getbootstrap.com/">Bootstrap</a>.
        </div>
    </div>
</div>

<script type="text/javascript">
    <c:if test="${not empty param.username}">
    setCredentials("${param.username}", "");
    </c:if>
    function setCredentials(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }
</script>
</body>
</html>