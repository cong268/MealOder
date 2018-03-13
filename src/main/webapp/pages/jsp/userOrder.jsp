<%--
  Created by IntelliJ IDEA.
  User: chien
  Date: 13/03/2018
  Time: 20:32
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
    <title>Order Meal</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/userOrderStyle.css"></c:url>">
    <script type="text/javascript" src="<c:url value="/assets/js/userOrder.js"></c:url>"></script>
</head>
<body>
    <div class="container wrap-user-order">
        <div class="form-user-order-centerd">
            <div class="text-left wrap-header-page">
                <h3>Order meal</h3>
            </div>
            <div class="form-horizontal">
                <div class="form-group">
                    <label for="input1" class="col-sm-4 control-label">Varrible 1</label>
                    <div class="col-sm-8">
                        <input type="Text" class="form-control" id="input1" >
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>