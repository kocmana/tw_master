CREATE TABLE IF NOT EXISTS query_statistic
(
    id                      INTEGER     NOT NULL AUTO_INCREMENT,
    response_time_in_millis LONG,
    benchmark_uuid          VARCHAR(36) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS benchmark
(
    uuid            VARCHAR(36)  NOT NULL,
    schema          VARCHAR(100) NOT NULL,
    number_of_calls INTEGER      NOT NULL,
    finished        BOOLEAN      NOT NULL,
    PRIMARY KEY (uuid)
);