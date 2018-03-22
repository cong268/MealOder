<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 11/03/2018
  Time: 16:47
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
            <div class="col-md-12 col-lg-12 col-xs-12 col-md-12">
                <div class="text-left wrap-header-page">
                    <h3>Meal Management <span class="badge" ng-cloak ng-bind="arrDataOrder.length"></span></h3>
                </div>
                <div ng-cloak class="row wrap-filter col-md-12 col-lg-12 col-xs-12 col-md-12 pull-left">
                    <div class="col-md-1 p-l-0 m-t-10 p-t-5">
                        Date
                    </div>
                    <div class="col-md-3 p-l-0 m-t-10">
                        <div class="input-group">
                            <input type="text" id="date-filter-input" date-range-picker-single-min
                                   ng-model="dateFilter" class="form-control">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="col-md-1 p-l-0 m-t-10">
                        <div class="btn btn-primary btn-default" ng-click="filterAccept()">Search<span class="glyphicon glyphicon-search m-l-10"></span></div>
                    </div>
                    <div class="col-md-offset-5 col-md-2  m-t-10 p-r-0">
                        <div class="btn btn-success btn-default pull-right btn-add-vis" ng-click="clearVisitorObject()" data-toggle="modal" data-target="#visitorModal">
                            <span class="glyphicon glyphicon-plus p-r-10"></span>Add Visitor
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row text-center" ng-cloak>
            <div class="col-md-12 col-lg-12 col-xs-12 col-md-12 wrap-meal-table">
                <table ng-cloak ng-table="tableParamsOrder" class="table table-condensed table-bordered table-striped table-custom"
                       ng-class="{'nomarginbottom': tableParamsOrder.total() == 0}">
                    <tr ng-repeat="row in $data">
                        <td data-title="'EmployeeCode'" sortable="'staffId'">{{row.staffId}}</td>
                        <td data-title="'Fullname'" sortable="'staffName'">{{row.staffName}}</td>
                        <td data-title="'Meal Time'" class="text-center">
                            {{getMealTimeName(row.mealTimeId)}}
                        </td>
                        <td data-title="'Location'" class="text-center">
                            {{getLocationName(row.locationId)}}
                        </td>
                        <td data-title="'Meal Type'" class="text-center">
                            {{getMealName(row.mealId)}}
                        </td>
                        <%--<td data-title="'Shift'" class="text-center">--%>
                            <%--{{getShiftName(row.shiftId)}}--%>
                        <%--</td>--%>
                        <td width="100" style="text-align: center" data-header="'addMealManager.html'">
                            <div class="btn btn-default btn-custom" tooltip title="Edit" data-toggle="modal" data-target="#editModal" ng-click="editMeal(row)">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </div>
                            <div class="btn btn-danger btn-custom" tooltip title="Delete" data-toggle="modal" data-target="#deleteModal" ng-click="showDeleteMealOrder(row)">
                                <span class="glyphicon glyphicon-trash"></span>
                            </div>
                        </td>
                    </tr>
                </table>
                <div class="showNoData" ng-if="tableParamsOrder.total() == 0" ng-cloak>No data available</div>
            </div>
        </div>
        <div class="row m-t-10" ng-cloak>
            <div ng-if="arrDataOrder.length > 0 || lstDelMeal.length  > 0" class="btn btn-primary btn-lg pull-right m-r-15" ng-click="submitMealManager()">SUBMIT</div>
            <div ng-if="arrDataOrder.length == 0 && lstDelMeal.length ==0" class="btn btn-disabled btn-lg pull-right m-r-15" >SUBMIT</div>
        </div>
    </div>
    <!-- EDIT MEAL -->
    <div class="wrap-popup-edit">
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title float-l" id="editModalTitle">EDIT MEAL</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="employee-code-popup" class="col-form-label">Employee code:</label>
                            <span  id="employee-code-popup" class="form-control disabled" ng-bind="selectedItemClone.staffId"></span>
                        </div>
                        <div class="form-group">
                            <label for="employee-name-popup" class="col-form-label">Employee name:</label>
                            <span  id="employee-name-popup" class="form-control disabled" ng-bind="selectedItemClone.staffName"></span>
                        </div>
                        <div class="form-group">
                            <label for="meal-time-popup" class="col-form-label">Meal time:</label>
                            <span  id="meal-time-popup" class="form-control disabled" ng-bind="getMealTimeName(selectedItemClone.mealTimeId)"></span>
                        </div>
                        <div class="form-group">
                            <label for="meal-popup" class="col-form-label">Meal type:</label>
                            <select id="meal-popup" ng-model="selectedItemClone.mealId" class="form-control">
                                <option ng-selected="selectedItemClone.mealId == mealObj.mealId"
                                        ng-repeat="mealObj in mealArr"
                                        ng-value="mealObj.mealId">{{mealObj.mealName}}</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="location-popup" class="col-form-label">Location:</label>
                            <select id="location-popup" ng-model="selectedItemClone.locationId" class="form-control">
                                <option ng-selected="selectedItemClone.locationId == locationObj.locationId"
                                        ng-repeat="locationObj in locationArr"
                                        ng-value="locationObj.locationId">{{locationObj.locationName}}</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" ng-click="saveEditMeal()">Save change</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ADD NEW MEAL -->
    <div class="wrap-popup-add-new">
        <div class="modal fade" id="addNewModal" tabindex="-1" role="dialog" aria-labelledby="addNewModalTitle" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title float-l" id="addNewModalTitle">ADD MEAL</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row text-center" ng-cloak>
                            <div class="col-md-12 col-lg-12 col-xs-12 col-md-12 wrap-meal-table">
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
                                        <%--<td data-title="'Shift'">--%>
                                            <%--<select ng-model="row.shiftId" class="form-control">--%>
                                                <%--<option ng-selected="row.shiftId == shilfObj.shiftId"--%>
                                                        <%--ng-repeat="shilfObj in shilfArr"--%>
                                                        <%--ng-value="shilfObj.shiftId">{{shilfObj.shiftName}}</option>--%>
                                            <%--</select>--%>
                                        <%--</td>--%>
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
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" ng-click="saveAddMeal()">Save change</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--ADD VISITOR-->
    <div class="wrap-popup-visitor">
        <div class="modal fade" id="visitorModal" tabindex="-1" role="dialog" aria-labelledby="visitorModalTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content" ng-form="visitorForm">
                    <div class="modal-header">
                        <h3 class="modal-title float-l" id="visitorModalTitle">ADD VISITOR</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="visitor-code-popup" class="col-form-label">Staff Id:</label>
                            <input type="text" class="form-control" ng-model="visitorObject.staffId" id="visitor-code-popup" required ng-minlength="6">
                        </div>
                        <div class="form-group">
                            <label for="visitor-name-popup" class="col-form-label">Employee name:</label>
                            <input type="text" class="form-control" ng-model="visitorObject.staffName" id="visitor-name-popup" required ng-minlength="6">
                        </div>
                        <div class="form-group">
                            <label for="visitor-meal-time-popup" class="col-form-label">Meal time:</label>
                            <select id="visitor-meal-time-popup" ng-model="visitorObject.mealTimeId" class="form-control" required>
                                <option ng-selected="selectedItemClone.mealTimeId == mealTimeObj.mealTimeId"
                                        ng-repeat="mealTimeObj in mealTimeArr"
                                        ng-value="mealTimeObj.mealTimeId">{{mealTimeObj.mealTimeName}}</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="visitor-location-popup" class="col-form-label">Location:</label>
                            <select id="visitor-location-popup" ng-model="visitorObject.locationId" class="form-control" required>
                                <option ng-selected="visitorObject.locationId == locationObj.locationId"
                                        ng-repeat="locationObj in locationArr"
                                        ng-value="locationObj.locationId">{{locationObj.locationName}}</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="visitor-meal-popup" class="col-form-label">Meal type:</label>
                            <select id="visitor-meal-popup" ng-model="visitorObject.mealId" class="form-control" required>
                                <option ng-selected="visitorObject.mealId == mealObj.mealId"
                                        ng-repeat="mealObj in mealArr"
                                        ng-value="mealObj.mealId">{{mealObj.mealName}}</option>
                            </select>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label for="visitor-shift-popup" class="col-form-label">Shift:</label>--%>
                            <%--<select id="visitor-shift-popup" ng-model="visitorObject.shiftId" class="form-control" required>--%>
                                <%--<option ng-selected="visitorObject.shiftId == shilfObj.shiftId"--%>
                                        <%--ng-repeat="shilfObj in shilfArr"--%>
                                        <%--ng-value="shilfObj.shiftId">{{shilfObj.shiftName}}</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-secondary" ng-if="!visitorForm.$valid">Add</button>
                        <button type="button" tooltip title="Validate" class="btn btn-primary" ng-if="visitorForm.$valid" ng-click="addVisitorObject()" data-dismiss="modal">Add</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--DELETE MEAL-->
    <div class="wrap-accept-delete">
        <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModal" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
                <div class="modal-content ">
                    <div class="modal-header">
                        <h3 class="modal-title float-l" id="deleteModalLongTitle">Confirm delete</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure to delete this field ?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-success" ng-click="deleteMeal()" data-dismiss="modal">Accept</button>
                    </div>
                </div>
            </div>
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
    <script type="text/ng-template" id="addMealManager.html">
        <div ng-click="showAddnewPopup()" tooltip title="Add meal" data-toggle="modal" data-target="#addNewModal" class="img-add-header pos-relative text-center">
            <span class="glyphicon glyphicon-plus text-white text-center m-l-1 ab-c-m"></span>
        </div>
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

