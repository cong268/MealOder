myApp.controller('showAdminCtrl', ['$scope', 'NgTableParams', 'ngTableEventsChannel', '$http', function($scope, NgTableParams, ngTableEventsChannel, $http) {
    $scope.demoCheckbox = 1;
    $scope.dataFiltered = [];
    $scope.locationArr = [];
    $scope.mealTimeArr = [];
    $scope.mealArr = [];
    $scope.arrData = [];
    $scope.departmentArr = [];
    /*CLONED*/
    $scope.locationArrClone = [];
    $scope.mealTimeArrClone = [];
    $scope.mealArrClone = [];
    $scope.departmentArrClone = [];
    $scope.mealTimeIdFilter='',
        $scope.mealIdFilter='',
        $scope.locationIdFilter='',
        $scope.departmentIdFilter='',

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
                $scope.departmentArr = dataAllPage.lstDepartMent;
                $scope.arrData = angular.copy(dataAllTable);
                drawTable();
                applyCloneArr();
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
                'mealTimeId': $scope.mealTimeIdFilter,
                'mealId': $scope.mealIdFilter,
                'locationId': $scope.locationIdFilter,
                'departmentId':$scope.departmentIdFilter
            }
        }, {
            counts: [5, 10, 50, 100],
            dataset: $scope.arrData,
        });
        ngTableEventsChannel.onAfterDataFiltered(function(eventListener, tableParams, filteredData ){
            $scope.dataFiltered = filteredData;
        });
    }
    function applyCloneArr(){
        $scope.locationArrClone = angular.copy($scope.locationArr);
        $scope.mealTimeArrClone = angular.copy($scope.mealTimeArr);
        $scope.mealArrClone = angular.copy($scope.mealArr);
        $scope.departmentArrClone = angular.copy($scope.departmentArr);
        $scope.locationArrClone.unshift({
            locationId : '',
            locationName: 'ALL'
        })
        $scope.mealTimeArrClone.unshift({
            mealTimeId : '',
            mealTimeName: 'ALL'
        })
        $scope.mealArrClone.unshift({
            mealId : '',
            mealName: 'ALL'
        })
        $scope.departmentArrClone.unshift({
            deptId : '',
            deptName: 'ALL'
        })
    }
    $scope.$watch('dataFiltered', function(){
        if($scope.dataFiltered){
            angular.forEach($scope.departmentArr, function(dept){
                dept['count'] = 0;
                angular.forEach($scope.dataFiltered, function (data) {
                    if(data.departmentId == dept.deptId){
                        dept['count']++;
                    }
                })
            })
        }
    })
    $scope.$watchGroup(['mealTimeIdFilter','mealIdFilter','locationIdFilter','departmentIdFilter'], function(){
        if($scope.tableParams){
            $scope.tableParams.filter(
                {
                    'mealTimeId': $scope.mealTimeIdFilter,
                    'mealId': $scope.mealIdFilter,
                    'locationId': $scope.locationIdFilter,
                    'departmentId':$scope.departmentIdFilter
                }
            );
        }
    })
    $scope.dateChange = function(){
        var fromdateStr = moment($scope.fromDate, 'DD/MM/YYYY').format('DDMMYYYY');
        var toDateStr = moment($scope.toDate, 'DD/MM/YYYY').format('DDMMYYYY');
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
                $scope.departmentArr = dataAllPage.lstDepartMent;
                $scope.arrData = angular.copy(dataAllTable);
                applyCloneArr();
                drawTable();
            }
        }, function errorCallback(response) {
            console.log('getLstByStatus FAIL')
        });
    }

    $scope.exportData = function(){
        var fromdateStr = moment($scope.fromDate, 'DD/MM/YYYY').format('DDMMYYYY');
        var toDateStr = moment($scope.toDate, 'DD/MM/YYYY').format('DDMMYYYY');
        window.location = _contextPath + '/exportData/exportCanteen?fromDate=' + fromdateStr + '&toDate=' + toDateStr;
    }
}]);