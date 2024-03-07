-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: localhost    Database: db_api
-- ------------------------------------------------------
-- Server version	8.0.35-0ubuntu0.22.04.1

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
-- Table structure for table `sale_product`
--

DROP TABLE IF EXISTS `sale_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_product` (
  `sale_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  KEY `FKj5rlwx4hxjlj9ohcwawgmnsva` (`product_id`),
  KEY `FKjjhg490frhh98nnlthr1xti0k` (`sale_id`),
  CONSTRAINT `FKj5rlwx4hxjlj9ohcwawgmnsva` FOREIGN KEY (`product_id`) REFERENCES `tb_products` (`id`),
  CONSTRAINT `FKjjhg490frhh98nnlthr1xti0k` FOREIGN KEY (`sale_id`) REFERENCES `tb_sales` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_product`
--

LOCK TABLES `sale_product` WRITE;
/*!40000 ALTER TABLE `sale_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_clients`
--

DROP TABLE IF EXISTS `tb_clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_clients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_clients`
--

LOCK TABLES `tb_clients` WRITE;
/*!40000 ALTER TABLE `tb_clients` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_products`
--

DROP TABLE IF EXISTS `tb_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `has_in_stock` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int DEFAULT NULL,
  `stock_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7c02hicsafvl8mdijt8fg9kkt` (`stock_id`),
  CONSTRAINT `FK7c02hicsafvl8mdijt8fg9kkt` FOREIGN KEY (`stock_id`) REFERENCES `tb_stock` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_products`
--

LOCK TABLES `tb_products` WRITE;
/*!40000 ALTER TABLE `tb_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sales`
--

DROP TABLE IF EXISTS `tb_sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sales` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `has_completed` bit(1) DEFAULT NULL,
  `moment_of_sale` datetime(6) DEFAULT NULL,
  `total_value_of_sale` double DEFAULT NULL,
  `client_who_buy_id` bigint DEFAULT NULL,
  `seller_who_sale_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kphnqveykavocdf21wke7ngd7` (`client_who_buy_id`),
  KEY `FKqjljelov8aox2ion8bmkjoqrg` (`seller_who_sale_id`),
  CONSTRAINT `FK9ces2l7xtmkbliraplc6ylxiv` FOREIGN KEY (`client_who_buy_id`) REFERENCES `tb_clients` (`id`),
  CONSTRAINT `FKqjljelov8aox2ion8bmkjoqrg` FOREIGN KEY (`seller_who_sale_id`) REFERENCES `tb_sellers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sales`
--

LOCK TABLES `tb_sales` WRITE;
/*!40000 ALTER TABLE `tb_sales` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sellers`
--

DROP TABLE IF EXISTS `tb_sellers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sellers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sale_quantity_in_month` int DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sellers`
--

LOCK TABLES `tb_sellers` WRITE;
/*!40000 ALTER TABLE `tb_sellers` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_sellers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_stock`
--

DROP TABLE IF EXISTS `tb_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_stock` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `capacity_max` int DEFAULT NULL,
  `current_capacity` bigint DEFAULT NULL,
  `stock_name` varchar(255) DEFAULT NULL,
  `total_price_in_stock` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_stock`
--

LOCK TABLES `tb_stock` WRITE;
/*!40000 ALTER TABLE `tb_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-06 23:14:53
