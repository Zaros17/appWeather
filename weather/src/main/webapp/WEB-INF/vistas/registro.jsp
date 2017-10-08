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

.select-dropdown{
    max-height: 20rem;
}
</style>
</head>
<body>
	<div class="container" style="margin-top: 5%">
		<div class="row">
			<div class="col s4 offset-s4 z-depth-1 ">
				<form:form name="datos" method="post" modelAttribute="usuario"
					action="login" novalidate="novalidate">
					<div class="row" style="padding: 3%">
						<div class="input-field">
							<strong>Nombre de usuario</strong>
							<form:input type="text" ng-model="username" path="username"
								 ng-minlength="3" maxlength="14" required="required" />
						</div>
					</div>
					<div class="row" style="padding: 3%">
						<div class="input-field">
							<strong>Contraseña</strong>
							<form:input type="password" ng-model="password"
								path="password" ng-minlength="3" maxlength="14" required="required"/>
							
						</div>
						<div class="input-field">
							<strong>Confirma tu contraseña</strong>
							<input type="password" ng-model="passwordre" ng-minlength="3" maxlength="14" required="required"/>			
						</div>
					</div>
					<div class="row" style="padding: 3%">
						<div class="input-field">
							<strong>Correo electrónico</strong>
							<form:input type="text" ng-model="email" path="email"
								 ng-minlength="3" maxlength="14" required="required" />
						</div>
					</div>
					<div class="row" style="padding: 3%">
						<div class="input-field">	
							<strong>Fecha de nacimiento</strong>	<br/>		
							<input type="text" placeholder="Día" style="margin-right:3%"
								 size="6" required="required" class="col s2" maxlength="2"/>
							<select class="col s5">
								<option value="" disabled selected>Mes</option>
								<option>Enero</option>
								<option>Febrero</option>
								<option>Marzo</option>
								<option>Abril</option>
								<option>Mayo</option>
								<option>Junio</option>
								<option>Julio</option>
								<option>Agosto</option>
								<option>Septiembre</option>
								<option>Octubre</option>
								<option>Noviembre</option>
								<option>Diciembre</option>
							</select>
							<input type="text"  class="col s3" placeholder="Año" style="margin-left:4%"
								  size="6" required="required" maxlength="4"/>
							
						</div>
					</div>
					<div class="row center-align" style="padding: 3%">
						<button class="btn waves-effect waves-light light-blue"
							type="submit" ng-disabled="datos.$invalid">Sign up</button>
					</div>
					
				</form:form>
			</div>
			
		</div>
	</div>

	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
	    	$('select').material_select();
	  	});
		// API fluida de escritura
		angular.module("formulario", []);
		
		
	</script>
	<script type="text/javascript">
	var $toastContent = $('<span>Usuario o contraseña incorrecta</span>').add($('<button class="btn-flat toast-action">Desliza</button>'));
	
	/*if( ${error} ) {
		Materialize.toast($toastContent , 10000);
	}*/
	</script>
</body>
</html>