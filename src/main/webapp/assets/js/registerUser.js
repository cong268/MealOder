myApp.controller('registerUserCtrl', ['$scope', '$http' , function($scope, $http){
    $scope.arrRole = [];

    $scope.initData = function(){
        //getAllRole
        $http({
            method: 'GET',
            url: 'staffController/getAllRole',
            responseType: 'json'
        }).then(function successCallback(response) {
            if(response.data){
                $scope.arrRole = response.data;
            }
        }, function errorCallback(response) {
            console.log('getAllRole FAIL');
        });
    }
    $scope.submitRegister = function(isValid){
        if(isValid){
            $http({
                method: 'POST',
                url: 'staffController/saveUser',
                responseType: 'json',
                headers: {
                    contentType: "application/json; charset=utf-8",
                    dataType: 'JSON'
                },
                data: $scope.userObj
            }).then(function successCallback(response) {
                alert('SUCCESS');
            }, function errorCallback(response) {
                alert('FAIL');
            })
        }
    }
}])