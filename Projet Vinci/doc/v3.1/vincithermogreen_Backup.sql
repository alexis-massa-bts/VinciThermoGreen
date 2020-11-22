-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2020 at 10:59 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vincithermogreen`
--

-- --------------------------------------------------------

--
-- Table structure for table `mesure`
--

CREATE TABLE `mesure` (
  `num_zone` int(1) DEFAULT NULL,
  `horodate` datetime DEFAULT NULL,
  `fahrenheit` float DEFAULT NULL,
  `id_stade` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mesure`
--

INSERT INTO `mesure` (`num_zone`, `horodate`, `fahrenheit`, `id_stade`) VALUES
(4, '2020-02-02 08:10:32', 51.17, 4),
(3, '2020-11-29 11:14:30', 52.1, 5),
(4, '2020-01-13 17:44:39', 52.84, 1),
(2, '2021-05-01 03:36:07', 50.76, 4),
(4, '2021-03-13 17:35:02', 50.8, 3),
(2, '2020-09-27 05:17:13', 52.68, 5),
(2, '2021-06-23 04:05:38', 53.35, 5),
(4, '2020-12-02 13:10:01', 51.96, 3),
(3, '2020-02-27 16:26:10', 51.04, 3),
(3, '2020-11-08 08:38:42', 52.58, 2),
(3, '2021-01-23 03:42:50', 50.3, 1),
(2, '2021-05-02 15:44:06', 50.04, 1),
(3, '2020-10-02 20:44:53', 53.64, 3),
(2, '2020-03-25 04:05:16', 51.06, 3),
(3, '2021-02-26 03:26:05', 51.67, 1),
(3, '2021-04-23 02:49:14', 50.47, 4),
(2, '2020-06-22 20:46:38', 51.39, 1),
(2, '2020-06-25 06:45:51', 53.26, 1),
(4, '2019-11-29 20:33:42', 50.47, 2),
(1, '2021-03-01 20:35:46', 52.06, 1),
(1, '2020-03-30 18:30:57', 52.33, 5),
(4, '2020-01-12 17:41:27', 50.74, 4),
(1, '2020-10-30 07:01:54', 51.5, 1),
(4, '2020-04-05 10:42:23', 53.21, 1),
(4, '2020-08-29 00:44:43', 53.81, 1),
(3, '2019-11-03 11:48:40', 50.73, 5),
(2, '2020-07-08 11:15:03', 50.84, 1),
(3, '2021-07-25 01:00:36', 52.88, 2),
(1, '2020-10-25 20:39:54', 51.73, 5),
(2, '2021-02-06 22:58:29', 50.67, 1),
(3, '2020-01-21 06:18:57', 53.36, 1),
(4, '2021-04-13 00:21:28', 53.04, 5),
(1, '2020-04-18 11:01:38', 52.6, 5),
(1, '2020-03-01 11:40:15', 51.91, 2),
(1, '2021-06-05 11:29:59', 50.08, 5),
(2, '2020-11-18 09:39:15', 50.04, 3),
(2, '2020-11-25 08:17:00', 50.27, 3),
(4, '2019-10-22 00:22:11', 52.09, 5),
(3, '2021-08-09 01:27:33', 51.28, 1),
(1, '2020-03-13 14:49:12', 53.25, 1),
(4, '2021-08-19 13:45:54', 50.58, 1),
(3, '2021-03-05 19:03:10', 50, 5),
(3, '2020-02-04 03:30:20', 51.29, 2),
(3, '2021-05-08 17:21:16', 52.34, 3),
(2, '2020-09-05 13:26:49', 52.27, 2),
(3, '2020-09-02 11:41:08', 53.14, 1),
(4, '2021-05-30 05:35:44', 52.04, 4),
(1, '2021-01-01 09:50:34', 52.13, 4),
(1, '2020-04-22 01:28:27', 52.79, 1),
(1, '2021-09-10 12:57:33', 50.77, 3),
(2, '2021-05-19 18:30:32', 52.73, 4),
(1, '2021-05-25 19:16:20', 50.09, 1),
(2, '2021-09-05 05:59:30', 51.72, 4),
(2, '2020-03-15 18:34:40', 53.12, 1),
(4, '2020-12-11 01:26:22', 51.21, 3),
(4, '2020-05-08 16:12:13', 50.25, 4),
(2, '2019-11-13 22:31:37', 51.14, 2),
(1, '2020-07-12 10:15:40', 53.78, 2),
(1, '2020-08-27 15:03:40', 53.34, 4),
(4, '2021-10-09 19:20:57', 53.62, 3),
(2, '2020-12-14 13:53:23', 52.83, 4),
(2, '2021-07-10 03:10:29', 50.97, 3),
(3, '2019-11-07 18:50:43', 52.15, 1),
(3, '2021-06-18 00:14:44', 51, 3),
(1, '2020-11-27 17:37:29', 52.94, 1),
(2, '2021-05-28 23:19:10', 50.17, 3),
(1, '2021-06-14 02:53:11', 51.92, 3),
(1, '2020-08-11 21:36:15', 50.13, 1),
(1, '2020-02-22 04:29:04', 52.9, 4),
(3, '2020-02-08 15:22:05', 52.41, 5),
(4, '2020-05-15 12:41:08', 51.32, 4),
(2, '2019-11-29 13:50:25', 52.07, 4),
(1, '2021-05-17 09:31:27', 53.89, 5),
(4, '2021-03-16 07:41:44', 51.91, 3),
(1, '2021-02-11 17:55:56', 51.23, 2),
(3, '2020-06-12 04:06:15', 53.34, 3),
(1, '2019-12-03 15:52:40', 52.98, 3),
(2, '2020-09-08 01:51:40', 53.69, 3),
(1, '2019-11-26 12:11:31', 50.83, 2),
(2, '2020-11-17 08:19:08', 51.27, 2),
(1, '2021-09-08 13:36:13', 52.38, 5),
(3, '2019-12-20 23:20:54', 53.4, 4),
(1, '2021-04-22 13:31:01', 50.5, 2),
(1, '2021-10-18 20:21:19', 53.15, 5),
(1, '2021-03-02 23:45:16', 53.39, 3),
(1, '2021-05-05 18:28:24', 50.02, 4),
(2, '2020-01-19 22:32:24', 53.44, 4),
(4, '2019-11-24 06:13:20', 53.13, 5),
(2, '2020-08-27 20:59:54', 52.32, 2),
(3, '2020-12-03 09:15:51', 50.21, 1),
(1, '2021-09-27 03:08:52', 51.42, 1),
(1, '2021-02-28 15:40:13', 51.45, 1),
(1, '2020-06-17 20:33:30', 51.73, 1),
(3, '2021-03-19 04:44:37', 52.37, 5),
(1, '2020-10-08 18:25:28', 53.58, 3),
(2, '2020-10-04 03:30:41', 50.18, 1),
(2, '2020-11-26 21:49:56', 52.46, 2),
(1, '2021-06-28 22:36:47', 52.62, 2),
(1, '2021-09-12 05:19:05', 52.82, 4),
(1, '2019-12-29 22:00:25', 53.34, 4);

