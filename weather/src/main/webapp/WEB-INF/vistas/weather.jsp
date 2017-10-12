<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html ng-app="weather">
<head>
<meta charset="utf-8">
<title>Tiempo - appWeather</title>
<link rel="icon" href="static/img/icon.ico">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.8/angular.min.js"></script>
<link rel="stylesheet"
	href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/themes/flick/jquery-ui.css" />
<link rel="stylesheet"
	href="static/styles/weather.css" />

</head>
<body ng-controller="weatherCtrl" ng-style="setBg(code, day)">

	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <a class="navbar-brand" href="#">appWeather</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Consultar tiempo <span class="sr-only">(current)</span></a></li>
        <li><a href="<c:url value='/historial' />">Historial&nbsp;<span ng-show="num > 0" class="badge">{{ num }}</span></a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <span class="navbar-text">Bienvenido, ${ sessionScope.username }</span>
        <li><a href="<c:url value='/logout' />">Cerrar sesión</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
	
	<div class="container" style="margin-top: 10%" id="weatherDiv">
		<div class="row">				
			<div class="well col-sm-4 col-sm-offset-4">
					<form class="form-inline" ng-submit="getWeather(search)"
						novalidate="novalidate" name="form_citydetails"
						id="form_citydetails">
						<div class="form-row">
							<div class="col inner-addon left-addon">
								<i class="glyphicon glyphicon-search"></i> <input type="text"
									name="search" class="form-control" style="margin-right:1%" value="" id="f_elem_city" ng-model="search" 
									maxlength="36" ng-minlength="3" placeholder="Busca una ciudad" required> <span
									class="error"
									ng-show="form_citydetails.search.$invalid && form_citydetails.search.$dirty ">Mínimo
									3 caracteres</span>
								<button type="submit" ng-disabled="form_citydetails.$invalid"
									class="btn btn-primary">Buscar</button>
							</div>
						</div>
					</form>
				</div>
		</div>

		<div class="row">
			<div class="well col-sm-6 col-sm-offset-3" ng-show="mostrar">
				<div class="row">
					<div class="col-sm-6">
						<i>Último update:</i> {{ weather.current.last_updated }}
					</div>
					<div class="col-sm-6 text-right">
						<i style="position: relative; bottom: 1.5em;">{{
							weather.current.condition.text }}</i> <img
							ng-src="{{ weather.current.condition.icon }}"></img>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6">
						<h4>{{ weather.location.name }}</h4>
						<ul>
							<li><strong>Región:</strong> {{ weather.location.region }}</li>
							<li><strong>País:</strong> {{ weather.location.country }}</li>
							<li><strong>Hora local:</strong> {{
								weather.location.localtime }}</li>
						</ul>
					</div>
					<div class="col-sm-6">
						<h4>Reporte</h4>
						<ul>
							<li><strong>Temperatura:</strong> {{ weather.current.temp_c
								}} °C</li>
							<li><strong>Vel. viento:</strong> {{
								weather.current.wind_kph }} km/h</li>
							<li ng-show="precipita"><strong>Precipitación:</strong> {{
								weather.current.precip_mm }} mm</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="alert alert-danger" ng-show="error">
				<strong>Error: </strong>{{ mensaje }}
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script type="text/javascript" src="static/scripts/weather-jQuery.js">	</script>
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	
	<script type="text/javascript" src="static/scripts/weather.js"></script>
	

	
</body>
</html>