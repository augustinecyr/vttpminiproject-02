CREATE USER 'footrix' @'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'footrix' @'localhost' WITH
GRANT OPTION;
drop database if exists footrix;
create database footrix;
use footrix;
commit;

CREATE TABLE `contact` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `text` text NOT NULL,
  `attachment` blob,
  PRIMARY KEY (`id`)
);
