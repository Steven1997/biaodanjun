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
  `userid` INT(10) PRIMARY KEY AUTO_INCREMENT COMMENT '�û����',
  `username` VARCHAR(20) NOT NULL UNIQUE COMMENT '�û���',
  `password` VARCHAR(256) NOT NULL COMMENT '����',
  `email` VARCHAR(50) NOT NULL UNIQUE COMMENT '����'
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin;

-- -------------------------------
-- Table structure for forms
-- -------------------------------
DROP TABLE IF EXISTS `sform`;
CREATE TABLE `sform`(
  `formid` INT(10) PRIMARY KEY AUTO_INCREMENT COMMENT '�����',
  `uuid` VARCHAR(256) NOT NULL UNIQUE COMMENT '�����к�',
  `formname` VARCHAR(128) NOT NULL COMMENT '����',
  `formpath` VARCHAR(256) NOT NULL COMMENT '��·��',
  `formstatus` INT(1) NOT NULL DEFAULT 0 COMMENT '��״̬:0��ʾ������д,1��ʾ�ܾ���д', 
  `formdesc` TEXT NOT NULL COMMENT '������',
  `begintime` VARCHAR(64) NOT NULL COMMENT '��ʼʱ��',
  `endtime` VARCHAR(64) NOT NULL COMMENT '����ʱ��',
  `userid` INT(10) COMMENT '�����û����',
  `password` VARCHAR(256) NOT NULL COMMENT '��д����',
   FOREIGN KEY(userid) REFERENCES suser(userid) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin;


-- -------------------------------
-- Table structure for feedbacks
-- -------------------------------
DROP TABLE IF EXISTS `sfeedback`;
CREATE TABLE `sfeedback`(
  `feedbackid` INT(10) PRIMARY KEY AUTO_INCREMENT COMMENT '�������',
  `formid` INT(10) COMMENT '���������',
  `rownumber` INT(10) COMMENT '��������',
  `feedbacktime` VARCHAR(64) NOT NULL COMMENT '����ʱ��',
  `feedbackstatus` INT(1) NOT NULL DEFAULT 0 COMMENT '����״̬:0��ʾ������,1��ʾ�Ѵ���',
   FOREIGN KEY(formid)  REFERENCES sform(formid) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin;



SET FOREIGN_KEY_CHECKS = 1;