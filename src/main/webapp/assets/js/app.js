// DEFINED FOR NSRP APPLICATION
var myApp = angular.module('NsrpApplication', ['ngTable','tw.directives.clickOutside']);
myApp.controller('wrapperMenuCtrl', ['$scope', function($scope) {

}])
myApp.directive('dateRangePickerSingle', function() {
    return{
        restrict: 'A',
        require:'ngModel',
        link: function(scope, element, attribute, ngModel) {
            setTimeout(function(){
                element.daterangepicker({
                        singleDatePicker: true,
                        showDropdowns: true
                    },
                    function(start, end, label) {
                        $(this).val(moment(end).format('DD/MM/YYYY'));
                        ngModel.$setViewValue(moment(end).format('DD/MM/YYYY'));
                        ngModel.$render();
                    });
            }, 500);
        }
    };
});