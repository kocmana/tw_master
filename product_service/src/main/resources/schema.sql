CREATE TABLE product
(
    id          INTEGER      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    weight      NUMERIC,
    width       NUMERIC,
    height      NUMERIC,
    depth       NUMERIC,
    PRIMARY KEY (id)
);

CREATE TABLE product_review
(
    id          INTEGER NOT NULL AUTO_INCREMENT,
    customer_id INTEGER NOT NULL,
    product_id  INTEGER NOT NULL,
    stars       INTEGER,
    review      VARCHAR(500),
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product ON DELETE CASCADE
);