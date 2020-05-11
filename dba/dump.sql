-- MySQL dump 10.13  Distrib 8.0.19, for Linux (x86_64)
--
-- Host: localhost    Database: brew_book
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brew`
--

DROP TABLE IF EXISTS `brew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brew` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `water_notes` varchar(255) DEFAULT NULL,
  `yeast_id` int DEFAULT NULL,
  `pitch_notes` varchar(255) DEFAULT NULL,
  `style_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `brew_user_fk` (`user_id`),
  KEY `brew_style_fk_idx` (`style_id`),
  KEY `brew_yeast_fk_idx` (`yeast_id`),
  CONSTRAINT `brew_style_fk` FOREIGN KEY (`style_id`) REFERENCES `style` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `brew_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `brew_yeast_fk` FOREIGN KEY (`yeast_id`) REFERENCES `yeast` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brew`
--

LOCK TABLES `brew` WRITE;
/*!40000 ALTER TABLE `brew` DISABLE KEYS */;
INSERT INTO `brew` VALUES (18,'Test','','',NULL,'',1,1),(19,'Nate\'s Brew','This is Nate\'s brews. Not yours.','Madison tap water, super hard.',15,'Pitch notes',109,1);
/*!40000 ALTER TABLE `brew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brew_fermentable`
--

DROP TABLE IF EXISTS `brew_fermentable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brew_fermentable` (
  `brew_id` int NOT NULL,
  `fermentable_id` int NOT NULL,
  `amount` decimal(5,2) NOT NULL,
  `unit_of_measure` varchar(3) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_brew_fermentable_1_idx` (`brew_id`),
  KEY `fk_brew_fermentable_2_idx` (`fermentable_id`),
  CONSTRAINT `fk_brew_fermentable_1` FOREIGN KEY (`brew_id`) REFERENCES `brew` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_brew_fermentable_2` FOREIGN KEY (`fermentable_id`) REFERENCES `fermentable` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brew_fermentable`
--

LOCK TABLES `brew_fermentable` WRITE;
/*!40000 ALTER TABLE `brew_fermentable` DISABLE KEYS */;
INSERT INTO `brew_fermentable` VALUES (19,18,5.00,'lbs',7),(19,232,12.00,'oz',8),(19,17,10.00,'lbs',9);
/*!40000 ALTER TABLE `brew_fermentable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brew_hop`
--

DROP TABLE IF EXISTS `brew_hop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brew_hop` (
  `brew_id` int NOT NULL,
  `hop_id` int NOT NULL,
  `amount` decimal(5,2) NOT NULL,
  `method` varchar(10) NOT NULL,
  `time_in_brew` decimal(5,2) NOT NULL,
  `time_unit_of_measure` varchar(5) NOT NULL,
  `amount_unit_of_measure` varchar(5) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_brew_hop_1_idx` (`brew_id`),
  KEY `fk_brew_hop_hop_idx` (`hop_id`),
  CONSTRAINT `fk_brew_hop_brew` FOREIGN KEY (`brew_id`) REFERENCES `brew` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_brew_hop_hop` FOREIGN KEY (`hop_id`) REFERENCES `hop` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brew_hop`
--

LOCK TABLES `brew_hop` WRITE;
/*!40000 ALTER TABLE `brew_hop` DISABLE KEYS */;
INSERT INTO `brew_hop` VALUES (19,6,5.00,'Boil',41.00,'sec','oz',7),(19,3,6.00,'Boil',10.00,'sec','oz',8);
/*!40000 ALTER TABLE `brew_hop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brew_misc`
--

DROP TABLE IF EXISTS `brew_misc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brew_misc` (
  `brew_id` int NOT NULL,
  `misc_id` int NOT NULL,
  `amount` decimal(5,2) NOT NULL,
  `amount_unit_of_measure` varchar(5) NOT NULL,
  `addition_parameter` varchar(15) NOT NULL,
  `time_in_brew` decimal(5,2) NOT NULL,
  `time_unit_of_measure` varchar(5) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_brew_misc_1_idx` (`brew_id`),
  KEY `fk_brew_misc_1_idx1` (`misc_id`),
  CONSTRAINT `fk_brew_misc_1` FOREIGN KEY (`misc_id`) REFERENCES `misc` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_brew_misc_brew` FOREIGN KEY (`brew_id`) REFERENCES `brew` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brew_misc`
--

LOCK TABLES `brew_misc` WRITE;
/*!40000 ALTER TABLE `brew_misc` DISABLE KEYS */;
/*!40000 ALTER TABLE `brew_misc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fermentable`
--

DROP TABLE IF EXISTS `fermentable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fermentable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=348 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fermentable`
--

LOCK TABLES `fermentable` WRITE;
/*!40000 ALTER TABLE `fermentable` DISABLE KEYS */;
INSERT INTO `fermentable` VALUES (1,'Acid Malt'),(2,'Amber Malt'),(3,'Aromatic Malt'),(4,'Barley Hulls'),(5,'Barley, Flaked'),(6,'Barley, Raw'),(7,'Barley, Torrefied'),(8,'Biscuit Malt'),(9,'Black (Patent) Malt'),(10,'Black Barley (Roast Barley)'),(11,'Briess - 2 Row Brewers Malt'),(12,'Briess - 2 Row Carapils Malt'),(13,'Briess - 6 Row Brewers Malt'),(14,'Briess - Aromatic Malt'),(15,'Briess - Ashburne Mild Malt'),(16,'Briess - Black Barley'),(17,'Briess - Black Malt'),(18,'Briess - Black Malted Barley Flour'),(19,'Briess - Blackprinz Malt'),(20,'Briess - Bonlander Munich Malt'),(21,'Briess - Carabrown Malt'),(22,'Briess - Caracrystal Wheat Malt'),(23,'Briess - Carapils Malt'),(24,'Briess - Chocolate Malt'),(25,'Briess - Dark Chocolate Malt'),(26,'Briess - Extra Special Malt'),(27,'Briess - Midnight Wheat Malt'),(28,'Briess - Munich Malt 10L'),(29,'Briess - Munich Malt 20L'),(30,'Briess - Pale Ale Malt'),(31,'Briess - Pilsen Malt'),(32,'Briess - Roasted Barley'),(33,'Briess - Rye Malt'),(34,'Briess - Smoked Malt'),(35,'Briess - Special Roast Malt'),(36,'Briess - Victory Malt'),(37,'Briess - Vienna Malt'),(38,'Briess - Wheat Malt, Red'),(39,'Briess - Wheat Malt, White'),(40,'Briess DME - Bavarian Wheat'),(41,'Briess DME - Golden Light'),(43,'Briess DME - Pilsen Light'),(44,'Briess DME - Sparkling Amber'),(45,'Briess DME - Traditional Dark'),(46,'Briess LME - Golden Light'),(48,'Briess LME - Munich'),(49,'Briess LME - Pilsen Light'),(50,'Briess LME - Sparkling Amber'),(51,'Briess LME - Sweet Brown Rice Syrup'),(52,'Briess LME - Traditional Dark'),(53,'Briess LME - White Sorghum Syrup'),(54,'Brown Malt (British Chocolate)'),(55,'Brown Sugar, Dark'),(56,'Brown Sugar, Light'),(57,'Brumalt'),(58,'Candi Sugar, Amber'),(59,'Candi Sugar, Clear'),(60,'Candi Sugar, Dark'),(61,'Cane (Beet) Sugar'),(62,'Cara-Pils/Dextrine'),(63,'Caraamber'),(64,'Carafa'),(65,'Carafa II'),(66,'Carafa III'),(67,'Carafoam'),(68,'Caramel/Crystal Malt - 10L'),(69,'Caramel/Crystal Malt - 120L'),(70,'Caramel/Crystal Malt - 20L'),(71,'Caramel/Crystal Malt - 30L'),(72,'Caramel/Crystal Malt - 40L'),(73,'Caramel/Crystal Malt - 60L'),(74,'Caramel/Crystal Malt - 80L'),(75,'Caramunich Malt'),(76,'Carared'),(77,'Caravienne Malt'),(78,'Carawheat (GR)'),(79,'Chocolate Malt (UK)'),(80,'Chocolate Malt (US)'),(81,'Coopers LME - Amber'),(82,'Coopers LME - Dark'),(83,'Coopers LME - Light'),(84,'Coopers LME - Wheat'),(85,'Corn Sugar (Dextrose)'),(86,'Corn Syrup'),(87,'Corn, Flaked'),(88,'Dark Liquid Extract'),(89,'Dememera Sugar'),(90,'Dry Extract (DME) - Amber'),(91,'Dry Extract (DME) - Dark'),(92,'Dry Extract (DME) - Extra Light'),(93,'Dry Extract (DME) - Light'),(94,'Dry Extract (DME) - Wheat'),(95,'Grits'),(96,'Honey'),(97,'Honey Malt'),(98,'Invert Sugar'),(99,'Liquid Extract (LME) - Amber'),(100,'Liquid Extract (LME) - Pale'),(101,'Liquid Extract (LME) - Pilsner'),(102,'Liquid Extract (LME) - Wheat'),(103,'Maple Syrup'),(104,'Melanoiden Malt'),(105,'Mild Malt'),(106,'Milk Sugar (Lactose)'),(107,'Molasses'),(108,'Munich Malt'),(109,'Munich Malt - 10L'),(110,'Munich Malt - 20L'),(111,'Muntons DME - Amber'),(112,'Muntons DME - Dark'),(113,'Muntons DME - Extra Dark'),(114,'Muntons DME - Extra Light'),(115,'Muntons DME - Light'),(116,'Muntons DME - Wheat'),(117,'Muntons LME - Amber'),(118,'Muntons LME - Dark'),(119,'Muntons LME - Extra Light'),(120,'Muntons LME - Light'),(121,'Muntons LME - Wheat'),(122,'Oats, Flaked'),(123,'Oats, Malted'),(124,'Pale Malt (2 Row) Bel'),(125,'Pale Malt (2 Row) UK'),(126,'Pale Malt (2 Row) US'),(127,'Pale Malt (6 Row) US'),(128,'Peat Smoked Malt'),(129,'Pilsner (2 Row) Bel'),(130,'Pilsner (2 Row) Ger'),(131,'Pilsner (2 Row) UK'),(132,'Rahr - 2 Row Malt'),(133,'Rahr - 6 Row Malt'),(134,'Rahr - Pale Ale Malt'),(135,'Rahr - Premium Pilsner Malt'),(136,'Rahr - Red Wheat Malt'),(137,'Rahr - White Wheat Malt'),(138,'Rauch Malt (Germany)'),(139,'Rice Extract Syrup'),(140,'Rice Hulls'),(141,'Rice, Flaked'),(142,'Roasted Barley'),(143,'Rye Malt'),(144,'Rye, Flaked'),(145,'Simpsons - Aromatic Malt'),(146,'Simpsons - Black Malt'),(147,'Simpsons - Caramalt'),(148,'Simpsons - Caramalt Light'),(149,'Simpsons - Chocolate Malt'),(150,'Simpsons - Coffee Malt'),(151,'Simpsons - Crystal Dark'),(152,'Simpsons - Crystal Extra Dark'),(153,'Simpsons - Crystal Medium'),(154,'Simpsons - Crystal Rye'),(155,'Simpsons - Golden Naked Oats'),(156,'Simpsons - Golden Promise'),(157,'Simpsons - Maris Otter'),(158,'Simpsons - Peated Malt'),(159,'Simpsons - Roasted Barley'),(160,'Smoked Malt'),(161,'Special B Malt'),(162,'Special Roast'),(163,'Sugar, Table (Sucrose)'),(164,'Toasted Malt'),(165,'Turbinado'),(166,'Victory Malt'),(167,'Vienna Malt'),(168,'Weyermann - Acidulated Malt'),(169,'Weyermann - Bohemian Pilsner Malt'),(170,'Weyermann - Carafa I'),(171,'Weyermann - Carafa II'),(172,'Weyermann - Carafa III'),(173,'Weyermann - Carafoam'),(174,'Weyermann - Carawheat'),(175,'Weyermann - Chocolate Rye'),(176,'Weyermann - Chocolate Wheat'),(177,'Weyermann - Dark Wheat Malt'),(178,'Weyermann - Dehusked Carafa I'),(179,'Weyermann - Dehusked Carafa II'),(180,'Weyermann - Dehusked Carafa III'),(181,'Weyermann - Light Munich Malt'),(182,'Weyermann - Melanoiden Malt'),(183,'Weyermann - Pale Ale Malt'),(184,'Weyermann - Pale Wheat Malt'),(185,'Weyermann - Pilsner Malt'),(186,'Weyermann - Rye Malt'),(187,'Weyermann - Smoked Malt'),(188,'Weyermann - Vienna Malt'),(189,'Wheat Malt, Bel'),(190,'Wheat Malt, Dark'),(191,'Wheat Malt, Ger'),(192,'Wheat, Flaked'),(193,'Wheat, Roasted'),(194,'Wheat, Torrified'),(195,'White Wheat Malt'),(196,'Briess - 2 Row Brewers Malt'),(197,'Caramel/Crystal Malt - 10L'),(198,'Briess - 2 Row Brewers Malt'),(199,'Weyermann - Light Munich Malt'),(200,'Caramel/Crystal Malt - 40L'),(201,'Briess - Victory Malt'),(202,'Briess - Chocolate Malt'),(203,'Simpsons - Maris Otter'),(204,'Caramel/Crystal Malt - 10L'),(205,'Caramel/Crystal Malt - 120L'),(206,'Simpsons - Maris Otter'),(207,'Briess - Munich Malt 10L'),(208,'Caramel/Crystal Malt - 40L'),(209,'Caramel/Crystal Malt - 120L'),(210,'Honey Malt'),(211,'Simpsons - Chocolate Malt'),(212,'Briess - 2 Row Brewers Malt'),(213,'Briess - Victory Malt'),(214,'Briess - Wheat Malt, White'),(215,'Caramel/Crystal Malt - 40L'),(216,'Briess - Munich Malt 10L'),(217,'Simpsons - Maris Otter'),(218,'Simpsons - Chocolate Malt'),(219,'Caramel/Crystal Malt - 40L'),(220,'Briess - Victory Malt'),(221,'Briess - Special Roast Malt'),(222,'Briess - 2 Row Brewers Malt'),(223,'Briess - Munich Malt 10L'),(224,'Caramel/Crystal Malt - 40L'),(225,'Simpsons - Black Malt'),(226,'Simpsons - Chocolate Malt'),(227,'Simpsons - Maris Otter'),(228,'Simpsons - Chocolate Malt'),(229,'Simpsons - Black Malt'),(230,'Oats, Flaked'),(231,'Caramel/Crystal Malt - 80L'),(232,'Briess - Victory Malt'),(233,'Briess - 2 Row Brewers Malt'),(234,'Briess - Munich Malt 10L'),(235,'Caramel/Crystal Malt - 10L'),(236,'Caramel/Crystal Malt - 40L'),(237,'Weyermann - Pilsner Malt'),(238,'Weyermann - Pale Wheat Malt'),(239,'Weyermann - Pilsner Malt'),(240,'Weyermann - Pale Wheat Malt'),(241,'Briess - Munich Malt 10L'),(242,'Caramunich Malt'),(243,'Weyermann - Pilsner Malt'),(244,'Weyermann - Pale Wheat Malt'),(245,'Weyermann - Pilsner Malt'),(246,'Weyermann - Pale Wheat Malt'),(247,'Sugar, Table (Sucrose)'),(248,'Simpsons - Aromatic Malt'),(249,'Briess - 2 Row Carapils Malt'),(250,'Briess - Chocolate Malt'),(251,'Caramel/Crystal Malt - 10L'),(252,'Caramel/Crystal Malt - 80L'),(253,'Special B Malt'),(254,'Briess - 2 Row Brewers Malt'),(255,'Corn Sugar (Dextrose)'),(256,'Rauch Malt (Germany)'),(257,'Weyermann - Pilsner Malt'),(258,'Weyermann - Pale Wheat Malt'),(259,'Briess - Munich Malt 10L'),(260,'Caramunich Malt'),(261,'Simpsons - Black Malt'),(262,'Weyermann - Melanoiden Malt'),(263,'Weyermann - Smoked Malt'),(264,'Briess DME - Golden Light'),(265,'Caramel/Crystal Malt - 10L'),(266,'Briess DME - Golden Light'),(267,'Briess LME - Munich'),(268,'Caramel/Crystal Malt - 40L'),(269,'Briess - Victory Malt'),(270,'Briess - Chocolate Malt'),(271,'Muntons DME - Light'),(272,'Caramel/Crystal Malt - 10L'),(273,'Caramel/Crystal Malt - 120L'),(274,'Muntons DME - Light'),(275,'Briess LME - Munich'),(276,'Caramel/Crystal Malt - 40L'),(277,'Caramel/Crystal Malt - 120L'),(278,'Briess - Chocolate Malt'),(279,'Simpsons - Chocolate Malt'),(280,'Honey Malt'),(281,'Briess DME - Golden Light'),(282,'Briess LME - Munich'),(283,'Briess DME - Bavarian Wheat'),(284,'Briess - Victory Malt'),(285,'Briess - Victory Malt'),(286,'Caramel/Crystal Malt - 40L'),(287,'Simpsons - Chocolate Malt'),(288,'Special Roast'),(289,'Muntons DME - Light'),(290,'Caramel/Crystal Malt - 40L'),(291,'Simpsons - Chocolate Malt'),(292,'Simpsons - Black Malt'),(293,'Briess LME - Munich'),(294,'Briess DME - Golden Light'),(295,'Oats, Flaked'),(296,'Simpsons - Chocolate Malt'),(297,'Briess - Victory Malt'),(298,'Caramel/Crystal Malt - 80L'),(299,'Simpsons - Black Malt'),(300,'Muntons DME - Light'),(301,'Caramel/Crystal Malt - 10L'),(302,'Caramel/Crystal Malt - 40L'),(303,'Briess DME - Golden Light'),(304,'Briess LME - Munich'),(305,'Briess DME - Bavarian Wheat'),(306,'Sugar, Table (Sucrose)'),(307,'Briess DME - Bavarian Wheat'),(308,'Briess LME - Munich'),(309,'Caramunich Malt'),(310,'Briess DME - Pilsen Light'),(311,'Briess DME - Pilsen Light'),(312,'Briess DME - Bavarian Wheat'),(313,'Sugar, Table (Sucrose)'),(314,'Briess DME - Bavarian Wheat'),(315,'Simpsons - Aromatic Malt'),(316,'Briess DME - Pilsen Light'),(317,'Corn Sugar (Dextrose)'),(318,'Caramel/Crystal Malt - 10L'),(319,'Caramel/Crystal Malt - 80L'),(320,'Briess - Chocolate Malt'),(321,'Special B Malt'),(322,'Briess LME - Golden Light'),(323,'Briess - 2 Row Black Malt'),(324,'Briess - 2 Row Caramel Malt 10L'),(325,'Briess - 2 Row Caramel Malt 120L'),(326,'Briess - 2 Row Caramel Malt 30L'),(327,'Briess - 2 Row Caramel Malt 40L'),(328,'Briess - 2 Row Caramel Malt 60L'),(329,'Briess - 2 Row Caramel Malt 80L'),(330,'Briess - 2 Row Chocolate Malt'),(331,'Briess - Barley Flakes'),(332,'Briess - Brown Rice Flakes'),(333,'Briess - Caramel Malt 10L'),(334,'Briess - Caramel Malt 120L'),(335,'Briess - Caramel Malt 20L'),(336,'Briess - Caramel Malt 40L'),(337,'Briess - Caramel Malt 60L'),(338,'Briess - Caramel Malt 80L'),(339,'Briess - Caramel Malt 90L'),(340,'Briess - Caramel Munich Malt 60L'),(341,'Briess - Caramel Vienne Malt 20L'),(342,'Briess - Goldpils Vienna Malt'),(343,'Briess - Oat Flakes'),(344,'Briess - Red Wheat Flakes'),(345,'Briess - Rye Flakes'),(346,'Briess - Torrified Red Wheat'),(347,'Briess - Yellow Corn Flakes');
/*!40000 ALTER TABLE `fermentable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hop`
--

DROP TABLE IF EXISTS `hop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hop`
--

LOCK TABLES `hop` WRITE;
/*!40000 ALTER TABLE `hop` DISABLE KEYS */;
INSERT INTO `hop` VALUES (1,'Agnus'),(2,'Ahtanum'),(3,'Amarillo'),(4,'Apollo'),(5,'Bor'),(6,'Bramling'),(7,'Bravo'),(8,'Brewers Gold'),(9,'Bullion'),(10,'Cascade'),(11,'Centennial'),(12,'Challenger'),(13,'Chelan'),(14,'Chinook'),(15,'Citra'),(16,'Cluster'),(17,'Columbus/Tomahawk/Zeus'),(18,'Crystal'),(19,'El Dorado'),(20,'First Gold'),(21,'Fuggles'),(22,'Galena'),(23,'Glacier'),(24,'Golding'),(25,'Green Bullet'),(26,'Hallertau'),(27,'Harmonie'),(28,'Hersbrucker'),(29,'Kent Goldings'),(30,'Liberty'),(31,'Lublin (Lubelski)'),(32,'Magnum'),(33,'Marynka'),(34,'Millennium'),(35,'Mount Hood'),(36,'Northern Brewer'),(37,'Nugget'),(38,'Palisade'),(39,'Perle'),(40,'Phoenix'),(41,'Premiant'),(42,'Pride of Ringwood'),(43,'Progress'),(44,'Rubin'),(45,'Saaz (Czech Republic)'),(46,'Saaz (USA)'),(47,'Simcoe'),(48,'Sladek'),(49,'Sorachi Ace'),(50,'Spalt'),(51,'Sterling'),(52,'Strisselspalt'),(53,'Styrian Goldings'),(54,'Summit'),(55,'Super Galena'),(56,'Target'),(57,'Tettnang'),(58,'Tillicum'),(59,'Tradition'),(60,'Vanguard'),(61,'Warrior'),(62,'Willamette'),(63,'Willamette'),(64,'Northern Brewer'),(65,'Northern Brewer'),(66,'Northern Brewer'),(67,'Kent Goldings'),(68,'Kent Goldings'),(69,'Kent Goldings'),(70,'Cascade'),(71,'Cascade'),(72,'Centennial'),(73,'Centennial'),(74,'Northern Brewer'),(75,'Chinook'),(76,'Kent Goldings'),(77,'Kent Goldings'),(78,'Fuggles'),(79,'Kent Goldings'),(80,'Kent Goldings'),(81,'Kent Goldings'),(82,'Nugget'),(83,'Centennial'),(84,'Simcoe'),(85,'Amarillo'),(86,'Hallertau'),(87,'Hallertau'),(88,'Hallertau'),(89,'Hallertau'),(90,'Hallertau'),(91,'Nugget'),(92,'Chinook'),(93,'Centennial'),(94,'Amarillo'),(95,'Hallertau'),(96,'Hallertau'),(97,'Willamette'),(98,'Northern Brewer'),(99,'Northern Brewer'),(100,'Northern Brewer'),(101,'Kent Goldings'),(102,'Kent Goldings'),(103,'Kent Goldings'),(104,'Chinook'),(105,'Cascade'),(106,'Centennial'),(107,'Cascade'),(108,'Centennial'),(109,'Kent Goldings'),(110,'Kent Goldings'),(111,'Kent Goldings'),(112,'Kent Goldings'),(113,'Fuggles'),(114,'Kent Goldings'),(115,'Nugget'),(116,'Centennial'),(117,'Simcoe'),(118,'Amarillo'),(119,'Hallertau'),(120,'Hallertau'),(121,'Hallertau'),(122,'Hallertau'),(123,'Hallertau'),(124,'Nugget'),(125,'Chinook'),(126,'Centennial'),(127,'Amarillo');
/*!40000 ALTER TABLE `hop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `misc`
--

DROP TABLE IF EXISTS `misc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `misc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `misc`
--

LOCK TABLES `misc` WRITE;
/*!40000 ALTER TABLE `misc` DISABLE KEYS */;
INSERT INTO `misc` VALUES (1,'Apricot'),(2,'Bitter Orange Peel'),(3,'Blueberry'),(4,'Boysenberry'),(5,'Burton Salts'),(6,'Calcium Carbonate'),(7,'Calcium Chloride'),(8,'Campden Tablet'),(9,'Cherry'),(10,'Cranberry'),(11,'Epsom Salt'),(12,'Gelatin'),(13,'Gypsum'),(14,'Hazelnut'),(15,'Heather Tips'),(16,'Instant Water - American'),(17,'Instant Water - Burton on Trent'),(18,'Instant Water - Dortmund'),(19,'Instant Water - Edinburgh'),(20,'Instant Water - London'),(21,'Instant Water - Munich'),(22,'Irish Moss'),(23,'IsoHop'),(24,'Kosher Salt'),(25,'Lactic Acid'),(26,'Licorice Root'),(27,'Oak Chips'),(28,'Oak Cubes'),(29,'Paradise Seed'),(30,'Peach'),(31,'Phosphoric Acid'),(32,'Polyclar'),(33,'Raspberry'),(34,'Sparkolloid'),(35,'Super Moss'),(36,'Sweet Orange Peel'),(37,'Vanilla Beans'),(38,'Whirlfloc'),(39,'Whole Coriander'),(40,'Yeast Nutrient'),(41,'pH 5.2 Stabilizer'),(42,'Aji Amarillo'),(43,'Ajowan'),(44,'Aleppo Chiles'),(45,'Allspice'),(46,'Ancho Chiles'),(47,'Basil'),(48,'Bay Leaves'),(49,'Birch Bark'),(50,'Peppercorns, Black'),(51,'Caraway Seeds'),(52,'Cardamom, Black'),(53,'Cardamom, Green'),(54,'Cascabel Chiles'),(55,'Cayenne Pepper'),(56,'Chicory Root'),(57,'Chipotle Chiles'),(58,'Cinnamon'),(59,'Citric Acid'),(60,'Cloves'),(61,'Cocoa Nibs'),(62,'Coriander Seeds'),(63,'Cubeb Berries'),(64,'Cumin'),(65,'Fennel Seeds'),(66,'Galangal'),(67,'Ginger, Candied'),(68,'Ginger Root'),(69,'Grains of Paradise'),(70,'Peppercorns, Green'),(71,'Guajillo Chiles'),(72,'Habanero Chiles'),(73,'Juniper Berries'),(74,'Lavender'),(75,'Lemon Peel'),(76,'Lime Leaves'),(77,'Lime Peel'),(78,'Mace'),(79,'Marash Chiles'),(80,'Mulato Chiles'),(81,'Nutmeg'),(82,'Pasilla Chiles'),(83,'Peppercorns, Pink'),(84,'Saffron'),(85,'Sanaam Chiles'),(86,'Star Anise'),(87,'Sumac'),(88,'Peppercorns, Szechuan'),(89,'Tien Tsin Chiles'),(90,'Tonka Beans'),(91,'Peppercorns, White');
/*!40000 ALTER TABLE `misc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_idx` (`user_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin','test',1),(2,'registered_user','test',1),(3,'registered_user','testing',5);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `style`
--

DROP TABLE IF EXISTS `style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `style` (
  `id` int NOT NULL AUTO_INCREMENT,
  `style_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `style`
--

LOCK TABLES `style` WRITE;
/*!40000 ALTER TABLE `style` DISABLE KEYS */;
INSERT INTO `style` VALUES (1,'Farmhouse Ale'),(2,'American Barleywine'),(3,'American Brown Ale'),(4,'American IPA'),(5,'American Pale Ale'),(6,'American Stout'),(7,'American Wheat or Rye Beer'),(8,'Applewine'),(9,'Baltic Porter'),(10,'Belgian Blond Ale'),(11,'Belgian Dark Strong Ale'),(12,'Belgian Dubbel'),(13,'Belgian Golden Strong Ale'),(14,'Belgian Pale Ale'),(15,'Belgian Specialty Ale'),(16,'Belgian Tripel'),(17,'Berliner Weisse'),(18,'Bière de Garde'),(19,'Blonde Ale'),(20,'Bohemian Pilsener'),(21,'Braggot'),(22,'Brown Porter'),(23,'California Common Beer'),(24,'Christmas/Winter Specialty Spiced Beer'),(25,'Classic American Pilsner'),(26,'Classic Rauchbier'),(27,'Common Cider'),(28,'Common Perry'),(29,'Cream Ale'),(30,'Cyser'),(31,'Dark American Lager'),(32,'Doppelbock'),(33,'Dortmunder Export'),(34,'Dry Mead'),(35,'Dry Stout'),(36,'Dunkelweizen'),(37,'Düsseldorf Altbier'),(38,'Eisbock'),(39,'English Barleywine'),(40,'English Cider '),(41,'English IPA'),(42,'Extra Special/Strong Bitter (English Pale Ale)'),(43,'Flanders Brown Ale/Oud Bruin'),(44,'Flanders Red Ale'),(45,'Foreign Extra Stout'),(46,'French Cider'),(47,'Fruit Beer'),(48,'Fruit Cider'),(49,'Fruit Lambic'),(50,'German Pilsner (Pils)'),(51,'Gueuze'),(52,'Imperial IPA'),(53,'Irish Red Ale'),(54,'Kölsch'),(55,'Lite American Lager'),(56,'Maibock/Helles Bock'),(57,'Metheglin'),(58,'Mild'),(59,'Munich Dunkel'),(60,'Munich Helles'),(61,'New England Cider'),(62,'Northern English Brown Ale'),(63,'Northern German Altbier'),(64,'Oatmeal Stout'),(65,'Oktoberfest/Märzen'),(66,'Old Ale'),(67,'Open Category Mead'),(68,'Other Fruit Melomel'),(69,'Other Smoked Beer'),(70,'Other Specialty Cider/Perry'),(71,'Premium American Lager'),(72,'Pyment'),(73,'Robust Porter'),(74,'Roggenbier (German Rye Beer)'),(75,'Russian Imperial Stout'),(76,'Saison'),(77,'Schwarzbier (Black Beer)'),(78,'Scottish Export 80/-'),(79,'Scottish Heavy 70/-'),(80,'Scottish Light 60/-'),(81,'Semi-sweet Mead'),(82,'Southern English Brown'),(83,'Special/Best/Premium Bitter'),(84,'Specialty Beer'),(85,'Spice, Herb, or Vegetable Beer'),(86,'Standard American Lager'),(87,'Standard/Ordinary Bitter'),(88,'Straight (Unblended) Lambic'),(89,'Strong Scotch Ale'),(90,'Sweet Mead'),(91,'Sweet Stout'),(92,'Traditional Bock'),(93,'Traditional Perry'),(94,'Vienna Lager'),(95,'Weizen/Weissbier'),(96,'Weizenbock'),(97,'Witbier'),(98,'Wood-Aged Beer'),(99,'Blonde Ale'),(100,'California Common Beer'),(101,'Extra Special/Strong Bitter (English Pale Ale)'),(102,'Scottish Heavy 70/-'),(103,'American Pale Ale'),(104,'Northern English Brown Ale'),(105,'Robust Porter'),(106,'Oatmeal Stout'),(107,'American IPA'),(108,'Weizen/Weissbier'),(109,'Saison'),(110,'Berliner Weisse'),(111,'Belgian Blond Ale'),(112,'American Barleywine'),(113,'Classic Rauchbier'),(114,'Blonde Ale'),(115,'California Common Beer'),(116,'Extra Special/Strong Bitter (English Pale Ale)'),(117,'Scottish Heavy 70/-'),(118,'American Pale Ale'),(119,'Northern English Brown Ale'),(120,'Robust Porter'),(121,'Oatmeal Stout'),(122,'American IPA'),(123,'Weizen/Weissbier'),(124,'Saison'),(125,'Berliner Weisse'),(126,'Belgian Blond Ale'),(127,'American Barleywine');
/*!40000 ALTER TABLE `style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test','test','test@test.com'),(2,'tester','tester','tester@test.com'),(5,'testing','password','testing@test.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yeast`
--

DROP TABLE IF EXISTS `yeast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yeast` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yeast`
--

LOCK TABLES `yeast` WRITE;
/*!40000 ALTER TABLE `yeast` DISABLE KEYS */;
INSERT INTO `yeast` VALUES (1,'Wyeast','American Wheat'),(2,'Danstar','Danstar - Windsor Ale'),(3,'Safale','Safale S-04'),(4,'Safale','Safale S-05'),(5,'White Labs','WLP001 - California Ale Yeast'),(6,'White Labs','WLP002 - English Ale Yeast'),(7,'White Labs','WLP004 - Irish Ale Yeast'),(8,'White Labs','WLP005 - British Ale Yeast'),(9,'White Labs','WLP006 - Bedford British'),(10,'White Labs','WLP007 - Dry English Ale Yeast'),(11,'White Labs','WLP008 - East Coast Ale Yeast'),(12,'White Labs','WLP009 - Australian Ale Yeast'),(13,'White Labs','WLP011 - European Ale Yeast'),(14,'White Labs','WLP013 - London Ale Yeast'),(15,'White Labs','WLP022 - Essex Ale Yeast'),(16,'White Labs','WLP023 - Burton Ale Yeast'),(17,'White Labs','WLP028 - Edinburgh Scottish Ale Yeast'),(18,'White Labs','WLP029 - German Ale/Kölsch Yeast'),(19,'White Labs','WLP036 - Dusseldorf Alt Yeast'),(20,'White Labs','WLP037 - Yorkshire Square Ale Yeast'),(21,'White Labs','WLP038 - Manchester Ale Yeast'),(22,'White Labs','WLP039 - Nottingham Ale Yeast'),(23,'White Labs','WLP041 - Pacific Ale Yeast'),(24,'White Labs','WLP051 - California Ale V Yeast'),(25,'White Labs','WLP060 - American Ale Yeast Blend'),(26,'White Labs','WLP080 - Cream Ale Yeast Blend'),(27,'White Labs','WLP099 - Super High Gravity Ale Yeast'),(28,'White Labs','WLP300 - Hefeweizen Ale Yeast'),(29,'White Labs','WLP320 - American Hefeweizen Ale Yeast'),(30,'White Labs','WLP351 - Bavarian Weizen Yeast'),(31,'White Labs','WLP380 - Hefeweizen IV Ale Yeast '),(32,'White Labs','WLP400 - Belgian Wit Ale Yeast'),(33,'White Labs','WLP410 - Belgian Wit II Ale Yeast'),(34,'White Labs','WLP500 - Trappist Ale Yeast'),(35,'White Labs','WLP510 - Belgian Bastogne Ale Yeast'),(36,'White Labs','WLP515 - Antwerp Ale Yeast'),(37,'White Labs','WLP530 - Abbey Ale Yeast'),(38,'White Labs','WLP540 - Abbey IV Ale Yeast'),(39,'White Labs','WLP545 - Belgian Strong Ale Yeast'),(40,'White Labs','WLP550 - Belgian Ale Yeast'),(41,'White Labs','WLP565 - Belgian Saison I Yeast'),(42,'White Labs','WLP566 - Belgian Saison II Yeast'),(43,'White Labs','WLP568 - Belgian Style Saison Ale Yeast Blend'),(44,'White Labs','WLP570 - Belgian Golden Ale Yeast'),(45,'White Labs','WLP575 - Belgian Style Ale Yeast Blend'),(46,'White Labs','WLP700 - Flor Sherry Yeast'),(47,'White Labs','WLP705 - Sake Yeast'),(48,'White Labs','WLP715 - Champagne Yeast'),(49,'White Labs','WLP718 - Avize Wine Yeast'),(50,'White Labs','WLP720 - Sweet Mead/Wine Yeast'),(51,'White Labs','WLP727 - Steinberg-Geisenheim Wine Yeast'),(52,'White Labs','WLP730 - Chardonnay White Wine Yeast'),(53,'White Labs','WLP735 - French White Wine Yeast'),(54,'White Labs','WLP740 - Merlot Red Wine Yeast'),(55,'White Labs','WLP749 - Assmanshausen Wine Yeast'),(56,'White Labs','WLP750 - French Red Wine Yeast'),(57,'White Labs','WLP760 - Cabernet Red Wine Yeast'),(58,'White Labs','WLP770 - Suremain Burgundy Wine Yeast'),(59,'White Labs','WLP775 - English Cider Yeast'),(60,'White Labs','WLP800 - Pilsner Lager Yeast'),(61,'White Labs','WLP802 - Czech Budejovice Lager Yeast'),(62,'White Labs','WLP810 - San Francisco Lager Yeast'),(63,'White Labs','WLP815 - Belgian Lager Yeast'),(64,'White Labs','WLP820 - Oktoberfest/Märzen Lager Yeast'),(65,'White Labs','WLP830 - German Lager Yeast'),(66,'White Labs','WLP833 - German Bock Lager Yeast'),(67,'White Labs','WLP838 - Southern German Lager Yeast'),(68,'White Labs','WLP840 - American Lager Yeast'),(69,'White Labs','WLP862 - Cry Havoc '),(70,'White Labs','WLP885 - Zurich Lager Yeast'),(71,'White Labs','WLP920 - Old Bavarian Lager Yeast'),(72,'White Labs','WLP940 - Mexican Lager Yeast'),(73,'Wyeast Labs','Wyeast - American Ale'),(74,'Wyeast Labs','Wyeast - American Ale II'),(75,'Wyeast Labs','Wyeast - American Lager'),(76,'Wyeast Labs','Wyeast - American Wheat'),(77,'Wyeast Labs','Wyeast - Bavarian Lager'),(78,'Wyeast Labs','Wyeast - Bavarian Wheat'),(79,'Wyeast Labs','Wyeast - Bavarian Wheat Blend'),(80,'Wyeast Labs','Wyeast - Belgian Abbey II'),(81,'Wyeast Labs','Wyeast - Belgian Ale'),(82,'Wyeast Labs','Wyeast - Belgian Ardennes'),(83,'Wyeast Labs','Wyeast - Belgian Lambic Blend'),(84,'Wyeast Labs','Wyeast - Belgian Saison'),(85,'Wyeast Labs','Wyeast - Belgian Strong Ale'),(86,'Wyeast Labs','Wyeast - Belgian Wheat'),(87,'Wyeast Labs','Wyeast - Belgian Witbier'),(88,'Wyeast Labs','Wyeast - Bohemian Lager'),(89,'Wyeast Labs','Wyeast - Brettanomyces bruxellensis'),(90,'Wyeast Labs','Wyeast - Brettanomyces lambicus'),(91,'Wyeast Labs','Wyeast - British Ale'),(92,'Wyeast Labs','Wyeast - British Ale II'),(93,'','Wyeast - Budvar'),(94,'Wyeast Labs','Wyeast - California Lager'),(95,'Wyeast Labs','Wyeast - Czech Pils'),(96,'Wyeast Labs','Wyeast - Danish Lager'),(97,'Wyeast Labs','Wyeast - European Ale'),(98,'Wyeast Labs','Wyeast - Forbidden Fruit'),(99,'Wyeast Labs','Wyeast - German Ale'),(100,'Wyeast Labs','Wyeast - German Wheat'),(101,'Wyeast Labs','Wyeast - Irish Ale'),(102,'Wyeast Labs','Wyeast - Kolsch'),(103,'Wyeast Labs','Wyeast - Lactobacillus'),(104,'Wyeast Labs','Wyeast - London Ale'),(105,'Wyeast Labs','Wyeast - London Ale III'),(106,'Wyeast Labs','Wyeast - London ESB Ale'),(107,'Wyeast Labs','Wyeast - Northwest Ale'),(108,'Wyeast Labs','Wyeast - Octoberfest Lager Blend'),(109,'Wyeast Labs','Wyeast - Pediococcus'),(110,'Wyeast Labs','Wyeast - Pilsen Lager'),(111,'Wyeast Labs','Wyeast - Ringwood Ale'),(112,'Wyeast Labs','Wyeast - Scottish Ale'),(113,'Wyeast Labs','Wyeast - Thames Valley Ale'),(114,'Wyeast Labs','Wyeast - Trappist High Gravity'),(115,'Wyeast Labs','Wyeast - Urquell'),(116,'Wyeast Labs','Wyeast - Weihenstephan Weizen'),(117,'Wyeast Labs','Wyeast - Whitbread Ale'),(118,'White Labs','WLP001 - California Ale Yeast'),(119,'White Labs','WLP810 - San Francisco Lager Yeast'),(120,'White Labs','WLP002 - English Ale Yeast'),(121,'White Labs','WLP028 - Edinburgh Scottish Ale Yeast'),(122,'White Labs','WLP001 - California Ale Yeast'),(123,'White Labs','WLP013 - London Ale Yeast'),(124,'White Labs','WLP001 - California Ale Yeast'),(125,'White Labs','WLP002 - English Ale Yeast'),(126,'White Labs','WLP001 - California Ale Yeast'),(127,'White Labs','WLP090 - San Diego Super Yeast'),(128,'White Labs','WLP300 - Hefeweizen Ale Yeast'),(129,'White Labs','WLP565 - Belgian Saison I Yeast'),(130,'White Labs','WLP011 - European Ale Yeast'),(131,'White Labs','WLP630 - Berliner Weisse Blend'),(132,'White Labs','WLP630 - Berliner Weisse Blend'),(133,'White Labs','WLP645 - Brettanomyces clausenii'),(134,'White Labs','WLP650 - Brettanomyces bruxellensis'),(135,'White Labs','WLP653 - Brettanomyces lambicus'),(136,'White Labs','WLP655 - Belgian Sour Mix 1'),(137,'White Labs','WLP670 - American Farmhouse Blend'),(138,'White Labs','WLP675 - Malolactic Bacteria'),(139,'White Labs','WLP677 - Lactobacillus Bacteria'),(140,'White Labs','WLP500 - Trappist Ale Yeast'),(141,'White Labs','WLP001 - California Ale Yeast'),(142,'White Labs','WLP830 - German Lager Yeast'),(143,'White Labs','WLP001 - California Ale Yeast'),(144,'White Labs','WLP810 - San Francisco Lager Yeast'),(145,'White Labs','WLP002 - English Ale Yeast'),(146,'White Labs','WLP028 - Edinburgh Scottish Ale Yeast'),(147,'White Labs','WLP001 - California Ale Yeast'),(148,'White Labs','WLP013 - London Ale Yeast'),(149,'White Labs','WLP001 - California Ale Yeast'),(150,'White Labs','WLP002 - English Ale Yeast'),(151,'White Labs','WLP001 - California Ale Yeast'),(152,'White Labs','WLP300 - Hefeweizen Ale Yeast'),(153,'White Labs','WLP565 - Belgian Saison I Yeast'),(154,'White Labs','WLP630 - Berliner Weisse Blend'),(155,'White Labs','WLP500 - Trappist Ale Yeast'),(156,'White Labs','WLP001 - California Ale Yeast');
/*!40000 ALTER TABLE `yeast` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-11 17:22:30
