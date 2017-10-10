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
<link rel="stylesheet" href="../css/userCss/userForm.css">
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
	<%
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/carrentalsystem";
			String username = "root";
			String password = "javatest";
			String query = "select * from userdetails where Email_Address ='" + user.getEmail_address() + "'";
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
	%>
	<hr>
	<h2>Update your profile</h2>
	<div class="form">
		<form action="update" method="POST">
			<p>Email Address:</p><input type="text" name="Email_Address"
				placeholder=<%=rs.getString("Email_address")%>> <p>First Name:</p> <input
				type="text" name="First_Name"
				placeholder=<%=rs.getString("First_Name")%>><p>Last Name:</p> <input
				type="text" name="Last_Name"
				placeholder=<%=rs.getString("Last_Name")%>> <input
				class="button" type="submit">
		</form>
	</div>
	<%
		}
	%>
	<%
		rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
	<hr>
	<footer>
		<a href="userhome.jsp"><button class="btn btn-outline-success">Home</button></a> 
		<a href="FAQ.jsp"></a><button class="btn btn-outline-success">FAQ</button></a>
		<form action="logout" method="POST">
			<button type="submit" class="btn btn-outline-success" value="Logout">log out</button>
		</form>
	</footer>
</body>

</html>