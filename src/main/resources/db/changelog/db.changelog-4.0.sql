--liquibase formatted sql

--changeset andrew:1
ALTER TABLE users
    ADD COLUMN password VARCHAR(128) DEFAULT '{bcrypt}$2a$10$cZxAdUQz87B9lRQp2o4dZutvWSx.J27uAAg.oYgt5tuOEF5vs0BTe';

--changeset andrew:2
ALTER TABLE users_aud
    ADD COLUMN password VARCHAR(128);