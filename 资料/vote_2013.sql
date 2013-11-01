# Host: localhost  (Version: 5.5.34)
# Date: 2013-11-01 09:23:38
# Generator: MySQL-Front 5.3  (Build 4.43)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "t_admin"
#

DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "t_admin"
#


#
# Structure for table "t_dept"
#

DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) DEFAULT NULL,
  `deptName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "t_dept"
#


#
# Structure for table "t_vote_system"
#

DROP TABLE IF EXISTS `t_vote_system`;
CREATE TABLE `t_vote_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `systemYear` int(11) DEFAULT NULL,
  `allowLogin` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "t_vote_system"
#


#
# Structure for table "t_votee"
#

DROP TABLE IF EXISTS `t_votee`;
CREATE TABLE `t_votee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL COMMENT '被投票人的名字',
  `isLeadership` int(11) DEFAULT NULL,
  `report` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='被投票人';

#
# Data for table "t_votee"
#


#
# Structure for table "t_ballot"
#

DROP TABLE IF EXISTS `t_ballot`;
CREATE TABLE `t_ballot` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) DEFAULT NULL,
  `voteeId` int(11) DEFAULT NULL COMMENT '被选举人',
  `overallMerit` int(11) DEFAULT NULL COMMENT '综合评价',
  `ideology` int(11) DEFAULT NULL COMMENT '思想品德',
  `workingAbility` int(11) DEFAULT NULL COMMENT '工作能力',
  `professionalEthics` int(11) DEFAULT NULL COMMENT '敬业精神',
  `clean` int(11) DEFAULT NULL COMMENT '廉洁自律',
  `innovationAbility` int(11) DEFAULT NULL COMMENT '创新能力',
  `completionOfJobs` int(11) DEFAULT NULL COMMENT '定量考核(工作目标完成情况)',
  PRIMARY KEY (`Id`),
  KEY `fk_ballot_voteeId_votee_id` (`voteeId`),
  CONSTRAINT `fk_ballot_voteeId_votee_id` FOREIGN KEY (`voteeId`) REFERENCES `t_votee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "t_ballot"
#


#
# Structure for table "t_votee_dept"
#

DROP TABLE IF EXISTS `t_votee_dept`;
CREATE TABLE `t_votee_dept` (
  `voteeId` int(11) NOT NULL,
  `deptId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`voteeId`,`deptId`),
  KEY `fk_votee_dept_deptId_dept_id` (`deptId`),
  CONSTRAINT `fk_votee_dept_deptId_dept_id` FOREIGN KEY (`deptId`) REFERENCES `t_dept` (`id`),
  CONSTRAINT `fk_votee_dept_voteeId_votee_id` FOREIGN KEY (`voteeId`) REFERENCES `t_votee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "t_votee_dept"
#


#
# Structure for table "t_voter"
#

DROP TABLE IF EXISTS `t_voter`;
CREATE TABLE `t_voter` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `deptId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_voter_deptId_dept_id` (`deptId`),
  CONSTRAINT `fk_voter_deptId_dept_id` FOREIGN KEY (`deptId`) REFERENCES `t_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='选民，投票人。';

#
# Data for table "t_voter"
#


#
# Structure for table "t_log"
#

DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `year` int(11) DEFAULT NULL,
  `voterId` int(11) DEFAULT NULL COMMENT '选举人，投票人，选民',
  `voteeId` int(11) DEFAULT NULL COMMENT '被投票人',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_log_voterId_voter_id` (`voterId`),
  KEY `fk_log_voteeId_votee_id` (`voteeId`),
  CONSTRAINT `fk_log_voteeId_votee_id` FOREIGN KEY (`voteeId`) REFERENCES `t_votee` (`id`),
  CONSTRAINT `fk_log_voterId_voter_id` FOREIGN KEY (`voterId`) REFERENCES `t_voter` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "t_log"
#


#
# Structure for table "t_voter_dept"
#

DROP TABLE IF EXISTS `t_voter_dept`;
CREATE TABLE `t_voter_dept` (
  `voterId` int(11) NOT NULL DEFAULT '0',
  `deptId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`voterId`,`deptId`),
  KEY `fk_voter_dept_deptId_dept_id` (`deptId`),
  CONSTRAINT `fk_voter_dept_deptId_dept_id` FOREIGN KEY (`deptId`) REFERENCES `t_dept` (`id`),
  CONSTRAINT `fk_voter_dept_voterId_voter_id` FOREIGN KEY (`voterId`) REFERENCES `t_voter` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "t_voter_dept"
#

