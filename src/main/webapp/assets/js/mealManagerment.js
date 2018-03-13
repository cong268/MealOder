var dataAllPage;
var dataAllTable;
myApp.controller('mealManagermentCtrl', ['$scope', 'NgTableParams', 'ngTableEventsChannel', '$http', function($scope, NgTableParams, ngTableEventsChannel, $http){
    $scope.demoCheckbox = 1;
    $scope.dataFiltered = [];
    $scope.statusMeal = false;
    $scope.locationArr = [];
    $scope.mealTimeArr = [];
    $scope.mealArr = [];
    $scope.shilfArr = [];
    $scope.arrData = [];
    moment.locale('fr');
    $scope.initData = function(){
        $http({
            method: 'GET',
            url: 'cateringController/getMealByDepartment?date=' + moment(new Date()).format('DDMMYYYY'),
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
            $scope.loadingPerformancePatri = false;
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

    $scope.submitMealManager = function(){
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
            url: 'cateringController/saveCatering',
            responseType: 'json',
            headers: {
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON'
            },
            data: listMealSave
        }).then(function successCallback(response) {
            alert('SUCCESS');
        }, function errorCallback(response) {

        })
    }

}]);