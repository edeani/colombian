create database domicilios;
use domicilios;
-- phpMyAdmin SQL Dump
-- version 4.3.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 07-12-2016 a las 19:05:12
-- Versión del servidor: 5.5.39-MariaDB
-- Versión de PHP: 5.5.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `domicilios`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `cons` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COMMENT='Estas son las categorias de producto: sopas, plato, porción o bebida.';

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`cons`, `nombre`) VALUES
(1, 'plato'),
(2, 'sopa'),
(3, 'porcion'),
(4, 'bebida');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallepedido`
--

CREATE TABLE IF NOT EXISTS `detallepedido` (
  `iddetallepedido` int(11) NOT NULL,
  `idproducto` int(11) DEFAULT NULL,
  `cantidadorden` int(11) DEFAULT NULL,
  `totalproducto` float NOT NULL,
  `preciounitario` float NOT NULL,
  `idpedido` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detallepedido`
--

INSERT INTO `detallepedido` (`iddetallepedido`, `idproducto`, `cantidadorden`, `totalproducto`, `preciounitario`, `idpedido`) VALUES
(1, 10, 1, 7500, 7500, 2),
(10, 5, 1, 9500, 9500, 5),
(11, 38, 1, 4500, 4500, 5),
(12, 39, 1, 5000, 5000, 5),
(13, 9, 1, 13990, 13990, 6),
(14, 7, 2, 4000, 2000, 6),
(15, 19, 2, 3000, 1500, 6),
(16, 31, 1, 18300, 18300, 7),
(17, 9, 1, 13990, 13990, 7),
(18, 3, 1, 4700, 4700, 8),
(19, 7, 1, 2000, 2000, 8),
(20, 37, 1, 3000, 3000, 8),
(21, 9, 2, 27980, 13990, 9),
(22, 31, 1, 18300, 18300, 10),
(23, 37, 1, 3000, 3000, 11),
(24, 38, 2, 9000, 4500, 11),
(25, 7, 1, 2000, 2000, 12),
(26, 37, 1, 3000, 3000, 12),
(27, 11, 1, 7800, 7800, 3),
(28, 7, 1, 2000, 2000, 3),
(29, 12, 4, 4000, 1000, 3),
(30, 14, 1, 1500, 1500, 3),
(31, 26, 2, 3000, 1500, 3),
(32, 22, 2, 7000, 3500, 3),
(33, 29, 3, 7500, 2500, 4),
(34, 31, 1, 18300, 18300, 4),
(39, 31, 1, 18300, 18300, 13),
(40, 10, 1, 7500, 7500, 13),
(41, 11, 2, 15600, 7800, 13),
(42, 19, 2, 3000, 1500, 13),
(43, 12, 1, 1000, 1000, 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `idpedido` int(11) NOT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `totalpedido` float DEFAULT NULL,
  `estadopedido` varchar(45) DEFAULT NULL,
  `coordenadas` varchar(200) DEFAULT NULL,
  `idtipopago` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `comentarios` varchar(500) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`idpedido`, `idusuario`, `direccion`, `totalpedido`, `estadopedido`, `coordenadas`, `idtipopago`, `fecha`, `comentarios`) VALUES
