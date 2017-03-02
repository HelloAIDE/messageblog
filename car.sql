-- MySQL dump 10.13  Distrib 5.5.40, for Win32 (x86)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	5.5.40

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
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text CHARACTER SET utf8 NOT NULL,
  `content` longtext CHARACTER SET utf8 NOT NULL,
  `city` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `u_id` int(11) NOT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `lastmodifytime` datetime DEFAULT NULL,
  `index_top` varchar(255) DEFAULT '0' COMMENT '置顶',
  `index_hot` varchar(255) DEFAULT '0' COMMENT '热门',
  `index_essence` varchar(255) DEFAULT '0' COMMENT '精华',
  `view_number` int(11) DEFAULT '0' COMMENT '阅读人数',
  `resources` text COMMENT '资源',
  `state` int(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'我是标题','我是内容','四川','1',1,'2016年12月20日 20:47:50',NULL,NULL,NULL,NULL,NULL,NULL,1),(2,'我是标题','我是内容','四川','1',1,'2016年12月20日 20:48:35',NULL,NULL,NULL,NULL,NULL,NULL,1),(3,'投票','欢迎关注大牛哥的微信','suzhou','1',1,'2016年12月20日 20:52:13',NULL,NULL,NULL,NULL,NULL,NULL,0),(4,'投票','欢迎关注大牛哥的微信','suzhou','1',1,'2016年12月20日 20:52:24',NULL,NULL,NULL,NULL,NULL,NULL,0),(5,'投票','欢迎关注大牛哥的微信<a href=\"123456\" >我是一个链接</a>','suzhou','1',1,'2016年12月20日 20:53:05',NULL,NULL,NULL,NULL,NULL,NULL,0),(6,'投票','欢迎关注大牛哥的微信&lta href=\"123456\" &rt我是一个链接&lt/a&rt','suzhou','1',1,'2016年12月20日 20:57:48',NULL,NULL,NULL,NULL,NULL,NULL,0),(7,'投票','欢迎关注大牛哥的微信','suzhou','1',1,'2016年12月20日 21:31:44',NULL,NULL,NULL,NULL,NULL,NULL,0),(8,'投票','欢迎关注大牛哥的微信&ltscript>','suzhou','1',1,'2016年12月20日 21:31:55',NULL,NULL,NULL,NULL,NULL,NULL,0),(9,'投票','欢迎关注大牛哥的微信&ltscript>alert(1)<\\/script>','suzhou','1',1,'2016年12月20日 21:32:20',NULL,NULL,NULL,NULL,NULL,NULL,0),(10,'123456','欢迎关注大牛哥的微信&ltscript>alert(1)&lt/script&rt','123123','1',1,'2016年12月20日 21:34:38',NULL,NULL,NULL,NULL,NULL,NULL,0),(11,'123456','欢迎关注大牛哥的微信&lt;script&gt;alert(1)&lt;/script&gt;','123123','1',1,'2016年12月20日 21:39:32',NULL,NULL,NULL,NULL,NULL,NULL,0),(12,'你猜','啊手动阀撒发射点发生发射点法发射点发生啊手动阀撒发射点发生发射点法发射点发生啊手动阀撒发射点发生发射点法发射点发生','123','1',1,'2016年12月23日 14:01:09',NULL,NULL,NULL,NULL,NULL,'123',0);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `u_name` varchar(255) DEFAULT NULL,
  `u_id` int(11) NOT NULL,
  `content` longtext CHARACTER SET utf8,
  `createtime` varchar(255) DEFAULT '0',
  `state` varchar(255) DEFAULT '0',
  PRIMARY KEY (`r_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (5,1,4,'admin',1,'test1','2016年12月23日 19:57:25',NULL),(4,1,NULL,'admin',1,'test','2016年12月23日 19:56:00',NULL),(18,1,5,'admin',1,'@admin:3','2016年12月24日 01:29:37',NULL);
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '板块名称',
  `image` varchar(255) DEFAULT NULL COMMENT '板块图片',
  `manager_id` varchar(255) DEFAULT NULL COMMENT '版主id',
  PRIMARY KEY (`t_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'移动流量1','http://localhost:8080/Spring_Study/image/type_default_image.png','111');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `introduct` varchar(255) DEFAULT NULL,
  `user` varchar(20) DEFAULT NULL,
  `pass` varchar(36) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phone` varchar(18) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `qq_number` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `createTime` varchar(30) NOT NULL DEFAULT '0',
  `lastLoginTime` varchar(30) DEFAULT '0',
  `lastModifyTime` varchar(30) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'admin','XhXvxrtCmbyYQKakP1NGzg==','admin','123',NULL,'2323','123456456@qq.com','qqq','0','2016年12月20日 18:06:22','2016年12月24日 11:22:49','2016年12月20日 18:15:31'),(2,NULL,'admintest','XhXvxrtCmbyYQKakP1NGzg==','报员_1482242251579','123456',NULL,'2399007164','afssd@Qq.ad','','0','2016年12月20日 21:57:31',NULL,NULL),(3,NULL,'admin123','XhXvxrtCmbyYQKakP1NGzg==','报员_1482550147046','15198094779',NULL,'2399007164','afssd@Qq.ad','asdfasdfasdfasdfasdfasfas','0','2016年12月24日 11:29:07',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-25 13:23:15
