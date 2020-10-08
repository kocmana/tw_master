INSERT INTO price (product_id, valid_from, valid_to, currency, value)
VALUES (1, '2020-01-01T00:00:00', '2020-12-31T23:59:59', 'EUR', 19.99);
INSERT INTO price (product_id, valid_from, valid_to, currency, value)
VALUES (1, '2019-01-01T00:00:00', '2019-12-31T23:59:59', 'EUR', 25.99);
INSERT INTO price (product_id, valid_from, valid_to, currency, value)
VALUES (2, '2020-01-01T00:00:00', '2020-12-31T23:59:59', 'EUR', 19.99);
INSERT INTO price (product_id, valid_from, valid_to, currency, value)
VALUES (2, '2019-01-01T00:00:00', '2019-12-31T23:59:59', 'EUR', 25.99);

INSERT INTO purchase(customer_id, payment_type)
VALUES (1, 'CASH');
INSERT INTO purchase(customer_id, payment_type)
VALUES (2, 'CASH');
INSERT INTO purchase(customer_id, payment_type)
VALUES (3, 'CASH');

INSERT INTO purchase_item (amount, currency, price_per_unit, product_id, purchase_id) VALUES ( 3, 'EUR', 19.99, 1, 1 );
INSERT INTO purchase_item (amount, currency, price_per_unit, product_id, purchase_id) VALUES ( 2, 'EUR', 25.99, 2, 1 );
INSERT INTO purchase_item (amount, currency, price_per_unit, product_id, purchase_id) VALUES ( 3, 'EUR', 19.99, 1, 2 );
INSERT INTO purchase_item (amount, currency, price_per_unit, product_id, purchase_id) VALUES ( 2, 'EUR', 25.99, 2, 2 );
INSERT INTO purchase_item (amount, currency, price_per_unit, product_id, purchase_id) VALUES ( 3, 'EUR', 19.99, 1, 3 );
INSERT INTO purchase_item (amount, currency, price_per_unit, product_id, purchase_id) VALUES ( 2, 'EUR', 25.99, 2, 3 );