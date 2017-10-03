<%  
	String type = (String) session.getAttribute("User_Type");
	if (!"User".equalsIgnoreCase(type)){
		response.sendRedirect("../login.html");
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
    <title>Document</title>
</head>

<body>
    <ul class="nav">
        <li>
            <a href="./userHome.jsp">Home</a>
        </li>
        <li>
            <a href="./update.jsp">Update Profile</a>
        </li>
        <li>
            <a href="./carRental.jsp">See Cars Available For Rental</a>
        </li>
        
    </ul>
    <hr>
    <h2>Update your profile</h2>
    <div class="form">
        <form action="/updateprofile">
            <input type="text" name="Email_Address" placeholder="Email Address">
            <input type="text" name="First_Name" placeholder="First Name">
            <input type="text" name="Last_Name" placeholder="Last Name">
            <input class="button" type="submit">
        </form>
    </div>
    <hr>
    <footer>
        <a href="userhome.jsp">Home</a>
        <a href="FAQ.jsp">FAQ</a>
        <a href="logout.jsp">Log Out</a>
    </footer>
</body>

</html>