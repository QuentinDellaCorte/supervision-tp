-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 29 Novembre 2018 à 08:25
-- Version du serveur :  5.7.24
-- Version de PHP :  5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `snmp`
--

-- --------------------------------------------------------

--
-- Structure de la table `Device`
--

CREATE TABLE IF NOT EXISTS `Device` (
  `id_device` int(11) NOT NULL,
  `device_name` varchar(100) NOT NULL,
  `device_type` varchar(100) DEFAULT NULL,
  `Ipadresse` varchar(100) DEFAULT NULL,
  `community` varchar(100) DEFAULT NULL,
  `oid` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Device`
--

INSERT INTO `Device` (`id_device`, `device_name`, `device_type`, `Ipadresse`, `community`, `oid`) VALUES
(1, 'is25sw01a', 'SWITCH', '192.168.140.143', 'univ-1', '1.3.6.1.2.1.2.2.1.10.1'),
(2, 'mau-nas1', 'NAS', '192.168.176.2', 'passprojet', '1.3.6.1.2.1.2.2.1.10.1');

-- --------------------------------------------------------

--
-- Structure de la table `Logs`
--

CREATE TABLE IF NOT EXISTS `Logs` (
  `id_log` int(11) NOT NULL,
  `id_device` int(11) NOT NULL,
  `log` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Logs`
--

INSERT INTO `Logs` (`id_log`, `id_device`, `log`) VALUES
(1, 1, 'une ligne de log du device id 1 = is25sw01a '),
(2, 2, 'voici une ligne de log du device 2 = mau-nas1');

-- --------------------------------------------------------

--
-- Structure de la table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `id_user` int(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Users`
--

INSERT INTO `Users` (`id_user`, `login`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'user', 'user', 'user');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Device`
--
ALTER TABLE `Device`
  ADD PRIMARY KEY (`id_device`);

--
-- Index pour la table `Logs`
--
ALTER TABLE `Logs`
  ADD PRIMARY KEY (`id_log`),
  ADD KEY `FK_Device` (`id_device`);

--
-- Index pour la table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Logs`
--
ALTER TABLE `Logs`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `Users`
--
ALTER TABLE `Users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Logs`
--
ALTER TABLE `Logs`
  ADD CONSTRAINT `FK_Device` FOREIGN KEY (`id_device`) REFERENCES `Device` (`id_device`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
