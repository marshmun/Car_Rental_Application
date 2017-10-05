CREATE TABLE `userdetails` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `First_Name` varchar(45) NOT NULL,
  `Last_Name` varchar(45) NOT NULL,
  `Email_Address` varchar(45) NOT NULL,
  `User_Type` enum('User','Admin') DEFAULT 'User',
  `Password` varchar(45) NOT NULL,
  `User_Name` varchar(45) NOT NULL,
  `Car_Rental` varchar(45) DEFAULT 'User has no car',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
