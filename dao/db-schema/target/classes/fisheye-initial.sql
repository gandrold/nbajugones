CREATE DATABASE  IF NOT EXISTS `fisheye` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fisheye`;
-- MySQL dump 10.13  Distrib 5.6.18, for Win32 (x86)
--
-- Host: localhost    Database: fisheye
-- ------------------------------------------------------
-- Server version	5.6.18-enterprise-commercial-advanced

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `AddressId` int(11) NOT NULL AUTO_INCREMENT,
  `Address1` varchar(250) NOT NULL,
  `Address2` varchar(250) DEFAULT NULL,
  `City` varchar(250) NOT NULL,
  `PostCode` varchar(250) NOT NULL,
  `CountryCode` varchar(250) NOT NULL,
  PRIMARY KEY (`AddressId`),
  KEY `CountryCode` (`CountryCode`),
  KEY `FK1ED033D4AF497FAC` (`AddressId`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`CountryCode`) REFERENCES `country` (`CountryCode`)
) ENGINE=InnoDB AUTO_INCREMENT=401 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'line1','line2','London','se26 5jd','UK'),(82,'newline1','newline2','London','se26 5jd','UK');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `addresstype`
--

DROP TABLE IF EXISTS `addresstype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresstype` (
  `AddressTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(250) NOT NULL,
  PRIMARY KEY (`AddressTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresstype`
--

LOCK TABLES `addresstype` WRITE;
/*!40000 ALTER TABLE `addresstype` DISABLE KEYS */;
INSERT INTO `addresstype` VALUES (1,'Home'),(2,'Work');
/*!40000 ALTER TABLE `addresstype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `CountryCode` varchar(5) NOT NULL,
  `CountryName` varchar(250) NOT NULL,
  PRIMARY KEY (`CountryCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES ('UK','United Kingdom');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email`
--

DROP TABLE IF EXISTS `email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email` (
  `EmailId` int(11) NOT NULL AUTO_INCREMENT,
  `PersonId` int(11) DEFAULT NULL,
  `EmailAddress` varchar(250) NOT NULL,
  `UpdateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `VerificationCode` varchar(45) DEFAULT NULL,
  `IsEmailVerified` bit(1) NOT NULL DEFAULT b'0',
  `verificationCodeDate` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`EmailId`),
  KEY `FK3FF5B7C1986E554` (`EmailId`),
  KEY `fk_email_person_idx` (`PersonId`),
  CONSTRAINT `email_ibfk_1` FOREIGN KEY (`PersonId`) REFERENCES `person` (`PersonId`),
  CONSTRAINT `fk_email_person` FOREIGN KEY (`PersonId`) REFERENCES `person` (`PersonId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1501 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email`
--

LOCK TABLES `email` WRITE;
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` VALUES (1,1,'test@test.com','2014-06-21 09:00:10','12345678','',NULL),(2,NULL,'jkkjk','2014-06-21 09:00:10','32423432','','2014-06-21 09:00:10'),(143,NULL,'test1@test1.com','2014-06-21 10:04:07','6589347fb56cb4e310d6c4f6e8f5ec1a','','2014-06-21 09:57:49'),(618,NULL,'test12@test12.com','2014-06-21 11:40:41','5e1abea6c5e7992c919964d6cee7bda0','\0','2014-06-21 11:40:41'),(619,NULL,'test123@test12.com','2014-06-21 11:41:06','b6a4121294510af07b210b7d280c23e1','\0','2014-06-21 11:41:06'),(641,620,'test1234@test12.com','2014-06-22 15:53:06','eedba29190370dcd6295dfa5345e86cd','\0','2014-06-22 15:05:38'),(642,NULL,'test12345@test12.com','2014-06-22 15:55:36','dab506f92764011f153601371de71a7c','\0','2014-06-22 15:55:36'),(757,736,'test123456@test12.com','2014-06-22 16:05:09','cde7cb8e33b6620cf763b86ae65aa95a','\0',NULL);
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_templates`
--

DROP TABLE IF EXISTS `email_templates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_templates` (
  `template_id` int(11) NOT NULL AUTO_INCREMENT,
  `from` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `subject` varchar(200) NOT NULL,
  `body` varchar(4000) NOT NULL,
  `locale` varchar(45) NOT NULL,
  `emailType` varchar(45) NOT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_templates`
--

LOCK TABLES `email_templates` WRITE;
/*!40000 ALTER TABLE `email_templates` DISABLE KEYS */;
INSERT INTO `email_templates` VALUES (1,'test@planfisheye.com','Email registration','Email registration','Hi %token%!','en','TEST'),(2,'test@planfisheye.com','Phone registration','Phone registration','the token is %token%','en','TEST'),(3,'test@planfisheye.com','Password reset','Password reset','Token for password recovery is %token%','en','TEST'),(4,'test@planfisheye.com','Lookout information from FE','Lookout information from FE','Hello %name% <br/> The lookout you have with %otherName% has been %state%','en','TEST'),(5,'test@planfisheye.com','Lookout information from FE','Lookout information from FE','Hello %name% <br/> %otherName% has requested you to be a lookout. Please log in to FE in order to awnser it. ','en','TEST');
/*!40000 ALTER TABLE `email_templates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lookoutactivity`
--

DROP TABLE IF EXISTS `lookoutactivity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lookoutactivity` (
  `LookoutActivityId` int(11) NOT NULL AUTO_INCREMENT,
  `SessionId` int(11) NOT NULL,
  `LookoutActivityTypeId` int(11) DEFAULT NULL,
  `Comment` varchar(2500) DEFAULT NULL,
  `CreatedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`LookoutActivityId`),
  KEY `SessionId` (`SessionId`),
  KEY `LookoutActivityTypeId` (`LookoutActivityTypeId`),
  CONSTRAINT `lookoutactivity_ibfk_1` FOREIGN KEY (`SessionId`) REFERENCES `session` (`SessionId`),
  CONSTRAINT `lookoutactivity_ibfk_2` FOREIGN KEY (`LookoutActivityTypeId`) REFERENCES `lookoutactivitytype` (`LookoutActivityTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lookoutactivity`
--

LOCK TABLES `lookoutactivity` WRITE;
/*!40000 ALTER TABLE `lookoutactivity` DISABLE KEYS */;
/*!40000 ALTER TABLE `lookoutactivity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lookoutactivitytype`
--

DROP TABLE IF EXISTS `lookoutactivitytype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lookoutactivitytype` (
  `LookoutActivityTypeId` int(11) NOT NULL,
  `Description` int(11) NOT NULL,
  PRIMARY KEY (`LookoutActivityTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lookoutactivitytype`
--

LOCK TABLES `lookoutactivitytype` WRITE;
/*!40000 ALTER TABLE `lookoutactivitytype` DISABLE KEYS */;
/*!40000 ALTER TABLE `lookoutactivitytype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lookoutcontact`
--

DROP TABLE IF EXISTS `lookoutcontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lookoutcontact` (
  `SessionId` int(11) NOT NULL,
  `RelationId` int(11) NOT NULL DEFAULT '0',
  `StartDate` timestamp NULL DEFAULT NULL,
  `EndDate` timestamp NULL DEFAULT NULL,
  `CreatedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Priority` int(11) DEFAULT NULL,
  `Active` bit(1) NOT NULL,
  PRIMARY KEY (`SessionId`,`RelationId`),
  KEY `RelationId` (`RelationId`),
  CONSTRAINT `lookoutcontact_ibfk_1` FOREIGN KEY (`RelationId`) REFERENCES `relation` (`RelationId`),
  CONSTRAINT `lookoutcontact_ibfk_2` FOREIGN KEY (`SessionId`) REFERENCES `session` (`SessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lookoutcontact`
--

LOCK TABLES `lookoutcontact` WRITE;
/*!40000 ALTER TABLE `lookoutcontact` DISABLE KEYS */;
/*!40000 ALTER TABLE `lookoutcontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `PersonId` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(250) DEFAULT NULL,
  `LastName` varchar(250) DEFAULT NULL,
  `DateOfBirth` timestamp NULL DEFAULT NULL,
  `CreationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PersonId`)
) ENGINE=InnoDB AUTO_INCREMENT=1480 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Test','test','2014-06-21 09:00:10','2014-06-21 09:00:10'),(597,NULL,NULL,NULL,'2014-06-21 11:40:41'),(598,NULL,NULL,NULL,'2014-06-21 11:41:06'),(620,NULL,NULL,NULL,'2014-06-22 15:05:37'),(621,NULL,NULL,NULL,'2014-06-22 15:54:41'),(736,NULL,NULL,NULL,'2014-06-22 16:05:09');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaddress`
--

DROP TABLE IF EXISTS `personaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personaddress` (
  `PersonId` int(11) NOT NULL,
  `AddressId` int(11) NOT NULL,
  `AddressTypeId` int(11) NOT NULL,
  `CreatedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PersonId`,`AddressId`),
  KEY `AddressTypeId` (`AddressTypeId`),
  KEY `AddressId` (`AddressId`),
  CONSTRAINT `personaddress_ibfk_1` FOREIGN KEY (`AddressTypeId`) REFERENCES `addresstype` (`AddressTypeId`),
  CONSTRAINT `personaddress_ibfk_2` FOREIGN KEY (`PersonId`) REFERENCES `person` (`PersonId`),
  CONSTRAINT `personaddress_ibfk_3` FOREIGN KEY (`AddressId`) REFERENCES `address` (`AddressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personaddress`
--

LOCK TABLES `personaddress` WRITE;
/*!40000 ALTER TABLE `personaddress` DISABLE KEYS */;
INSERT INTO `personaddress` VALUES (1,82,2,'2014-06-21 09:34:27');
/*!40000 ALTER TABLE `personaddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone` (
  `phoneId` int(11) NOT NULL AUTO_INCREMENT,
  `personId` int(11) DEFAULT NULL,
  `PhoneTypeId` int(11) NOT NULL,
  `phoneNumber` varchar(25) NOT NULL,
  `updateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VerificationCode` varchar(45) DEFAULT NULL,
  `IsPhoneVerified` bit(1) DEFAULT b'0',
  `verificationCodeDate` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`phoneId`),
  KEY `PhoneTypeId` (`PhoneTypeId`),
  KEY `FK4984D4E57AAAAA6` (`phoneId`),
  KEY `phone_ibfk_1` (`personId`),
  CONSTRAINT `phone_ibfk_1` FOREIGN KEY (`personId`) REFERENCES `person` (`PersonId`),
  CONSTRAINT `phone_ibfk_2` FOREIGN KEY (`PhoneTypeId`) REFERENCES `phonetype` (`PhoneTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3500 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (1,NULL,1,'12345678','2014-06-21 09:00:10','E3R4T5','',NULL),(103,NULL,1,'1234567854','2014-06-28 10:30:22','E3R4T5','',NULL),(1442,736,1,'1234567854568','2014-06-22 17:26:05',NULL,'\0',NULL),(1443,NULL,1,'1234567854','2014-06-28 10:38:03','E3R4T5','',NULL),(1444,NULL,1,'1234567854','2014-06-28 10:39:08','E3R4T5','',NULL),(1445,NULL,1,'1234567854','2014-06-28 10:39:52','E3R4T5','',NULL),(1446,NULL,1,'1234567854','2014-06-28 10:42:13','E3R4T5','',NULL),(1447,NULL,1,'1234567854','2014-06-28 11:53:42','E3R4T5','',NULL),(1448,NULL,1,'1234567854','2014-06-28 12:53:58','E3R4T5','',NULL),(1449,1,1,'1234567854','2014-06-28 12:53:58','E3R4T5','',NULL);
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phonetype`
--

DROP TABLE IF EXISTS `phonetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phonetype` (
  `PhoneTypeId` int(11) NOT NULL,
  `Description` varchar(250) NOT NULL,
  PRIMARY KEY (`PhoneTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phonetype`
--

LOCK TABLES `phonetype` WRITE;
/*!40000 ALTER TABLE `phonetype` DISABLE KEYS */;
INSERT INTO `phonetype` VALUES (1,'Home'),(2,'Work');
/*!40000 ALTER TABLE `phonetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation`
--

DROP TABLE IF EXISTS `relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relation` (
  `RelationId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `RelatedUserId` int(11) NOT NULL,
  `RelationTypeId` int(11) DEFAULT NULL,
  `CreationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `position` int(11) DEFAULT NULL,
  `lookoutAccepted` int(1) DEFAULT NULL,
  PRIMARY KEY (`RelationId`),
  KEY `UserId` (`UserId`),
  KEY `RelatedUserId` (`RelatedUserId`),
  KEY `RelationTypeId` (`RelationTypeId`),
  CONSTRAINT `relation_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`),
  CONSTRAINT `relation_ibfk_2` FOREIGN KEY (`RelatedUserId`) REFERENCES `user` (`UserId`),
  CONSTRAINT `relation_ibfk_3` FOREIGN KEY (`RelationTypeId`) REFERENCES `relationtype` (`RelationTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=575 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation`
--

LOCK TABLES `relation` WRITE;
/*!40000 ALTER TABLE `relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relationtype`
--

DROP TABLE IF EXISTS `relationtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relationtype` (
  `RelationTypeId` int(11) NOT NULL,
  `Description` varchar(250) NOT NULL,
  PRIMARY KEY (`RelationTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relationtype`
--

LOCK TABLES `relationtype` WRITE;
/*!40000 ALTER TABLE `relationtype` DISABLE KEYS */;
INSERT INTO `relationtype` VALUES (1,'Contact'),(2,'Lookout');
/*!40000 ALTER TABLE `relationtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `SessionId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `StartTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `EndTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `Frequency` int(11) NOT NULL,
  `CanDisclosePosition` bit(1) NOT NULL,
  `SessionStatusId` int(11) NOT NULL,
  `CreatedDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`SessionId`),
  KEY `UserId` (`UserId`),
  KEY `SessionStatusId` (`SessionStatusId`),
  CONSTRAINT `session_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`),
  CONSTRAINT `session_ibfk_2` FOREIGN KEY (`SessionStatusId`) REFERENCES `sessionstatus` (`SessionStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_tracking`
--

DROP TABLE IF EXISTS `session_tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_tracking` (
  `session_id` varchar(50) NOT NULL,
  `user_id` int(11) NOT NULL,
  `start_of_session` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_time_used` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`session_id`),
  KEY `fk_session_user_idx` (`user_id`),
  CONSTRAINT `fk_session_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_tracking`
--

LOCK TABLES `session_tracking` WRITE;
/*!40000 ALTER TABLE `session_tracking` DISABLE KEYS */;
INSERT INTO `session_tracking` VALUES ('6a5b3fc7c1f708b23574c750dbf16904',1,'2014-06-28 12:53:33','2014-06-28 12:53:58');
/*!40000 ALTER TABLE `session_tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessionstatus`
--

DROP TABLE IF EXISTS `sessionstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessionstatus` (
  `SessionStatusId` int(11) NOT NULL,
  `Description` int(11) NOT NULL,
  PRIMARY KEY (`SessionStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessionstatus`
--

LOCK TABLES `sessionstatus` WRITE;
/*!40000 ALTER TABLE `sessionstatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `sessionstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracking`
--

DROP TABLE IF EXISTS `tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tracking` (
  `TrackingId` int(11) NOT NULL AUTO_INCREMENT,
  `SessionId` int(11) NOT NULL,
  `Longitude` decimal(10,2) DEFAULT NULL,
  `Latitude` decimal(10,2) DEFAULT NULL,
  `HasCheckedIn` bit(1) NOT NULL,
  `CreatedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`TrackingId`),
  KEY `SessionId` (`SessionId`),
  CONSTRAINT `tracking_ibfk_1` FOREIGN KEY (`SessionId`) REFERENCES `session` (`SessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracking`
--

LOCK TABLES `tracking` WRITE;
/*!40000 ALTER TABLE `tracking` DISABLE KEYS */;
/*!40000 ALTER TABLE `tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(30) DEFAULT NULL,
  `Password` varchar(200) DEFAULT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdateDate` timestamp NULL DEFAULT NULL,
  `EmailId` int(11) DEFAULT NULL,
  `PhoneId` int(11) DEFAULT NULL,
  `PersonId` int(11) DEFAULT NULL,
  `UserStateId` int(11) NOT NULL,
  `pwd_token` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  KEY `EmailId` (`EmailId`),
  KEY `UserStateId` (`UserStateId`),
  KEY `PersonId` (`PersonId`),
  KEY `PhoneId` (`PhoneId`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`EmailId`) REFERENCES `email` (`EmailId`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`UserStateId`) REFERENCES `userstate` (`UserStateId`),
  CONSTRAINT `user_ibfk_3` FOREIGN KEY (`PersonId`) REFERENCES `person` (`PersonId`),
  CONSTRAINT `user_ibfk_4` FOREIGN KEY (`PhoneId`) REFERENCES `phone` (`phoneId`)
) ENGINE=InnoDB AUTO_INCREMENT=1532 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test','test','2014-06-21 09:02:47','2014-06-28 12:53:58',1,1449,1,3,NULL),(143,'test1','test1','2014-06-21 09:57:49','2014-06-21 09:57:49',143,NULL,NULL,3,NULL),(636,'test12','test1','2014-06-21 11:40:41','2014-06-21 11:40:41',618,NULL,597,3,NULL),(637,'test123','test1','2014-06-21 11:41:06','2014-06-21 11:41:06',619,NULL,598,3,NULL),(658,'test1234','5a105e8b9d40e1329780d62ea2265d8a','2014-06-22 15:05:37','2014-06-22 15:05:37',641,NULL,620,3,NULL),(659,'test12345','827ccb0eea8a706c4c34a16891f84e7b','2014-06-22 15:55:29','2014-06-22 15:55:29',642,NULL,621,3,NULL),(780,'test123456','827ccb0eea8a706c4c34a16891f84e7b','2014-06-22 16:05:09','2014-06-22 17:26:05',757,1442,736,3,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userstate`
--

DROP TABLE IF EXISTS `userstate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userstate` (
  `UserStateId` int(11) NOT NULL,
  `Description` varchar(250) NOT NULL,
  PRIMARY KEY (`UserStateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userstate`
--

LOCK TABLES `userstate` WRITE;
/*!40000 ALTER TABLE `userstate` DISABLE KEYS */;
INSERT INTO `userstate` VALUES (1,'Active'),(2,'Inactive'),(3,'Preregistered'),(4,'Contact');
/*!40000 ALTER TABLE `userstate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-20 11:41:29
