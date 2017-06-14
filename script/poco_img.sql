/*
Navicat MariaDB Data Transfer

Source Server         : moontell
Source Server Version : 50552
Source Host           : 122.152.197.205:3306
Source Database       : moontell

Target Server Type    : MariaDB
Target Server Version : 50552
File Encoding         : 65001

Date: 2017-06-15 01:42:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for poco_img
-- ----------------------------
DROP TABLE IF EXISTS `poco_img`;
CREATE TABLE `poco_img` (
  `link` varchar(255) NOT NULL,
  `img_src` longtext NOT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`link`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
