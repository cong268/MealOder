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
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MEAL MANAGERMENT</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/mealManagermentStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/lodash.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/underscore-min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/mealManagerment.js"></c:url>"></script>
</head>
<body ng-controller="mealManagermentCtrl">
    <div class="container wrap-meal-managerment">
        <div class="row text-center" >
            <div class="col-md-12 col-lg-12 col-xs-12 col-md-12 wrap-meal-table">
                <div class="text-left wrap-header-page">
                    <h3>Meal Managerment</h3>
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
                <table ng-cloak ng-table="tableParams" class="table table-condensed table-bordered table-striped table-custom"
                       ng-class="{'nomarginbottom': tableParams.total() == 0}">
                    <tr ng-repeat="row in $data">
                        <td data-title="'EmployeeCode'" sortable="'StaffId'">{{row.StaffId}}</td>
                        <td data-title="'Fullname'" sortable="'StaffName'">{{row.StaffName}}</td>
                        <td data-title="'Meal Time'" >
                            <select ng-model="row.MealTimeId" class="form-control">
                                <option ng-selected="row.MealTimeId == mealTime.MealTimeId"
                                        ng-repeat="mealTime in mealTimeArr"
                                        ng-value="mealTime.MealTimeId">{{mealTime.MealTimeName}}</option>
                            </select>
                        </td>
                        <td data-title="'Location'">
                            <select ng-model="row.LocationId" class="form-control">
                                <option ng-selected="row.LocationId == location.LocationId"
                                        ng-repeat="location in locationArr"
                                        ng-value="location.LocationId">{{location.LocationName}}</option>
                            </select>
                        </td>
                        <td data-title="'Meal Type'">
                            <select ng-model="row.MealId" class="form-control">
                                <option ng-selected="row.MealId == meal.MealId"
                                        ng-repeat="meal in mealArr"
                                        ng-value="meal.MealId">{{meal.MealName}}</option>
                            </select>
                        </td>
                        <td width="10" style="text-align: center" data-header="'checkbox.html'">
                            <label class="control control-checkbox">
                                <input type="checkbox"
                                       ng-model="row.Status"
                                       ng-true-value="1"
                                       ng-false-value="0"
                                       ng-checked="row.Status == 1">
                                <div class="control-indicator"></div>
                            </label>
                        </td>
                    </tr>
                </table>
                <div class="showNoData" ng-if="tableParams.total() == 0" ng-cloak>No data available</div>
            </div>
        </div>
        <%--<pre style="margin-top: 20px;">{{arrData | json}}</pre>--%>
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
</html>
