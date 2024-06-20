CREATE TABLE IF NOT EXISTS address (
    id          BIGINT GENERATED ALWAYS AS IDENTITY,
    country     VARCHAR(255),
    state       VARCHAR(255),
    city        VARCHAR(255),
    street      VARCHAR(255),
    number      VARCHAR(255),
    complement  VARCHAR(255),
    zipcode     VARCHAR(255),
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS allocation (
    id                    BIGINT GENERATED ALWAYS AS IDENTITY,
    professional_id       BIGINT,
    client_id             BIGINT,
    role                  VARCHAR(255),
    value_per_hour        DOUBLE PRECISION,
    allocation_start_date DATE,
    allocation_end_date   DATE,
    allocation_status     VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS client (
    client_id   BIGINT,
    name        VARCHAR(255),
    PRIMARY KEY (client_id)
);