-- MySQL dump 10.13  Distrib 5.5.20, for Linux (x86_64)
--
-- Host: localhost    Database: tnt
-- ------------------------------------------------------
-- Server version	5.5.20

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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account descriptive name',
  `number` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account number',
  `accountTypeId` int(11) NOT NULL COMMENT 'Account type',
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Account description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_account_accountTypeId` (`accountTypeId`),
  KEY `FK1D0C220D597DD228` (`accountTypeId`),
  CONSTRAINT `FK1D0C220D597DD228` FOREIGN KEY (`accountTypeId`) REFERENCES `AccountType` (`id`),
  CONSTRAINT `fk_account_accountTypeId` FOREIGN KEY (`accountTypeId`) REFERENCES `AccountType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='almacenan las cuentas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (1,'Caja','0000000000000000000',1,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AccountEntry`
--

DROP TABLE IF EXISTS `AccountEntry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountEntry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL COMMENT 'account where the entry is charged',
  `accountEntryTypeId` int(11) NOT NULL COMMENT 'Account entry type',
  `entryDate` date NOT NULL COMMENT 'account entry date',
  `entryAmountDate` date NOT NULL COMMENT 'account entry amount date (fecha valor)',
  `concept` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `amount` decimal(10,2) NOT NULL COMMENT 'account entry amount',
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `entryNumber` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `docNumber` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_accountEntry_accountId` (`accountId`),
  KEY `ndx_accountEntry_accountEntryTypeId` (`accountEntryTypeId`),
  KEY `FK4968366544025E1A` (`accountEntryTypeId`),
  KEY `FK496836654DE38A94` (`accountId`),
  CONSTRAINT `FK4968366544025E1A` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`),
  CONSTRAINT `FK496836654DE38A94` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`),
  CONSTRAINT `fk_accountEntry_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`),
  CONSTRAINT `fk_accountEntry_accountId` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='los movimientos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccountEntry`
--