-- --------------------------------------------------------

--
-- Table structure for table `stade`
--

CREATE TABLE `stade` (
  `id_stade` int(11) NOT NULL,
  `nom_stade` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stade`
--

INSERT INTO `stade` (`id_stade`, `nom_stade`) VALUES
(1, 'Stade Vélodrome'),
(2, 'Parc Olympique Lyonnais'),
(3, 'Stade Pierre-Mauroy'),
(4, 'Parc des Princes'),
(5, 'Matmut Atlantique');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `login` varchar(15) NOT NULL,
  `password` text NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role` tinyint(1) NOT NULL COMMENT 'true : admin'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`login`, `password`, `name`, `surname`, `email`, `role`) VALUES
('amassa', '$2a$10$ga0sRsIrTXvpJdmgV2II0uvFSm6Cm57YMOD0OjoCD5PDvQpMX9fyq', 'Alexis', 'Massa', 'alexis.massa351@gmail.com', 1),
('pdupont', '$2a$10$3NxUsoqG624O2HeEAJM/4ef8rM2eDzVRxtPQ3IQkMpprezNq9EZr2', 'Pierre', 'Dupont', 'pierre.dupont462@gmail.com', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mesure`
--
ALTER TABLE `mesure`
  ADD KEY `id_stade` (`id_stade`);

--
-- Indexes for table `stade`
--
ALTER TABLE `stade`
  ADD PRIMARY KEY (`id_stade`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`login`),
  ADD UNIQUE KEY `login` (`login`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mesure`
--
ALTER TABLE `mesure`
  ADD CONSTRAINT `mesure_ibfk_1` FOREIGN KEY (`id_stade`) REFERENCES `stade` (`id_stade`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
