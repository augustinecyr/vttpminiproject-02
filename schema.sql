CREATE USER 'footrix' @'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'footrix' @'localhost' WITH
GRANT OPTION;
drop database if exists footrix;
create database footrix;
use footrix;
commit;

CREATE TABLE `contact` (
  `id` CHAR(8) NOT NULL,
  `email` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `text` text NOT NULL,
  `attachment` mediumblob,
  `timestamp` timestamp,
  UNIQUE(`id`),
  PRIMARY KEY (`id`)
);

CREATE TABLE `club_player` (
  `id` CHAR(8) NOT NULL,
  `name` varchar(255) NOT NULL,
  UNIQUE(`id`),
  PRIMARY KEY (`id`)
);

CREATE TABLE `player_stats` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `club_player_id` CHAR(8) NOT NULL,
  `goals` INT,
  `assists` INT,
  `yellowCards` INT,
  `redCards` INT,
  `cleanSheets` INT,
  `concededGoals` INT,
  `isGoalkeeper` BOOLEAN,
  UNIQUE(`id`),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`club_player_id`) REFERENCES `club_player`(`id`)
);
