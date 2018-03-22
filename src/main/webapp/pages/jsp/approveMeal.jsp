<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 12/03/2018
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<c:if test = "${sessionScope.userRole == 'Manager'}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Approve Meal</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/approveMealStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/approveMeal.js"></c:url>"></script>
</head>
<body ng-controller="approveMealCtrl" ng-init="initData()">
    <div class="container wrap-aprove-managerment">
        <div class="row text-center" >
            <div class="col-md-12 col-lg-12 col-xs-12 col-md-12 wrap-meal-table">
                <div class="text-left wrap-header-page">
                    <h3>Approve Meal <span class="badge" ng-cloak ng-bind="tableParams.total()"/></h3>
                </div>
                <div ng-cloak class="row wrap-filter col-md-5 col-lg-5 col-xs-5 col-md-5 pull-left">
                    <div class="col-sm-3 p-l-0 m-t-10 p-t-5">
                        Date
                    </div>
                    <div class="col-sm-5 p-l-0 m-t-10">
                        <div class="input-group">
                            <input type="text" ng-model="dateFilter" id="date-filter-input" date-range-picker-single
                                   ng-model="dateFilter" class="form-control">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="col-sm-4 p-l-0 m-t-10">
                        <div class="btn btn-success btn-default" ng-click="filterAccept()">Catering Date<span class="glyphicon glyphicon-search p-l-10"></span></div>
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
                        <td width="10" style="text-align: center" data-header="'checkbox.html'">
                            <label class="control control-checkbox">
                                <input type="checkbox"
                                       ng-model="row.checked"
                                       ng-true-value="1"
                                       ng-false-value="0"
                                       ng-change="checkStatus()"
                                       ng-checked="row.checked == 1">
                                <div class="control-indicator"></div>
                            </label>
                        </td>
                    </tr>
                </table>
                <div class="showNoData" ng-if="tableParams.total() == 0" ng-cloak>No data available</div>
            </div>
        </div>
        <div class="row m-t-10">
            <div class="btn btn-primary btn-lg pull-right m-r-15" ng-click="submitApprove()">APPROVE</div>
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
        <h1><spring:message code="label.access_denied"/> <span class="badge p-t-8 p-b-8 p-l-8 p-r-8">404</span></h1>
        <h3><spring:message code="label.access_denied_info"/></h3>
    </div>
</c:if>
</html>