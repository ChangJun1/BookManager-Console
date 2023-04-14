--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student`
(
    `sid`   int                                                               NOT NULL AUTO_INCREMENT,
    `name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL,
    `sex`   enum ('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `grade` int                                                               NOT NULL,
    PRIMARY KEY (`sid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DELIMITER ;;
/*!50003 CREATE */ /*!50017 DEFINER =`test`@`localhost`*/ /*!50003 TRIGGER `del_stu`
    BEFORE DELETE
    ON `student`
    FOR EACH ROW DELETE
                 FROM borrow
                 WHERE sid = old.sid */;;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book`
(
    `bid`         int NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `price`       decimal(10, 2)                          DEFAULT NULL,
    PRIMARY KEY (`bid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DELIMITER ;;
/*!50003 CREATE */ /*!50017 DEFINER =`test`@`localhost`*/ /*!50003 TRIGGER `del_book`
    BEFORE DELETE
    ON `book`
    FOR EACH ROW DELETE
                 FROM borrow
                 WHERE bid = old.bid */;;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow`
(
    `id`  int NOT NULL AUTO_INCREMENT,
    `sid` int DEFAULT NULL,
    `bid` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_sid_bid` (`sid`, `bid`),
    KEY `f_bid` (`bid`),
    CONSTRAINT `f_bid` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`),
    CONSTRAINT `f_sid` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;