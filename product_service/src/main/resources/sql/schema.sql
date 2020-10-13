CREATE TABLE IF NOT EXISTS product
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

CREATE TABLE IF NOT EXISTS product_review
(
    id          INTEGER NOT NULL AUTO_INCREMENT,
    customer_id INTEGER NOT NULL,
    product_id  INTEGER NOT NULL,
    stars       INTEGER,
    review      VARCHAR(500),
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product ON DELETE CASCADE
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