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
    <link rel="stylesheet" href="../css/customerCss/customer.css">
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
            <a href="#">Customer</a>

            <ul>
                <li>
                    <a href="./addCustomer.jsp">Add New Customer</a>
                </li>
                <li>
                    <a href="./updateCustomer.jsp">Update Exisiting Customer Profile</a>
                </li>

                <li>
                    <a href="./deleteCustomer.jsp">Delete Exisiting Customer</a>
                </li>
            </ul>

        </li>
        <li>
            <a href="#">Admin</a>
            <ul>
                <li>
                    <a href="./addAdmin.jsp">Add New Admin</a>
                </li>

                <li>
                    <a href="./updateAdmin.jsp">Update Exisiting Amdin Profile</a>
                </li>
                <li>
                    <a href="./deleteAdmin.jsp">Delete Exisiting Admin</a>
                </li>
            </ul>
        </li>
        <li id="right">
            <a href="#">Rentals</a>

            <ul>
                <li>
                    <a href="./rentCar.jsp">Customer Rental</a>
                </li>
                <li>
                    <a href="./returnCar.jsp">Return a Car</a>
                </li>

            </ul>
        </li>
    </ul>
    <hr>
    <h1>Delete Current User</h1>
    <h2>Look for user by Email Address</h2>
    <div class="form">
        <form action="/addCustomer" method="POST">
            <input type="text" name="User_Name" placeholder="User Name to Delete" required>
            <input type="text" name="Password" placeholder="Admin Password" required>
            <input id="submit" type="submit">
        </form>
        <div class="results"></div>
    </div>
    <hr>
    <footer>
        <a href="adminHome.jsp">Home</a>
        <a href="">Log out</a>
    </footer>
</body>

</html>