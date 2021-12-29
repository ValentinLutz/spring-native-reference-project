CREATE TABLE spring_native_reference_project.order
(
    id            INT GENERATED ALWAYS AS IDENTITY,
    creation_date TIMESTAMPTZ NOT NULL,
    order_id      varchar(26) NOT NULL UNIQUE,
    order_status  text        NOT NULL,
    PRIMARY KEY (id)
);
