--liquibase formatted sql

--changeset andrew:1
CREATE TABLE IF NOT EXISTS revision
(
    id SERIAL PRIMARY KEY ,
    timestamp timestamp NOT NULL
);
--changeset andrew:2
CREATE TABLE IF NOT EXISTS users_aud
(
    id BIGINT,
    rev INT REFERENCES revision (id),
    revtype SMALLINT ,
    username VARCHAR(64),
    birth_date DATE,
    firstname VARCHAR(64),
    lastname VARCHAR(64),
    role VARCHAR(32),
    company_id INT
    );
--changeset andrew:3
CREATE TABLE IF NOT EXISTS company_aud
(
    id INT,
    rev INT REFERENCES revision (id),
    revtype SMALLINT ,
    name VARCHAR(64)
    );