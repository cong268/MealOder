myApp.controller('carteredAdminCtrl', ['$scope', 'NgTableParams', 'ngTableEventsChannel', '$http', function($scope, NgTableParams, ngTableEventsChannel, $http) {
    $scope.demoCheckbox = 1;
    $scope.dataFiltered = [];
    $scope.locationArr = [];
    $scope.mealTimeArr = [];
    $scope.mealArr = [];
    $scope.shilfArr = [];
    $scope.arrData = [];
    moment.locale('en');
    $scope.fromDate = moment(new Date()).format('DD/MM/YYYY');
    $scope.toDate = moment(new Date()).format('DD/MM/YYYY');
    $scope.initData = function(){
        var fromdateStr = moment($scope.fromDate, 'DD/MM/YYYY').format('DDMMYYYY');
        var toDateStr = moment($scope.toDate, 'DD/MM/YYYY').format('DDMMYYYY');
        $http({
            method: 'GET',
            url: 'cateringController/getLstByDate?fromDate='+fromdateStr+'&toDate=' + toDateStr,
            responseType: 'json'
        }).then(function successCallback(response) {
            if(response.data){
                var dataAllPage = response.data;
                var dataAllTable = dataAllPage.listMealOder;
                $scope.locationArr = dataAllPage.lstLocation;
                $scope.mealTimeArr = dataAllPage.lstMealTime;
                $scope.mealArr = dataAllPage.lstMealType;
                $scope.shilfArr = dataAllPage.lstShift;
                $scope.arrData = angular.copy(dataAllTable);
                drawTable();
            }
        }, function errorCallback(response) {
            $scope.loadingPerformancePatri = false;
        });
    }
    $scope.getMealTimeName = function(mealTimeId){
        var mealTimeName = '';
        for(var i = 0 ; i< $scope.mealTimeArr.length; i++){
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
    $scope.getShiftName = function(shiftId){
        var shiftName = '';
        for(var i = 0 ; i< $scope.shilfArr.length; i++){
            if($scope.shilfArr[i].shiftId == shiftId){
                shiftName =  $scope.shilfArr[i].shiftName;
            }
        }
        return shiftName;
    }
    function drawTable(){
        $scope.tableParams = new NgTableParams({
            page: 1,
            count: 10
        }, {
            dataset: $scope.arrData,
        });

        ngTableEventsChannel.onAfterReloadData(function(tableParams, filteredData){
            $scope.dataFiltered = filteredData || tableParams.data;
        });
    }

    $scope.filterAccept = function(){
        var fromDateInput = angular.element(document.getElementById("date-filter-from")).val();
        var toDateInput = angular.element(document.getElementById("date-filter-to")).val();
        var fromdateStr = moment(fromDateInput, 'DD/MM/YYYY').format('DDMMYYYY');
        var toDateStr = moment(toDateInput, 'DD/MM/YYYY').format('DDMMYYYY');
        $http({
            method: 'GET',
            url: 'cateringController/getLstByDate?fromDate='+fromdateStr+'&toDate=' + toDateStr,
            responseType: 'json'
        }).then(function successCallback(response) {
            if(response.data){
                dataAllPage = response.data;
                dataAllTable = dataAllPage.listMealOder;
                $scope.locationArr = dataAllPage.lstLocation;
                $scope.mealTimeArr = dataAllPage.lstMealTime;
                $scope.mealArr = dataAllPage.lstMealType;
                $scope.shilfArr = dataAllPage.lstShift;
                $scope.arrData = angular.copy(dataAllTable);
                drawTable();
            }
        }, function errorCallback(response) {
            console.log('getLstByStatus FAIL')
        });
    }

    $scope.exportData = function(){

    }
}]);