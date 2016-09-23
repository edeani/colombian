-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-09-2016 a las 02:28:04
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `domicilios`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `cons` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Estas son las categorias de producto: sopas, plato, porción o bebida.';

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

CREATE TABLE `detallepedido` (
  `iddetallepedido` int(11) NOT NULL,
  `idproducto` int(11) DEFAULT NULL,
  `cantidadorden` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `idpedidos` int(11) NOT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `puntodereferencia` varchar(45) DEFAULT NULL,
  `iddetallepedido` int(11) DEFAULT NULL,
  `totalpedido` float DEFAULT NULL,
  `estadopedido` varchar(45) DEFAULT NULL,
  `coordenadas` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL,
  `nombreproducto` varchar(45) DEFAULT NULL,
  `precioproducto` float DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `descripcion` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idproducto`, `nombreproducto`, `precioproducto`, `tipo`, `descripcion`) VALUES
(2, '1/2 POLLO ASADO', 9000, 1, ''),
(3, '1/4 POLLO ASADO', 4700, 1, ''),
(4, '1 POLLO APANADO', 18000, 1, ''),
(5, '1/2 POLLO APANADO', 9500, 1, ''),
(6, '1/4 POLLO APANADO', 5000, 1, ''),
(7, 'CONSOME', 2000, 2, ''),
(8, 'ARROZ CON POLLO', 10500, 1, ''),
(9, 'CHURRASCO', 13990, 1, ''),
(10, 'BANDEJA  ASADO ENSAL CA', 7500, 1, ''),
(11, 'BANDEJA  APANA ENS CASER', 7800, 1, ''),
(12, 'PORCION DE ARROZ', 1000, 3, ''),
(13, 'PORCION DE PAPA SALADA', 1500, 3, ''),
(14, 'PORCION DE MADURO', 1500, 3, ''),
(15, 'PORCION DE FRIJOLES', 800, 3, ''),
(16, 'ENSALADA CASERA', 1300, 3, ''),
(17, 'ENSALADA DE PIÑA', 1300, 3, ''),
(18, 'PAPA A LA FRANCESA', 2000, 1, ''),
(19, 'GASEOSA 350', 1500, 4, ''),
(20, 'GASEOSA NO RETORNABLE', 2500, 4, ''),
(21, 'JUGO HIT 250ML', 1200, 4, ''),
(22, 'GASEOSA FAMILIAR 1.5 L', 3500, 4, ''),
(23, 'AGUA TARRO CON/SIN GAS', 2000, 4, ''),
(24, 'AGUA SABORIZADA', 2500, 4, ''),
(25, 'MR TEA', 1200, 4, ''),
(26, 'PONIMALTA', 1500, 4, ''),
(27, 'COLA Y POLA', 1500, 4, ''),
(28, 'CERVEZA POKER', 2000, 4, ''),
(29, 'CERVEZA CLUB COLOMBIA', 2500, 4, ''),
(30, '1 POLLO ASADO DOMICILIO', 17300, 1, ''),
(31, '1 POLLO APANADO DOMICILIO', 18300, 1, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL,
  `nombrerol` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idrol`, `nombrerol`) VALUES
(1, 'USUARIO'),
(2, 'ADMINISTRADOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `nombreusuario` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `idrol` int(11) DEFAULT NULL,
  `cedula` varchar(45) DEFAULT NULL,
  `estado` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `nombreusuario`, `password`, `correo`, `direccion`, `idrol`, `cedula`, `estado`) VALUES
(1, 'edeani', 'compaq', 'anloder4@gmail.com', 'calle falsa 123', 1, '1032400254', 'A'),
(2, 'comandante', '123456', 'edeani@eltiempo.com', 'calle 1 #2-9', 2, '1000111222', 'A'),
(3, 'Prueba Uno', '12345678', 'uno@m.com', NULL, 1, NULL, 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `validacion_usuarios`
--

CREATE TABLE `validacion_usuarios` (
  `cons` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `token` varchar(100) NOT NULL,
  `estado` varchar(1) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `fechaactivacion` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `validacion_usuarios`
--

INSERT INTO `validacion_usuarios` (`cons`, `idusuario`, `token`, `estado`, `fecha`, `fechaactivacion`) VALUES
(1, 3, '$2a$10$gkGm7B5mPjrYcRGTEXhleemb8W2X8ZN5fNju8vX/X3HK3IZxB11By', 'I', '2016-09-04 03:17:00', NULL);

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
  ADD PRIMARY KEY (`iddetallepedido`),
  ADD KEY `FK_detallepedido_producto_idx` (`idproducto`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`idpedidos`),
  ADD KEY `FK_pedido_detallepedido_idx` (`iddetallepedido`),
  ADD KEY `FK_pedido_usuario_idx` (`idusuario`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idproducto`),
  ADD KEY `fk_productos_categoria_idx` (`tipo`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idrol`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD KEY `FK_usuario_rol_idx` (`idrol`);

--
-- Indices de la tabla `validacion_usuarios`
--
ALTER TABLE `validacion_usuarios`
  ADD PRIMARY KEY (`cons`),
  ADD KEY `fk_idusuario_validacion_idx` (`idusuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `cons` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idrol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `validacion_usuarios`
--
ALTER TABLE `validacion_usuarios`
  MODIFY `cons` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detallepedido`
--
ALTER TABLE `detallepedido`
  ADD CONSTRAINT `FK_detallepedido_producto` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_pedido_detallepedido` FOREIGN KEY (`iddetallepedido`) REFERENCES `detallepedido` (`iddetallepedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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
