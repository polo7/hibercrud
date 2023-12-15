--CREATE TABLE labels (
--    id SERIAL PRIMARY KEY,
--    name VARCHAR(255)
--);

-- TODO: создание таблиц для POJO

CREATE TABLE hibernate_developers(
   id SERIAL PRIMARY KEY,
   first_name VARCHAR(50) DEFAULT NULL,
   last_name VARCHAR(50) DEFAULT NULL,
   specialty VARCHAR(50) DEFAULT NULL,
   experience INT DEFAULT NULL
);

CREATE TABLE labels(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    status VARCHAR(7)
);

CREATE TABLE posts(
    id SERIAL PRIMARY KEY,
    title VARCHAR(50),
    content VARCHAR,
    -- list of labels?
);

--CREATE SEQUENCE hibernate_developers_seq start 1 increment 1;