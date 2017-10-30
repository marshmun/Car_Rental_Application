<%@ page import="com.rental.models.ErrorBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/main.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Error Page</title>
</head>
<body>
<nav class="navbar navbar-default --primary">
		<div class="container">
			<div class="navbar--brand brand">
				<a href="#" class="brand__logo">
							<img src="./pictures/farmBureau.png" alt="Render logo">
						</a>
			</div>
			<form action="login" class="form-inline" method="POST">
				<div class="form-group">
					<label for="User_Name">User Name:</label>
					<input type="text" class="form-control" name="User_Name" id="User_Name" placeholder="Enter User Name">
				</div>
				<div class="form-group">
					<label for="password">Password:</label>
					<input type="password" class="form-control" id="password" name="password" >
				</div>
				<div class="checkbox">
					<label><input type="checkbox"> Remember me</label>
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
	</nav>

<h3>Please send the error seen below to an IT Specialist</h3>
<%
ErrorBean errorbean = (ErrorBean)request.getAttribute("errorbean");
out.println(errorbean.getError());

%>

</body>
</html>