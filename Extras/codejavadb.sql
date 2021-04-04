-- ----------------------------
-- Table structure for users
-- ----------------------------

DROP TABLE IF EXISTS `users`;
  CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `address` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL,
  `DOB` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ---------------------------------------------------
-- Table structure for Transation of Appointments
-- ---------------------------------------------------
CREATE TABLE `Transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Patient_id` varchar(45) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Timeslot` varchar(100) NOT NULL,
  `Test_center` varchar(45) NOT NULL,
  `Test_Type` varchar(45) NOT NULL,
  `Type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;