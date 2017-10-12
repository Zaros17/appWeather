<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html ng-app="registro">
<head>
<meta charset="utf-8">
<title>Registro - appWeather</title>
<link rel="icon" href="static/img/icon.ico">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<link rel="stylesheet"
	href="static/styles/errores.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular.min.js"></script>
	
<style>
.select-dropdown{
    max-height: 20rem;
}
</style>
</head>
<body ng-controller="registroCtrl">
	<div class="container" style="margin-top: 5%">
		<div class="row">
			<div class="col s4 offset-s4 z-depth-1 ">
				<form:form name="datos" method="post" modelAttribute="usuario"
					action="" novalidate="novalidate" >
					<div class="row" style="padding: 3%">
						<div class="input-field">
							<strong>Nombre de usuario</strong>
							<form:input type="text" ng-model="username" path="username"
								 ng-minlength="3" maxlength="14" required="required" ng-pattern="/^[A-Za-z0-9^_-]{3,14}$/" />
						     <span class="error" ng-show="datos.username.$invalid && datos.username.$dirty ">Nombre de usuario inválido</span>
						     <form:errors path="username" cssClass="error" />
						</div>
					</div>
					<div class="row" style="padding: 3%">
						<div class="input-field">
							<strong>Contraseña</strong>
							<form:input type="password" ng-model="password"
								path="password" maxlength="14" required="required" ng-pattern="/^[A-Za-z0-9^_-]{3,14}$/"/>
							<span class="error" ng-show="!datos.password.$valid && datos.password.$dirty">Contraseña inválida</span>
							<form:errors path="password" cssClass="error" />
							
						</div>
						<div class="input-field">
							<strong>Confirma tu contraseña</strong>
							<input type="password" ng-disabled="!datos.password.$dirty" name="passwordre" ng-model="passwordre" maxlength="14" required="required" password-confirm="password" />	
							<span class="error" ng-show="!datos.passwordre.$valid && datos.passwordre.$dirty">Las contraseñas no coinciden</span>
								
						</div>
					</div>
					<div class="row" style="padding: 3%">
						<div class="input-field">
							<strong>Correo electrónico</strong>
							<form:input type="text" ng-model="email" path="email"
								 ng-minlength="3" maxlength="25" required="required"
								 ng-pattern="/^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/" />
							<span class="error" ng-show="!datos.email.$valid && datos.email.$dirty">Email no válido</span>
							<form:errors path="email" cssClass="error" />
						</div>
					</div>
					<div class="row" style="padding: 3%">
						<div class="input-field">	
							<strong>Fecha de nacimiento</strong>	<br/>		
							<input type="text" placeholder="Día" name="dia" ng-model="dia" style="margin-right:3%" 
								 size="6" required="required" class="col s2" maxlength="2" ng-pattern="/^[0-9]{2}$/"/>
							<select class="col s5" name="mes">
								<option value="Enero">Enero</option>
								<option value="Febrero">Febrero</option>
								<option value="Marzo">Marzo</option>
								<option value="Abril">Abril</option>
								<option value="Mayo">Mayo</option>
								<option value="Junio">Junio</option>
								<option value="Julio">Julio</option>
								<option value="Agosto">Agosto</option>
								<option value="Septiembre">Septiembre</option>
								<option value="Octubre">Octubre</option>
								<option value="Noviembre">Noviembre</option>
								<option value="Diciembre">Diciembre</option>
							</select>
							<input type="text" name="year" class="col s3" placeholder="Año" style="margin-left:4%" ng-model="year"
								  size="6" required="required" maxlength="4" ng-pattern="/^[0-9]{4}$/" />  <br/> <br/> <br/>	 
							<div>
								<span class="error" style="margin-right:3%"ng-show="!datos.dia.$valid && datos.dia.$dirty">Día inválido</span>  	
								<span class="error" ng-show="!datos.year.$valid && datos.year.$dirty">Año inválido</span>
							</div>
							<c:if test = "${requestScope.error != ''}">
         						<span class="error">${ requestScope.error }</span>
      						</c:if>
							
						</div>
						
					</div>
					
					<div class="row center-align" style="padding: 3%">						
						<button class="btn waves-effect waves-light light-blue"
							type="submit" ng-disabled="datos.$invalid" >Sign up</button>
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
	</script>
	<script type="text/javascript" src="static/scripts/registro.js">
	</script>
</body>
</html>