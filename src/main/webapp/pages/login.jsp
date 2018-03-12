<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 11/03/2018
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>LOGIN NSRP</title>
    <link rel="icon" type="image/png" href="img/icons/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/bootstrap/bootstrap.min.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/font-awesome-4.7.0/css/font-awesome.min.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/Linearicons-Free-v1.0.0/icon-font.min.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/css/util-css.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/loginStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/jquery-1.11.3.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/angular.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/lib/bootstrap/bootstrap.min.js"></c:url>"></script>
</head>
<body ng-app="loginApplication">
<div class="limiter">
    <div class="container-login-nsrp" ng-controller="loginController">
        <input type="hidden" id="errorMess" value="${messsage}">
        <div class="wrap-login-nsrp p-t-30 p-b-50">
				<span class="login-nsrp-form-title p-b-41">
					NSRP LOGIN ACCESS
				</span>
            <form class="login-nsrp-form validate-form p-b-33 p-t-5" method="POST" action="authentication">
                <div class="wrap-error-message">
                    <span class="error-message" ng-bind="errorMessage"></span>
                </div>
                <div class="wrap-input-nsrp validate-input" data-validate="Enter username">
                    <input class="input-nsrp" ng-click="clickHideMessage()" id="nsrp-username" type="text" name="username" placeholder="User name">
                    <span class="focus-input-nsrp" data-placeholder="&#xe82a;"></span>
                </div>
                <div class="wrap-input-nsrp validate-input" data-validate="Enter password">
                    <input class="input-nsrp" ng-click="clickHideMessage()" id="nsrp-password" type="password" name="password" placeholder="Password">
                    <span class="focus-input-nsrp" data-placeholder="&#xe80f;"></span>
                </div>
                <div class="container-login-nsrp-form-btn m-t-32">
                    <button class="login-nsrp-form-btn">
                        Login
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/assets/js/login.js"></c:url>"></script>
</body>
</html>
