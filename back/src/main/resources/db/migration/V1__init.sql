CREATE TABLE agent (
    id SERIAL PRIMARY KEY,
    code integer NOT NULL,
    processing_date timestamp with time zone NOT NULL
);

CREATE TABLE region (
    id SERIAL PRIMARY KEY,
    acronym varchar(2) NOT NULL,
    agent_id bigint NOT NULL REFERENCES agent(id)
);

CREATE TABLE generation_value (
    region_id bigint NOT NULL REFERENCES region(id),
    value decimal(10, 3) NOT NULL
);

CREATE TABLE purchase_price (
    region_id bigint NOT NULL REFERENCES region(id),
    value decimal(10, 3) NOT NULL
);