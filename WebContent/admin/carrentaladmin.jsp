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
           <tr>
           <td><form action="rentoutadmin?id=<%=rs.getInt("id") %>" method="POST">
           		<button type="submit" name="rent">Rent to customer</button>
           </form></td>
           <td><%=rs.getInt("id") %></td>
           <td><%=rs.getString("Year") %></td>
           <td><%=rs.getString("Make") %></td>
           <td><%=rs.getString("Model") %></td>
           <td><%=rs.getString("Color") %></td>
           <td><%=rs.getString("Availability") %></td>
           <td> <form action="updatecar?id=<%=rs.getInt("id") %>" method="POST">
           		<button type="submit" name="update">Update</button>
           	</form></td>
          	<td><form action="deletecar?id=<%=rs.getInt("id") %>" method="POST">
                <button type="submit" name="delete">Delete</button>
            </form></td>
            
             </tr>
   <%
       }
   %>
   </table>
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
 <div class="form">
        <form action="newcar" method="POST">
            <input type="text" name="Year" placeholder="Year" required>
            <input type="text" name="Make" placeholder="Make" required>
            <input type="text" name="Model" placeholder="Model" required>
            <input type="text" name="Color" placeholder="Color" required>
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