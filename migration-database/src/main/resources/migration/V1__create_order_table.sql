CREATE TABLE spring_native_reference_project.order
(
    id            INT GENERATED ALWAYS AS IDENTITY,
    creation_date TIMESTAMPTZ NOT NULL,
    order_id      varchar     NOT NULL UNIQUE,
    order_status  varchar     NOT NULL,
    PRIMARY KEY (id)
);
