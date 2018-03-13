<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 13/03/2018
  Time: 08:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<c:if test = "${sessionScope.userRole == 'Admin'}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cartering</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/showAdminStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/showAdmin.js"></c:url>"></script>
</head>
<body ng-controller="carteredAdminCtrl">
<div class="container wrap-cartered-managerment">
    <div class="row text-center" >
        <div class="col-md-12 col-lg-12 col-xs-12 col-md-12 wrap-meal-table">
            <div class="text-left wrap-header-page">
                <h3>Cartered Employees</h3>
            </div>
            <div class="row wrap-filter">
                <div class="col-md-4 col-lg-4 col-xs-4 col-md-4">
                    <div class="col-xs-6 vcenter"><span>Code filter</span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" class="form-control" ng-model="tableParams.filter()['StaffId']">
                    </div>
                </div>
                <div class="col-md-4 col-lg-4 col-xs-4 col-md-4">
                    <div class="col-xs-6 vcenter">
                        <span>Name filter </span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" class="form-control" ng-model="tableParams.filter()['StaffName']">
                    </div>
                </div>
                <div class="col-md-4 col-lg-4 col-xs-4 col-md-4">
                    <div class="col-xs-6 vcenter">
                        <span>Date filter</span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" class="form-control" ng-model="tableParams.filter()['StaffDate']">
                    </div>
                </div>
            </div>
            <table ng-cloak ng-table="tableParams" class="table table-condensed table-bordered table-striped table-custom">
                <tr ng-repeat="row in $data">
                    <td data-title="'EmployeeCode'" sortable="'StaffId'">{{row.StaffId}}</td>
                    <td data-title="'Fullname'" sortable="'StaffName'">{{row.StaffName}}</td>
                    <td data-title="'Meal Time'" >
                            <span ng-bind="row.MealTimeId">
                            </span>
                    </td>
                    <td data-title="'Location'">
                            <span ng-bind="row.LocationId">
                            </span>
                    </td>
                    <td data-title="'Meal Type'">
                            <span ng-bind="row.MealId">
                            </span>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</c:if>
<c:if test = "${sessionScope.userRole != 'Admin'}">
    <div class="container">
        <h1>ACCESS DENIED <span class="badge p-t-8 p-b-8 p-l-8 p-r-8">404</span></h1>
        <h3>You not have permission to access this page !</h3>
    </div>
</c:if>
</html>