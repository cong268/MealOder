var dataAllPage;
var dataAllTable;
myApp.controller('carterAdminCtrl', ['$scope', 'NgTableParams', 'ngTableEventsChannel', '$http', function($scope, NgTableParams, ngTableEventsChannel, $http) {
    $scope.demoCheckbox = 1;
    $scope.dataFiltered = [];
    $scope.locationArr = [];
    $scope.mealTimeArr = [];
    $scope.mealArr = [];
    $scope.shilfArr = [];
    $scope.arrData = [];
    $scope.departmentArr = [];
    moment.locale('en');
    $scope.dateFilter = moment(new Date()).format('DD/MM/YYYY');
    $scope.initData = function(){
        var dateStr = moment($scope.dateFilter, 'DD/MM/YYYY').format('DDMMYYYY')
        $http({
            method: 'GET',
            url: 'cateringController/getLstByStatus?date=' + dateStr,
            responseType: 'json'
        }).then(function successCallback(response) {
            if(response.data){
                dataAllPage = response.data;
                dataAllTable = dataAllPage.listMealOder;
                $scope.locationArr = dataAllPage.lstLocation;
                $scope.mealTimeArr = dataAllPage.lstMealTime;
                $scope.mealArr = dataAllPage.lstMealType;
                $scope.shilfArr = dataAllPage.lstShift;
                $scope.departmentArr = dataAllPage.lstDepartMent;
                $scope.arrData = angular.copy(dataAllTable);
                drawTable();
            }
        }, function errorCallback(response) {
            console.log('getLstByStatus FAIL');
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
    // $scope.getShiftName = function(shiftId){
    //     var shiftName = '';
    //     for(var i = 0 ; i< $scope.shilfArr.length; i++){
    //         if($scope.shilfArr[i].shiftId == shiftId){
    //             shiftName =  $scope.shilfArr[i].shiftName;
    //         }
    //     }
    //     return shiftName;
    // }
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
            count: 10
        }, {
            dataset: $scope.arrData,
        });

        ngTableEventsChannel.onAfterDataFiltered(function(eventListener, tableParams, filteredData ){
            $scope.dataFiltered = filteredData || tableParams.data;
        });
    }

    $scope.filterAccept = function(){
        var dateInput = angular.element(document.getElementById("date-filter-input")).val();
        var dateStr = moment(dateInput, 'DD/MM/YYYY').format('DDMMYYYY')
        $http({
            method: 'GET',
            url: 'cateringController/getLstByStatus?date=' + dateStr,
            responseType: 'json'
        }).then(function successCallback(response) {
            if(response.data){
                dataAllPage = response.data;
                dataAllTable = dataAllPage.listMealOder;
                $scope.locationArr = dataAllPage.lstLocation;
                $scope.mealTimeArr = dataAllPage.lstMealTime;
                $scope.mealArr = dataAllPage.lstMealType;
                $scope.shilfArr = dataAllPage.lstShift;
                $scope.departmentArr = dataAllPage.lstDepartMent;
                $scope.arrData = angular.copy(dataAllTable);
                drawTable();
            }
        }, function errorCallback(response) {
            console.log('getLstByStatus FAIL');
        });
    }


    $scope.submitCarter = function(){
        var dateInput = angular.element(document.getElementById("date-filter-input")).val();
        var dateStr = moment(dateInput, 'DD/MM/YYYY').format('DDMMYYYY');
        $http({
            method: 'POST',
            url: 'cateringController/saveCateringByAdmin?date='+dateStr,
            responseType: 'json',
            headers: {
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON'
            },
            data: $scope.arrData
        }).then(function successCallback(response) {
            showSuccessAlert();
            $scope.initData();
        }, function errorCallback(response) {
            showErrorAlert();
        })
    }
}]);