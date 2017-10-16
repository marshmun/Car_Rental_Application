<%@ page import="com.rental.models.User"%>
<%@ page import="com.rental.models.ErrorBean"%>
<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
		user = new User();
	}
	if (!"User".equalsIgnoreCase(user.getType())) {
		response.sendRedirect("../noccsess.jsp");
	}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/userCss/userNav.css">
<link rel="stylesheet" href="../css/userCss/userForm.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
								<a href="./userhome.jsp"><button class="btn btn-outline-success">Home</button></a>
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

<h3>Please send the error seen below to an IT Specialist</h3>
<%
ErrorBean errorbean = (ErrorBean)request.getAttribute("errorbean");
out.println(errorbean.getError());

%>

</body>
</html>