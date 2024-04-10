-- Active: 1712763254475@@127.0.0.1@3306@apiong
CREATE DATABASE IF NOT EXISTS apiong;
USE apiong;
INSERT INTO `cities` (`id`, `department`, `name`) VALUES (1,'Santander','Bucaramanga'),(2,'Santander','Floridablanca'),(3,'Cundinamarca','Bogota');
INSERT INTO `headquarter` VALUES (1,'Cabecera','HQ-1-1','SedeBucaros',1),(2,'FloridaCentro','HQ-2-2','SedeFlorida',2);
INSERT INTO `professions` VALUES (1,'PR-1','Doctor'),(2,'PR-2','Enfermero'),(3,'PR-3','Medico cirujano'),(4,'PR-4','Contable'),(5,'PR-5','Tesorero');
INSERT INTO `users` VALUES (1,'admin@example.com','passAdmin'),(2,'admin5@example.com','passAdmin5'),(3,'aux1@example.com','passAux1'),
(8,'gonzales@example.com','passCamilo'),(9,'marce@example.com','passMarce');
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN',1),(2,'ROLE_ADMIN',2),(3,'ROLE_ASSISTANT',3),(8,'ROLE_DIRECTOR',8),(9,'ROLE_DIRECTOR',9);
INSERT INTO `annual_fee` VALUES (1,10,'Minima'),(2,20,'Media'),(3,30,'Maxima');
INSERT INTO `person` VALUES (1,1111555888,'CEDULA','camilo@example.com','M','Camilo','Hernandez'),
(2,1234567891,'PASAPORTE','fernanda@example.com','F','Fernanda','Arias'),(3,1987654321,'CEDULA_EXTRANJERIA','andrea@example.com','F','Andrea','Gomez'),
(4,1111222333,'CEDULA','karen@example.com','F','Karen','Aguilar'),(6,1234567892,'CEDULA','andres@example.com','M','Andres','Martinez'),
(11,1234567894,'CEDULA','gonzales@example.com','M','Camilo','Gonzales'),(12,1234567893,'CEDULA','marce@example.com','F','Marcela','Perez'),
(13,1234567895,'CEDULA','john@example.com','M','John','Doe'),(14,1234567896,'CEDULA','alice@example.com','F','Alice','Smith'),
(15,1234567897,'CEDULA','bob@example.com','M','Bob','Johnson');
INSERT INTO `administrative` VALUES (13,1,4),(14,1,4),(15,2,5);
INSERT INTO `directors` VALUES (12,1),(11,2);
INSERT INTO `partners` VALUES (321123,'2024-04-10',4,2,1),(123321,'2024-04-10',6,3,2);
INSERT INTO `sanitaries` VALUES (_binary '\0',3,1,1,1),(_binary '',0,2,2,3),(_binary '',0,3,1,2);
INSERT INTO `shelter` VALUES (1,'Norte','SH-1-1','RefugioBucaros1',1),(2,'Kennedy','SH-3-2','RefugioBogota1',3),(3,'Champinero','SH-3-3','RefugioBogota2',3);
