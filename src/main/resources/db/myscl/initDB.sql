CREATE DATABASE IF NOT EXISTS eshop;

USE eshop;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_type;

CREATE TABLE orders (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) DEFAULT NULL,
  product_id int(11) DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_id (id)
) ENGINE=InnoDB;

CREATE TABLE product_type (
  id int(11) NOT NULL AUTO_INCREMENT,
  type varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY type_UNIQUE (type)
) ENGINE=InnoDB;

CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  login varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY login_UNIQUE (login),
  UNIQUE KEY email_UNIQUE (email)
) ENGINE=InnoDB;

CREATE TABLE products (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(64) NOT NULL,
  type_id int(11) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB;



