-- Default users and authorities
INSERT INTO users (username, password, enabled)
VALUES ('ecommerceservice_user', 'ecommerceservice_user_password', true);
INSERT INTO users (username, password, enabled)
VALUES ('ecommerceservice_admin', 'ecommerceservice_admin_password', true);

INSERT INTO authorities (username, authority)
VALUES ('ecommerceservice_user', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('ecommerceservice_admin', 'ROLE_ADMIN');