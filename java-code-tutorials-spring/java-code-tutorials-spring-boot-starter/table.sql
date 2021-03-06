/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.25 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `test`;

/*Table structure for table `GB_SYS_USER` */

DROP TABLE IF EXISTS `GB_SYS_USER`;

CREATE TABLE `GB_SYS_USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT` varchar(20) NOT NULL DEFAULT 'guest',
  `PASSWORD` char(32) NOT NULL DEFAULT '',
  `SEX` char(1) DEFAULT '0',
  `REAL_NAME` varchar(80) DEFAULT '',
  `NICK_NAME` varchar(40) DEFAULT '',
  `BIRTHDAY` date DEFAULT '0000-00-00',
  `DELETE_FLAG` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `unique_active_account` (`ACCOUNT`,`DELETE_FLAG`),
  KEY `idx_nick_name` (`NICK_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
