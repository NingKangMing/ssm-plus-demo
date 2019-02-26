-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1:3306
-- 生成日期： 2019-02-26 08:03:55
-- 服务器版本： 5.7.24
-- PHP 版本： 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `lichuanweb`
--

-- --------------------------------------------------------

--
-- 表的结构 `t_user`
--

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint(20) NOT NULL COMMENT '用户表id',
  `username` varchar(50) DEFAULT NULL COMMENT '账号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `relname` varchar(50) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `idcard` varchar(50) DEFAULT NULL COMMENT '身份证',
  `department_id` bigint(20) DEFAULT NULL COMMENT '部门表id',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `email` varchar(50) DEFAULT NULL COMMENT 'email',
  `duty` varchar(100) DEFAULT NULL COMMENT '职务',
  `reg_ip` varchar(50) DEFAULT NULL COMMENT '注册IP',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `credentials_salt` varchar(255) DEFAULT NULL COMMENT '密码盐',
  `input_user` varchar(50) DEFAULT NULL COMMENT '录入人',
  `create_time` datetime DEFAULT NULL COMMENT '录入时间',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  `status` tinyint(3) DEFAULT '0' COMMENT '状态,0：正常；1：删除',
  `u_locked` bit(1) DEFAULT b'0' COMMENT '是否锁定',
  `spare0` varchar(50) DEFAULT NULL COMMENT '备用字段',
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `spare4` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户表';
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
