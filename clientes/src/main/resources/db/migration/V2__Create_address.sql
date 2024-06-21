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
