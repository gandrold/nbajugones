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
-- Table structure for table `action_logging`
--

DROP TABLE IF EXISTS `action_logging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_logging` (
  `action_id` int(11) NOT NULL AUTO_INCREMENT,
  `action_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `action_type` varchar(45) NOT NULL,
  `message` varchar(2000) NOT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`action_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5362 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
) ENGINE=InnoDB AUTO_INCREMENT=1027 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



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
) ENGINE=InnoDB AUTO_INCREMENT=2098 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



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
) ENGINE=InnoDB AUTO_INCREMENT=2077 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



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
  `relationId` bigint(20) DEFAULT NULL,
  `sessionId` bigint(20) DEFAULT NULL,
  `Active` bit(1) DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `Priority` bigint(20) DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  PRIMARY KEY (`PersonId`,`AddressId`),
  KEY `AddressTypeId` (`AddressTypeId`),
  KEY `AddressId` (`AddressId`),
  CONSTRAINT `personaddress_ibfk_1` FOREIGN KEY (`AddressTypeId`) REFERENCES `addresstype` (`AddressTypeId`),
  CONSTRAINT `personaddress_ibfk_2` FOREIGN KEY (`PersonId`) REFERENCES `person` (`PersonId`),
  CONSTRAINT `personaddress_ibfk_3` FOREIGN KEY (`AddressId`) REFERENCES `address` (`AddressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



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
) ENGINE=InnoDB AUTO_INCREMENT=3954 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



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
  `order` int(11) DEFAULT NULL,
  `lookoutAccepted` int(1) DEFAULT NULL,
  `position` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`RelationId`),
  KEY `UserId` (`UserId`),
  KEY `RelatedUserId` (`RelatedUserId`),
  KEY `RelationTypeId` (`RelationTypeId`),
  CONSTRAINT `relation_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`),
  CONSTRAINT `relation_ibfk_2` FOREIGN KEY (`RelatedUserId`) REFERENCES `user` (`UserId`),
  CONSTRAINT `relation_ibfk_3` FOREIGN KEY (`RelationTypeId`) REFERENCES `relationtype` (`RelationTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=895 DEFAULT CHARSET=utf8;
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
  `Frequency` int(11) NOT NULL,
  `CanDisclosePosition` bit(1) NOT NULL,
  `SessionTypeId` int(11) NOT NULL,
  `CreatedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `EndTime` timestamp NULL DEFAULT NULL,
  `SessionStatusId` int(11) NOT NULL,
  `EndDate` datetime DEFAULT NULL,
  PRIMARY KEY (`SessionId`),
  KEY `UserId` (`UserId`),
  KEY `SessionStatusId` (`SessionTypeId`),
  KEY `session_ibfk_3_idx` (`SessionStatusId`),
  CONSTRAINT `session_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`),
  CONSTRAINT `session_ibfk_2` FOREIGN KEY (`SessionTypeId`) REFERENCES `session_type` (`SessionTypeId`),
  CONSTRAINT `session_ibfk_3` FOREIGN KEY (`SessionStatusId`) REFERENCES `session_status` (`SessionStatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_activity_type`
--

DROP TABLE IF EXISTS `session_activity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_activity_type` (
  `SessionActivityTypeId` int(11) NOT NULL,
  `Description` int(11) NOT NULL,
  PRIMARY KEY (`SessionActivityTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_activity_type`
--

LOCK TABLES `session_activity_type` WRITE;
/*!40000 ALTER TABLE `session_activity_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `session_activity_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_events`
--

DROP TABLE IF EXISTS `session_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_events` (
  `SessionEventId` int(11) NOT NULL AUTO_INCREMENT,
  `SessionId` int(11) NOT NULL,
  `Comment` varchar(2500) DEFAULT NULL,
  `CreatedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SessionEventId`),
  KEY `SessionId` (`SessionId`),
  CONSTRAINT `lookoutactivity_ibfk_1` FOREIGN KEY (`SessionId`) REFERENCES `session` (`SessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_events`
--

LOCK TABLES `session_events` WRITE;
/*!40000 ALTER TABLE `session_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `session_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_locations`
--

DROP TABLE IF EXISTS `session_locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_locations` (
  `SessionLocationId` int(11) NOT NULL AUTO_INCREMENT,
  `SessionId` int(11) NOT NULL,
  `Longitude` decimal(10,2) DEFAULT NULL,
  `Latitude` decimal(10,2) DEFAULT NULL,
  `HasCheckedIn` bit(1) NOT NULL,
  `CreatedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SessionLocationId`),
  KEY `SessionId` (`SessionId`),
  CONSTRAINT `tracking_ibfk_1` FOREIGN KEY (`SessionId`) REFERENCES `session` (`SessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_locations`
--

LOCK TABLES `session_locations` WRITE;
/*!40000 ALTER TABLE `session_locations` DISABLE KEYS */;
/*!40000 ALTER TABLE `session_locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_members`
--

DROP TABLE IF EXISTS `session_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_members` (
  `SessionId` int(11) NOT NULL,
  `RelationId` int(11) NOT NULL,
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
-- Dumping data for table `session_members`
--

LOCK TABLES `session_members` WRITE;
/*!40000 ALTER TABLE `session_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `session_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_status`
--

DROP TABLE IF EXISTS `session_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_status` (
  `SessionStatusId` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(45) NOT NULL,
  PRIMARY KEY (`SessionStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_status`
--

LOCK TABLES `session_status` WRITE;
/*!40000 ALTER TABLE `session_status` DISABLE KEYS */;
INSERT INTO `session_status` VALUES (1,'Created'),(2,'Awaiting response'),(3,'Accepted'),(4,'Rejected'),(5,'Cancelled');
/*!40000 ALTER TABLE `session_status` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `session_tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_type`
--

DROP TABLE IF EXISTS `session_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_type` (
  `SessionTypeId` int(11) NOT NULL,
  `Description` varchar(45) NOT NULL,
  PRIMARY KEY (`SessionTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_type`
--

LOCK TABLES `session_type` WRITE;
/*!40000 ALTER TABLE `session_type` DISABLE KEYS */;
INSERT INTO `session_type` VALUES (1,'Meetme'),(2,'Lookout');
/*!40000 ALTER TABLE `session_type` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2133 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2014-07-28 19:46:34
