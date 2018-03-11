// DEFINED FOR NSRP APPLICATION
var myApp = angular.module('NsrpApplication', ['ngTable','tw.directives.clickOutside']);
myApp.controller('mealManagermentCtrl', ['$scope', 'NgTableParams', function($scope, NgTableParams){
	var arrData = [{id: 1, name: "Moroni", age: 50, money: -10},
        {id: 2, name: "Tiancum", age: 43,money: 120},
        {id: 3, name: "Jacob", age: 27, money: 5.5},
        {id: 4, name: "Nephi", age: 29,money: -54},
        {id: 5, name: "Enos", age: 34,money: 110},
        {id: 6, name: "Tiancum", age: 43, money: 1000},
        {id: 7, name: "Jacob", age: 27,money: -201},
        {id: 8, name: "Nephi", age: 29, money: 100},
        {id: 9, name: "Enos", age: 34, money: -52.5},
        {id: 10, name: "Tiancum", age: 43, money: 52.1},
        {id: 11, name: "Jacob", age: 27, money: 110},
        {id: 12, name: "Nephi", age: 29, money: -55},
        {id: 13, name: "Enos", age: 34, money: 551},
        {id: 14, name: "Tiancum", age: 43, money: -1410},
        {id: 15, name: "Jacob", age: 27, money: 410},
        {id: 16, name: "Nephi", age: 29, money: 100},
        {id: 17, name: "Enos", age: 34, money: -100},
        {id: 18, name: "Enos", age: 34, money: -100},
        {id: 19, name: "Enos", age: 34, money: -100},
        {id: 20, name: "Enos", age: 34, money: -100},
        {id: 21, name: "Enos", age: 34, money: -100},
        {id: 22, name: "Enos", age: 34, money: -100},
        {id: 23, name: "Enos", age: 34, money: -100},
        {id: 24, name: "Enos", age: 34, money: -100},
        {id: 25, name: "Enos", age: 34, money: -100},
        {id: 26, name: "Enos", age: 34, money: -100},
        {id: 27, name: "Enos", age: 34, money: -100},
        {id: 28, name: "Enos", age: 34, money: -100},
        {id: 29, name: "Enos", age: 34, money: -100},
        {id: 30, name: "Enos", age: 34, money: -100}];
	this.tableParams = new NgTableParams({
      sorting: { name: "asc" } 
    }, {
      dataset: arrData
    });
    $scope.statusMeal = { 'checked': false, items: {} };

    // watch for check all checkbox
    $scope.$watch('statusMeal.checked', function(value) {
        angular.forEach($scope.orderedData, function(item) {
            if (angular.isDefined(item.id)) {
                $scope.checkboxes.items[item.id] = value;
            }
        });
    });

    // watch for data checkboxes
    $scope.$watch('checkboxes.items', function(values) {
        if (!$scope.users) {
            return;
        }
        var checked = 0, unchecked = 0,
            total = $scope.users.length;
        angular.forEach($scope.users, function(item) {
            checked   +=  ($scope.checkboxes.items[item.id]) || 0;
            unchecked += (!$scope.checkboxes.items[item.id]) || 0;
        });
        if ((unchecked == 0) || (checked == 0)) {
            $scope.checkboxes.checked = (checked == total);
        }
        // grayed checkbox
        angular.element(document.getElementById("select_all")).prop("indeterminate", (checked != 0 && unchecked != 0));
    }, true);
}]);