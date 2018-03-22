// DEFINED FOR NSRP APPLICATION
"use strict";
var myApp = angular.module('NsrpApplication', ['ngTable','tw.directives.clickOutside']);
myApp.controller('userInfoCtrl', ['$scope', function($scope) {
    $scope.initData = function(){

    }
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
                        format: 'DD/MM/YYYY',
                        firstDay: 1
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
myApp.directive('dateRangePickerSingleMin', function($http) {
    return{
        restrict: 'A',
        require:'ngModel',
        link: function(scope, element, attribute, ngModel) {
            setTimeout(function(){
                $http({
                    method: 'GET',
                    url: "cateringController/getTime",
                    responseType: 'json',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                }).then(function successCallback(response) {
                    var timeObj = response.data;
                    setDatePicker(moment(timeObj.timeServer, 'DD/MM/YYYY HH:mm:ss').format('DD/MM/YYYY'));
                }, function errorCallback(response) {
                    console.log('GET TIME ERROR');
                })
                function setDatePicker(minDateStr){
                    element.daterangepicker({
                        singleDatePicker: true,
                        showDropdowns: true,
                        locale: {
                            format: 'DD/MM/YYYY',
                            firstDay: 1
                        },
                        minDate: minDateStr
                    },
                    function(start, end, label) {
                        $(this).val(moment(end).format('DD/MM/YYYY'));
                        ngModel.$setViewValue(moment(end).format('DD/MM/YYYY'));
                        ngModel.$render();
                    });
                }
            }, 500);
        }
    };
});
myApp.directive('dateRangePickerDoubleMin', function($http) {
    return{
        restrict: 'A',
        require:'ngModel',
        scope: {
            callChange: '&',
            applyText: '=',
            cancelText: '='
        },
        link: function($scope, element, attribute, ngModel) {
            setTimeout(function(){
                $http({
                    method: 'GET',
                    url: "cateringController/getTime",
                    responseType: 'json',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                }).then(function successCallback(response) {
                    var timeObj = response.data;
                    $scope.callChange({
                        fromDate: moment(timeObj.timeServer, 'DD/MM/YYYY HH:mm:ss').format('DD/MM/YYYY'),
                        toDate: moment(timeObj.timeServer, 'DD/MM/YYYY HH:mm:ss').format('DD/MM/YYYY')
                    });
                    setDatePicker(moment(timeObj.timeServer, 'DD/MM/YYYY HH:mm:ss').format('DD/MM/YYYY'));
                }, function errorCallback(response) {
                    console.log('GET TIME ERROR');
                })
                function setDatePicker(minDateStr) {
                    element.daterangepicker({
                        singleDatePicker: false,
                        showDropdowns: true,
                        locale: {
                            format: 'DD/MM/YYYY',
                            firstDay: 1
                        },
                        linkedCalendars:false,
                        minDate: minDateStr
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
                    if($scope.applyText){
                        $('.daterangepicker .range_inputs .applyBtn').text($scope.applyText);
                    }
                    if($scope.cancelText){
                        $('.daterangepicker .range_inputs .cancelBtn').text($scope.cancelText);
                    }
                }
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

