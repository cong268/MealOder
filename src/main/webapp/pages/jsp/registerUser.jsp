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
<body>
    <div class="container wrap-register-user">
        <div class="form-register-centerd">
            <div class="text-center wrap-header-page">
                <h3>REGISTER NEW USER</h3>
            </div>
            <div class="form-horizontal">
                <div class="form-group">
                    <label for="input1" class="col-sm-3 control-label">Varrible 1</label>
                    <div class="col-sm-9">
                        <input type="Text" class="form-control" id="input1" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="input2" class="col-sm-3 control-label">Varrible 2</label>
                    <div class="col-sm-9">
                        <input type="email" class="form-control" id="input2" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="input3" class="col-sm-3 control-label">Varrible 3</label>
                    <div class="col-sm-9">
                        <input type="email" class="form-control" id="input3" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="input4" class="col-sm-3 control-label">Varrible 4</label>
                    <div class="col-sm-9">
                        <input type="email" class="form-control" id="input4" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="input5" class="col-sm-3 control-label">Varrible 5</label>
                    <div class="col-sm-9">
                        <input type="email" class="form-control" id="input5">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-7">
                        <div type="submit" class="btn btn-lg btn-primary">REGISTER</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>