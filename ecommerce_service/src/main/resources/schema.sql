CREATE TABLE price
(
    product_id INTEGER   NOT NULL,
    valid_from TIMESTAMP NOT NULL,
    valid_to   TIMESTAMP NOT NULL,
    currency   VARCHAR(255),
    value      FLOAT,
    PRIMARY KEY (product_id, valid_from, valid_to)
);

CREATE TABLE purchase
(
    ID           BIGINT AUTO_INCREMENT,
    customer_id  INTEGER,
    payment_type VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE purchase_item
(
    id             BIGINT AUTO_INCREMENT,
    amount         INTEGER,
    currency       VARCHAR(255),
    price_per_unit FLOAT,
    product_id     INTEGER,
    purchase_id    BIGINT,
    PRIMARY KEY (id)
);