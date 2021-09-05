CREATE DATABASE  IF NOT EXISTS `bookstore` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bookstore`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` bigint NOT NULL AUTO_INCREMENT,
  `address_name` varchar(45) DEFAULT NULL,
  `district_id` bigint DEFAULT NULL,
  `province_id` bigint DEFAULT NULL,
  `village_id` bigint DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `FKqbjwfi50pdenou8j14knnffrh` (`district_id`),
  KEY `FKf8x0jfwoo94op8u88og1ohdcn` (`province_id`),
  KEY `FKgufu9edl0iiai9gwjs84iwmgb` (`village_id`),
  CONSTRAINT `FKf8x0jfwoo94op8u88og1ohdcn` FOREIGN KEY (`province_id`) REFERENCES `province` (`province_id`),
  CONSTRAINT `FKgufu9edl0iiai9gwjs84iwmgb` FOREIGN KEY (`village_id`) REFERENCES `village` (`village_id`),
  CONSTRAINT `FKqbjwfi50pdenou8j14knnffrh` FOREIGN KEY (`district_id`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id_author` bigint NOT NULL AUTO_INCREMENT,
  `author_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_author`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Tổ Hữu'),(2,'Chế Lan Viên'),(3,'Nguyễn Duy'),(4,'Thế Lữ'),(5,'Hamingway'),(6,'Paulo Coelho'),(7,'Dale Carnegie'),(8,'Napoleon Hill'),(9,'Shiv Khera'),(10,' T. Harv Eker'),(11,'Tony ');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id_book` bigint NOT NULL AUTO_INCREMENT,
  `book_name` varchar(45) DEFAULT NULL,
  `describe_book` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `publish_day` date DEFAULT NULL,
  `total_quantity` int DEFAULT NULL,
  `id_author` bigint DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `id_company` bigint DEFAULT NULL,
  PRIMARY KEY (`id_book`),
  KEY `FKrb7h78i8dnxxboiapao9caepy` (`id_author`),
  KEY `FKam9riv8y6rjwkua1gapdfew4j` (`category_id`),
  KEY `FKoqlj3gpg7ogs1k63vo6a28k3d` (`id_company`),
  CONSTRAINT `FKam9riv8y6rjwkua1gapdfew4j` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `FKoqlj3gpg7ogs1k63vo6a28k3d` FOREIGN KEY (`id_company`) REFERENCES `publishing_company` (`id_company`),
  CONSTRAINT `FKrb7h78i8dnxxboiapao9caepy` FOREIGN KEY (`id_author`) REFERENCES `author` (`id_author`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Chuông nguyện hồn ai','Đây là cuốn sách kể về thời chiến tranh thế giới thứ 2','chuong-nguyen-hon-ai.jpg',120000.00,'1940-10-21',200,5,3,3),(2,'Đắc nhân tâm','Đây là cuồn sách giúp bạn thu phục lòng người','dac-nhan-tam.jpg',200000.00,'1936-10-01',150,7,2,3),(3,'You Can Win','Đây là cuốn sách giúp bạn chinh phục mọi thứ trong cuộc đời','you-can-win.jpg',80000.00,'1998-01-01',210,9,2,3),(4,'Bí mật tư duy triệu phú','Làm giàu không khó ','bi-mat-tu-duy-trieu-phu.jpg',110000.00,'2005-05-12',180,10,2,10),(5,'Nghĩ giàu làm giàu','Nghĩ là làm, không nói nhiều','nghi-giau-lam-giau.jpg',95000.00,'1937-01-01',90,8,2,7),(7,'Trên đường băng','Tuổi trẻ phải đọc một lần','tren-duong-bang.jpg',78000.00,'2015-01-01',120,11,2,7);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Sách giáo khoa'),(2,'Sách kỹ năng'),(3,'Tiểu thuyết'),(4,'Sách văn hóa lịch sử'),(5,'Sách tôn giáo');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `district` (
  `district_id` bigint NOT NULL AUTO_INCREMENT,
  `district_name` varchar(45) DEFAULT NULL,
  `province_id` bigint DEFAULT NULL,
  PRIMARY KEY (`district_id`),
  KEY `FK276utu38g5lgqeth6pwfm3rw2` (`province_id`),
  CONSTRAINT `FK276utu38g5lgqeth6pwfm3rw2` FOREIGN KEY (`province_id`) REFERENCES `province` (`province_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gender` (
  `id_gender` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_gender`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `item_id` bigint NOT NULL AUTO_INCREMENT,
  `quantity_books` int DEFAULT NULL,
  `id_book` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FKjt4lglxyfbs0kv25yorn3c7m6` (`id_book`),
  KEY `FKmhqj9105yawax1y8dp68i66xm` (`user_id`),
  CONSTRAINT `FKjt4lglxyfbs0kv25yorn3c7m6` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`),
  CONSTRAINT `FKmhqj9105yawax1y8dp68i66xm` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) DEFAULT NULL,
  `quantity` int NOT NULL,
  `id_book` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhf74ol57vvqf2ld97ddjb37eq` (`id_book`),
  KEY `FKrws2q0si6oyd6il8gqe2aennc` (`order_id`),
  CONSTRAINT `FKhf74ol57vvqf2ld97ddjb37eq` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`),
  CONSTRAINT `FKrws2q0si6oyd6il8gqe2aennc` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `name_of_customer` varchar(45) DEFAULT NULL,
  `order_day` date DEFAULT NULL,
  `order_status` int DEFAULT NULL,
  `phone_of_customer` varchar(10) DEFAULT NULL,
  `total_price` decimal(19,2) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `province` (
  `province_id` bigint NOT NULL AUTO_INCREMENT,
  `province_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishing_company`
--

DROP TABLE IF EXISTS `publishing_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publishing_company` (
  `id_company` bigint NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_company`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishing_company`
--

LOCK TABLES `publishing_company` WRITE;
/*!40000 ALTER TABLE `publishing_company` DISABLE KEYS */;
INSERT INTO `publishing_company` VALUES (1,'Tôn giáo'),(2,'Giáo dục'),(3,'New Star'),(4,'Khoa học và kỹ thuật'),(5,'Tài chính'),(6,'Nông nghiệp'),(7,'Tuổi trẻ'),(8,'Thể dục thể thao'),(9,'Đại học quốc gia Hà Nội'),(10,'Thanh niên'),(11,'Kim Đồng');
/*!40000 ALTER TABLE `publishing_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id_book` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `comments` varchar(200) DEFAULT NULL,
  `star` float NOT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id_book`,`user_id`),
  KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  CONSTRAINT `FK4pg8e54fsaswy3hv8f3uuy381` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_role` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `id_gender` bigint DEFAULT NULL,
  `id_role` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK2q2e2aefyrvp7frjv6a1lqdh9` (`id_gender`),
  KEY `FK6njoh3pti5jnlkowken3r8ttn` (`id_role`),
  CONSTRAINT `FK2q2e2aefyrvp7frjv6a1lqdh9` FOREIGN KEY (`id_gender`) REFERENCES `gender` (`id_gender`),
  CONSTRAINT `FK6njoh3pti5jnlkowken3r8ttn` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_address` (
  `address_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`address_id`,`user_id`),
  KEY `FKk2ox3w9jm7yd6v1m5f68xibry` (`user_id`),
  CONSTRAINT `FKdaaxogn1ss81gkcsdn05wi6jp` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `FKk2ox3w9jm7yd6v1m5f68xibry` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `village`
--

DROP TABLE IF EXISTS `village`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `village` (
  `village_id` bigint NOT NULL AUTO_INCREMENT,
  `village_name` varchar(45) DEFAULT NULL,
  `district_id` bigint DEFAULT NULL,
  PRIMARY KEY (`village_id`),
  KEY `FKm6rpub02lqr66lfljhc7nhffw` (`district_id`),
  CONSTRAINT `FKm6rpub02lqr66lfljhc7nhffw` FOREIGN KEY (`district_id`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `village`
--

LOCK TABLES `village` WRITE;
/*!40000 ALTER TABLE `village` DISABLE KEYS */;
/*!40000 ALTER TABLE `village` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bookstore'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-04 20:54:48
