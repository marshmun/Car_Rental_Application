<%@ page import="com.rental.models.User" %>
<%  
	User user = (User) session.getAttribute("user");
	if (user==null){
		user=new User();
	}
	if (!"User".equalsIgnoreCase(user.getType())){
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
    <%@ page import="java.sql.ResultSet" %>
	<%@ page import="java.sql.Statement" %>
	<%@ page import="java.sql.Connection" %>
	<%@ page import="java.sql.DriverManager" %>
    <title>Rent a car</title>
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
           <td><form action="rentoutuser?id=<%=rs.getInt("id") %>" method="POST">
           		<button type="submit" name="rent">Rent</button>
           </form></td>
           <td><%=rs.getInt("id") %></td>
           <td><%=rs.getString("Year") %></td>
           <td><%=rs.getString("Make") %></td>
           <td><%=rs.getString("Model") %></td>
           <td><%=rs.getString("Color") %></td>
           <td><%=rs.getString("Availability") %></td>
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
    <hr>
    <footer>
        <a href="userhome.jsp">Home</a>
        <a href="FAQ.jsp">FAQ</a>
        <a href="logout.jsp">Log Out</a>
    </footer>
</body>

</html>