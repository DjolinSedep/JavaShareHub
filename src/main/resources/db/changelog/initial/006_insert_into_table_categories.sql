--liquibase formatted sql
--changeset Sedep:006
insert into categories(name)
values ('Anime'), ('KDrama'), ('Programming'), ('Food')
