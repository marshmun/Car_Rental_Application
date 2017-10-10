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
	<link rel="stylesheet" href="../css/userCss/userNav.css">
	<link rel="stylesheet" href="../css/carCss/car.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<%@ page import="java.sql.ResultSet"%>
		<%@ page import="java.sql.Statement"%>
			<%@ page import="java.sql.Connection"%>
				<%@ page import="java.sql.DriverManager"%>
					<title>Rent a car</title>
</head>

<body>
	<nav class="navbar navbar-default --primary">
		<div class="containers">
			<div class="navbar--brand brand">
				<a href="#" class="brand__logo">
					<img src="../pictures/farmBureau.png" alt="Render logo">
				</a>
			</div>
			<div>
				<ul class="nav">
					<li>
						<a href="./userHome.jsp"><button class="btn btn-outline-success">Home</button></a>
					</li>
					<li>
							<a href="./update.jsp"><button class="btn btn-outline-success">Update Profile</button></a>
					<li>
							<a href="./carRental.jsp"><button class="btn btn-outline-success">See Cars Available For Rental</button></a>
					</li>

				</ul>
			</div>
			<div class="confirmation">
				<p>Logged in as :<%=user.getFirst_name() %> <%=user.getLast_Name() %></p>
			</div>
		</div>
	</nav>
	<hr>

	<table border="2">
		<tr class="tblHeader">
			<td>Rent This car</td>
			<td>Car ID</td>
			<td>Year</td>
			<td>Make</td>
			<td>Model</td>
			<td>Color</td>
			<td>Availability</td>


		</tr>
		<%
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/carrentalsystem";
				String username = "root";
				String password = "javatest";
				String query = "select * from cardetails";
				Connection conn = DriverManager.getConnection(url, username, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					Integer id = rs.getInt("id");
					String year = rs.getString("Year");
					String make = rs.getString("Make");
					String model = rs.getString("Model");
					String color = rs.getString("Color");
					String avail = rs.getString("Availability");
					String renting;
		%>
			<tr>
				<td>
					<button type="button" class="btn btn-info btn-lg" name="update" <%if (avail.equals( "Unavailable")) { renting="disabled"
					 ; } else { renting="" ; }%>
						<%=renting%> data-toggle="modal" data-target="#rentmeoutModal<%=id%>">Rent this car</button>
					<div id="rentmeoutModal<%=id%>" class="modal fade" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Are you sure you'd like to rent this car?</h4>
								</div>
								<div class="modal-body">

									<form action="rentoutuser?id=<%=id%>" method="POST">
										<button type="submit" name="rent">Rent</button>
									</form>

									<br>
									<p>
										ID:
										<%=id%>
									</p>
									<br>
									<p>
										Year:
										<%=year%>
									</p>
									<br>
									<p>
										Make:
										<%=make%>
									</p>
									<br>
									<p>
										Model:
										<%=model%>
									</p>
									<br>
									<p>
										Color:
										<%=color%>
									</p>
									<br>
									<p>
										Availability:
										<%=avail%>
									</p>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
							</div>

						</div>
					</div>
				</td>
				<td>
					<%=id%>
				</td>
				<td>
					<%=year%>
				</td>
				<td>
					<%=make%>
				</td>
				<td>
					<%=model%>
				</td>
				<td>
					<%=color%>
				</td>
				<td>
					<%=avail%>
				</td>
			</tr>
			<%
			}
		%>
	</table>
	<%
		rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
		</form>
		<hr>
		<footer>
			<a href="userhome.jsp"><button class="btn btn-outline-success">Home</button></a>
			<a href="FAQ.jsp"><button class="btn btn-outline-success">FAQ</button></a>
			<form action="logout" method="POST">
				<button class=" btn btn-outline-success" type="submit" value="Logout">log out</button>
			</form>
		</footer>
</body>

</html>