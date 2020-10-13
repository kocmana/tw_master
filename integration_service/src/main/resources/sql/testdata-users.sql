-- Default users and authorities
INSERT INTO users (username, password, enabled)
VALUES ('integrationservice_user', '$2a$10$XwBEL4rnNRtqQROlFwwJ0urbwDocY94kuQ5kXc10TXH4Gjb7YibEu', true);
INSERT INTO users (username, password, enabled)
VALUES ('integrationservice_admin', '$2a$10$jl.IuBIz.jtF6MLiSuHlsOhTKk/uVGnOw5e/L2/PhwuF0.LZjE/ae', true);

INSERT INTO authorities (username, authority)
VALUES ('integrationservice_user', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('integrationservice_admin', 'ROLE_ADMIN');