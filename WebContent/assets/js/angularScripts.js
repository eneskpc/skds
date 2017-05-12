var homeModule = angular.module("homeModule", []);

homeModule.controller("homeController", function($scope, $http) {
	
	$http.post('/app/getRequestList', {"test" : "test"}).then(
			function(resp) {
				$scope.requestList = resp.data;
				console.log(resp.data);
			}, 
			function(resp) {
				console.log("error");
			});
	
	
});