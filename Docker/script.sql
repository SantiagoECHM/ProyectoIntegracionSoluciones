CREATE DATABASE IF NOT EXISTS tienda;
USE tienda;
CREATE USER IF NOT EXISTS 'yo'@'localhost' IDENTIFIED BY '123456';
CREATE USER IF NOT EXISTS 'yo'@'127.0.0.1' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON tienda.* TO 'yo'@'localhost';
GRANT ALL PRIVILEGES ON tienda.* TO 'yo'@'127.0.0.1';
FLUSH PRIVILEGES;
