<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 11/03/2018
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message  code="label.login_header" /></title>
    <link rel="icon" type="image/png" href="img/icons/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/bootstrap/bootstrap.min.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/css/tooltipster.bundle.min.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/css/tooltipster-borderless-cuzt.min.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/font-awesome-4.7.0/css/font-awesome.min.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/Linearicons-Free-v1.0.0/icon-font.min.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/css/util-css.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/flag-icon.min.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/loginStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/jquery-1.11.3.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/angular.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/tooltipster.bundle.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/lib/bootstrap/bootstrap.min.js"></c:url>"></script>
</head>
<body ng-app="loginApplication">
<div class="limiter">
    <div class="container-login-nsrp" ng-controller="loginController">
        <div class="wrap-login-nsrp p-t-30 p-b-50">
            <span class="login-nsrp-form-title p-b-41">
                <spring:message  code="label.login_header" />
            </span>
            <form class="login-nsrp-form validate-form p-b-45 p-t-5" method="POST" action="authentication">
                <c:if test="${message eq 'error'}">
                    <div class="wrap-error-message">
                        <span class="error-message">
                            <spring:message  code="label.login_error" />
                        </span>
                    </div>
                </c:if>
                <div class="wrap-input-nsrp validate-input" data-validate="<spring:message  code="label.enter_username" />">
                    <input class="input-nsrp" ng-click="clickHideMessage()" ng-model="nsrpUsername"
                           id="nsrp-username" type="text" name="username"
                           placeholder="<spring:message  code="label.user_name" />">
                    <span class="focus-input-nsrp" data-placeholder="&#xe82a;"></span>
                </div>
                <div class="wrap-input-nsrp validate-input" data-validate="<spring:message  code="label.enter_password" />">
                    <input class="input-nsrp" ng-click="clickHideMessage()" ng-model="nsrpPassword"
                           id="nsrp-password" type="password" name="password"
                           placeholder="<spring:message  code="label.password" />">
                    <span class="focus-input-nsrp" data-placeholder="&#xe80f;"></span>
                </div>
                <div class="container-login-nsrp-form-btn m-t-32">
                    <button class="login-nsrp-form-btn">
                        <spring:message  code="label.login" />
                    </button>
                </div>
            </form>
            <div class="langue-support-wrap">
                <span><spring:message  code="label.language" /> : </span>
                 <a href="?lang=vi">
                    <div class="flag-custom flag flag-icon-background flag-icon-vn" tooltip title="<spring:message  code="label.lang_vi_long" />"></div>
                </a>
                <span class="m-t-2">|</span>
                <a href="?lang=en">
                    <div class="flag-custom flag flag-icon-background flag-icon-gb" tooltip title="<spring:message  code="label.lang_en_long" />"></div>
                </a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/assets/js/login.js"></c:url>"></script>
</body>
</html>
