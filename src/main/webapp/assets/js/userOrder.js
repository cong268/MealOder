myApp.controller('userOrderCtrl', ['$scope',  '$http', function($scope, $http){

    $scope.initData = function(){
        $http({
            method: 'GET',
            url: 'cateringController/getMealByStaff',
            responseType: 'json'
        }).then(function successCallback(response) {
            if(response.data){
               console.log(response.data)
            }
        }, function errorCallback(response) {
            $scope.loadingPerformancePatri = false;
        });
    }
}]);
