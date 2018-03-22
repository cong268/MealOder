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
                showSuccessAlert();
                $scope.staffObj = undefined;
            }, function errorCallback(response) {
                showErrorAlert();
            })
        }
    }
}])