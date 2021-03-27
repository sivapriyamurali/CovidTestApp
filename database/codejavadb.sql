/*
 Navicat Premium Data Transfer

 Source Server         : 121.41.88.205
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 121.41.88.205:6240
 Source Schema         : codejavadb

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 27/03/2021 09:16:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment`  (
  `appointment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `day` bigint(20) NOT NULL,
  `invited_count` bigint(20) NOT NULL,
  `month` bigint(20) NOT NULL,
  `time` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `year` bigint(20) NOT NULL,
  PRIMARY KEY (`appointment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES (24, 26, 55, 3, '9:00 AM - 10:00 AM', 2021);
INSERT INTO `appointment` VALUES (25, 19, 222, 3, '9:00 AM - 10:00 AM', 2021);

-- ----------------------------
-- Table structure for test_center
-- ----------------------------
DROP TABLE IF EXISTS `test_center`;
CREATE TABLE `test_center`  (
  `center_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Test center id',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Test center name',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Test center address',
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
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dob` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` bigint(20) NOT NULL,
  `age` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_6dotkott2kjsp8vw4d0m25fb7`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'James', 'Auburn Road', NULL, '4098@gmail.com', '123', 18628320437, 20);
INSERT INTO `users` VALUES (2, 'Bob', 'Okaland Road', NULL, '1111@gmail.com', '23', 18628320437, 40);

SET FOREIGN_KEY_CHECKS = 1;
