CREATE TABLE spring_native_reference_project.order
(
    id            INT GENERATED ALWAYS AS IDENTITY,
    order_id      varchar     NOT NULL UNIQUE,
    creation_date TIMESTAMPTZ NOT NULL,
    order_status  varchar     NOT NULL,
    PRIMARY KEY (id)
);
