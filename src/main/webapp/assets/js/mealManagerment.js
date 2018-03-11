myApp.controller('mealManagermentCtrl', ['$scope', 'NgTableParams', function($scope, NgTableParams){
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
    this.tableParams = new NgTableParams({
        sorting: { name: "asc" }
    }, {
        dataset: $scope.arrData
    });
    $scope.statusMeal = { 'checked': false, items: {} };
}]);