<%@ page import="com.rental.models.User" %>
<%  
	User user = (User) session.getAttribute("user");
	if (user==null){
		user=new User();
	}
	if (!"Admin".equalsIgnoreCase(user.getType())){
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
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Document</title>
</head>

<body>
    <ul class="nav">
        <li>
            <a href="./adminHome.jsp">Home</a>
        </li>
        <li>
        	<a href="./carrentaladmin.jsp">Car</a>
        </li>
       
        <li>
            <a href="./adminUser.jsp">Users</a>

        </li>
 
        <li id="right">
            <a href="./returnCar.jsp">Return a Rental</a>
        </li>
    </ul>
    
    <hr>
    <h1>Return Customers Rental</h1>
    <h2> Please enter the customers Email</h2>
    <div class="form">
        <form action="returnACar" method="POST">
            <input type="text" name="User_Name" placeholder="User Name" required>
            <input id="submit" type="submit">
        </form>
        
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