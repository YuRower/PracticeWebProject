var app = angular.module("AngularTestApp", []);

app.controller("SampleAppCtrl", function ($scope) {

    $scope.tableView = "ajaxFile/ajaxTable.jsp";

    $scope.listView = "ajaxFile/listUser.jsp";

    $scope.url = $scope.tableView;

    $scope.showList = function () {
        $scope.url = $scope.listView;
    }

    $scope.showTable = function () {
        $scope.url = $scope.tableView;
    }
});