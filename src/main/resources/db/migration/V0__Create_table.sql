CREATE TABLE labels(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    status VARCHAR
);

CREATE TABLE posts(
    id SERIAL PRIMARY KEY,
    title VARCHAR,
    content VARCHAR,
    -- list of labels?
    status VARCHAR
);

CREATE TABLE writers(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR,
    last_name VARCHAR,
    -- list of posts?
    status VARCHAR
);

--CREATE SEQUENCE hibernate_developers_seq start 1 increment 1;