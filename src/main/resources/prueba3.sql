-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: apiong
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Dumping data for table `administrative`
--

LOCK TABLES `administrative` WRITE;
/*!40000 ALTER TABLE `administrative` DISABLE KEYS */;
INSERT INTO `administrative` VALUES (13,1,4),(14,1,4),(15,2,5);
/*!40000 ALTER TABLE `administrative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `aid_sanitary_document`
--

LOCK TABLES `aid_sanitary_document` WRITE;
/*!40000 ALTER TABLE `aid_sanitary_document` DISABLE KEYS */;
INSERT INTO `aid_sanitary_document` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `aid_sanitary_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `annual_fee`
--

LOCK TABLES `annual_fee` WRITE;
/*!40000 ALTER TABLE `annual_fee` DISABLE KEYS */;
INSERT INTO `annual_fee` VALUES (1,10,'Minima'),(2,20,'Media'),(3,30,'Maxima');
/*!40000 ALTER TABLE `annual_fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'Santander','Bucaramanga'),(2,'Santander','Floridablanca'),(3,'Cundinamarca','Bogota');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `directors`
--

LOCK TABLES `directors` WRITE;
/*!40000 ALTER TABLE `directors` DISABLE KEYS */;
INSERT INTO `directors` VALUES (12,1),(11,2);
/*!40000 ALTER TABLE `directors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `headquarter`
--

LOCK TABLES `headquarter` WRITE;
/*!40000 ALTER TABLE `headquarter` DISABLE KEYS */;
INSERT INTO `headquarter` VALUES (1,'Cabecera','HQ-1-1','SedeBucaros',1),(2,'FloridaCentro','HQ-2-2','SedeFlorida',2);
/*!40000 ALTER TABLE `headquarter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `humanitarian_aid`
--

LOCK TABLES `humanitarian_aid` WRITE;
/*!40000 ALTER TABLE `humanitarian_aid` DISABLE KEYS */;
INSERT INTO `humanitarian_aid` VALUES (1,1,1,1,1),(2,1,1,1,3),(3,1,1,1,5);
/*!40000 ALTER TABLE `humanitarian_aid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'Toneladas de harina','ALIMENTO',2),(2,'Toneladas de harina','ALIMENTO',2),(3,'Toneladas de harina','ALIMENTO',2),(4,'Toneladas de harina','ALIMENTO',2),(5,'Toneladas de harina','ALIMENTO',2);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `material_aid`
--

LOCK TABLES `material_aid` WRITE;
/*!40000 ALTER TABLE `material_aid` DISABLE KEYS */;
INSERT INTO `material_aid` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5);
/*!40000 ALTER TABLE `material_aid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `material_aid_material`
--

LOCK TABLES `material_aid_material` WRITE;
/*!40000 ALTER TABLE `material_aid_material` DISABLE KEYS */;
INSERT INTO `material_aid_material` VALUES (1,1),(2,2),(3,3),(4,4),(5,5);
/*!40000 ALTER TABLE `material_aid_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `partners`
--

LOCK TABLES `partners` WRITE;
/*!40000 ALTER TABLE `partners` DISABLE KEYS */;
INSERT INTO `partners` VALUES (321123,'2024-04-10',4,2,1),(123321,'2024-04-10',6,3,2);
/*!40000 ALTER TABLE `partners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,1111555888,'CEDULA','camilo@example.com','M','Camilo','Hernandez'),(2,1234567891,'PASAPORTE','fernanda@example.com','F','Fernanda','Arias'),(3,1987654321,'CEDULA_EXTRANJERIA','andrea@example.com','F','Andrea','Gomez'),(4,1111222333,'CEDULA','karen@example.com','F','Karen','Aguilar'),(6,1234567892,'CEDULA','andres@example.com','M','Andres','Martinez'),(11,1234567894,'CEDULA','gonzales@example.com','M','Camilo','Gonzales'),(12,1234567893,'CEDULA','marce@example.com','F','Marcela','Perez'),(13,1234567895,'CEDULA','john@example.com','M','John','Doe'),(14,1234567896,'CEDULA','alice@example.com','F','Alice','Smith'),(15,1234567897,'CEDULA','bob@example.com','M','Bob','Johnson');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `professions`
--

LOCK TABLES `professions` WRITE;
/*!40000 ALTER TABLE `professions` DISABLE KEYS */;
INSERT INTO `professions` VALUES (1,'PR-1','Doctor'),(2,'PR-2','Enfermero'),(3,'PR-3','Medico cirujano'),(4,'PR-4','Contable'),(5,'PR-5','Tesorero');
/*!40000 ALTER TABLE `professions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN',1),(2,'ROLE_ADMIN',2),(3,'ROLE_ASSISTANT',3),(8,'ROLE_DIRECTOR',8),(9,'ROLE_DIRECTOR',9);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sanitaries`
--

LOCK TABLES `sanitaries` WRITE;
/*!40000 ALTER TABLE `sanitaries` DISABLE KEYS */;
INSERT INTO `sanitaries` VALUES (_binary '\0',3,1,1,1),(_binary '',0,2,2,3),(_binary '',0,3,1,2);
/*!40000 ALTER TABLE `sanitaries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sanitary_sent`
--

LOCK TABLES `sanitary_sent` WRITE;
/*!40000 ALTER TABLE `sanitary_sent` DISABLE KEYS */;
INSERT INTO `sanitary_sent` VALUES (1,1111555888),(2,1111555888),(3,1111555888);
/*!40000 ALTER TABLE `sanitary_sent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shelter`
--

LOCK TABLES `shelter` WRITE;
/*!40000 ALTER TABLE `shelter` DISABLE KEYS */;
INSERT INTO `shelter` VALUES (1,'Norte','SH-1-1','RefugioBucaros1',1),(2,'Kennedy','SH-3-2','RefugioBogota1',3),(3,'Champinero','SH-3-3','RefugioBogota2',3);
/*!40000 ALTER TABLE `shelter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
INSERT INTO `shipment` VALUES (1,'SHP-1','2024-04-20 09:35:48.286000',1),(2,'SHP-2','2024-04-20 09:35:48.286000',1),(3,'SHP-3','2024-04-20 09:35:48.286000',1),(4,'SHP-4','2024-04-20 09:35:48.286000',1),(5,'SHP-5','2024-04-20 09:35:48.286000',1);
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@example.com','passAdmin'),(2,'admin5@example.com','passAdmin5'),(3,'aux1@example.com','passAux1'),(8,'gonzales@example.com','passCamilo'),(9,'marce@example.com','passMarce');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-10  5:52:17
