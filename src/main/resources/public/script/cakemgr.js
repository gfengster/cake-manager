/**
 * author gfeng
 */


var app = angular.module('myApp', []);

app.controller('tableCtrl', function($scope, $http) {
    $http.get("/cakes")
    .then(function (response) {$scope.cakes = response.data;});
});

app.controller('formCtrl', function($scope, $http) {
    $scope.save = function() {
		$scope.master = {title: "", desc: "", image: ""};
        console.log($scope.cake);
        $http.post('/cakes', $scope.cake, 
				{'headers':{'Content-Type': 'application/json'}})
				.then(function(response){
					if (response.status == 200){
						$scope.result = response.data.id + " saved";
					} else {
						$scope.result = "";
					}
                console.log(response)});
		
		$scope.reset();
	};
	
	$scope.reset = function() {
        $scope.cake = angular.copy($scope.master);
    };
	
	$scope.save();
});