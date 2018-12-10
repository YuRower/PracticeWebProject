var app = angular.module("AngularTestApp", []);

app.controller("viewTableCtrl", function($scope) {
	$scope.tableView = "ajaxFile/ajaxTable.jsp";
	$scope.listView = "ajaxFile/listUser.jsp";
	$scope.url = $scope.tableView;
	$scope.showList = function() {
		$scope.url = $scope.listView;
	}
	$scope.showTable = function() {
		$scope.url = $scope.tableView;
	}
});

var model = "Welcome To My WEBSITE";
app.controller("ChangeCtrl", function($scope) {
	$scope.message = model;
	$scope.clickHandler = function() {
		$scope.message = $scope.text;
	}
});

app.controller("validateCtrl", function($scope) {
	$scope.message = "VALIDATION";

	 $scope.addNewUser = function (userDetails, isvalid) {
         if (isvalid) {
             $scope.message = userDetails.name + " " + userDetails.email;
         }
         else {
             $scope.message = "Error";
         }
     }
	 
	$scope.getError = function(error) {
		if (angular.isDefined(error)) {
			if (error.required) {
				return "Поле не должно быть пустым";
			} else if (error.email) {
				return "Введите праивльный email";

			}
		}
	}
});

app.controller("ajaxCtrl", function ($scope, $http) {
    $scope.sendRequest = function () {
       /* $http.get("ajaxFile/ajaxTable.jsp").success(function (response) {
            $scope.items = response;
        })*/
    	$http({method:'GET', url: 'http://localhost:8080/PracticeWebProject/CurrencyPBRate'}).
        then(function success(response) {
                $scope.items=response.data;
        }, function error(response){
                console.log("Возникла ошибка");
        }
    );
    }
});
