--liquibase formatted sql
--changeset Sedep:005
insert into roles(name)
values ('ROLE_USER');
