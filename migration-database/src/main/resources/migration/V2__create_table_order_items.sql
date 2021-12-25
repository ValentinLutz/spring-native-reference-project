CREATE TABLE spring_native_reference_project.order_items
(
    id            INT GENERATED ALWAYS AS IDENTITY,
    creation_date TIMESTAMPTZ NOT NULL DEFAULT now(),
    order_id      uuid        NOT NULL,
    CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES orders (order_id),
    PRIMARY KEY (id)
);
