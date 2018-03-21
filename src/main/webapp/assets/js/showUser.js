myApp.controller('carteredAdminCtrl', ['$scope', 'NgTableParams', 'ngTableEventsChannel', '$http','$q', function($scope, NgTableParams, ngTableEventsChannel, $http, $q) {
    var dataAllPage, dataAllTable;
    $scope.demoCheckbox = 1;
    $scope.dataFiltered = [];
    $scope.locationArr = [];
    $scope.mealTimeArr = [];
    $scope.mealArr = [];
    $scope.arrData = [];
    $scope.departmentArr = [];
    moment.locale('en');
    $scope.fromDate = moment(new Date()).format('DD/MM/YYYY');
    $scope.toDate = moment(new Date()).format('DD/MM/YYYY');
    $scope.initData = function(){
        var fromdateStr = moment($scope.fromDate, 'DD/MM/YYYY').format('DDMMYYYY');
        var toDateStr = moment($scope.toDate, 'DD/MM/YYYY').format('DDMMYYYY');
        $q.all([
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
                }
            }, function errorCallback(response) {
            }),
            $http({
                method: 'GET',
                url: 'cateringController/getHistoryStaff?fromDate='+fromdateStr+'&toDate=' + toDateStr,
                responseType: 'json'
            }).then(function successCallback(response) {
                if(response.data){
                    var dataArr = response.data;
                    $scope.arrData = angular.copy(dataArr);
                    drawTable();
                }
            }, function errorCallback(response) {
            })
        ]).then(function () {
            $scope.showPreload = false;
        });
    }
    $scope.getMealTimeName = function(mealTimeId){
        var mealTimeName = '';
        for(var i = 0 ; i < $scope.mealTimeArr.length; i++){
            if($scope.mealTimeArr[i].mealTimeId == mealTimeId){
                mealTimeName =  $scope.mealTimeArr[i].mealTimeName;
            }
        }
        return mealTimeName;
    }
    $scope.getLocationName = function(locationId){
        var locationName = '';
        for(var i = 0 ; i< $scope.locationArr.length; i++){
            if($scope.locationArr[i].locationId == locationId){
                locationName =  $scope.locationArr[i].locationName;
            }
        }
        return locationName;
    }
    $scope.getMealName = function(mealId){
        var mealName = '';
        for(var i = 0 ; i< $scope.mealArr.length; i++){
            if($scope.mealArr[i].mealId == mealId){
                mealName =  $scope.mealArr[i].mealName;
            }
        }
        return mealName;
    }
    $scope.getDepartmentName = function(departmentId){
        var deptNameFind = '';
        for(var i = 0 ; i< $scope.departmentArr.length; i++){
            if($scope.departmentArr[i].deptId == departmentId){
                deptNameFind =  $scope.departmentArr[i].deptName;
            }
        }
        return deptNameFind;
    }
    function drawTable(){
        $scope.tableParams = new NgTableParams({
            page: 1,
            count: 10,
        }, {
            counts: [5, 10, 50, 100],
            dataset: $scope.arrData,
        });
        ngTableEventsChannel.onAfterDataFiltered(function(eventListener, tableParams, filteredData ){
            $scope.dataFiltered = filteredData;
        });
    }
    $scope.editMeal = function(row){
        $scope.selectedItem = row;
        $scope.selectedItemClone = angular.copy($scope.selectedItem);
    }
    $scope.saveEditMeal = function(){
        $scope.selectedItem = $.extend($scope.selectedItem,$scope.selectedItemClone);
    }
    $scope.dateChange = function(){
        var fromdateStr = moment($scope.fromDate, 'DD/MM/YYYY').format('DDMMYYYY');
        var toDateStr = moment($scope.toDate, 'DD/MM/YYYY').format('DDMMYYYY');
        $http({
            method: 'GET',
            url: 'cateringController/getHistoryStaff?fromDate='+fromdateStr+'&toDate=' + toDateStr,
            responseType: 'json'
        }).then(function successCallback(response) {
            if(response.data){
                var dataArr = response.data;
                $scope.arrData = angular.copy(dataArr);
                drawTable();
            }
        }, function errorCallback(response) {
            console.log('getLstByStatus FAIL')
        });
    }
}]);