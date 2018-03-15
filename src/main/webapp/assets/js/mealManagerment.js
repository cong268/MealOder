var dataAllPage;
var dataAllTable;
myApp.controller('mealManagermentCtrl', ['$scope', 'NgTableParams', 'ngTableEventsChannel', '$http', '$q', function($scope, NgTableParams, ngTableEventsChannel, $http, $q){
    $scope.demoCheckbox = 1;
    $scope.dataFiltered = [];
    $scope.statusMeal = false;
    $scope.visitorObject = undefined;
    $scope.locationArr = [];
    $scope.mealTimeArr = [];
    $scope.mealArr = [];
    $scope.shilfArr = [];
    $scope.arrData = [];
    //ORDER
    $scope.dataFilteredOrder = [];
    $scope.locationArrOrder = [];
    $scope.mealTimeArrOrder = [];
    $scope.mealArrOrder = [];
    $scope.shilfArrOrder = [];
    $scope.arrDataOrder = [];
    moment.locale('en');
    $scope.dateFilter = moment(new Date()).format('DD/MM/YYYY');
    $scope.initData = function(){
        var dateStr = moment($scope.dateFilter, 'DD/MM/YYYY').format('DDMMYYYY')
        $q.all([
            $http({
                method: 'GET',
                url: 'cateringController/getLstByOder?date=' + dateStr,
                responseType: 'json'
            }).then(function successCallback(response) {
                if(response.data){
                    dataAllPage = response.data;
                    dataAllTable = dataAllPage.listMealOder;
                    $scope.locationArrOrder = dataAllPage.lstLocation;
                    $scope.mealTimeArrOrder = dataAllPage.lstMealTime;
                    $scope.mealArrOrder = dataAllPage.lstMealType;
                    $scope.shilfArrOrder = dataAllPage.lstShift;
                    $scope.arrDataOrder = angular.copy(dataAllTable);
                    drawTableOrder();
                }
            }, function errorCallback(response) {
                console.log('getMealByDepartment FAIL');
            }),$http({
                method: 'GET',
                url: 'cateringController/getMealByDepartment?date=' + dateStr,
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
                console.log('getMealByDepartment FAIL');
            })
        ]).then(function () {
            $scope.showPreload = false;
        });
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
    function drawTableOrder(){
        $scope.tableParamsOrder = new NgTableParams({
            page: 1,
            count: 10
        }, {
            dataset: $scope.arrDataOrder,
        });
        ngTableEventsChannel.onAfterReloadData(function(tableParams, filteredData){
            $scope.dataFilteredOrder = filteredData || tableParams.data;
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
    $scope.filterAccept = function(){
        var dateInput = angular.element(document.getElementById("date-filter-input")).val();
        var dateStr = moment(dateInput, 'DD/MM/YYYY').format('DDMMYYYY')
        $q.all([
            $http({
                method: 'GET',
                url: 'cateringController/getLstByOder?date=' + dateStr,
                responseType: 'json'
            }).then(function successCallback(response) {
                if(response.data){
                    dataAllPage = response.data;
                    dataAllTable = dataAllPage.listMealOder;
                    $scope.locationArrOrder = dataAllPage.lstLocation;
                    $scope.mealTimeArrOrder = dataAllPage.lstMealTime;
                    $scope.mealArrOrder = dataAllPage.lstMealType;
                    $scope.shilfArrOrder = dataAllPage.lstShift;
                    $scope.arrDataOrder = angular.copy(dataAllTable);
                    drawTableOrder();
                }
            }, function errorCallback(response) {
                console.log('getLstByOder FAIL');
            }),$http({
                method: 'GET',
                url: 'cateringController/getMealByDepartment?date=' + dateStr,
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
                console.log('getMealByDepartment FAIL');
            })
        ]).then(function () {
            $scope.showPreload = false;
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
    $scope.showDeleteMealOrder = function(row){
        $scope.mealOrderToDelete = row;
    }
    $scope.deleteMeal = function(){
        var index = $scope.arrDataOrder.indexOf($scope.mealOrderToDelete);
        var cloneObj = angular.copy($scope.mealOrderToDelete);
        $scope.arrDataOrder.splice(index,1);
        drawTableOrder();
        $scope.arrData.push(cloneObj);
        drawTable();
    }
    $scope.saveEditMeal = function(){
        $scope.selectedItem = $.extend($scope.selectedItem,$scope.selectedItemClone);
    }
    $scope.saveAddMeal = function(){
        for(var i = 0; i < $scope.arrData.length; i++){
            if($scope.arrData[i]){
                if($scope.arrData[i].status == 1){
                    var obj = angular.copy($scope.arrData[i]);
                    $scope.arrDataOrder.push(obj);
                    $scope.arrData.splice(i,1);
                    i = -1;
                }
            }
        }
        drawTableOrder();
        drawTable();
    }
    $scope.editMeal = function(row){
        $scope.selectedItem = row;
        $scope.selectedItemClone = angular.copy($scope.selectedItem);
    }
    $scope.clearVisitorObject = function(){
        $scope.visitorObject = null;
    }
    $scope.addVisitorObject = function(){
        var objToPush = angular.copy($scope.visitorObject);
        $scope.arrDataOrder.push(objToPush);
        drawTableOrder();
    }

    $scope.submitMealManager = function(){
        var dateInput = angular.element(document.getElementById("date-filter-input")).val();
        var dateStr = moment(dateInput, 'DD/MM/YYYY').format('DDMMYYYY');
        $http({
            method: 'POST',
            url: 'cateringController/saveCatering?date=' + dateStr,
            responseType: 'json',
            headers: {
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON'
            },
            data: listMealSave
        }).then(function successCallback(response) {
            alert('SUCCESS');
        }, function errorCallback(response) {
            alert('FAIL');
        })
    }

}]);