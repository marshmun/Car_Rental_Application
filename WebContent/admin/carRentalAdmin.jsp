<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../css/adminCss/adminNav.css">
    <link rel="stylesheet" href="../css/carCss/car.css">
   
	
    <title>Document</title>
</head>

<body>
    <ul class="nav">
        <li>
            <a href="./adminHome.html">Home</a>
        </li>

        <li>
            <a href="./carRentalAdmin.html">Car</a>
        </li>
        <li>
            <a href="#">Customer</a>

            <ul>
                <li>
                    <a href="./addCustomer.html">Add New Customer</a>
                </li>
                <li>
                    <a href="./updateCustomer.html">Update Exisiting Customer Profile</a>
                </li>

                <li>
                    <a href="./deleteCustomer.html">Delete Exisiting Customer</a>
                </li>
            </ul>

        </li>
        <li>
            <a href="#">Admin</a>
            <ul>
                <li>
                    <a href="./addAdmin.html">Add New Admin</a>
                </li>

                <li>
                    <a href="./updateAdmin.html">Update Exisiting Amdin Profile</a>
                </li>
                <li>
                    <a href="./deleteAdmin.html">Delete Exisiting Admin</a>
                </li>
            </ul>
        </li>
        <li id="right">
            <a href="#">Rentals</a>

            <ul>
                <li>
                    <a href="./rentCar.html">Customer Rental</a>
                </li>
                <li>
                    <a href="./returnCar.html">Return a Car</a>
                </li>

            </ul>
        </li>
    </ul>
    <hr>
    <h1>See All Cars</h1>
    
    <div class="carData">
        <p>ID:</p>
        <p>|</p>
        <p>Year:</p>
        <p>|</p>
        <p>Make:</p>
        <p>|</p>
        <p>Model:</p>
        <p>|</p>
        <p>Color:</p>
        <p>|</p>
        <p>Availability:</p>
    </div>
   
		

    <hr>
    <footer>
        <a href="adminHome.html">Home</a>
        <a href="">Log out</a>
    </footer>
</body>

</html>