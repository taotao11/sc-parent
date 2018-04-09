/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : sucerity

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-04-09 21:58:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `type` int(10) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `isdelete` int(4) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `creat_time` datetime NOT NULL,
  `update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '用户管理', null, '0', '0', null, '0', '一级菜单 管理用户', '2018-04-07 21:35:59', null);
INSERT INTO `sys_permission` VALUES ('2', '新增用户', 'user/addUser', '1', '1', null, '0', '添加用户', '2018-04-07 21:38:28', null);
INSERT INTO `sys_permission` VALUES ('3', '查询用户', 'user/find', '1', '1', null, '0', '二级菜单 查询用户', '2018-04-07 21:39:57', null);
INSERT INTO `sys_permission` VALUES ('4', '修改用户', 'user/uodate', '1', '1', null, '0', '二级菜单 修改用户', '2018-04-07 21:41:07', null);
INSERT INTO `sys_permission` VALUES ('5', '删除用户', 'user/delete', '1', '1', null, '0', '二级菜单 删除用户', '2018-04-07 21:42:11', null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `creat_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员有所有的权限', '管理员', 'ADMIN', '2018-04-07 11:13:42', null, '0');

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`),
  KEY `FKomxrs8a388bknvhjokh440waq` (`permission_id`),
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_role_permission_ibfk_3` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '1', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '1', '3');
INSERT INTO `sys_role_permission` VALUES ('4', '1', '4');
INSERT INTO `sys_role_permission` VALUES ('5', '1', '5');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgkmyslkrfeyn9ukmolvek8b8f` (`uid`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user_info` (`uid`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ipone` varchar(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `creat_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` int(8) NOT NULL,
  `type` int(8) NOT NULL,
  `prent_uid` bigint(20) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `唯一索引` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', '$2a$10$.n44oj28awG2dPHtgGjmM.5BGOtttLH0C2r2pjJV2fwZ0TThMc71q', '18883156885', '29239@qq.com', '2018-04-07 11:09:55', null, '0', '0', '0');
