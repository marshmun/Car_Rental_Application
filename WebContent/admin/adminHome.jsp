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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title>Admin Home</title>
</head>

<body>
	<nav class="navbar navbar-default --primary">
		<div class="container">
			<div class="navbar--brand brand">
				<a href="#" class="brand__logo">
					<img src="../pictures/farmBureau.png" alt="Render logo">
				</a>
			</div>
			<div class="confirmation">
				<p>Logged in as :<%=user.getFirstName() %> <%=user.getLastName() %></p>
			</div>
		</div>
	</nav>
	<hr>
	<div class="options">
		<a href="./carrentaladmin.jsp">
			<button class="containers">
				<img src="../pictures/carlogo.png" alt="Plus">
				<a href="./carrentaladmin.jsp">Add, delete, update and rentout Cars</a>
			</button>
		</a>
		<a href="./adminUser.jsp">
			<button class="contianers">
				<img src="../pictures/Admin-logo.png" alt="Add Admin">
				<a href="./adminUser.jsp">Add, delete, upgrade, or update Users</a>
			</button>
		</a>
		<a href="./returnCar.jsp">
			<button class="containers">
				<img class="car" src="../pictures/carlogo.png" alt="Return a Car">
				<a href="./returnCar.jsp">Return a Car for Exisiting Customer</a>
			</button>
		</a>
	</div>
	<hr>
	<footer>
		<a href="adminHome.jsp" class="btn btn-primary btn-lg active" role="button">Home</a>
		<form action="logout" method="POST">
			<button type="submit" class="btn btn-primary btn-lg active">log out</button>
		</form>
	</footer>
</body>

</html>