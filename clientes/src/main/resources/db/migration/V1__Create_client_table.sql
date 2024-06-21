CREATE TABLE IF NOT EXISTS client (
    client_id           BIGINT GENERATED ALWAYS AS IDENTITY,
    name                VARCHAR(255),
    phone               VARCHAR(255),
    address_id          BIGINT,
    client_status VARCHAR(255),
    contact_email     VARCHAR(255),
    PRIMARY KEY (client_id)
    );