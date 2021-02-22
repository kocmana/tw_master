CREATE TABLE IF NOT EXISTS customer
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

CREATE TABLE IF NOT EXISTS customer_interaction
(
    source_customer_id   INTEGER     NOT NULL,
    interaction_type VARCHAR(20) NOT NULL,
    target_customer_id   INTEGER     NOT NULL,
    PRIMARY KEY (source_customer_id, interaction_type, target_customer_id),
    FOREIGN KEY (source_customer_id) REFERENCES customer ON DELETE CASCADE,
    FOREIGN KEY (target_customer_id) REFERENCES customer ON DELETE CASCADE
);