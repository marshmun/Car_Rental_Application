CREATE TABLE `cardetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Year` varchar(45) NOT NULL,
  `Make` varchar(45) NOT NULL,
  `Model` varchar(45) NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Availability` enum('Available','Unavailable') NOT NULL DEFAULT 'Available',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;


