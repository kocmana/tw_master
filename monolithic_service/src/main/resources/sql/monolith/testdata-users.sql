-- Default users and authorities
INSERT INTO users (username, password, enabled)
VALUES ('monolithicservice_user', '$2a$10$HEC7o1RIEToJ0yVaTtVeruXUVQ/vBroRUGwPIAa2TLIpk3syFGU22',
        true);
INSERT INTO users (username, password, enabled)
VALUES ('monolithicservice_admin', '$2a$10$MfV9LZpUK2/vTQROXavrROp/srLDsxluHnKVWxnQX746QETZF36..',
        true);

INSERT INTO authorities (username, authority)
VALUES ('monolithicservice_user', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('monolithicservice_admin', 'ROLE_ADMIN');