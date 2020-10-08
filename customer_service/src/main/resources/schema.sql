CREATE TABLE customer
(
    customer_id      INTEGER     NOT NULL AUTO_INCREMENT,
    gender           VARCHAR(6),
    first_name       VARCHAR(50) NOT NULL,
    last_name        VARCHAR(50) NOT NULL,
    street           VARCHAR(50),
    house_number     VARCHAR(10),
    top              VARCHAR(10),
    postal_code      VARCHAR(10),
    country          VARCHAR(50) NOT NULL,
    telephone_number VARCHAR(50),
    email_address    VARCHAR(50) NOT NULL,
    PRIMARY KEY (customer_id)
);

CREATE TABLE customer_interaction
(
    source_customer   INTEGER     NOT NULL,
    relationship_type VARCHAR(20) NOT NULL,
    target_customer   INTEGER     NOT NULL,
    PRIMARY KEY (source_customer, relationship_type, target_customer),
    FOREIGN KEY (source_customer) REFERENCES customer ON DELETE CASCADE,
    FOREIGN KEY (target_customer) REFERENCES customer ON DELETE CASCADE
);