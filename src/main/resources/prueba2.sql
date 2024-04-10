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

/*!40000 ALTER TABLE `administrative` DISABLE KEYS */;
INSERT INTO `administrative` (`id`, `id_headquarter`, `id_profession`) VALUES (13,1,4),(14,1,4),(15,2,5);
/*!40000 ALTER TABLE `administrative` ENABLE KEYS */;

--
-- Dumping data for table `aid_sanitary_document`
--

/*!40000 ALTER TABLE `aid_sanitary_document` DISABLE KEYS */;
/*!40000 ALTER TABLE `aid_sanitary_document` ENABLE KEYS */;

--
-- Dumping data for table `annual_fee`
--

/*!40000 ALTER TABLE `annual_fee` DISABLE KEYS */;
INSERT INTO `annual_fee` (`id`, `amount`, `name`) VALUES (1,10,'Minima'),(2,20,'Media'),(3,30,'Maxima');
/*!40000 ALTER TABLE `annual_fee` ENABLE KEYS */;

--
-- Dumping data for table `cities`
--

/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` (`id`, `department`, `name`) VALUES (1,'Santander','Bucaramanga'),(2,'Santander','Floridablanca'),(3,'Cundinamarca','Bogota');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;

--
-- Dumping data for table `directors`
--

/*!40000 ALTER TABLE `directors` DISABLE KEYS */;
INSERT INTO `directors` (`id`, `id_headquarter`) VALUES (12,1),(11,2);
/*!40000 ALTER TABLE `directors` ENABLE KEYS */;

--
-- Dumping data for table `headquarter`
--

/*!40000 ALTER TABLE `headquarter` DISABLE KEYS */;
INSERT INTO `headquarter` (`id`, `address`, `code_hq`, `name`, `id_city`) VALUES (1,'Cabecera','HQ-1-1','SedeBucaros',1),(2,'FloridaCentro','HQ-2-2','SedeFlorida',2);
/*!40000 ALTER TABLE `headquarter` ENABLE KEYS */;

--
-- Dumping data for table `humanitarian_aid`
--

/*!40000 ALTER TABLE `humanitarian_aid` DISABLE KEYS */;
/*!40000 ALTER TABLE `humanitarian_aid` ENABLE KEYS */;

--
-- Dumping data for table `material`
--

/*!40000 ALTER TABLE `material` DISABLE KEYS */;
/*!40000 ALTER TABLE `material` ENABLE KEYS */;

--
-- Dumping data for table `material_aid`
--

/*!40000 ALTER TABLE `material_aid` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_aid` ENABLE KEYS */;

--
-- Dumping data for table `partners`
--

/*!40000 ALTER TABLE `partners` DISABLE KEYS */;
INSERT INTO `partners` (`account`, `payment_date`, `id`, `id_fee`, `id_headquarter`) VALUES (321123,'2024-04-10',4,2,1),(123321,'2024-04-10',6,3,2);
/*!40000 ALTER TABLE `partners` ENABLE KEYS */;

--
-- Dumping data for table `person`
--

/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id`, `document`, `document_type`, `email`, `gender`, `name`, `surname`) VALUES (1,1111555888,'CEDULA','camilo@example.com','M','Camilo','Hernandez'),(2,1234567891,'PASAPORTE','fernanda@example.com','F','Fernanda','Arias'),(3,1987654321,'CEDULA_EXTRANJERIA','andrea@example.com','F','Andrea','Gomez'),(4,1111222333,'CEDULA','karen@example.com','F','Karen','Aguilar'),(6,1234567892,'CEDULA','andres@example.com','M','Andres','Martinez'),(11,1234567894,'CEDULA','gonzales@example.com','M','Camilo','Gonzales'),(12,1234567893,'CEDULA','marce@example.com','F','Marcela','Perez'),(13,1234567895,'CEDULA','john@example.com','M','John','Doe'),(14,1234567896,'CEDULA','alice@example.com','F','Alice','Smith'),(15,1234567897,'CEDULA','bob@example.com','M','Bob','Johnson');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;

--
-- Dumping data for table `professions`
--

/*!40000 ALTER TABLE `professions` DISABLE KEYS */;
INSERT INTO `professions` (`id`, `code_pr`, `name`) VALUES (1,'PR-1','Doctor'),(2,'PR-2','Enfermero'),(3,'PR-3','Medico cirujano'),(4,'PR-4','Contable'),(5,'PR-5','Tesorero');
/*!40000 ALTER TABLE `professions` ENABLE KEYS */;

--
-- Dumping data for table `roles`
--

/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `role_name`, `id_user`) VALUES (1,'ROLE_ADMIN',1),(2,'ROLE_ADMIN',2),(3,'ROLE_ASSISTANT',3),(8,'ROLE_DIRECTOR',8),(9,'ROLE_DIRECTOR',9);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

--
-- Dumping data for table `sanitaries`
--

/*!40000 ALTER TABLE `sanitaries` DISABLE KEYS */;
INSERT INTO `sanitaries` (`is_available`, `participation`, `id`, `id_headquarter`, `id_profession`) VALUES (_binary '',0,1,1,1),(_binary '',0,2,2,3),(_binary '',0,3,1,2);
/*!40000 ALTER TABLE `sanitaries` ENABLE KEYS */;

--
-- Dumping data for table `sanitary_sent`
--

/*!40000 ALTER TABLE `sanitary_sent` DISABLE KEYS */;
/*!40000 ALTER TABLE `sanitary_sent` ENABLE KEYS */;

--
-- Dumping data for table `shelter`
--

/*!40000 ALTER TABLE `shelter` DISABLE KEYS */;
INSERT INTO `shelter` (`id`, `address`, `code_sh`, `name`, `id_city`) VALUES (1,'Norte','SH-1-1','RefugioBucaros1',1),(2,'Kennedy','SH-3-2','RefugioBogota1',3),(3,'Champinero','SH-3-3','RefugioBogota2',3);
/*!40000 ALTER TABLE `shelter` ENABLE KEYS */;

--
-- Dumping data for table `shipment`
--

/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `email`, `pwd`) VALUES (1,'admin@example.com','passAdmin'),(2,'admin5@example.com','passAdmin5'),(3,'aux1@example.com','passAux1'),(8,'gonzales@example.com','passCamilo'),(9,'marce@example.com','passMarce');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-10  3:54:58
