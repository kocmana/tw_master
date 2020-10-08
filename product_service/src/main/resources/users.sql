-- Default users and authorities
INSERT INTO users (username, password, enabled)
VALUES ('productservice_user', 'productservice_user_password', true);
INSERT INTO users (username, password, enabled)
VALUES ('productservice_admin', 'productservice_admin_password', true);

INSERT INTO authorities (username, authority)
VALUES ('productservice_user', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('productservice_admin', 'ROLE_ADMIN');