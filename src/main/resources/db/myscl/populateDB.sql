USE eshop;
INSERT INTO users (id, login, password, email, role) VALUES (1, 'Tom', '1234', 'tom@yandex.ru', 'manager');
INSERT INTO users (id, login, password, email, role) VALUES (2, 'Sara', '1234', 'sara@yandex.ru','customer');

INSERT INTO product_type (id, type) VALUES (1, 'Animals');
INSERT INTO product_type (id, type) VALUES (2, 'Food');

INSERT INTO products (id, name, type_id) VALUES (1, 'cat', 1);
INSERT INTO products (id, name, type_id) VALUES (2, 'meet', 2);


