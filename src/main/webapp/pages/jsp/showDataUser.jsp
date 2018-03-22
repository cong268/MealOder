<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 21/03/2018
  Time: 01:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>History</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/showUserStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/showUser.js"></c:url>"></script>
    <script type="text/javascript"> var _contextPath = "${pageContext.request.contextPath}"; </script>
</head>
<body ng-controller="carteredAdminCtrl" ng-init="initData()">
    <div class="container wrap-cartered-managerment">
        <div class="row text-center" >
            <div class="col-md-12 col-lg-12 col-xs-12 col-md-12">
                <div class="text-left wrap-header-page">
                    <h3>History Order <span class="badge" ng-cloak ng-bind="arrData.length"/></h3>
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
                </div>
                <div ng-cloak class="row m-b-10 col-md-12 col-lg-12 col-xs-12 col-md-12 text-left">
                    <span class="vcenter">Total: </span><span class="text-danger vcenter fs-18" ng-cloak ng-bind="tableParams.total()"/>
                    <div class="col-sm-3 p-l-0 m-t-10">
                        <div class="btn btn-success btn-default" ng-click="filterAccept()">Catering Date<span class="glyphicon glyphicon-search p-l-10"></span></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row text-center" >
            <div class="col-md-12 col-lg-12 col-xs-12 col-md-12 wrap-user-table">
                <table ng-cloak ng-table="tableParams" class="table table-condensed table-bordered table-striped table-custom"
                       ng-class="{'nomarginbottom': tableParams.total() == 0}">
                    <tr ng-repeat="row in $data">
                        <td data-title="'Date'" sortable="'dateMeal'" class="text-center">{{row.dateMeal}}</td>
                        <td data-title="'Meal Time'" sortable="'mealTimeId'" class="text-center">
                            {{getMealTimeName(row.mealTimeId)}}
                        </td>
                        <td data-title="'Meal Type'" sortable="'mealId'" class="text-center">{{getMealName(row.mealId)}}</td>
                        <td data-title="'Location'" sortable="'locationId'" class="text-center">
                            {{getLocationName(row.locationId)}}
                        </td>
                        <c:if test = "${sessionScope.userRole == 'Employee'}">
                        <td width="100" data-title="'Action'" class="text-center" >
                            <span class="text-success" ng-if="row.status == 1 || row.catered == 1 || row.disable == 1">Done</span>
                            <div class="btn btn-default btn-custom" tooltip title="Edit"
                                 ng-if="row.status != 1 && row.catered != 1 && row.disable != 1"
                                 data-toggle="modal" data-target="#editMealModal" ng-click="editMeal(row)">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </div>
                            <div class="btn btn-danger btn-custom" tooltip title="Delete"
                                 ng-if="row.status != 1 && row.catered != 1 && row.disable != 1"
                                 data-toggle="modal" data-target="#deleteModal" ng-click="showDeleteMealUser(row)">
                                <span class="glyphicon glyphicon-trash"></span>
                            </div>
                        </td>
                        </c:if>
                        <c:if test = "${sessionScope.userRole == 'Manager'}">
                            <td width="100" data-title="'Action'" class="text-center" >
                                <span class="text-success" ng-if="row.catered == 1 || row.disable == 1">Done</span>
                                <div class="btn btn-default btn-custom" tooltip title="Edit"
                                     ng-if="row.catered != 1 && row.disable != 1"
                                     data-toggle="modal" data-target="#editMealModal" ng-click="editMeal(row)">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </div>
                                <div class="btn btn-danger btn-custom" tooltip title="Delete"
                                     ng-if="row.catered != 1 && row.disable != 1"
                                     data-toggle="modal" data-target="#deleteModal" ng-click="showDeleteMealUser(row)">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </div>
                            </td>
                        </c:if>
                        <c:if test = "${sessionScope.userRole == 'Admin'}">
                            <td width="100" data-title="'Action'" class="text-center" >
                                <span class="text-success" ng-if="row.disable == 1">Done</span>
                                <div class="btn btn-default btn-custom" tooltip title="Edit"
                                     ng-if="row.disable != 1"
                                     data-toggle="modal" data-target="#editMealModal" ng-click="editMeal(row)">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </div>
                                <div class="btn btn-danger btn-custom" tooltip title="Delete"
                                     ng-if="row.disable != 1"
                                     data-toggle="modal" data-target="#deleteModal" ng-click="showDeleteMealUser(row)">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </div>
                            </td>
                        </c:if>
                    </tr>
                </table>
                <div class="showNoData" ng-if="tableParams.total() == 0" ng-cloak>No data available</div>
            </div>
        </div>
    </div>
    <div class="wrap-popup-edit">
        <div class="modal fade" id="editMealModal" tabindex="-1" role="dialog" aria-labelledby="editMealModalTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title float-l" id="editMealModalTitle">EDIT MEAL</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="meal-date-popup" class="col-form-label">Date:</label>
                            <span id="meal-date-popup" class="form-control disabled" ng-bind="selectedItemClone.dateMeal"></span>
                        </div>
                        <div class="form-group">
                            <label for="meal-time-popup" class="col-form-label">Meal time:</label>
                            <span id="meal-time-popup" class="form-control disabled" ng-bind="getMealTimeName(selectedItemClone.mealTimeId)"></span>
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
                                <option ng-selected="selectedItemClone.locationId == location.locationId"
                                        ng-repeat="location in locationArr"
                                        ng-value="location.locationId">{{location.locationName}}</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" ng-click="saveEditMeal()">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
                    <div class="modal-footer text-center">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-success" ng-click="deleteMealUser()" data-dismiss="modal">Accept</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>