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
        <div class="col-md-12 col-lg-12 col-xs-12 col-md-12">
            <div class="text-left wrap-header-page">
                <h3>Cartered Employees <span class="badge" ng-cloak ng-bind="arrData.length"/></h3>
            </div>
            <div ng-cloak class="row wrap-filter col-md-12 col-lg-12 col-xs-12 col-md-12">
                <div class="col-sm-offset-2 col-sm-4 form-horizontal">
                    <div class="form-group">
                        <span class="col-sm-4 m-t-5 m-b-5">From date</span>
                        <div class="input-group col-sm-8">
                            <input type="text" id="date-filter-from" date-range-picker-single ng-change="dateChange()"
                                   ng-model="fromDate" class="form-control">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4 form-horizontal">
                    <div class="form-group">
                        <span class="col-sm-4 m-t-5 m-b-5">To date</span>
                        <div class="input-group col-sm-8">
                            <input type="text" id="date-filter-to" date-range-picker-single ng-change="dateChange()"
                                   ng-model="toDate" class="form-control">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
                <%--<div class="col-sm-3 p-l-0 m-t-10">--%>
                    <%--<div class="btn btn-success btn-default" ng-click="filterAccept()">Catering Date<span class="glyphicon glyphicon-search p-l-10"></span></div>--%>
                <%--</div>--%>
            </div>
            <div ng-cloak class="row wrap-filter col-md-12 col-lg-12 col-xs-12 col-md-12">
                <div class="col-sm-3 form-horizontal">
                    <div class="form-group">
                        <span class="col-sm-4 m-t-5 m-b-5">Meal time</span>
                        <div class="input-group col-sm-8 col-xs-12">
                            <select ng-model="mealTimeIdFilter" class="form-control">
                                <option value="{{mealTime.mealTimeId}}" ng-repeat="mealTime in mealTimeArr">{{mealTime.mealTimeName}}</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3 form-horizontal">
                    <div class="form-group">
                        <span class="col-sm-4 m-t-5 m-b-5">Meal type</span>
                        <div class="input-group col-sm-8 col-xs-12">
                            <select ng-model="mealIdFilter" class="form-control">
                                <option value="{{meal.mealId}}" ng-repeat="meal in mealArr">{{meal.mealName}}</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3 form-horizontal">
                    <div class="form-group">
                        <span class="col-sm-4 m-t-5 m-b-5">Location</span>
                        <div class="input-group col-sm-8 col-xs-12">
                            <select ng-model="locationIdFilter" class="form-control">
                                <option value="{{location.locationId}}" ng-repeat="location in locationArr">{{location.locationName}}</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3 form-horizontal">
                    <div class="form-group">
                        <span class="col-sm-6 m-t-5 m-b-5">Department</span>
                        <div class="input-group col-sm-6 col-xs-12">
                            <select ng-model="departmentIdFilter" class="form-control">
                                <option value="{{department.deptId}}" ng-repeat="department in departmentArr">{{department.deptName}}</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div ng-cloak class="row m-b-10 col-md-12 col-lg-12 col-xs-12 col-md-12 text-left">
                <span class="vcenter">Row count: </span><span class="text-danger vcenter fs-18" ng-cloak ng-bind="tableParams.total()"/>
            </div>
        </div>
    </div>
    <div class="row text-center" >
        <div class="col-md-12 col-lg-12 col-xs-12 col-md-12 wrap-meal-table">
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
    <div class=" row text-center m-l-0 m-r-0  m-t-10" ng-if="tableParams.total() > 0">
        <div class="col-md-12  wrap-info highlight text-left">
            <div ng-repeat="department in departmentArr" ng-if="department.count > 0">
                <span>Department <span ng-bind="department.deptName"></span> : <span ng-bind="department.count"></span></span>
            </div>
        </div>
    </div>
    <div class="row m-t-10">
        <div class="btn btn-success btn-lg pull-right m-r-15" ng-click="exportData()">EXPORT DATA<span class="glyphicon glyphicon-download-alt p-l-10"></span></div>
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