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
    $scope.departmentArrClone = [];
    $scope.departmentIdFilter='';
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
                angular.forEach($scope.arrData, function(item){
                    item.checked = 1;
                });
                drawTable();
                applyCloneArr();
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
            filter: {
                'departmentId':$scope.departmentIdFilter,
            }
        }, {
            dataset: $scope.arrData,
        });

        ngTableEventsChannel.onAfterDataFiltered(function(eventListener, tableParams, filteredData ){
            $scope.dataFiltered = filteredData || tableParams.data;
            $scope.checkStatus();
        });
    }
    function applyCloneArr(){
        $scope.departmentArrClone = angular.copy($scope.departmentArr);
        $scope.departmentArrClone.unshift({
            deptId : '',
            deptName: 'ALL'
        })
    }
    $scope.$watchGroup(['departmentIdFilter'], function(){
        if($scope.tableParams){
            $scope.tableParams.filter(
                {
                    'departmentId':$scope.departmentIdFilter
                }
            );
        }
    })
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
                angular.forEach($scope.arrData, function(item){
                    item.checked = 1;
                });
                drawTable();
            }
        }, function errorCallback(response) {
            console.log('getLstByStatus FAIL');
        });
    }
    $scope.changeStatus = function (statusMeal) {
        angular.forEach($scope.dataFiltered, function(item){
            if(statusMeal == true){
                item.checked = 1;
            } else if(statusMeal == false){
                item.checked = 0;
            }
        });
    };
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
    $scope.submitCarter = function(){
        var dateInput = angular.element(document.getElementById("date-filter-input")).val();
        var dateStr = moment(dateInput, 'DD/MM/YYYY').format('DDMMYYYY');
        var lstDataSave = [];
        angular.forEach($scope.arrData, function(item){
            if(item.checked == 1){
                var objClone = angular.copy(item);
                delete objClone['checked'];
                lstDataSave.push(objClone);
            }
        });
        $http({
            method: 'POST',
            url: 'cateringController/saveCateringByAdmin?date='+dateStr,
            responseType: 'json',
            headers: {
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON'
            },
            data: lstDataSave
        }).then(function successCallback(response) {
            showSuccessAlert();
            $scope.initData();
        }, function errorCallback(response) {
            showErrorAlert();
        })
    }
}]);