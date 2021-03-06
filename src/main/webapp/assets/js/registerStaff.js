myApp.controller('registerStaffCtrl', ['$scope', '$http' , function($scope, $http){
    $scope.initData = function(){
        $http({
            method: 'GET',
            url: 'staffController/getAllDepartment',
            responseType: 'json'
        }).then(function successCallback(response) {
            if(response.data){
                $scope.departmentArr  = response.data;
            }
        }, function errorCallback(response) {
            console.log('getLstByOder FAIL');
        })
    }
    $scope.submitRegisterStaff = function(isValid){
        if(isValid){
            $http({
                method: 'POST',
                url: 'staffController/saveStaff?isNew=true',
                responseType: 'json',
                headers: {
                    contentType: "application/json; charset=utf-8",
                    dataType: 'JSON'
                },
                data: $scope.staffObj
            }).then(function successCallback(response) {
                if(response.data == 1){
                    showSuccessAlert();
                    $scope.staffObj = undefined;
                } else if(response.data == 2){
                    $scope.errorExists = true;
                } else {
                    showErrorAlert();
                }
            }, function errorCallback(response) {
                showErrorAlert();
            })
        }
    }
}])