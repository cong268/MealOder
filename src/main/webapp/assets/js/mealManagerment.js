var dataAllPage, dataAllTable, dataAllPageOrder, dataAllTableOrder;
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
    $scope.lstDelMeal = [];
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
                    dataAllPageOrder = response.data;
                    dataAllTableOrder = dataAllPageOrder.listMealOder;
                    $scope.locationArrOrder = dataAllPageOrder.lstLocation;
                    $scope.mealTimeArrOrder = dataAllPageOrder.lstMealTime;
                    $scope.mealArrOrder = dataAllPageOrder.lstMealType;
                    $scope.shilfArrOrder = dataAllPageOrder.lstShift;
                    $scope.arrDataOrder = angular.copy(dataAllTableOrder);
                    drawTableOrder();
                }
            }, function errorCallback(response) {
                console.log('getLstByOder FAIL');
            }),$http({
                method: 'GET',
                url: 'cateringController/getLstNewByDept',
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
                console.log('getLstNewByDept FAIL');
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
        ngTableEventsChannel.onAfterDataFiltered(function(eventListener, tableParams, filteredData ){
            $scope.dataFiltered = filteredData || tableParams.data;
            $scope.checkStatus();
        });
    }
    $scope.checkStatus = function(){
        var countStatus = 0, countNumber = 0;
        angular.forEach($scope.dataFiltered, function(item){
            if(item.checked == 1){
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
        ngTableEventsChannel.onAfterDataFiltered(function(eventListener, tableParamsOrder, filteredDataOrder ){
            $scope.dataFilteredOrder = filteredDataOrder || tableParamsOrder.data;
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
                    dataAllPageOrder = response.data;
                    dataAllTableOrder = dataAllPageOrder.listMealOder;
                    $scope.locationArrOrder = dataAllPageOrder.lstLocation;
                    $scope.mealTimeArrOrder = dataAllPageOrder.lstMealTime;
                    $scope.mealArrOrder = dataAllPageOrder.lstMealType;
                    $scope.shilfArrOrder = dataAllPageOrder.lstShift;
                    $scope.arrDataOrder = angular.copy(dataAllTableOrder);
                    drawTableOrder();
                }
            }, function errorCallback(response) {
                console.log('getLstByOder FAIL');
            }),$http({
                method: 'GET',
                url: 'cateringController/getLstNewByDept',
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
        angular.forEach($scope.arrData, function(item){
            if(statusMeal == true){
                item.checked = 1;
            } else if(statusMeal == false){
                item.checked = 0;
            }
        });
    };
    $scope.showDeleteMealOrder = function(row){
        $scope.mealOrderToDelete = row;
    }
    $scope.deleteMeal = function(){
        var index = $scope.arrDataOrder.indexOf($scope.mealOrderToDelete);
        var cloneObj = angular.copy($scope.mealOrderToDelete);
        $scope.lstDelMeal.push(cloneObj);
        $scope.arrDataOrder.splice(index,1);
        drawTableOrder();
    }
    $scope.saveEditMeal = function(){
        var index = $scope.arrDataOrder.indexOf($scope.selectedItem);
        var isOverWrite = false;
        for(var i = 0; i < $scope.arrDataOrder.length; i++) {
            if($scope.arrDataOrder[i].staffId == $scope.selectedItem.staffId
                && $scope.arrDataOrder[i].mealTimeId == $scope.selectedItem.mealTimeId
                && index != i){
                $scope.arrDataOrder[i] = $.extend($scope.arrDataOrder[i], $scope.selectedItem);
                $scope.arrDataOrder.splice(index,1);
                isOverWrite = true;
                break;
            }
        }
        if(isOverWrite == false){
            $scope.selectedItem = $.extend($scope.selectedItem,$scope.selectedItemClone);
        }
        drawTableOrder();
    }
    $scope.saveAddMeal = function(){
        for(var i = 0; i < $scope.arrData.length; i++){
            if($scope.arrData[i]){
                if($scope.arrData[i].checked == 1){
                    var obj = angular.copy($scope.arrData[i]);
                    delete obj['checked'];
                    var isAdded = false;
                    for(var j = 0; j < $scope.arrDataOrder.length; j++) {
                        if($scope.arrDataOrder[j].staffId == obj.staffId
                            && $scope.arrDataOrder[j].mealTimeId == obj.mealTimeId){
                            $scope.arrDataOrder[j] = $.extend($scope.arrDataOrder[j], obj);
                            isAdded = true;
                            break;
                        }
                    }
                    if(isAdded == false){
                        $scope.arrDataOrder.push(obj);
                        isAdded = true;
                    }
                }
            }
        }
        $scope.arrData = angular.copy(dataAllTable);
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
        var dataObj = angular.copy($scope.arrDataOrder);
        var dataDelObj = angular.copy($scope.lstDelMeal);
        angular.forEach(dataObj, function (item) {
            delete item['checked'];
        })
        angular.forEach(dataDelObj, function (item) {
            delete item['checked'];
        })
        var dataBean = {
            lstCateringSave: dataObj,
            lstCateringReject: dataDelObj
        };
        $http({
            method: 'POST',
            url: 'cateringController/saveCateringByManager?date=' + dateStr,
            responseType: 'json',
            headers: {
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON'
            },
            data: dataBean
        }).then(function successCallback(response) {
            showSuccessAlert();
            $scope.initData();
        }, function errorCallback(response) {
            showErrorAlert();
        })
    }

}]);