<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html ng-app="formulario">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular.min.js"></script>
<style>
input.ng-invalid.ng-touched {
	background: #ffebee;
}

.error {
	color: #e57373;
}
</style>
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
							<span class="error" ng-show="datos.username.$invalid && datos.username.$touched ">Mínimo 3 caracteres</span>
						</div>
					</div>
					<div class="row" style="padding: 3%">
						<div class="input-field">
							<form:input type="password" ng-model="password"
								path="password" ng-minlength="3" maxlength="14" required="required"/>
							<label>Password (admin)</label>
							<span class="error" ng-show="!datos.password.$valid && datos.password.$touched">Mínimo 3 caracteres</span>
						</div>
					</div>
					<div class="row center-align" style="padding: 3%" >
						<button class="btn waves-effect waves-light light-blue"
							type="submit" ng-disabled="datos.$invalid">Login</button>
					</div>
					
				</form:form>
			</div>
			
		</div>
	</div>

	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<script type="text/javascript">
		// API fluida de escritura
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