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


-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `Test Center` varchar(45) NOT NULL,
  `Date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Time` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Number of Slots` bigint(20) NOT NULL,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES (1, 'Madison Hts', '01/01/2021', '9:00 AM - 10:00 AM', 10,'Vaccine');
INSERT INTO `appointment` VALUES (2, 'Madison Hts', '01/01/2021', '9:00 AM - 10:00 AM', 20,'Test');
INSERT INTO `appointment` VALUES (3, 'Madison Hts', '01/01/2021', '9:00 AM - 10:00 AM', 30,'Vaccine');

-- ----------------------------
-- Table structure for test_center
-- ----------------------------
DROP TABLE IF EXISTS `test_center`;
CREATE TABLE `test_center`  (
  `center_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'testcenter id',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'testcenter name',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'testcenet address',
  `zip_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`center_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_center
-- ----------------------------
INSERT INTO `test_center` VALUES (1, 'test_center1', 'address1', '4801');
INSERT INTO `test_center` VALUES (2, 'test_center2', 'address2', '4802');