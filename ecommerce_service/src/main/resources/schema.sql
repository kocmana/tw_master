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

-- Default spring security schema: https://docs.spring.io/spring-security/site/docs/current/reference/html5/#user-schema
create table users
(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(50) not null,
    enabled  boolean                not null
);

create table authorities
(
    username  varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);