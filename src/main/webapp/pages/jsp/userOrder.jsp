<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 13/03/2018
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<c:if test = "${sessionScope.userRole == 'Employee' || sessionScope.userRole == 'Admin' || sessionScope.userRole == 'Manager'}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message code="page.user_order"/></title>
    <link rel="stylesheet" href="<c:url value="/assets/css/userOrderStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/userOrder.js"></c:url>"></script>
</head>
<body ng-controller="userOrderCtrl" ng-init="initData()">
    <div class="container wrap-user-order">
        <div class="form-user-order-centerd">
            <div class="text-center wrap-header-page">
                <h3><spring:message code="label.order_meal"/></h3>
            </div>
            <div class="form-horizontal form-user-order" ng-form="userOrderForm">
                <div class="form-group">
                    <span class="col-sm-4 control-label"><spring:message code="label.staff_id"/></span>
                    <div class="col-sm-8">
                        <span class="form-control disabled" ng-bind="arrData[0].staffId"></span>
                    </div>
                </div>
                <div class="form-group">
                    <span class="col-sm-4 control-label"><spring:message code="label.staff_name"/></span>
                    <div class="col-sm-8">
                        <span class="form-control disabled" ng-bind="arrData[0].staffName"></span>
                    </div>
                </div>
                <div class="form-group">
                    <span class="col-sm-4 control-label"><spring:message code="label.department"/></span>
                    <div class="col-sm-8">
                        <span class="form-control disabled" ng-bind="getDepartmentName(arrData[0].departmentId)"></span>
                    </div>
                </div>
                <div class="form-group">
                    <span class="col-sm-4 control-label"><spring:message code="label.cartering_date"/></span>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" required
                               ng-model="dateOrder"
                               id="date-order-input"
                               call-change="changeDateDouble(fromDate, toDate)"
                               apply-text="'<spring:message code="label.apply_date"/>'"
                               cancel-text="'<spring:message code="label.cancel_date"/>'"
                               date-range-picker-double-min/>
                    </div>
                </div>
<!--                 <div class="form-group"> -->
<!--                     <span class="col-sm-4 control-label">Department</span> -->
<!--                     <div class="col-sm-8"> -->
<!--                         <span class="form-control">{{getDepartmentName(arrData[0].departmentId)}}</span> -->
<!--                     </div> -->
<!--                 </div> -->
                <div class="form-group">
                    <span class="col-sm-4 control-label"><spring:message code="label.meal_type"/></span>
                    <div class="col-sm-8">
                        <select ng-model="arrData[0].mealId" class="form-control" required>
                            <option ng-selected="arrData[0].mealId == mealObj.mealId"
                                    ng-repeat="mealObj in mealArr"
                                    ng-value="mealObj.mealId">{{mealObj.mealName}}</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <span class="col-sm-4 control-label"><spring:message code="label.meal_time"/></span>
                    <div class="col-sm-8">
                        <select ng-model="arrData[0].mealTimeId" class="form-control" required>
                            <option ng-selected="arrData[0].mealTimeId == mealTimeObj.mealTimeId"
                                    ng-repeat="mealTimeObj in mealTimeArr"
                                    ng-value="mealTimeObj.mealTimeId">{{mealTimeObj.mealTimeName}}</option>
                        </select>
                    </div>
                </div>
                <%--<div class="form-group">--%>
                    <%--<span class="col-sm-4 control-label"><spring:message code="label.shift"/></span>--%>
                    <%--<div class="col-sm-8">--%>
                        <%--<select ng-model="arrData[0].shiftId" class="form-control">--%>
                            <%--<option ng-selected="arrData[0].shiftId == shilfObj.shiftId"--%>
                                    <%--ng-repeat="shilfObj in shilfArr"--%>
                                    <%--ng-value="shilfObj.shiftId">{{shilfObj.shiftName}}</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <span class="col-sm-4 control-label"><spring:message code="label.location"/></span>
                    <div class="col-sm-8">
                        <select ng-model="arrData[0].locationId" class="form-control" required>
                            <option ng-selected="arrData[0].locationId == locationObj.locationId"
                                    ng-repeat="locationObj in locationArr"
                                    ng-value="locationObj.locationId">{{locationObj.locationName}}</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row text-right p-r-15">
                <div tooltip title="<spring:message code="label.order_now"/>" class="btn btn-success btn-lg" ng-click="orderingCallback()"><spring:message code="label.order_upper"/></div>
            </div>
        </div>
    </div>
</body>
</c:if>
<c:if test = "${sessionScope.userRole != 'Employee' && sessionScope.userRole == 'Admin' && sessionScope.userRole == 'Manager'}">
    <div class="container">
        <h1><spring:message code="label.access_denied"/> <span class="badge p-t-8 p-b-8 p-l-8 p-r-8">404</span></h1>
        <h3><spring:message code="label.access_denied_info"/></h3>
    </div>
</c:if>
</html>