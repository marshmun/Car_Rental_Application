<%  
	String type = (String) session.getAttribute("User_Type");
	if (!"User".equalsIgnoreCase(type)){
		response.sendRedirect("../noccsess.jsp");
	}
	
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../css/userCss/userHome.css">
    <title>Document</title>
</head>

<body>
    <h1>Welcome home </h1>
    <div class="container">
        <img src="../pictures/profile.jpg" alt="Profile">
        <a href="./update.jsp">Update Profile</a>
    </div>
    <div class="container">
        <img src="../pictures/carlogo.png" alt="Car">
        <a href="./carRental.jsp">See Cars available for rental</a>
    </div>
    
    <div>
        <div class="container">
            <img src="../pictures/logout.jpg" alt="Log Out">
            <form action="/logout">
                <button type="submit">Log Out</button>
            </form>
        </div>
    </div>
    <hr>
    <footer>
        <a href="userhome.jsp">Home</a>
        <a href="FAQ.jsp">FAQ</a>
        <a href="logout.jsp">Log Out</a>
    </footer>


</body>
</html>