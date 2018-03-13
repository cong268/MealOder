myApp.controller('userOrderCtrl', ['$scope',  '$http', function($scope, $http){

    $scope.locationArr = [];
    $scope.mealTimeArr = [];
    $scope.mealArr = [];
    $scope.shilfArr = [];
    $scope.arrData = [];
    $scope.lstDepartMent = [];
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
            $scope.loadingPerformancePatri = false;
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
    $scope.orderingCallback = function(){
        console.log($scope.arrData);
        $http({
            method: 'POST',
            url: 'cateringController/saveCatering?date=' +moment(new Date()).format('DDMMYYYY'),
            responseType: 'json',
            headers: {
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON'
            },
            data: $scope.arrData
        }).then(function successCallback(response) {
            alert('SUCCESS');
        }, function errorCallback(response) {
            alert('FAIL');
        })
    }
}]);
