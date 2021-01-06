/*
Navicat MySQL Data Transfer

Source Server         : 本机mysql
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : blog_system

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2021-01-06 15:25:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(12) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `visible` int(1) DEFAULT NULL,
  `sts` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '标题1', 'tzs', '2020-05-17 15:21:05', null, '1', 'A');
INSERT INTO `article` VALUES ('2', '标题2', 'tzs', '2020-05-17 15:21:05', '', '1', 'A');
INSERT INTO `article` VALUES ('3', '标题3', 'tzs', '2020-05-17 15:21:05', '', '1', 'A');
INSERT INTO `article` VALUES ('4', '标题4', 'tzs', '2020-05-17 15:21:05', '', '1', 'A');
INSERT INTO `article` VALUES ('5', '标题5', 'tzs', '2020-05-17 15:21:05', '', '1', 'A');
INSERT INTO `article` VALUES ('6', '标题6', 'tzs', '2020-05-17 15:21:05', '', '1', 'A');
INSERT INTO `article` VALUES ('7', '标题7', 'tzs', '2020-05-17 15:21:05', '', '1', 'A');
