<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en" ng-app="availableDisksModule">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title><spring:message code="available.title"/></title>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/app.css">
    <script src="/js/angular.min.js"></script>

    <script>
        (function () {
            var mod = angular.module("availableDisksModule", []);
            mod.controller("availableDisksController", ['$scope', '$http', function ($scope, $http) {

                var dataloadErrorMsg = '<spring:message code="error.dataload"/>';
                var actionErrorMsg = '<spring:message code="error.action"/>';
                $scope.disks = [];
                $scope.hasError = false;
                $scope.errorMsg = "";

                $http.get("/disk/available/list", {}).then(
                    function (resp) {
                        console.log("success", resp);
                        $scope.disks = resp.data;
                    },
                    function (errResp) {
                        console.error("error", errResp);
                        $scope.hasError = true;
                        $scope.errorMsg = dataloadErrorMsg;
                    }
                );

                var removeDisk = function (disk) {
                    for (var i = 0; i < $scope.disks.length; i++) {
                        if ($scope.disks[i] === disk) {
                            $scope.disks.splice(i, 1);
                        }
                    }
                };

                $scope.take = function (index) {
                    var disk = $scope.disks[index];
                    console.log("take disk", disk, index, $scope.disks);

                    $http.post('/disk/take', disk, {}).then(function (resp) {
                            console.log("success", resp);
                            removeDisk(disk);
                        },
                        function (errResp) {
                            console.error("error", errResp);
                            $scope.hasError = true;
                            $scope.errorMsg = actionErrorMsg;
                        });
                };
            }]);
        })();

    </script>
</head>
<body ng-controller="availableDisksController">

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">DVD Exchange</a>
        </div>

        <ul class="nav navbar-nav">
            <li class="active"><a href="/disk/available"><spring:message code="menu.item.available.text"/></a></li>
            <li><a href="/disk/add"><spring:message code="menu.item.add.text"/></a></li>
            <li><a href="/disk/taken"><spring:message code="menu.item.taken.text"/></a></li>
            <li><a href="/disk/given"><spring:message code="menu.item.given.text"/></a></li>
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
                        <spring:message code="available.title"/>
                    </h2>
                </div>
                <div class="panel-body">
                    <table class="table table-striped the-table">
                        <thead>
                        <tr>
                            <th class="text-left col-md-8"><spring:message code="available.table.diskCol"/></th>
                            <th class="text-left col-md-3"><spring:message code="available.table.ownerCol"/></th>
                            <th class="text-center col-md-1"><spring:message code="available.table.takeCol"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="disk in disks">
                            <td class="col-md-8">{{disk.name}}</td>
                            <td class="col-md-3">{{disk.owner.name}}</td>
                            <td class="text-center col-md-1">
                                <span ng-click="take($index)"
                                      class="gi-2x glyphicon glyphicon-floppy-saved pointer"></span>
                            </td>
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