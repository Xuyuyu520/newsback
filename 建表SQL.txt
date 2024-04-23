-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.36 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  12.6.0.6812
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 newsmanage 的数据库结构
CREATE DATABASE IF NOT EXISTS `newsmanage` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `newsmanage`;

-- 导出  表 newsmanage.article 结构
CREATE TABLE IF NOT EXISTS `article` (
  `article_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章id',
  `article_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '新闻名',
  `article_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '新闻图片地址',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者',
  `type` int NOT NULL COMMENT '新闻类型',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `count` int NOT NULL COMMENT '数量',
  PRIMARY KEY (`article_id`) USING BTREE,
  KEY `type` (`type`) USING BTREE,
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`type`) REFERENCES `category` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  newsmanage.article 的数据：~5 rows (大约)
INSERT INTO `article` (`article_id`, `article_name`, `article_url`, `author`, `type`, `price`, `count`) VALUES
	('7322c035c1ff4c2ab1909d2e9ba87372', '单号打击打击', '/upload/20240422/2145f058-aeae-4b48-8b4c-fb40d5ab3953.jpeg', '是多大', 1, 111.00, 11),
	('a88778be4bd543cdbc8dff6589c1f3a4', '单号打击打击', '/upload/20240422/2145f058-aeae-4b48-8b4c-fb40d5ab3952.jpeg', '是多大', 1, 111.00, 11),
	('ab8a4ddae6014696a98f8b1807e6504b', '单号打击打击', '/upload/20240422/2145f058-aeae-4b48-8b4c-fb40d5ab3955.jpeg', '是多大', 1, 111.00, 11),
	('d370912ceb924e44b745b5a6869a19b7', '单号打击打击', '/upload/20240422/2145f058-aeae-4b48-8b4c-fb40d5ab3954.jpeg', '是多大', 1, 111.00, 11),
	('f996f79ec52e4e0d8efd1aea3112426c', '单号的哈', '/upload/20240422/2145f058-aeae-4b48-8b4c-fb40d5ab3951.jpeg', '得到', 1, 111.00, 11);

-- 导出  表 newsmanage.branch 结构
CREATE TABLE IF NOT EXISTS `branch` (
  `branch_no` int NOT NULL AUTO_INCREMENT COMMENT '分部id',
  `cno` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分部号',
  `branch_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分部名字',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用：true 启用 false不启用',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`branch_no`) USING BTREE,
  KEY `chapter_ibfk_1` (`cno`),
  CONSTRAINT `branch_ibfk_1` FOREIGN KEY (`cno`) REFERENCES `news` (`cno`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  newsmanage.branch 的数据：~0 rows (大约)
INSERT INTO `branch` (`branch_no`, `cno`, `branch_name`, `enabled`, `update_time`, `create_time`) VALUES
	(1, '12333', 'a市日报', 1, NULL, NULL);

-- 导出  表 newsmanage.category 结构
CREATE TABLE IF NOT EXISTS `category` (
  `type_id` int NOT NULL COMMENT '新闻分类',
  `eName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  newsmanage.category 的数据：~0 rows (大约)
INSERT INTO `category` (`type_id`, `eName`, `cName`) VALUES
	(1, 'dd', '得到');

-- 导出  表 newsmanage.division 结构
CREATE TABLE IF NOT EXISTS `division` (
  `division_no` int NOT NULL AUTO_INCREMENT COMMENT '支部d',
  `branch_no` int NOT NULL COMMENT '分部id',
  `cno` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '总部号',
  `division_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '支部名字',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用：true 启用 false不启用',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`division_no`) USING BTREE,
  KEY `section_ibfk_1` (`cno`) USING BTREE,
  KEY `section_ibfk_2` (`branch_no`) USING BTREE,
  CONSTRAINT `division_ibfk_1` FOREIGN KEY (`cno`) REFERENCES `news` (`cno`) ON UPDATE CASCADE,
  CONSTRAINT `division_ibfk_2` FOREIGN KEY (`branch_no`) REFERENCES `branch` (`branch_no`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支部';

-- 正在导出表  newsmanage.division 的数据：~0 rows (大约)
INSERT INTO `division` (`division_no`, `branch_no`, `cno`, `division_name`, `enabled`, `update_time`, `create_time`) VALUES
	(2, 1, '12333', 'a县日报', 1, NULL, NULL);

-- 导出  表 newsmanage.file_info 结构
CREATE TABLE IF NOT EXISTS `file_info` (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件id',
  `cno` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属新闻总部',
  `resource_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源类型',
  `new_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件新名称',
  `old_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件原始名称',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '存储路径',
  `size` decimal(10,2) NOT NULL COMMENT '文件大小',
  `suffix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件后缀',
  `type` int NOT NULL COMMENT '类型',
  `creator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件上传人',
  `download_num` int NOT NULL DEFAULT '0' COMMENT '下载次数',
  `branch` int NOT NULL COMMENT '分部',
  `division` int NOT NULL COMMENT '支部',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `file_ibfk_1` (`cno`) USING BTREE,
  KEY `file_ibfk_2` (`type`) USING BTREE,
  KEY `file_ibfk_3` (`branch`) USING BTREE,
  KEY `file_ibfk_4` (`division`) USING BTREE,
  CONSTRAINT `file_ibfk_1` FOREIGN KEY (`cno`) REFERENCES `news` (`cno`) ON UPDATE CASCADE,
  CONSTRAINT `file_ibfk_2` FOREIGN KEY (`type`) REFERENCES `file_type` (`type_id`) ON UPDATE CASCADE,
  CONSTRAINT `file_ibfk_3` FOREIGN KEY (`branch`) REFERENCES `branch` (`branch_no`) ON UPDATE CASCADE,
  CONSTRAINT `file_ibfk_4` FOREIGN KEY (`division`) REFERENCES `division` (`division_no`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  newsmanage.file_info 的数据：~0 rows (大约)
INSERT INTO `file_info` (`id`, `cno`, `resource_type`, `new_name`, `old_name`, `url`, `size`, `suffix`, `type`, `creator`, `download_num`, `branch`, `division`, `upload_time`, `update_time`) VALUES
	('5b978c294cbe4c21a18377d078383a5f', '12333', 'image/jpeg', 'e255f6c376aa40c69e4f9ac1c6ecc4e4.jpeg', '4k1.jpeg', '/upload/resource/20240422/e255f6c376aa40c69e4f9ac1c6ecc4e4.jpeg', 329.00, '.jpeg', 1, '张三', 0, 1, 2, '2024-04-22 19:29:20', '2024-04-22 19:29:20');

-- 导出  表 newsmanage.file_type 结构
CREATE TABLE IF NOT EXISTS `file_type` (
  `type_id` int NOT NULL COMMENT '新闻类型',
  `eName` varchar(50) DEFAULT NULL,
  `cName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  newsmanage.file_type 的数据：~4 rows (大约)
INSERT INTO `file_type` (`type_id`, `eName`, `cName`) VALUES
	(1, 'image', '图片'),
	(2, 'pdf', '幻灯片'),
	(3, 'music', '音乐'),
	(4, 'video', '视频'),
	(11, 'wp', '网盘');

-- 导出  表 newsmanage.news 结构
CREATE TABLE IF NOT EXISTS `news` (
  `cno` varchar(40) NOT NULL COMMENT '主键id',
  `cname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '新闻总部名',
  `tname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '新闻分部名',
  `url` varchar(100) NOT NULL COMMENT '图片地址',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用：true 启用 false不启用',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`cno`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  newsmanage.news 的数据：~2 rows (大约)
INSERT INTO `news` (`cno`, `cname`, `tname`, `url`, `enabled`, `update_time`, `create_time`) VALUES
	('12333', 'a省日报', 'a省', '/upload/20240422/0e2ea939-300b-4fbd-b664-454b77010fdb.jpeg', 1, NULL, NULL),
	('12334', 'b省日报', 'b省', '/upload/20240422/fc5378cd-5a67-409e-83f3-7995eb976e8c.jpeg', 1, NULL, NULL);

-- 导出  表 newsmanage.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `password` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  newsmanage.user 的数据：~2 rows (大约)
INSERT INTO `user` (`username`, `nickname`, `password`, `phone`, `created`, `role`) VALUES
	('admin', '小徐', '$2a$10$yd62eVgXWnWo4.sVdqt5dO.of2hNikOhBUAmTmy6qG1dfEiUbUioe', '123456', '2024-04-21 21:00:00', 'admin'),
	('user', '用户', '$2a$10$ssLY5/B0W4mE6UL5uvbareEbWT.EAGzG.ak6OX8LzrAVJFyten9r2', '123', '2024-04-22 21:03:14', 'user');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
