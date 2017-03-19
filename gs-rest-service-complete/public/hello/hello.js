angular.module('demo', [])
.controller('Hello', function($scope, $http) {
    $http.get('http://localhost:8080/customers/Alice').
        then(function(response) {
            $scope.customer = response.data;
        });
});
