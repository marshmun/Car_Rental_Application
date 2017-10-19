<%@ page import="com.rental.models.User"%>
<%@ page import="com.rental.models.ConfirmationBean" %>
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
<link rel="stylesheet" href="../css/adminCss/adminNav.css">
<link rel="stylesheet" href="../css/rentalsCss/rental.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"
	type="text/javascript"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
<title>Document</title>
</head>

<body>
		<nav class="navbar navbar-default --primary">
				<div class="container">
					<div class="navbar--brand brand">
						<a href="#" class="brand__logo">
							<img src="../pictures/farmBureau.png" alt="Render logo">
						</a>
					</div>
					<ul class="nav">
							<li><a href="./adminHome.jsp"><button class="btn btn-outline-success" type="button">Home</button></a></li>
							<li><a href="./carrentaladmin.jsp"><button class="btn btn-outline-success" type="button">Car</button></a></li>
							<li><a href="./adminUser.jsp"><button class="btn btn-outline-success" type="button">Users</button></a></li>
							<li id="right"><a href="./returnCar.jsp"><button class="btn btn-outline-success" type="button">Return a rental</button></a></li>
					</ul>
					<div class="confirmation">
						<p>Logged in as :<%=user.getFirstName() %> <%=user.getLastName() %></p>
					</div>
				</div>
			</nav>

	<hr>
	<h1>Return Customers Rental</h1>
	<h4 class="sucsess">
	<%  ConfirmationBean confirmationBean = (ConfirmationBean)request.getAttribute("confirmation");
		if(confirmationBean != null){
		if( !confirmationBean.getConfirmation().equals("") || confirmationBean.getConfirmation() != null){
			out.println(confirmationBean.getConfirmation());
		}
		}
	%>
	</h4>
	<h2>Please enter the customers Email</h2>
	<div class="form">
		<form action="returnacar" method="POST">
			<input type="text" name="User_Name" placeholder="User Name" required>
			<input id="submit" type="submit">
		</form>

	</div>
	<hr>
	<footer>
		<a href="adminHome.jsp" class="btn btn-primary btn-lg active"
			role="button">Home</a>
		<form action="logout" method="POST">
			<button type="submit" class="btn btn-primary btn-lg active" >log out</button>
		</form>
	</footer>
</body>

</html>