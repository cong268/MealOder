(function ($) {
    "use strict";


    /*Focus input*/



})(jQuery);
var myApp = angular.module('loginApplication',[]).controller('loginController', ['$scope' , function($scope, NgTableParams){
    $scope.errorMessage = angular.element(document.getElementById("errorMess")).val();
    $scope.clickHideMessage = function(){
        $scope.errorMessage = null;
    }
}]);
