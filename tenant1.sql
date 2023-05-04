-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Máj 04. 13:33
-- Kiszolgáló verziója: 10.4.24-MariaDB
-- PHP verzió: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `tenant1`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `employee`
--

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `bankAccountNumber` varchar(255) DEFAULT NULL,
  `dateOfBirth` datetime DEFAULT NULL,
  `employeeRole` int(11) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `identityCardNumber` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `placeOfBirth` varchar(255) DEFAULT NULL,
  `post` varchar(255) DEFAULT NULL,
  `postDescription` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `isVerified` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `employee`
--

INSERT INTO `employee` (`id`, `address`, `bankAccountNumber`, `dateOfBirth`, `employeeRole`, `firstName`, `identityCardNumber`, `lastName`, `password`, `placeOfBirth`, `post`, `postDescription`, `username`, `isVerified`, `email`) VALUES
(1, NULL, '0000000000000000', '1990-06-28 00:00:00', 0, 'Péter', '000000AA', 'Példa', '$2a$10$CNLXc3nuBfdx2lsVOmk4rOMgybWC.QyEQhjFfo14ABuMO6vloDGEa', 'Ököritófülpös', 'CEO', 'Vezetem a vállalkozást, főzöm a kávét a fejlesztőknek.', 'peldapeter', b'1', 'pelda@peter.hu'),
(2, NULL, NULL, NULL, 2, 'Petra', NULL, 'Próba', '$2a$10$RwH9H5VB1aQhIy4YY3BTuOwMEOedgPZFa.QRK19uuk0oSEhi7E9sG', NULL, NULL, NULL, 'probapetra', b'1', 'proba@petra.hu');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(12);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `list`
--

CREATE TABLE `list` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `list`
--

INSERT INTO `list` (`id`, `name`, `number`, `project_id`) VALUES
(5, 'Tasks', 1, 4),
(6, 'In Progress', 2, 4),
(7, 'Done', 3, 4);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `projects`
--

CREATE TABLE `projects` (
  `id` bigint(20) NOT NULL,
  `createdOn` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `projects`
--

INSERT INTO `projects` (`id`, `createdOn`, `description`, `dueDate`, `name`) VALUES
(4, '2023-05-04 13:07:09', NULL, '2024-05-04 13:07:09', 'projekt1');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `manager` bit(1) DEFAULT NULL,
  `employee` bigint(20) DEFAULT NULL,
  `project` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `role`
--

INSERT INTO `role` (`id`, `manager`, `employee`, `project`) VALUES
(3, b'1', 1, 4),
(9, b'0', 2, 4);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `task`
--

CREATE TABLE `task` (
  `id` bigint(20) NOT NULL,
  `createdOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `list_id` bigint(20) DEFAULT NULL,
  `serial` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `task`
--

INSERT INTO `task` (`id`, `createdOn`, `description`, `dueDate`, `name`, `list_id`, `serial`) VALUES
(8, '2023-05-04 13:23:59', 'Elmosogatni a bögréket', '2023-05-05 00:00:00', 'Az első feladat', 5, 1),
(10, '2023-05-04 13:26:09', 'Meleg van, kajakómásak vagyunk', '2024-06-05 02:00:00', 'Bevezetni a sziesztát az irodában', 5, 1),
(11, '2023-05-04 13:27:16', 'Szakdolgozatnak talán jó lesz :(', '2024-06-05 02:00:00', 'Chh, tele van bug-gal ez a program', 6, 1);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `task_employee`
--

CREATE TABLE `task_employee` (
  `TaskEntity_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `task_employee`
--

INSERT INTO `task_employee` (`TaskEntity_id`, `employee_id`) VALUES
(8, 2),
(8, 2);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_v9p52k4owkd6pgqpiw3r2ovl` (`username`);

--
-- A tábla indexei `list`
--
ALTER TABLE `list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2pkwn2v9i8rqrgpb4u0yvmw6j` (`project_id`);

--
-- A tábla indexei `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd1ci4kpviy38ak4fw4bg3rq4f` (`employee`),
  ADD KEY `FK41ymktprubsei7vgbp2g0d9ya` (`project`);

--
-- A tábla indexei `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbbdgikos8t9ilk4mdpqkx55m1` (`list_id`);

--
-- A tábla indexei `task_employee`
--
ALTER TABLE `task_employee`
  ADD KEY `FKp73vhx154ri20js1bgglyjpcs` (`employee_id`),
  ADD KEY `FK4n5dexaeqe4fjmjyhcyxefbgf` (`TaskEntity_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
