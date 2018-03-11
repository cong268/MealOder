<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 11/03/2018
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>NGHI SON REFINERY AND PETROCHEMICAL LLC</title>
    <link rel="stylesheet" href="<c:url value="/assets/lib/bootstrap/bootstrap.min.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/lib/ng-table/ng-table.min.css"></c:url>">
    <link rel="stylesheet" href="<c:url value="/assets/css/mainTemplayeStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/jquery-1.11.3.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/angular.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/angular-click-outside.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/lib/bootstrap/bootstrap.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/lib/ng-table/ng-table.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/app.js"></c:url>"></script>
</head>
<body ng-app="NsrpApplication" ng-controller="mealManagermentCtrl as mmc">
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
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Meal Managerment <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Persional Managerment <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Configuration <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Contact <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown dropdown-user-custom">
                        <span data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle user-text-control">User control</span>
                        <img src="<c:url value="/assets/img/default-user-image.png"></c:url>" class="dropdown-toggle img-user-nsrp" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="wrapper-nsrp-content container">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-xs-12 col-md-12">
                <h3>Default configuration</h3>
                <table ng-table="mmc.tableParams" class="table table-condensed table-bordered table-striped">
                    <tr ng-repeat="row in $data">
                        <td data-title="'Name'" sortable="'name'">{{row.name}}</td>
                        <td data-title="'Age'" sortable="'age'">{{row.age}}</td>
                        <td data-title="'Money'" sortable="'money'">{{row.money}}</td>
                        <td width="10" style="text-align: center" header="'ng-table/headers/checkbox.html'">
                            <label class="control control-checkbox">
                                <input type="checkbox" ng-model="statusMeal.items[row.id]">
                                <div class="control-indicator"></div>
                            </label>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <footer class="nsrp-page-footer">
        <div class="container">
            <span class="pull-left">&copy; Coppyright 2014 NSRP LLC</span>
            <span class="pull-right">Nghi Son Refinery and PETROCHEMICAL LLC</span>
        </div>
    </footer>
    <script type="text/ng-template" id="ng-table/headers/checkbox.html">
        <label id="select_all" class="control control-checkbox">
            <input type="checkbox" ng-model="statusMeal.checked">
            <div class="control-indicator"></div>
        </label>
    </script>
</body>
</html>