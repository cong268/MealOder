var dataAllPage;
var dataAllTable;
myApp.controller('approveMealCtrl', ['$scope', 'NgTableParams', 'ngTableEventsChannel', '$http', function($scope, NgTableParams, ngTableEventsChannel, $http) {
    $scope.demoCheckbox = 1;
    $scope.dataFiltered = [];
    $scope.statusMeal = false;
    $scope.locationArr = [];
    $scope.mealTimeArr = [];
    $scope.mealArr = [];
    $scope.shilfArr = [];
    $scope.arrData = [];
    moment.locale('en');
    $scope.dateFilter = moment(new Date()).format('DD/MM/YYYY');
    $scope.initData = function(){
        var dateStr = moment($scope.dateFilter, 'DD/MM/YYYY').format('DDMMYYYY')
        $http({
            method: 'GET',
            url: 'cateringController/getLstByOder?date=' + dateStr,
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
            console.log('getLstByOder FAIL');
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
            $scope.checkStatus();
        });
    }
    $scope.checkStatus = function(){
        var countStatus = 0, countNumber = 0;
        angular.forEach($scope.dataFiltered, function(item){
            if(item.status == 1){
                countStatus++;
            }
            countNumber++;
        });
        if(countNumber === countStatus && countNumber !== 0){
            $scope.statusMeal = true;
            $('#select_all').prop('checked',true);
        } else {
            $scope.statusMeal = false;
            $('#select_all').prop('checked',false);
        }
    }

    $scope.filterAccept = function(){
        var dateInput = angular.element(document.getElementById("date-filter-input")).val();
        var dateStr = moment(dateInput, 'DD/MM/YYYY').format('DDMMYYYY')
        $http({
            method: 'GET',
            url: 'cateringController/getLstByOder?date=' + dateStr,
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
            console.log('getLstByOder FAIL');
        });
    }

    $scope.changeStatus = function (statusMeal) {
        angular.forEach($scope.dataFiltered, function(item){
            if(statusMeal == true){
                item.status = 1;
            } else if(statusMeal == false){
                item.status = 0;
            }
        });
    };

    $scope.submitApprove = function(){
        var dateInput = angular.element(document.getElementById("date-filter-input")).val();
        var dateStr = moment(dateInput, 'DD/MM/YYYY').format('DDMMYYYY');
        var listMealSave = [];
        angular.forEach($scope.arrData, function(item){
            if(item.status == 1){
                var obj = angular.copy(item);
                delete obj['status'];
                listMealSave.push(obj);
            }
        })
        $http({
            method: 'POST',
            url: 'cateringController/saveCateringByManager?date='+dateStr,
            responseType: 'json',
            headers: {
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON'
            },
            data: listMealSave
        }).then(function successCallback(response) {
            showSuccessAlert();
            $scope.initData();
        }, function errorCallback(response) {
            showErrorAlert();
        })
    }
}]);