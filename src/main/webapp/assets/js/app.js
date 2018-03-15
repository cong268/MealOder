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
                        showDropdowns: true,
                        locale: {
                            format: 'DD/MM/YYYY'
                        },
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
myApp.directive('dateRangePickerSingleMin', function() {
    return{
        restrict: 'A',
        require:'ngModel',
        link: function(scope, element, attribute, ngModel) {
            setTimeout(function(){
                element.daterangepicker({
                    singleDatePicker: true,
                    showDropdowns: true,
                    locale: {
                        format: 'DD/MM/YYYY'
                    },
                    minDate: moment().format('DD/MM/YYYY')
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
myApp.directive('dateRangePickerDoubleMin', function() {
    return{
        restrict: 'A',
        require:'ngModel',
        scope: {
            callChange: '&'
        },
        link: function($scope, element, attribute, ngModel) {
            setTimeout(function(){
                element.daterangepicker({
                    singleDatePicker: false,
                    showDropdowns: true,
                    locale: {
                        format: 'DD/MM/YYYY'
                    },
                    minDate: moment().format('DD/MM/YYYY')
                },
                function(start, end, label) {
                    $scope.callChange({
                        fromDate: moment(start).format('DD/MM/YYYY'),
                        toDate: moment(end).format('DD/MM/YYYY')
                    });
                    $(this).val(moment(start).format('DD/MM/YYYY')+' - '+moment(end).format('DD/MM/YYYY'));
                    ngModel.$setViewValue(moment(start).format('DD/MM/YYYY')+' - '+moment(end).format('DD/MM/YYYY'));
                    ngModel.$render();
                });
            }, 500);
        }
    };
});
myApp.directive('tooltip', function () {
    return {
        restrict: 'A',
        link: function ($scope, element, attrs) {
            $scope.hidenTooltip = function () {
                $(element).tooltipster('hide');
            }
            element.ready(function () {
                $(element).tooltipster({
                    animation: attrs.animation,
                    theme: 'tooltipster-borderless-cuzt'
                });
                $scope.$watch(
                    function () {
                        return attrs.title;
                    },
                    function (newValue, oldValue) {
                        if ($(element).length&&newValue) {
                            $(element).tooltipster(
                                'content', newValue
                            );
                        } else {
                            $(element).tooltipster('destroy');
                        }
                    }
                );
            });

        }
    };
});
function showSuccessAlert() {
    $('.popup-successful').fadeIn(function(){
        setTimeout(function () {
            $('.popup-successful').fadeOut();
        }, 3000);
    })
}
function showErrorAlert() {
    $('.popup-error').fadeIn(function(){
        setTimeout(function () {
            $('.popup-error').fadeOut();
        }, 3000);
    })
}
