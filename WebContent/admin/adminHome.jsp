<%@ page import="com.rental.models.User"%>
<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
		user = new User();
	}
	if (!"Admin".equalsIgnoreCase(user.getType())) {
		response.sendRedirect("../noccsess.jsp");
	}
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="../css/adminCss/admin.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Admin Home</title>
</head>

<body>
	<h1>Welcome to your home please choose from the selected what you
		would like to do.</h1>
	<hr>
	<div class="options">
		<div>
			<img src="../pictures/carlogo.png" alt="Plus"> <a
				href="./carrentaladmin.jsp">Add, delete, update and rentout Cars</a>
		</div>
		<div>
			<img src="../pictures/Admin-logo.png" alt="Add Admin"> <a
				href="./adminUser.jsp">Add, delete, upgrade, or update Users</a>
		</div>
		<div>
			<img class="car" src="../pictures/carlogo.png" alt="Return a Car">
			<a href="./returnCar.jsp">Return a Car for Exisiting Customer</a>
		</div>
	</div>
	<hr>
	<footer>
		<a href="adminHome.jsp">Home</a>
		<form action="logout" method="POST">
			<button type="submit" value="Logout">log out</button>
		</form>
	</footer>
</body>

</html>