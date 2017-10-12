<%@page import="java.util.List"%>
<%@ page import="com.rental.models.User"%>
<%@ page import="com.rental.models.Table"%>
<%@ page import="com.rental.models.Car"%>
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
<link rel="stylesheet" href="../css/carCss/car.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>

<title>Document</title>
</head>

<body>
	<nav class="navbar navbar-default --primary">
		<div class="container">
			<div class="navbar--brand brand">
				<a href="#" class="brand__logo"> <img
					src="../pictures/farmBureau.png" alt="Render logo">
				</a>
			</div>
			<ul class="nav">
				<li><a href="./adminHome.jsp"><button
							class="btn btn-outline-success" type="button">Home</button></a></li>
				<li><a href="./carrentaladmin.jsp"><button
							class="btn btn-outline-success" type="button">Car</button></a></li>
				<li><a href="./adminUser.jsp"><button
							class="btn btn-outline-success" type="button">Users</button></a></li>
				<li id="right"><a href="./returnCar.jsp"><button
							class="btn btn-outline-success" type="button">Return a
							rental</button></a></li>
			</ul>
			<div class="confirmation">
				<p>
					Logged in as :<%=user.getFirst_name()%>
					<%=user.getLast_Name()%></p>
			</div>
		</div>
	</nav>
	<hr>
	<h1>See All Cars</h1>

	<button type="button" class="btn btn-info btn-lg" name="update"
		data-toggle="modal" id="topbutton" data-target="#addModal">Add
		a new Car</button>
	<div id="addModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add Car Information</h4>
				</div>
				<div class="modal-body">

					<form class="form" action="newcar" method="POST">
						<input type="text" name="Year" placeholder="Year" required>
						<input type="text" name="Make" placeholder="Make" required>
						<input type="text" name="Model" placeholder="Model" required>
						<input type="text" name="Color" placeholder="Color" required>
						<input id="submit" type="submit">
					</form>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>




	<table border="2">
		<tr class="tblHeader">
			<td>Rent This car</td>
			<td>Car ID</td>
			<td>Year</td>
			<td>Make</td>
			<td>Model</td>
			<td>Color</td>
			<td>Availability</td>
			<td>Update</td>
			<td>Delete</td>

		</tr>
		<%
			Table table = new Table();
			table.getTableConnection();
			List<Car> cars = table.getTableConnection();
			String renting;
			for (Car c : cars) {
				int id = c.getId();
				String year = c.getYear();
				String make = c.getMake();
				String model = c.getModel();
				String color = c.getColor();
				String avail = c.getAvailable();
				
		%>
		<tr>
			<td>
				<button type="button" class="btn btn-info btn-lg" name="update"<%if (avail.equals("Unavailable")) {renting = "disabled";} else {renting = "";
				}%>
					<%=renting%> data-toggle="modal"
					data-target="#rentoutModal<%=id%>">Rent to Customer</button>
				<div id="rentoutModal<%=id%>" class="modal fade" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Enter User Name to rent out the
									following Car</h4>
							</div>
							<div class="modal-body">

								<form action="rentoutadmin?id=<%=id%>" method="POST">
									<input type="text" name="User_Name" placeholder="User Name">
									<input id="submit" type="submit">
								</form>

								<br>
								<p>
									ID:
									<%=id%></p>
								<br>
								<p>
									Year:
									<%=year%></p>
								<br>
								<p>
									Make:
									<%=make%></p>
								<br>
								<p>
									Model:
									<%=model%></p>
								<br>
								<p>
									Color:
									<%=color%></p>
								<br>
								<p>
									Availability:
									<%=avail%></p>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>

					</div>
				</div>

			</td>
			<td><%=id%></td>
			<td><%=year%></td>
			<td><%=make%></td>
			<td><%=model%></td>
			<td><%=color%></td>
			<td><%=avail%></td>
			<td>
				<button type="button" class="btn btn-info btn-lg" name="update"
					data-toggle="modal" data-target="#updateModal<%=id%>">Update</button>
				<div id="updateModal<%=id%>" class="modal fade" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Update Car information</h4>
							</div>
							<div class="modal-body">

								<form class="form" action="admincarupdate" method="POST">
									<input type=hidden name="id" value=<%=id%>> <input
										type="text" name="Year" placeholder=<%=year%>> <input
										type="text" name="Make" placeholder=<%=make%>> <input
										type="text" name="Model" placeholder=<%=model%>> <input
										type="text" name="Color" placeholder=<%=color%>> <input
										id="submit" type="submit">
								</form>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>

					</div>
				</div>
			</td>
			<td><form action="deletecar?id=<%=id%>" method="POST">
					<button type="submit" class="btn btn-info btn-lg" name="delete">Delete</button>
				</form></td>

		</tr>

	</table>

	<%
		}
	%>



	<hr>
	<footer>
		<a href="adminHome.jsp" class="btn btn-primary btn-lg active"
			role="button">Home</a>
		<form action="logout" method="POST">
			<button type="submit" class="btn btn-primary btn-lg active">log
				out</button>
		</form>
	</footer>
</body>

</html>