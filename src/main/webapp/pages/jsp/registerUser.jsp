<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 12/03/2018
  Time: 21:51
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
    <title>Register User</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/registerUserStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/registerUser.js"></c:url>"></script>
</head>
<body ng-controller="registerUserCtrl" ng-init="initData()">
    <div class="container wrap-register-user">
        <div class="form-register-centerd">
            <div class="text-center wrap-header-page">
                <h3>REGISTER NEW USER</h3>
            </div>
            <div class="form-horizontal form-register" ng-form="formRegister">
                <div class="form-group">
                    <label for="input-username" class="col-sm-4 control-label">Username</label>
                    <div class="col-sm-8">
                        <input type="text" autocomplete="off" ng-model="userObj.userName" ng-minlength="6" class="form-control" id="input-username" required>
                    </div>
                </div>
                <c:if test = "${sessionScope.userRole == 'Admin'}">
                <div class="form-group" ng-cloak>
                    <label for="input-role" class="col-sm-4 control-label">Role</label>
                    <div class="col-sm-8">
                        <select id="input-role" ng-cloak ng-model="userObj.userRoleId" class="form-control" required>
                            <option ng-selected="nsrpRoleId == role.userRoleID"
                                    ng-repeat="role in arrRole"
                                    ng-value="role.userRoleID">{{role.name}}</option>
                        </select>
                    </div>
                </div>
                </c:if>
                <div class="form-group">
                    <label for="input-staff" class="col-sm-4 control-label">Staff Id</label>
                    <div class="col-sm-8">
                        <input type="text" autocomplete="off" ng-model="userObj.staffId" ng-minlength="6" class="form-control" id="input-staff" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="input-pass" class="col-sm-4 control-label">Password</label>
                    <div class="col-sm-8">
                        <input type="password" autocomplete="off" ng-model="userObj.password" ng-minlength="6" class="form-control" id="input-pass" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-7">
                        <div ng-click="submitRegister(formRegister.$valid)" class="btn btn-lg btn-primary">REGISTER</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>