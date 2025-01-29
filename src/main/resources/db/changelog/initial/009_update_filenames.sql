--liquibase formatted sql
--changeset Sedep:009

UPDATE files
SET filename = CONCAT(filename, '.jpg')
WHERE filename NOT LIKE '%.jpg';