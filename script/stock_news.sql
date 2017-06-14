/*
Navicat MariaDB Data Transfer

Source Server         : moontell
Source Server Version : 50552
Source Host           : 122.152.197.205:3306
Source Database       : moontell

Target Server Type    : MariaDB
Target Server Version : 50552
File Encoding         : 65001

Date: 2017-06-15 01:42:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for stock_news
-- ----------------------------
DROP TABLE IF EXISTS `stock_news`;
CREATE TABLE `stock_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `article` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
