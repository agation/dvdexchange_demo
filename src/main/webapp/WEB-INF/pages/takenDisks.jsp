<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en" ng-app="takenModule">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title><spring:message code="taken.title"/></title>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/app.css">
    <script src="/js/angular.min.js"></script>

    <script>
        (function () {
            var mod = angular.module("takenModule", []);
            mod.controller("takenController", ['$scope', '$http', function ($scope, $http) {

                var dataloadErrorMsg = '<spring:message code="error.dataload"/>';
                var actionErrorMsg = '<spring:message code="error.action"/>';
                $scope.items = [];
                $scope.hasError = false;
                $scope.errorMsg = "";

                $http.get("/disk/taken/list", {}).then(
                    function (resp) {
                        console.log("success", resp);
                        $scope.items = resp.data;
                    },
                    function (errResp) {
                        console.error("error", errResp);
                        $scope.hasError = true;
                        $scope.errorMsg = dataloadErrorMsg;
                    }
                );

                var removeItem = function (item) {
                    for (var i = 0; i < $scope.items.length; i++) {
                        if ($scope.items[i] === item) {
                            $scope.items.splice(i, 1);
                        }
                    }
                };

                $scope.itemClick = function (index) {
                    var item = $scope.items[index];
                    console.log("return disk", item, index, $scope.items);

                    $http.post('/disk/return', item, {}).then(
                        function (resp) {
                            console.log("return success: resp", resp);
                            removeItem(item);
                        },
                        function (errResp) {
                            console.error("return fail: resp", errResp);
                            $scope.hasError = true;
                            $scope.errorMsg = actionErrorMsg;
                        });
                };
            }]);
        })();

    </script>
</head>
<body ng-controller="takenController">

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">DVD Exchange</a>
        </div>

        <ul class="nav navbar-nav">
            <li><a href="/disk/available"><spring:message code="menu.item.available.text"/></a></li>
            <li><a href="/disk/add"><spring:message code="menu.item.add.text"/></a></li>
            <li class="active"><a href="/disk/taken"><spring:message code="menu.item.taken.text"/></a></li>
            <li><a href="/disk/given"><spring:message code="menu.item.given.text"/></a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> <spring:message code="menu.item.logout.text"/></a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="text-center">
                        <spring:message code="taken.title"/>
                    </h2>
                </div>
                <div class="panel-body">
                    <table class="table table-striped the-table">
                        <thead>
                        <tr>
                            <th class="text-left col-md-8"><spring:message code="taken.table.diskCol"/></th>
                            <th class="text-left col-md-3"><spring:message code="taken.table.ownerCol"/></th>
                            <th class="text-center col-md-1"><spring:message code="taken.table.returnCol"/></th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr ng-repeat="item in items">
                            <td class="col-md-8">{{item.disk.name}}</td>
                            <td class="col-md-3">{{item.disk.owner.name}}</td>
                            <td class="text-center col-md-1">
                                <span ng-click="itemClick($index)" class="gi-2x glyphicon glyphicon-floppy-remove pointer"></span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div ng-show="hasError" class="alert alert-danger message"><h3 class="text-center inner-message">{{errorMsg}}</h3></div>
</div>
</body>
</html>