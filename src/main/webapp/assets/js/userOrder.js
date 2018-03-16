myApp.controller('userOrderCtrl', ['$scope',  '$http', function($scope, $http){
    moment.locale('en');
    $scope.locationArr = [];
    $scope.mealTimeArr = [];
    $scope.mealArr = [];
    $scope.shilfArr = [];
    $scope.arrData = [];
    $scope.lstDepartMent = [];
    $scope.dateOrder = moment(new Date()).format('DD/MM/YYYY');
    $scope.fromDateStr = moment(new Date()).format('DD/MM/YYYY');
    $scope.toDateStr = moment(new Date()).format('DD/MM/YYYY');
    $scope.initData = function(){
        $http({
            method: 'GET',
            url: 'cateringController/getMealByStaff',
            responseType: 'json'
        }).then(function successCallback(response) {
            if(response.data){
                dataAllPage = response.data;
                dataAllTable = dataAllPage.listMealOder;
                $scope.locationArr = dataAllPage.lstLocation;
                $scope.mealTimeArr = dataAllPage.lstMealTime;
                $scope.lstDepartMent = dataAllPage.lstDepartMent;
                $scope.mealArr = dataAllPage.lstMealType;
                $scope.shilfArr = dataAllPage.lstShift;
                $scope.arrData = angular.copy(dataAllTable);
            }
        }, function errorCallback(response) {
        });
    }
    $scope.getDepartmentName = function(departmentId){
        var deptNameFind = '';
        for(var i = 0 ; i< $scope.lstDepartMent.length; i++){
            if($scope.lstDepartMent[i].deptId == departmentId){
                deptNameFind =  $scope.lstDepartMent[i].deptName;
            }
        }
       return deptNameFind;
    }
    $scope.changeDateDouble = function(fromDate, toDate){
        $scope.fromDateStr = fromDate;
        $scope.toDateStr = toDate;
    }
    $scope.orderingCallback = function(){
        var dateInput = angular.element(document.getElementById("date-order-input")).val();
        var dateStr = moment(dateInput, 'DD/MM/YYYY').format('DDMMYYYY');
        $http({
            method: 'POST',
            url: 'cateringController/saveCateringEmployee?fromDate=' + $scope.fromDateStr +'&toDate='+$scope.toDateStr,
            responseType: 'json',
            headers: {
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON'
            },
            data: $scope.arrData
        }).then(function successCallback(response) {
            showSuccessAlert();
        }, function errorCallback(response) {
            showErrorAlert();
        })
    }
}]);
