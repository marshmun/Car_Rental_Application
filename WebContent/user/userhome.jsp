<%@ page import="com.rental.models.User"%>
<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
		user = new User();
	}
	if (!"User".equalsIgnoreCase(user.getType())) {
		response.sendRedirect("../noccsess.jsp");
	}
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="../css/userCss/userHome.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Document</title>
</head>

<body>
	<h1>Welcome home</h1>
	<div class="container">
		<img src="../pictures/profile.jpg" alt="Profile"> <a
			href="./update.jsp">Update Profile</a>
	</div>
	<div class="container">
		<img src="../pictures/carlogo.png" alt="Car"> <a
			href="./carRental.jsp">See Cars available for rental</a>
	</div>

	<div>
		<div class="container">
			<img src="../pictures/logout.jpg" alt="Log Out">
			<form action="logout" method="POST">
				<button type="submit" value="Logout">log out</button>
			</form>
		</div>
	</div>
	<hr>
	<footer>
		<a href="userhome.jsp">Home</a> <a href="FAQ.jsp">FAQ</a>
		<form action="logout" method="POST">
			<button type="submit" value="Logout">log out</button>
		</form>
	</footer>


</body>
</html>