(2, 1000, 'calle falsa 123', 7500, 'P', NULL, 1, '2016-11-19 00:00:00', NULL),
(3, 1000, 'calle falsa 123', 25300, 'A', NULL, 2, '2016-11-19 00:00:00', NULL),
(4, 1000, 'Carrera 74B #47-57', 25800, 'R', NULL, 1, '2016-11-19 00:00:00', NULL),
(5, 1000, 'Carrera 37 #25-77', 19000, 'P', NULL, 1, '2016-11-21 00:00:00', NULL),
(6, 1000, 'Carrera 37 #25-77', 20990, 'P', NULL, 1, '2016-11-21 00:00:00', NULL),
(7, 1000, 'Carrera 37 #25-77', 32290, 'R', NULL, 1, '2016-11-21 00:00:00', NULL),
(8, 1000, 'Carrera 37 #25B-28', 9700, 'A', NULL, 1, '2016-11-21 00:00:00', NULL),
(9, 1000, 'Carrera 37 #25-77', 27980, 'P', NULL, 1, '2016-11-21 00:00:00', NULL),
(10, 1000, 'Carrera 37 #25-77', 18300, 'P', NULL, 2, '2016-11-21 00:00:00', NULL),
(11, 1000, 'Carrera 37 #25-77', 12000, 'P', NULL, 1, '2016-11-21 00:00:00', NULL),
(12, 1000, 'Carrera 37 #25-77', 5000, 'P', NULL, 1, '2016-11-21 00:00:00', NULL),
(13, 1000, 'Carrera 37 #25-77', 45400, 'A', NULL, 3, '2016-11-21 00:00:00', 'Mis comentarios de los buenos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE IF NOT EXISTS `producto` (
  `idproducto` int(11) NOT NULL,
  `nombreproducto` varchar(45) DEFAULT NULL,
  `precioproducto` float DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `descripcion` varchar(500) NOT NULL,
  `estado` varchar(1) DEFAULT NULL,
  `imagen` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idproducto`, `nombreproducto`, `precioproducto`, `tipo`, `descripcion`, `estado`, `imagen`) VALUES
(2, '1/2 POLLO ASADO', 9000, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(3, '1/4 POLLO ASADO', 4700, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(4, '1 POLLO APANADO', 18000, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(5, '1/2 POLLO APANADO', 9500, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(6, '1/4 POLLO APANADO', 5000, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(7, 'CONSOME', 2000, 2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(8, 'ARROZ CON POLLO', 10500, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(9, 'CHURRASCO', 13990, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', '9.jpg'),
(10, 'BANDEJA  ASADO ENSAL CA', 7500, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(11, 'BANDEJA  APANA ENS CASER', 7800, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(12, 'PORCION DE ARROZ', 1000, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(13, 'PORCION DE PAPA SALADA', 1500, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(14, 'PORCION DE MADURO', 1500, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(15, 'PORCION DE FRIJOLES', 800, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(16, 'ENSALADA CASERA', 1300, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(17, 'ENSALADA DE PIÑA', 1300, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(18, 'PAPA A LA FRANCESA', 2000, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(19, 'GASEOSA 350', 1500, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(20, 'GASEOSA NO RETORNABLE', 2500, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(21, 'JUGO HIT 250ML', 1200, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(22, 'GASEOSA FAMILIAR 1.5 L', 3500, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(23, 'AGUA TARRO CON/SIN GAS', 2000, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(24, 'AGUA SABORIZADA', 2500, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(25, 'MR TEA', 1200, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(26, 'PONIMALTA', 1500, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(27, 'COLA Y POLA', 1500, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(28, 'CERVEZA POKER', 2000, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(29, 'CERVEZA CLUB COLOMBIA', 2500, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(30, '1 POLLO ASADO DOMICILIO', 17300, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.', 'A', NULL),
(31, '1 POLLO APANADO DOMICILIO', 18300, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam.', 'A', '31.png'),
(32, 'Mr Tea', 2500, NULL, 'sdfkljsdklfj ksklfj skljkl', 'A', NULL),
(33, 'Jugo de Mora', 3000, NULL, 'Jugo en Agua y en leche', 'A', NULL),
(34, 'Glaceado', 2500, NULL, 'dasdfgfdsgdfg', 'A', NULL),
(35, 'ILY', 4500, NULL, 'ASDFASFAF', 'A', NULL),
(36, 'Ejemplo 1', 2500, NULL, 'asdas', 'A', NULL),
(37, 'Ejemplo Foto 2', 3000, 2, 'dagdrshderhe', 'A', 'imagen'),
(38, 'Ejemplo 3', 4500, 2, 'dadadas', 'A', 'imagen'),
(39, 'Ejemplo 4', 5000, 2, 'adsfdsf', 'A', NULL),
(40, 'Ejemplo 6', 3000, 4, 'sadasd', 'A', '40.jpg'),
(41, 'Patatas', 3600, 3, 'Vienen con salsas Mayonesa - tomate', 'A', '41.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE IF NOT EXISTS `rol` (
  `idrol` int(11) NOT NULL,
  `nombrerol` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idrol`, `nombrerol`) VALUES
