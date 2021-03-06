var myApp = angular.module('loginApplication',[]).controller('loginController', ['$scope', '$http' , function($scope, $http){
    angular.element(document).ready(function(){
        (function ($) {
            "use strict";
            /*Focus input*/
            $('.input-nsrp').each(function(){
                $(this).on('blur', function(){
                    if($(this).val().trim() != "") {
                        $(this).addClass('has-val');
                    }
                    else {
                        $(this).removeClass('has-val');
                    }
                })
            })
            $scope.$watch('nsrpUsername', function(){
                if($scope.nsrpUsername != '' && $scope.nsrpUsername){
                    $('#nsrp-username').addClass('has-val');
                }
            });
            $scope.$watch('nsrpPassword', function(){
                if($scope.nsrpPassword != '' && $scope.nsrpPassword){
                    $('#nsrp-password').addClass('has-val');
                }
            })


            /* Validate */
            var input = $('.validate-input .input-nsrp');
            $('.validate-form').on('submit',function(){
                var check = true;
                for(var i = 0; i < input.length; i++) {
                    if(validate(input[i]) == false){
                        showValidate(input[i]);
                        check = false;
                    }
                }
                return check;
            });


            $('.validate-form .input-nsrp').each(function(){
                $(this).focus(function(){
                    hideValidate(this);
                });
            });

            $(document).on('click','.validate-form .input-nsrp', function(){
                $('.wrap-error-message').hide();
            });
            function validate (input) {
                if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
                    if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                        return false;
                    }
                } else {
                    if($(input).val().trim() == ''){
                        return false;
                    }
                }
            }

            function showValidate(input) {
                var thisAlert = $(input).parent();
                $(thisAlert).addClass('alert-validate');
            }

            function hideValidate(input) {
                var thisAlert = $(input).parent();
                $(thisAlert).removeClass('alert-validate');
            }
        })(jQuery);
    })
}]);
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