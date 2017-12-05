<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title><spring:message code="signup.title"/></title>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class=" navbar-header">
            <a class="navbar-brand" href="#">DVD Exchange</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">

        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="text-center">
                        <spring:message code="signup.title"/>
                    </h2>
                </div>
                <div class="panel-body">

                    <form:form modelAttribute="signupForm" method="post">
                        <spring:bind path="name">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <spring:message code="signin.username.placeholder" var="signinUsernamePlaceholder"/>
                                <form:input type="text" path="name" class="form-control" id="login"
                                            placeholder="${signinUsernamePlaceholder}" required=""></form:input>
                            </div>
                            <form:errors path="name" element="div" cssClass="alert alert-danger"></form:errors>
                        </spring:bind>

                        <spring:bind path="password">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <spring:message code="signin.password.placeholder" var="signinPasswordPlaceholder"/>
                                <form:input type="password" path="password" class="form-control"
                                            placeholder="${signinPasswordPlaceholder}"
                                            id="password" required=""></form:input>
                            </div>
                            <form:errors path="password" element="div" cssClass="alert alert-danger"></form:errors>
                        </spring:bind>

                        <spring:bind path="confirmPassword">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <spring:message code="signup.password.confirm.placeholder" var="signinPasswordConfirmPlaceholder"/>
                                <form:input type="password" path="confirmPassword" class="form-control"
                                            placeholder="${signinPasswordConfirmPlaceholder}"
                                            id="confirmPassword" required=""></form:input>
                            </div>
                            <form:errors path="confirmPassword" element="div"
                                         cssClass="alert alert-danger"></form:errors>
                        </spring:bind>

                        <button type="submit" class="btn btn-primary btn-block btn-lg"><spring:message code="signup.button.text"/></button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>