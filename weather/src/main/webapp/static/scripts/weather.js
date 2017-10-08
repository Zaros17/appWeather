var weather = angular.module("weather", []);

weather.controller("weatherCtrl", ["$scope","$http", function($scope, $http) {
	
	$scope.weather = undefined;
	
	$scope.getWeather = function(search) {
		
		$scope.error = false;
		$scope.mostrar = false;
			
		var url = "https://api.apixu.com/v1/current.json?key=a886585368c04490a34101741170610&q=" + search;
		
		$http.get(url).success(function(data) {
				
			$scope.weather = data;
			$scope.mostrar = true;
				
			var dataObj = {
					"ciudad": $scope.weather.location.name,
					"region": $scope.weather.location.region,
					"pais": $scope.weather.location.country,
					"temperatura": $scope.weather.current.temp_c
			};
				
			var response = $http.post('weather', dataObj);
				
			response.error(function(data, status, headers, config) {
				alert( "Exception details: " + console.log(JSON.stringify({data:status})));
			});
				
		}).error(function() {
			$scope.error = true;
			$scope.mensaje = "No existe ninguna ciudad con ese nombre.";
		});

	}

	}]);
