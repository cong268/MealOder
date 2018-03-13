<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 11/03/2018
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:insertAttribute name="titlePage"/></title>
    <link rel="stylesheet" href="<c:url value="/assets/lib/bootstrap/bootstrap.min.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/lib/ng-table/ng-table.min.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/css/mainTemplayeStyle.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/css/util-css.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/jquery-1.11.3.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/angular.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/angular-click-outside.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/moment-with-locales.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/lib/bootstrap/bootstrap.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/lib/ng-table/ng-table.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/app.js"></c:url>"></script>
</head>
<body ng-app="NsrpApplication">
    <nav class="navbar navbar-custom" ng-controller="wrapperMenuCtrl">
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
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Meal Managerment <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="mealManagerment">Manegerment</a></li>
                            <li><a href="approveMeal">Approve Meal</a></li>
                        </ul>
                    </li>
                    </c:if>
                    <c:if test = "${sessionScope.userRole == 'Admin'}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Personal Managerment <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="registerUser">Register User</a></li>
                        </ul>
                    </li>
                    </c:if>
                    <c:if test = "${sessionScope.userRole == 'Admin'}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin Configuration <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="carterAdmin">Cartering action</a></li>
                            <li><a href="showDataAdmin">Cartered action</a></li>
                        </ul>
                    </li>
                    </c:if>
                    <c:if test = "${sessionScope.userRole == 'Employee'}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User Access <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="userOrder">User order meal</a></li>
                        </ul>
                    </li>
                    </c:if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown dropdown-user-custom">
                        <span data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle user-text-control">User control</span>
                        <img src="<c:url value="/assets/img/default-user-image.png"></c:url>" class="dropdown-toggle img-user-nsrp" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <ul class="dropdown-menu">
                            <li><a href="#">Username: ${sessionScope.userName}</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="logout">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="wrapper-nsrp-content">
        <tiles:insertAttribute name="mainContent"/>
    </div>
    <footer class="nsrp-page-footer">
        <div class="container">
            <span class="pull-left">&copy; Coppyright 2014 NSRP LLC</span>
            <span class="pull-right">Nghi Son Refinery and PETROCHEMICAL LLC</span>
        </div>
    </footer>
</body>
</html>