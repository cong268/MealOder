myApp.controller('registerStaffCtrl', ['$scope', '$http' , function($scope, $http){
    $scope.submitRegisterStaff = function(isValid){
        if(isValid){
            $http({
                method: 'POST',
                url: 'staffController/saveStaff',
                responseType: 'json',
                headers: {
                    contentType: "application/json; charset=utf-8",
                    dataType: 'JSON'
                },
                data: $scope.staffObj
            }).then(function successCallback(response) {
                showSuccessAlert();
                $scope.staffObj = undefined;
            }, function errorCallback(response) {
                showErrorAlert();
            })
        }
    }
}])