<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<title>Error</title>
</head>
<body>
	<div class="container">
		<h1 style="color: red">${errorMsg}</h1>
		<a href="<c:url value='/'/>">Volver a Inicio</a>
	</div>
</body>
</html>
