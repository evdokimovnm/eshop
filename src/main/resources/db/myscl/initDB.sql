CREATE DATABASE IF NOT EXISTS eshop;

USE eshop;

CREATE TABLE IF NOT EXISTS orders (
  user_id int(11) DEFAULT NULL,
  product_id int(11) DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  id int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),
  UNIQUE KEY unique_id (id)
);

CREATE TABLE IF NOT EXISTS product_type (
  id int(11) NOT NULL AUTO_INCREMENT,
  type varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY type_UNIQUE (type)
);

CREATE TABLE IF NOT EXISTS users (
  id int(11) NOT NULL AUTO_INCREMENT,
  login varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY login_UNIQUE (login),
  UNIQUE KEY email_UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS products (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  type_id int(11) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  KEY type_id (type_id),
  CONSTRAINT type_id FOREIGN KEY (type_id) REFERENCES product_type (id)
);



