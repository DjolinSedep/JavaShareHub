--liquibase formatted sql
--changeset Sedep:007
--password: qwerty
insert into users(username, email, password, enabled, role_id)
values ('sedep', 'sedep@gmail.com', '$2a$12$3N7Wooke.xk5PPb8Y9ks2.AJWWRg1wTx7/ttd/i7lv0V5BGOZX7C6', true, (select id from roles where roles.name = 'ROLE_USER')),
       ('user', 'user@gmail.com', '$2a$12$3N7Wooke.xk5PPb8Y9ks2.AJWWRg1wTx7/ttd/i7lv0V5BGOZX7C6', true, (select id from roles where roles.name = 'ROLE_USER')),
       ('cat', 'cat@gmail.com', '$2a$12$3N7Wooke.xk5PPb8Y9ks2.AJWWRg1wTx7/ttd/i7lv0V5BGOZX7C6', true, (select id from roles where roles.name = 'ROLE_USER'));