LOCK TABLES `AccountEntry` WRITE;
/*!40000 ALTER TABLE `AccountEntry` DISABLE KEYS */;
/*!40000 ALTER TABLE `AccountEntry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AccountEntryGroup`
--

DROP TABLE IF EXISTS `AccountEntryGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountEntryGroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account entry group descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='grupos de movimiento';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccountEntryGroup`
--

LOCK TABLES `AccountEntryGroup` WRITE;
/*!40000 ALTER TABLE `AccountEntryGroup` DISABLE KEYS */;
INSERT INTO `AccountEntryGroup` VALUES (1,'Ingreso','Ingresos en cuenta',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `AccountEntryGroup` VALUES (2,'Gasto','Gastos en cuenta',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `AccountEntryGroup` VALUES (3,'Traspaso','Traspasos',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `AccountEntryGroup` VALUES (4,'Arranque anual','Movimiento que representa al arranque anual',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `AccountEntryGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AccountEntryType`
--

DROP TABLE IF EXISTS `AccountEntryType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountEntryType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountEntryGroupId` int(11) NOT NULL COMMENT 'Account entry group',
  `name` varchar(256) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account descriptive name',
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `accountEntryTypeId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `customizableId` int(11) DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_accountEntryType_accountEntryGroupId` (`accountEntryGroupId`),
  KEY `FK_accountentrytype_accountEntryTypeId` (`accountEntryTypeId`),
  KEY `FK54147F3F44025E1A` (`accountEntryTypeId`),
  KEY `FK54147F3F7FE55EAE` (`accountEntryGroupId`),
  CONSTRAINT `FK54147F3F44025E1A` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`),
  CONSTRAINT `FK54147F3F7FE55EAE` FOREIGN KEY (`accountEntryGroupId`) REFERENCES `AccountEntryGroup` (`id`),
  CONSTRAINT `fk_accountEntryType_accountEntryGroupId` FOREIGN KEY (`accountEntryGroupId`) REFERENCES `AccountEntryGroup` (`id`),
  CONSTRAINT `FK_accountentrytype_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de movimientos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccountEntryType`
--

LOCK TABLES `AccountEntryType` WRITE;
/*!40000 ALTER TABLE `AccountEntryType` DISABLE KEYS */;
INSERT INTO `AccountEntryType` VALUES (1,4,'Arranque inicial','Tipo de asiento que representa el arranque inicial de un año',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `AccountEntryType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AccountType`
--

DROP TABLE IF EXISTS `AccountType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account type descriptive name',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='lmacenan las cuentas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccountType`
--

LOCK TABLES `AccountType` WRITE;
/*!40000 ALTER TABLE `AccountType` DISABLE KEYS */;
INSERT INTO `AccountType` VALUES (1,'Caja',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `AccountType` VALUES (2,'Cuenta corriente',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `AccountType` VALUES (3,'Cuenta de crédito',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `AccountType` VALUES (4,'Depósito',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `AccountType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Activity`
--

DROP TABLE IF EXISTS `Activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `startDate` datetime DEFAULT '0000-00-00 00:00:00',
  `duration` int(11) NOT NULL COMMENT 'Duración en minutos',
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  `roleId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `projectEnsayoId` int(11) DEFAULT NULL,
  `projectId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_activity_userId` (`userId`),
  KEY `fk_activity_roleId` (`roleId`),
  KEY `ndx_activity_projectEnsayoId` (`projectEnsayoId`),
  KEY `ndx_activity_projectId` (`projectId`),
  KEY `FKA126572FB930816C` (`projectId`),
  KEY `FKA126572F217581A6` (`projectEnsayoId`),
  KEY `FKA126572FB1E24C1F` (`roleId`),
  KEY `FKA126572F2A1FD1F2` (`userId`),
  CONSTRAINT `FKA126572F217581A6` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`),
  CONSTRAINT `FKA126572F2A1FD1F2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `FKA126572FB1E24C1F` FOREIGN KEY (`roleId`) REFERENCES `ProjectRole` (`id`),
  CONSTRAINT `FKA126572FB930816C` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_activity_projectEnsayoId` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`),
  CONSTRAINT `fk_activity_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_activity_roleId` FOREIGN KEY (`roleId`) REFERENCES `ProjectRole` (`id`),
  CONSTRAINT `fk_activity_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Activityes de los Users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Activity`
--

LOCK TABLES `Activity` WRITE;
/*!40000 ALTER TABLE `Activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `Activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Albaran`
--

DROP TABLE IF EXISTS `Albaran`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Albaran` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `fecha` date NOT NULL,
  `devuelto` tinyint(1) DEFAULT '0',
  `client` int(11) DEFAULT NULL,
  `billId` int(11) DEFAULT NULL,
  `observaciones` text COLLATE utf8_spanish_ci,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `ndx_albaran_billId` (`billId`),
  KEY `ndx_albaran_client` (`client`),
  KEY `FK2C533E15921236A` (`billId`),
  KEY `FK2C533E15F66603FF` (`client`),
  CONSTRAINT `FK2C533E15921236A` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
  CONSTRAINT `FK2C533E15F66603FF` FOREIGN KEY (`client`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_albaran_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
  CONSTRAINT `fk_albaran_client` FOREIGN KEY (`client`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Albaran`
--

LOCK TABLES `Albaran` WRITE;
/*!40000 ALTER TABLE `Albaran` DISABLE KEYS */;
INSERT INTO `Albaran` VALUES (1,'A001/12','2012-01-10',0,4,1,'',1,1,'2012-01-10 01:49:13','2012-01-10 01:49:22',1);
/*!40000 ALTER TABLE `Albaran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bill`
--

DROP TABLE IF EXISTS `Bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creationDate` date NOT NULL,
  `expiration` smallint(6) DEFAULT NULL,
  `paymentMode` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `state` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `number` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `file` varchar(512) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fileMime` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `observations` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `projectId` int(11) DEFAULT '5' COMMENT 'project id',
  `startBillDate` date NOT NULL DEFAULT '1980-01-01',
  `endBillDate` date NOT NULL DEFAULT '1980-01-01',
  `billType` varchar(16) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'ISSUED',
  `orderNumber` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `contactId` int(11) DEFAULT NULL,
  `providerId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `clientId` int(11) DEFAULT NULL,
  `lineaTrabajoId` int(10) unsigned DEFAULT NULL,
  `pendienteDePedido` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_bill_number` (`number`),
  KEY `ndx_bill_projectId` (`projectId`),
  KEY `ndx_bill_contactId` (`contactId`),
  KEY `ndx_bill_providerId` (`providerId`),
  KEY `ndx_bill_clientId` (`clientId`),
  KEY `ndx_bill_lineaTrabajoId` (`lineaTrabajoId`),
  KEY `FK1F9827538D6B00` (`providerId`),
  KEY `FK1F9827B930816C` (`projectId`),
  KEY `FK1F98277D784A5A` (`clientId`),
  KEY `FK1F9827615CB88` (`lineaTrabajoId`),
  KEY `FK1F9827212C0DBA` (`contactId`),
  CONSTRAINT `FK1F9827212C0DBA` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK1F9827538D6B00` FOREIGN KEY (`providerId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `FK1F9827615CB88` FOREIGN KEY (`lineaTrabajoId`) REFERENCES `Department` (`id`),
  CONSTRAINT `FK1F98277D784A5A` FOREIGN KEY (`clientId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `FK1F9827B930816C` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_bill_clientId` FOREIGN KEY (`clientId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_bill_contactId` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_bill_lineaTrabajoId` FOREIGN KEY (`lineaTrabajoId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_bill_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_bill_providerId` FOREIGN KEY (`providerId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bill`
--

LOCK TABLES `Bill` WRITE;
/*!40000 ALTER TABLE `Bill` DISABLE KEYS */;
INSERT INTO `Bill` VALUES (1,'2012-01-10',90,'TRANSFER','EMITTED','F001/12','Recepciones marzo',NULL,NULL,'',NULL,'2012-01-10','2012-01-10','ISSUED','',2,NULL,1,1,'2012-01-10 01:51:12','2012-01-10 01:51:19',1,4,5,0);
/*!40000 ALTER TABLE `Bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BillBreakDown`
--

DROP TABLE IF EXISTS `BillBreakDown`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BillBreakDown` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `billId` int(11) NOT NULL,
  `concept` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `units` decimal(10,2) NOT NULL DEFAULT '1.00',
  `amount` decimal(10,2) NOT NULL,
  `iva` decimal(4,2) NOT NULL DEFAULT '16.00',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_billBreakDown_bill` (`billId`),
  KEY `FK8B661DDA921236A` (`billId`),
  CONSTRAINT `FK8B661DDA921236A` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
  CONSTRAINT `fk_billBreakDown_bill` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BillBreakDown`
--

LOCK TABLES `BillBreakDown` WRITE;
/*!40000 ALTER TABLE `BillBreakDown` DISABLE KEYS */;
/*!40000 ALTER TABLE `BillBreakDown` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bill_AccountEntry`
--

DROP TABLE IF EXISTS `Bill_AccountEntry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bill_AccountEntry` (
  `billId` int(11) NOT NULL COMMENT 'bill id',
  `accountEntryId` int(11) NOT NULL COMMENT 'account entry id',
  `observations` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`billId`,`accountEntryId`),
  KEY `ndx_bill_AccountEntry_billId` (`billId`),
  KEY `ndx_bill_AccountEntry_accountEntryId` (`accountEntryId`),
  KEY `FK4EC8BF7D921236A` (`billId`),
  KEY `FK4EC8BF7DA95FDF86` (`accountEntryId`),
  CONSTRAINT `FK4EC8BF7D921236A` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
  CONSTRAINT `FK4EC8BF7DA95FDF86` FOREIGN KEY (`accountEntryId`) REFERENCES `AccountEntry` (`id`),
  CONSTRAINT `fk_billAccountEntry_accountEntryId` FOREIGN KEY (`accountEntryId`) REFERENCES `AccountEntry` (`id`),
  CONSTRAINT `fk_billAccountEntry_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='relaciona n m Facturas y movimientos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bill_AccountEntry`
--

LOCK TABLES `Bill_AccountEntry` WRITE;
/*!40000 ALTER TABLE `Bill_AccountEntry` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bill_AccountEntry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `author` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ISBN` varchar(13) COLLATE utf8_spanish_ci DEFAULT NULL,
  `URL` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `purchaseDate` datetime DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Book_userId` (`userId`),
  KEY `ndx_book_userId` (`userId`),
  KEY `FK1FAF092A1FD1F2` (`userId`),
  CONSTRAINT `FK1FAF092A1FD1F2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_bill_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_Book_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BulletinBoard`
--

DROP TABLE IF EXISTS `BulletinBoard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BulletinBoard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `message` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `documentPath` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `documentContentType` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_bulletinboard_categoryId` (`categoryId`),
  KEY `ndx_bulletinboard_userId` (`userId`),
  KEY `FKE470E55FE5D71855` (`categoryId`),
  KEY `FKE470E55F2A1FD1F2` (`userId`),
  CONSTRAINT `FKE470E55F2A1FD1F2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `FKE470E55FE5D71855` FOREIGN KEY (`categoryId`) REFERENCES `BulletinBoardCategory` (`id`),
  CONSTRAINT `fk_bulletinboard_categoryId` FOREIGN KEY (`categoryId`) REFERENCES `BulletinBoardCategory` (`id`),
  CONSTRAINT `fk_bulletinboard_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='mensajes del tabln';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BulletinBoard`
--

LOCK TABLES `BulletinBoard` WRITE;
/*!40000 ALTER TABLE `BulletinBoard` DISABLE KEYS */;
INSERT INTO `BulletinBoard` VALUES (1,1,1,'2011-01-09 00:00:00','El día 7 de marzo permanecerá cerrado el laboratorio a partir de las 14.00 por motivos de mantenimiento del edificio.','Horario de laboratorio reducido el próximo día 7',NULL,NULL,1,'2011-01-09 16:42:40','2011-01-29 20:29:56',1);
/*!40000 ALTER TABLE `BulletinBoard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BulletinBoardCategory`
--

DROP TABLE IF EXISTS `BulletinBoardCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BulletinBoardCategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='almacenan las categoras';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BulletinBoardCategory`
--

LOCK TABLES `BulletinBoardCategory` WRITE;
/*!40000 ALTER TABLE `BulletinBoardCategory` DISABLE KEYS */;
INSERT INTO `BulletinBoardCategory` VALUES (1,'Pública',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `BulletinBoardCategory` VALUES (2,'General',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `BulletinBoardCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CompanyState`
--

DROP TABLE IF EXISTS `CompanyState`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CompanyState` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT 'aplicacin que manda el mail',
  `creationDate` datetime NOT NULL,
  `description` longtext COLLATE utf8_spanish_ci NOT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_companystate_userId` (`userId`),
  KEY `FK5E5F32342A1FD1F2` (`userId`),
  CONSTRAINT `FK5E5F32342A1FD1F2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_companystate_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='comentario del director de empresa';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CompanyState`
--

LOCK TABLES `CompanyState` WRITE;
/*!40000 ALTER TABLE `CompanyState` DISABLE KEYS */;
/*!40000 ALTER TABLE `CompanyState` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Contact`
--

DROP TABLE IF EXISTS `Contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizationId` int(11) NOT NULL,
  `name` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `mobile` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `position` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Cargo de la persona de contacto',
  `notified` tinyint(1) NOT NULL DEFAULT '0',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_contact_organizationId` (`organizationId`),
  KEY `FK9BEFBC00101189C2` (`organizationId`),
  CONSTRAINT `FK9BEFBC00101189C2` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_contact_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='contactos de las Organizationes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contact`
--

LOCK TABLES `Contact` WRITE;
/*!40000 ALTER TABLE `Contact` DISABLE KEYS */;
INSERT INTO `Contact` VALUES (1,1,'Fran Perea','fran.perea@miempresa.com','955955955','662662662','Director de prensa',1,1,1,'2011-01-09 17:24:00','2011-12-31 13:35:11',1);
INSERT INTO `Contact` VALUES (2,4,'Luis Cabeza Ruiz','luis.cabeza@acronotex.es','955955955','655955955','Departamento de compras',1,1,1,'2011-12-31 13:36:40','2011-12-31 13:36:40',NULL);
/*!40000 ALTER TABLE `Contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ContractType`
--

DROP TABLE IF EXISTS `ContractType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ContractType` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ContractType`
--

LOCK TABLES `ContractType` WRITE;
/*!40000 ALTER TABLE `ContractType` DISABLE KEYS */;
INSERT INTO `ContractType` VALUES (1,'Prácticas','Departamento de dirección.',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `ContractType` VALUES (2,'Duración determinada','Departamento de dirección.',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `ContractType` VALUES (3,'Indefinido','Departamento de dirección.',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ContractType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Department` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parentId` int(10) unsigned DEFAULT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_department_department` (`parentId`),
  KEY `FKA9601F726926C5D8` (`parentId`),
  CONSTRAINT `FKA9601F726926C5D8` FOREIGN KEY (`parentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_department_department` FOREIGN KEY (`parentId`) REFERENCES `Department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department`
--

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
INSERT INTO `Department` VALUES (1,NULL,'Dirección','Departamento de dirección.',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Department` VALUES (4,NULL,'Control de calidad','',1,1,'2012-01-09 22:08:22','2012-01-09 22:08:22',NULL);
INSERT INTO `Department` VALUES (5,4,'Recepción de materiales','',1,1,'2012-01-09 22:08:57','2012-01-09 22:08:57',NULL);
INSERT INTO `Department` VALUES (6,4,'Procesos industriales','',1,1,'2012-01-09 22:09:46','2012-01-09 22:09:46',NULL);
INSERT INTO `Department` VALUES (7,NULL,'Proyectos industriales','',1,1,'2012-01-09 22:12:01','2012-01-09 22:12:01',NULL);
INSERT INTO `Department` VALUES (8,NULL,'Asistencias técnicas','',1,1,'2012-01-09 22:12:53','2012-01-09 22:12:53',NULL);
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `Department_hijos`
--

DROP TABLE IF EXISTS `Department_hijos`;
/*!50001 DROP VIEW IF EXISTS `Department_hijos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `Department_hijos` (
  `padre` int(11) unsigned,
  `hijo` int(11) unsigned
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Document`
--

DROP TABLE IF EXISTS `Document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Document` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `name` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `description` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Document`
--

LOCK TABLES `Document` WRITE;
/*!40000 ALTER TABLE `Document` DISABLE KEYS */;
/*!40000 ALTER TABLE `Document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DocumentCategory`
--

DROP TABLE IF EXISTS `DocumentCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DocumentCategory` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `code` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `categoryid` int(10) unsigned DEFAULT NULL,
  `documentslastupdate` datetime DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_documentCategory_categoryId` (`categoryid`),
  KEY `FK6DD4EB59A4AAE453` (`categoryid`),
  CONSTRAINT `FK6DD4EB59A4AAE453` FOREIGN KEY (`categoryid`) REFERENCES `DocumentCategory` (`id`),
  CONSTRAINT `fk_documentCategory_categoryId` FOREIGN KEY (`categoryid`) REFERENCES `DocumentCategory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DocumentCategory`
--

LOCK TABLES `DocumentCategory` WRITE;
/*!40000 ALTER TABLE `DocumentCategory` DISABLE KEYS */;
INSERT INTO `DocumentCategory` VALUES (1,'Documentos de Calidad','Documentos de calidad','CALIDAD',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (2,'Otros Documentos','Otros documentos no clasificados',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (3,'Curriculum Vitae','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (4,'Documentos de Usuarios','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (10,'(I1-PC01) Lista de distribución de la documentación','','I1-PC01',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (11,'(I1-PC02) Acta de reunión de revisión del sistema','','I1-PC02',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (12,'(I1-PC08) Informe de auditoría interna','','I1-PC08',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (13,'(I2-PC02) Planificación de objetivos de calidad','','I2-PC02',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (14,'(I2-PC08) Informe de no conformidad/reclamación del cliente','','I2-PC08',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (15,'(I3-PC08) Informe de acción correctiva/preventiva','','I3-PC08',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (16,'(PC01) Sistema de Gestión de la Calidad','Documento inicial de descripción','PC01',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (17,'(PC02) Responsabilidad de la Dirección','','PC02',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (18,'(PC03) Gestión de los Recursos','','PC03',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (19,'(PC04) Procesos relacionados con los clientes','','PC04',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (20,'(PC05) Gestión de compras','','PC05',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (21,'(PC06) Evaluación de proveedores y subcontratistas','','PC06',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (22,'(PC07) Prestación del servicio','','PC07',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (23,'(PC08) Medición análisis y mejora','','PC08',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (24,'Control documentación entregada y recibida','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (25,'Criterio de evaluación y seguimiento de procesos','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (26,'Cuestionario de satisfacción del cliente','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (27,'E-mail aprobación documentación','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (28,'E-mail de comunicaciones','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (29,'Ficha de mantenimiento de equipos','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (30,'Índice de ediciones de documentos','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (31,'Índice de no conformidades','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (32,'Inventario de recursos','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (33,'Listado de documentación externa en vigor','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (34,'Listado de proveedores y subcontratistas evaluados','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (35,'Listado de registros','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (36,'Manual de Gestión de la Calidad (MGC)','','MGC',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (37,'Perfil del empleado','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (38,'Perfil puesto trabajo','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (39,'Plan de auditoría anual','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (40,'Plan de formación anual','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (41,'Política de Calidad','','',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (42,'(I5-PC03) Registro perfil del empleado','','I5-PC03',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (43,'(I8-PC03) Cuestionario de satisfacción laboral','','I8-PC03',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (44,'(I6-PC08) Evaluación de satisfacción del cliente','','I6-PC08',1,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (45,'Antonio Sánchez Romo','Antonio Sánchez Romo',NULL,4,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES (46,'Felipe Herrero Guadalupe','Felipe Herrero Guadalupe',NULL,4,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `DocumentCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DocumentCategoryDoc`
--

DROP TABLE IF EXISTS `DocumentCategoryDoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DocumentCategoryDoc` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `categoryid` int(10) unsigned NOT NULL,
  `documentid` int(10) unsigned NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DocumentCategorydoc_category` (`categoryid`),
  KEY `FK_DocumentCategorydoc_docu` (`documentid`),
  KEY `FK3CA8B71FA4AAE453` (`categoryid`),
  KEY `FK3CA8B71F209BA012` (`documentid`),
  CONSTRAINT `FK3CA8B71F209BA012` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`),
  CONSTRAINT `FK3CA8B71FA4AAE453` FOREIGN KEY (`categoryid`) REFERENCES `DocumentCategory` (`id`),
  CONSTRAINT `FK_DocumentCategorydoc_category` FOREIGN KEY (`categoryid`) REFERENCES `DocumentCategory` (`id`),
  CONSTRAINT `FK_DocumentCategorydoc_docu` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DocumentCategoryDoc`
--

LOCK TABLES `DocumentCategoryDoc` WRITE;
/*!40000 ALTER TABLE `DocumentCategoryDoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `DocumentCategoryDoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DocumentVersion`
--

DROP TABLE IF EXISTS `DocumentVersion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DocumentVersion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `documentPath` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `creationDate` datetime NOT NULL,
  `version` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `documentid` int(10) unsigned NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DocumentVersion_document_id` (`documentid`),
  KEY `FK9446371D209BA012` (`documentid`),
  CONSTRAINT `FK9446371D209BA012` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`),
  CONSTRAINT `FK_DocumentVersion_document_id` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DocumentVersion`
--

LOCK TABLES `DocumentVersion` WRITE;
/*!40000 ALTER TABLE `DocumentVersion` DISABLE KEYS */;
/*!40000 ALTER TABLE `DocumentVersion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ensayo`
--

DROP TABLE IF EXISTS `Ensayo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ensayo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Ensayo descriptive name',
  `cost` decimal(10,2) DEFAULT NULL,
  `perHour` tinyint(1) DEFAULT '0',
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nameIngles` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dimensional` tinyint(1) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='ensayos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ensayo`
--

LOCK TABLES `Ensayo` WRITE;
/*!40000 ALTER TABLE `Ensayo` DISABLE KEYS */;
INSERT INTO `Ensayo` VALUES (1,'Espectrograma',18.15,0,'Obtener el espectrograma de un material','Spectrogram',0,1,1,'2012-01-09 22:22:15','2012-01-10 00:50:31',1);
INSERT INTO `Ensayo` VALUES (2,'Torsión',16.06,0,'En ingeniería, torsión es la solicitación que se presenta cuando se aplica un momento sobre el eje longitudinal de un elemento constructivo o prisma mecánico, como pueden ser ejes o, en general, elementos donde una dimensión predomina sobre las otras dos, aunque es posible encontrarla en situaciones diversas.','Torsion',0,1,1,'2012-01-09 22:25:03','2012-01-10 00:50:31',1);
INSERT INTO `Ensayo` VALUES (3,'Tensión cortante',13.75,0,'La tensión cortante o tensión de corte es aquella que, fijado un plano, actúa tangente al mismo. Se suele representar con la letra griega tau. En piezas prismáticas, las tensiones cortantes aparecen en caso de aplicación de un esfuerzo cortante o bien de un momento torsor.','Shear stress',0,1,1,'2012-01-09 22:28:27','2012-01-10 00:50:31',1);
INSERT INTO `Ensayo` VALUES (4,'Flexión',12.00,0,'En ingeniería se denomina flexión al tipo de deformación que presenta un elemento estructural alargado en una dirección perpendicular a su eje longitudinal. El término \"alargado\" se aplica cuando una dimensión es dominante frente a las otras. Un caso típico son las vigas, las que están diseñadas para trabajar, principalmente, por flexión. Igualmente, el concepto de flexión se extiende a elementos estructurales superficiales como placas o láminas.','Bending',0,1,1,'2012-01-10 01:03:45','2012-01-10 01:03:45',NULL);
/*!40000 ALTER TABLE `Ensayo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EnsayoCost`
--

DROP TABLE IF EXISTS `EnsayoCost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EnsayoCost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizationId` int(11) NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_ensayoCost_organizationId` (`organizationId`),
  CONSTRAINT `fk_ensayoCost_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `ProjectCost` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EnsayoCost`
--

LOCK TABLES `EnsayoCost` WRITE;
/*!40000 ALTER TABLE `EnsayoCost` DISABLE KEYS */;
/*!40000 ALTER TABLE `EnsayoCost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EnsayoPrecio`
--

DROP TABLE IF EXISTS `EnsayoPrecio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EnsayoPrecio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizationId` int(11) NOT NULL,
  `ensayoId` int(11) NOT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_ensayoPrecio_ensayoId` (`ensayoId`),
  KEY `ndx_ensayoPrecio_organizationId` (`organizationId`),
  KEY `FK904DBBD3101189C2` (`organizationId`),
  KEY `FK904DBBD3964F19B6` (`ensayoId`),
  CONSTRAINT `FK904DBBD3101189C2` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `FK904DBBD3964F19B6` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
  CONSTRAINT `fk_ensayoPrecio_ensayoId` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
  CONSTRAINT `fk_ensayoPrecio_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EnsayoPrecio`
--

LOCK TABLES `EnsayoPrecio` WRITE;
/*!40000 ALTER TABLE `EnsayoPrecio` DISABLE KEYS */;
/*!40000 ALTER TABLE `EnsayoPrecio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EnsayoProcedimiento`
--

DROP TABLE IF EXISTS `EnsayoProcedimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EnsayoProcedimiento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `ensayoId` int(11) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_ensayoProcedimiento_ensayoId` (`ensayoId`),
  KEY `FK8507F4E7964F19B6` (`ensayoId`),
  CONSTRAINT `FK8507F4E7964F19B6` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
  CONSTRAINT `fk_ensayoProcedimiento_ensayoId` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EnsayoProcedimiento`
--

LOCK TABLES `EnsayoProcedimiento` WRITE;
/*!40000 ALTER TABLE `EnsayoProcedimiento` DISABLE KEYS */;
INSERT INTO `EnsayoProcedimiento` VALUES (1,'UNE-2211',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `EnsayoProcedimiento` VALUES (2,'DIN-9956.6',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `EnsayoProcedimiento` VALUES (3,'DIN-2334.9',2,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `EnsayoProcedimiento` VALUES (4,'ISO-223/2',3,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `EnsayoProcedimiento` VALUES (5,'ATE-994.3/89',3,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `EnsayoProcedimiento` VALUES (6,'ctrf776',4,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `EnsayoProcedimiento` VALUES (7,'ATE-894.3/96',4,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `EnsayoProcedimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FinancialRatio`
--

DROP TABLE IF EXISTS `FinancialRatio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FinancialRatio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `ratioDate` date NOT NULL,
  `banksAccounts` decimal(10,2) NOT NULL,
  `customers` decimal(10,2) NOT NULL,
  `stocks` decimal(10,2) NOT NULL,
  `amortizations` decimal(10,2) NOT NULL,
  `infrastructures` decimal(10,2) NOT NULL,
  `shortTermLiability` decimal(10,2) NOT NULL,
  `obligationBond` decimal(10,2) NOT NULL,
  `capital` decimal(10,2) NOT NULL,
  `reserves` decimal(10,2) NOT NULL,
  `incomes` decimal(10,2) NOT NULL,
  `expenses` decimal(10,2) NOT NULL,
  `otherExploitationExpenses` decimal(10,2) NOT NULL,
  `financialExpenses` decimal(10,2) NOT NULL,
  `taxes` decimal(10,2) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ratios financieros';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FinancialRatio`
--

LOCK TABLES `FinancialRatio` WRITE;
/*!40000 ALTER TABLE `FinancialRatio` DISABLE KEYS */;
/*!40000 ALTER TABLE `FinancialRatio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Frequency`
--

DROP TABLE IF EXISTS `Frequency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Frequency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `months` int(10) unsigned DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Frecuencia de los asientos contables';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Frequency`
--

LOCK TABLES `Frequency` WRITE;
/*!40000 ALTER TABLE `Frequency` DISABLE KEYS */;
INSERT INTO `Frequency` VALUES (1,'Mensual',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Frequency` VALUES (2,'Trimestral',3,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Frequency` VALUES (3,'Semestral',6,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Frequency` VALUES (4,'Anual',12,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Frequency` VALUES (5,'Bimensual',2,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Frequency` VALUES (6,'Ocasional',0,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Frequency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Historial`
--

DROP TABLE IF EXISTS `Historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Historial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idObjeto` int(11) DEFAULT NULL,
  `fechaHora` datetime DEFAULT NULL,
  `klazz` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tipoCambio` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usuario` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_historial_usuario` (`usuario`),
  KEY `FKDE4AFF8F540992FA` (`usuario`),
  CONSTRAINT `FKDE4AFF8F540992FA` FOREIGN KEY (`usuario`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_historial_usuario` FOREIGN KEY (`usuario`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Historial`
--

LOCK TABLES `Historial` WRITE;
/*!40000 ALTER TABLE `Historial` DISABLE KEYS */;
INSERT INTO `Historial` VALUES (1,1,'2011-01-09 17:24:00','com.autentia.intra.businessobject.Contact','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (2,3,'2011-01-09 17:24:44','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (3,4,'2011-01-09 17:26:54','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (4,4,'2011-01-09 17:31:26','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (5,4,'2011-01-09 17:31:31','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (6,4,'2011-01-09 17:44:58','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (7,2,'2011-01-09 17:49:00','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (8,1,'2011-01-09 17:49:07','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (9,1,'2011-12-31 13:30:28','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (10,2,'2011-12-31 13:31:46','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (11,4,'2011-12-31 13:34:28','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (12,1,'2011-12-31 13:35:11','com.autentia.intra.businessobject.Contact','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (13,2,'2011-12-31 13:36:40','com.autentia.intra.businessobject.Contact','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (14,1,'2012-01-09 22:22:16','com.autentia.intra.businessobject.Ensayo','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (15,2,'2012-01-09 22:25:03','com.autentia.intra.businessobject.Ensayo','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (16,2,'2012-01-09 22:25:26','com.autentia.intra.businessobject.Ensayo','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (17,1,'2012-01-09 22:25:59','com.autentia.intra.businessobject.Ensayo','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (18,3,'2012-01-09 22:28:27','com.autentia.intra.businessobject.Ensayo','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (19,3,'2012-01-09 22:28:52','com.autentia.intra.businessobject.Ensayo','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (20,1,'2012-01-09 22:33:38','com.autentia.intra.businessobject.Pauta','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (21,1,'2012-01-09 22:34:01','com.autentia.intra.businessobject.Pauta','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (22,1,'2012-01-09 22:39:02','com.autentia.intra.businessobject.Pauta','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (23,1,'2012-01-09 22:39:02','com.autentia.intra.businessobject.Pauta$$EnhancerByCGLIB$$d899b67','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (24,1,'2012-01-09 22:39:08','com.autentia.intra.businessobject.Pauta','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (25,1,'2012-01-09 22:39:08','com.autentia.intra.businessobject.Pauta$$EnhancerByCGLIB$$d899b67','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (26,1,'2012-01-09 22:40:53','com.autentia.intra.businessobject.Pauta$$EnhancerByCGLIB$$d899b67','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (27,1,'2012-01-09 22:40:53','com.autentia.intra.businessobject.Pauta$$EnhancerByCGLIB$$d899b67','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (28,1,'2012-01-09 22:41:09','com.autentia.intra.businessobject.Pauta$$EnhancerByCGLIB$$d899b67','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (29,1,'2012-01-09 22:41:10','com.autentia.intra.businessobject.Pauta$$EnhancerByCGLIB$$d899b67','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (30,1,'2012-01-09 22:41:21','com.autentia.intra.businessobject.Pauta','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (31,5,'2012-01-10 00:13:49','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (32,5,'2012-01-10 00:13:59','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (33,1,'2012-01-10 00:14:04','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (34,1,'2012-01-10 00:14:06','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (35,6,'2012-01-10 00:14:13','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (36,6,'2012-01-10 00:14:14','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (37,3,'2012-01-10 00:14:34','com.autentia.intra.businessobject.Contact','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (38,3,'2012-01-10 00:14:38','com.autentia.intra.businessobject.Contact','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (39,1,'2012-01-10 00:50:31','com.autentia.intra.businessobject.Ensayo','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (40,2,'2012-01-10 00:50:31','com.autentia.intra.businessobject.Ensayo','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (41,3,'2012-01-10 00:50:31','com.autentia.intra.businessobject.Ensayo','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (42,1,'2012-01-10 00:50:58','com.autentia.intra.businessobject.Pauta','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (43,4,'2012-01-10 01:03:45','com.autentia.intra.businessobject.Ensayo','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (44,7,'2012-01-10 01:04:55','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (45,7,'2012-01-10 01:05:01','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (46,1,'2012-01-10 01:05:04','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (47,1,'2012-01-10 01:05:05','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (48,8,'2012-01-10 01:05:10','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (49,8,'2012-01-10 01:05:10','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (50,4,'2012-01-10 01:05:22','com.autentia.intra.businessobject.Contact','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (51,4,'2012-01-10 01:05:25','com.autentia.intra.businessobject.Contact','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (52,1,'2012-01-10 01:06:55','com.autentia.intra.businessobject.Offer','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (53,1,'2012-01-10 01:09:08','com.autentia.intra.businessobject.Offer','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (54,9,'2012-01-10 01:10:56','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (55,9,'2012-01-10 01:11:00','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (56,1,'2012-01-10 01:11:02','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (57,1,'2012-01-10 01:11:03','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (58,10,'2012-01-10 01:11:06','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (59,10,'2012-01-10 01:11:07','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (60,5,'2012-01-10 01:11:16','com.autentia.intra.businessobject.Contact','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (61,5,'2012-01-10 01:11:18','com.autentia.intra.businessobject.Contact','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (62,2,'2012-01-10 01:11:29','com.autentia.intra.businessobject.Offer','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (63,2,'2012-01-10 01:11:32','com.autentia.intra.businessobject.Offer','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (64,3,'2012-01-10 01:12:21','com.autentia.intra.businessobject.Offer','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (65,3,'2012-01-10 01:12:27','com.autentia.intra.businessobject.Offer','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (66,4,'2012-01-10 01:13:20','com.autentia.intra.businessobject.Offer','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (67,4,'2012-01-10 01:13:26','com.autentia.intra.businessobject.Offer','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (68,5,'2012-01-10 01:14:19','com.autentia.intra.businessobject.Offer','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (69,5,'2012-01-10 01:14:25','com.autentia.intra.businessobject.Offer','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (70,2,'2012-01-10 01:26:16','com.autentia.intra.businessobject.Pauta','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (71,2,'2012-01-10 01:27:13','com.autentia.intra.businessobject.Pauta','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (72,3,'2012-01-10 01:28:59','com.autentia.intra.businessobject.Pauta','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (73,3,'2012-01-10 01:29:06','com.autentia.intra.businessobject.Pauta','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (74,11,'2012-01-10 01:33:45','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (75,11,'2012-01-10 01:33:58','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (76,1,'2012-01-10 01:34:04','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (77,1,'2012-01-10 01:34:07','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (78,12,'2012-01-10 01:34:17','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (79,12,'2012-01-10 01:34:19','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (80,6,'2012-01-10 01:34:45','com.autentia.intra.businessobject.Contact','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (81,6,'2012-01-10 01:34:51','com.autentia.intra.businessobject.Contact','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (82,6,'2012-01-10 01:35:22','com.autentia.intra.businessobject.Offer','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (83,6,'2012-01-10 01:35:29','com.autentia.intra.businessobject.Offer','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (84,4,'2012-01-10 01:35:57','com.autentia.intra.businessobject.Pauta','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (85,4,'2012-01-10 01:36:04','com.autentia.intra.businessobject.Pauta','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (86,7,'2012-01-10 01:38:56','com.autentia.intra.businessobject.Offer','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (87,6,'2012-01-10 01:39:07','com.autentia.intra.businessobject.Project','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (88,7,'2012-01-10 01:39:07','com.autentia.intra.businessobject.Project','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (89,7,'2012-01-10 01:39:08','com.autentia.intra.businessobject.Offer','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (90,6,'2012-01-10 01:40:37','com.autentia.intra.businessobject.Project','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (91,1,'2012-01-10 01:42:32','com.autentia.intra.businessobject.ProjectEnsayo','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (92,1,'2012-01-10 01:43:14','com.autentia.intra.businessobject.ProjectEnsayo','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (93,2,'2012-01-10 01:47:39','com.autentia.intra.businessobject.ProjectEnsayo','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (94,1,'2012-01-10 01:49:13','com.autentia.intra.businessobject.Albaran','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (95,1,'2012-01-10 01:49:22','com.autentia.intra.businessobject.Albaran','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (96,1,'2012-01-10 01:51:12','com.autentia.intra.businessobject.Bill','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (97,1,'2012-01-10 01:51:19','com.autentia.intra.businessobject.Bill$$EnhancerByCGLIB$$df66cf3f','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (98,1,'2012-01-10 01:52:20','com.autentia.intra.businessobject.NotaSalida','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (99,13,'2012-01-10 01:55:23','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (100,13,'2012-01-10 01:55:36','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (101,1,'2012-01-10 01:55:43','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (102,1,'2012-01-10 01:55:46','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (103,14,'2012-01-10 01:55:55','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (104,14,'2012-01-10 01:55:56','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (105,7,'2012-01-10 01:56:20','com.autentia.intra.businessobject.Contact','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (106,7,'2012-01-10 01:56:25','com.autentia.intra.businessobject.Contact','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (107,8,'2012-01-10 01:56:53','com.autentia.intra.businessobject.Offer','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (108,7,'2012-01-10 01:57:17','com.autentia.intra.businessobject.Offer','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (109,5,'2012-01-10 01:57:42','com.autentia.intra.businessobject.Pauta','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (110,5,'2012-01-10 01:57:49','com.autentia.intra.businessobject.Pauta','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (111,15,'2012-01-10 22:41:57','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (112,15,'2012-01-10 22:42:05','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (113,1,'2012-01-10 22:42:09','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (114,1,'2012-01-10 22:42:11','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (115,16,'2012-01-10 22:42:17','com.autentia.intra.businessobject.Organization','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (116,16,'2012-01-10 22:42:18','com.autentia.intra.businessobject.Organization','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (117,8,'2012-01-10 22:42:35','com.autentia.intra.businessobject.Contact','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (118,8,'2012-01-10 22:42:38','com.autentia.intra.businessobject.Contact','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (119,9,'2012-01-10 22:42:56','com.autentia.intra.businessobject.Offer','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (120,7,'2012-01-10 22:43:01','com.autentia.intra.businessobject.Offer','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (121,6,'2012-01-10 22:43:18','com.autentia.intra.businessobject.Pauta','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (122,6,'2012-01-10 22:43:23','com.autentia.intra.businessobject.Pauta','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (123,5,'2012-01-10 22:44:53','com.autentia.intra.businessobject.Ensayo','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (124,5,'2012-01-10 22:45:42','com.autentia.intra.businessobject.Ensayo','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (125,6,'2012-01-10 22:47:30','com.autentia.intra.businessobject.Ensayo','CREADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (126,6,'2012-01-10 22:47:34','com.autentia.intra.businessobject.Ensayo','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Historial` VALUES (127,7,'2012-01-10 22:52:25','com.autentia.intra.businessobject.Project','MODIFICADO',1,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Holiday`
--

DROP TABLE IF EXISTS `Holiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Holiday` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `date` datetime NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Holiday`
--

LOCK TABLES `Holiday` WRITE;
/*!40000 ALTER TABLE `Holiday` DISABLE KEYS */;
/*!40000 ALTER TABLE `Holiday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Idea`
--

DROP TABLE IF EXISTS `Idea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Idea` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `cost` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `benefits` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `name` varchar(300) COLLATE utf8_spanish_ci NOT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_idea_userId` (`userId`),
  KEY `FK22B3172A1FD1F2` (`userId`),
  CONSTRAINT `FK22B3172A1FD1F2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_idea_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ideas de los empleados';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Idea`
--

LOCK TABLES `Idea` WRITE;
/*!40000 ALTER TABLE `Idea` DISABLE KEYS */;
/*!40000 ALTER TABLE `Idea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Interaction`
--

DROP TABLE IF EXISTS `Interaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Interaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) DEFAULT '5' COMMENT 'project id',
  `userId` int(11) NOT NULL DEFAULT '1' COMMENT 'user id',
  `interactionTypeId` int(11) NOT NULL DEFAULT '6' COMMENT 'type id',
  `creationDate` datetime NOT NULL,
  `interest` varchar(16) COLLATE utf8_spanish_ci NOT NULL COMMENT 'enum InteractionInterest',
  `why` varchar(5000) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `file` varchar(400) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fileMime` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `observations` varchar(5000) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `offerId` int(11) DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `medio` varchar(16) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'EMAIL' COMMENT 'enum InteractionMedio',
  `organizationId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_interaction_projectId` (`projectId`),
  KEY `ndx_interaction_userId` (`userId`),
  KEY `ndx_interaction_interactionTypeId` (`interactionTypeId`),
  KEY `ndx_interaction_offerId` (`offerId`),
  KEY `ndx_interaction_organizationId` (`organizationId`),
  KEY `FKD15475F2101189C2` (`organizationId`),
  KEY `FKD15475F2B930816C` (`projectId`),
  KEY `FKD15475F22C2D1832` (`interactionTypeId`),
  KEY `FKD15475F2C5F3E6F2` (`offerId`),
  KEY `FKD15475F22A1FD1F2` (`userId`),
  CONSTRAINT `FKD15475F2101189C2` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `FKD15475F22A1FD1F2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `FKD15475F22C2D1832` FOREIGN KEY (`interactionTypeId`) REFERENCES `InteractionType` (`id`),
  CONSTRAINT `FKD15475F2B930816C` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `FKD15475F2C5F3E6F2` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
  CONSTRAINT `fk_interaction_interactionTypeId` FOREIGN KEY (`interactionTypeId`) REFERENCES `InteractionType` (`id`),
  CONSTRAINT `fk_interaction_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
  CONSTRAINT `fk_interaction_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_interaction_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_interaction_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='contactos que se mantienen con ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Interaction`
--

LOCK TABLES `Interaction` WRITE;
/*!40000 ALTER TABLE `Interaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `Interaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `InteractionType`
--

DROP TABLE IF EXISTS `InteractionType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `InteractionType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Interaction type descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Interaction type description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de interacciones';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `InteractionType`
--

LOCK TABLES `InteractionType` WRITE;
/*!40000 ALTER TABLE `InteractionType` DISABLE KEYS */;
INSERT INTO `InteractionType` VALUES (1,'No conformidad','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` VALUES (2,'Accion comercial','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` VALUES (3,'Envío de oferta','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` VALUES (4,'Envío de factura','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` VALUES (5,'Accion administrativa','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` VALUES (6,'No definida','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` VALUES (7,'Primer contacto','',NULL,NULL,'2011-01-09 19:37:08','2011-01-09 19:37:08',NULL);
INSERT INTO `InteractionType` VALUES (8,'Confirmación de oferta','',NULL,NULL,'2011-01-09 19:37:08','2011-01-09 19:37:08',NULL);
/*!40000 ALTER TABLE `InteractionType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Inventory`
--

DROP TABLE IF EXISTS `Inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyDate` date DEFAULT NULL,
  `asignedToId` int(11) DEFAULT NULL,
  `renting` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'De renting (1) o no (0)',
  `cost` decimal(10,2) DEFAULT NULL,
  `amortizable` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Amortizable (1) o no (0)consumible',
  `serialNumber` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `type` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `provider` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `trademark` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `model` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `speed` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `storage` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ram` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `location` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `description` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_invetory_userId` (`asignedToId`),
  KEY `FKFF02393C8C4344DF` (`asignedToId`),
  CONSTRAINT `FKFF02393C8C4344DF` FOREIGN KEY (`asignedToId`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_inventory_userId` FOREIGN KEY (`asignedToId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Inventory de mquinas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Inventory`
--

LOCK TABLES `Inventory` WRITE;
/*!40000 ALTER TABLE `Inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `Inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Magazine`
--

DROP TABLE IF EXISTS `Magazine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Magazine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Magazines a las que se envan';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Magazine`
--

LOCK TABLES `Magazine` WRITE;
/*!40000 ALTER TABLE `Magazine` DISABLE KEYS */;
/*!40000 ALTER TABLE `Magazine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NotaSalida`
--

DROP TABLE IF EXISTS `NotaSalida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NotaSalida` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `fecha` date NOT NULL,
  `peticionarioId` int(11) DEFAULT NULL,
  `clienteOrigenId` int(11) DEFAULT NULL,
  `observaciones` text COLLATE utf8_spanish_ci,
  `unico` tinyint(1) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `ndx_notaSalida_peticionarioId` (`peticionarioId`),
  KEY `ndx_notaSalida_clienteOrigenId` (`clienteOrigenId`),
  KEY `FK687D33B67A5F3C55` (`peticionarioId`),
  KEY `FK687D33B6AA4EB573` (`clienteOrigenId`),
  CONSTRAINT `FK687D33B67A5F3C55` FOREIGN KEY (`peticionarioId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `FK687D33B6AA4EB573` FOREIGN KEY (`clienteOrigenId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_notaSalida_clienteOrigenId` FOREIGN KEY (`clienteOrigenId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_notaSalida_peticionarioId` FOREIGN KEY (`peticionarioId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NotaSalida`
--

LOCK TABLES `NotaSalida` WRITE;
/*!40000 ALTER TABLE `NotaSalida` DISABLE KEYS */;
/*!40000 ALTER TABLE `NotaSalida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Objective`
--

DROP TABLE IF EXISTS `Objective`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Objective` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `projectId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `state` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `name` varchar(512) COLLATE utf8_spanish_ci NOT NULL,
  `log` longtext COLLATE utf8_spanish_ci,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_objective_userId` (`userId`),
  KEY `ndx_objective_projectId` (`projectId`),
  KEY `FK1EA8D919B930816C` (`projectId`),
  KEY `FK1EA8D9192A1FD1F2` (`userId`),
  CONSTRAINT `FK1EA8D9192A1FD1F2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `FK1EA8D919B930816C` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_objective_projectId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_objective_userId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Se almacenan los Objectives';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Objective`
--

LOCK TABLES `Objective` WRITE;
/*!40000 ALTER TABLE `Objective` DISABLE KEYS */;
/*!40000 ALTER TABLE `Objective` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Occupation`
--

DROP TABLE IF EXISTS `Occupation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Occupation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `duration` int(11) NOT NULL COMMENT 'Duración en minutos',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_occupation_userId` (`userId`),
  KEY `ndx_occupation_projectId` (`projectId`),
  KEY `FKD71A456BB930816C` (`projectId`),
  KEY `FKD71A456B2A1FD1F2` (`userId`),
  CONSTRAINT `FKD71A456B2A1FD1F2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `FKD71A456BB930816C` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_occupation_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_occupation_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Future occupations of Users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Occupation`
--

LOCK TABLES `Occupation` WRITE;
/*!40000 ALTER TABLE `Occupation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Occupation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Offer`
--

DROP TABLE IF EXISTS `Offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Offer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `organizationId` int(11) NOT NULL,
  `contactId` int(11) NOT NULL,
  `creationDate` date NOT NULL,
  `validityDate` date DEFAULT NULL,
  `maturityDate` date DEFAULT NULL,
  `offerPotential` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `offerState` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `offerRejectReasonId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `number` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `billId` int(11) DEFAULT NULL,
  `informesCreados` tinyint(1) DEFAULT '0',
  `iva` decimal(4,2) DEFAULT '16.00',
  `lineaTrabajoId` int(10) unsigned DEFAULT NULL,
  `orderNumber` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_offer_organizationId` (`organizationId`),
  KEY `ndx_offer_contactId` (`contactId`),
  KEY `ndx_offer_offerRejectReasonId` (`offerRejectReasonId`),
  KEY `ndx_offer_userId` (`userId`),
  KEY `ndx_offer_billId` (`billId`),
  KEY `ndx_offer_lineaTrabajoId` (`lineaTrabajoId`),
  KEY `FK4892A3C921236A` (`billId`),
  KEY `FK4892A3C101189C2` (`organizationId`),
  KEY `FK4892A3C736E2538` (`offerRejectReasonId`),
  KEY `FK4892A3C615CB88` (`lineaTrabajoId`),
  KEY `FK4892A3C212C0DBA` (`contactId`),
  KEY `FK4892A3C2A1FD1F2` (`userId`),
  CONSTRAINT `FK4892A3C101189C2` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `FK4892A3C212C0DBA` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `FK4892A3C2A1FD1F2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `FK4892A3C615CB88` FOREIGN KEY (`lineaTrabajoId`) REFERENCES `Department` (`id`),
  CONSTRAINT `FK4892A3C736E2538` FOREIGN KEY (`offerRejectReasonId`) REFERENCES `OfferRejectReason` (`id`),
  CONSTRAINT `FK4892A3C921236A` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
  CONSTRAINT `fk_offer_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
  CONSTRAINT `fk_offer_contactId` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_offer_lineaTrabajoId` FOREIGN KEY (`lineaTrabajoId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_offer_offerRejectReasonId` FOREIGN KEY (`offerRejectReasonId`) REFERENCES `OfferRejectReason` (`id`),
  CONSTRAINT `fk_offer_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_offer_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ofertas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Offer`
--

LOCK TABLES `Offer` WRITE;
/*!40000 ALTER TABLE `Offer` DISABLE KEYS */;
INSERT INTO `Offer` VALUES (7,'Recepción ATE-8834','',1,4,2,'2012-01-10','2012-02-09',NULL,'HIGH','ACCEPTED',NULL,1,1,'2012-01-10 01:38:56','2012-01-10 22:43:01','P001/12','',1,NULL,1,16.00,5,'');
INSERT INTO `Offer` VALUES (8,'Recepción ATE-8834','',1,4,2,'2012-01-10','2012-02-09',NULL,'HIGH','OPEN',NULL,1,1,'2012-01-10 01:56:53','2012-01-10 01:56:53','P002/12','',NULL,NULL,0,16.00,5,'');
INSERT INTO `Offer` VALUES (9,'Recepción ATE-8834','',1,4,2,'2012-01-10','2012-02-09',NULL,'HIGH','OPEN',NULL,1,1,'2012-01-10 22:42:56','2012-01-10 22:42:56','P003/12','',NULL,NULL,0,16.00,5,'');
/*!40000 ALTER TABLE `Offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OfferCost`
--

DROP TABLE IF EXISTS `OfferCost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OfferCost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `offerId` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_offerCost_offerId` (`offerId`),
  KEY `FKEB51D029C5F3E6F2` (`offerId`),
  CONSTRAINT `FKEB51D029C5F3E6F2` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
  CONSTRAINT `fk_offerCost_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OfferCost`
--

LOCK TABLES `OfferCost` WRITE;
/*!40000 ALTER TABLE `OfferCost` DISABLE KEYS */;
/*!40000 ALTER TABLE `OfferCost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OfferEnsayo`
--

DROP TABLE IF EXISTS `OfferEnsayo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OfferEnsayo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `offerId` int(11) NOT NULL,
  `ensayoId` int(11) NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `unidades` int(11) NOT NULL DEFAULT '1',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `listindex` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_offerEnsayo_ensayoId` (`ensayoId`),
  KEY `ndx_offerEnsayo_offerId` (`offerId`),
  KEY `FK6179CB69964F19B6` (`ensayoId`),
  KEY `FK6179CB69C5F3E6F2` (`offerId`),
  CONSTRAINT `FK6179CB69964F19B6` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
  CONSTRAINT `FK6179CB69C5F3E6F2` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
  CONSTRAINT `fk_offerEnsayo_ensayoId` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
  CONSTRAINT `fk_offerEnsayo_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OfferEnsayo`
--

LOCK TABLES `OfferEnsayo` WRITE;
/*!40000 ALTER TABLE `OfferEnsayo` DISABLE KEYS */;
/*!40000 ALTER TABLE `OfferEnsayo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OfferPauta`
--

DROP TABLE IF EXISTS `OfferPauta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OfferPauta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cost` decimal(10,2) NOT NULL,
  `offerId` int(11) NOT NULL,
  `pautaId` int(11) NOT NULL,
  `unidades` int(11) NOT NULL DEFAULT '1',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `listindex` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_offerPauta_offerId` (`offerId`),
  KEY `ndx_offerPauta_pautaId` (`pautaId`),
  KEY `FK7F991135C5F3E6F2` (`offerId`),
  KEY `FK7F991135F337E51C` (`pautaId`),
  CONSTRAINT `FK7F991135C5F3E6F2` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
  CONSTRAINT `FK7F991135F337E51C` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`),
  CONSTRAINT `fk_offerPauta_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
  CONSTRAINT `fk_offerPauta_pautaId` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OfferPauta`
--

LOCK TABLES `OfferPauta` WRITE;
/*!40000 ALTER TABLE `OfferPauta` DISABLE KEYS */;
INSERT INTO `OfferPauta` VALUES (7,29.50,7,1,2,NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `OfferPauta` VALUES (8,29.50,8,1,2,NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `OfferPauta` VALUES (9,29.50,9,1,2,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `OfferPauta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OfferRejectReason`
--

DROP TABLE IF EXISTS `OfferRejectReason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OfferRejectReason` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Causas rechazo de ofertas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OfferRejectReason`
--

LOCK TABLES `OfferRejectReason` WRITE;
/*!40000 ALTER TABLE `OfferRejectReason` DISABLE KEYS */;
INSERT INTO `OfferRejectReason` VALUES (1,'Sin respuesta','El cliente no responde a la oferta',1,1,'2011-01-09 19:37:08','2011-01-09 19:37:08',NULL);
INSERT INTO `OfferRejectReason` VALUES (2,'Oferta cara','El cliente considera la oferta excesivamente cara',1,1,'2011-01-09 19:37:08','2011-01-09 19:37:08',NULL);
INSERT INTO `OfferRejectReason` VALUES (3,'Tecnología inadecuada','Tecnología escogida en la oferta inadecuada',1,1,'2011-01-09 19:37:08','2011-01-09 19:37:08',NULL);
INSERT INTO `OfferRejectReason` VALUES (4,'Proyecto retrasado','El proyecto para el cual se hizo la oferta ha sido retrasado',1,1,'2011-01-09 19:37:08','2011-01-09 19:37:08',NULL);
INSERT INTO `OfferRejectReason` VALUES (5,'Proyecto cancelado','El proyecto para el cual se hizo la oferta ha sido cancelado',1,1,'2011-01-09 19:37:08','2011-01-09 19:37:08',NULL);
/*!40000 ALTER TABLE `OfferRejectReason` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OfferRole`
--

DROP TABLE IF EXISTS `OfferRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OfferRole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `offerId` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `costPerHour` decimal(10,2) NOT NULL,
  `expectedHours` decimal(10,2) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_offerRole_offerId` (`offerId`),
  KEY `FKEB58A0D2C5F3E6F2` (`offerId`),
  CONSTRAINT `FKEB58A0D2C5F3E6F2` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
  CONSTRAINT `fk_offerRole_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OfferRole`
--

LOCK TABLES `OfferRole` WRITE;
/*!40000 ALTER TABLE `OfferRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `OfferRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Organization`
--

DROP TABLE IF EXISTS `Organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizationTypeId` int(11) NOT NULL DEFAULT '1' COMMENT 'organization type id',
  `organizationISOCategoryId` int(11) NOT NULL DEFAULT '1' COMMENT 'iso category id',
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `cif` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `street` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `number` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Building number in street',
  `locator` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Location information inside building',
  `postalCode` varchar(32) COLLATE utf8_spanish_ci DEFAULT NULL,
  `city` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provinceId` int(11) NOT NULL,
  `state` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `country` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fax` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `website` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ftpsite` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `notes` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `acronimo` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `certificado` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `columnaRefCli` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `direccionEnvioFactura` text COLLATE utf8_spanish_ci,
  `direccionEnvioInformes` text COLLATE utf8_spanish_ci,
  `numeroProveedor` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fechaAlta` date DEFAULT NULL,
  `fechaCaducidadCertificado` date DEFAULT NULL,
  `procedimientoAdministrativo` text COLLATE utf8_spanish_ci,
  PRIMARY KEY (`id`),
  KEY `ndx_organization_organizationTypeId` (`organizationTypeId`),
  KEY `ndx_organization_isoOrganizationCategoryId` (`organizationISOCategoryId`),
  KEY `ndx_organization_provinceId` (`provinceId`),
  KEY `FK50104153491C8A56` (`organizationTypeId`),
  KEY `FK501041539249E65A` (`organizationISOCategoryId`),
  KEY `FK50104153EFBC22FC` (`provinceId`),
  CONSTRAINT `FK50104153491C8A56` FOREIGN KEY (`organizationTypeId`) REFERENCES `OrganizationType` (`id`),
  CONSTRAINT `FK501041539249E65A` FOREIGN KEY (`organizationISOCategoryId`) REFERENCES `OrganizationISOCategory` (`id`),
  CONSTRAINT `FK50104153EFBC22FC` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`),
  CONSTRAINT `fk_organization_isoOrganizationCategoryId` FOREIGN KEY (`organizationISOCategoryId`) REFERENCES `OrganizationISOCategory` (`id`),
  CONSTRAINT `fk_organization_organizationTypeId` FOREIGN KEY (`organizationTypeId`) REFERENCES `OrganizationType` (`id`),
  CONSTRAINT `fk_organization_provinceId` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='los clientes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Organization`
--

LOCK TABLES `Organization` WRITE;
/*!40000 ALTER TABLE `Organization` DISABLE KEYS */;
INSERT INTO `Organization` VALUES (1,2,1,'Nuestra empresa','C1910548E','956333222','Ancha','88','','11005','Cádiz',11,'Andalucía','España','956333223','info@miempresa.com','www.miempresa.com','','',NULL,NULL,NULL,'2012-01-10 22:42:11',1,'NE','SI','','N/A','N/A','','ACEPTADO','2011-12-01',NULL,'N/A');
INSERT INTO `Organization` VALUES (2,2,1,'Nuestro laboratorio','N5460948B','956222111','Estrecha','88','','11555','Cádiz',11,'Andalucía','España','956222112','lab@miempresa.com','www.miempresa.com','','',NULL,NULL,NULL,'2011-12-31 13:31:46',1,'NL','SI','','N/A','N/A','','ACEPTADO',NULL,NULL,'N/A');
INSERT INTO `Organization` VALUES (4,1,1,'Acronotex','K5798889A','955111222','Ganadores','88','','41001','Sevilla',41,'Andalucía','España','955111221','acronotex@acronotex.es','www.acronotex.es','','',1,1,'2011-01-09 17:26:54','2011-12-31 13:34:28',1,'ATE','PENDIENTE','ID.','Calle Ganadores, 88\r\n41001 Sevilla','Calle Ganadores, 88\r\n41001 Sevilla','23','ACEPTADO','2010-01-01','2014-12-16','Llamar a Juan Pérez (955111222) y hablar con él la forma de enviarle los resultados, ya que cada trabajo tiene un procedimiento diferente.');
/*!40000 ALTER TABLE `Organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrganizationISOCategory`
--

DROP TABLE IF EXISTS `OrganizationISOCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrganizationISOCategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'ISO Organization Category descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'ISO Organization Category description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de organizaciones segun ISO';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrganizationISOCategory`
--

LOCK TABLES `OrganizationISOCategory` WRITE;
/*!40000 ALTER TABLE `OrganizationISOCategory` DISABLE KEYS */;
INSERT INTO `OrganizationISOCategory` VALUES (1,'A','Proveedor / Subcontratista habitual.',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationISOCategory` VALUES (2,'B','Proveedor / Subcontratista recomendado.',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationISOCategory` VALUES (3,'C','Proveedor / Subcontratista no habitual.',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationISOCategory` VALUES (4,'D','Proveedor / Subcontratista que haya tenido disconformidades.',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `OrganizationISOCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrganizationType`
--

DROP TABLE IF EXISTS `OrganizationType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrganizationType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Organization type descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Organization type description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de organizaciones';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrganizationType`
--

LOCK TABLES `OrganizationType` WRITE;
/*!40000 ALTER TABLE `OrganizationType` DISABLE KEYS */;
INSERT INTO `OrganizationType` VALUES (1,'Cliente','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationType` VALUES (2,'Proveedor','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationType` VALUES (3,'Cliente y proveedor','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationType` VALUES (4,'Prospecto','Posible cliente',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `OrganizationType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ParametroString`
--

DROP TABLE IF EXISTS `ParametroString`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ParametroString` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `incertidumbre` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreIngles` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pautaEnsayoId` int(11) DEFAULT NULL,
  `requerimiento` varchar(800) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `listindex` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_parametroString_pautaEnsayoId` (`pautaEnsayoId`),
  KEY `FK85F32A8AE16F6156` (`pautaEnsayoId`),
  CONSTRAINT `FK85F32A8AE16F6156` FOREIGN KEY (`pautaEnsayoId`) REFERENCES `PautaEnsayo` (`id`),
  CONSTRAINT `fk_parametroString_pautaEnsayoId` FOREIGN KEY (`pautaEnsayoId`) REFERENCES `PautaEnsayo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ParametroString`
--

LOCK TABLES `ParametroString` WRITE;
/*!40000 ALTER TABLE `ParametroString` DISABLE KEYS */;
INSERT INTO `ParametroString` VALUES (1,NULL,'Average strengh','Tensión media',1,'12 N/mm²',NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `ParametroString` VALUES (2,NULL,'Maximum strengh','Tensión máxima',1,'19 N/mm²',NULL,NULL,NULL,NULL,NULL,1);
INSERT INTO `ParametroString` VALUES (3,NULL,'Maximum torsion','Torsión máxima',2,'2Kg/cm',NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `ParametroString` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pauta`
--

DROP TABLE IF EXISTS `Pauta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pauta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agrupacion` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `clienteId` int(11) DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ensayosSolicitados` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `familia` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `material` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Ensayo descriptive name',
  `referenciaCliente` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tiempoRespuesta` int(11) DEFAULT NULL,
  `titulo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pauta_clienteId` (`clienteId`),
  KEY `FK49535F1D5D50CC9` (`clienteId`),
  CONSTRAINT `FK49535F1D5D50CC9` FOREIGN KEY (`clienteId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_pauta_clienteId` FOREIGN KEY (`clienteId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='ensayos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pauta`
--

LOCK TABLES `Pauta` WRITE;
/*!40000 ALTER TABLE `Pauta` DISABLE KEYS */;
INSERT INTO `Pauta` VALUES (1,'Aceros',4,29.50,'','','Metales','Acero inoxidable','ATE-595.5/2008','ATE-595.5/2008',3,'Recepción técnica de Tuercas M3 para A380',1,1,'2012-01-09 22:33:38','2012-01-10 00:50:58',1);
/*!40000 ALTER TABLE `Pauta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PautaEnsayo`
--

DROP TABLE IF EXISTS `PautaEnsayo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PautaEnsayo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ensayoId` int(11) NOT NULL,
  `procedimiento` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `pautaId` int(11) NOT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `listindex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_pautaEnsayo_ensayoId` (`ensayoId`),
  KEY `ndx_pautaEnsayo_pautaId` (`pautaId`),
  KEY `FK73D97B5E964F19B6` (`ensayoId`),
  KEY `FK73D97B5EF337E51C` (`pautaId`),
  CONSTRAINT `FK73D97B5E964F19B6` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
  CONSTRAINT `FK73D97B5EF337E51C` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`),
  CONSTRAINT `fk_pautaEnsayo_ensayoId` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
  CONSTRAINT `fk_pautaEnsayo_pautaId` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PautaEnsayo`
--

LOCK TABLES `PautaEnsayo` WRITE;
/*!40000 ALTER TABLE `PautaEnsayo` DISABLE KEYS */;
INSERT INTO `PautaEnsayo` VALUES (1,3,'ATE-994.3/89',NULL,NULL,NULL,'2012-01-09 22:41:10',1,1,0);
INSERT INTO `PautaEnsayo` VALUES (2,2,'DIN-2334.9',NULL,NULL,NULL,'2012-01-09 22:40:53',1,1,1);
/*!40000 ALTER TABLE `PautaEnsayo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PautaEnsayoDimension`
--

DROP TABLE IF EXISTS `PautaEnsayoDimension`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PautaEnsayoDimension` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pautaEnsayoId` int(11) NOT NULL,
  `valor` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `listindex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_pautaEnsayoDimension_pautaEnsayoId` (`pautaEnsayoId`),
  KEY `fk_pautaEnsayoDimension_pautaEnsayoId` (`pautaEnsayoId`),
  KEY `FK2C5FBA8E16F6156` (`pautaEnsayoId`),
  CONSTRAINT `FK2C5FBA8E16F6156` FOREIGN KEY (`pautaEnsayoId`) REFERENCES `PautaEnsayo` (`id`),
  CONSTRAINT `fk_pautaEnsayoDimension_pautaEnsayoId` FOREIGN KEY (`pautaEnsayoId`) REFERENCES `PautaEnsayo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PautaEnsayoDimension`
--

LOCK TABLES `PautaEnsayoDimension` WRITE;
/*!40000 ALTER TABLE `PautaEnsayoDimension` DISABLE KEYS */;
/*!40000 ALTER TABLE `PautaEnsayoDimension` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PautaIdentificacion`
--

DROP TABLE IF EXISTS `PautaIdentificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PautaIdentificacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `pautaId` int(11) NOT NULL,
  `valor` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `listindex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_pautaIdentificacion_pautaId` (`pautaId`),
  KEY `FKBD1FD070F337E51C` (`pautaId`),
  CONSTRAINT `FKBD1FD070F337E51C` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`),
  CONSTRAINT `fk_pautaIdentificacion_pautaId` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PautaIdentificacion`
--

LOCK TABLES `PautaIdentificacion` WRITE;
/*!40000 ALTER TABLE `PautaIdentificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `PautaIdentificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PautaPrecio`
--

DROP TABLE IF EXISTS `PautaPrecio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PautaPrecio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cost` decimal(10,2) DEFAULT NULL,
  `organizationId` int(11) NOT NULL,
  `pautaId` int(11) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_pautaPrecio_organizationId` (`organizationId`),
  KEY `ndx_paitaPrecio_pautaId` (`pautaId`),
  KEY `FK86D0D097101189C2` (`organizationId`),
  KEY `FK86D0D097F337E51C` (`pautaId`),
  CONSTRAINT `FK86D0D097101189C2` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `FK86D0D097F337E51C` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`),
  CONSTRAINT `fk_pautaPrecio_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_pautaPrecio_pautaId` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PautaPrecio`
--

LOCK TABLES `PautaPrecio` WRITE;
/*!40000 ALTER TABLE `PautaPrecio` DISABLE KEYS */;
/*!40000 ALTER TABLE `PautaPrecio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PeriodicalAccountEntry`
--

DROP TABLE IF EXISTS `PeriodicalAccountEntry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PeriodicalAccountEntry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL COMMENT 'account where the entry is charged',
  `accountEntryTypeId` int(11) NOT NULL COMMENT 'entry type',
  `frequencyId` int(11) NOT NULL COMMENT 'entry frequency',
  `concept` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `entryDate` date NOT NULL COMMENT 'entry date',
  `amount` decimal(10,2) NOT NULL COMMENT 'entry amount',
  `rise` decimal(4,2) DEFAULT NULL COMMENT 'account entry rise',
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_periodicalAccountEntry_accountId` (`accountId`),
  KEY `ndx_periodicalAccountEntry_accountEntryTypeId` (`accountEntryTypeId`),
  KEY `ndx_periodicalAccountEntry_frequencyId` (`frequencyId`),
  KEY `FK318F18B44025E1A` (`accountEntryTypeId`),
  KEY `FK318F18BED42D572` (`frequencyId`),
  KEY `FK318F18B4DE38A94` (`accountId`),
  CONSTRAINT `FK318F18B44025E1A` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`),
  CONSTRAINT `FK318F18B4DE38A94` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`),
  CONSTRAINT `FK318F18BED42D572` FOREIGN KEY (`frequencyId`) REFERENCES `Frequency` (`id`),
  CONSTRAINT `fk_periodicalAccountEntry_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`),
  CONSTRAINT `fk_periodicalAccountEntry_accountId` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`),
  CONSTRAINT `fk_periodicalAccountEntry_frequencyId` FOREIGN KEY (`frequencyId`) REFERENCES `Frequency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='asientos contables periodicos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PeriodicalAccountEntry`
--

LOCK TABLES `PeriodicalAccountEntry` WRITE;
/*!40000 ALTER TABLE `PeriodicalAccountEntry` DISABLE KEYS */;
/*!40000 ALTER TABLE `PeriodicalAccountEntry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductoPrecio`
--

DROP TABLE IF EXISTS `ProductoPrecio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductoPrecio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cost` decimal(19,2) DEFAULT NULL,
  `organizationId` int(11) NOT NULL,
  `producto` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_produtoPrecio_organizationId` (`organizationId`),
  KEY `FKD7E1A8E6101189C2` (`organizationId`),
  CONSTRAINT `FKD7E1A8E6101189C2` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_productoPrecio_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductoPrecio`
--

LOCK TABLES `ProductoPrecio` WRITE;
/*!40000 ALTER TABLE `ProductoPrecio` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProductoPrecio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Project`
--

DROP TABLE IF EXISTS `Project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizationId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `open` tinyint(1) DEFAULT '0',
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  `updatedById` int(11) DEFAULT NULL,
  `albaranId` int(11) DEFAULT NULL,
  `billId` int(11) DEFAULT NULL,
  `costeAFacturar` decimal(19,2) DEFAULT NULL,
  `descripcion` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ensayosSolicitados` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `especificacion` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estadoDeRecepcion` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fabricante` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fechaCaducidad` datetime DEFAULT NULL,
  `loteFabricante` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `lote` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `motivoDevolucion` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numMuestras` int(11) DEFAULT NULL,
  `objetivosDelInforme` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `observaciones` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `offerId` int(11) DEFAULT NULL,
  `orderNumber` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `plantillaLab` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `plantilla` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `proveedor` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `realizadoFecha` datetime DEFAULT NULL,
  `realizadoPor` int(11) DEFAULT NULL,
  `recibidoPor` int(11) DEFAULT NULL,
  `referenciaCliente` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `referenciaLaboratorio` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `supervisadoPor` int(11) DEFAULT NULL,
  `tiempoRespuesta` int(11) DEFAULT NULL,
  `titulo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `referenciaLaboratorio` (`referenciaLaboratorio`),
  KEY `ndx_project_organizationId` (`organizationId`),
  KEY `ndx_project_albaranId` (`albaranId`),
  KEY `ndx_project_billId` (`billId`),
  KEY `ndx_project_offerId` (`offerId`),
  KEY `ndx_project_realizadoPor` (`realizadoPor`),
  KEY `ndx_project_recibidoPor` (`recibidoPor`),
  KEY `ndx_project_supervisadoPor` (`supervisadoPor`),
  KEY `FK50C8E2F9B71AE0A4` (`albaranId`),
  KEY `FK50C8E2F9921236A` (`billId`),
  KEY `FK50C8E2F9101189C2` (`organizationId`),
  KEY `FK50C8E2F91AAE04F8` (`supervisadoPor`),
  KEY `FK50C8E2F9C5F3E6F2` (`offerId`),
  KEY `FK50C8E2F9204C8A02` (`realizadoPor`),
  KEY `FK50C8E2F9F59D0DD4` (`recibidoPor`),
  CONSTRAINT `FK50C8E2F9101189C2` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `FK50C8E2F91AAE04F8` FOREIGN KEY (`supervisadoPor`) REFERENCES `User` (`id`),
  CONSTRAINT `FK50C8E2F9204C8A02` FOREIGN KEY (`realizadoPor`) REFERENCES `User` (`id`),
  CONSTRAINT `FK50C8E2F9921236A` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
  CONSTRAINT `FK50C8E2F9B71AE0A4` FOREIGN KEY (`albaranId`) REFERENCES `Albaran` (`id`),
  CONSTRAINT `FK50C8E2F9C5F3E6F2` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
  CONSTRAINT `FK50C8E2F9F59D0DD4` FOREIGN KEY (`recibidoPor`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_project_albaranId` FOREIGN KEY (`albaranId`) REFERENCES `Albaran` (`id`),
  CONSTRAINT `fk_project_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
  CONSTRAINT `fk_project_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
  CONSTRAINT `fk_project_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_project_realizadoPor` FOREIGN KEY (`realizadoPor`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_project_recibidoPor` FOREIGN KEY (`recibidoPor`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_project_supervisadoPor` FOREIGN KEY (`supervisadoPor`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Projects';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Project`
--

LOCK TABLES `Project` WRITE;
/*!40000 ALTER TABLE `Project` DISABLE KEYS */;
INSERT INTO `Project` VALUES (6,4,'2012-01-10','2012-01-11',0,'P001/12 Recepción ATE-8834','',1,1,'2012-01-10 01:39:07','2012-01-10 01:40:37',1,1,1,NULL,29.50,'','','ATE-595.5/2008','',NULL,NULL,NULL,NULL,'',2,NULL,'',7,'','boleto_lab','tit',NULL,'2012-01-10 00:00:00',1,1,'ATE-595.5/2008','ATE0001/12',1,3,'Recepción técnica de Tuercas M3 para A380');
INSERT INTO `Project` VALUES (7,4,'2012-01-10','2012-01-12',0,'P001/12 Recepción ATE-8834','',1,1,'2012-01-10 01:39:07','2012-01-10 22:52:25',1,1,NULL,NULL,29.50,'','','ATE-595.5/2008','',NULL,NULL,NULL,NULL,'',NULL,NULL,'',7,'','boleto_lab','tit',NULL,'2012-01-12 00:00:00',1,1,'ATE-595.5/2008','ATE0002/12',1,3,'Recepción técnica de Tuercas M3 para A380');
/*!40000 ALTER TABLE `Project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProjectCost`
--

DROP TABLE IF EXISTS `ProjectCost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProjectCost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_projectCost_projectId` (`projectId`),
  KEY `FK2B5CE266B930816C` (`projectId`),
  CONSTRAINT `FK2B5CE266B930816C` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_projectCost_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProjectCost`
--

LOCK TABLES `ProjectCost` WRITE;
/*!40000 ALTER TABLE `ProjectCost` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProjectCost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProjectEnsayo`
--

DROP TABLE IF EXISTS `ProjectEnsayo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProjectEnsayo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aprobadoFecha` datetime DEFAULT NULL,
  `aprobadoPor` int(11) DEFAULT NULL,
  `conformidad` varchar(800) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cost` decimal(10,2) NOT NULL,
  `ensayoId` int(11) NOT NULL,
  `fechaRecepcionMuestra` datetime DEFAULT NULL,
  `identificacionCliente` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `identificacionLab` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `idTitMatEns` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `notaSalidaId` int(11) DEFAULT NULL,
  `observaciones` text COLLATE utf8_spanish_ci,
  `photo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `plantillaDim` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `plantillaLab` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `plantillaReq` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `plantillaTit` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `plantilla` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `procedimiento` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `projectId` int(11) NOT NULL,
  `realizadoFecha` datetime DEFAULT NULL,
  `realizadoPor` int(11) DEFAULT NULL,
  `requerimiento` varchar(800) COLLATE utf8_spanish_ci DEFAULT NULL,
  `revisadoFecha` datetime DEFAULT NULL,
  `revisadoPor` int(11) DEFAULT NULL,
  `valor` varchar(800) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `listindex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_projectEnsayo_aprobadoPor` (`aprobadoPor`),
  KEY `ndx_projectEnsayo_ensayoId` (`ensayoId`),
  KEY `ndx_projectEnsayo_notaSalidaId` (`notaSalidaId`),
  KEY `ndx_projectEnsayo_projectId` (`projectId`),
  KEY `ndx_projectEnsayo_realizadoPor` (`realizadoPor`),
  KEY `ndx_projectEnsayo_revisadoPor` (`revisadoPor`),
  KEY `FKCB0942661291B9E8` (`notaSalidaId`),
  KEY `FKCB094266B930816C` (`projectId`),
  KEY `FKCB094266964F19B6` (`ensayoId`),
  KEY `FKCB094266A337D4A9` (`aprobadoPor`),
  KEY `FKCB0942666C547540` (`revisadoPor`),
  KEY `FKCB094266204C8A02` (`realizadoPor`),
  CONSTRAINT `FKCB0942661291B9E8` FOREIGN KEY (`notaSalidaId`) REFERENCES `NotaSalida` (`id`),
  CONSTRAINT `FKCB094266204C8A02` FOREIGN KEY (`realizadoPor`) REFERENCES `User` (`id`),
  CONSTRAINT `FKCB0942666C547540` FOREIGN KEY (`revisadoPor`) REFERENCES `User` (`id`),
  CONSTRAINT `FKCB094266964F19B6` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
  CONSTRAINT `FKCB094266A337D4A9` FOREIGN KEY (`aprobadoPor`) REFERENCES `User` (`id`),
  CONSTRAINT `FKCB094266B930816C` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_projectEnsayo_aprobadoPor` FOREIGN KEY (`aprobadoPor`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_projectEnsayo_ensayoId` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
  CONSTRAINT `fk_projectEnsayo_notaSalidaId` FOREIGN KEY (`notaSalidaId`) REFERENCES `NotaSalida` (`id`),
  CONSTRAINT `fk_projectEnsayo_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_projectEnsayo_realizadoPor` FOREIGN KEY (`realizadoPor`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_projectEnsayo_revisadoPor` FOREIGN KEY (`revisadoPor`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProjectEnsayo`
--

LOCK TABLES `ProjectEnsayo` WRITE;
/*!40000 ALTER TABLE `ProjectEnsayo` DISABLE KEYS */;
INSERT INTO `ProjectEnsayo` VALUES (1,'2012-01-11 00:00:00',1,'',13.75,3,NULL,'ATE-595.5/2008','ATE0001/12-1','',NULL,'',NULL,'tit_ensayo_dims','lab_ensayo','tit_ensayo_params','tit_ensayo','lab','ATE-994.3/89',6,'2012-01-10 00:00:00',1,'','2012-01-11 00:00:00',1,'',NULL,NULL,NULL,'2012-01-10 01:43:14',1,0);
INSERT INTO `ProjectEnsayo` VALUES (2,'2012-01-11 00:00:00',1,'NO CONFORME',16.06,2,'2012-01-10 00:00:00','ATE-595.5/2008','ATE0001/12-2','',NULL,'',NULL,'tit_ensayo_dims','lab_ensayo','tit_ensayo_params','tit_ensayo','lab','DIN-2334.9',6,'2012-01-10 00:00:00',1,'2Kg/cm','2012-01-11 00:00:00',1,'2.5±3%',NULL,NULL,NULL,'2012-01-10 01:47:39',1,1);
INSERT INTO `ProjectEnsayo` VALUES (3,NULL,NULL,NULL,13.75,3,NULL,'ATE-595.5/2008','ATE0002/12-1',NULL,NULL,NULL,NULL,'tit_ensayo_dims','lab_ensayo','tit_ensayo_params','tit_ensayo','lab','ATE-994.3/89',7,'2012-01-12 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `ProjectEnsayo` VALUES (4,NULL,NULL,NULL,16.06,2,NULL,'ATE-595.5/2008','ATE0002/12-2',NULL,NULL,NULL,NULL,'tit_ensayo_dims','lab_ensayo','tit_ensayo_params','tit_ensayo','lab','DIN-2334.9',7,'2012-01-12 00:00:00',NULL,'2Kg/cm',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `ProjectEnsayo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProjectEnsayoDimension`
--

DROP TABLE IF EXISTS `ProjectEnsayoDimension`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProjectEnsayoDimension` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `projectEnsayoId` int(11) DEFAULT NULL,
  `valor` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `listindex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_projectEnsayoDimension_projectEnsayoId` (`projectEnsayoId`),
  KEY `FK9B19DA0217581A6` (`projectEnsayoId`),
  CONSTRAINT `FK9B19DA0217581A6` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`),
  CONSTRAINT `ndx_projectEnsayoDimension_projectEnsayoId` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProjectEnsayoDimension`
--

LOCK TABLES `ProjectEnsayoDimension` WRITE;
/*!40000 ALTER TABLE `ProjectEnsayoDimension` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProjectEnsayoDimension` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProjectEnsayoIdentificacion`
--

DROP TABLE IF EXISTS `ProjectEnsayoIdentificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProjectEnsayoIdentificacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `projectEnsayoId` int(11) DEFAULT NULL,
  `valor` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `listindex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_projectEnsayoIdentificacion_projectEnsayoId` (`projectEnsayoId`),
  KEY `FK82D66C25217581A6` (`projectEnsayoId`),
  CONSTRAINT `FK82D66C25217581A6` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`),
  CONSTRAINT `fk_projectEnsayoIdentificacion_projectEnsayoId` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProjectEnsayoIdentificacion`
--

LOCK TABLES `ProjectEnsayoIdentificacion` WRITE;
/*!40000 ALTER TABLE `ProjectEnsayoIdentificacion` DISABLE KEYS */;
INSERT INTO `ProjectEnsayoIdentificacion` VALUES (1,'Elemento',1,'AKGR43245-1',NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `ProjectEnsayoIdentificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProjectEnsayoRequerimiento`
--

DROP TABLE IF EXISTS `ProjectEnsayoRequerimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProjectEnsayoRequerimiento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conformidad` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreIngles` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `projectEnsayoId` int(11) DEFAULT NULL,
  `requerimiento` varchar(800) COLLATE utf8_spanish_ci DEFAULT NULL,
  `valor` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `listindex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_projectEnsayoRequerimiento_projectEnsayoId` (`projectEnsayoId`),
  KEY `FK592DCA5F217581A6` (`projectEnsayoId`),
  CONSTRAINT `FK592DCA5F217581A6` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`),
  CONSTRAINT `fk_projectEnsayoRequerimiento_projectEnsayoId` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProjectEnsayoRequerimiento`
--

LOCK TABLES `ProjectEnsayoRequerimiento` WRITE;
/*!40000 ALTER TABLE `ProjectEnsayoRequerimiento` DISABLE KEYS */;
INSERT INTO `ProjectEnsayoRequerimiento` VALUES (1,'CONFORME','Average strengh','Tensión media',1,'12 N/mm²','11.99±5%',NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `ProjectEnsayoRequerimiento` VALUES (2,'CONFORME','Maximum strengh','Tensión máxima',1,'19 N/mm²','20.02±5%',NULL,NULL,NULL,NULL,NULL,1);
INSERT INTO `ProjectEnsayoRequerimiento` VALUES (3,NULL,'Average strengh','Tensión media',3,'12 N/mm²',NULL,NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `ProjectEnsayoRequerimiento` VALUES (4,NULL,'Maximum strengh','Tensión máxima',3,'19 N/mm²',NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `ProjectEnsayoRequerimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProjectIdentificacion`
--

DROP TABLE IF EXISTS `ProjectIdentificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProjectIdentificacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `projectId` int(11) DEFAULT NULL,
  `valor` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `listindex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_projectIdentification_projectId` (`projectId`),
  KEY `FK49BC0F78B930816C` (`projectId`),
  CONSTRAINT `FK49BC0F78B930816C` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_projectIdentificacion_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProjectIdentificacion`
--

LOCK TABLES `ProjectIdentificacion` WRITE;
/*!40000 ALTER TABLE `ProjectIdentificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProjectIdentificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProjectRole`
--

DROP TABLE IF EXISTS `ProjectRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProjectRole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `costPerHour` decimal(10,2) NOT NULL,
  `expectedHours` int(11) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_projectRole_projectId` (`projectId`),
  KEY `FK2B63B30FB930816C` (`projectId`),
  CONSTRAINT `FK2B63B30FB930816C` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_projectRole_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProjectRole`
--

LOCK TABLES `ProjectRole` WRITE;
/*!40000 ALTER TABLE `ProjectRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProjectRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `Project_Estado`
--

DROP TABLE IF EXISTS `Project_Estado`;
/*!50001 DROP VIEW IF EXISTS `Project_Estado`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `Project_Estado` (
  `id` int(11),
  `estado` varchar(30)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Province`
--

DROP TABLE IF EXISTS `Province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Province` (
  `id` int(11) NOT NULL COMMENT 'El id no es autoincremental porque ya tienen unos códigos fijos',
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla con las Provinces';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Province`
--

LOCK TABLES `Province` WRITE;
/*!40000 ALTER TABLE `Province` DISABLE KEYS */;
INSERT INTO `Province` VALUES (1,'Alava',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (2,'Albacete',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (3,'Alicante',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (4,'Almería',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (5,'Avila',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (6,'Badajoz',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (7,'Balears, Illes',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (8,'Barcelona',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (9,'Burgos',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (10,'Cáceres',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (11,'Cádiz',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (12,'Castellón',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (13,'Ciudad Real',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (14,'Córdoba',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (15,'Coruña, A',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (16,'Cuenca',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (17,'Girona',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (18,'Granada',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (19,'Guadalajara',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (20,'Guipúzcoa',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (21,'Huelva',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (22,'Huesca',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (23,'Jaén',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (24,'León',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (25,'Lleida',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (26,'Rioja, La',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (27,'Lugo',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (28,'Madrid',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (29,'Málaga',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (30,'Murcia',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (31,'Navarra',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (32,'Ourense',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (33,'Asturias',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (34,'Palencia',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (35,'Palmas, Las',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (36,'Pontevedra',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (37,'Salamanca',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (38,'Santa Cruz de Tenerife',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (39,'Cantabria',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (40,'Segovia',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (41,'Sevilla',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (42,'Soria',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (43,'Tarragona',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (44,'Teruel',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (45,'Toledo',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (46,'Valencia',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (47,'Valladolid',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (48,'Vizcaya',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (49,'Zamora',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (50,'Zaragoza',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (51,'Ceuta',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES (52,'Melilla',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Publication`
--

DROP TABLE IF EXISTS `Publication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Publication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `magazineId` int(11) NOT NULL,
  `magazineDeliveryDate` datetime DEFAULT NULL COMMENT 'Fecha de entrega a la Magazine',
  `magazinePublicationDate` date DEFAULT NULL,
  `ownPublicationDate` date DEFAULT NULL COMMENT 'Fecha de publicacin en Adictos',
  `accepted` tinyint(1) DEFAULT NULL COMMENT 'Indicador de si el Tutorial ha sido aceptado (1) o no (0)',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  `tutorialId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_publication_tutorialId` (`id`),
  KEY `fk_publication_magazineId` (`magazineId`),
  KEY `FK23254A0CF7749AC4` (`magazineId`),
  KEY `FK23254A0C683ECFD8` (`tutorialId`),
  CONSTRAINT `FK23254A0C683ECFD8` FOREIGN KEY (`tutorialId`) REFERENCES `Tutorial` (`id`),
  CONSTRAINT `FK23254A0CF7749AC4` FOREIGN KEY (`magazineId`) REFERENCES `Magazine` (`id`),
  CONSTRAINT `fk_publicacion_tutorialId` FOREIGN KEY (`id`) REFERENCES `Tutorial` (`id`),
  CONSTRAINT `fk_publication_magazineId` FOREIGN KEY (`magazineId`) REFERENCES `Magazine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='publicaciones de tutoriales';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Publication`
--

LOCK TABLES `Publication` WRITE;
/*!40000 ALTER TABLE `Publication` DISABLE KEYS */;
/*!40000 ALTER TABLE `Publication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RequestHoliday`
--

DROP TABLE IF EXISTS `RequestHoliday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RequestHoliday` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `beginDate` datetime NOT NULL,
  `finalDate` datetime NOT NULL,
  `state` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `userId` int(11) NOT NULL,
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `userComment` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `chargeYear` date NOT NULL DEFAULT '2007-01-01',
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_requestHoliday_userId` (`userId`),
  KEY `FK96B96AE92A1FD1F2` (`userId`),
  CONSTRAINT `FK96B96AE92A1FD1F2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_requestHoliday_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RequestHoliday`
--

LOCK TABLES `RequestHoliday` WRITE;
/*!40000 ALTER TABLE `RequestHoliday` DISABLE KEYS */;
/*!40000 ALTER TABLE `RequestHoliday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Roles de la aplicacin';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'Administrador',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Role` VALUES (2,'Supervisor',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Role` VALUES (3,'Usuario',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Role` VALUES (4,'Staff',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Role` VALUES (5,'Cliente',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Setting`
--

DROP TABLE IF EXISTS `Setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `name` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `value` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='User settings';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Setting`
--

LOCK TABLES `Setting` WRITE;
/*!40000 ALTER TABLE `Setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `Setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tutorial`
--

DROP TABLE IF EXISTS `Tutorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tutorial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `maxDeliveryDate` datetime NOT NULL,
  `endDate` datetime DEFAULT NULL COMMENT 'Fecha de finalizacin del Tutorial',
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_tutorial_userId` (`userId`),
  KEY `FKF5F8F7E2A1FD1F2` (`userId`),
  CONSTRAINT `FKF5F8F7E2A1FD1F2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_tutorial_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tutoriales que son enviado a la';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tutorial`
--

LOCK TABLES `Tutorial` WRITE;
/*!40000 ALTER TABLE `Tutorial` DISABLE KEYS */;
/*!40000 ALTER TABLE `Tutorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `roleId` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'User activo',
  `name` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `nif` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `academicQualification` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  `mobile` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  `street` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `postalCode` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provinceId` int(11) DEFAULT NULL,
  `married` tinyint(1) NOT NULL COMMENT 'Casado (1) o no (0)',
  `childrenNumber` tinyint(4) NOT NULL,
  `drivenLicenseType` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `vehicleType` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `licensePlate` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `startDate` date NOT NULL COMMENT 'fecha de alta en la empresa',
  `categoryId` int(11) NOT NULL,
  `socialSecurityNumber` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `bank` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `account` varchar(34) COLLATE utf8_spanish_ci DEFAULT NULL,
  `travelAvailability` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Disponibilidad para viajar',
  `workingInClient` tinyint(1) NOT NULL COMMENT 'Si esta tabajando en el cliente',
  `email` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `genre` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `salaryExtras` decimal(10,2) DEFAULT NULL,
  `documentCategoryId` int(10) unsigned DEFAULT NULL,
  `securityCard` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `healthInsurance` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `notes` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `endTestPeriodDate` date DEFAULT NULL,
  `endContractDate` date DEFAULT NULL,
  `departmentId` int(10) unsigned NOT NULL DEFAULT '1',
  `contractTypeId` int(10) unsigned DEFAULT NULL,
  `contractObservations` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `dayDuration` int(11) NOT NULL DEFAULT '480' COMMENT 'Duración en minutos',
  `agreementId` int(11) NOT NULL DEFAULT '1',
  `updatedById` int(11) DEFAULT NULL,
  `cargo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `organizationId` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `ndx_user_roleId` (`roleId`),
  KEY `ndx_user_provinceId` (`provinceId`),
  KEY `ndx_user_categoryId` (`categoryId`),
  KEY `ndx_user_documentCategoryId` (`documentCategoryId`),
  KEY `ndx_user_departmentId` (`departmentId`),
  KEY `ndx_user_contractTypeId` (`contractTypeId`),
  KEY `ndx_user_agreementId` (`agreementId`),
  KEY `fk_user_organizationId` (`organizationId`),
  KEY `FK285FEB40320CFF` (`agreementId`),
  KEY `FK285FEBBF154FAE` (`documentCategoryId`),
  KEY `FK285FEB2392B783` (`categoryId`),
  KEY `FK285FEBEB25F680` (`departmentId`),
  KEY `FK285FEB101189C2` (`organizationId`),
  KEY `FK285FEB24CA7C88` (`roleId`),
  KEY `FK285FEBEFBC22FC` (`provinceId`),
  KEY `FK285FEB1EDC8FD4` (`contractTypeId`),
  CONSTRAINT `FK285FEB101189C2` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `FK285FEB1EDC8FD4` FOREIGN KEY (`contractTypeId`) REFERENCES `ContractType` (`id`),
  CONSTRAINT `FK285FEB2392B783` FOREIGN KEY (`categoryId`) REFERENCES `UserCategory` (`id`),
  CONSTRAINT `FK285FEB24CA7C88` FOREIGN KEY (`roleId`) REFERENCES `Role` (`id`),
  CONSTRAINT `FK285FEB40320CFF` FOREIGN KEY (`agreementId`) REFERENCES `WorkingAgreement` (`id`),
  CONSTRAINT `FK285FEBBF154FAE` FOREIGN KEY (`documentCategoryId`) REFERENCES `DocumentCategory` (`id`),
  CONSTRAINT `FK285FEBEB25F680` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `FK285FEBEFBC22FC` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`),
  CONSTRAINT `fk_user_agreementId` FOREIGN KEY (`agreementId`) REFERENCES `WorkingAgreement` (`id`),
  CONSTRAINT `fk_user_categoryId` FOREIGN KEY (`categoryId`) REFERENCES `UserCategory` (`id`),
  CONSTRAINT `fk_user_contractTypeId` FOREIGN KEY (`contractTypeId`) REFERENCES `ContractType` (`id`),
  CONSTRAINT `fk_user_departmentId` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_user_documentCategoryId` FOREIGN KEY (`documentCategoryId`) REFERENCES `DocumentCategory` (`id`),
  CONSTRAINT `fk_user_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_user_provinceId` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`),
  CONSTRAINT `fk_user_roleId` FOREIGN KEY (`roleId`) REFERENCES `Role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Users de la aplicacin';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'admin','dd94709528bb1c83d08f3088d4043f4742891f4f',1,1,'Antonio Sánchez Romo','11222333N','1985-02-05','','','666555444','Real, 55','San Fernando','11100',11,0,0,'B','','','2011-01-09',1,'','','','',0,'antonio.sanchez@miempresa.com','MAN',NULL,NULL,45,'','','',NULL,NULL,NULL,1,3,'',NULL,'2012-01-09 22:15:09',480,1,1,'Director',1);
INSERT INTO `User` VALUES (2,'felipe','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',1,1,'Felipe Herrero Guadalupe','22333444F','1980-01-07','F.P.II Química','','666222111','Rosario, 99','Cádiz','11102',11,0,0,'B','','','2010-01-06',3,'','','','',0,'felipe.herrero@miempresa.com','MAN',38000.00,NULL,46,'','','',NULL,NULL,NULL,8,3,'','2012-01-09 22:19:36','2012-01-09 22:19:36',480,1,NULL,'',1);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserCategory`
--

DROP TABLE IF EXISTS `UserCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserCategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Se almacenan las categorias';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserCategory`
--

LOCK TABLES `UserCategory` WRITE;
/*!40000 ALTER TABLE `UserCategory` DISABLE KEYS */;
INSERT INTO `UserCategory` VALUES (1,'Socio',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `UserCategory` VALUES (2,'Becario',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `UserCategory` VALUES (3,'Administrativo',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `UserCategory` VALUES (4,'Gerente',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `UserCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Version`
--

DROP TABLE IF EXISTS `Version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Version` (
  `version` varchar(32) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='version de la base de datos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Version`
--

LOCK TABLES `Version` WRITE;
/*!40000 ALTER TABLE `Version` DISABLE KEYS */;
INSERT INTO `Version` VALUES ('0.9');
/*!40000 ALTER TABLE `Version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WorkingAgreement`
--

DROP TABLE IF EXISTS `WorkingAgreement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WorkingAgreement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `holidays` int(11) NOT NULL DEFAULT '22',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedById` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Convenios laborales';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WorkingAgreement`
--

LOCK TABLES `WorkingAgreement` WRITE;
/*!40000 ALTER TABLE `WorkingAgreement` DISABLE KEYS */;
INSERT INTO `WorkingAgreement` VALUES (1,'Convenio Nuestra Empresa','Este es el convenio de nuestra empresa',22,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `WorkingAgreement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tnt'
--
/*!50003 DROP FUNCTION IF EXISTS `bill_lineatrabajo_sugerida` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `bill_lineatrabajo_sugerida`(bill_Id int) RETURNS int(11)
BEGIN return (select coalesce( (select distinct b.lineaTrabajoId from Bill b where b.id = bill_Id limit 1), (select distinct o.lineaTrabajoId from Albaran a join Project p on p.albaranId = a.id join Offer o on p.offerId = o.id where a.billId = bill_Id limit 1), (select distinct o.lineaTrabajoId from Offer o where o.billId = bill_Id limit 1), 1) as lineaTrabajoId); END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `coste_albaran_sin_iva` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `coste_albaran_sin_iva`(albaran_Id int) RETURNS decimal(19,2)
BEGIN return (select sum(coalesce(p.costeAFacturar, coste_project_sin_iva(p.id), 0)) from Albaran a left join Project p on p.albaranId = a.id where a.id = albaran_Id group by a.id); END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `coste_factura_sin_iva` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `coste_factura_sin_iva`(bill_Id int) RETURNS decimal(19,2)
BEGIN return (SELECT sum(coalesce(bb.units*bb.amount,0))+ sum(coalesce(coste_albaran_sin_iva(a.id),0))+ sum(coalesce(coste_presupuesto_sin_ensayos_ni_projectos_sin_iva(o.id),0)) FROM Bill b left join BillBreakDown bb on b.id=bb.billId left join Albaran a on b.id=a.billId left join Offer o on b.id=o.billId where b.id=bill_Id group by b.id); END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `coste_presupuesto_sin_ensayos_ni_projectos_sin_iva` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `coste_presupuesto_sin_ensayos_ni_projectos_sin_iva`(offer_Id int) RETURNS decimal(19,2)
BEGIN return (select coalesce(sum(ofr.costPerHour*ofr.expectedHours),0)+ coalesce(sum(oc.cost*oc.billable),0) From Offer o left join OfferRole ofr on ofr.offerId = o.id left join OfferCost oc on oc.offerId = o.id where o.id = offer_Id group by o.id); END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `coste_project_sin_costes_ni_tareas_sin_iva` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `coste_project_sin_costes_ni_tareas_sin_iva`(project_Id int) RETURNS decimal(19,2)
BEGIN return (select coalesce(p.costeAFacturar,sum(pe.cost),0) from Project p left join ProjectEnsayo pe on pe.projectId = p.id where p.id = project_Id group by p.id); END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `coste_project_sin_iva` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `coste_project_sin_iva`(project_Id int) RETURNS decimal(19,2)
BEGIN return (select coalesce(sum(pc.cost),0)+ coalesce(sum(pr.costPerHour*pr.expectedHours),0)+ coalesce(sum(pe.cost),0) from Project p left join ProjectCost pc on pc.projectId = p.id left join ProjectRole pr on pr.projectId = p.id left join ProjectEnsayo pe on pe.projectId = p.id where p.id = project_Id group by p.id); END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `estado_de_informe` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `estado_de_informe`(projectId int) RETURNS varchar(30) CHARSET utf8
BEGIN return ( select (case  when (not con_albaran and not con_factura and alguno_sin_realizar ) then 'PENDIENTE'  when (not con_albaran and not con_factura and ninguno_sin_realizar and todos_sin_nota_salida ) then 'PEND_ENV_LABORATORIO'  when (informe.billable and not con_albaran and not con_factura and ninguno_sin_realizar and not todos_sin_nota_salida and not alguno_sin_nota_salida ) then 'PEND_ENV_EMPRESA'  when (not con_albaran and not con_factura and ninguno_sin_realizar and not todos_sin_nota_salida and alguno_sin_nota_salida ) then 'PARCIAL_NS'  when (billable and con_albaran and not con_factura ) then 'EN_ALBARAN'  when (billable and con_factura and pendienteDePedido ) then 'EN_FACTURA_PEND_PEDIDO'  when (billable and con_factura ) then 'FACTURADO'  when (not billable ) then 'NO_FACTURABLE'  else 'DESCONOCIDO'  end) from ( select informe.billable, informe.albaranId is not null as con_albaran, factura.id is not null as con_factura, factura.pendienteDePedido, -1 = ANY (select coalesce(pe.realizadoFecha, -1) from ProjectEnsayo pe where pe.projectId = informe.id) as alguno_sin_realizar, -1 != ALL (select coalesce(pe.realizadoFecha, -1) from ProjectEnsayo pe where pe.projectId = informe.id) as ninguno_sin_realizar, -1 = ALL (select coalesce(pe.notaSalidaId, -1) from ProjectEnsayo pe where pe.projectId = informe.id) as todos_sin_nota_salida, -1 = ANY (select coalesce(pe.notaSalidaId, -1) from ProjectEnsayo pe where pe.projectId = informe.id) as alguno_sin_nota_salida  from Project informe left join Albaran albaran on (informe.albaranId = albaran.id) left join Bill factura on (informe.billId = factura.id or albaran.billId = factura.id) where informe.id = projectId ) informe ); END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `informe_estado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `informe_estado`(project_Id int) RETURNS text CHARSET latin1
BEGIN return (select case  when exists (select * from Project p join Albaran a on (p.albaranId=a.id) join Bill b on (a.billId=b.id) where p.id=project_Id) then 'ALBARAN_FACTURADO'  when exists (select * from Project p join Albaran a on (p.albaranId=a.id) where p.id=project_Id and a.billId is null) then 'EN_ALBARAN'  when not exists (select * from ProjectEnsayo pe where pe.projectId=id and pe.notaSalidaId is null) then 'TOTAL_NS'  when exists (select * from ProjectEnsayo pe where pe.projectId=id and pe.notaSalidaId is not null) then 'PARCIAL_NS'  else 'PENDIENTE'  end collate utf8_spanish_ci ); END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `informe_tiene_familia` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `informe_tiene_familia`(project_Id int) RETURNS tinyint(1)
BEGIN return (select case when exists (select * from Pauta pa where pa.name=p.especificacion and not nulo(pa.name) and not nulo(pa.familia)) then 1 else 0 end from Project p where p.id = project_Id); END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `informe_tiene_pauta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `informe_tiene_pauta`(project_Id int) RETURNS tinyint(1)
BEGIN return (select case when exists (select * from Pauta pa where pa.name=p.especificacion and not nulo(pa.name)) then 1 else 0 end from Project p where p.id = project_Id); END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `nulo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `nulo`(texto text) RETURNS tinyint(1)
BEGIN return (select case when coalesce(texto,'')='' then 1 else 0 end); END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `Department_hijos`
--

/*!50001 DROP TABLE IF EXISTS `Department_hijos`*/;
/*!50001 DROP VIEW IF EXISTS `Department_hijos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `Department_hijos` AS select `d0`.`id` AS `padre`,`d0`.`id` AS `hijo` from `Department` `d0` union select `d0`.`id` AS `padre`,`d1`.`id` AS `hijo` from (`Department` `d0` join `Department` `d1` on((`d1`.`parentId` = `d0`.`id`))) union select `d0`.`id` AS `padre`,`d2`.`id` AS `hijo` from ((`Department` `d0` join `Department` `d1` on((`d1`.`parentId` = `d0`.`id`))) join `Department` `d2` on((`d2`.`parentId` = `d1`.`id`))) union select `d0`.`id` AS `padre`,`d3`.`id` AS `hijo` from (((`Department` `d0` join `Department` `d1` on((`d1`.`parentId` = `d0`.`id`))) join `Department` `d2` on((`d2`.`parentId` = `d1`.`id`))) join `Department` `d3` on((`d3`.`parentId` = `d2`.`id`))) union select `d0`.`id` AS `padre`,`d4`.`id` AS `hijo` from ((((`Department` `d0` join `Department` `d1` on((`d1`.`parentId` = `d0`.`id`))) join `Department` `d2` on((`d2`.`parentId` = `d1`.`id`))) join `Department` `d3` on((`d3`.`parentId` = `d2`.`id`))) join `Department` `d4` on((`d4`.`parentId` = `d3`.`id`))) union select `d0`.`id` AS `padre`,`d5`.`id` AS `hijo` from (((((`Department` `d0` join `Department` `d1` on((`d1`.`parentId` = `d0`.`id`))) join `Department` `d2` on((`d2`.`parentId` = `d1`.`id`))) join `Department` `d3` on((`d3`.`parentId` = `d2`.`id`))) join `Department` `d4` on((`d4`.`parentId` = `d3`.`id`))) join `Department` `d5` on((`d5`.`parentId` = `d4`.`id`))) union select `d0`.`id` AS `padre`,`d6`.`id` AS `hijo` from ((((((`Department` `d0` join `Department` `d1` on((`d1`.`parentId` = `d0`.`id`))) join `Department` `d2` on((`d2`.`parentId` = `d1`.`id`))) join `Department` `d3` on((`d3`.`parentId` = `d2`.`id`))) join `Department` `d4` on((`d4`.`parentId` = `d3`.`id`))) join `Department` `d5` on((`d5`.`parentId` = `d4`.`id`))) join `Department` `d6` on((`d6`.`parentId` = `d5`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Project_Estado`
--

/*!50001 DROP TABLE IF EXISTS `Project_Estado`*/;
/*!50001 DROP VIEW IF EXISTS `Project_Estado`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `Project_Estado` AS (select `Project`.`id` AS `id`,(`estado_de_informe`(`Project`.`id`) collate utf8_spanish_ci) AS `estado` from `Project`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-02-15 22:19:42
