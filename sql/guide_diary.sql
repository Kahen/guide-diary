/*
 Navicat Premium Data Transfer

 Source Server         : localmysql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : guide_diary

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 07/12/2020 00:03:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `blog_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '博客ID',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `video_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '视频URL',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `publish_time` datetime(0) NOT NULL COMMENT '发布时间',
  `is_original` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'true' COMMENT '是否原创',
  PRIMARY KEY (`blog_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, 1, '1', '1', '2020-12-05 21:46:28', '2020-12-06 15:10:12', '1');

-- ----------------------------
-- Table structure for blog_collect
-- ----------------------------
DROP TABLE IF EXISTS `blog_collect`;
CREATE TABLE `blog_collect`  (
  `collect_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '转发ID',
  `blog_id` bigint(0) NOT NULL COMMENT '博客ID',
  `user_id` bigint(0) NOT NULL COMMENT '收藏用户ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`collect_id`) USING BTREE,
  INDEX `blog_id`(`blog_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_collect
-- ----------------------------
INSERT INTO `blog_collect` VALUES (1, 1, 1, '2020-12-06 15:17:42');

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `blog_id` bigint(0) NOT NULL COMMENT '博客ID',
  `user_id` bigint(0) NOT NULL COMMENT '评论用户ID',
  `pid` bigint(0) NULL DEFAULT 0 COMMENT '该评论所在的父级ID',
  `reply_id` bigint(0) NULL DEFAULT 0 COMMENT '该评论回复的评论ID,没有则为0',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `publish_time` datetime(0) NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `blog_id`(`blog_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------

-- ----------------------------
-- Table structure for blog_img
-- ----------------------------
DROP TABLE IF EXISTS `blog_img`;
CREATE TABLE `blog_img`  (
  `img_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `blog_id` bigint(0) NOT NULL COMMENT '博客ID',
  `img_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '图片URL',
  PRIMARY KEY (`img_id`) USING BTREE,
  INDEX `blog_id`(`blog_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_img
-- ----------------------------

-- ----------------------------
-- Table structure for blog_like
-- ----------------------------
DROP TABLE IF EXISTS `blog_like`;
CREATE TABLE `blog_like`  (
  `like_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
  `blog_id` bigint(0) NOT NULL COMMENT '博客ID',
  `user_id` bigint(0) NOT NULL COMMENT '点赞用户ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`like_id`) USING BTREE,
  INDEX `blog_id`(`blog_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_like
-- ----------------------------

-- ----------------------------
-- Table structure for blog_repost
-- ----------------------------
DROP TABLE IF EXISTS `blog_repost`;
CREATE TABLE `blog_repost`  (
  `repost_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '转发ID',
  `blog_id` bigint(0) NOT NULL COMMENT '博客ID',
  `user_id` bigint(0) NOT NULL COMMENT '转发用户ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`repost_id`) USING BTREE,
  INDEX `blog_id`(`blog_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客转发表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_repost
-- ----------------------------

-- ----------------------------
-- Table structure for code_column_config
-- ----------------------------
DROP TABLE IF EXISTS `code_column_config`;
CREATE TABLE `code_column_config`  (
  `column_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `column_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `column_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `extra` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `form_show` bit(1) NULL DEFAULT NULL,
  `form_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `list_show` bit(1) NULL DEFAULT NULL,
  `not_null` bit(1) NULL DEFAULT NULL,
  `query_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date_annotation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX `idx_table_name`(`table_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 253 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成字段信息存储' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of code_column_config
-- ----------------------------
INSERT INTO `code_column_config` VALUES (191, 'blog', 'blog_id', 'bigint', NULL, 'auto_increment', b'1', '', 'PRI', b'1', b'0', NULL, '博客ID', NULL);
INSERT INTO `code_column_config` VALUES (192, 'blog', 'user_id', 'bigint', NULL, '', b'1', '', 'MUL', b'1', b'0', NULL, '用户ID', NULL);
INSERT INTO `code_column_config` VALUES (193, 'blog', 'content', 'longtext', NULL, '', b'1', 'Textarea', '', b'1', b'1', 'Like', '内容', '');
INSERT INTO `code_column_config` VALUES (194, 'blog', 'video_url', 'varchar', NULL, '', b'1', 'Input', '', b'1', b'0', '=', '视频URL', NULL);
INSERT INTO `code_column_config` VALUES (195, 'blog', 'create_time', 'datetime', NULL, '', b'1', 'Date', '', b'1', b'0', '=', '创建时间', 'CreationTimestamp');
INSERT INTO `code_column_config` VALUES (196, 'blog', 'publish_time', 'datetime', NULL, '', b'1', 'Date', '', b'1', b'0', '=', '发布时间', 'UpdateTimestamp');
INSERT INTO `code_column_config` VALUES (197, 'blog', 'is_original', 'varchar', 'is_original', '', b'1', 'Radio', '', b'1', b'1', '=', '是否原创', NULL);
INSERT INTO `code_column_config` VALUES (198, 'blog_like', 'like_id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'1', NULL, '点赞ID', NULL);
INSERT INTO `code_column_config` VALUES (199, 'blog_like', 'blog_id', 'bigint', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '博客ID', NULL);
INSERT INTO `code_column_config` VALUES (200, 'blog_like', 'user_id', 'bigint', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '点赞用户ID', NULL);
INSERT INTO `code_column_config` VALUES (201, 'blog_like', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '创建时间', NULL);
INSERT INTO `code_column_config` VALUES (202, 'blog_repost', 'repost_id', 'bigint', NULL, 'auto_increment', b'1', 'Input', 'PRI', b'1', b'0', NULL, '转发ID', NULL);
INSERT INTO `code_column_config` VALUES (203, 'blog_repost', 'blog_id', 'bigint', NULL, '', b'1', 'Input', 'MUL', b'1', b'0', NULL, '博客ID', NULL);
INSERT INTO `code_column_config` VALUES (204, 'blog_repost', 'user_id', 'bigint', NULL, '', b'1', 'Input', 'MUL', b'1', b'0', NULL, '转发用户ID', NULL);
INSERT INTO `code_column_config` VALUES (205, 'blog_repost', 'content', 'text', NULL, '', b'1', 'Textarea', '', b'1', b'0', NULL, '内容', NULL);
INSERT INTO `code_column_config` VALUES (206, 'blog_repost', 'create_time', 'datetime', NULL, '', b'1', 'Date', '', b'1', b'0', NULL, '创建时间', 'CreationTimestamp');
INSERT INTO `code_column_config` VALUES (207, 'blog_collect', 'collect_id', 'bigint', NULL, 'auto_increment', b'1', 'Input', 'PRI', b'1', b'0', NULL, '转发ID', NULL);
INSERT INTO `code_column_config` VALUES (208, 'blog_collect', 'blog_id', 'bigint', NULL, '', b'1', 'Input', 'MUL', b'1', b'0', NULL, '博客ID', NULL);
INSERT INTO `code_column_config` VALUES (209, 'blog_collect', 'user_id', 'bigint', NULL, '', b'1', 'Input', 'MUL', b'1', b'0', NULL, '收藏用户ID', NULL);
INSERT INTO `code_column_config` VALUES (210, 'blog_collect', 'create_time', 'datetime', NULL, '', b'1', 'Date', '', b'1', b'0', NULL, '创建时间', 'CreationTimestamp');
INSERT INTO `code_column_config` VALUES (211, 'comment_like', 'like_id', 'bigint', NULL, 'auto_increment', b'1', 'Input', 'PRI', b'1', b'0', '=', '点赞ID', NULL);
INSERT INTO `code_column_config` VALUES (212, 'comment_like', 'comment_id', 'bigint', NULL, '', b'1', 'Input', 'MUL', b'1', b'0', '=', '评论ID', NULL);
INSERT INTO `code_column_config` VALUES (213, 'comment_like', 'user_id', 'bigint', NULL, '', b'1', 'Input', 'MUL', b'1', b'0', '=', '点赞用户ID', NULL);
INSERT INTO `code_column_config` VALUES (214, 'comment_like', 'create_time', 'datetime', NULL, '', b'1', 'Date', '', b'1', b'0', '=', '创建时间', 'CreationTimestamp');
INSERT INTO `code_column_config` VALUES (215, 'blog_comment', 'id', 'bigint', NULL, 'auto_increment', b'1', 'Input', 'PRI', b'1', b'0', '=', '评论ID', NULL);
INSERT INTO `code_column_config` VALUES (216, 'blog_comment', 'blog_id', 'bigint', NULL, '', b'1', 'Input', 'MUL', b'1', b'0', '=', '博客ID', NULL);
INSERT INTO `code_column_config` VALUES (217, 'blog_comment', 'user_id', 'bigint', NULL, '', b'1', 'Input', 'MUL', b'1', b'0', '=', '评论用户ID', NULL);
INSERT INTO `code_column_config` VALUES (218, 'blog_comment', 'pid', 'bigint', NULL, '', b'1', 'Input', '', b'1', b'0', NULL, '该评论所在的父级ID', NULL);
INSERT INTO `code_column_config` VALUES (219, 'blog_comment', 'reply_id', 'bigint', NULL, '', b'1', 'Input', '', b'1', b'0', NULL, '该评论回复的评论ID,没有则为0', NULL);
INSERT INTO `code_column_config` VALUES (220, 'blog_comment', 'content', 'text', NULL, '', b'1', 'Textarea', '', b'1', b'0', NULL, '内容', NULL);
INSERT INTO `code_column_config` VALUES (221, 'blog_comment', 'publish_time', 'datetime', NULL, '', b'1', 'Date', '', b'1', b'0', NULL, '发布时间', 'CreationTimestamp');
INSERT INTO `code_column_config` VALUES (222, 'blog_img', 'img_id', 'bigint', NULL, 'auto_increment', b'1', 'Input', 'PRI', b'1', b'0', '=', '图片ID', NULL);
INSERT INTO `code_column_config` VALUES (223, 'blog_img', 'blog_id', 'bigint', NULL, '', b'1', 'Input', 'MUL', b'1', b'0', '=', '博客ID', NULL);
INSERT INTO `code_column_config` VALUES (224, 'blog_img', 'img_url', 'varchar', NULL, '', b'1', 'Input', '', b'1', b'0', 'Like', '图片URL', NULL);
INSERT INTO `code_column_config` VALUES (225, 'tool_alipay_config', 'config_id', 'bigint', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, 'ID', NULL);
INSERT INTO `code_column_config` VALUES (226, 'tool_alipay_config', 'app_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '应用ID', NULL);
INSERT INTO `code_column_config` VALUES (227, 'tool_alipay_config', 'charset', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '编码', NULL);
INSERT INTO `code_column_config` VALUES (228, 'tool_alipay_config', 'format', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '类型 固定格式json', NULL);
INSERT INTO `code_column_config` VALUES (229, 'tool_alipay_config', 'gateway_url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '网关地址', NULL);
INSERT INTO `code_column_config` VALUES (230, 'tool_alipay_config', 'notify_url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '异步回调', NULL);
INSERT INTO `code_column_config` VALUES (231, 'tool_alipay_config', 'private_key', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '私钥', NULL);
INSERT INTO `code_column_config` VALUES (232, 'tool_alipay_config', 'public_key', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '公钥', NULL);
INSERT INTO `code_column_config` VALUES (233, 'tool_alipay_config', 'return_url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '回调地址', NULL);
INSERT INTO `code_column_config` VALUES (234, 'tool_alipay_config', 'sign_type', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '签名方式', NULL);
INSERT INTO `code_column_config` VALUES (235, 'tool_alipay_config', 'sys_service_provider_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '商户号', NULL);
INSERT INTO `code_column_config` VALUES (236, 'tool_email_config', 'config_id', 'bigint', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, 'ID', NULL);
INSERT INTO `code_column_config` VALUES (237, 'tool_email_config', 'from_user', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '收件人', NULL);
INSERT INTO `code_column_config` VALUES (238, 'tool_email_config', 'host', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '邮件服务器SMTP地址', NULL);
INSERT INTO `code_column_config` VALUES (239, 'tool_email_config', 'pass', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '密码', NULL);
INSERT INTO `code_column_config` VALUES (240, 'tool_email_config', 'port', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '端口', NULL);
INSERT INTO `code_column_config` VALUES (241, 'tool_email_config', 'user', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '发件者用户名', NULL);
INSERT INTO `code_column_config` VALUES (242, 'tool_local_storage', 'storage_id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, 'ID', NULL);
INSERT INTO `code_column_config` VALUES (243, 'tool_local_storage', 'real_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '文件真实的名称', NULL);
INSERT INTO `code_column_config` VALUES (244, 'tool_local_storage', 'name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '文件名', NULL);
INSERT INTO `code_column_config` VALUES (245, 'tool_local_storage', 'suffix', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '后缀', NULL);
INSERT INTO `code_column_config` VALUES (246, 'tool_local_storage', 'path', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '路径', NULL);
INSERT INTO `code_column_config` VALUES (247, 'tool_local_storage', 'type', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '类型', NULL);
INSERT INTO `code_column_config` VALUES (248, 'tool_local_storage', 'size', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '大小', NULL);
INSERT INTO `code_column_config` VALUES (249, 'tool_local_storage', 'create_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建者', NULL);
INSERT INTO `code_column_config` VALUES (250, 'tool_local_storage', 'update_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更新者', NULL);
INSERT INTO `code_column_config` VALUES (251, 'tool_local_storage', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建日期', NULL);
INSERT INTO `code_column_config` VALUES (252, 'tool_local_storage', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更新时间', NULL);
INSERT INTO `code_column_config` VALUES (253, 'diary_user', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', '=', '自增id', NULL);
INSERT INTO `code_column_config` VALUES (254, 'diary_user', 'uid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', 'Like', 'uid', NULL);
INSERT INTO `code_column_config` VALUES (255, 'diary_user', 'name', 'varchar', NULL, '', b'1', 'Input', '', b'1', b'1', 'Like', '用户名', NULL);
INSERT INTO `code_column_config` VALUES (256, 'diary_user', 'nickname', 'varchar', NULL, '', b'1', 'Input', '', b'1', b'1', 'Like', '昵称', NULL);
INSERT INTO `code_column_config` VALUES (257, 'diary_user', 'description', 'varchar', NULL, '', b'1', 'Textarea', '', b'1', b'1', NULL, '用户介绍', NULL);
INSERT INTO `code_column_config` VALUES (258, 'diary_user', 'followers_count', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '粉丝数', NULL);
INSERT INTO `code_column_config` VALUES (259, 'diary_user', 'friends_count', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '关注数', NULL);
INSERT INTO `code_column_config` VALUES (260, 'diary_user', 'statuses_count', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '博客数', NULL);
INSERT INTO `code_column_config` VALUES (261, 'diary_user', 'create_time', 'datetime', NULL, '', b'1', 'Date', '', b'1', b'1', NULL, '创建时间', NULL);
INSERT INTO `code_column_config` VALUES (262, 'diary_user', 'avatar_url', 'varchar', NULL, '', b'1', 'Input', '', b'1', b'1', NULL, '头像链接', NULL);
INSERT INTO `code_column_config` VALUES (263, 'diary_user', 'password', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '密码哈希', NULL);

-- ----------------------------
-- Table structure for code_gen_config
-- ----------------------------
DROP TABLE IF EXISTS `code_gen_config`;
CREATE TABLE `code_gen_config`  (
  `config_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `cover` bit(1) NULL DEFAULT NULL COMMENT '是否覆盖',
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `pack` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '至于哪个包下',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端代码生成的路径',
  `api_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端Api文件路径',
  `prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表前缀',
  `api_alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口名称',
  PRIMARY KEY (`config_id`) USING BTREE,
  INDEX `idx_table_name`(`table_name`(100)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成器配置' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of code_gen_config
-- ----------------------------
INSERT INTO `code_gen_config` VALUES (7, 'blog', 'Kahen', b'1', 'eladmin-system', 'me.zhengjie.modules.blog', 'blog', 'blog\\', NULL, 'blog');
INSERT INTO `code_gen_config` VALUES (8, 'blog_like', 'Kahen', b'0', 'eladmin-system', 'me.zhengjie.modules.blog', 'blog_like', 'blog_like\\', 'blog_', 'blog-like');
INSERT INTO `code_gen_config` VALUES (9, 'blog_repost', 'Kahen', b'0', 'eladmin-system', 'me.zhengjie.modules.blog', 'blog_repost', 'blog_repost\\', 'blog_', 'blog_repost');
INSERT INTO `code_gen_config` VALUES (10, 'blog_collect', 'Kahen', b'0', 'eladmin-system', 'me.zhengjie.modules.blog', 'blog_collect', 'blog_collect\\', 'blog_', 'blog_collect');
INSERT INTO `code_gen_config` VALUES (11, 'comment_like', 'Kahen', b'0', 'eladmin-system', 'me.zhengjie.modules.blog', 'comment_like', 'comment_like\\', '', 'comment_like');
INSERT INTO `code_gen_config` VALUES (12, 'blog_comment', 'Kahen', b'0', 'eladmin-system', 'me.zhengjie.modules.blog', 'blog_comment', 'blog_comment\\', 'blog_', 'blog_comment');
INSERT INTO `code_gen_config` VALUES (13, 'blog_img', 'Kahen', b'0', 'eladmin-system', 'me.zhengjie.modules.blog', 'blog_img', 'blog_img\\', 'blog_', 'blog_img');
INSERT INTO `code_gen_config` VALUES (14, 'diary_user', 'Kahen', b'1', 'eladmin-system', 'me.zhengjie.modules.blog', 'diary_user', 'diary_user\\', NULL, 'diary_user');

-- ----------------------------
-- Table structure for comment_like
-- ----------------------------
DROP TABLE IF EXISTS `comment_like`;
CREATE TABLE `comment_like`  (
  `like_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
  `comment_id` bigint(0) NULL DEFAULT NULL COMMENT '评论ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '点赞用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`like_id`) USING BTREE,
  INDEX `comment_id`(`comment_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_like
-- ----------------------------

-- ----------------------------
-- Table structure for diary_user
-- ----------------------------
DROP TABLE IF EXISTS `diary_user`;
CREATE TABLE `diary_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uid',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户介绍',
  `followers_count` bigint(0) NOT NULL DEFAULT 0 COMMENT '粉丝数',
  `friends_count` bigint(0) NOT NULL DEFAULT 0 COMMENT '关注数',
  `statuses_count` bigint(0) NOT NULL DEFAULT 0 COMMENT '博客数',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `avatar_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像链接',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码哈希',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diary_user
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_app
-- ----------------------------
DROP TABLE IF EXISTS `mnt_app`;
CREATE TABLE `mnt_app`  (
  `app_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `upload_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传目录',
  `deploy_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部署路径',
  `backup_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备份路径',
  `port` int(0) NULL DEFAULT NULL COMMENT '应用端口',
  `start_script` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '启动脚本',
  `deploy_script` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部署脚本',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`app_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用管理' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_app
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_database
-- ----------------------------
DROP TABLE IF EXISTS `mnt_database`;
CREATE TABLE `mnt_database`  (
  `db_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `jdbc_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jdbc连接',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`db_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库管理' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_database
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_deploy
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy`;
CREATE TABLE `mnt_deploy`  (
  `deploy_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `app_id` bigint(0) NULL DEFAULT NULL COMMENT '应用编号',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`deploy_id`) USING BTREE,
  INDEX `FK6sy157pseoxx4fmcqr1vnvvhy`(`app_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部署管理' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_deploy
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_deploy_history
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_history`;
CREATE TABLE `mnt_deploy_history`  (
  `history_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `app_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用名称',
  `deploy_date` datetime(0) NOT NULL COMMENT '部署日期',
  `deploy_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部署用户',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务器IP',
  `deploy_id` bigint(0) NULL DEFAULT NULL COMMENT '部署编号',
  PRIMARY KEY (`history_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部署历史管理' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_deploy_history
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_deploy_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_server`;
CREATE TABLE `mnt_deploy_server`  (
  `deploy_id` bigint(0) NOT NULL COMMENT '部署ID',
  `server_id` bigint(0) NOT NULL COMMENT '服务ID',
  PRIMARY KEY (`deploy_id`, `server_id`) USING BTREE,
  INDEX `FKeaaha7jew9a02b3bk9ghols53`(`server_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用与服务器关联' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_deploy_server
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_server`;
CREATE TABLE `mnt_server`  (
  `server_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `port` int(0) NULL DEFAULT NULL COMMENT '端口',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`server_id`) USING BTREE,
  INDEX `idx_ip`(`ip`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '服务器管理' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_server
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint(0) NULL DEFAULT NULL COMMENT '上级部门',
  `sub_count` int(0) NULL DEFAULT 0 COMMENT '子部门数目',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `dept_sort` int(0) NULL DEFAULT 999 COMMENT '排序',
  `enabled` bit(1) NOT NULL COMMENT '状态',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE,
  INDEX `inx_pid`(`pid`) USING BTREE,
  INDEX `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (2, 7, 1, '研发部', 3, b'1', 'admin', 'admin', '2019-03-25 09:15:32', '2020-08-02 14:48:47');
INSERT INTO `sys_dept` VALUES (5, 7, 0, '运维部', 4, b'1', 'admin', 'admin', '2019-03-25 09:20:44', '2020-05-17 14:27:27');
INSERT INTO `sys_dept` VALUES (6, 8, 0, '测试部', 6, b'1', 'admin', 'admin', '2019-03-25 09:52:18', '2020-06-08 11:59:21');
INSERT INTO `sys_dept` VALUES (7, NULL, 2, '华南分部', 0, b'1', 'admin', 'admin', '2019-03-25 11:04:50', '2020-06-08 12:08:56');
INSERT INTO `sys_dept` VALUES (8, NULL, 2, '华北分部', 1, b'1', 'admin', 'admin', '2019-03-25 11:04:53', '2020-05-14 12:54:00');
INSERT INTO `sys_dept` VALUES (15, 8, 0, 'UI部门', 7, b'1', 'admin', 'admin', '2020-05-13 22:56:53', '2020-05-14 12:54:13');
INSERT INTO `sys_dept` VALUES (17, 2, 0, '研发一组', 999, b'1', 'admin', 'admin', '2020-08-02 14:49:07', '2020-08-02 14:49:07');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'user_status', '用户状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (4, 'dept_status', '部门状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (5, 'job_status', '岗位状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (6, 'is_original', '是否原创', 'admin', 'admin', '2020-12-06 14:55:38', '2020-12-06 14:55:38');

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
  `detail_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dict_id` bigint(0) NULL DEFAULT NULL COMMENT '字典id',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典标签',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典值',
  `dict_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `FK5tpkputc6d9nboxojdbgnpmyb`(`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典详情' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES (1, 1, '激活', 'true', 1, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (2, 1, '禁用', 'false', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (3, 4, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (4, 4, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (5, 5, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (6, 5, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (7, 6, '原创', '1', 2, 'admin', 'admin', '2020-12-06 14:55:54', '2020-12-06 14:56:26');
INSERT INTO `sys_dict_detail` VALUES (8, 6, '转载', '0', 1, 'admin', 'admin', '2020-12-06 14:56:10', '2020-12-06 14:56:23');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `enabled` bit(1) NOT NULL COMMENT '岗位状态',
  `job_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name`) USING BTREE,
  INDEX `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (8, '人事专员', b'1', 3, NULL, NULL, '2019-03-29 14:52:28', NULL);
INSERT INTO `sys_job` VALUES (10, '产品经理', b'1', 4, NULL, NULL, '2019-03-29 14:55:51', NULL);
INSERT INTO `sys_job` VALUES (11, '全栈开发', b'1', 2, NULL, 'admin', '2019-03-31 13:39:30', '2020-05-05 11:33:43');
INSERT INTO `sys_job` VALUES (12, '软件测试', b'1', 5, NULL, 'admin', '2019-03-31 13:39:43', '2020-05-10 19:56:26');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `request_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(0) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `log_create_time_index`(`create_time`) USING BTREE,
  INDEX `inx_log_type`(`log_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3552 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (3537, '新增菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"web\",\"updateTime\":1607161296991,\"title\":\"动态管理\",\"type\":0,\"subCount\":0,\"path\":\"blog\",\"createBy\":\"admin\",\"createTime\":1607161296991,\"iFrame\":false,\"id\":118,\"menuSort\":999}', '172.24.96.1', 379, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 17:41:37');
INSERT INTO `sys_log` VALUES (3538, '修改角色菜单', 'INFO', 'me.zhengjie.modules.system.rest.RoleController.updateMenu()', '{\"level\":3,\"dataScope\":\"本级\",\"id\":1,\"menus\":[{\"subCount\":0,\"id\":97,\"menuSort\":999},{\"subCount\":0,\"id\":98,\"menuSort\":999},{\"subCount\":0,\"id\":102,\"menuSort\":999},{\"subCount\":0,\"id\":103,\"menuSort\":999},{\"subCount\":0,\"id\":104,\"menuSort\":999},{\"subCount\":0,\"id\":105,\"menuSort\":999},{\"subCount\":0,\"id\":106,\"menuSort\":999},{\"subCount\":0,\"id\":107,\"menuSort\":999},{\"subCount\":0,\"id\":108,\"menuSort\":999},{\"subCount\":0,\"id\":109,\"menuSort\":999},{\"subCount\":0,\"id\":110,\"menuSort\":999},{\"subCount\":0,\"id\":111,\"menuSort\":999},{\"subCount\":0,\"id\":112,\"menuSort\":999},{\"subCount\":0,\"id\":113,\"menuSort\":999},{\"subCount\":0,\"id\":114,\"menuSort\":999},{\"subCount\":0,\"id\":116,\"menuSort\":999},{\"subCount\":0,\"id\":118,\"menuSort\":999},{\"subCount\":0,\"id\":1,\"menuSort\":999},{\"subCount\":0,\"id\":2,\"menuSort\":999},{\"subCount\":0,\"id\":3,\"menuSort\":999},{\"subCount\":0,\"id\":5,\"menuSort\":999},{\"subCount\":0,\"id\":6,\"menuSort\":999},{\"subCount\":0,\"id\":7,\"menuSort\":999},{\"subCount\":0,\"id\":9,\"menuSort\":999},{\"subCount\":0,\"id\":10,\"menuSort\":999},{\"subCount\":0,\"id\":11,\"menuSort\":999},{\"subCount\":0,\"id\":14,\"menuSort\":999},{\"subCount\":0,\"id\":15,\"menuSort\":999},{\"subCount\":0,\"id\":18,\"menuSort\":999},{\"subCount\":0,\"id\":19,\"menuSort\":999},{\"subCount\":0,\"id\":21,\"menuSort\":999},{\"subCount\":0,\"id\":22,\"menuSort\":999},{\"subCount\":0,\"id\":23,\"menuSort\":999},{\"subCount\":0,\"id\":24,\"menuSort\":999},{\"subCount\":0,\"id\":27,\"menuSort\":999},{\"subCount\":0,\"id\":28,\"menuSort\":999},{\"subCount\":0,\"id\":30,\"menuSort\":999},{\"subCount\":0,\"id\":32,\"menuSort\":999},{\"subCount\":0,\"id\":33,\"menuSort\":999},{\"subCount\":0,\"id\":34,\"menuSort\":999},{\"subCount\":0,\"id\":35,\"menuSort\":999},{\"subCount\":0,\"id\":36,\"menuSort\":999},{\"subCount\":0,\"id\":37,\"menuSort\":999},{\"subCount\":0,\"id\":38,\"menuSort\":999},{\"subCount\":0,\"id\":39,\"menuSort\":999},{\"subCount\":0,\"id\":41,\"menuSort\":999},{\"subCount\":0,\"id\":44,\"menuSort\":999},{\"subCount\":0,\"id\":45,\"menuSort\":999},{\"subCount\":0,\"id\":46,\"menuSort\":999},{\"subCount\":0,\"id\":48,\"menuSort\":999},{\"subCount\":0,\"id\":49,\"menuSort\":999},{\"subCount\":0,\"id\":50,\"menuSort\":999},{\"subCount\":0,\"id\":52,\"menuSort\":999},{\"subCount\":0,\"id\":53,\"menuSort\":999},{\"subCount\":0,\"id\":54,\"menuSort\":999},{\"subCount\":0,\"id\":56,\"menuSort\":999},{\"subCount\":0,\"id\":57,\"menuSort\":999},{\"subCount\":0,\"id\":58,\"menuSort\":999},{\"subCount\":0,\"id\":60,\"menuSort\":999},{\"subCount\":0,\"id\":61,\"menuSort\":999},{\"subCount\":0,\"id\":62,\"menuSort\":999},{\"subCount\":0,\"id\":64,\"menuSort\":999},{\"subCount\":0,\"id\":65,\"menuSort\":999},{\"subCount\":0,\"id\":66,\"menuSort\":999},{\"subCount\":0,\"id\":73,\"menuSort\":999},{\"subCount\":0,\"id\":74,\"menuSort\":999},{\"subCount\":0,\"id\":75,\"menuSort\":999},{\"subCount\":0,\"id\":77,\"menuSort\":999},{\"subCount\":0,\"id\":78,\"menuSort\":999},{\"subCount\":0,\"id\":79,\"menuSort\":999},{\"subCount\":0,\"id\":80,\"menuSort\":999},{\"subCount\":0,\"id\":82,\"menuSort\":999},{\"subCount\":0,\"id\":83,\"menuSort\":999},{\"subCount\":0,\"id\":90,\"menuSort\":999},{\"subCount\":0,\"id\":92,\"menuSort\":999},{\"subCount\":0,\"id\":93,\"menuSort\":999},{\"subCount\":0,\"id\":94,\"menuSort\":999}]}', '172.24.96.1', 253, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 17:41:56');
INSERT INTO `sys_log` VALUES (3539, '新增菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"fwb\",\"pid\":118,\"updateTime\":1607173432459,\"title\":\"博客管理\",\"type\":1,\"subCount\":0,\"path\":\"blog\",\"component\":\"api/blog\",\"createBy\":\"admin\",\"createTime\":1607173432459,\"iFrame\":false,\"id\":119,\"componentName\":\"Blog\",\"menuSort\":999}', '172.24.96.1', 518, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:03:53');
INSERT INTO `sys_log` VALUES (3540, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"fwb\",\"pid\":118,\"updateTime\":1607173432000,\"title\":\"博客管理\",\"type\":1,\"subCount\":0,\"path\":\"blog\",\"component\":\"api/blog/index\",\"createBy\":\"admin\",\"createTime\":1607173432000,\"iFrame\":false,\"id\":119,\"componentName\":\"Blog\",\"menuSort\":999}', '172.24.96.1', 236, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:06:26');
INSERT INTO `sys_log` VALUES (3541, '修改角色菜单', 'INFO', 'me.zhengjie.modules.system.rest.RoleController.updateMenu()', '{\"level\":3,\"dataScope\":\"本级\",\"id\":1,\"menus\":[{\"subCount\":0,\"id\":97,\"menuSort\":999},{\"subCount\":0,\"id\":98,\"menuSort\":999},{\"subCount\":0,\"id\":102,\"menuSort\":999},{\"subCount\":0,\"id\":103,\"menuSort\":999},{\"subCount\":0,\"id\":104,\"menuSort\":999},{\"subCount\":0,\"id\":105,\"menuSort\":999},{\"subCount\":0,\"id\":106,\"menuSort\":999},{\"subCount\":0,\"id\":107,\"menuSort\":999},{\"subCount\":0,\"id\":108,\"menuSort\":999},{\"subCount\":0,\"id\":109,\"menuSort\":999},{\"subCount\":0,\"id\":110,\"menuSort\":999},{\"subCount\":0,\"id\":111,\"menuSort\":999},{\"subCount\":0,\"id\":112,\"menuSort\":999},{\"subCount\":0,\"id\":113,\"menuSort\":999},{\"subCount\":0,\"id\":114,\"menuSort\":999},{\"subCount\":0,\"id\":116,\"menuSort\":999},{\"subCount\":0,\"id\":118,\"menuSort\":999},{\"subCount\":0,\"id\":119,\"menuSort\":999},{\"subCount\":0,\"id\":1,\"menuSort\":999},{\"subCount\":0,\"id\":2,\"menuSort\":999},{\"subCount\":0,\"id\":3,\"menuSort\":999},{\"subCount\":0,\"id\":5,\"menuSort\":999},{\"subCount\":0,\"id\":6,\"menuSort\":999},{\"subCount\":0,\"id\":7,\"menuSort\":999},{\"subCount\":0,\"id\":9,\"menuSort\":999},{\"subCount\":0,\"id\":10,\"menuSort\":999},{\"subCount\":0,\"id\":11,\"menuSort\":999},{\"subCount\":0,\"id\":14,\"menuSort\":999},{\"subCount\":0,\"id\":15,\"menuSort\":999},{\"subCount\":0,\"id\":18,\"menuSort\":999},{\"subCount\":0,\"id\":19,\"menuSort\":999},{\"subCount\":0,\"id\":21,\"menuSort\":999},{\"subCount\":0,\"id\":22,\"menuSort\":999},{\"subCount\":0,\"id\":23,\"menuSort\":999},{\"subCount\":0,\"id\":24,\"menuSort\":999},{\"subCount\":0,\"id\":27,\"menuSort\":999},{\"subCount\":0,\"id\":28,\"menuSort\":999},{\"subCount\":0,\"id\":30,\"menuSort\":999},{\"subCount\":0,\"id\":32,\"menuSort\":999},{\"subCount\":0,\"id\":33,\"menuSort\":999},{\"subCount\":0,\"id\":34,\"menuSort\":999},{\"subCount\":0,\"id\":35,\"menuSort\":999},{\"subCount\":0,\"id\":36,\"menuSort\":999},{\"subCount\":0,\"id\":37,\"menuSort\":999},{\"subCount\":0,\"id\":38,\"menuSort\":999},{\"subCount\":0,\"id\":39,\"menuSort\":999},{\"subCount\":0,\"id\":41,\"menuSort\":999},{\"subCount\":0,\"id\":44,\"menuSort\":999},{\"subCount\":0,\"id\":45,\"menuSort\":999},{\"subCount\":0,\"id\":46,\"menuSort\":999},{\"subCount\":0,\"id\":48,\"menuSort\":999},{\"subCount\":0,\"id\":49,\"menuSort\":999},{\"subCount\":0,\"id\":50,\"menuSort\":999},{\"subCount\":0,\"id\":52,\"menuSort\":999},{\"subCount\":0,\"id\":53,\"menuSort\":999},{\"subCount\":0,\"id\":54,\"menuSort\":999},{\"subCount\":0,\"id\":56,\"menuSort\":999},{\"subCount\":0,\"id\":57,\"menuSort\":999},{\"subCount\":0,\"id\":58,\"menuSort\":999},{\"subCount\":0,\"id\":60,\"menuSort\":999},{\"subCount\":0,\"id\":61,\"menuSort\":999},{\"subCount\":0,\"id\":62,\"menuSort\":999},{\"subCount\":0,\"id\":64,\"menuSort\":999},{\"subCount\":0,\"id\":65,\"menuSort\":999},{\"subCount\":0,\"id\":66,\"menuSort\":999},{\"subCount\":0,\"id\":73,\"menuSort\":999},{\"subCount\":0,\"id\":74,\"menuSort\":999},{\"subCount\":0,\"id\":75,\"menuSort\":999},{\"subCount\":0,\"id\":77,\"menuSort\":999},{\"subCount\":0,\"id\":78,\"menuSort\":999},{\"subCount\":0,\"id\":79,\"menuSort\":999},{\"subCount\":0,\"id\":80,\"menuSort\":999},{\"subCount\":0,\"id\":82,\"menuSort\":999},{\"subCount\":0,\"id\":83,\"menuSort\":999},{\"subCount\":0,\"id\":90,\"menuSort\":999},{\"subCount\":0,\"id\":92,\"menuSort\":999},{\"subCount\":0,\"id\":93,\"menuSort\":999},{\"subCount\":0,\"id\":94,\"menuSort\":999}]}', '172.24.96.1', 290, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:06:57');
INSERT INTO `sys_log` VALUES (3542, '修改角色菜单', 'INFO', 'me.zhengjie.modules.system.rest.RoleController.updateMenu()', '{\"level\":3,\"dataScope\":\"本级\",\"id\":1,\"menus\":[{\"subCount\":0,\"id\":97,\"menuSort\":999},{\"subCount\":0,\"id\":98,\"menuSort\":999},{\"subCount\":0,\"id\":102,\"menuSort\":999},{\"subCount\":0,\"id\":103,\"menuSort\":999},{\"subCount\":0,\"id\":104,\"menuSort\":999},{\"subCount\":0,\"id\":105,\"menuSort\":999},{\"subCount\":0,\"id\":106,\"menuSort\":999},{\"subCount\":0,\"id\":107,\"menuSort\":999},{\"subCount\":0,\"id\":108,\"menuSort\":999},{\"subCount\":0,\"id\":109,\"menuSort\":999},{\"subCount\":0,\"id\":110,\"menuSort\":999},{\"subCount\":0,\"id\":111,\"menuSort\":999},{\"subCount\":0,\"id\":112,\"menuSort\":999},{\"subCount\":0,\"id\":113,\"menuSort\":999},{\"subCount\":0,\"id\":114,\"menuSort\":999},{\"subCount\":0,\"id\":116,\"menuSort\":999},{\"subCount\":0,\"id\":118,\"menuSort\":999},{\"subCount\":0,\"id\":119,\"menuSort\":999},{\"subCount\":0,\"id\":1,\"menuSort\":999},{\"subCount\":0,\"id\":2,\"menuSort\":999},{\"subCount\":0,\"id\":3,\"menuSort\":999},{\"subCount\":0,\"id\":5,\"menuSort\":999},{\"subCount\":0,\"id\":6,\"menuSort\":999},{\"subCount\":0,\"id\":7,\"menuSort\":999},{\"subCount\":0,\"id\":9,\"menuSort\":999},{\"subCount\":0,\"id\":10,\"menuSort\":999},{\"subCount\":0,\"id\":11,\"menuSort\":999},{\"subCount\":0,\"id\":14,\"menuSort\":999},{\"subCount\":0,\"id\":15,\"menuSort\":999},{\"subCount\":0,\"id\":18,\"menuSort\":999},{\"subCount\":0,\"id\":19,\"menuSort\":999},{\"subCount\":0,\"id\":21,\"menuSort\":999},{\"subCount\":0,\"id\":22,\"menuSort\":999},{\"subCount\":0,\"id\":23,\"menuSort\":999},{\"subCount\":0,\"id\":24,\"menuSort\":999},{\"subCount\":0,\"id\":27,\"menuSort\":999},{\"subCount\":0,\"id\":28,\"menuSort\":999},{\"subCount\":0,\"id\":30,\"menuSort\":999},{\"subCount\":0,\"id\":32,\"menuSort\":999},{\"subCount\":0,\"id\":33,\"menuSort\":999},{\"subCount\":0,\"id\":34,\"menuSort\":999},{\"subCount\":0,\"id\":35,\"menuSort\":999},{\"subCount\":0,\"id\":36,\"menuSort\":999},{\"subCount\":0,\"id\":37,\"menuSort\":999},{\"subCount\":0,\"id\":38,\"menuSort\":999},{\"subCount\":0,\"id\":39,\"menuSort\":999},{\"subCount\":0,\"id\":41,\"menuSort\":999},{\"subCount\":0,\"id\":44,\"menuSort\":999},{\"subCount\":0,\"id\":45,\"menuSort\":999},{\"subCount\":0,\"id\":46,\"menuSort\":999},{\"subCount\":0,\"id\":48,\"menuSort\":999},{\"subCount\":0,\"id\":49,\"menuSort\":999},{\"subCount\":0,\"id\":50,\"menuSort\":999},{\"subCount\":0,\"id\":52,\"menuSort\":999},{\"subCount\":0,\"id\":53,\"menuSort\":999},{\"subCount\":0,\"id\":54,\"menuSort\":999},{\"subCount\":0,\"id\":56,\"menuSort\":999},{\"subCount\":0,\"id\":57,\"menuSort\":999},{\"subCount\":0,\"id\":58,\"menuSort\":999},{\"subCount\":0,\"id\":60,\"menuSort\":999},{\"subCount\":0,\"id\":61,\"menuSort\":999},{\"subCount\":0,\"id\":62,\"menuSort\":999},{\"subCount\":0,\"id\":64,\"menuSort\":999},{\"subCount\":0,\"id\":65,\"menuSort\":999},{\"subCount\":0,\"id\":66,\"menuSort\":999},{\"subCount\":0,\"id\":73,\"menuSort\":999},{\"subCount\":0,\"id\":74,\"menuSort\":999},{\"subCount\":0,\"id\":75,\"menuSort\":999},{\"subCount\":0,\"id\":77,\"menuSort\":999},{\"subCount\":0,\"id\":78,\"menuSort\":999},{\"subCount\":0,\"id\":79,\"menuSort\":999},{\"subCount\":0,\"id\":80,\"menuSort\":999},{\"subCount\":0,\"id\":82,\"menuSort\":999},{\"subCount\":0,\"id\":83,\"menuSort\":999},{\"subCount\":0,\"id\":90,\"menuSort\":999},{\"subCount\":0,\"id\":92,\"menuSort\":999},{\"subCount\":0,\"id\":93,\"menuSort\":999},{\"subCount\":0,\"id\":94,\"menuSort\":999}]}', '172.24.96.1', 72, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:26:27');
INSERT INTO `sys_log` VALUES (3543, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"web\",\"updateTime\":1607161297000,\"title\":\"动态管理\",\"type\":0,\"subCount\":1,\"path\":\"blog\",\"createBy\":\"admin\",\"createTime\":1607161297000,\"iFrame\":false,\"id\":118,\"menuSort\":999}', '172.24.96.1', 41, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:26:57');
INSERT INTO `sys_log` VALUES (3544, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"fwb\",\"pid\":118,\"updateTime\":1607173585000,\"title\":\"博客管理\",\"type\":1,\"subCount\":0,\"path\":\"blog\",\"component\":\"api/blog/index\",\"createBy\":\"admin\",\"createTime\":1607173432000,\"iFrame\":false,\"id\":119,\"componentName\":\"Blog\",\"menuSort\":999}', '172.24.96.1', 48, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:27:09');
INSERT INTO `sys_log` VALUES (3545, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"fwb\",\"pid\":118,\"updateTime\":1607173585000,\"title\":\"博客管理\",\"type\":1,\"subCount\":0,\"path\":\"blog\",\"component\":\"api/blog/blog/index\",\"createBy\":\"admin\",\"createTime\":1607173432000,\"iFrame\":false,\"id\":119,\"componentName\":\"Blog\",\"menuSort\":999}', '172.24.96.1', 62, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:29:21');
INSERT INTO `sys_log` VALUES (3546, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"fwb\",\"pid\":118,\"updateTime\":1607174961000,\"title\":\"博客管理\",\"type\":1,\"subCount\":0,\"path\":\"blog\",\"component\":\"api/blog_comment/index\",\"createBy\":\"admin\",\"createTime\":1607173432000,\"iFrame\":false,\"id\":119,\"componentName\":\"Blog\",\"menuSort\":999}', '172.24.96.1', 50, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:30:00');
INSERT INTO `sys_log` VALUES (3547, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"fwb\",\"pid\":118,\"updateTime\":1607175000000,\"title\":\"博客管理\",\"type\":1,\"subCount\":0,\"path\":\"blog\",\"component\":\"blog/index\",\"createBy\":\"admin\",\"createTime\":1607173432000,\"iFrame\":false,\"id\":119,\"componentName\":\"Blog\",\"menuSort\":999}', '172.24.96.1', 434, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:39:44');
INSERT INTO `sys_log` VALUES (3548, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"fwb\",\"pid\":118,\"updateTime\":1607175583000,\"title\":\"博客管理\",\"type\":1,\"subCount\":0,\"path\":\"blog/blog\",\"component\":\"blog/blog/index\",\"createBy\":\"admin\",\"createTime\":1607173432000,\"iFrame\":false,\"id\":119,\"componentName\":\"Blog\",\"menuSort\":999}', '172.24.96.1', 56, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:45:03');
INSERT INTO `sys_log` VALUES (3549, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 20, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:45:24');
INSERT INTO `sys_log` VALUES (3550, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 19, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-05 21:46:39');
INSERT INTO `sys_log` VALUES (3551, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 98, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 14:51:09');
INSERT INTO `sys_log` VALUES (3552, '新增字典', 'INFO', 'me.zhengjie.modules.system.rest.DictController.create()', '{\"updatedBy\":\"admin\",\"description\":\"是否原创\",\"dictDetails\":[],\"updateTime\":1607237738376,\"createBy\":\"admin\",\"createTime\":1607237738376,\"name\":\"is_original\",\"id\":6}', '172.24.96.1', 461, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 14:55:38');
INSERT INTO `sys_log` VALUES (3553, '新增字典详情', 'INFO', 'me.zhengjie.modules.system.rest.DictDetailController.create()', '{\"updatedBy\":\"admin\",\"dictSort\":999,\"updateTime\":1607237753703,\"label\":\"原创\",\"createBy\":\"admin\",\"createTime\":1607237753703,\"dict\":{\"id\":6},\"id\":7,\"value\":\"1\"}', '172.24.96.1', 25, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 14:55:54');
INSERT INTO `sys_log` VALUES (3554, '新增字典详情', 'INFO', 'me.zhengjie.modules.system.rest.DictDetailController.create()', '{\"updatedBy\":\"admin\",\"dictSort\":999,\"updateTime\":1607237769726,\"label\":\"转载\",\"createBy\":\"admin\",\"createTime\":1607237769726,\"dict\":{\"id\":6},\"id\":8,\"value\":\"0\"}', '172.24.96.1', 20, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 14:56:10');
INSERT INTO `sys_log` VALUES (3555, '修改字典详情', 'INFO', 'me.zhengjie.modules.system.rest.DictDetailController.update()', '{\"updatedBy\":\"admin\",\"dictSort\":1,\"updateTime\":1607237770000,\"label\":\"转载\",\"createBy\":\"admin\",\"createTime\":1607237770000,\"dict\":{\"id\":6},\"id\":8,\"value\":\"0\"}', '172.24.96.1', 85, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 14:56:23');
INSERT INTO `sys_log` VALUES (3556, '修改字典详情', 'INFO', 'me.zhengjie.modules.system.rest.DictDetailController.update()', '{\"updatedBy\":\"admin\",\"dictSort\":2,\"updateTime\":1607237754000,\"label\":\"原创\",\"createBy\":\"admin\",\"createTime\":1607237754000,\"dict\":{\"id\":6},\"id\":7,\"value\":\"1\"}', '172.24.96.1', 21, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 14:56:26');
INSERT INTO `sys_log` VALUES (3557, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 12, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:10:02');
INSERT INTO `sys_log` VALUES (3558, '修改blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.update()', '{\"publishTime\":1607175981000,\"userId\":1,\"content\":\"1\",\"isOriginal\":\"1\",\"videoUrl\":\"1\",\"createTime\":1607175988000,\"blogId\":1}', '172.24.96.1', 22, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:10:12');
INSERT INTO `sys_log` VALUES (3559, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 6, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:10:13');
INSERT INTO `sys_log` VALUES (3560, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"message\",\"updateTime\":1607161297000,\"title\":\"动态管理\",\"type\":0,\"subCount\":1,\"path\":\"blog\",\"createBy\":\"admin\",\"createTime\":1607161297000,\"iFrame\":false,\"id\":118,\"menuSort\":999}', '172.24.96.1', 146, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:11:30');
INSERT INTO `sys_log` VALUES (3561, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"message\",\"updateTime\":1607238689000,\"title\":\"动态管理\",\"type\":0,\"subCount\":1,\"path\":\"blog\",\"createBy\":\"admin\",\"createTime\":1607161297000,\"iFrame\":false,\"id\":118,\"menuSort\":2}', '172.24.96.1', 32, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:11:53');
INSERT INTO `sys_log` VALUES (3562, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 6, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:12:01');
INSERT INTO `sys_log` VALUES (3563, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"fwb\",\"pid\":118,\"updateTime\":1607175903000,\"title\":\"博客管理\",\"type\":1,\"subCount\":0,\"path\":\"blog/blog\",\"component\":\"blog/blog/index\",\"createBy\":\"admin\",\"createTime\":1607173432000,\"iFrame\":false,\"id\":119,\"componentName\":\"Blog\",\"menuSort\":1}', '172.24.96.1', 168, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:12:47');
INSERT INTO `sys_log` VALUES (3564, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 10, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:14:39');
INSERT INTO `sys_log` VALUES (3565, '新增菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"source\",\"pid\":118,\"updateTime\":1607239009187,\"title\":\"收藏管理\",\"type\":1,\"subCount\":0,\"path\":\"blog/blog_collect\",\"component\":\"blog/blog_collect/index\",\"createBy\":\"admin\",\"createTime\":1607239009187,\"iFrame\":false,\"id\":120,\"componentName\":\"Collect\",\"menuSort\":2}', '172.24.96.1', 18, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:16:49');
INSERT INTO `sys_log` VALUES (3566, '修改角色菜单', 'INFO', 'me.zhengjie.modules.system.rest.RoleController.updateMenu()', '{\"level\":3,\"dataScope\":\"本级\",\"id\":1,\"menus\":[{\"subCount\":0,\"id\":97,\"menuSort\":999},{\"subCount\":0,\"id\":98,\"menuSort\":999},{\"subCount\":0,\"id\":102,\"menuSort\":999},{\"subCount\":0,\"id\":103,\"menuSort\":999},{\"subCount\":0,\"id\":104,\"menuSort\":999},{\"subCount\":0,\"id\":105,\"menuSort\":999},{\"subCount\":0,\"id\":106,\"menuSort\":999},{\"subCount\":0,\"id\":107,\"menuSort\":999},{\"subCount\":0,\"id\":108,\"menuSort\":999},{\"subCount\":0,\"id\":109,\"menuSort\":999},{\"subCount\":0,\"id\":110,\"menuSort\":999},{\"subCount\":0,\"id\":111,\"menuSort\":999},{\"subCount\":0,\"id\":112,\"menuSort\":999},{\"subCount\":0,\"id\":113,\"menuSort\":999},{\"subCount\":0,\"id\":114,\"menuSort\":999},{\"subCount\":0,\"id\":116,\"menuSort\":999},{\"subCount\":0,\"id\":118,\"menuSort\":999},{\"subCount\":0,\"id\":119,\"menuSort\":999},{\"subCount\":0,\"id\":120,\"menuSort\":999},{\"subCount\":0,\"id\":1,\"menuSort\":999},{\"subCount\":0,\"id\":2,\"menuSort\":999},{\"subCount\":0,\"id\":3,\"menuSort\":999},{\"subCount\":0,\"id\":5,\"menuSort\":999},{\"subCount\":0,\"id\":6,\"menuSort\":999},{\"subCount\":0,\"id\":7,\"menuSort\":999},{\"subCount\":0,\"id\":9,\"menuSort\":999},{\"subCount\":0,\"id\":10,\"menuSort\":999},{\"subCount\":0,\"id\":11,\"menuSort\":999},{\"subCount\":0,\"id\":14,\"menuSort\":999},{\"subCount\":0,\"id\":15,\"menuSort\":999},{\"subCount\":0,\"id\":18,\"menuSort\":999},{\"subCount\":0,\"id\":19,\"menuSort\":999},{\"subCount\":0,\"id\":21,\"menuSort\":999},{\"subCount\":0,\"id\":22,\"menuSort\":999},{\"subCount\":0,\"id\":23,\"menuSort\":999},{\"subCount\":0,\"id\":24,\"menuSort\":999},{\"subCount\":0,\"id\":27,\"menuSort\":999},{\"subCount\":0,\"id\":28,\"menuSort\":999},{\"subCount\":0,\"id\":30,\"menuSort\":999},{\"subCount\":0,\"id\":32,\"menuSort\":999},{\"subCount\":0,\"id\":33,\"menuSort\":999},{\"subCount\":0,\"id\":34,\"menuSort\":999},{\"subCount\":0,\"id\":35,\"menuSort\":999},{\"subCount\":0,\"id\":36,\"menuSort\":999},{\"subCount\":0,\"id\":37,\"menuSort\":999},{\"subCount\":0,\"id\":38,\"menuSort\":999},{\"subCount\":0,\"id\":39,\"menuSort\":999},{\"subCount\":0,\"id\":41,\"menuSort\":999},{\"subCount\":0,\"id\":44,\"menuSort\":999},{\"subCount\":0,\"id\":45,\"menuSort\":999},{\"subCount\":0,\"id\":46,\"menuSort\":999},{\"subCount\":0,\"id\":48,\"menuSort\":999},{\"subCount\":0,\"id\":49,\"menuSort\":999},{\"subCount\":0,\"id\":50,\"menuSort\":999},{\"subCount\":0,\"id\":52,\"menuSort\":999},{\"subCount\":0,\"id\":53,\"menuSort\":999},{\"subCount\":0,\"id\":54,\"menuSort\":999},{\"subCount\":0,\"id\":56,\"menuSort\":999},{\"subCount\":0,\"id\":57,\"menuSort\":999},{\"subCount\":0,\"id\":58,\"menuSort\":999},{\"subCount\":0,\"id\":60,\"menuSort\":999},{\"subCount\":0,\"id\":61,\"menuSort\":999},{\"subCount\":0,\"id\":62,\"menuSort\":999},{\"subCount\":0,\"id\":64,\"menuSort\":999},{\"subCount\":0,\"id\":65,\"menuSort\":999},{\"subCount\":0,\"id\":66,\"menuSort\":999},{\"subCount\":0,\"id\":73,\"menuSort\":999},{\"subCount\":0,\"id\":74,\"menuSort\":999},{\"subCount\":0,\"id\":75,\"menuSort\":999},{\"subCount\":0,\"id\":77,\"menuSort\":999},{\"subCount\":0,\"id\":78,\"menuSort\":999},{\"subCount\":0,\"id\":79,\"menuSort\":999},{\"subCount\":0,\"id\":80,\"menuSort\":999},{\"subCount\":0,\"id\":82,\"menuSort\":999},{\"subCount\":0,\"id\":83,\"menuSort\":999},{\"subCount\":0,\"id\":90,\"menuSort\":999},{\"subCount\":0,\"id\":92,\"menuSort\":999},{\"subCount\":0,\"id\":93,\"menuSort\":999},{\"subCount\":0,\"id\":94,\"menuSort\":999}]}', '172.24.96.1', 64, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:17:12');
INSERT INTO `sys_log` VALUES (3567, '查询blog_collect', 'INFO', 'me.zhengjie.modules.blog.rest.CollectController.query()', '', '172.24.96.1', 29, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:17:24');
INSERT INTO `sys_log` VALUES (3568, '新增blog_collect', 'INFO', 'me.zhengjie.modules.blog.rest.CollectController.create()', '{\"collectId\":1,\"userId\":1,\"createTime\":1608048000000,\"blogId\":1}', '172.24.96.1', 18, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:17:42');
INSERT INTO `sys_log` VALUES (3569, '查询blog_collect', 'INFO', 'me.zhengjie.modules.blog.rest.CollectController.query()', '', '172.24.96.1', 4, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:17:43');
INSERT INTO `sys_log` VALUES (3570, '查询blog_collect', 'INFO', 'me.zhengjie.modules.blog.rest.CollectController.query()', '', '172.24.96.1', 5, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:24:02');
INSERT INTO `sys_log` VALUES (3571, '新增菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"message\",\"pid\":118,\"updateTime\":1607239522462,\"title\":\"评论\",\"type\":1,\"subCount\":0,\"path\":\"blog/blog_comment\",\"component\":\"blog/blog_comment/index\",\"createBy\":\"admin\",\"createTime\":1607239522462,\"iFrame\":false,\"id\":121,\"componentName\":\"Comment\",\"menuSort\":3}', '172.24.96.1', 17, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:25:22');
INSERT INTO `sys_log` VALUES (3572, '新增菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"image\",\"pid\":118,\"updateTime\":1607239594059,\"title\":\"图片管理\",\"type\":1,\"subCount\":0,\"path\":\"blog/blog_img\",\"component\":\"blog/blog_img/index\",\"createBy\":\"admin\",\"createTime\":1607239594059,\"iFrame\":false,\"id\":122,\"componentName\":\"Img\",\"menuSort\":4}', '172.24.96.1', 30, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:26:34');
INSERT INTO `sys_log` VALUES (3573, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"message\",\"pid\":118,\"updateTime\":1607239522000,\"title\":\"评论管理\",\"type\":1,\"subCount\":0,\"path\":\"blog/blog_comment\",\"component\":\"blog/blog_comment/index\",\"createBy\":\"admin\",\"createTime\":1607239522000,\"iFrame\":false,\"id\":121,\"componentName\":\"Comment\",\"menuSort\":3}', '172.24.96.1', 32, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:26:47');
INSERT INTO `sys_log` VALUES (3574, '新增菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"people\",\"pid\":118,\"updateTime\":1607239705560,\"title\":\"点赞管理\",\"type\":1,\"subCount\":0,\"path\":\"blog/blog_like\",\"component\":\"blog/blog/index\",\"createBy\":\"admin\",\"createTime\":1607239705560,\"iFrame\":false,\"id\":123,\"componentName\":\"Like\",\"menuSort\":5}', '172.24.96.1', 20, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:28:26');
INSERT INTO `sys_log` VALUES (3575, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 8, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:28:49');
INSERT INTO `sys_log` VALUES (3576, '新增菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"link\",\"pid\":118,\"updateTime\":1607239810606,\"title\":\"转发管理\",\"type\":1,\"subCount\":0,\"path\":\"blog/blog_repost\",\"component\":\"blog/blog_repost/index\",\"createBy\":\"admin\",\"createTime\":1607239810606,\"iFrame\":false,\"id\":124,\"componentName\":\"Repost\",\"menuSort\":6}', '172.24.96.1', 20, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:30:11');
INSERT INTO `sys_log` VALUES (3577, '新增菜单', 'ERROR', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"hidden\":false,\"icon\":\"nested\",\"pid\":118,\"title\":\"评论点赞\",\"type\":1,\"subCount\":0,\"path\":\"blog/comment_list\",\"component\":\"blog/comment_like/index\",\"iFrame\":false,\"componentName\":\"Like\",\"menuSort\":7}', '172.24.96.1', 59, 'admin', '内网IP', 'Chrome 8', 'me.zhengjie.exception.EntityExistException: Menu with componentName Like existed\r\n	at me.zhengjie.modules.system.service.impl.MenuServiceImpl.create(MenuServiceImpl.java:116)\r\n	at me.zhengjie.modules.system.service.impl.MenuServiceImpl$$FastClassBySpringCGLIB$$a0cfbc77.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at me.zhengjie.modules.system.service.impl.MenuServiceImpl$$EnhancerBySpringCGLIB$$b8809d4a.create(<generated>)\r\n	at me.zhengjie.modules.system.rest.MenuController.create(MenuController.java:121)\r\n	at me.zhengjie.modules.system.rest.MenuController$$FastClassBySpringCGLIB$$158b4bdf.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n	at me.zhengjie.aspect.LogAspect.logAround(LogAspect.java:69)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor345.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor.invoke(MethodSecurityInterceptor.java:69)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at me.zhengjie.modules.system.rest.MenuController$$EnhancerBySpringCGLIB$$729c7305.create(<generated>)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:215)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:142)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:800)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:998)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:901)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:660)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:875)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:101)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:124)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:127)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:91)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:170)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at me.zhengjie.modules.security.security.TokenFilter.doFilter(TokenFilter.java:91)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:66)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:770)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:834)\r\n', '2020-12-06 15:32:17');
INSERT INTO `sys_log` VALUES (3578, '新增菜单', 'ERROR', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"hidden\":false,\"icon\":\"nested\",\"pid\":118,\"title\":\"评论点赞\",\"type\":1,\"subCount\":0,\"path\":\"blog/comment_list\",\"component\":\"blog/comment_like/index\",\"iFrame\":false,\"componentName\":\"Like\",\"menuSort\":7}', '172.24.96.1', 5, 'admin', '内网IP', 'Chrome 8', 'me.zhengjie.exception.EntityExistException: Menu with componentName Like existed\r\n	at me.zhengjie.modules.system.service.impl.MenuServiceImpl.create(MenuServiceImpl.java:116)\r\n	at me.zhengjie.modules.system.service.impl.MenuServiceImpl$$FastClassBySpringCGLIB$$a0cfbc77.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at me.zhengjie.modules.system.service.impl.MenuServiceImpl$$EnhancerBySpringCGLIB$$b8809d4a.create(<generated>)\r\n	at me.zhengjie.modules.system.rest.MenuController.create(MenuController.java:121)\r\n	at me.zhengjie.modules.system.rest.MenuController$$FastClassBySpringCGLIB$$158b4bdf.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n	at me.zhengjie.aspect.LogAspect.logAround(LogAspect.java:69)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor345.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor.invoke(MethodSecurityInterceptor.java:69)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at me.zhengjie.modules.system.rest.MenuController$$EnhancerBySpringCGLIB$$729c7305.create(<generated>)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:215)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:142)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:800)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:998)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:901)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:660)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:875)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:101)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:124)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:127)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:91)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:170)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at me.zhengjie.modules.security.security.TokenFilter.doFilter(TokenFilter.java:91)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:66)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:770)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:834)\r\n', '2020-12-06 15:32:20');
INSERT INTO `sys_log` VALUES (3579, '新增菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"nested\",\"pid\":118,\"updateTime\":1607239967563,\"title\":\"评论点赞\",\"type\":1,\"subCount\":0,\"path\":\"blog/comment_list\",\"component\":\"blog/comment_like/index\",\"createBy\":\"admin\",\"createTime\":1607239967563,\"iFrame\":false,\"id\":125,\"componentName\":\"CommentLike\",\"menuSort\":7}', '172.24.96.1', 69, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:32:48');
INSERT INTO `sys_log` VALUES (3580, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 7, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:00');
INSERT INTO `sys_log` VALUES (3581, '修改角色菜单', 'INFO', 'me.zhengjie.modules.system.rest.RoleController.updateMenu()', '{\"level\":3,\"dataScope\":\"本级\",\"id\":1,\"menus\":[{\"subCount\":0,\"id\":97,\"menuSort\":999},{\"subCount\":0,\"id\":98,\"menuSort\":999},{\"subCount\":0,\"id\":102,\"menuSort\":999},{\"subCount\":0,\"id\":103,\"menuSort\":999},{\"subCount\":0,\"id\":104,\"menuSort\":999},{\"subCount\":0,\"id\":105,\"menuSort\":999},{\"subCount\":0,\"id\":106,\"menuSort\":999},{\"subCount\":0,\"id\":107,\"menuSort\":999},{\"subCount\":0,\"id\":108,\"menuSort\":999},{\"subCount\":0,\"id\":109,\"menuSort\":999},{\"subCount\":0,\"id\":110,\"menuSort\":999},{\"subCount\":0,\"id\":111,\"menuSort\":999},{\"subCount\":0,\"id\":112,\"menuSort\":999},{\"subCount\":0,\"id\":113,\"menuSort\":999},{\"subCount\":0,\"id\":114,\"menuSort\":999},{\"subCount\":0,\"id\":116,\"menuSort\":999},{\"subCount\":0,\"id\":118,\"menuSort\":999},{\"subCount\":0,\"id\":119,\"menuSort\":999},{\"subCount\":0,\"id\":120,\"menuSort\":999},{\"subCount\":0,\"id\":121,\"menuSort\":999},{\"subCount\":0,\"id\":122,\"menuSort\":999},{\"subCount\":0,\"id\":123,\"menuSort\":999},{\"subCount\":0,\"id\":124,\"menuSort\":999},{\"subCount\":0,\"id\":125,\"menuSort\":999},{\"subCount\":0,\"id\":1,\"menuSort\":999},{\"subCount\":0,\"id\":2,\"menuSort\":999},{\"subCount\":0,\"id\":3,\"menuSort\":999},{\"subCount\":0,\"id\":5,\"menuSort\":999},{\"subCount\":0,\"id\":6,\"menuSort\":999},{\"subCount\":0,\"id\":7,\"menuSort\":999},{\"subCount\":0,\"id\":9,\"menuSort\":999},{\"subCount\":0,\"id\":10,\"menuSort\":999},{\"subCount\":0,\"id\":11,\"menuSort\":999},{\"subCount\":0,\"id\":14,\"menuSort\":999},{\"subCount\":0,\"id\":15,\"menuSort\":999},{\"subCount\":0,\"id\":18,\"menuSort\":999},{\"subCount\":0,\"id\":19,\"menuSort\":999},{\"subCount\":0,\"id\":21,\"menuSort\":999},{\"subCount\":0,\"id\":22,\"menuSort\":999},{\"subCount\":0,\"id\":23,\"menuSort\":999},{\"subCount\":0,\"id\":24,\"menuSort\":999},{\"subCount\":0,\"id\":27,\"menuSort\":999},{\"subCount\":0,\"id\":28,\"menuSort\":999},{\"subCount\":0,\"id\":30,\"menuSort\":999},{\"subCount\":0,\"id\":32,\"menuSort\":999},{\"subCount\":0,\"id\":33,\"menuSort\":999},{\"subCount\":0,\"id\":34,\"menuSort\":999},{\"subCount\":0,\"id\":35,\"menuSort\":999},{\"subCount\":0,\"id\":36,\"menuSort\":999},{\"subCount\":0,\"id\":37,\"menuSort\":999},{\"subCount\":0,\"id\":38,\"menuSort\":999},{\"subCount\":0,\"id\":39,\"menuSort\":999},{\"subCount\":0,\"id\":41,\"menuSort\":999},{\"subCount\":0,\"id\":44,\"menuSort\":999},{\"subCount\":0,\"id\":45,\"menuSort\":999},{\"subCount\":0,\"id\":46,\"menuSort\":999},{\"subCount\":0,\"id\":48,\"menuSort\":999},{\"subCount\":0,\"id\":49,\"menuSort\":999},{\"subCount\":0,\"id\":50,\"menuSort\":999},{\"subCount\":0,\"id\":52,\"menuSort\":999},{\"subCount\":0,\"id\":53,\"menuSort\":999},{\"subCount\":0,\"id\":54,\"menuSort\":999},{\"subCount\":0,\"id\":56,\"menuSort\":999},{\"subCount\":0,\"id\":57,\"menuSort\":999},{\"subCount\":0,\"id\":58,\"menuSort\":999},{\"subCount\":0,\"id\":60,\"menuSort\":999},{\"subCount\":0,\"id\":61,\"menuSort\":999},{\"subCount\":0,\"id\":62,\"menuSort\":999},{\"subCount\":0,\"id\":64,\"menuSort\":999},{\"subCount\":0,\"id\":65,\"menuSort\":999},{\"subCount\":0,\"id\":66,\"menuSort\":999},{\"subCount\":0,\"id\":73,\"menuSort\":999},{\"subCount\":0,\"id\":74,\"menuSort\":999},{\"subCount\":0,\"id\":75,\"menuSort\":999},{\"subCount\":0,\"id\":77,\"menuSort\":999},{\"subCount\":0,\"id\":78,\"menuSort\":999},{\"subCount\":0,\"id\":79,\"menuSort\":999},{\"subCount\":0,\"id\":80,\"menuSort\":999},{\"subCount\":0,\"id\":82,\"menuSort\":999},{\"subCount\":0,\"id\":83,\"menuSort\":999},{\"subCount\":0,\"id\":90,\"menuSort\":999},{\"subCount\":0,\"id\":92,\"menuSort\":999},{\"subCount\":0,\"id\":93,\"menuSort\":999},{\"subCount\":0,\"id\":94,\"menuSort\":999}]}', '172.24.96.1', 147, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:09');
INSERT INTO `sys_log` VALUES (3582, '修改角色菜单', 'INFO', 'me.zhengjie.modules.system.rest.RoleController.updateMenu()', '{\"level\":3,\"dataScope\":\"本级\",\"id\":1,\"menus\":[{\"subCount\":0,\"id\":97,\"menuSort\":999},{\"subCount\":0,\"id\":98,\"menuSort\":999},{\"subCount\":0,\"id\":102,\"menuSort\":999},{\"subCount\":0,\"id\":103,\"menuSort\":999},{\"subCount\":0,\"id\":104,\"menuSort\":999},{\"subCount\":0,\"id\":105,\"menuSort\":999},{\"subCount\":0,\"id\":106,\"menuSort\":999},{\"subCount\":0,\"id\":107,\"menuSort\":999},{\"subCount\":0,\"id\":108,\"menuSort\":999},{\"subCount\":0,\"id\":109,\"menuSort\":999},{\"subCount\":0,\"id\":110,\"menuSort\":999},{\"subCount\":0,\"id\":111,\"menuSort\":999},{\"subCount\":0,\"id\":112,\"menuSort\":999},{\"subCount\":0,\"id\":113,\"menuSort\":999},{\"subCount\":0,\"id\":114,\"menuSort\":999},{\"subCount\":0,\"id\":116,\"menuSort\":999},{\"subCount\":0,\"id\":118,\"menuSort\":999},{\"subCount\":0,\"id\":119,\"menuSort\":999},{\"subCount\":0,\"id\":120,\"menuSort\":999},{\"subCount\":0,\"id\":121,\"menuSort\":999},{\"subCount\":0,\"id\":122,\"menuSort\":999},{\"subCount\":0,\"id\":123,\"menuSort\":999},{\"subCount\":0,\"id\":124,\"menuSort\":999},{\"subCount\":0,\"id\":125,\"menuSort\":999},{\"subCount\":0,\"id\":1,\"menuSort\":999},{\"subCount\":0,\"id\":2,\"menuSort\":999},{\"subCount\":0,\"id\":3,\"menuSort\":999},{\"subCount\":0,\"id\":5,\"menuSort\":999},{\"subCount\":0,\"id\":6,\"menuSort\":999},{\"subCount\":0,\"id\":7,\"menuSort\":999},{\"subCount\":0,\"id\":9,\"menuSort\":999},{\"subCount\":0,\"id\":10,\"menuSort\":999},{\"subCount\":0,\"id\":11,\"menuSort\":999},{\"subCount\":0,\"id\":14,\"menuSort\":999},{\"subCount\":0,\"id\":15,\"menuSort\":999},{\"subCount\":0,\"id\":18,\"menuSort\":999},{\"subCount\":0,\"id\":19,\"menuSort\":999},{\"subCount\":0,\"id\":21,\"menuSort\":999},{\"subCount\":0,\"id\":22,\"menuSort\":999},{\"subCount\":0,\"id\":23,\"menuSort\":999},{\"subCount\":0,\"id\":24,\"menuSort\":999},{\"subCount\":0,\"id\":27,\"menuSort\":999},{\"subCount\":0,\"id\":28,\"menuSort\":999},{\"subCount\":0,\"id\":30,\"menuSort\":999},{\"subCount\":0,\"id\":32,\"menuSort\":999},{\"subCount\":0,\"id\":33,\"menuSort\":999},{\"subCount\":0,\"id\":34,\"menuSort\":999},{\"subCount\":0,\"id\":35,\"menuSort\":999},{\"subCount\":0,\"id\":36,\"menuSort\":999},{\"subCount\":0,\"id\":37,\"menuSort\":999},{\"subCount\":0,\"id\":38,\"menuSort\":999},{\"subCount\":0,\"id\":39,\"menuSort\":999},{\"subCount\":0,\"id\":41,\"menuSort\":999},{\"subCount\":0,\"id\":44,\"menuSort\":999},{\"subCount\":0,\"id\":45,\"menuSort\":999},{\"subCount\":0,\"id\":46,\"menuSort\":999},{\"subCount\":0,\"id\":48,\"menuSort\":999},{\"subCount\":0,\"id\":49,\"menuSort\":999},{\"subCount\":0,\"id\":50,\"menuSort\":999},{\"subCount\":0,\"id\":52,\"menuSort\":999},{\"subCount\":0,\"id\":53,\"menuSort\":999},{\"subCount\":0,\"id\":54,\"menuSort\":999},{\"subCount\":0,\"id\":56,\"menuSort\":999},{\"subCount\":0,\"id\":57,\"menuSort\":999},{\"subCount\":0,\"id\":58,\"menuSort\":999},{\"subCount\":0,\"id\":60,\"menuSort\":999},{\"subCount\":0,\"id\":61,\"menuSort\":999},{\"subCount\":0,\"id\":62,\"menuSort\":999},{\"subCount\":0,\"id\":64,\"menuSort\":999},{\"subCount\":0,\"id\":65,\"menuSort\":999},{\"subCount\":0,\"id\":66,\"menuSort\":999},{\"subCount\":0,\"id\":73,\"menuSort\":999},{\"subCount\":0,\"id\":74,\"menuSort\":999},{\"subCount\":0,\"id\":75,\"menuSort\":999},{\"subCount\":0,\"id\":77,\"menuSort\":999},{\"subCount\":0,\"id\":78,\"menuSort\":999},{\"subCount\":0,\"id\":79,\"menuSort\":999},{\"subCount\":0,\"id\":80,\"menuSort\":999},{\"subCount\":0,\"id\":82,\"menuSort\":999},{\"subCount\":0,\"id\":83,\"menuSort\":999},{\"subCount\":0,\"id\":90,\"menuSort\":999},{\"subCount\":0,\"id\":92,\"menuSort\":999},{\"subCount\":0,\"id\":93,\"menuSort\":999},{\"subCount\":0,\"id\":94,\"menuSort\":999}]}', '172.24.96.1', 54, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:12');
INSERT INTO `sys_log` VALUES (3583, '查询blog-like', 'INFO', 'me.zhengjie.modules.blog.rest.LikeController.query()', '', '172.24.96.1', 204, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:21');
INSERT INTO `sys_log` VALUES (3584, '查询blog_repost', 'INFO', 'me.zhengjie.modules.blog.rest.RepostController.query()', '', '172.24.96.1', 42, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:26');
INSERT INTO `sys_log` VALUES (3585, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 4, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:29');
INSERT INTO `sys_log` VALUES (3586, '查询blog_repost', 'INFO', 'me.zhengjie.modules.blog.rest.RepostController.query()', '', '172.24.96.1', 3, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:31');
INSERT INTO `sys_log` VALUES (3587, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 5, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:33');
INSERT INTO `sys_log` VALUES (3588, '查询blog_img', 'INFO', 'me.zhengjie.modules.blog.rest.ImgController.query()', '', '172.24.96.1', 23, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:34');
INSERT INTO `sys_log` VALUES (3589, '查询blog_comment', 'INFO', 'me.zhengjie.modules.blog.rest.CommentController.query()', '', '172.24.96.1', 31, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:35');
INSERT INTO `sys_log` VALUES (3590, '查询blog_collect', 'INFO', 'me.zhengjie.modules.blog.rest.CollectController.query()', '', '172.24.96.1', 7, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:37');
INSERT INTO `sys_log` VALUES (3591, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 5, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:38');
INSERT INTO `sys_log` VALUES (3592, '查询blog-like', 'INFO', 'me.zhengjie.modules.blog.rest.LikeController.query()', '', '172.24.96.1', 4, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 15:33:47');
INSERT INTO `sys_log` VALUES (3593, '查询blog-like', 'INFO', 'me.zhengjie.modules.blog.rest.LikeController.query()', '', '172.24.96.1', 84, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 19:59:07');
INSERT INTO `sys_log` VALUES (3594, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 19, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 19:59:22');
INSERT INTO `sys_log` VALUES (3595, '查询blog-like', 'INFO', 'me.zhengjie.modules.blog.rest.LikeController.query()', '', '172.24.96.1', 4, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 19:59:26');
INSERT INTO `sys_log` VALUES (3596, '新增blog-like', 'ERROR', 'me.zhengjie.modules.blog.rest.LikeController.create()', '{\"userId\":1,\"createTime\":1606838400000,\"likeId\":1}', '172.24.96.1', 589, 'admin', '内网IP', 'Chrome 8', 'org.springframework.dao.DataIntegrityViolationException: could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement\r\n	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:296)\r\n	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:253)\r\n	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.translateExceptionIfPossible(AbstractEntityManagerFactoryBean.java:527)\r\n	at org.springframework.dao.support.ChainedPersistenceExceptionTranslator.translateExceptionIfPossible(ChainedPersistenceExceptionTranslator.java:61)\r\n	at org.springframework.dao.support.DataAccessUtils.translateIfNecessary(DataAccessUtils.java:242)\r\n	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:153)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:135)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.data.repository.core.support.SurroundingTransactionDetectorMethodInterceptor.invoke(SurroundingTransactionDetectorMethodInterceptor.java:61)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\r\n	at com.sun.proxy.$Proxy165.save(Unknown Source)\r\n	at me.zhengjie.modules.blog.service.impl.LikeServiceImpl.create(LikeServiceImpl.java:78)\r\n	at me.zhengjie.modules.blog.service.impl.LikeServiceImpl$$FastClassBySpringCGLIB$$6b1c946c.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at me.zhengjie.modules.blog.service.impl.LikeServiceImpl$$EnhancerBySpringCGLIB$$55a94145.create(<generated>)\r\n	at me.zhengjie.modules.blog.rest.LikeController.create(LikeController.java:69)\r\n	at me.zhengjie.modules.blog.rest.LikeController$$FastClassBySpringCGLIB$$371b044a.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n	at me.zhengjie.aspect.LogAspect.logAround(LogAspect.java:69)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor345.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor.invoke(MethodSecurityInterceptor.java:69)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at me.zhengjie.modules.blog.rest.LikeController$$EnhancerBySpringCGLIB$$c4e0b726.create(<generated>)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:215)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:142)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:800)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:998)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:901)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:660)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:875)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:101)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:124)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:127)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:91)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:170)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at me.zhengjie.modules.security.security.TokenFilter.doFilter(TokenFilter.java:91)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:66)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:770)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:834)\r\nCaused by: org.hibernate.exception.ConstraintViolationException: could not execute statement\r\n	at org.hibernate.exception.internal.SQLExceptionTypeDelegate.convert(SQLExceptionTypeDelegate.java:59)\r\n	at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:42)\r\n	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:113)\r\n	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:99)\r\n	at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:178)\r\n	at org.hibernate.dialect.identity.GetGeneratedKeysDelegate.executeAndExtract(GetGeneratedKeysDelegate.java:57)\r\n	at org.hibernate.id.insert.AbstractReturningDelegate.performInsert(AbstractReturningDelegate.java:42)\r\n	at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3072)\r\n	at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3663)\r\n	at org.hibernate.action.internal.EntityIdentityInsertAction.execute(EntityIdentityInsertAction.java:81)\r\n	at org.hibernate.engine.spi.ActionQueue.execute(ActionQueue.java:645)\r\n	at org.hibernate.engine.spi.ActionQueue.addResolvedEntityInsertAction(ActionQueue.java:282)\r\n	at org.hibernate.engine.spi.ActionQueue.addInsertAction(ActionQueue.java:263)\r\n	at org.hibernate.engine.spi.ActionQueue.addAction(ActionQueue.java:317)\r\n	at org.hibernate.event.internal.AbstractSaveEventListener.addInsertAction(AbstractSaveEventListener.java:359)\r\n	at org.hibernate.event.internal.AbstractSaveEventListener.performSaveOrReplicate(AbstractSaveEventListener.java:292)\r\n	at org.hibernate.event.internal.AbstractSaveEventListener.performSave(AbstractSaveEventListener.java:200)\r\n	at org.hibernate.event.internal.AbstractSaveEventListener.saveWithGeneratedId(AbstractSaveEventListener.java:131)\r\n	at org.hibernate.event.internal.DefaultMergeEventListener.saveTransientEntity(DefaultMergeEventListener.java:255)\r\n	at org.hibernate.event.internal.DefaultMergeEventListener.entityIsTransient(DefaultMergeEventListener.java:235)\r\n	at org.hibernate.event.internal.DefaultMergeEventListener.entityIsDetached(DefaultMergeEventListener.java:301)\r\n	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:170)\r\n	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:69)\r\n	at org.hibernate.internal.SessionImpl.fireMerge(SessionImpl.java:901)\r\n	at org.hibernate.internal.SessionImpl.merge(SessionImpl.java:887)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor236.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.orm.jpa.ExtendedEntityManagerCreator$ExtendedEntityManagerInvocationHandler.invoke(ExtendedEntityManagerCreator.java:350)\r\n	at com.sun.proxy.$Proxy157.merge(Unknown Source)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor236.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.orm.jpa.SharedEntityManagerCreator$SharedEntityManagerInvocationHandler.invoke(SharedEntityManagerCreator.java:308)\r\n	at com.sun.proxy.$Proxy157.merge(Unknown Source)\r\n	at org.springframework.data.jpa.repository.support.SimpleJpaRepository.save(SimpleJpaRepository.java:492)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor235.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.data.repository.core.support.RepositoryComposition$RepositoryFragments.invoke(RepositoryComposition.java:359)\r\n	at org.springframework.data.repository.core.support.RepositoryComposition.invoke(RepositoryComposition.java:200)\r\n	at org.springframework.data.repository.core.support.RepositoryFactorySupport$ImplementationMethodExecutionInterceptor.invoke(RepositoryFactorySupport.java:644)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.doInvoke(RepositoryFactorySupport.java:608)\r\n	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.lambda$invoke$3(RepositoryFactorySupport.java:595)\r\n	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.invoke(RepositoryFactorySupport.java:595)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:59)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:139)\r\n	... 124 more\r\nCaused by: java.sql.SQLIntegrityConstraintViolationException: Column \'blog_id\' cannot be null\r\n	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:117)\r\n	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)\r\n	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)\r\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeInternal(ClientPreparedStatement.java:974)\r\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1113)\r\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1061)\r\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeLargeUpdate(ClientPreparedStatement.java:1381)\r\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdate(ClientPreparedStatement.java:1046)\r\n	at net.sf.log4jdbc.sql.jdbcapi.PreparedStatementSpy.executeUpdate(PreparedStatementSpy.java:1080)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_executeUpdate(FilterChainImpl.java:3253)\r\n	at com.alibaba.druid.filter.FilterAdapter.preparedStatement_executeUpdate(FilterAdapter.java:1092)\r\n	at com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_executeUpdate(FilterEventAdapter.java:491)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_executeUpdate(FilterChainImpl.java:3251)\r\n	at com.alibaba.druid.proxy.jdbc.PreparedStatementProxyImpl.executeUpdate(PreparedStatementProxyImpl.java:194)\r\n	at com.alibaba.druid.pool.DruidPooledPreparedStatement.executeUpdate(DruidPooledPreparedStatement.java:255)\r\n	at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:175)\r\n	... 172 more\r\n', '2020-12-06 19:59:34');
INSERT INTO `sys_log` VALUES (3597, '新增blog-like', 'ERROR', 'me.zhengjie.modules.blog.rest.LikeController.create()', '{\"userId\":1,\"createTime\":1606838400000,\"likeId\":1}', '172.24.96.1', 9, 'admin', '内网IP', 'Chrome 8', 'org.springframework.dao.DataIntegrityViolationException: could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement\r\n	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:296)\r\n	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:253)\r\n	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.translateExceptionIfPossible(AbstractEntityManagerFactoryBean.java:527)\r\n	at org.springframework.dao.support.ChainedPersistenceExceptionTranslator.translateExceptionIfPossible(ChainedPersistenceExceptionTranslator.java:61)\r\n	at org.springframework.dao.support.DataAccessUtils.translateIfNecessary(DataAccessUtils.java:242)\r\n	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:153)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:135)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.data.repository.core.support.SurroundingTransactionDetectorMethodInterceptor.invoke(SurroundingTransactionDetectorMethodInterceptor.java:61)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\r\n	at com.sun.proxy.$Proxy165.save(Unknown Source)\r\n	at me.zhengjie.modules.blog.service.impl.LikeServiceImpl.create(LikeServiceImpl.java:78)\r\n	at me.zhengjie.modules.blog.service.impl.LikeServiceImpl$$FastClassBySpringCGLIB$$6b1c946c.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at me.zhengjie.modules.blog.service.impl.LikeServiceImpl$$EnhancerBySpringCGLIB$$55a94145.create(<generated>)\r\n	at me.zhengjie.modules.blog.rest.LikeController.create(LikeController.java:69)\r\n	at me.zhengjie.modules.blog.rest.LikeController$$FastClassBySpringCGLIB$$371b044a.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n	at me.zhengjie.aspect.LogAspect.logAround(LogAspect.java:69)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor345.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor.invoke(MethodSecurityInterceptor.java:69)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at me.zhengjie.modules.blog.rest.LikeController$$EnhancerBySpringCGLIB$$c4e0b726.create(<generated>)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:215)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:142)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:800)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:998)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:901)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:660)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:875)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:101)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:124)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:127)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:91)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:170)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at me.zhengjie.modules.security.security.TokenFilter.doFilter(TokenFilter.java:91)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:66)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:770)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:834)\r\nCaused by: org.hibernate.exception.ConstraintViolationException: could not execute statement\r\n	at org.hibernate.exception.internal.SQLExceptionTypeDelegate.convert(SQLExceptionTypeDelegate.java:59)\r\n	at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:42)\r\n	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:113)\r\n	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:99)\r\n	at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:178)\r\n	at org.hibernate.dialect.identity.GetGeneratedKeysDelegate.executeAndExtract(GetGeneratedKeysDelegate.java:57)\r\n	at org.hibernate.id.insert.AbstractReturningDelegate.performInsert(AbstractReturningDelegate.java:42)\r\n	at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3072)\r\n	at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3663)\r\n	at org.hibernate.action.internal.EntityIdentityInsertAction.execute(EntityIdentityInsertAction.java:81)\r\n	at org.hibernate.engine.spi.ActionQueue.execute(ActionQueue.java:645)\r\n	at org.hibernate.engine.spi.ActionQueue.addResolvedEntityInsertAction(ActionQueue.java:282)\r\n	at org.hibernate.engine.spi.ActionQueue.addInsertAction(ActionQueue.java:263)\r\n	at org.hibernate.engine.spi.ActionQueue.addAction(ActionQueue.java:317)\r\n	at org.hibernate.event.internal.AbstractSaveEventListener.addInsertAction(AbstractSaveEventListener.java:359)\r\n	at org.hibernate.event.internal.AbstractSaveEventListener.performSaveOrReplicate(AbstractSaveEventListener.java:292)\r\n	at org.hibernate.event.internal.AbstractSaveEventListener.performSave(AbstractSaveEventListener.java:200)\r\n	at org.hibernate.event.internal.AbstractSaveEventListener.saveWithGeneratedId(AbstractSaveEventListener.java:131)\r\n	at org.hibernate.event.internal.DefaultMergeEventListener.saveTransientEntity(DefaultMergeEventListener.java:255)\r\n	at org.hibernate.event.internal.DefaultMergeEventListener.entityIsTransient(DefaultMergeEventListener.java:235)\r\n	at org.hibernate.event.internal.DefaultMergeEventListener.entityIsDetached(DefaultMergeEventListener.java:301)\r\n	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:170)\r\n	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:69)\r\n	at org.hibernate.internal.SessionImpl.fireMerge(SessionImpl.java:901)\r\n	at org.hibernate.internal.SessionImpl.merge(SessionImpl.java:887)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor236.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.orm.jpa.ExtendedEntityManagerCreator$ExtendedEntityManagerInvocationHandler.invoke(ExtendedEntityManagerCreator.java:350)\r\n	at com.sun.proxy.$Proxy157.merge(Unknown Source)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor236.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.orm.jpa.SharedEntityManagerCreator$SharedEntityManagerInvocationHandler.invoke(SharedEntityManagerCreator.java:308)\r\n	at com.sun.proxy.$Proxy157.merge(Unknown Source)\r\n	at org.springframework.data.jpa.repository.support.SimpleJpaRepository.save(SimpleJpaRepository.java:492)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor235.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.data.repository.core.support.RepositoryComposition$RepositoryFragments.invoke(RepositoryComposition.java:359)\r\n	at org.springframework.data.repository.core.support.RepositoryComposition.invoke(RepositoryComposition.java:200)\r\n	at org.springframework.data.repository.core.support.RepositoryFactorySupport$ImplementationMethodExecutionInterceptor.invoke(RepositoryFactorySupport.java:644)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.doInvoke(RepositoryFactorySupport.java:608)\r\n	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.lambda$invoke$3(RepositoryFactorySupport.java:595)\r\n	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.invoke(RepositoryFactorySupport.java:595)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:59)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:139)\r\n	... 124 more\r\nCaused by: java.sql.SQLIntegrityConstraintViolationException: Column \'blog_id\' cannot be null\r\n	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:117)\r\n	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)\r\n	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)\r\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeInternal(ClientPreparedStatement.java:974)\r\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1113)\r\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1061)\r\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeLargeUpdate(ClientPreparedStatement.java:1381)\r\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdate(ClientPreparedStatement.java:1046)\r\n	at net.sf.log4jdbc.sql.jdbcapi.PreparedStatementSpy.executeUpdate(PreparedStatementSpy.java:1080)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_executeUpdate(FilterChainImpl.java:3253)\r\n	at com.alibaba.druid.filter.FilterAdapter.preparedStatement_executeUpdate(FilterAdapter.java:1092)\r\n	at com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_executeUpdate(FilterEventAdapter.java:491)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_executeUpdate(FilterChainImpl.java:3251)\r\n	at com.alibaba.druid.proxy.jdbc.PreparedStatementProxyImpl.executeUpdate(PreparedStatementProxyImpl.java:194)\r\n	at com.alibaba.druid.pool.DruidPooledPreparedStatement.executeUpdate(DruidPooledPreparedStatement.java:255)\r\n	at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:175)\r\n	... 172 more\r\n', '2020-12-06 19:59:36');
INSERT INTO `sys_log` VALUES (3598, '新增菜单', 'ERROR', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"hidden\":false,\"icon\":\"Steve-Jobs\",\"pid\":118,\"title\":\"用户管理\",\"type\":1,\"subCount\":0,\"path\":\"blog/diary_user\",\"component\":\"blog/diary_user/index\",\"iFrame\":false,\"componentName\":\"DiaryUser\",\"menuSort\":0}', '172.24.96.1', 11, 'admin', '内网IP', 'Chrome 8', 'me.zhengjie.exception.EntityExistException: Menu with title 用户管理 existed\r\n	at me.zhengjie.modules.system.service.impl.MenuServiceImpl.create(MenuServiceImpl.java:112)\r\n	at me.zhengjie.modules.system.service.impl.MenuServiceImpl$$FastClassBySpringCGLIB$$a0cfbc77.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at me.zhengjie.modules.system.service.impl.MenuServiceImpl$$EnhancerBySpringCGLIB$$b654d9c6.create(<generated>)\r\n	at me.zhengjie.modules.system.rest.MenuController.create(MenuController.java:121)\r\n	at me.zhengjie.modules.system.rest.MenuController$$FastClassBySpringCGLIB$$158b4bdf.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n	at me.zhengjie.aspect.LogAspect.logAround(LogAspect.java:69)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor.invoke(MethodSecurityInterceptor.java:69)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at me.zhengjie.modules.system.rest.MenuController$$EnhancerBySpringCGLIB$$13eac055.create(<generated>)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:215)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:142)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:800)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:998)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:901)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:660)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:875)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:101)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:124)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:127)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:91)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:170)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at me.zhengjie.modules.security.security.TokenFilter.doFilter(TokenFilter.java:91)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:66)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:770)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:834)\r\n', '2020-12-06 20:16:43');
INSERT INTO `sys_log` VALUES (3599, '新增菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.create()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"Steve-Jobs\",\"pid\":118,\"updateTime\":1607257026042,\"title\":\"动态用户管理\",\"type\":1,\"subCount\":0,\"path\":\"blog/diary_user\",\"component\":\"blog/diary_user/index\",\"createBy\":\"admin\",\"createTime\":1607257026042,\"iFrame\":false,\"id\":126,\"componentName\":\"DiaryUser\",\"menuSort\":0}', '172.24.96.1', 579, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:17:06');
INSERT INTO `sys_log` VALUES (3600, '修改角色菜单', 'INFO', 'me.zhengjie.modules.system.rest.RoleController.updateMenu()', '{\"level\":3,\"dataScope\":\"本级\",\"id\":1,\"menus\":[{\"subCount\":0,\"id\":97,\"menuSort\":999},{\"subCount\":0,\"id\":98,\"menuSort\":999},{\"subCount\":0,\"id\":102,\"menuSort\":999},{\"subCount\":0,\"id\":103,\"menuSort\":999},{\"subCount\":0,\"id\":104,\"menuSort\":999},{\"subCount\":0,\"id\":105,\"menuSort\":999},{\"subCount\":0,\"id\":106,\"menuSort\":999},{\"subCount\":0,\"id\":107,\"menuSort\":999},{\"subCount\":0,\"id\":108,\"menuSort\":999},{\"subCount\":0,\"id\":109,\"menuSort\":999},{\"subCount\":0,\"id\":110,\"menuSort\":999},{\"subCount\":0,\"id\":111,\"menuSort\":999},{\"subCount\":0,\"id\":112,\"menuSort\":999},{\"subCount\":0,\"id\":113,\"menuSort\":999},{\"subCount\":0,\"id\":114,\"menuSort\":999},{\"subCount\":0,\"id\":116,\"menuSort\":999},{\"subCount\":0,\"id\":118,\"menuSort\":999},{\"subCount\":0,\"id\":119,\"menuSort\":999},{\"subCount\":0,\"id\":120,\"menuSort\":999},{\"subCount\":0,\"id\":121,\"menuSort\":999},{\"subCount\":0,\"id\":122,\"menuSort\":999},{\"subCount\":0,\"id\":123,\"menuSort\":999},{\"subCount\":0,\"id\":124,\"menuSort\":999},{\"subCount\":0,\"id\":125,\"menuSort\":999},{\"subCount\":0,\"id\":126,\"menuSort\":999},{\"subCount\":0,\"id\":1,\"menuSort\":999},{\"subCount\":0,\"id\":2,\"menuSort\":999},{\"subCount\":0,\"id\":3,\"menuSort\":999},{\"subCount\":0,\"id\":5,\"menuSort\":999},{\"subCount\":0,\"id\":6,\"menuSort\":999},{\"subCount\":0,\"id\":7,\"menuSort\":999},{\"subCount\":0,\"id\":9,\"menuSort\":999},{\"subCount\":0,\"id\":10,\"menuSort\":999},{\"subCount\":0,\"id\":11,\"menuSort\":999},{\"subCount\":0,\"id\":14,\"menuSort\":999},{\"subCount\":0,\"id\":15,\"menuSort\":999},{\"subCount\":0,\"id\":18,\"menuSort\":999},{\"subCount\":0,\"id\":19,\"menuSort\":999},{\"subCount\":0,\"id\":21,\"menuSort\":999},{\"subCount\":0,\"id\":22,\"menuSort\":999},{\"subCount\":0,\"id\":23,\"menuSort\":999},{\"subCount\":0,\"id\":24,\"menuSort\":999},{\"subCount\":0,\"id\":27,\"menuSort\":999},{\"subCount\":0,\"id\":28,\"menuSort\":999},{\"subCount\":0,\"id\":30,\"menuSort\":999},{\"subCount\":0,\"id\":32,\"menuSort\":999},{\"subCount\":0,\"id\":33,\"menuSort\":999},{\"subCount\":0,\"id\":34,\"menuSort\":999},{\"subCount\":0,\"id\":35,\"menuSort\":999},{\"subCount\":0,\"id\":36,\"menuSort\":999},{\"subCount\":0,\"id\":37,\"menuSort\":999},{\"subCount\":0,\"id\":38,\"menuSort\":999},{\"subCount\":0,\"id\":39,\"menuSort\":999},{\"subCount\":0,\"id\":41,\"menuSort\":999},{\"subCount\":0,\"id\":44,\"menuSort\":999},{\"subCount\":0,\"id\":45,\"menuSort\":999},{\"subCount\":0,\"id\":46,\"menuSort\":999},{\"subCount\":0,\"id\":48,\"menuSort\":999},{\"subCount\":0,\"id\":49,\"menuSort\":999},{\"subCount\":0,\"id\":50,\"menuSort\":999},{\"subCount\":0,\"id\":52,\"menuSort\":999},{\"subCount\":0,\"id\":53,\"menuSort\":999},{\"subCount\":0,\"id\":54,\"menuSort\":999},{\"subCount\":0,\"id\":56,\"menuSort\":999},{\"subCount\":0,\"id\":57,\"menuSort\":999},{\"subCount\":0,\"id\":58,\"menuSort\":999},{\"subCount\":0,\"id\":60,\"menuSort\":999},{\"subCount\":0,\"id\":61,\"menuSort\":999},{\"subCount\":0,\"id\":62,\"menuSort\":999},{\"subCount\":0,\"id\":64,\"menuSort\":999},{\"subCount\":0,\"id\":65,\"menuSort\":999},{\"subCount\":0,\"id\":66,\"menuSort\":999},{\"subCount\":0,\"id\":73,\"menuSort\":999},{\"subCount\":0,\"id\":74,\"menuSort\":999},{\"subCount\":0,\"id\":75,\"menuSort\":999},{\"subCount\":0,\"id\":77,\"menuSort\":999},{\"subCount\":0,\"id\":78,\"menuSort\":999},{\"subCount\":0,\"id\":79,\"menuSort\":999},{\"subCount\":0,\"id\":80,\"menuSort\":999},{\"subCount\":0,\"id\":82,\"menuSort\":999},{\"subCount\":0,\"id\":83,\"menuSort\":999},{\"subCount\":0,\"id\":90,\"menuSort\":999},{\"subCount\":0,\"id\":92,\"menuSort\":999},{\"subCount\":0,\"id\":93,\"menuSort\":999},{\"subCount\":0,\"id\":94,\"menuSort\":999}]}', '172.24.96.1', 131, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:17:17');
INSERT INTO `sys_log` VALUES (3601, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 34, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:23:34');
INSERT INTO `sys_log` VALUES (3602, '修改菜单', 'INFO', 'me.zhengjie.modules.system.rest.MenuController.update()', '{\"cache\":false,\"updatedBy\":\"admin\",\"hidden\":false,\"icon\":\"Steve-Jobs\",\"pid\":118,\"updateTime\":1607257026000,\"title\":\"动态用户管理\",\"type\":1,\"subCount\":0,\"path\":\"blog/diary_user\",\"component\":\"blog/diary_user/index\",\"createBy\":\"admin\",\"createTime\":1607257026000,\"iFrame\":false,\"id\":126,\"componentName\":\"DiaryUser\",\"menuSort\":0}', '172.24.96.1', 75, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:24:50');
INSERT INTO `sys_log` VALUES (3603, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 64, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:26:45');
INSERT INTO `sys_log` VALUES (3604, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 8, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:26:45');
INSERT INTO `sys_log` VALUES (3605, '查询blog', 'INFO', 'me.zhengjie.modules.blog.rest.BlogController.query()', '', '172.24.96.1', 24, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:27:09');
INSERT INTO `sys_log` VALUES (3606, '查询blog_repost', 'INFO', 'me.zhengjie.modules.blog.rest.RepostController.query()', '', '172.24.96.1', 24, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:27:38');
INSERT INTO `sys_log` VALUES (3607, '查询blog_repost', 'INFO', 'me.zhengjie.modules.blog.rest.RepostController.query()', '', '172.24.96.1', 14, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:31:54');
INSERT INTO `sys_log` VALUES (3608, '查询comment_like', 'INFO', 'me.zhengjie.modules.blog.rest.CommentLikeController.query()', '', '172.24.96.1', 23, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:32:00');
INSERT INTO `sys_log` VALUES (3609, '查询blog_repost', 'INFO', 'me.zhengjie.modules.blog.rest.RepostController.query()', '', '172.24.96.1', 9, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:32:07');
INSERT INTO `sys_log` VALUES (3610, '查询diary_user', 'INFO', 'me.zhengjie.modules.blog.rest.DiaryUserController.query()', '', '172.24.96.1', 28, 'admin', '内网IP', 'Chrome 8', NULL, '2020-12-06 20:32:10');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint(0) NULL DEFAULT NULL COMMENT '上级菜单ID',
  `sub_count` int(0) NULL DEFAULT 0 COMMENT '子菜单数目',
  `type` int(0) NULL DEFAULT NULL COMMENT '菜单类型',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `menu_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `i_frame` bit(1) NULL DEFAULT NULL COMMENT '是否外链',
  `cache` bit(1) NULL DEFAULT b'0' COMMENT '缓存',
  `hidden` bit(1) NULL DEFAULT b'0' COMMENT '隐藏',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE,
  UNIQUE INDEX `uniq_title`(`title`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name`) USING BTREE,
  INDEX `inx_pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, NULL, 7, 0, '系统管理', NULL, NULL, 1, 'system', 'system', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:11:29', NULL);
INSERT INTO `sys_menu` VALUES (2, 1, 3, 1, '用户管理', 'User', 'system/user/index', 2, 'peoples', 'user', b'0', b'0', b'0', 'user:list', NULL, NULL, '2018-12-18 15:14:44', NULL);
INSERT INTO `sys_menu` VALUES (3, 1, 3, 1, '角色管理', 'Role', 'system/role/index', 3, 'role', 'role', b'0', b'0', b'0', 'roles:list', NULL, NULL, '2018-12-18 15:16:07', NULL);
INSERT INTO `sys_menu` VALUES (5, 1, 3, 1, '菜单管理', 'Menu', 'system/menu/index', 5, 'menu', 'menu', b'0', b'0', b'0', 'menu:list', NULL, NULL, '2018-12-18 15:17:28', NULL);
INSERT INTO `sys_menu` VALUES (6, NULL, 5, 0, '系统监控', NULL, NULL, 10, 'monitor', 'monitor', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:17:48', NULL);
INSERT INTO `sys_menu` VALUES (7, 6, 0, 1, '操作日志', 'Log', 'monitor/log/index', 11, 'log', 'logs', b'0', b'1', b'0', NULL, NULL, 'admin', '2018-12-18 15:18:26', '2020-06-06 13:11:57');
INSERT INTO `sys_menu` VALUES (9, 6, 0, 1, 'SQL监控', 'Sql', 'monitor/sql/index', 18, 'sqlMonitor', 'druid', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:19:34', NULL);
INSERT INTO `sys_menu` VALUES (10, NULL, 5, 0, '组件管理', NULL, NULL, 50, 'zujian', 'components', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-19 13:38:16', NULL);
INSERT INTO `sys_menu` VALUES (11, 10, 0, 1, '图标库', 'Icons', 'components/icons/index', 51, 'icon', 'icon', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-19 13:38:49', NULL);
INSERT INTO `sys_menu` VALUES (14, 36, 0, 1, '邮件工具', 'Email', 'tools/email/index', 35, 'email', 'email', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-27 10:13:09', NULL);
INSERT INTO `sys_menu` VALUES (15, 10, 0, 1, '富文本', 'Editor', 'components/Editor', 52, 'fwb', 'tinymce', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-27 11:58:25', NULL);
INSERT INTO `sys_menu` VALUES (18, 36, 3, 1, '存储管理', 'Storage', 'tools/storage/index', 34, 'qiniu', 'storage', b'0', b'0', b'0', 'storage:list', NULL, NULL, '2018-12-31 11:12:15', NULL);
INSERT INTO `sys_menu` VALUES (19, 36, 0, 1, '支付宝工具', 'AliPay', 'tools/aliPay/index', 37, 'alipay', 'aliPay', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-31 14:52:38', NULL);
INSERT INTO `sys_menu` VALUES (21, NULL, 2, 0, '多级菜单', NULL, '', 900, 'menu', 'nested', b'0', b'0', b'0', NULL, NULL, 'admin', '2019-01-04 16:22:03', '2020-06-21 17:27:35');
INSERT INTO `sys_menu` VALUES (22, 21, 2, 1, '二级菜单1', NULL, 'nested/menu1/index', 999, 'menu', 'menu1', b'0', b'0', b'0', NULL, NULL, 'admin', '2019-01-04 16:23:29', '2020-06-21 17:27:20');
INSERT INTO `sys_menu` VALUES (23, 21, 0, 1, '二级菜单2', NULL, 'nested/menu2/index', 999, 'menu', 'menu2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:23:57', NULL);
INSERT INTO `sys_menu` VALUES (24, 22, 0, 1, '三级菜单1', NULL, 'nested/menu1/menu1-1', 999, 'menu', 'menu1-1', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:24:48', NULL);
INSERT INTO `sys_menu` VALUES (27, 22, 0, 1, '三级菜单2', NULL, 'nested/menu1/menu1-2', 999, 'menu', 'menu1-2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-07 17:27:32', NULL);
INSERT INTO `sys_menu` VALUES (28, 1, 3, 1, '任务调度', 'Timing', 'system/timing/index', 999, 'timing', 'timing', b'0', b'0', b'0', 'timing:list', NULL, NULL, '2019-01-07 20:34:40', NULL);
INSERT INTO `sys_menu` VALUES (30, 36, 0, 1, '代码生成', 'GeneratorIndex', 'generator/index', 32, 'dev', 'generator', b'0', b'1', b'0', NULL, NULL, NULL, '2019-01-11 15:45:55', NULL);
INSERT INTO `sys_menu` VALUES (32, 6, 0, 1, '异常日志', 'ErrorLog', 'monitor/log/errorLog', 12, 'error', 'errorLog', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-13 13:49:03', NULL);
INSERT INTO `sys_menu` VALUES (33, 10, 0, 1, 'Markdown', 'Markdown', 'components/MarkDown', 53, 'markdown', 'markdown', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-08 13:46:44', NULL);
INSERT INTO `sys_menu` VALUES (34, 10, 0, 1, 'Yaml编辑器', 'YamlEdit', 'components/YamlEdit', 54, 'dev', 'yaml', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-08 15:49:40', NULL);
INSERT INTO `sys_menu` VALUES (35, 1, 3, 1, '部门管理', 'Dept', 'system/dept/index', 6, 'dept', 'dept', b'0', b'0', b'0', 'dept:list', NULL, NULL, '2019-03-25 09:46:00', NULL);
INSERT INTO `sys_menu` VALUES (36, NULL, 7, 0, '系统工具', NULL, '', 30, 'sys-tools', 'sys-tools', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 10:57:35', NULL);
INSERT INTO `sys_menu` VALUES (37, 1, 3, 1, '岗位管理', 'Job', 'system/job/index', 7, 'Steve-Jobs', 'job', b'0', b'0', b'0', 'job:list', NULL, NULL, '2019-03-29 13:51:18', NULL);
INSERT INTO `sys_menu` VALUES (38, 36, 0, 1, '接口文档', 'Swagger', 'tools/swagger/index', 36, 'swagger', 'swagger2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 19:57:53', NULL);
INSERT INTO `sys_menu` VALUES (39, 1, 3, 1, '字典管理', 'Dict', 'system/dict/index', 8, 'dictionary', 'dict', b'0', b'0', b'0', 'dict:list', NULL, NULL, '2019-04-10 11:49:04', NULL);
INSERT INTO `sys_menu` VALUES (41, 6, 0, 1, '在线用户', 'OnlineUser', 'monitor/online/index', 10, 'Steve-Jobs', 'online', b'0', b'0', b'0', NULL, NULL, NULL, '2019-10-26 22:08:43', NULL);
INSERT INTO `sys_menu` VALUES (44, 2, 0, 2, '用户新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'user:add', NULL, NULL, '2019-10-29 10:59:46', NULL);
INSERT INTO `sys_menu` VALUES (45, 2, 0, 2, '用户编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'user:edit', NULL, NULL, '2019-10-29 11:00:08', NULL);
INSERT INTO `sys_menu` VALUES (46, 2, 0, 2, '用户删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'user:del', NULL, NULL, '2019-10-29 11:00:23', NULL);
INSERT INTO `sys_menu` VALUES (48, 3, 0, 2, '角色创建', NULL, '', 2, '', '', b'0', b'0', b'0', 'roles:add', NULL, NULL, '2019-10-29 12:45:34', NULL);
INSERT INTO `sys_menu` VALUES (49, 3, 0, 2, '角色修改', NULL, '', 3, '', '', b'0', b'0', b'0', 'roles:edit', NULL, NULL, '2019-10-29 12:46:16', NULL);
INSERT INTO `sys_menu` VALUES (50, 3, 0, 2, '角色删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'roles:del', NULL, NULL, '2019-10-29 12:46:51', NULL);
INSERT INTO `sys_menu` VALUES (52, 5, 0, 2, '菜单新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'menu:add', NULL, NULL, '2019-10-29 12:55:07', NULL);
INSERT INTO `sys_menu` VALUES (53, 5, 0, 2, '菜单编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'menu:edit', NULL, NULL, '2019-10-29 12:55:40', NULL);
INSERT INTO `sys_menu` VALUES (54, 5, 0, 2, '菜单删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'menu:del', NULL, NULL, '2019-10-29 12:56:00', NULL);
INSERT INTO `sys_menu` VALUES (56, 35, 0, 2, '部门新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dept:add', NULL, NULL, '2019-10-29 12:57:09', NULL);
INSERT INTO `sys_menu` VALUES (57, 35, 0, 2, '部门编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dept:edit', NULL, NULL, '2019-10-29 12:57:27', NULL);
INSERT INTO `sys_menu` VALUES (58, 35, 0, 2, '部门删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dept:del', NULL, NULL, '2019-10-29 12:57:41', NULL);
INSERT INTO `sys_menu` VALUES (60, 37, 0, 2, '岗位新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'job:add', NULL, NULL, '2019-10-29 12:58:27', NULL);
INSERT INTO `sys_menu` VALUES (61, 37, 0, 2, '岗位编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'job:edit', NULL, NULL, '2019-10-29 12:58:45', NULL);
INSERT INTO `sys_menu` VALUES (62, 37, 0, 2, '岗位删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'job:del', NULL, NULL, '2019-10-29 12:59:04', NULL);
INSERT INTO `sys_menu` VALUES (64, 39, 0, 2, '字典新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dict:add', NULL, NULL, '2019-10-29 13:00:17', NULL);
INSERT INTO `sys_menu` VALUES (65, 39, 0, 2, '字典编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dict:edit', NULL, NULL, '2019-10-29 13:00:42', NULL);
INSERT INTO `sys_menu` VALUES (66, 39, 0, 2, '字典删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dict:del', NULL, NULL, '2019-10-29 13:00:59', NULL);
INSERT INTO `sys_menu` VALUES (73, 28, 0, 2, '任务新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'timing:add', NULL, NULL, '2019-10-29 13:07:28', NULL);
INSERT INTO `sys_menu` VALUES (74, 28, 0, 2, '任务编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'timing:edit', NULL, NULL, '2019-10-29 13:07:41', NULL);
INSERT INTO `sys_menu` VALUES (75, 28, 0, 2, '任务删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'timing:del', NULL, NULL, '2019-10-29 13:07:54', NULL);
INSERT INTO `sys_menu` VALUES (77, 18, 0, 2, '上传文件', NULL, '', 2, '', '', b'0', b'0', b'0', 'storage:add', NULL, NULL, '2019-10-29 13:09:09', NULL);
INSERT INTO `sys_menu` VALUES (78, 18, 0, 2, '文件编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'storage:edit', NULL, NULL, '2019-10-29 13:09:22', NULL);
INSERT INTO `sys_menu` VALUES (79, 18, 0, 2, '文件删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'storage:del', NULL, NULL, '2019-10-29 13:09:34', NULL);
INSERT INTO `sys_menu` VALUES (80, 6, 0, 1, '服务监控', 'ServerMonitor', 'monitor/server/index', 14, 'codeConsole', 'server', b'0', b'0', b'0', 'monitor:list', NULL, 'admin', '2019-11-07 13:06:39', '2020-05-04 18:20:50');
INSERT INTO `sys_menu` VALUES (82, 36, 0, 1, '生成配置', 'GeneratorConfig', 'generator/config', 33, 'dev', 'generator/config/:tableName', b'0', b'1', b'1', '', NULL, NULL, '2019-11-17 20:08:56', NULL);
INSERT INTO `sys_menu` VALUES (83, 10, 0, 1, '图表库', 'Echarts', 'components/Echarts', 50, 'chart', 'echarts', b'0', b'1', b'0', '', NULL, NULL, '2019-11-21 09:04:32', NULL);
INSERT INTO `sys_menu` VALUES (90, NULL, 5, 1, '运维管理', 'Mnt', '', 20, 'mnt', 'mnt', b'0', b'0', b'0', NULL, NULL, NULL, '2019-11-09 10:31:08', NULL);
INSERT INTO `sys_menu` VALUES (92, 90, 3, 1, '服务器', 'ServerDeploy', 'mnt/server/index', 22, 'server', 'mnt/serverDeploy', b'0', b'0', b'0', 'serverDeploy:list', NULL, NULL, '2019-11-10 10:29:25', NULL);
INSERT INTO `sys_menu` VALUES (93, 90, 3, 1, '应用管理', 'App', 'mnt/app/index', 23, 'app', 'mnt/app', b'0', b'0', b'0', 'app:list', NULL, NULL, '2019-11-10 11:05:16', NULL);
INSERT INTO `sys_menu` VALUES (94, 90, 3, 1, '部署管理', 'Deploy', 'mnt/deploy/index', 24, 'deploy', 'mnt/deploy', b'0', b'0', b'0', 'deploy:list', NULL, NULL, '2019-11-10 15:56:55', NULL);
INSERT INTO `sys_menu` VALUES (97, 90, 1, 1, '部署备份', 'DeployHistory', 'mnt/deployHistory/index', 25, 'backup', 'mnt/deployHistory', b'0', b'0', b'0', 'deployHistory:list', NULL, NULL, '2019-11-10 16:49:44', NULL);
INSERT INTO `sys_menu` VALUES (98, 90, 3, 1, '数据库管理', 'Database', 'mnt/database/index', 26, 'database', 'mnt/database', b'0', b'0', b'0', 'database:list', NULL, NULL, '2019-11-10 20:40:04', NULL);
INSERT INTO `sys_menu` VALUES (102, 97, 0, 2, '删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deployHistory:del', NULL, NULL, '2019-11-17 09:32:48', NULL);
INSERT INTO `sys_menu` VALUES (103, 92, 0, 2, '服务器新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:add', NULL, NULL, '2019-11-17 11:08:33', NULL);
INSERT INTO `sys_menu` VALUES (104, 92, 0, 2, '服务器编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:edit', NULL, NULL, '2019-11-17 11:08:57', NULL);
INSERT INTO `sys_menu` VALUES (105, 92, 0, 2, '服务器删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:del', NULL, NULL, '2019-11-17 11:09:15', NULL);
INSERT INTO `sys_menu` VALUES (106, 93, 0, 2, '应用新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:add', NULL, NULL, '2019-11-17 11:10:03', NULL);
INSERT INTO `sys_menu` VALUES (107, 93, 0, 2, '应用编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:edit', NULL, NULL, '2019-11-17 11:10:28', NULL);
INSERT INTO `sys_menu` VALUES (108, 93, 0, 2, '应用删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:del', NULL, NULL, '2019-11-17 11:10:55', NULL);
INSERT INTO `sys_menu` VALUES (109, 94, 0, 2, '部署新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:add', NULL, NULL, '2019-11-17 11:11:22', NULL);
INSERT INTO `sys_menu` VALUES (110, 94, 0, 2, '部署编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:edit', NULL, NULL, '2019-11-17 11:11:41', NULL);
INSERT INTO `sys_menu` VALUES (111, 94, 0, 2, '部署删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:del', NULL, NULL, '2019-11-17 11:12:01', NULL);
INSERT INTO `sys_menu` VALUES (112, 98, 0, 2, '数据库新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:add', NULL, NULL, '2019-11-17 11:12:43', NULL);
INSERT INTO `sys_menu` VALUES (113, 98, 0, 2, '数据库编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:edit', NULL, NULL, '2019-11-17 11:12:58', NULL);
INSERT INTO `sys_menu` VALUES (114, 98, 0, 2, '数据库删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:del', NULL, NULL, '2019-11-17 11:13:14', NULL);
INSERT INTO `sys_menu` VALUES (116, 36, 0, 1, '生成预览', 'Preview', 'generator/preview', 999, 'java', 'generator/preview/:tableName', b'0', b'1', b'1', NULL, NULL, NULL, '2019-11-26 14:54:36', NULL);
INSERT INTO `sys_menu` VALUES (118, NULL, 8, 0, '动态管理', NULL, NULL, 2, 'message', 'blog', b'0', b'0', b'0', NULL, 'admin', 'admin', '2020-12-05 17:41:37', '2020-12-06 15:11:53');
INSERT INTO `sys_menu` VALUES (119, 118, 0, 1, '博客管理', 'Blog', 'blog/blog/index', 1, 'fwb', 'blog/blog', b'0', b'0', b'0', NULL, 'admin', 'admin', '2020-12-05 21:03:52', '2020-12-06 15:12:47');
INSERT INTO `sys_menu` VALUES (120, 118, 0, 1, '收藏管理', 'Collect', 'blog/blog_collect/index', 2, 'source', 'blog/blog_collect', b'0', b'0', b'0', NULL, 'admin', 'admin', '2020-12-06 15:16:49', '2020-12-06 15:16:49');
INSERT INTO `sys_menu` VALUES (121, 118, 0, 1, '评论管理', 'Comment', 'blog/blog_comment/index', 3, 'message', 'blog/blog_comment', b'0', b'0', b'0', NULL, 'admin', 'admin', '2020-12-06 15:25:22', '2020-12-06 15:26:47');
INSERT INTO `sys_menu` VALUES (122, 118, 0, 1, '图片管理', 'Img', 'blog/blog_img/index', 4, 'image', 'blog/blog_img', b'0', b'0', b'0', NULL, 'admin', 'admin', '2020-12-06 15:26:34', '2020-12-06 15:26:34');
INSERT INTO `sys_menu` VALUES (123, 118, 0, 1, '点赞管理', 'Like', 'blog/blog/index', 5, 'people', 'blog/blog_like', b'0', b'0', b'0', NULL, 'admin', 'admin', '2020-12-06 15:28:26', '2020-12-06 15:28:26');
INSERT INTO `sys_menu` VALUES (124, 118, 0, 1, '转发管理', 'Repost', 'blog/blog_repost/index', 6, 'link', 'blog/blog_repost', b'0', b'0', b'0', NULL, 'admin', 'admin', '2020-12-06 15:30:11', '2020-12-06 15:30:11');
INSERT INTO `sys_menu` VALUES (125, 118, 0, 1, '评论点赞', 'CommentLike', 'blog/comment_like/index', 7, 'nested', 'blog/comment_list', b'0', b'0', b'0', NULL, 'admin', 'admin', '2020-12-06 15:32:48', '2020-12-06 15:32:48');
INSERT INTO `sys_menu` VALUES (126, 118, 0, 1, '动态用户管理', 'DiaryUser', 'blog/diary_user/index', 0, 'Steve-Jobs', 'blog/diary_user', b'0', b'0', b'0', NULL, 'admin', 'admin', '2020-12-06 20:17:06', '2020-12-06 20:17:06');

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job`  (
  `job_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) NULL DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `person_in_charge` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报警邮箱',
  `sub_task` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子任务ID',
  `pause_after_failure` bit(1) NULL DEFAULT NULL COMMENT '任务失败后是否暂停',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`) USING BTREE,
  INDEX `inx_is_pause`(`is_pause`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES (2, 'testTask', '0/5 * * * * ?', b'1', '测试1', 'run1', 'test', '带参测试，多参使用json', '测试', NULL, NULL, NULL, NULL, 'admin', '2019-08-22 14:08:29', '2020-05-24 13:58:33');
INSERT INTO `sys_quartz_job` VALUES (3, 'testTask', '0/5 * * * * ?', b'1', '测试', 'run', '', '不带参测试', 'Zheng Jie', '', '5,6', b'1', NULL, 'admin', '2019-09-26 16:44:39', '2020-05-24 14:48:12');
INSERT INTO `sys_quartz_job` VALUES (5, 'Test', '0/5 * * * * ?', b'1', '任务告警测试', 'run', NULL, '测试', 'test', '', NULL, b'1', 'admin', 'admin', '2020-05-05 20:32:41', '2020-05-05 20:36:13');
INSERT INTO `sys_quartz_job` VALUES (6, 'testTask', '0/5 * * * * ?', b'1', '测试3', 'run2', NULL, '测试3', 'Zheng Jie', '', NULL, b'1', 'admin', 'admin', '2020-05-05 20:35:41', '2020-05-05 20:36:07');

-- ----------------------------
-- Table structure for sys_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_log`;
CREATE TABLE `sys_quartz_log`  (
  `log_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `is_success` bit(1) NULL DEFAULT NULL,
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 151 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务日志' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_quartz_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `level` int(0) NULL DEFAULT NULL COMMENT '角色级别',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `data_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name`) USING BTREE,
  INDEX `role_name_index`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 1, '-', '全部', NULL, 'admin', '2018-11-23 11:04:37', '2020-12-06 20:17:17');
INSERT INTO `sys_role` VALUES (2, '普通用户', 2, '-', '本级', NULL, 'admin', '2018-11-23 13:09:06', '2020-09-05 10:45:12');

-- ----------------------------
-- Table structure for sys_roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_depts`;
CREATE TABLE `sys_roles_depts`  (
  `role_id` bigint(0) NOT NULL,
  `dept_id` bigint(0) NOT NULL,
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE,
  INDEX `FK7qg6itn5ajdoa9h9o78v9ksur`(`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色部门关联' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_roles_depts
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_menus`;
CREATE TABLE `sys_roles_menus`  (
  `menu_id` bigint(0) NOT NULL COMMENT '菜单ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_id`, `role_id`) USING BTREE,
  INDEX `FKcngg2qadojhi3a651a5adkvbq`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_roles_menus
-- ----------------------------
INSERT INTO `sys_roles_menus` VALUES (1, 1);
INSERT INTO `sys_roles_menus` VALUES (2, 1);
INSERT INTO `sys_roles_menus` VALUES (3, 1);
INSERT INTO `sys_roles_menus` VALUES (5, 1);
INSERT INTO `sys_roles_menus` VALUES (6, 1);
INSERT INTO `sys_roles_menus` VALUES (7, 1);
INSERT INTO `sys_roles_menus` VALUES (9, 1);
INSERT INTO `sys_roles_menus` VALUES (10, 1);
INSERT INTO `sys_roles_menus` VALUES (11, 1);
INSERT INTO `sys_roles_menus` VALUES (14, 1);
INSERT INTO `sys_roles_menus` VALUES (15, 1);
INSERT INTO `sys_roles_menus` VALUES (18, 1);
INSERT INTO `sys_roles_menus` VALUES (19, 1);
INSERT INTO `sys_roles_menus` VALUES (21, 1);
INSERT INTO `sys_roles_menus` VALUES (22, 1);
INSERT INTO `sys_roles_menus` VALUES (23, 1);
INSERT INTO `sys_roles_menus` VALUES (24, 1);
INSERT INTO `sys_roles_menus` VALUES (27, 1);
INSERT INTO `sys_roles_menus` VALUES (28, 1);
INSERT INTO `sys_roles_menus` VALUES (30, 1);
INSERT INTO `sys_roles_menus` VALUES (32, 1);
INSERT INTO `sys_roles_menus` VALUES (33, 1);
INSERT INTO `sys_roles_menus` VALUES (34, 1);
INSERT INTO `sys_roles_menus` VALUES (35, 1);
INSERT INTO `sys_roles_menus` VALUES (36, 1);
INSERT INTO `sys_roles_menus` VALUES (37, 1);
INSERT INTO `sys_roles_menus` VALUES (38, 1);
INSERT INTO `sys_roles_menus` VALUES (39, 1);
INSERT INTO `sys_roles_menus` VALUES (41, 1);
INSERT INTO `sys_roles_menus` VALUES (44, 1);
INSERT INTO `sys_roles_menus` VALUES (45, 1);
INSERT INTO `sys_roles_menus` VALUES (46, 1);
INSERT INTO `sys_roles_menus` VALUES (48, 1);
INSERT INTO `sys_roles_menus` VALUES (49, 1);
INSERT INTO `sys_roles_menus` VALUES (50, 1);
INSERT INTO `sys_roles_menus` VALUES (52, 1);
INSERT INTO `sys_roles_menus` VALUES (53, 1);
INSERT INTO `sys_roles_menus` VALUES (54, 1);
INSERT INTO `sys_roles_menus` VALUES (56, 1);
INSERT INTO `sys_roles_menus` VALUES (57, 1);
INSERT INTO `sys_roles_menus` VALUES (58, 1);
INSERT INTO `sys_roles_menus` VALUES (60, 1);
INSERT INTO `sys_roles_menus` VALUES (61, 1);
INSERT INTO `sys_roles_menus` VALUES (62, 1);
INSERT INTO `sys_roles_menus` VALUES (64, 1);
INSERT INTO `sys_roles_menus` VALUES (65, 1);
INSERT INTO `sys_roles_menus` VALUES (66, 1);
INSERT INTO `sys_roles_menus` VALUES (73, 1);
INSERT INTO `sys_roles_menus` VALUES (74, 1);
INSERT INTO `sys_roles_menus` VALUES (75, 1);
INSERT INTO `sys_roles_menus` VALUES (77, 1);
INSERT INTO `sys_roles_menus` VALUES (78, 1);
INSERT INTO `sys_roles_menus` VALUES (79, 1);
INSERT INTO `sys_roles_menus` VALUES (80, 1);
INSERT INTO `sys_roles_menus` VALUES (82, 1);
INSERT INTO `sys_roles_menus` VALUES (83, 1);
INSERT INTO `sys_roles_menus` VALUES (90, 1);
INSERT INTO `sys_roles_menus` VALUES (92, 1);
INSERT INTO `sys_roles_menus` VALUES (93, 1);
INSERT INTO `sys_roles_menus` VALUES (94, 1);
INSERT INTO `sys_roles_menus` VALUES (97, 1);
INSERT INTO `sys_roles_menus` VALUES (98, 1);
INSERT INTO `sys_roles_menus` VALUES (102, 1);
INSERT INTO `sys_roles_menus` VALUES (103, 1);
INSERT INTO `sys_roles_menus` VALUES (104, 1);
INSERT INTO `sys_roles_menus` VALUES (105, 1);
INSERT INTO `sys_roles_menus` VALUES (106, 1);
INSERT INTO `sys_roles_menus` VALUES (107, 1);
INSERT INTO `sys_roles_menus` VALUES (108, 1);
INSERT INTO `sys_roles_menus` VALUES (109, 1);
INSERT INTO `sys_roles_menus` VALUES (110, 1);
INSERT INTO `sys_roles_menus` VALUES (111, 1);
INSERT INTO `sys_roles_menus` VALUES (112, 1);
INSERT INTO `sys_roles_menus` VALUES (113, 1);
INSERT INTO `sys_roles_menus` VALUES (114, 1);
INSERT INTO `sys_roles_menus` VALUES (116, 1);
INSERT INTO `sys_roles_menus` VALUES (118, 1);
INSERT INTO `sys_roles_menus` VALUES (119, 1);
INSERT INTO `sys_roles_menus` VALUES (120, 1);
INSERT INTO `sys_roles_menus` VALUES (121, 1);
INSERT INTO `sys_roles_menus` VALUES (122, 1);
INSERT INTO `sys_roles_menus` VALUES (123, 1);
INSERT INTO `sys_roles_menus` VALUES (124, 1);
INSERT INTO `sys_roles_menus` VALUES (125, 1);
INSERT INTO `sys_roles_menus` VALUES (126, 1);
INSERT INTO `sys_roles_menus` VALUES (1, 2);
INSERT INTO `sys_roles_menus` VALUES (2, 2);
INSERT INTO `sys_roles_menus` VALUES (6, 2);
INSERT INTO `sys_roles_menus` VALUES (7, 2);
INSERT INTO `sys_roles_menus` VALUES (9, 2);
INSERT INTO `sys_roles_menus` VALUES (10, 2);
INSERT INTO `sys_roles_menus` VALUES (11, 2);
INSERT INTO `sys_roles_menus` VALUES (14, 2);
INSERT INTO `sys_roles_menus` VALUES (15, 2);
INSERT INTO `sys_roles_menus` VALUES (19, 2);
INSERT INTO `sys_roles_menus` VALUES (21, 2);
INSERT INTO `sys_roles_menus` VALUES (22, 2);
INSERT INTO `sys_roles_menus` VALUES (23, 2);
INSERT INTO `sys_roles_menus` VALUES (24, 2);
INSERT INTO `sys_roles_menus` VALUES (27, 2);
INSERT INTO `sys_roles_menus` VALUES (30, 2);
INSERT INTO `sys_roles_menus` VALUES (32, 2);
INSERT INTO `sys_roles_menus` VALUES (33, 2);
INSERT INTO `sys_roles_menus` VALUES (34, 2);
INSERT INTO `sys_roles_menus` VALUES (36, 2);
INSERT INTO `sys_roles_menus` VALUES (80, 2);
INSERT INTO `sys_roles_menus` VALUES (82, 2);
INSERT INTO `sys_roles_menus` VALUES (83, 2);
INSERT INTO `sys_roles_menus` VALUES (116, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dept_id` bigint(0) NULL DEFAULT NULL COMMENT '部门名称',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `avatar_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像真实路径',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `is_admin` bit(1) NULL DEFAULT b'0' COMMENT '是否为admin账号',
  `enabled` bigint(0) NULL DEFAULT NULL COMMENT '状态：1启用、0禁用',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新着',
  `pwd_reset_time` datetime(0) NULL DEFAULT NULL COMMENT '修改密码的时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `UK_kpubos9gc2cvtkb0thktkbkes`(`email`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `uniq_username`(`username`) USING BTREE,
  UNIQUE INDEX `uniq_email`(`email`) USING BTREE,
  INDEX `FK5rwmryny6jthaaxkogownknqp`(`dept_id`) USING BTREE,
  INDEX `FKpq2dhypk2qgt68nauh2by22jb`(`avatar_name`) USING BTREE,
  INDEX `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 2, 'admin', '管理员', '男', '18888888888', '201507802@qq.com', 'avatar-20201206032351812.png', 'C:\\eladmin\\avatar\\avatar-20201206032351812.png', '$2a$10$Egp1/gvFlt7zhlXVfEFw4OfWQCGPw0ClmMcc6FjTnvXNRVf9zdMRa', b'1', 1, NULL, 'admin', '2020-05-03 16:38:31', '2018-08-23 09:11:56', '2020-12-06 15:23:52');
INSERT INTO `sys_user` VALUES (2, 2, 'test', '测试', '男', '19999999999', '231@qq.com', NULL, NULL, '$2a$10$4XcyudOYTSz6fue6KFNMHeUQnCX5jbBQypLEnGk1PmekXt5c95JcK', b'0', 1, 'admin', 'admin', NULL, '2020-05-05 11:15:49', '2020-09-05 10:43:38');

-- ----------------------------
-- Table structure for sys_users_jobs
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_jobs`;
CREATE TABLE `sys_users_jobs`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `job_id` bigint(0) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `job_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_users_jobs
-- ----------------------------
INSERT INTO `sys_users_jobs` VALUES (1, 11);
INSERT INTO `sys_users_jobs` VALUES (2, 12);

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FKq4eq273l04bpu4efj0jd0jb98`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
INSERT INTO `sys_users_roles` VALUES (1, 1);
INSERT INTO `sys_users_roles` VALUES (2, 2);

-- ----------------------------
-- Table structure for tool_alipay_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_alipay_config`;
CREATE TABLE `tool_alipay_config`  (
  `config_id` bigint(0) NOT NULL COMMENT 'ID',
  `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用ID',
  `charset` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `format` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型 固定格式json',
  `gateway_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网关地址',
  `notify_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异步回调',
  `private_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '私钥',
  `public_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公钥',
  `return_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `sign_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名方式',
  `sys_service_provider_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户号',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付宝配置类' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tool_alipay_config
-- ----------------------------
INSERT INTO `tool_alipay_config` VALUES (1, '2016091700532697', 'utf-8', 'JSON', 'https://openapi.alipaydev.com/gateway.do', 'http://api.auauz.net/api/aliPay/notify', 'MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC5js8sInU10AJ0cAQ8UMMyXrQ+oHZEkVt5lBwsStmTJ7YikVYgbskx1YYEXTojRsWCb+SH/kDmDU4pK/u91SJ4KFCRMF2411piYuXU/jF96zKrADznYh/zAraqT6hvAIVtQAlMHN53nx16rLzZ/8jDEkaSwT7+HvHiS+7sxSojnu/3oV7BtgISoUNstmSe8WpWHOaWv19xyS+Mce9MY4BfseFhzTICUymUQdd/8hXA28/H6osUfAgsnxAKv7Wil3aJSgaJczWuflYOve0dJ3InZkhw5Cvr0atwpk8YKBQjy5CdkoHqvkOcIB+cYHXJKzOE5tqU7inSwVbHzOLQ3XbnAgMBAAECggEAVJp5eT0Ixg1eYSqFs9568WdetUNCSUchNxDBu6wxAbhUgfRUGZuJnnAll63OCTGGck+EGkFh48JjRcBpGoeoHLL88QXlZZbC/iLrea6gcDIhuvfzzOffe1RcZtDFEj9hlotg8dQj1tS0gy9pN9g4+EBH7zeu+fyv+qb2e/v1l6FkISXUjpkD7RLQr3ykjiiEw9BpeKb7j5s7Kdx1NNIzhkcQKNqlk8JrTGDNInbDM6inZfwwIO2R1DHinwdfKWkvOTODTYa2MoAvVMFT9Bec9FbLpoWp7ogv1JMV9svgrcF9XLzANZ/OQvkbe9TV9GWYvIbxN6qwQioKCWO4GPnCAQKBgQDgW5MgfhX8yjXqoaUy/d1VjI8dHeIyw8d+OBAYwaxRSlCfyQ+tieWcR2HdTzPca0T0GkWcKZm0ei5xRURgxt4DUDLXNh26HG0qObbtLJdu/AuBUuCqgOiLqJ2f1uIbrz6OZUHns+bT/jGW2Ws8+C13zTCZkZt9CaQsrp3QOGDx5wKBgQDTul39hp3ZPwGNFeZdkGoUoViOSd5Lhowd5wYMGAEXWRLlU8z+smT5v0POz9JnIbCRchIY2FAPKRdVTICzmPk2EPJFxYTcwaNbVqL6lN7J2IlXXMiit5QbiLauo55w7plwV6LQmKm9KV7JsZs5XwqF7CEovI7GevFzyD3w+uizAQKBgC3LY1eRhOlpWOIAhpjG6qOoohmeXOphvdmMlfSHq6WYFqbWwmV4rS5d/6LNpNdL6fItXqIGd8I34jzql49taCmi+A2nlR/E559j0mvM20gjGDIYeZUz5MOE8k+K6/IcrhcgofgqZ2ZED1ksHdB/E8DNWCswZl16V1FrfvjeWSNnAoGAMrBplCrIW5xz+J0Hm9rZKrs+AkK5D4fUv8vxbK/KgxZ2KaUYbNm0xv39c+PZUYuFRCz1HDGdaSPDTE6WeWjkMQd5mS6ikl9hhpqFRkyh0d0fdGToO9yLftQKOGE/q3XUEktI1XvXF0xyPwNgUCnq0QkpHyGVZPtGFxwXiDvpvgECgYA5PoB+nY8iDiRaJNko9w0hL4AeKogwf+4TbCw+KWVEn6jhuJa4LFTdSqp89PktQaoVpwv92el/AhYjWOl/jVCm122f9b7GyoelbjMNolToDwe5pF5RnSpEuDdLy9MfE8LnE3PlbE7E5BipQ3UjSebkgNboLHH/lNZA5qvEtvbfvQ==', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAut9evKRuHJ/2QNfDlLwvN/S8l9hRAgPbb0u61bm4AtzaTGsLeMtScetxTWJnVvAVpMS9luhEJjt+Sbk5TNLArsgzzwARgaTKOLMT1TvWAK5EbHyI+eSrc3s7Awe1VYGwcubRFWDm16eQLv0k7iqiw+4mweHSz/wWyvBJVgwLoQ02btVtAQErCfSJCOmt0Q/oJQjj08YNRV4EKzB19+f5A+HQVAKy72dSybTzAK+3FPtTtNen/+b5wGeat7c32dhYHnGorPkPeXLtsqqUTp1su5fMfd4lElNdZaoCI7osZxWWUo17vBCZnyeXc9fk0qwD9mK6yRAxNbrY72Xx5VqIqwIDAQAB', 'http://api.auauz.net/api/aliPay/return', 'RSA2', '2088102176044281');

-- ----------------------------
-- Table structure for tool_email_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_email_config`;
CREATE TABLE `tool_email_config`  (
  `config_id` bigint(0) NOT NULL COMMENT 'ID',
  `from_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '端口',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发件者用户名',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮箱配置' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tool_email_config
-- ----------------------------

-- ----------------------------
-- Table structure for tool_local_storage
-- ----------------------------
DROP TABLE IF EXISTS `tool_local_storage`;
CREATE TABLE `tool_local_storage`  (
  `storage_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `size` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大小',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`storage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '本地存储' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tool_local_storage
-- ----------------------------

-- ----------------------------
-- Table structure for tool_qiniu_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_qiniu_config`;
CREATE TABLE `tool_qiniu_config`  (
  `config_id` bigint(0) NOT NULL COMMENT 'ID',
  `access_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'accessKey',
  `bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Bucket 识别符',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外链域名',
  `secret_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'secretKey',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '空间类型',
  `zone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机房',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '七牛云配置' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tool_qiniu_config
-- ----------------------------

-- ----------------------------
-- Table structure for tool_qiniu_content
-- ----------------------------
DROP TABLE IF EXISTS `tool_qiniu_content`;
CREATE TABLE `tool_qiniu_content`  (
  `content_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Bucket 识别符',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件大小',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型：私有或公开',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件url',
  `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '上传或同步的时间',
  PRIMARY KEY (`content_id`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '七牛云文件存储' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tool_qiniu_content
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
