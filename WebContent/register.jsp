<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="./css/main.css">
<script src="/password.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Document</title>
</head>

<body>
	<h2>Please sign up using your Email address, and provide a
		password you can remember.</h2>
	<div class="form">
		<form action="register" method="POST">
			<input type="text" name="User_Name" placeholder="User Name" required>
			<input type="email" name="Email_Address" placeholder="Email Address"
				required> <input type="text" name="First_Name"
				placeholder="First Name" required> <input type="text"
				name="Last_Name" placeholder="Last Name" required> <input
				type="password" name="Password" placeholder="Password" required>
			<input type="password" name="confirm_password" id="confirm_password"
				placeholder="Confirm Password" /> <span id='message'></span> <input
				class="button" type="submit">

		</form>
	</div>

</body>

</html>