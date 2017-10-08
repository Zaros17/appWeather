<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html ng-app="weather">
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.8/angular.min.js"></script>
<link rel="stylesheet" href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/themes/flick/jquery-ui.css" />
<style type="text/css">
.ui-menu .ui-menu-item a,.ui-menu .ui-menu-item a.ui-state-hover, .ui-menu .ui-menu-item a.ui-state-active {
	font-weight: normal;
	margin: -1px;
	text-align:left;
	font-size:14px;
	}
	
	input.ng-invalid.ng-dirty {
	background: #ffebee;
}

.error {
	color: #e57373;
}
</style>
</head>
<body>
	<div class="container" style="margin-top: 10%" ng-controller="weatherCtrl">
	<h2>Bienvenido, ${ sessionScope.username }</h2>
		<div class="row">
			<div class="col s3 offset-s4 z-depth-1 ">
				<form ng-submit="getWeather(search)"  novalidate="novalidate" name="form_citydetails" id="form_citydetails" enctype="multipart/form-data">
					<div class="row" style="padding: 3%" >
						<input type="text" name="search" value="" id="f_elem_city" ng-model="search" maxlength="36" ng-minlength="3" required />
						<span class="error" ng-show="form_citydetails.search.$invalid && form_citydetails.search.$dirty ">Mínimo 3 caracteres</span>
						<div class="row center align" style="padding: 3%">
							<button class="btn waves-effect waves-light light-blue center-align"
								type="submit" ng-disabled="form_citydetails.$invalid">Buscar</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<div class="row">
			<div ng-show="mostrar">
			<h4>El tiempo</h4>
				<ul>
					<li><strong>Ciudad:</strong> {{ weather.location.name }}</li>
					<li><strong>Región:</strong> {{ weather.location.region }}</li>
					<li><strong>País:</strong> {{ weather.location.country }}</li>
					<li><strong>Temperatura:</strong> {{ weather.current.temp_c }} °C</li>
				</ul>
		</div>
		<div ng-show="error">
			<h4>{{ mensaje }}</h4>
		</div>
		</div>
	</div>

	
	<script type="text/javascript" src="static/scripts/weather.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js" type="text/javascript"></script>
	
 	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	
	<script type="text/javascript">
	
		jQuery(function () 
			 {
				 jQuery("#f_elem_city").autocomplete({
					source: function (request, response) {
					 jQuery.getJSON(
						"http://gd.geobytes.com/AutoCompleteCity?callback=?&q="+request.term,
						function (data) {
						 response(data);
						}
					 );
					},
					minLength: 3,
					select: function (event, ui) {
					 var selectedObj = ui.item;
					 jQuery("#f_elem_city").val(selectedObj.value);
					 return false;
					},
					open: function () {
					 jQuery(this).removeClass("ui-corner-all").addClass("ui-corner-top");
					},
					close: function () {
					 jQuery(this).removeClass("ui-corner-top").addClass("ui-corner-all");
					}
				 });
				 jQuery("#f_elem_city").autocomplete("option", "delay", 100);
				});
	</script>
</body>
</html>