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
<body ng-controller="carteredAdminCtrl" ng-init="initData()">
<div class="container wrap-cartered-managerment">
    <div class="row text-center" >
        <div class="col-md-12 col-lg-12 col-xs-12 col-md-12 wrap-meal-table">
            <div class="text-left wrap-header-page">
                <h3>Cartered Employees <span class="badge" ng-cloak ng-bind="tableParams.total()"/></h3>
            </div>
            <div ng-cloak class="row wrap-filter col-md-7 col-lg-7 col-xs-7 col-md-7 pull-left">
                <div class="col-sm-2 p-l-0 m-t-10">
                    <select ng-model="filterType" ng-init="filterType = 'date'" class="form-control">
                        <option value="code">Code</option>
                        <option value="name">Name</option>
                        <option value="date">Date</option>
                    </select>
                </div>
                <div class="col-sm-3 p-l-0 m-t-10">
                    <input type="text" ng-if="filterType == 'code'" id="codeFilter" class="form-control" ng-model="tableParams.filter()['staffId']">
                    <input type="text" ng-if="filterType == 'name'" id="nameFilter" class="form-control" ng-model="tableParams.filter()['staffName']">
                    <input type="text" ng-if="filterType == 'date'" id="date-filter-from" date-range-picker-single
                           ng-model="fromDate" class="form-control">
                </div>
                <div class="col-sm-3 p-l-0 m-t-10" ng-if="filterType == 'date'">
                    <input type="text" id="date-filter-to" date-range-picker-single
                           ng-model="toDate" class="form-control">
                </div>
                <div class="col-sm-3 p-l-0 m-t-10" ng-if="filterType == 'date'">
                    <div class="btn btn-success btn-default" ng-click="filterAccept()">Catering Date</div>
                </div>
            </div>
            <table ng-cloak ng-table="tableParams" class="table table-condensed table-bordered table-striped table-custom"
                   ng-class="{'nomarginbottom': tableParams.total() == 0}">
                <tr ng-repeat="row in $data">
                    <td data-title="'EmployeeCode'" sortable="'staffId'">{{row.staffId}}</td>
                    <td data-title="'Fullname'" sortable="'staffName'">{{row.staffName}}</td>
                    <td data-title="'Meal Time'" >
                        {{getMealTimeName(row.mealTimeId)}}
                    </td>
                    <td data-title="'Location'">
                        {{getLocationName(row.locationId)}}
                    </td>
                    <td data-title="'Meal Type'">
                        {{getMealName(row.mealId)}}
                    </td>
                    <td data-title="'Shift'">
                        {{getShiftName(row.shiftId)}}
                    </td>
                </tr>
            </table>
            <div class="showNoData" ng-if="tableParams.total() == 0" ng-cloak>No data available</div>
        </div>
    </div>
    <div class="row m-t-10">
        <div class="btn btn-success btn-lg pull-right m-r-15" ng-click="exportData()">EXPORT DATA</div>
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