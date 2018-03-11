myApp.controller('mealManagermentCtrl', ['$scope', 'NgTableParams', '$filter', '$timeout', function($scope, NgTableParams, $filter, $timeout){
    $scope.demoCheckbox = 1;
    $scope.locationArr = [
        {
            LocationId: 1,
            LocationName: 'Hanoi City'
        },
        {
            LocationId: 2,
            LocationName: 'HCM City'
        },
        {
            LocationId: 3,
            LocationName: 'NghiSon - Thanhhoa'
        }
    ]
    $scope.mealTimeArr = [
        {
            MealTimeId: 1,
            MealTimeName: 'Breakfast'
        },
        {
            MealTimeId: 2,
            MealTimeName: 'Lunch'
        },
        {
            MealTimeId: 3,
            MealTimeName: 'Diner'
        }
    ]
    $scope.mealArr = [
        {
            MealId: 1,
            MealName: 'Vietnam'
        },
        {
            MealId: 2,
            MealName: 'Japan'
        },
        {
            MealId: 3,
            MealName: 'Halala'
        }
    ]
    $scope.arrData = [
        {
            StaffId: '1000000',
            StaffName : 'Nguyen Van Cong',
            MealTimeId: 1,
            LocationId: 1,
            MealId: 1,
            Status: 0
        },
        {
            StaffId: '1000001',
            StaffName : 'Tran Dinh Chien',
            MealTimeId: 2,
            LocationId: 1,
            MealId: 2,
            Status: 0
        },
        {
            StaffId: '1000002',
            StaffName : 'Chu Trong Khanh',
            MealTimeId: 1,
            LocationId: 1,
            MealId: 1,
            Status: 0
        }
    ];
    $scope.tableParams = new NgTableParams({
        sorting: {
            StaffId: "asc"
        },
        page: 1,
        count: 1
    }, {
        total: 0,
        getData: function ($defer, params) {
            $scope.data = params.sorting() ? $filter('orderBy')($scope.arrData, params.orderBy()) : $scope.arrData;
            $scope.data = params.filter() ? $filter('filter')($scope.data, params.filter()) : $scope.data;
            $scope.data = $scope.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
            $defer.resolve($scope.data);
        }
    });
    $scope.statusMeal = false;
    $scope.$watch(function(){
        return $scope.statusMeal;
    }, function (value) {
        angular.forEach($scope.arrData, function(item){
            item.Status = value;
        });
    });
}]);