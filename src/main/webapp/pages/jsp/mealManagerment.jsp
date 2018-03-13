<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 11/03/2018
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<c:if test = "${sessionScope.userRole == 'Manager'}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MEAL MANAGERMENT</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/mealManagermentStyle.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/css/mainTemplayeStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/lodash.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/underscore-min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/mealManagerment.js"></c:url>"></script>
</head>
<body ng-controller="mealManagermentCtrl" ng-init="initData()">
    <div class="container wrap-meal-managerment">
        <div class="row text-center" ng-cloak>
            <div class="col-md-12 col-lg-12 col-xs-12 col-md-12 wrap-meal-table">
                <div class="text-left wrap-header-page">
                    <h3>Meal Managerment <span class="badge" ng-cloak ng-bind="tableParams.total()"></span></h3>
                </div>
                <div ng-cloak class="row wrap-filter col-md-5 col-lg-5 col-xs-5 col-md-5 pull-left">
                    <div class="col-sm-3 p-l-0 m-t-10">
                        <select ng-model="filterType" ng-init="filterType = 'date'" class="form-control">
                            <option value="code">Code</option>
                            <option value="name">Name</option>
                            <option value="date">Date</option>
                        </select>
                    </div>
                    <div class="col-sm-5 p-l-0 m-t-10">
                        <input type="text" ng-if="filterType == 'code'" id="codeFilter" class="form-control" ng-model="tableParams.filter()['staffId']">
                        <input type="text" ng-if="filterType == 'name'" id="nameFilter" class="form-control" ng-model="tableParams.filter()['staffName']">
                        <div class="input-group" ng-if="filterType == 'date'">
                            <input type="text" ng-if="filterType == 'date'" id="date-filter-input" date-range-picker-single
                                   ng-model="dateFilter" class="form-control">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                    <div class="col-sm-4 p-l-0 m-t-10" ng-if="filterType == 'date'">
                        <div class="btn btn-success btn-default" ng-click="filterAccept()">Catering Date<span class="glyphicon glyphicon-search p-l-10"></span></div>
                    </div>
                </div>
                <table ng-cloak ng-table="tableParams" class="table table-condensed table-bordered table-striped table-custom"
                       ng-class="{'nomarginbottom': tableParams.total() == 0}">
                    <tr ng-repeat="row in $data">
                        <td data-title="'EmployeeCode'" sortable="'staffId'">{{row.staffId}}</td>
                        <td data-title="'Fullname'" sortable="'staffName'">{{row.staffName}}</td>
                        <td data-title="'Meal Time'" >
                            <select ng-model="row.mealTimeId" class="form-control">
                                <option ng-selected="row.mealTimeId == mealTimeObj.mealTimeId"
                                        ng-repeat="mealTimeObj in mealTimeArr"
                                        ng-value="mealTimeObj.mealTimeId">{{mealTimeObj.mealTimeName}}</option>
                            </select>
                        </td>
                        <td data-title="'Location'">
                            <select ng-model="row.locationId" class="form-control">
                                <option ng-selected="row.locationId == locationObj.locationId"
                                        ng-repeat="locationObj in locationArr"
                                        ng-value="locationObj.locationId">{{locationObj.locationName}}</option>
                            </select>
                        </td>
                        <td data-title="'Meal Type'">
                            <select ng-model="row.mealId" class="form-control">
                                <option ng-selected="row.mealId == mealObj.mealId"
                                        ng-repeat="mealObj in mealArr"
                                        ng-value="mealObj.mealId">{{mealObj.mealName}}</option>
                            </select>
                        </td>
                        <td data-title="'Shift'">
                            <select ng-model="row.shiftId" class="form-control">
                                <option ng-selected="row.shiftId == shilfObj.shiftId"
                                        ng-repeat="shilfObj in shilfArr"
                                        ng-value="shilfObj.shiftId">{{shilfObj.shiftName}}</option>
                            </select>
                        </td>
                        <td width="10" style="text-align: center" data-header="'checkbox.html'">
                            <label class="control control-checkbox">
                                <input type="checkbox"
                                       ng-model="row.status"
                                       ng-true-value="1"
                                       ng-false-value="0"
                                       ng-change="checkStatus()"
                                       ng-checked="row.status == 1">
                                <div class="control-indicator"></div>
                            </label>
                        </td>
                    </tr>
                </table>
                <div class="showNoData" ng-if="tableParams.total() == 0" ng-cloak>No data available</div>
            </div>
        </div>
        <div class="row m-t-10">
            <div class="btn btn-primary btn-lg pull-right m-r-15" ng-click="submitMealManager()">SUBMIT</div>
        </div>
    </div>
    <script type="text/ng-template" id="checkbox.html">
        <label  class="control control-checkbox">
            <input type="checkbox" ng-model="statusMeal" ng-true-value="true"
                   ng-false-value="false"
                   id="select_all"
                   ng-checked="statusMeal === true"
                   ng-model-options="{getterSetter: true}" ng-click="changeStatus(statusMeal)">
            <div class="control-indicator"></div>
        </label>
    </script>
</body>
</c:if>
<c:if test = "${sessionScope.userRole != 'Manager'}">
    <div class="container">
        <h1>ACCESS DENIED <span class="badge p-t-8 p-b-8 p-l-8 p-r-8">404</span></h1>
        <h3>You not have permission to access this page !</h3>
    </div>
</c:if>
</html>

