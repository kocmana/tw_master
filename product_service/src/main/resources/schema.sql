CREATE TABLE product (
      id INTEGER NOT NULL AUTO_INCREMENT,
      name VARCHAR(255) NOT NULL,
      description VARCHAR(500),
      weight NUMERIC,
      width NUMERIC,
      height NUMERIC,
      depth FLOAT,
      PRIMARY KEY (id)
);