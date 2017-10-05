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
    <link rel="stylesheet" href="../css/carCss/car.css">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%@ page import="java.sql.ResultSet" %>
	<%@ page import="java.sql.Statement" %>
	<%@ page import="java.sql.Connection" %>
	<%@ page import="java.sql.DriverManager" %>
	
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
    <h1>See All Cars</h1>
    
    <a class="addcar" href="carRentalAdmin.jsp#form">Add a new car</a>

   <form method="post">


   <%
   try
   {
       Class.forName("com.mysql.jdbc.Driver");
       String url="jdbc:mysql://localhost:3306/carrentalsystem";
       String username="root";
       String password="javatest";
       String query="select * from cardetails";
       Connection conn=DriverManager.getConnection(url, username, password);
       Statement stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery(query);
       while(rs.next())
       {
   %>
        <div class="form">
        <form action="newcar" method="POST">
            <input type="text" name="Year" placeholder=<%=rs.getString("Year") %>>
            <input type="text" name="Make" placeholder=<%=rs.getString("Make") %>>
            <input type="text" name="Model" placeholder=<%=rs.getString("Model") %>>
            <input type="text" name="Color" placeholder=<%=rs.getString("Color") %>>
            <input id="submit" type="submit">
        </form>
        <div class="results"></div>
    </div>
   <%
       }
   %>
  
   <%
        rs.close();
        stmt.close();
        conn.close();
   }
   catch(Exception e)
   {
        e.printStackTrace();
   }
   %>
</form>

		

    <hr>
    <footer>
        <a href="adminHome.jsp">Home</a>
        <a href="">Log out</a>
    </footer>
</body>

</html>