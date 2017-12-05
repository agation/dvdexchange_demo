<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <sec:csrfMetaTags/>
    <title><spring:message code="signin.title"/></title>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class=" navbar-header">
            <a class="navbar-brand" href="/">DVD Exchange</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="text-center">
                        <spring:message code="signin.title"/>
                    </h2>
                </div>
                <div class="panel-body">
                    <form action="/signin" method="post">
                        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                        <sec:csrfInput/>

                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger text-center"><strong>
                                <spring:message code="error.signin.username.not.found"/></strong></div>
                        </c:if>

                        <c:if test="${param.logout != null}">
                            <div class="alert alert-info text-center"><strong><spring:message code="signin.message.logout"/></strong></div>
                        </c:if>

                        <div class="form-group">
                            <input type="text" name="name" class="form-control" id="login" placeholder='<spring:message code="signin.username.placeholder"/>'
                                   required>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" class="form-control" id="password"
                                   placeholder="<spring:message code="signin.password.placeholder"/>" required>
                        </div>
                        <div class="form-group text-center">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-2">
                                    <a href="?lang=ru"
                                       class="btn ${langCode == 'ru' ? 'btn-warning active' : 'btn-primary'}"><spring:message code="signin.locale.ru.text"/></a>
                                    <a href="?lang=en"
                                       class="btn ${langCode == 'en' ? 'btn-warning active' : 'btn-primary'}"><spring:message code="signin.locale.en.text"/></a>
                                </div>
                            </div>
                        </div>
                        <div class="form-group text-center">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-2">
                                    <button type="submit" class="btn btn-primary btn-lg btn-block"><spring:message code="signin.button.text"/></button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group text-center">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-2">
                                    <a href="/signup"><spring:message code="signup.title"/></a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>