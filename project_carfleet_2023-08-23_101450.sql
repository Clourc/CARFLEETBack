-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: project_carfleet
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fleet`
--

DROP TABLE IF EXISTS `fleet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fleet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `place` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fleet`
--

/*!40000 ALTER TABLE `fleet` DISABLE KEYS */;
INSERT INTO `fleet` VALUES (1,'strasbourg'),(2,'paris'),(3,'lyon'),(4,'marseille');
/*!40000 ALTER TABLE `fleet` ENABLE KEYS */;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

/*!40000 ALTER TABLE `model` DISABLE KEYS */;
/*!40000 ALTER TABLE `model` ENABLE KEYS */;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `vehicule_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt0fnqb20ygbx0e030kdjlm28s` (`user_id`),
  KEY `FKmglcr07j4c46lvh9q0bwahsou` (`vehicule_id`),
  CONSTRAINT `FKmglcr07j4c46lvh9q0bwahsou` FOREIGN KEY (`vehicule_id`) REFERENCES `vehicule` (`id`),
  CONSTRAINT `FKt0fnqb20ygbx0e030kdjlm28s` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `nb_cp` varchar(255) DEFAULT NULL,
  `nb_licence` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `fleet_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9jc1om02bwao3c5e0024ppm5i` (`fleet_id`),
  CONSTRAINT `FK9jc1om02bwao3c5e0024ppm5i` FOREIGN KEY (`fleet_id`) REFERENCES `fleet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'aurelie.ziegler@example.com','aurelie','ziegler','1234567A','YL123','0601020304',1),(2,'yannick.minck@example.com','yannick','minck','2345678Y','AL456','0601020304',1),(3,'jamal.ouldrabia@example.com','jamal','ouldrabia','3456789J','JAO12','0601020305',2),(4,'lucas.corbino@example.com','lucas','corbino','4567891L','LM345','0601020305',2),(5,'richard.nguyen@example.com','richard','nguyen','7891234R','RS789','0601020306',4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Table structure for table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `energy` varchar(255) DEFAULT NULL,
  `licence_plate` varchar(255) DEFAULT NULL,
  `nb_doors` int NOT NULL,
  `nb_seats` int NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `fleet_id` bigint DEFAULT NULL,
  `model_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnf23hsdlvi53n6vroe5exgk2t` (`fleet_id`),
  KEY `FKaf8p255ag4p8wisehf2i1r300` (`model_id`),
  CONSTRAINT `FKaf8p255ag4p8wisehf2i1r300` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`),
  CONSTRAINT `FKnf23hsdlvi53n6vroe5exgk2t` FOREIGN KEY (`fleet_id`) REFERENCES `fleet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicule`
--

/*!40000 ALTER TABLE `vehicule` DISABLE KEYS */;
INSERT INTO `vehicule` VALUES (1,'renault','electric','ZO123RE',5,5,'ZOE R110',1,NULL),(2,'renault ','essence','CL456RE',5,5,'CLIO RS Line',2,NULL),(3,'renault ','essence','KA789RE',5,5,'KANGOO 3',3,NULL),(4,'renault ','essence','ME234RE',5,5,'MEGANE 3 phase 3',4,NULL),(5,'citroen ','electric','BE567CI',5,7,'e BERLINGO shine',1,NULL),(6,'peugeot ','diesel','BO891PE',4,3,'BOXER Asphalt 333',2,NULL),(7,'renault ','diesel','TR912RE',3,4,'TRAFIC Grand confort',4,NULL);
/*!40000 ALTER TABLE `vehicule` ENABLE KEYS */;

--
-- Dumping routines for database 'project_carfleet'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-23 10:15:20
