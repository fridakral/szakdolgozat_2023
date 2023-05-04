-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Ápr 17. 09:32
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
(9, NULL, NULL, NULL, 2, 'F', NULL, 'F', '$2a$10$W/IxIWephAUXqPLqTDSlouIrdshE7Rud3UikBtYXH1QbmVkm74BEi', NULL, NULL, NULL, 'managerrr', b'1', 'lokospatrik8@gmail.com'),
(10, NULL, NULL, NULL, 0, 'Friderika', NULL, 'Král', '$2a$10$691xw89aJRnAWwPnnv.u5eTPpj7uVD4EmTePMIaS15pTNseenKhmq', NULL, NULL, NULL, 'fridus', b'1', 'kralfrida@gmail.com'),
(12, NULL, NULL, NULL, 2, 'asdasd', NULL, 'asdasd', '$2a$10$aiYCh8WMxn6jwngwrsY9/eeO4I9oxPFPOUHJBnjb5vPTkUoE/ubFy', NULL, NULL, NULL, 'asdasd', b'1', 'asdasd@asd.hu'),
(13, NULL, NULL, NULL, 0, 'Friderika', NULL, 'Král', '$2a$10$9hB6f8QGEA7seA.iMQr/k.KgjlAdkAbckAWIDwjx3BjbmsQoYZ7mu', NULL, NULL, NULL, 'kralfrida', b'1', 'kralfrida@gmail.com'),
(120, NULL, NULL, NULL, 2, 'asd', NULL, 'asd', '$2a$10$6htRUTVxVnh.eAARxn3RcuKZK9ogyTRNGbXRBDTnzBqdf4JxyFqIS', NULL, NULL, NULL, 'dsa', b'1', 'asd@asd.asd');

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
(157);

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
(117, 'Tasks', 1, 116),
(118, 'In Progress', 2, 116),
(119, 'Done', 3, 116),
(152, 'Tasks', 1, 151),
(153, 'In Progress', 2, 151),
(154, 'Done', 3, 151);

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
(116, '2023-04-07 13:33:17', NULL, '2024-04-07 13:33:16', 'sadasd'),
(151, '2023-04-11 20:48:45', NULL, '2024-04-11 20:48:45', 'asd');

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
(115, b'1', 13, 116),
(150, b'1', 13, 151);

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
(147, '2023-04-11 17:46:28', 'ez egy próba task, egy próba leírással', '2024-06-05 02:00:00', 'b', 117, 1),
(148, '2023-04-11 17:46:30', 'ez egy próba task, egy próba leírással', '2024-06-05 02:00:00', 'c', 117, 2),
(149, '2023-04-11 20:24:12', 'ez egy próba task, egy próba leírással', '2024-06-05 02:00:00', 'asdasdasdadasds', 117, 3),
(155, '2023-04-14 21:38:09', 'ez egy próba task, egy próba leírással', '2024-06-05 02:00:00', 'asd', 117, 4),
(156, '2023-04-14 21:43:59', 'ez egy próba task, egy próba leírással', '2024-06-05 02:00:00', 'adsadsasd', 117, 5);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `task_employee`
--

CREATE TABLE `task_employee` (
  `TaskEntity_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
