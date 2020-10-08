-- Default users and authorities
INSERT INTO users (username, password, enabled)
VALUES ('product_service_user', 'product_service_user_password', true);
INSERT INTO users (username, password, enabled)
VALUES ('product_service_admin', 'product_service_admin_password', true);

INSERT INTO authorities (username, authority)
VALUES ('product_service_user', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('product_service_admin', 'ROLE_ADMIN');