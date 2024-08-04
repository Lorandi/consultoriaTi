DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id                   BIGINT GENERATED ALWAYS AS IDENTITY,
    login                VARCHAR(100) UNIQUE NOT NULL,
    password             VARCHAR(255) NOT NULL,
    role                 VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
    );