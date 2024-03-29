CREATE TABLE spring_native_reference_project.task_log
(
    id            INT GENERATED ALWAYS AS IDENTITY,
    order_id      VARCHAR     NOT NULL,
    task          VARCHAR     NOT NULL,
    creation_date TIMESTAMPTZ NOT NULL,
    data          VARCHAR,
    CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES "order" (id),
    PRIMARY KEY (id)
);
