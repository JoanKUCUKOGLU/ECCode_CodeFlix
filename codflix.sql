-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 25 juin 2020 à 20:46
-- Version du serveur :  10.4.13-MariaDB
-- Version de PHP : 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `codflix`
--

-- --------------------------------------------------------

--
-- Structure de la table `episode`
--

CREATE TABLE `episode` (
  `id` int(11) NOT NULL,
  `media_id` int(11) NOT NULL,
  `saison_index` int(11) NOT NULL,
  `episode_index` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `duration` int(11) NOT NULL,
  `summary` longtext NOT NULL,
  `url` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `episode`
--

INSERT INTO `episode` (`id`, `media_id`, `saison_index`, `episode_index`, `name`, `duration`, `summary`, `url`) VALUES
(1, 4, 1, 1, 'Daphné', 7, 'Episode 1 - Daphné', 'https://www.youtube.com/embed/DcHTaMenp-o?autoplay=true'),
(2, 4, 1, 2, 'Gaëtan', 5, 'Episode 2 - Gaëtan', 'https://www.youtube.com/embed/MBmdFFjGEIA?autoplay=true\r\n'),
(3, 4, 1, 3, 'Claire', 9, 'Episode 3 - Claire', 'https://www.youtube.com/embed/8Y8nNeb3SOA?autoplay=true\r\n'),
(4, 4, 1, 4, 'Franck', 5, 'Episode 4 - Franck', 'https://www.youtube.com/embed/OVP2MZET9TQ?autoplay=true\r\n'),
(5, 4, 1, 5, 'Bruno', 6, 'Episode 5 - Bruno', 'https://www.youtube.com/embed/JTC97YMXTPc?autoplay=true'),
(6, 4, 1, 6, 'Elsa', 8, 'Episode 6 - Elsa', 'https://www.youtube.com/embed/MsrybaWje9U?autoplay=true\r\n'),
(7, 4, 1, 7, 'Alex', 9, 'Episode 7 - Alex', 'https://www.youtube.com/embed/9TfyHsLFuxQ?autoplay=true'),
(8, 5, 1, 1, 'Episode 1', 22, 'Groom - Episode 1', 'https://www.youtube.com/embed/_sWYGycsXpE'),
(9, 5, 1, 2, 'Episode 2', 23, 'Groom - Episode 2', 'https://www.youtube.com/embed/-v8PkPLZsB0?autoplay=true'),
(10, 5, 1, 3, 'Episode 3', 21, 'Groom - Episode 3', 'https://www.youtube.com/embed/QDfGd-ak8M4?autoplay=true'),
(11, 5, 1, 4, 'Episode 4', 22, 'Groom - Episode 4', 'https://www.youtube.com/embed/fvJr2bfR0fo?autoplay=true'),
(12, 5, 1, 5, 'Episode 5', 25, 'Groom - Episode 5', 'https://www.youtube.com/embed/WH60eiaexdk?autoplay=true'),
(13, 5, 1, 6, 'Episode 6', 20, 'Groom - Episode 6', 'https://www.youtube.com/embed/tNEB_ZBGuUg?autoplay=true'),
(14, 5, 1, 7, 'Episode 7', 24, 'Groom - Episode 7', 'https://www.youtube.com/embed/r0fKNQyREJk?autoplay=true'),
(15, 5, 1, 8, 'Episode 8', 20, 'Groom - Episode 8', 'https://www.youtube.com/embed/2EjoQcOnmQ4?autoplay=true'),
(16, 5, 1, 9, 'Episode 9', 23, 'Groom - Episode 9', 'https://www.youtube.com/embed/jybxfALZ710?autoplay=true'),
(17, 5, 1, 10, 'Episode 10', 24, 'Groom - Episode 10', 'https://www.youtube.com/embed/KMgZ08tRHTs?autoplay=true'),
(18, 5, 2, 1, 'Bébé Requin', 23, 'Groom - Episode 1', 'https://www.youtube.com/embed/3vbW6dtUYcU?autoplay=true'),
(19, 5, 2, 2, 'Double Casquette', 26, 'Groom - Episode 2', 'https://www.youtube.com/embed/r4FCW4Zr7_8?autoplay=true'),
(20, 5, 2, 3, 'Mission Impossible', 25, 'Groom - Episode 3', 'https://www.youtube.com/embed/DysYZiLFc6s?autoplay=true'),
(21, 5, 2, 4, 'Scout Toujours', 25, 'Groom - Episode 4', 'https://www.youtube.com/embed/OCqCMz6pBrM?autoplay=true'),
(22, 5, 2, 5, 'Les Pour les Contres', 21, 'Groom - Episode 5', 'https://www.youtube.com/embed/9545qxo8QEo?autoplay=true'),
(23, 5, 2, 6, 'Résiste', 25, 'Groom - Episode 6', 'https://www.youtube.com/embed/jshZSPU3atk?autoplay=true'),
(24, 5, 2, 7, 'Hasta la vista', 26, 'Groom - Episode 7', 'https://www.youtube.com/embed/4ZVJscP3u4E?autoplay=true'),
(25, 5, 2, 8, 'Pizza', 22, 'Groom - Episode 8', 'https://www.youtube.com/embed/EMz9FOK2CzA?autoplay=true'),
(26, 5, 2, 9, 'Business Family', 22, 'Groom - Episode 9', 'https://www.youtube.com/embed/vGcJ8DziiF4?autoplay=true'),
(27, 5, 2, 10, 'Bingo', 22, 'Groom - Episode 10', 'https://www.youtube.com/embed/8KxDR24t7vs?autoplay=true');

-- --------------------------------------------------------

--
-- Structure de la table `genre`
--

CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `genre`
--

INSERT INTO `genre` (`id`, `name`) VALUES
(0, 'Action'),
(1, 'Aventure'),
(2, 'Science-Fiction'),
(3, 'Fantastique'),
(4, 'Comédie'),
(5, 'Drame');

-- --------------------------------------------------------

--
-- Structure de la table `history`
--

CREATE TABLE `history` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `media_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `finish_date` datetime DEFAULT NULL,
  `watch_duration` int(11) NOT NULL DEFAULT 0 COMMENT 'in seconds'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `media`
--

CREATE TABLE `media` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `type_id` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `release_date` date NOT NULL,
  `summary` longtext NOT NULL,
  `duration` int(11) NOT NULL,
  `img_url` varchar(150) NOT NULL,
  `trailer_url` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `media`
--

INSERT INTO `media` (`id`, `title`, `type_id`, `status`, `release_date`, `summary`, `duration`, `img_url`, `trailer_url`) VALUES
(0, 'Transformers', 1, 'published', '2007-07-25', 'Une guerre sans merci oppose depuis des temps immémoriaux deux races de robots extraterrestres : les Autobots et les cruels Decepticons. Son enjeu : la maîtrise de l\'univers. Dans les premières années du 21ème siècle, le conflit s\'étend à la Terre, et le jeune Sam Witwicky devient, à son insu, l\'ultime espoir de l\'humanité. Semblable à des milliers d\'adolescents, Sam n\'a connu que les soucis de son âge : le lycée, les amis, les voitures, les filles.\r ', 144, 'https://fr.web.img2.acsta.net/medias/nmedia/18/62/81/09/18784915.jpg', 'https://www.youtube.com/embed/F1lHPqLdfms?autoplay=true'),
(1, 'Les Tuche', 1, 'published', '2011-07-01', 'A Bouzolles, tout le monde connaît la famille Tuche. Jeff, Cathy et leurs trois enfants vivent du système D. Respectueuse de la philosophie Tuche, l\'homme n\'est pas fait pour travailler, toute la famille s\'emploie à être heureuse malgré le cruel manque de revenus. Leurs vies étaient toutes tracées. Ils seraient toujours pauvres, mais heureux.', 95, 'https://fr.web.img2.acsta.net/medias/nmedia/18/79/51/22/19732939.jpg', 'https://www.youtube.com/embed/0100xOECrJA?autoplay=true'),
(2, '13 Reasons Why', 3, 'terminée', '2017-03-31', 'Clay Jensen découvre sous son porche au retour du lycée une mystérieuse boîte portant son nom. À l\'intérieur, des cassettes enregistrées par Hannah Baker, une camarade de classe qui s\'est tragiquement suicidée deux semaines auparavant.', 0, 'https://media.senscritique.com/media/000016743844/source_big/13_Reasons_Why.jpg', 'https://www.youtube.com/embed/LVVMvRpmu0s?autoplay=true'),
(3, 'Bref.', 3, 'terminée', '2011-08-29', 'Les chroniques extraordinaires d’un homme ordinaire. Dans la vie, au début on naît, à la fin on meurt, pendant ce temps là, il se passe des trucs. Bref, c\'est la vie d\'un mec pendant ce temps là.', 0, 'https://fr.web.img6.acsta.net/pictures/19/10/17/07/59/3926800.jpg', 'https://www.youtube.com/embed/UO8tcf3U0dY?autoplay=true'),
(4, 'AREL3', 2, 'terminée', '2018-04-27', 'Plusieurs individus n’ayant rien en commun se réveillent dans une pièce sans portes ni murs, sans savoir où ils sont ni pourquoi. Ils n’ont jamais faim, jamais soif, ne sont jamais fatigués. Ils n’ont absolument aucune indication de quoi faire ni de contact avec l’extérieur. Le seul indice à leur disposition: à intervalles très irrégulières, la lumière blanche, provenant du plafond, tourne au rouge, puis au noir complet, avant de se rallumer selon le procédé inverse.', 0, 'https://i2.wp.com/lonelydarkmeeple.fr/wp-content/uploads/2018/07/AREL3_Header.jpg?resize=600%2C432&ssl=1', 'https://www.youtube.com/embed/DcHTaMenp-o?autoplay=true'),
(5, 'Groom', 2, 'terminée', '2018-09-19', 'William est le fils héritier du richissime propriétaire d’une chaîne d’hôtel internationale. Enfant gâté habitué au luxe depuis tout petit et n’ayant jamais eu à faire le moindre effort pour avoir ce qu’il voulait, tout change pour William quand le soir de ses 30 ans qu’il fête avec faste et décadence dans un hôtel de son père, il blesse le groom de l’hôtel après un pari stupide avec ses amis. C’est la goutte d’eau qui fait déborder le vase pour son père qui décide de lui couper les vivres en lui fixant un ultimatum, soit William remplace son groom, soit il le déshérite.', 0, 'https://fr.web.img5.acsta.net/pictures/18/09/10/12/14/5008825.jpg', 'https://www.youtube.com/embed/TO6d-14LQH4?autoplay=true');

-- --------------------------------------------------------

--
-- Structure de la table `media_genre`
--

CREATE TABLE `media_genre` (
  `media_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `media_genre`
--

INSERT INTO `media_genre` (`media_id`, `genre_id`) VALUES
(0, 0),
(0, 1),
(0, 2),
(0, 3),
(1, 4),
(4, 5),
(5, 4);

-- --------------------------------------------------------

--
-- Structure de la table `media_type`
--

CREATE TABLE `media_type` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `media_type`
--

INSERT INTO `media_type` (`id`, `name`) VALUES
(1, 'Films'),
(2, 'Séries'),
(3, 'OUT');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(254) NOT NULL,
  `password` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `password`) VALUES
