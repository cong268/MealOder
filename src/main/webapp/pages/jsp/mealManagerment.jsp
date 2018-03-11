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
    <script type="text/javascript" src="<c:url value="/assets/js/lodash.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/underscore-min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/mealManagerment.js"></c:url>"></script>
</head>
<body ng-controller="mealManagermentCtrl">
    <div class="container wrap-meal-managerment">
        <div class="row text-center" >
            <div class="col-md-12 col-lg-12 col-xs-12 col-md-12 wrap-meal-table">
                <h3>Meal Managerment</h3>
                <table ng-table="tableParams" class="table table-condensed table-bordered table-striped table-custom">
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
                        <td width="10" style="text-align: center" header="'ng-table/headers/checkbox.html'">
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
            </div>
        </div>
        <pre style="margin-top: 20px;">{{arrData | json}}</pre>
    </div>
    <script type="text/ng-template" id="ng-table/headers/checkbox.html">
        <label id="select_all" class="control control-checkbox">
            <input type="checkbox" ng-model="statusMeal"
                   ng-true-value="1"
                   ng-false-value="0"
                   ng-change="changeStatus(statusMeal)"
                   ng-checked="statusMeal == 1">
            <div class="control-indicator"></div>
        </label>
    </script>
</body>
</html>
