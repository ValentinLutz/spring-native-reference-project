CREATE TABLE spring_native_reference_project.order
(
    id            INT GENERATED ALWAYS AS IDENTITY,
    creation_date TIMESTAMPTZ NOT NULL DEFAULT now(),
    order_id      uuid UNIQUE NOT NULL,
    PRIMARY KEY (id)
);
