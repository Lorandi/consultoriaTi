DROP TABLE IF EXISTS deallocation;

CREATE TABLE IF NOT EXISTS deallocation (
    id                    BIGINT GENERATED ALWAYS AS IDENTITY,
    allocation_id         BIGINT,
    deallocation_date     DATE,
    deallocation_reason   VARCHAR(100),
    PRIMARY KEY (id)
);


