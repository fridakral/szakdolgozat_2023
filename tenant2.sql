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
-- Adatbázis: `tenant2`
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
(10, NULL, NULL, NULL, 0, 'Péter', NULL, 'Példa', '$2a$10$e63eFvSHWN3Zdk2cyr/Rm.fH.JK.EP1NmadkJeBqaOwGqm9olQNeO', NULL, NULL, NULL, 'peldapeter', b'1', 'pelda@tenant2.asd'),
(23, NULL, NULL, NULL, 2, 'asd', NULL, 'asd', '$2a$10$ruu8p1hm6BFNO.R8Bj5sneirLkXAAF2lN9FsICQ6j4JiP4u639mtC', NULL, NULL, NULL, 'asd', b'0', 'asd@asd.asd'),
(24, NULL, NULL, NULL, 2, 'asd', NULL, 'asd', '$2a$10$CqU1Plonp9VXf9wXNPsZ.eAHafMRCDt8wjJ1tQawOyaE9hVUZjZPq', NULL, NULL, NULL, 'asdasd', b'0', 'asd@asd.asd');

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
(25);

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
(13, 'Tasks', 1, 12),
(14, 'In Progress', 2, 12),
(15, 'Done', 3, 12),
(18, 'Tasks', 1, 17),
(19, 'In Progress', 2, 17),
(20, 'Done', 3, 17);

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
(12, '2023-04-06 15:15:33', NULL, '2024-12-03 01:00:00', 'proba projekt 3'),
(17, '2023-04-06 17:49:49', NULL, '2024-04-06 17:49:49', 'ez az első projektem');

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
(11, b'1', 10, 12),
(16, b'1', 10, 17);

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
(21, '2023-04-06 17:51:53', 'ez egy próba task, egy próba leírással', '2024-06-05 02:00:00', 'első task', 18, 1),
(22, '2023-04-06 17:53:11', 'ez egy próba task, egy próba leírással', '2024-06-05 02:00:00', 'második task', 18, 2);

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

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `list`
--
ALTER TABLE `list`
  ADD CONSTRAINT `FK2pkwn2v9i8rqrgpb4u0yvmw6j` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`);

--
-- Megkötések a táblához `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `FK41ymktprubsei7vgbp2g0d9ya` FOREIGN KEY (`project`) REFERENCES `projects` (`id`),
  ADD CONSTRAINT `FKd1ci4kpviy38ak4fw4bg3rq4f` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`);

--
-- Megkötések a táblához `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `FKbbdgikos8t9ilk4mdpqkx55m1` FOREIGN KEY (`list_id`) REFERENCES `list` (`id`);

--
-- Megkötések a táblához `task_employee`
--
ALTER TABLE `task_employee`
  ADD CONSTRAINT `FK4n5dexaeqe4fjmjyhcyxefbgf` FOREIGN KEY (`TaskEntity_id`) REFERENCES `task` (`id`),
  ADD CONSTRAINT `FKp73vhx154ri20js1bgglyjpcs` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
