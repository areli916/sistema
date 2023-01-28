-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-01-2023 a las 06:07:06
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ejemplo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `matricula` varchar(10) NOT NULL,
  `correo` varchar(256) NOT NULL,
  `f_nacimiento` date NOT NULL,
  `carrera` int(11) NOT NULL DEFAULT 0,
  `estado` varchar(50) DEFAULT 'Activo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`matricula`, `correo`, `f_nacimiento`, `carrera`, `estado`) VALUES
('1111111111', '1111111111@TESE.EDU.MX', '1998-11-11', 4, 'Activo'),
('201621433', '201621433@tese.edu.mx', '1998-02-20', 2, 'Activo'),
('201711433', 'FERNANDA@GMAIL.COM', '1997-08-17', 4, 'Activo'),
('201720056', '201720056@TESE.EDU.MX', '1999-10-27', 4, 'Activo'),
('201812345', '2018123456@tese.edu.mx', '1999-03-23', 0, 'Activo'),
('201821158', '201821158@TESE:EDU:MX', '2000-12-11', 2, 'Activo'),
('201821234', '201821234@TESE.EDU.MX', '2020-12-16', 2, 'Activo'),
('201821501', '201821501@TESE.EDU.MX', '1999-09-09', 1, 'Activo'),
('201821938', '201821938@TESE.EDU.MX', '2001-03-15', 0, 'Activo'),
('201823346', '201823346@TESE.EDU.MX', '2000-10-01', 1, 'Activo'),
('201823415', '201823415', '1999-09-05', 1, 'Activo'),
('201823417', '201823417@TESE.EDU.MX', '1999-05-27', 4, 'Activo'),
('201823456', '201823456@TESE.EDU.MX', '2000-11-03', 0, 'Activo'),
('201823819', '201823819@TESE.EDU.MX', '2000-07-15', 4, 'Activo'),
('201824098', '201824098@TESE.EDU.MX', '2000-04-02', 0, 'Activo'),
('201911233', '201911233@TESE.EDU.MX', '1998-07-22', 4, 'Activo'),
('202222345', '202222345@TESE.EDU.MX', '2003-01-26', 7, 'Activo'),
('20811433', '20811433@TESE.EDU.MX', '1995-01-21', 7, 'Activo'),
('2201821943', '201821943@TESE.EDU,MX', '1999-05-27', 2, 'Activo'),
('5555', 'externo@tese.edu.mx', '1997-10-10', 1, 'Activo'),
('999999', '44@tese.edu.mx', '1998-11-10', 0, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carreras`
--

CREATE TABLE `carreras` (
  `clave` int(11) NOT NULL DEFAULT 0,
  `nombrecarrera` varchar(50) NOT NULL,
  `estadoc` varchar(10) DEFAULT 'Activa'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carreras`
--

INSERT INTO `carreras` (`clave`, `nombrecarrera`, `estadoc`) VALUES
(0, 'Carrera en espera', ''),
(1, 'ingenieria   informatica', 'Activa'),
(2, 'sistemas computacionales', 'Activa'),
(3, 'ingenieria quimica', 'Activa'),
(4, 'ingenieria industrial', 'Activa'),
(5, 'contabilidad', 'Activa'),
(6, 'bioquimica', 'Activa'),
(7, 'ingenieria robotica', 'Activa'),
(8, 'gestion empresarial', 'Activa'),
(9, 'INGENIERIA ELECTRONICA', 'Activa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `correo` varchar(256) NOT NULL COMMENT 'campo llave',
  `nombre` varchar(100) NOT NULL,
  `pw` int(20) NOT NULL,
  `estadou` varchar(10) DEFAULT 'Activo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`correo`, `nombre`, `pw`, `estadou`) VALUES
('1111111111@TESE.EDU.MX', 'ALEJANDRA VALENCIA', 2494, 'Activo'),
('201621433@tese.edu.mx', 'VENTURA ZUNIGA', 369, 'Activo'),
('201720056@TESE.EDU.MX', 'DANIELA BUCIO', 196, 'Activo'),
('2018123456@tese.edu.mx', 'DANNA DIAZ', 2302, 'Activo'),
('201821117@TESE.EDU,MX', 'Garcia filemon areli', 123499, 'Inactivo'),
('201821117@TESE.EDU.MX', 'DANIELA RIOS', 114, 'Activo'),
('201821158@TESE:EDU:MX', 'ENRIQUE REYES', 789, 'Activo'),
('201821234@TESE.EDU.MX', 'JORGE PEREZ', 427, 'Activo'),
('201821501@TESE.EDU.MX', 'GABRIEL SUAREZ', 654, 'Activo'),
('201821938@TESE.EDU.MX', 'IVAN OROPEZA', 987, 'Activo'),
('201821943@TESE.EDU,MX ', 'FERNANDO O', 1111, 'Activo'),
('201823346@TESE.EDU.MX', 'XIMENA RIOS', 357, 'Activo'),
('201823415', 'ANA LAURA DIAZ', 127, 'Activo'),
('201823417@TESE.EDU.MX', 'ese men', 55, 'Activo'),
('201823456@TESE.EDU.MX', 'VANESSA PEREZ LOPEZ', 561, 'Activo'),
('201823819@TESE.EDU.MX', 'RUBEN RAMIREZ', 555, 'Activo'),
('201824098@TESE.EDU.MX', 'LILIANA TAKEDA', 654, 'Activo'),
('201911233@TESE.EDU.MX', 'MAURICIO PALOMERO', 12345, 'Activo'),
('202222345@TESE.EDU.MX', 'NOHEMI LUNA', 2703, 'Activo'),
('20811433@TESE.EDU.MX', 'JESUS LUNA BECERRA', 2512, 'Activo'),
('44@tese.edu.mx', 'REGINA ARIZAGA', 2712, 'Activo'),
('5555555555@TESE.EDU.MX', 'MINERVA VILLEGAS GUTIERREZ', 341, 'Activo'),
('diego.com', 'DIEGO HERNANDEZ', 6789, 'Activo'),
('externo@tese.edu.mx', 'el nombre del humano 3', 12345, 'Activo'),
('FERNANDA@GMAIL.COM', 'MARIA FERNANDA TREJO', 134, 'Activo');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vistaalumnos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vistaalumnos` (
`estadou` varchar(10)
,`estado` varchar(50)
,`correo` varchar(256)
,`nombre` varchar(100)
,`pw` int(20)
,`matricula` varchar(10)
,`f_nacimiento` date
,`nombrecarrera` varchar(50)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `vistaalumnos`
--
DROP TABLE IF EXISTS `vistaalumnos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vistaalumnos`  AS SELECT `u`.`estadou` AS `estadou`, `a`.`estado` AS `estado`, `u`.`correo` AS `correo`, `u`.`nombre` AS `nombre`, `u`.`pw` AS `pw`, `a`.`matricula` AS `matricula`, `a`.`f_nacimiento` AS `f_nacimiento`, `c`.`nombrecarrera` AS `nombrecarrera` FROM ((`usuarios` `u` join `alumnos` `a` on(`a`.`correo` = `u`.`correo`)) join `carreras` `c` on(`a`.`carrera` = `c`.`clave`))  ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`matricula`),
  ADD KEY `correo` (`correo`),
  ADD KEY `carrera` (`carrera`);

--
-- Indices de la tabla `carreras`
--
ALTER TABLE `carreras`
  ADD PRIMARY KEY (`clave`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`correo`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD CONSTRAINT `alumnos_ibfk_1` FOREIGN KEY (`carrera`) REFERENCES `carreras` (`clave`),
  ADD CONSTRAINT `alumnos_ibfk_2` FOREIGN KEY (`correo`) REFERENCES `usuarios` (`correo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
