<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html ng-app="formulario">
<head>
<meta charset="utf-8">
<title>Login - appWeather</title>
<link rel="icon" href="static/img/icon.ico">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<link rel="stylesheet"
	href="static/styles/errores.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular.min.js"></script>

</head>
<body>
	<div class="container" style="margin-top: 10%">
		<div class="row">
			<div class="col s4 offset-s4 z-depth-1 ">
				<form:form name="datos" method="post" modelAttribute="usuario"
					 novalidate="novalidate">
					<div class="row" style="padding: 3%">
						<div class="input-field">
							<form:input type="text" ng-model="username" path="username"
								 ng-minlength="3" maxlength="14" required="required" />
							<label>Username (admin)</label>
							<span class="error" ng-show="datos.username.$invalid && datos.username.$dirty ">Mínimo 3 caracteres</span>
						</div>
					</div>
					<div class="row" style="padding: 3%">
						<div class="input-field">
							<form:input type="password" ng-model="password"
								path="password" ng-minlength="3" maxlength="14" required="required"/>
							<label>Password (admin)</label>
							<span class="error" ng-show="!datos.password.$valid && datos.password.$dirty">Mínimo 3 caracteres</span>
						</div>
					</div>
					<div class="row center-align" style="padding: 3%" >
						<button class="btn waves-effect waves-light light-blue" style="margin-right:2%"
							type="submit" ng-disabled="datos.$invalid">Login</button>
						<span>¿No tienes una cuenta? Regístrate <a href="<c:url value='/signup' />">aquí</a></span>
					</div>
					
				</form:form>
			</div>
			
		</div>
	</div>

	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<script type="text/javascript">
		angular.module("formulario", []);	
	</script>
	<script type="text/javascript">
	var $toastContent = $('<span>Usuario o contraseña incorrecta</span>').add($('<button class="btn-flat toast-action">Desliza</button>'));
	
	if( ${error} ) {
		Materialize.toast($toastContent , 10000);
	}
	</script>
</body>
</html>