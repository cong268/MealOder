<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 13/03/2018
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<c:if test = "${sessionScope.userRole == 'Employee'}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Order Meal</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/userOrderStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/userOrder.js"></c:url>"></script>
</head>
<body ng-controller="userOrderCtrl" ng-init="initData()">
    <div class="container wrap-user-order">
        <div class="form-user-order-centerd">
            <div class="text-center wrap-header-page">
                <h3>Order meal</h3>
            </div>
            <div class="form-horizontal">
                <div class="form-group">
                    <span class="col-sm-4 control-label">Staff Id</span>
                    <div class="col-sm-8">
                        <span class="form-control" ng-bind="arrData[0].staffId"></span>
                    </div>
                </div>
                <div class="form-group">
                    <span class="col-sm-4 control-label">Staff Name</span>
                    <div class="col-sm-8">
                        <span class="form-control" ng-bind="arrData[0].staffName"></span>
                    </div>
                </div>
                <div class="form-group">
                    <span class="col-sm-4 control-label">Department</span>
                    <div class="col-sm-8">
                        <span class="form-control">{{getDepartmentName(arrData[0].departmentId)}}</span>
                    </div>
                </div>
                <div class="form-group">
                    <span class="col-sm-4 control-label">Meal type</span>
                    <div class="col-sm-8">
                        <select ng-model="arrData[0].mealId" class="form-control">
                            <option ng-selected="arrData[0].mealId == mealObj.mealId"
                                    ng-repeat="mealObj in mealArr"
                                    ng-value="mealObj.mealId">{{mealObj.mealName}}</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <span class="col-sm-4 control-label">Meal time</span>
                    <div class="col-sm-8">
                        <select ng-model="arrData[0].mealTimeId" class="form-control">
                            <option ng-selected="arrData[0].mealTimeId == mealTimeObj.mealTimeId"
                                    ng-repeat="mealTimeObj in mealTimeArr"
                                    ng-value="mealTimeObj.mealTimeId">{{mealTimeObj.mealTimeName}}</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <span class="col-sm-4 control-label">Shift</span>
                    <div class="col-sm-8">
                        <select ng-model="arrData[0].shiftId" class="form-control">
                            <option ng-selected="arrData[0].shiftId == shilfObj.shiftId"
                                    ng-repeat="shilfObj in shilfArr"
                                    ng-value="shilfObj.shiftId">{{shilfObj.shiftName}}</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <span class="col-sm-4 control-label">Location</span>
                    <div class="col-sm-8">
                        <select ng-model="arrData[0].locationId" class="form-control">
                            <option ng-selected="arrData[0].locationId == locationObj.locationId"
                                    ng-repeat="locationObj in locationArr"
                                    ng-value="locationObj.locationId">{{locationObj.locationName}}</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row text-right p-r-15">
                <div class="btn btn-success btn-lg" ng-click="orderingCallback()">ORDER</div>
            </div>
        </div>
    </div>
</body>
</c:if>
<c:if test = "${sessionScope.userRole != 'Employee'}">
    <div class="container">
        <h1>ACCESS DENIED <span class="badge p-t-8 p-b-8 p-l-8 p-r-8">404</span></h1>
        <h3>PLEASE CONTACT TO ADMIN TO MORE INFORMATION !</h3>
    </div>
</c:if>
</html>