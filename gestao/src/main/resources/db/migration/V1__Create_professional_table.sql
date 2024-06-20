CREATE TABLE IF NOT EXISTS professional (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY,
    name                VARCHAR(255),
    corporate_email     VARCHAR(255),
    professional_status VARCHAR(255),
    phone               VARCHAR(255),
    address_id          BIGINT,
    remuneration        DOUBLE PRECISION,
    PRIMARY KEY (id)
    );