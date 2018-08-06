SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- database for form
-- ----------------------------
DROP DATABASE IF EXISTS `form`;
CREATE DATABASE `form` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `form`;

-- -------------------------------
-- Table structure for users
-- -------------------------------
DROP TABLE IF EXISTS `suser`;
CREATE TABLE `suser` (
  `userid` INT(10) PRIMARY KEY AUTO_INCREMENT COMMENT '用户编号',
  `username` VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
  `password` VARCHAR(256) NOT NULL COMMENT '密码',
  `email` VARCHAR(50) NOT NULL UNIQUE COMMENT '邮箱'
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin;

-- -------------------------------
-- Table structure for forms
-- -------------------------------
DROP TABLE IF EXISTS `sform`;
CREATE TABLE `sform`(
  `formid` INT(10) PRIMARY KEY AUTO_INCREMENT COMMENT '表单编号',
  `uuid` VARCHAR(256) NOT NULL UNIQUE COMMENT '表单序列号',
  `formname` VARCHAR(128) NOT NULL COMMENT '表单名',
  `formpath` VARCHAR(256) NOT NULL COMMENT '表单路径',
  `formstatus` INT(1) NOT NULL DEFAULT 0 COMMENT '表单状态:0表示允许填写,1表示拒绝填写', 
  `formdesc` TEXT NOT NULL COMMENT '表单描述',
  `begintime` VARCHAR(64) NOT NULL COMMENT '开始时间',
  `endtime` VARCHAR(64) NOT NULL COMMENT '结束时间',
  `userid` INT(10) COMMENT '创建用户编号',
  `password` VARCHAR(256) NOT NULL COMMENT '填写密码',
   FOREIGN KEY(userid) REFERENCES suser(userid) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin;


-- -------------------------------
-- Table structure for feedbacks
-- -------------------------------
DROP TABLE IF EXISTS `sfeedback`;
CREATE TABLE `sfeedback`(
  `feedbackid` INT(10) PRIMARY KEY AUTO_INCREMENT COMMENT '反馈编号',
  `formid` INT(10) COMMENT '所属表单编号',
  `rownumber` INT(10) COMMENT '所在行数',
  `feedbacktime` VARCHAR(64) NOT NULL COMMENT '反馈时间',
  `feedbackstatus` INT(1) NOT NULL DEFAULT 0 COMMENT '反馈状态:0表示待处理,1表示已处理',
   FOREIGN KEY(formid)  REFERENCES sform(formid) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin;



SET FOREIGN_KEY_CHECKS = 1;