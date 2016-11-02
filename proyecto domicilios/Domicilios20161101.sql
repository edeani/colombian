-- MySQL dump 10.13  Distrib 5.7.13, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: domicilios
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.18-MariaDB

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `cons` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cons`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COMMENT='Estas son las categorias de producto: sopas, plato, porción o bebida.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'plato'),(2,'sopa'),(3,'porcion'),(4,'bebida');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallepedido`
--

DROP TABLE IF EXISTS `detallepedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallepedido` (
  `iddetallepedido` int(11) NOT NULL AUTO_INCREMENT,
  `idproducto` int(11) DEFAULT NULL,
  `cantidadorden` int(11) DEFAULT NULL,
  `totalproducto` float NOT NULL,
  `preciounitario` float NOT NULL,
  `idpedido` int(11) NOT NULL,
  PRIMARY KEY (`iddetallepedido`),
  KEY `FK_detallepedido_producto_idx` (`idproducto`),
  KEY `idx_detallepedido_pedido` (`idpedido`),
  CONSTRAINT `FK_detallepedido_pedido` FOREIGN KEY (`idpedido`) REFERENCES `pedido` (`idpedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_detallepedido_producto` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallepedido`
--

LOCK TABLES `detallepedido` WRITE;
/*!40000 ALTER TABLE `detallepedido` DISABLE KEYS */;
INSERT INTO `detallepedido` VALUES (1,10,1,7500,7500,2),(2,11,1,7800,7800,3),(3,7,1,2000,2000,3),(4,12,4,4000,1000,3),(5,14,1,1500,1500,3),(6,26,2,3000,1500,3),(7,29,2,5000,2500,4),(8,7,4,8000,2000,4),(9,31,1,18300,18300,4),(10,5,1,9500,9500,5),(11,38,1,4500,4500,5),(12,39,1,5000,5000,5),(13,9,1,13990,13990,6),(14,7,2,4000,2000,6),(15,19,2,3000,1500,6);
/*!40000 ALTER TABLE `detallepedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `idpedido` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `totalpedido` float DEFAULT NULL,
  `estadopedido` varchar(45) DEFAULT NULL,
  `coordenadas` varchar(200) DEFAULT NULL,
  `idtipopago` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`idpedido`),
  KEY `FK_pedido_usuario_idx` (`idusuario`),
  KEY `idx_pedido_tipopago` (`idtipopago`),
  CONSTRAINT `FK_pedido_tipopago` FOREIGN KEY (`idtipopago`) REFERENCES `tipopago` (`idtipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pedido_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (2,1,'calle falsa 123',7500,'P',NULL,1,'0000-00-00 00:00:00'),(3,1,'calle falsa 123',18300,'P',NULL,2,'0000-00-00 00:00:00'),(4,1,'Carrera 74B #47-57',31300,'P',NULL,1,'0000-00-00 00:00:00'),(5,1,'Carrera 37 #25-77',19000,'P',NULL,1,'2016-11-01 21:50:25'),(6,1,'Carrera 37 #25-77',20990,'P',NULL,1,'2016-11-01 22:58:23');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL AUTO_INCREMENT,
  `nombreproducto` varchar(45) DEFAULT NULL,
  `precioproducto` float DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `descripcion` varchar(500) NOT NULL,
  `estado` varchar(1) DEFAULT NULL,
  `imagen` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idproducto`),
  KEY `fk_productos_categoria_idx` (`tipo`),
  CONSTRAINT `fk_prod_categoria` FOREIGN KEY (`tipo`) REFERENCES `categoria` (`cons`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (2,'1/2 POLLO ASADO',9000,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(3,'1/4 POLLO ASADO',4700,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(4,'1 POLLO APANADO',18000,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(5,'1/2 POLLO APANADO',9500,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(6,'1/4 POLLO APANADO',5000,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(7,'CONSOME',2000,2,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(8,'ARROZ CON POLLO',10500,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(9,'CHURRASCO',13990,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(10,'BANDEJA  ASADO ENSAL CA',7500,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(11,'BANDEJA  APANA ENS CASER',7800,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(12,'PORCION DE ARROZ',1000,3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(13,'PORCION DE PAPA SALADA',1500,3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(14,'PORCION DE MADURO',1500,3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(15,'PORCION DE FRIJOLES',800,3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(16,'ENSALADA CASERA',1300,3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(17,'ENSALADA DE PIÑA',1300,3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(18,'PAPA A LA FRANCESA',2000,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(19,'GASEOSA 350',1500,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(20,'GASEOSA NO RETORNABLE',2500,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(21,'JUGO HIT 250ML',1200,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(22,'GASEOSA FAMILIAR 1.5 L',3500,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(23,'AGUA TARRO CON/SIN GAS',2000,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(24,'AGUA SABORIZADA',2500,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(25,'MR TEA',1200,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(26,'PONIMALTA',1500,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(27,'COLA Y POLA',1500,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(28,'CERVEZA POKER',2000,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(29,'CERVEZA CLUB COLOMBIA',2500,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(30,'1 POLLO ASADO DOMICILIO',17300,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(31,'1 POLLO APANADO DOMICILIO',18300,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.','A',NULL),(32,'Mr Tea',2500,NULL,'sdfkljsdklfj ksklfj skljkl','A',NULL),(33,'Jugo de Mora',3000,NULL,'Jugo en Agua y en leche','A',NULL),(34,'Glaceado',2500,NULL,'dasdfgfdsgdfg','A',NULL),(35,'ILY',4500,NULL,'ASDFASFAF','A',NULL),(36,'Ejemplo 1',2500,NULL,'asdas','A',NULL),(37,'Ejemplo Foto 2',3000,2,'dagdrshderhe','A','imagen'),(38,'Ejemplo 3',4500,2,'dadadas','A','imagen'),(39,'Ejemplo 4',5000,2,'adsfdsf','A',NULL),(40,'Ejemplo 6',3000,4,'sadasd','A','40.jpg');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  `nombrerol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipopago`
--

DROP TABLE IF EXISTS `tipopago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipopago` (
  `idtipo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  PRIMARY KEY (`idtipo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipopago`
--

LOCK TABLES `tipopago` WRITE;
/*!40000 ALTER TABLE `tipopago` DISABLE KEYS */;
INSERT INTO `tipopago` VALUES (1,'Efectivo'),(2,'Visa'),(3,'Master Card');
/*!40000 ALTER TABLE `tipopago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreusuario` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `idrol` int(11) DEFAULT NULL,
  `cedula` varchar(45) DEFAULT NULL,
  `estado` varchar(1) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `FK_usuario_rol_idx` (`idrol`),
  CONSTRAINT `FK_usuario_rol` FOREIGN KEY (`idrol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'edeani','compaq','anloder4@gmail.com','Carrera 37 #25-77',1,'1032400254','A','3002768802'),(2,'comandante','123456','edeani@eltiempo.com','calle 1 #2-9',2,'1000111222','A',''),(3,'Prueba Uno','12345678','uno@m.com',NULL,1,NULL,'A','');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validacion_usuarios`
--

DROP TABLE IF EXISTS `validacion_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `validacion_usuarios` (
  `cons` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) NOT NULL,
  `token` varchar(100) NOT NULL,
  `estado` varchar(1) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `fechaactivacion` datetime DEFAULT NULL,
  PRIMARY KEY (`cons`),
  KEY `fk_idusuario_validacion_idx` (`idusuario`),
  CONSTRAINT `fk_idusuario_validacion` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validacion_usuarios`
--

LOCK TABLES `validacion_usuarios` WRITE;
/*!40000 ALTER TABLE `validacion_usuarios` DISABLE KEYS */;
INSERT INTO `validacion_usuarios` VALUES (1,3,'$2a$10$gkGm7B5mPjrYcRGTEXhleemb8W2X8ZN5fNju8vX/X3HK3IZxB11By','I','2016-09-04 03:17:00',NULL);
/*!40000 ALTER TABLE `validacion_usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-01 23:08:48
