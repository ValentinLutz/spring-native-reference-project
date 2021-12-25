CREATE TABLE spring_native_reference_project.orders
(
    id            INT GENERATED ALWAYS AS IDENTITY,
    creation_date TIMESTAMPTZ NOT NULL DEFAULT now(),
    order_id      uuid UNIQUE NOT NULL,
    PRIMARY KEY (id)
);
