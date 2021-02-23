CREATE TABLE IF NOT EXISTS price
(
    product_id INTEGER   NOT NULL,
    valid_from TIMESTAMP NOT NULL,
    valid_to   TIMESTAMP NOT NULL,
    currency   VARCHAR(255),
    value      FLOAT,
    PRIMARY KEY (product_id, valid_from, valid_to)
);

CREATE TABLE IF NOT EXISTS purchase
(
    id           INTEGER NOT NULL AUTO_INCREMENT,
    customer_id  INTEGER,
    payment_type VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS purchase_item
(
    id             INTEGER NOT NULL AUTO_INCREMENT,
    amount         INTEGER,
    currency       VARCHAR(255),
    price_per_unit FLOAT,
    product_id     INTEGER,
    purchase_id    INTEGER,
    PRIMARY KEY (id)
);

-- Default spring security schema: https://docs.spring.io/spring-security/site/docs/current/reference/html5/#user-schema
create table if not exists users
(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(100) not null,
    enabled  boolean                not null
);

create table if not exists authorities
(
    username  varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index if not exists ix_auth_username on authorities (username, authority);