(1, 'joankucukoglu@gmail.com', '99004cefbcb74f559a892bd1475f1ed50009c661314e2bd2c187ef7297a625ad'),
(2, 'coding@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `episode`
--
ALTER TABLE `episode`
  ADD PRIMARY KEY (`id`),
  ADD KEY `media_id` (`media_id`);

--
-- Index pour la table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `history_user_id_fk_media_id` (`user_id`),
  ADD KEY `history_media_id_fk_media_id` (`media_id`);

--
-- Index pour la table `media`
--
ALTER TABLE `media`
  ADD PRIMARY KEY (`id`),
  ADD KEY `type_id` (`type_id`);

--
-- Index pour la table `media_genre`
--
ALTER TABLE `media_genre`
  ADD KEY `media_id` (`media_id`),
  ADD KEY `genre_id` (`genre_id`);

--
-- Index pour la table `media_type`
--
ALTER TABLE `media_type`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `episode`
--
ALTER TABLE `episode`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `history`
--
ALTER TABLE `history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `media`
--
ALTER TABLE `media`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `media_type`
--
ALTER TABLE `media_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `episode`
--
ALTER TABLE `episode`
  ADD CONSTRAINT `episode_ibfk_1` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`);

--
-- Contraintes pour la table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `history_media_id_fk_media_id` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `history_user_id_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `media`
--
ALTER TABLE `media`
  ADD CONSTRAINT `media_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `media_type` (`id`);

--
-- Contraintes pour la table `media_genre`
--
ALTER TABLE `media_genre`
  ADD CONSTRAINT `media_genre_ibfk_1` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `media_genre_ibfk_2` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
