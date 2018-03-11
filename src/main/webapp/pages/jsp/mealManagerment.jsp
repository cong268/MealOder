<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 11/03/2018
  Time: 16:47
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
    <title>MEAL MANAGERMENT</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/mainTemplayeStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/mealManagerment.js"></c:url>"></script>
</head>
<body ng-controller="mealManagermentCtrl as mmc">
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
</body>
</html>
