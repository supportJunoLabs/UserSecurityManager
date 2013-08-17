<!DOCTYPE html>

<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title>User Securiry Manager</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
	</head>

	<body>
		<div id="headerContainer">
			<%@include file="../includes/header.jsp" %>
		</div>
	
		<div class="mainContainer">
			<h1>Titulo</h1>
			<% String view = "../views/" + ((String)(request.getAttribute("controller"))) + "/" + (String)request.getAttribute("action") + ".jsp"; %>
			<jsp:include page="<%= view %>"/>
		</div>
		
		<div id="footerContainer">
			<%@include file="../includes/footer.jsp" %>
		</div>
	</body>
</html>