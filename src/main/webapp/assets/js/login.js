var myApp = angular.module('loginApplication',[]).controller('loginController', ['$scope', '$http' , function($scope, $http){
    $scope.errorMessage = angular.element(document.getElementById("errorMess")).val();
    $scope.clickHideMessage = function(){
        $scope.errorMessage = null;
    }
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
                if(check == true){
                    var username = $('#nsrp-username').val();
                    var password = $('#nsrp-password').val();
                    $http({
                        method: 'POST',
                        url: 'authentication',
                        responseType: 'json',
                        headers: {
                            contentType: "application/json; charset=utf-8",
                            dataType: 'JSON'
                        },
                        data: {
                            'username': username,
                            'password': password
                        }
                    }).then(function successCallback(response) {

                    }, function errorCallback(response) {

                    })
                }
                return check;
            });


            $('.validate-form .input-nsrp').each(function(){
                $(this).focus(function(){
                    hideValidate(this);
                });
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
