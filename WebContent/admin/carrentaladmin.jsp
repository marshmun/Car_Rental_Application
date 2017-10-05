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
            <a href="./adminUser.jsp">Users</a>

        </li>
 
        <li id="right">
            <a href="./returnCar.jsp">Return a Rental</a>
        </li>
    </ul>
    <hr>
    <h1>See All Cars</h1>
    
    <button type="button" class="btn btn-info btn-lg" name="update" data-toggle="modal" data-target="#addModal">Add a new Car</button>
           	<div id="addModal" class="modal fade" role="dialog">
  				<div class="modal-dialog">

   					 <!-- Modal content-->
    				<div class="modal-content">
     				 <div class="modal-header">
       				 <button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4 class="modal-title">Add Car Information</h4>
      		</div>
      			<div class="modal-body">
       
        <form action="newcar" method="POST">
            <input type="text" name="Year" placeholder="Year" required>
            <input type="text" name="Make" placeholder="Make" required>
            <input type="text" name="Model" placeholder="Model" required>
            <input type="text" name="Color" placeholder="Color" required>
            <input id="submit" type="submit">
        </form>
       
     
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
    

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
           <td>
           <button type="button" class="btn btn-info btn-lg" name="update" data-toggle="modal" data-target="#rentoutModal">Rent to Customer</button>
           	<div id="rentoutModal" class="modal fade" role="dialog">
  				<div class="modal-dialog">

   					 <!-- Modal content-->
    				<div class="modal-content">
     				 <div class="modal-header">
       				 <button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4 class="modal-title">Enter User Name to rent out the following Car</h4>
      		</div>
      			<div class="modal-body">
        		 
        <form action="rentoutadmin?id=<%=rs.getInt("id") %>" method="POST">
        	<input type="text" name="User_Name" placeholder="User Name">
        	<br>
            <input id="submit" type="submit">
        </form>
       <br>
        <p> ID: <%=rs.getInt("id") %></p>
        <br>
        <p> Year: <%=rs.getString("Year") %></p>
        <br>
        <p> Make: <%=rs.getString("Make") %></p>
        <br>
        <p> Model: <%=rs.getString("Model") %></p>
        <br>
        <p> Color: <%=rs.getString("Color") %></p>
        <br>
        <p> Availability: <%=rs.getString("Availability") %></p>
     
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
           	
           </td>
           <td><%=rs.getInt("id") %></td>
           <td><%=rs.getString("Year") %></td>
           <td><%=rs.getString("Make") %></td>
           <td><%=rs.getString("Model") %></td>
           <td><%=rs.getString("Color") %></td>
           <td><%=rs.getString("Availability") %></td>
           <td> 
           	<button type="button" class="btn btn-info btn-lg" name="update" data-toggle="modal" data-target="#updateModal">Update</button>
           	<div id="updateModal" class="modal fade" role="dialog">
  				<div class="modal-dialog">

   					 <!-- Modal content-->
    				<div class="modal-content">
     				 <div class="modal-header">
       				 <button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4 class="modal-title">Update Car information</h4>
      		</div>
      			<div class="modal-body">
        		 
        <form action="admincarupdate" method="POST">
        	<input type=hidden name="id" value=<%=rs.getInt("id") %>>
            <input type="text" name="Year" placeholder=<%=rs.getString("Year") %>>
            <input type="text" name="Make" placeholder=<%=rs.getString("Make") %>>
            <input type="text" name="Model" placeholder=<%=rs.getString("Model") %>>
            <input type="text" name="Color" placeholder=<%=rs.getString("Color") %>>
            <input id="submit" type="submit">
        </form>
     
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
           	</td>
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
 
		

    <hr>
    <footer>
        <a href="adminHome.jsp">Home</a>
        <a href="">Log out</a>
    </footer>
</body>

</html>