var dataAllPageOrder, dataAllTableOrder;
myApp.controller('editStaffCtrl', ['$scope', 'NgTableParams', 'ngTableEventsChannel', '$http', '$q', function($scope, NgTableParams, ngTableEventsChannel, $http, $q){
    $scope.dataFilteredOrder = [];
    $scope.locationArrOrder = [];
    $scope.mealTimeArrOrder = [];
    $scope.mealArrOrder = [];
    $scope.shilfArrOrder = [];
    $scope.arrDataOrder = [];
    $scope.dateFilter = moment(new Date()).format('DD/MM/YYYY');
    $scope.staffIdFilter = '';
    $scope.filterAccept = function() {
        var dateStr = moment($scope.dateFilter, 'DD/MM/YYYY').format('DDMMYYYY')
        if($scope.staffIdFilter != ''){
            $http({
                method: 'GET',
                url: 'cateringController/getLstApproOfStaff?staffId='+$scope.staffIdFilter+'&date=' + dateStr,
                responseType: 'json'
            }).then(function successCallback(response) {
                if(response.data){
                    dataAllPageOrder = response.data;
                    dataAllTableOrder = dataAllPageOrder.listMealOder;
                    $scope.locationArrOrder = dataAllPageOrder.lstLocation;
                    $scope.mealTimeArrOrder = dataAllPageOrder.lstMealTime;
                    $scope.mealArrOrder = dataAllPageOrder.lstMealType;
                    $scope.arrDataOrder = angular.copy(dataAllTableOrder);
                    drawTableOrder();
                }
            }, function errorCallback(response) {
                console.log('getLstByOder FAIL');
            })
        } else {
            $scope.arrDataOrder = [];
            drawTableOrder();
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
        for(var i = 0 ; i< $scope.mealTimeArrOrder.length; i++){
            if($scope.mealTimeArrOrder[i].mealTimeId == mealTimeId){
                mealTimeName =  $scope.mealTimeArrOrder[i].mealTimeName;
            }
        }
        return mealTimeName;
    }
    $scope.getLocationName = function(locationId){
        var locationName = '';
        for(var i = 0 ; i< $scope.locationArrOrder.length; i++){
            if($scope.locationArrOrder[i].locationId == locationId){
                locationName =  $scope.locationArrOrder[i].locationName;
            }
        }
        return locationName;
    }
    $scope.getMealName = function(mealId){
        var mealName = '';
        for(var i = 0 ; i< $scope.mealArrOrder.length; i++){
            if($scope.mealArrOrder[i].mealId == mealId){
                mealName =  $scope.mealArrOrder[i].mealName;
            }
        }
        return mealName;
    }
    $scope.editMeal = function(row){
        $scope.selectedItem = row;
        $scope.selectedItemClone = angular.copy($scope.selectedItem);
    }
    $scope.saveEditMeal = function(){
        var objClone = angular.copy($scope.selectedItemClone);
        $http({
            method: 'POST',
            url: 'cateringController/updateCatering',
            responseType: 'json',
            headers: {
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON'
            },
            data: objClone
        }).then(function successCallback(response) {
            showSuccessAlert();
            $scope.selectedItem = $.extend($scope.selectedItem,$scope.selectedItemClone);
            $('#editMealModal').modal('toggle');
        }, function errorCallback(response) {
            showErrorAlert();
        })

    }
    $scope.showDeleteMealOrder = function(row){
        $scope.deleteObjMeal = row;
    }
    $scope.deleteMealUser = function(){
        var objClone = angular.copy($scope.deleteObjMeal);
        $http({
            method: 'POST',
            url: 'cateringController/deleteCatering',
            responseType: 'json',
            headers: {
                contentType: "application/json; charset=utf-8",
                dataType: 'JSON'
            },
            data: objClone
        }).then(function successCallback(response) {
            showSuccessAlert();
            var index = $scope.arrDataOrder.indexOf($scope.deleteObjMeal);
            if(index > -1){
                $scope.arrDataOrder.splice(index, 1);
                drawTableOrder();
            } else {
                $scope.initData();
            }
        }, function errorCallback(response) {
            showErrorAlert();
        })
    }
}]);