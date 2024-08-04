DROP TABLE IF EXISTS client;

CREATE TABLE IF NOT EXISTS client (
    client_id           BIGINT GENERATED ALWAYS AS IDENTITY,
    name                VARCHAR(255),
    phone               VARCHAR(255),
    address_id          BIGINT,
    client_status       VARCHAR(255),
    contact_email       VARCHAR(255),
    PRIMARY KEY (client_id)
    );

INSERT INTO client (name, phone, address_id, client_status, contact_email) VALUES
    ('Apple Inc.', '+1-800-676-2775', 1, 'ACTIVE', 'contact@apple.com'),
    ('Microsoft Corporation', '+1-800-642-7676', 2, 'ACTIVE', 'info@microsoft.com'),
    ('Amazon.com Inc.', '+1-888-280-4331', 3, 'ACTIVE', 'support@amazon.com'),
    ('Google LLC', '+1-650-253-0000', 4, 'ACTIVE', 'contact@google.com'),
    ('Facebook, Inc.', '+1-650-543-4800', 5, 'ACTIVE', 'info@facebook.com'),
    ('Intel Corporation', '+1-408-765-8080', 6, 'ACTIVE', 'support@intel.com'),
    ('IBM Corporation', '+1-800-426-4968', 7, 'ACTIVE', 'contact@ibm.com'),
    ('Oracle Corporation', '+1-800-633-0645', 8, 'ACTIVE', 'support@oracle.com'),
    ('NVIDIA Corporation', '+1-408-486-2000', 9, 'NOT_ACTIVE', 'contact@nvidia.com'),
    ('Salesforce.com Inc.', '+1-800-667-6389', 10, 'ACTIVE', 'support@salesforce.com');

