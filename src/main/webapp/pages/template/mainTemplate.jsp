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
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:set var="titlePage"><tiles:getAsString name="titlePage"/></c:set>
    <title><spring:message code="${titlePage}"></spring:message></title>
    <link rel="stylesheet" href="<c:url value="/assets/lib/bootstrap/bootstrap.min.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/lib/ng-table/ng-table.min.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/css/mainTemplayeStyle.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/css/tooltipster.bundle.min.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/css/tooltipster-borderless-cuzt.min.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/lib/css/daterangepicker.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/flag-icon.min.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/css/util-css.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/jquery-1.11.3.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/angular.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/tooltipster.bundle.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/angular-click-outside.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/moment-with-locales.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/lib/bootstrap/bootstrap.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/lib/ng-table/ng-table.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/lib/js/daterangepicker.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/app.js"></c:url>"></script>
</head>
<body ng-app="NsrpApplication">
    <nav class="navbar navbar-custom">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#nsrp-navber-collapse" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">NSRP</a>
            </div>
            <div class="collapse navbar-collapse" id="nsrp-navber-collapse">
                <ul class="nav navbar-nav">
                    <c:if test = "${sessionScope.userRole == 'Manager'}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="label.meal_management"/> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="mealManagerment"><spring:message code="label.management"/></a></li>
                            <li><a href="showDataManager"><spring:message code="label.show_manager"/></a></li>

                        </ul>
                    </li>
                    </c:if>
                    <c:if test = "${sessionScope.userRole == 'Admin' || sessionScope.userRole == 'Chef'}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="label.admin_configuration"/> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                        <c:if test = "${sessionScope.userRole == 'Admin'}">
                        	<li><a href="carterAdmin"><spring:message code="label.cartering_action"/></a></li>
                            <li><a href="showDataAdmin"><spring:message code="label.report_info"/></a></li>
                        </c:if>
                        <c:if test = "${sessionScope.userRole == 'Chef'}">
                        	<li><a href="showDataChef"><spring:message code="label.report_info"/></a></li>
                        </c:if>
                        </ul>
                    </li>
                    </c:if>
                    <c:if test = "${sessionScope.userRole == 'Admin' || sessionScope.userRole == 'Manager'}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="label.person_manager"/> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="registerUser"><spring:message code="label.register_user"/></a></li>
                            <li><a href="registerStaff"><spring:message code="label.register_staff"/></a></li>
                        </ul>
                    </li>
                    </c:if>
                    <c:if test = "${sessionScope.userRole == 'Employee' || sessionScope.userRole == 'Admin' || sessionScope.userRole == 'Manager'}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="label.user_access"/> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="userOrder"><spring:message code="label.order_meal"/></a></li>
                        </ul>
                    </li>
                    </c:if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown dropdown-user-custom">
                        <span data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle user-text-control"><spring:message code="label.user_control"/></span>
                        <img src="<c:url value="/assets/img/default-user-image.png"></c:url>" class="dropdown-toggle img-user-nsrp" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <ul class="dropdown-menu">
                            <li><a href="#" data-toggle="modal" data-target="#userInfoModal">Username: ${sessionScope.userName}</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="logout"><spring:message code="label.logout"/><span class="glyphicon glyphicon-log-out p-l-10"></span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="wrapper-nsrp-content">
        <tiles:insertAttribute name="mainContent"/>
    </div>
    <div class="popup-successful text-center">
        <div class="p-t-30 p-b-30 p-l-20 p-r-20">
            <spring:message code="label.success_upper"/>
        </div>
    </div>
    <div class="popup-Error text-center">
        <div class="p-t-30 p-b-30 p-l-20 p-r-20">
            <spring:message code="label.error_upper"/>
        </div>
    </div>
    <footer class="nsrp-page-footer">
        <div class="container wrap-footer-temp">
            <span class="info-nsrp pull-left">&copy; Coppyright 2014 NSRP LLC</span>
            <span class="info-nsrp pull-right">Nghi Son Refinery and PETROCHEMICAL LLC</span>
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
    </footer>
    <%-- POPUP USER --%>
    <div class="wrap-user-info" ng-controller="userInfoCtrl" ng-init="initData()">
        <div class="modal fade" id="userInfoModal" tabindex="-1" role="dialog" aria-labelledby="userInfoModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content ">
                    <div class="modal-header">
                        <h3 class="modal-title float-l" id="userInfoModalLongTitle">User Infomation</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        User Info Here
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-success" ng-click="updateUserInfo()" data-dismiss="modal">Accept</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>