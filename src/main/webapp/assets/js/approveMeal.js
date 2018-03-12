myApp.controller('approveMealCtrl', ['$scope', 'NgTableParams', 'ngTableEventsChannel', '$timeout', function($scope, NgTableParams, ngTableEventsChannel, $timeout) {
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
            StaffName : 'Nguyễn Văn Công',
            MealTimeId: 1,
            LocationId: 1,
            MealId: 1,
            Status: 0
        },
        {
            StaffId: '1000001',
            StaffName : 'Trần Đình Chiến',
            MealTimeId: 2,
            LocationId: 1,
            MealId: 2,
            Status: 0
        },
        {
            StaffId: '1000002',
            StaffName : 'Chu Trọng Khanh',
            MealTimeId: 1,
            LocationId: 1,
            MealId: 1,
            Status: 0
        }
    ];
    for(var i =3 ; i< 1000; i++){
        var length = (i.toString()).length,
            surffixNum = '';
        for(var j = 0; j <  3 - length; j++){
            surffixNum += '0';
        }

        $scope.arrData.push({
            StaffId: '1000'+surffixNum+i,
            StaffName : 'Customer '+(new Date()).getTime(),
            MealTimeId: i%3 == 1 ? 1 : 2,
            LocationId: i%3 == 1 ? 1 : 3,
            MealId: i%3 == 1 ? 1 : 2,
            Status: i%3 == 1 ? 1 : 0
        })
    }

    $scope.tableParams = new NgTableParams({
        sorting: {
            StaffId: "asc"
        },
        page: 1,
        count: 10
    }, {
        dataset: $scope.arrData,
    });
    $scope.dataFiltered = [];
    ngTableEventsChannel.onAfterReloadData(function(tableParams, filteredData){
        $scope.dataFiltered = tableParams.data || filteredData.data;
        console.log(tableParams);
    });
    $scope.statusMeal = false;
    $scope.changeStatus = function (statusMeal) {
        angular.forEach($scope.dataFiltered, function(item){
            if(statusMeal == true){
                item.Status = 1;
            } else if(statusMeal == false){
                item.Status = 0;
            }
        });
    };
    $scope.$watch('dataFiltered', function(){
        var countStatus = 0, countNumber = 0;
        angular.forEach($scope.dataFiltered, function(item){
            if(item.Status == 1){
                countStatus++;
            }
            countNumber++;
        });
        if(countNumber == countStatus && countNumber != 0){
            $scope.statusMeal = true;
        } else {
            $scope.statusMeal = false;
        }
    })
}]);