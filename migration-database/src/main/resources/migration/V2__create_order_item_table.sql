CREATE TABLE spring_native_reference_project.order_item
(
    id            INT GENERATED ALWAYS AS IDENTITY,
    creation_date TIMESTAMPTZ NOT NULL,
    order_id      varchar(20) NOT NULL,
    item_name     text        NOT NULL,
    CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES "order" (order_id),
    PRIMARY KEY (id)
);