(1, 'USER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipopago`
--

CREATE TABLE IF NOT EXISTS `tipopago` (
  `idtipo` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `estado` varchar(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipopago`
--

INSERT INTO `tipopago` (`idtipo`, `nombre`, `estado`) VALUES
(1, 'Efectivo', 'I'),
(2, 'American Express', 'A'),
(3, 'Master Card', 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` int(11) NOT NULL,
  `nombreusuario` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `idrol` int(11) DEFAULT NULL,
  `cedula` varchar(45) DEFAULT NULL,
  `estado` varchar(1) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `nombreusuario`, `password`, `correo`, `direccion`, `idrol`, `cedula`, `estado`, `telefono`) VALUES
(1000, 'Eder Anillo Lora', 'compaq', 'anloder4@gmail.com', 'Avenida Calle 37 #25-77', 1, '1032400254', 'A', '3002768802'),
(1001, 'comandante', '123456', 'edeani@eltiempo.com', 'Calle 1 #2-9', 2, '1000111222', 'A', '3106308877'),
(1002, 'Prueba Uno', '12345678', 'uno@m.com', NULL, 1, NULL, 'A', ''),
(1003, 'Eder Nuevo', 'compaq123', 'm1@mail.com', NULL, 1, NULL, 'I', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `validacion_usuarios`
--

CREATE TABLE IF NOT EXISTS `validacion_usuarios` (
  `cons` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `token` varchar(100) NOT NULL,
  `estado` varchar(1) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `fechaactivacion` datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `validacion_usuarios`
--

INSERT INTO `validacion_usuarios` (`cons`, `idusuario`, `token`, `estado`, `fecha`, `fechaactivacion`) VALUES
(2, 1003, '$2a$10$IZuxAvRNoXiVjHptGZ7s2.Z3aIae6l50fuuQePZwoe2L4JECLNxdG', 'I', '2016-12-09 01:40:00', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`cons`);

--
-- Indices de la tabla `detallepedido`
--
ALTER TABLE `detallepedido`
  ADD PRIMARY KEY (`iddetallepedido`), ADD KEY `FK_detallepedido_producto_idx` (`idproducto`), ADD KEY `idx_detallepedido_pedido` (`idpedido`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`idpedido`), ADD KEY `FK_pedido_usuario_idx` (`idusuario`), ADD KEY `idx_pedido_tipopago` (`idtipopago`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idproducto`), ADD KEY `fk_productos_categoria_idx` (`tipo`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idrol`);

--
-- Indices de la tabla `tipopago`
--
ALTER TABLE `tipopago`
  ADD PRIMARY KEY (`idtipo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`), ADD KEY `FK_usuario_rol_idx` (`idrol`);

--
-- Indices de la tabla `validacion_usuarios`
--
ALTER TABLE `validacion_usuarios`
  ADD PRIMARY KEY (`cons`), ADD KEY `fk_idusuario_validacion_idx` (`idusuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `cons` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `detallepedido`
--
ALTER TABLE `detallepedido`
  MODIFY `iddetallepedido` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `idpedido` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idproducto` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=42;
--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idrol` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `tipopago`
--
ALTER TABLE `tipopago`
  MODIFY `idtipo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1004;
--
-- AUTO_INCREMENT de la tabla `validacion_usuarios`
--
ALTER TABLE `validacion_usuarios`
  MODIFY `cons` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detallepedido`
--
ALTER TABLE `detallepedido`
ADD CONSTRAINT `FK_detallepedido_pedido` FOREIGN KEY (`idpedido`) REFERENCES `pedido` (`idpedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_detallepedido_producto` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
ADD CONSTRAINT `FK_pedido_tipopago` FOREIGN KEY (`idtipopago`) REFERENCES `tipopago` (`idtipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_pedido_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
ADD CONSTRAINT `fk_prod_categoria` FOREIGN KEY (`tipo`) REFERENCES `categoria` (`cons`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
ADD CONSTRAINT `FK_usuario_rol` FOREIGN KEY (`idrol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `validacion_usuarios`
--
ALTER TABLE `validacion_usuarios`
ADD CONSTRAINT `fk_idusuario_validacion` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
