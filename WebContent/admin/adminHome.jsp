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
    <link rel="stylesheet" href="../css/adminCss/admin.css">
    <title>Admin Home</title>
</head>

<body>
    <h1>Welcome to your home please choose from the selected what you would like to do.</h1>
    <hr>
    <div class="options">
        <div>
            <img src="../pictures/carlogo.png" alt="Plus">
            <a href="./carrentaladmin.jsp">Add/ Delete/ update Cars</a>
        </div>
        <div>
            <img src="../pictures/plusLogo.png" alt="Add Admin">
            <a href="./addAdmin.jsp">Add New Admin</a>
        </div>
        <div>
            <img id="admin" src="../pictures/Admin-logo.png" alt="Update Admin">
            <a href="./updateAdmin.jsp">Update Exisiting Admin Profile</a>
        </div>
        <div>
            <img src="../pictures/deleteButton.png" alt="Delete Exisiting Admin">
            <a href="./deleteAdmin.jsp">Delete Exisiting Admin</a>
        </div>
        <div>
            <img class="car" src="../pictures/carlogo.png" alt="Rent a Car">
            <a href="./rentCar.jsp">Rent a Car for Exisiting Customer</a>
        </div>
        <div>
            <img class="car" src="../pictures/carlogo.png" alt="Return a Car">
            <a href="./returnCar.jsp">Return a Car for Exisiting Customer</a>
        </div>
    </div>
    <hr>
    <footer>
        <a href="adminHome.jsp">Home</a>
        <a href="">Log out</a>
    </footer>
</body>

</html>