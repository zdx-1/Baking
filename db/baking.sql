/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : baking

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2024-07-03 08:33:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `img_url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES ('1', '/imgs/banner1.jpg', '2024-01-15 00:00:00');
INSERT INTO `banner` VALUES ('2', '/imgs/banner2.jpg', '2024-01-16 00:00:00');
INSERT INTO `banner` VALUES ('3', '/imgs/banner3.jpg', '2024-01-17 00:00:00');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '烘培食谱', '1', '0', '1');
INSERT INTO `category` VALUES ('2', '面包', '2', '1', '1');
INSERT INTO `category` VALUES ('3', '家常菜', '2', '1', '1');
INSERT INTO `category` VALUES ('4', '小食', '2', '1', '1');
INSERT INTO `category` VALUES ('5', '烘培视频', '1', '0', '2');
INSERT INTO `category` VALUES ('6', '家常菜教学', '2', '5', '2');
INSERT INTO `category` VALUES ('7', '美食欣赏', '2', '5', '2');
INSERT INTO `category` VALUES ('8', '资讯', '1', '0', '3');
INSERT INTO `category` VALUES ('9', '美食资讯', '2', '8', '3');
INSERT INTO `category` VALUES ('10', '店家资讯', '2', '8', '3');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `content_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '4321453251', '3', '2', null);
INSERT INTO `comment` VALUES ('2', '这是一个评论测试', '1', '4', null);
INSERT INTO `comment` VALUES ('3', '这是一个评论', '1', '3', null);
INSERT INTO `comment` VALUES ('4', '这是一个评论', '1', '5', null);

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `content` text,
  `type` int(11) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  `brief` varchar(50) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('3', '这是一篇测试文章', '/2024/07/03/f43710de-f4b8-4ca9-9623-d1c10acdda6b.jpg', null, '<p>这里是文章内容</p>', '1', null, null, '这里是文章内容', '2', '1', null, null, null);
INSERT INTO `content` VALUES ('4', '这是一个视频测试', '/2024/07/03/d459eef4-f1a0-4bff-b50f-78bcd066f4b1.jpg', '/2024/07/03/812ef1f0-4894-4343-9259-6b0e6118c463.mp4', null, '2', null, null, null, '7', '1', null, null, null);
INSERT INTO `content` VALUES ('5', '这是一个资讯测试文章', '/2024/07/03/a0818bc6-059e-4024-926a-d1667a061b66.jpg', null, '<p>这里是文章内容</p>', '1', null, null, '这里是文章内容', '2', '1', null, null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `is_admin` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1001', 'ea48ea7f11946bf1ed8936648352f5f1', '1001', '1', '2024-07-03 08:11:49', '/2024/07/03/7389fc8a-28b2-4d4d-b567-fc568f1ac651.jpg');
INSERT INTO `user` VALUES ('3', '1002', '7e08736172386c40c40070042c211e09', '1002', '0', '2024-07-03 08:31:24', null);
INSERT INTO `user` VALUES ('4', '1003', '10a2bfae1c97feeb827f2ffdc9547500', '1003', '0', '2024-07-03 08:31:38', null);
