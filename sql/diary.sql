/*
 Navicat Premium Data Transfer

 Source Server         : maria
 Source Server Type    : MariaDB
 Source Server Version : 100505
 Source Host           : localhost:3306
 Source Schema         : diary

 Target Server Type    : MariaDB
 Target Server Version : 100505
 File Encoding         : 65001

 Date: 16/12/2020 17:34:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for diary
-- ----------------------------
DROP TABLE IF EXISTS `diary`;
CREATE TABLE `diary`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `mood` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '心情贴纸',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `guide_1` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '引导词1',
  `guide_2` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '引导词2',
  `guide_3` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '引导词3',
  `guide_4` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '引导词4',
  `guide_5` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '引导词5',
  `guide_6` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '引导词6',
  `content_1` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容1',
  `content_2` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容2',
  `content_3` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容3',
  `content_4` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容4',
  `content_5` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容5',
  `content_6` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容6',
  `created_date` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_date` datetime(0) NOT NULL COMMENT '更新时间',
  `period` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '时间类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for guide_mood
-- ----------------------------
DROP TABLE IF EXISTS `guide_mood`;
CREATE TABLE `guide_mood`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid',
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '心情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for guide_motto
-- ----------------------------
DROP TABLE IF EXISTS `guide_motto`;
CREATE TABLE `guide_motto`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `author` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '格言分类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '名言警句' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for guide_tag
-- ----------------------------
DROP TABLE IF EXISTS `guide_tag`;
CREATE TABLE `guide_tag`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid',
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for guide_tips
-- ----------------------------
DROP TABLE IF EXISTS `guide_tips`;
CREATE TABLE `guide_tips`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `tip_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '提示类型',
  `period_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '提示时间段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '引导词' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
