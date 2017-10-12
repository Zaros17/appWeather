/*
 * Script de angular que consiste en un controlador que realiza una petición POST
 * al servicio web rest Apixu para consultar el tiempo de una determinada ciudad
 */

var weather = angular.module("weather", []);

weather.controller("weatherCtrl", ["$scope","$http", function($scope, $http) {
	
	$scope.weather = undefined;
	$scope.num = 0;
	
	$scope.getWeather = function(search) {
		
		$scope.error = false;
		$scope.mostrar = false;
		$scope.precipita = false;
			
		var url = "http://api.apixu.com/v1/current.json?key=c80ccea431444bcd8df141532171210&q=" + search + "&lang=es";
		
		$http.get(url).success(function(data) {
				
			$scope.weather = data;
			if($scope.weather.current.precip_mm !== 0) {
				$scope.precipita = true;
			}
			
			$scope.code = $scope.weather.current.condition.code;
			$scope.day = $scope.weather.current.is_day;
			$scope.mostrar = true;
				
			var dataObj = {
					"ciudad": $scope.weather.location.name,
					"region": $scope.weather.location.region,
					"pais": $scope.weather.location.country,
					"temperatura": $scope.weather.current.temp_c
			};
				
			var response = $http.post('weather', dataObj);
			
			response.success(function (data, status, headers, config) {
				$scope.num++;
            });
			response.error(function(data, status, headers, config) {
				alert("Ha habido un error.\nVuelva a intentarlo");
			});
				
		}).error(function() {
			$scope.error = true;
			$scope.mensaje = "No existe ninguna ciudad con ese nombre.";
		});
		
		/*
		 * Función que se encarga de cambiar el background dependiendo de tipo de tiempo que haya
		 */
		$scope.setBg = function(code, day) {
			
			// Despejado 
			if(code == 1000) {
				if(day) {
					return {'background-image':'url(static/img/day/clear.jpg)'};
				} else
					return {'background-image':'url(static/img/night/clearNight.jpg)'};
			}
			
			// Lluvia
			if(code >= 1183 && code <= 1198 || code == 1201 || code >= 1240 && code <= 1246) {
				if(day) {
					return {'background-image':'url(static/img/day/rain.jpg)'};
				} else
					return {'background-image':'url(static/img/night/rainNight.jpg)'};
			}
			
			// Nublado
			if(code >= 1003 && code <= 1009 || code >= 1063 && code <= 1072 || code >= 1150 && code <= 1153 || 
					code >= 1168 && code <= 1171 || code == 1180 || code >= 1249 && code <= 1258) {
				if(day) {
					return {'background-image':'url(static/img/day/cloud.jpg)'};
				} else
					return {'background-image':'url(static/img/night/cloudNight.jpg)'};
			}
			
			// Tormenta
			if(code >= 1273 && code <= 1282 || code == 1087) {
				if(day) {
					return {'background-image':'url(static/img/day/storm.jpg)'};
				} else
					return {'background-image':'url(static/img/night/stormNight.jpg)'};
			}
			
			// Nieve 
			if(code >= 1114 && code <= 1117 || code >= 1210 && code <= 1225) {
				if(day) {
					return {'background-image':'url(static/img/day/snow.jpg)'};
				} else
					return {'background-image':'url(static/img/night/snowNight.jpg)'};
			}
			
			// Niebla
			if(code >= 1135 && code <= 1147 || code == 1030) {
				if(day) {
					return {'background-image':'url(static/img/day/fog.jpg)'};
				} else
					return {'background-image':'url(static/img/night/fogNight.jpg)'};
			}
			
			// Granizo
			if(code >= 1261 && code <= 1264 || code == 1237) {
				return {'background-image':'url(static/img/day/icepellet.jpg)'};
			}
			
			// Aguanieve
			if(code >= 1204 && code <= 1207) {
				if(day) {
					return {'background-image':'url(static/img/day/sleet.jpg)'};
				} else
					return {'background-image':'url(static/img/night/sleetNight.jpg)'};
			}

		}

	}

	}]);
