<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 18/03/2018
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<c:if test = "${sessionScope.userRole == 'Manager' || sessionScope.userRole == 'Admin'}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register Staff</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/registerStaffStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/registerStaff.js"></c:url>"></script>
</head>
<body ng-controller="registerStaffCtrl" ng-init="initData()">
<div class="container wrap-register-user">
    <div class="form-register-centerd">
        <div class="text-center wrap-header-page">
            <h3>REGISTER NEW STAFF</h3>
        </div>
        <div class="form-horizontal form-register-staff" ng-form="formRegisterStaff">
            <div class="form-group">
                <label for="input-id" class="col-sm-4 control-label">Staff Id</label>
                <div class="col-sm-8">
                    <input type="text" autocomplete="off" ng-model="staffObj.staffId" ng-minlength="6" class="form-control" id="input-id" required>
                </div>
            </div>
            <div class="form-group">
                <label for="input-username" class="col-sm-4 control-label">Staff Name</label>
                <div class="col-sm-8">
                    <input type="text" autocomplete="off" ng-model="staffObj.staffName" ng-minlength="6" class="form-control" id="input-username" required>
                </div>
            </div>
            <div class="form-group">
                <label for="input-depart" class="col-sm-4 control-label">Department</label>
                <div class="col-sm-8">
                    <select ng-model="staffObj.departId" class="form-control" id="input-depart" required>
                        <option value="{{department.deptId}}" ng-repeat="department in departmentArr">{{department.deptName}}</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="input-gender" class="col-sm-4 control-label">Gender</label>
                <div class="col-sm-8">
                    <select id="input-gender" ng-cloak ng-model="staffObj.gender" class="form-control col-sm-6" required>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="input-job" class="col-sm-4 control-label">Job Title</label>
                <div class="col-sm-8">
                    <input type="text" autocomplete="off" ng-model="staffObj.jobTitle" class="form-control" id="input-job">
                </div>
            </div>
            <div class="form-group">
                <label for="input-address" class="col-sm-4 control-label">Address</label>
                <div class="col-sm-8">
                    <input type="text" autocomplete="off" ng-model="staffObj.address" class="form-control" id="input-address" >
                </div>
            </div>
            <div class="form-group">
                <label for="input-cardid" class="col-sm-4 control-label">Card Id</label>
                <div class="col-sm-8">
                    <input type="text" autocomplete="off" ng-model="staffObj.cardId" ng-minlength="6" ng-maxlength="12" class="form-control" id="input-cardid" required>
                </div>
            </div>
            <div class="form-group">
                <label for="input-phoneNum" class="col-sm-4 control-label">Phone</label>
                <div class="col-sm-8">
                    <input type="text" autocomplete="off" ng-model="staffObj.phoneNum" class="form-control" id="input-phoneNum" >
                </div>
            </div>
            <div class="form-group">
                <label for="input-email" class="col-sm-4 control-label">Email</label>
                <div class="col-sm-8">
                    <input type="email" autocomplete="off" ng-model="staffObj.email" class="form-control" id="input-email">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-7">
                    <div ng-if="formRegisterStaff.$invalid" class="btn btn-lg btn-warning">REGISTER</div>
                    <div ng-if="formRegisterStaff.$valid" ng-click="submitRegisterStaff(formRegisterStaff.$valid)" class="btn btn-lg btn-success">REGISTER</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</c:if>
<c:if test = "${sessionScope.userRole != 'Manager' && sessionScope.userRole != 'Admin'}">
    <div class="container">
        <h1><spring:message code="label.access_denied"/> <span class="badge p-t-8 p-b-8 p-l-8 p-r-8">404</span></h1>
        <h3><spring:message code="label.access_denied_info"/></h3>
    </div>
</c:if>
</html>
