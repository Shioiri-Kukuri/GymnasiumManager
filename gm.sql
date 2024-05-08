/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : gm

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 08/05/2024 10:37:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_competition
-- ----------------------------
DROP TABLE IF EXISTS `c_competition`;
CREATE TABLE `c_competition`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '赛事名称',
  `sort` int(11) NOT NULL COMMENT '顺序',
  `type_id` int(11) NULL DEFAULT NULL COMMENT '赛事类型',
  `status` int(11) NULL DEFAULT NULL COMMENT '赛事状态;1、进行中  2、已完成  3、已取消',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '赛事表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_competition
-- ----------------------------

-- ----------------------------
-- Table structure for c_jugde
-- ----------------------------
DROP TABLE IF EXISTS `c_jugde`;
CREATE TABLE `c_jugde`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `gender` int(11) NOT NULL DEFAULT 1 COMMENT '性别;1、男   2、女',
  `description` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人简介',
  `competition_id` int(11) NOT NULL COMMENT '赛事编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '裁判表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_jugde
-- ----------------------------

-- ----------------------------
-- Table structure for c_notice
-- ----------------------------
DROP TABLE IF EXISTS `c_notice`;
CREATE TABLE `c_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `content` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告内容',
  `competition_id` int(11) NOT NULL COMMENT '赛事编号',
  `create_by` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_notice
-- ----------------------------

-- ----------------------------
-- Table structure for c_type
-- ----------------------------
DROP TABLE IF EXISTS `c_type`;
CREATE TABLE `c_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型名称',
  `sort` int(11) NOT NULL COMMENT '顺序',
  `status` int(11) NULL DEFAULT 2 COMMENT '类型状态;1、启用   2、禁用',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_type
-- ----------------------------

-- ----------------------------
-- Table structure for f_borrow
-- ----------------------------
DROP TABLE IF EXISTS `f_borrow`;
CREATE TABLE `f_borrow`  (
  `field_no` bigint(20) NOT NULL,
  `account` int(11) NOT NULL,
  `time_start` time NULL DEFAULT NULL,
  `time_end` time NULL DEFAULT NULL,
  `time_week` int(11) NULL DEFAULT NULL,
  `borrow_cost` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`field_no`, `account`) USING BTREE,
  INDEX `field_no`(`field_no` ASC, `time_start` ASC, `time_end` ASC, `time_week` ASC) USING BTREE,
  INDEX `account`(`account` ASC) USING BTREE,
  CONSTRAINT `f_borrow_ibfk_1` FOREIGN KEY (`field_no`, `time_start`, `time_end`, `time_week`) REFERENCES `f_time` (`field_no`, `time_start`, `time_end`, `time_week`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `f_borrow_ibfk_2` FOREIGN KEY (`account`) REFERENCES `u_user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_borrow
-- ----------------------------

-- ----------------------------
-- Table structure for f_cost
-- ----------------------------
DROP TABLE IF EXISTS `f_cost`;
CREATE TABLE `f_cost`  (
  `cost_id` bigint(20) NOT NULL,
  `cost_count` int(11) NULL DEFAULT NULL,
  `account` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cost_id`) USING BTREE,
  INDEX `account`(`account` ASC) USING BTREE,
  CONSTRAINT `f_cost_ibfk_1` FOREIGN KEY (`account`) REFERENCES `u_user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_cost
-- ----------------------------

-- ----------------------------
-- Table structure for f_field
-- ----------------------------
DROP TABLE IF EXISTS `f_field`;
CREATE TABLE `f_field`  (
  `field_no` bigint(20) NOT NULL,
  `field_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `field_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `field_cost` int(11) NOT NULL,
  PRIMARY KEY (`field_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_field
-- ----------------------------

-- ----------------------------
-- Table structure for f_time
-- ----------------------------
DROP TABLE IF EXISTS `f_time`;
CREATE TABLE `f_time`  (
  `field_no` bigint(20) NOT NULL,
  `time_start` time NOT NULL,
  `time_end` time NOT NULL,
  `time_week` int(11) NOT NULL,
  `field_status` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`field_no`, `time_start`, `time_end`, `time_week`) USING BTREE,
  CONSTRAINT `f_time_ibfk_1` FOREIGN KEY (`field_no`) REFERENCES `f_field` (`field_no`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_time
-- ----------------------------

-- ----------------------------
-- Table structure for u_noticeboard
-- ----------------------------
DROP TABLE IF EXISTS `u_noticeboard`;
CREATE TABLE `u_noticeboard`  (
  `no` int(3) NOT NULL COMMENT '公告编号',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告的信息',
  `account` int(12) NULL DEFAULT NULL COMMENT '公告发布者',
  PRIMARY KEY (`no`) USING BTREE,
  INDEX `fk_noticeboard_user`(`account` ASC) USING BTREE,
  CONSTRAINT `fk_noticeboard_user` FOREIGN KEY (`account`) REFERENCES `u_user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_noticeboard
-- ----------------------------

-- ----------------------------
-- Table structure for u_role
-- ----------------------------
DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role`  (
  `role_id` int(2) NOT NULL,
  `name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`role_id`, `name`) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_role
-- ----------------------------
INSERT INTO `u_role` VALUES (1, '普通用户');
INSERT INTO `u_role` VALUES (2, '管理员');
INSERT INTO `u_role` VALUES (3, '超级管理员');

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user`  (
  `account` int(12) NOT NULL COMMENT '账号，长度固定',
  `key` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名,理论上可与教务系统搭配使用用户作为职工名/用户实名/机构名唯一标识，但不在本系统中作为实际有效的标识，用户可自定义编辑',
  `role_id` int(2) NOT NULL COMMENT '外键，预设的角色类型中的一种1表示普通用户2表示管理员3表示超级管理员',
  PRIMARY KEY (`account`, `key`) USING BTREE,
  INDEX `fk_user_role`(`role_id` ASC) USING BTREE,
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `u_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
