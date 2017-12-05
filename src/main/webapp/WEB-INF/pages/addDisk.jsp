<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title><spring:message code="add.title"/></title>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">DVD Exchange</a>
        </div>

        <ul class="nav navbar-nav">
            <li><a href="/disk/available"><spring:message code="menu.item.available.text"/></a></li>
            <li class="active"><a href="/disk/add"><spring:message code="menu.item.add.text"/></a></li>
            <li><a href="/disk/taken"><spring:message code="menu.item.taken.text"/></a></li>
            <li><a href="/disk/given"><spring:message code="menu.item.given.text"/></a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> <spring:message code="menu.item.logout.text"/></a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="text-center">
                        <spring:message code="add.title"/>
                    </h2>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="diskForm" method="post" class="form-horizontal">
                        <spring:bind path="name">
                            <div class="form-group">
                                <label for="diskName" class="control-label col-md-3"><spring:message code="add.disk.name"/></label>
                                <div class="col-md-8">
                                    <spring:message code="add.disk.name.placeholder" var="addDiskNamePlaceholder"/>
                                    <form:input path="name" type="text" class="form-control" id="diskName" required=""
                                                placeholder="${addDiskNamePlaceholder}"></form:input>
                                </div>
                            </div>
                            <form:errors path="name" element="div" cssClass="alert alert-danger"></form:errors>
                        </spring:bind>

                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-4">
                                <button type="submit" class="btn btn-primary"><spring:message code="add.button.add"/></button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>