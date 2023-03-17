CREATE USER 'footrix' @'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'footrix' @'localhost' WITH
GRANT OPTION;
drop database if exists footrix;
create database footrix;
use footrix;
commit;

