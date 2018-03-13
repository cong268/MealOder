<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 12/03/2018
  Time: 22:31
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
    <title>Approve Meal</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/approveMealStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/approveMeal.js"></c:url>"></script>
</head>
<body ng-controller="approveMealCtrl">
    <div class="container wrap-aprove-managerment">
        <div class="row text-center" >
            <div class="col-md-12 col-lg-12 col-xs-12 col-md-12 wrap-meal-table">
                <div class="text-left wrap-header-page">
                    <h3>Approve Meal</h3>
                </div>
                <div ng-cloak class="row wrap-filter col-md-4 col-lg-4 col-xs-4 col-md-4 pull-left">
                    <div class="col-sm-4 p-l-0 m-t-10">
                        <%--<select ng-model="filterType" ng-init="filterType = 'code'" class="form-control">--%>
                            <%--<option value="code">Code</option>--%>
                            <%--<option value="name">Name</option>--%>
                            <%--<option value="date">Date</option>--%>
                        <%--</select>--%>
                        Date
                    </div>
                    <div class="col-sm-6 p-l-0 m-t-10">
                        <%--<input type="text" ng-if="filterType == 'code'" id="codeFilter" class="form-control" ng-model="tableParams.filter()['StaffId']">--%>
                        <%--<input type="text" ng-if="filterType == 'name'" id="nameFilter" class="form-control" ng-model="tableParams.filter()['StaffName']">--%>
                        <%--<input type="text" ng-if="filterType == 'date'" ng-model="dateFilter" class="form-control">--%>
                            <input type="text" ng-model="dateFilter" class="form-control">
                    </div>
                    <div class="col-sm-2 p-l-0 m-t-10">
                        <div class="btn btn-success btn-default">Filter</div>
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
<c:if test = "${sessionScope.userRole != 'Manager'}">
    <div class="container">
        <h1>ACCESS DENIED <span class="badge p-t-8 p-b-8 p-l-8 p-r-8">404</span></h1>
        <h3>PLEASE CONTACT TO ADMIN TO MORE INFORMATION !</h3>
    </div>
</c:if>
</html>