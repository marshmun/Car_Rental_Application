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
<link rel="stylesheet" href="../css/adminCss/adminNav.css">
<link rel="stylesheet" href="../css/customerCss/customer.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
						<p>Logged in as :</p>
					</div>
				</div>
			</nav>
	<hr>
	<h1>Add remove or update a user or an Admin!</h1>
	<button type="button" class="btn btn-info btn-lg" name="update"
		data-toggle="modal" data-target="#adduserModal">Add a new
		User/Admin</button>
	<div id="adduserModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add all the user information</h4>
				</div>
				<div class="modal-body">

					<form action="adminadduser" method="POST">
						<input type="text" name="User_Name" placeholder="User Name"
							required> <input type="text" name="First_Name"
							placeholder="First Name" required> <input type="text"
							name="Last_Name" placeholder="Last Name" required> <input
							type="email" name="Email_Address" placeholder="Email Address"
							required> <input type="text" name="User_Type"
							placeholder="User Type" required> <input type="password"
							name="Password" placeholder="Password" required> <input
							id="submit" type="submit">
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	<h1>Add remove or update a user or an Admin!</h1>
	<button type="button" class="btn btn-info btn-lg" name="update"
		data-toggle="modal" data-target="#updateUserModal">Update a
		User/Admin</button>
	<div id="updateUserModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Update User Information</h4>
				</div>
				<div class="modal-body">

					<form action="adminupdate" method="POST">
						<input type="text" name="User_Name" placeholder="User Name"
							required> <input type="text" name="First_Name"
							placeholder="First Name"> <input type="text"
							name="Last_Name" placeholder="Last Name"> <input
							type="text" name="Email_Address" placeholder="Email Address">
						<input type="text" name="Type" placeholder="Type"> <input
							id="submit" type="submit">
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	<button type="button" class="btn btn-info btn-lg" name="update"
		data-toggle="modal" data-target="#deleteUserModal">Delete a
		User/Admin</button>
	<div id="deleteUserModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Delete user from Data-base</h4>
				</div>
				<div class="modal-body">

					<form action="admindeletecustomer" method="POST">
						<input type="text" name="User_Name"
							placeholder="User Name to Delete" required> <input
							id="submit" type="submit">
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
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