/*
 * Script de angular que consiste en una directiva personalizada para
 * validar el campo de "Repetir contraseña" del registro
 */
var app = angular.module('registro', []);
	app.directive("passwordConfirm", function() {
	    "use strict";
	    return {
	        require: "ngModel",
	        restrict: "A",
	        scope: {
	            //We will be checking that our input is equals to this expression
	            passwordConfirm: '&'
	        },
	        link: function(scope, element, attrs, ctrl) {
	            //The actual validation
	            function passwordConfirmValidator(modelValue, viewValue) {
	                return modelValue == scope.passwordConfirm();
	            }
	            //Register the validaton when this input changes
	            ctrl.$validators.passwordConfirm = passwordConfirmValidator;
	            //Also validate when the expression changes
	            scope.$watch(scope.passwordConfirm, ctrl.$validate);
	        }
	    };
	});
	
	app.controller("registroCtrl", ["$scope", function($scope) {
		
	}]);