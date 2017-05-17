var homeModule = angular.module("homeModule", []);

homeModule.controller("homeController", function($scope, $http) {
	
	$http.post('/app/getRequestList?userType=home').then(
			function(resp) {
				$scope.requestList = resp.data;
				console.log(resp.data);
			}, 
			function(resp) {
				console.log("error");
			});
	
	
});

var customerModule = angular.module("customerModule", []);

customerModule.controller("customerController", function($scope, $http) {
	
	$http.post('/app/getRequestList?userType=customer').then(
			function(resp) {
				$scope.requestList = resp.data;
				console.log(resp.data);
			}, 
			function(resp) {
				console.log("error");
			});
	
});

var companyModule = angular.module("companyModule", []);

companyModule.controller("companyController", function($scope, $http) {
	$http.post('/app/getCompanyRequestList').then(
			function(resp) {
				$scope.data = resp.data;
			}, 
			function(resp) {
				console.log("error");
			});
	
});

var personalModule = angular.module("personalModule", []);

personalModule.controller("personalController", function($scope, $http) {
	$http.post('/app/getStaffRequestList').then(
			function(resp) {
				$scope.data = resp.data;
				console.log($scope.data);
			}, 
			function(resp) {
				console.log("error");
			});
	
});