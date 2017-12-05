<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en" ng-app="givenModule">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title><spring:message code="given.title"/></title>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/app.css">
    <script src="/js/angular.min.js"></script>


    <script>
        (function () {
            var mod = angular.module("givenModule", []);
            mod.controller("givenController", ['$scope', '$http', function ($scope, $http) {

                $scope.items = [];
                $scope.hasError = false;
                $scope.errorMsg = '<spring:message code="error.dataload"/>';

                $http.get("/disk/given/list", {}).then(
                    function (resp) {
                        console.log("success", resp);
                        $scope.items = resp.data;
                    },
                    function (errResp) {
                        console.error("error", errResp);
                        $scope.hasError = true;
                    }
                );
            }]);
        })();

    </script>
</head>
<body ng-controller="givenController">

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">DVD Exchange</a>
        </div>

        <ul class="nav navbar-nav">
            <li><a href="/disk/available"><spring:message code="menu.item.available.text"/></a></li>
            <li><a href="/disk/add"><spring:message code="menu.item.add.text"/></a></li>
            <li><a href="/disk/taken"><spring:message code="menu.item.taken.text"/></a></li>
            <li class="active"><a href="/disk/given"><spring:message code="menu.item.given.text"/></a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> <spring:message
                    code="menu.item.logout.text"/></a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="text-center">
                        <spring:message code="given.title"/>
                    </h2>
                </div>
                <div class="panel-body">
                    <table class="table table-striped the-table">
                        <thead>
                        <tr>
                            <th class="text-left col-md-8"><spring:message code="given.table.diskCol"/></th>
                            <th class="text-left col-md-3"><spring:message code="given.table.tookCol"/></th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr ng-repeat="item in items">
                            <td class="col-md-8">{{item.disk.name}}</td>
                            <td class="col-md-3">{{item.user.name}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div ng-show="hasError" class="alert alert-danger message"><h3 class="text-center inner-message">{{errorMsg}}</h3>
    </div>
</div>
</body>
</html>