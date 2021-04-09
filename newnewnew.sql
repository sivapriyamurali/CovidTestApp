
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
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `enabled` tinyint NOT NULL,
  `verification_code` varchar(64) DEFAULT NULL,
  `reset_password_token` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Patient_id` varchar(45)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Date`varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Timeslot` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Test_center` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Test_Type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Type` varchar(45) NOT NULL,
  `image` blob NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Records of transaction
-- ----------------------------
INSERT INTO `transaction` VALUES (1, 'yafun@gmail.com', '2021-04-04', '9:00 AM - 10:00 AM','Michigan','Moderna','Test', 0x52306C474F446C6848674155414D51414141414141502F2F2F2F483135666A36386F2B734C4A61784F5A323252715338564B764259624C4762726E4C65384451696366576C7337626F745867734E7A6C7665507179757276312F2F2F2F7741414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414143483542414541414249414C4141414141416541425141414157655943434F5A476D65614B717562477443554B514F384B42474361455442574E47694230424966735643677559343042596B434C484A4752784C49344731564B776943315941394343626451674F463647633643734A6A6B4944564A776E41716968695438536D2B796A2F67424467694443453442674849456551687568487149493334696B6956366C49736B624364366D6956766356645A4A4242365856396864434A5142416F504549493862574653564155514A77494B5167554B41695641516B51724D46386D4E4243704C73724C7A4345414F773D3D);

-- ----------------------------
-- Table structure for HCP
-- ----------------------------
DROP TABLE IF EXISTS `hcp`;
CREATE TABLE `hcp`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;



-- ----------------------------
-- Records of HCP
-- ----------------------------
INSERT INTO `hcp` VALUES(1,'1@gmail.com','$2a$10$.Lk2wErvrAiLhRMfkTLBdOy4p8GGfmIubBF.4ZrXg4UDLO7wEL41e');
INSERT INTO `hcp` VALUES(2,'test@gmail.com','$2a$10$.Lk2wErvrAiLhRMfkTLBdOy4p8GGfmIubBF.4ZrXg4UDLO7wEL41e');




