<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Historial - appWeather</title>
<link rel="icon" href="static/img/icon.ico">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<link rel="stylesheet"
	href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/themes/flick/jquery-ui.css" />
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.8/angular.min.js"></script>
<style>
	.collapsible-header a {
  display: inline-block;
  text-align: center;
  margin-left: auto;
}
</style>
</head>
<body>
	
	<nav>
    <div class="nav-wrapper grey darken-4">
      <h4 class="brand-logo right">appWeather</h4>
      <ul id="nav-mobile" class="left hide-on-med-and-down">
        <li><a href="<c:url value='/' />">Consultar tiempo</a></li>
        <li class="active"><a href="#">Historial</a></li>
      </ul>
    </div>
  </nav>
	
	<div class="container" style="margin-top: 5%">
		
		<div class="row">
			<div class="col s5 offset-s4">
				<h4>Historial (${fn:length(consultas)} consultas)</h4>
				<ul class="collapsible popout" data-collapsible="accordion">
				<c:forEach items='${ consultas }' var="consulta" varStatus="each">
					<li>
						<div class="collapsible-header">${ consulta.ciudad }<a>${ each.index + 1 }</a></div>
						<div class="collapsible-body">
							<ul class="browser-default">
								<li><strong>Región: </strong>${ consulta.region }</li>
								<li><strong>País: </strong>${ consulta.pais }</li>
								<li><strong>Temperatura: </strong>${ consulta.temperatura } °C</li>
							</ul>
						</div>
					</li>
				</c:forEach>
			</ul>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$('.collapsible').collapsible();
		});
	</script>
</body>
</html>