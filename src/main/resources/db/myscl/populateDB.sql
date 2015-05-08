INSERT INTO product_type (id, type) VALUES (1, 'animals');
INSERT INTO product_type (id, type) VALUES (2, 'furniture');

INSERT INTO products (id, name, type_id) VALUES (1, 'chair', 2);
INSERT INTO products (id, name, type_id) VALUES (2, 'table', 2);
INSERT INTO products (id, name, type_id) VALUES (3, 'bed', 2);
INSERT INTO products (id, name, type_id) VALUES (5, 'writing desk', 2);

INSERT INTO products (id, name, type_id) VALUES (6, 'lemur', 1);
INSERT INTO products (id, name, type_id) VALUES (7, 'dog', 1);
INSERT INTO products (id, name, type_id) VALUES (8, 'snake', 1);
INSERT INTO products (id, name, type_id) VALUES (9, 'penguin', 1);

INSERT INTO users (id, login, password, email, role) VALUES (1, 'Mike', '1234', 'mike@ya.ru', 'manager');
INSERT INTO users (id, login, password, email, role) VALUES (2, 'Sara', '1234', 'sara@ya.ru', 'customer');
COMMIT;


