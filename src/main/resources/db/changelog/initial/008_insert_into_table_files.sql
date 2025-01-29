--liquibase formatted sql
--changeset Sedep:008
insert into files(filename, original_filename, download_counter, status, private_key, user_id, category_id)
values ('803f3e23-0870-46a9-b48a-6f1e3a8e9ad3','Naruto.jpg', 5, 'public', null, (select id from users where email = 'sedep@gmail.com'), (select id from categories where name = 'Anime')),
       ('3932129e-3809-4686-852d-b56b550a9787','Sasuke.jpg', 3, 'private', 'qwerty', (select id from users where email = 'sedep@gmail.com'), (select id from categories where name = 'Anime')),
       ('b1b0cf08-d6f1-49c4-8b8c-40420a731f49','Boys over flowers.jpg', 5, 'public', null, (select id from users where email = 'user@gmail.com'), (select id from categories where name = 'KDrama')),
       ('cb9eeee6-e645-4877-b6a6-3af25ed8ece3','java.jpg', 6, 'public', null, (select id from users where email = 'cat@gmail.com'), (select id from categories where name = 'Programming')),
       ('b0a29028-cfb9-400d-b39b-ebd72807e9dc','cake.jpg', 7, 'public', null, (select id from users where email = 'cat@gmail.com'), (select id from categories where name = 'Food')),
       ('c3812592-c819-46c8-a5f0-0a9c7c13db05','Obito.jpg', 3, 'public', null, (select id from users where email = 'user@gmail.com'), (select id from categories where name = 'Anime')),
       ('c3812592-c819-46c8-a5f0-0a9c7c13db05','Игра в кальмара.jpg', 2, 'public', null, (select id from users where email = 'sedep@gmail.com'), (select id from categories where name = 'KDrama')),
       ('cb56296e-19a3-4a18-898b-a54c9237ec3c','Unity.jpg', 7, 'public', null, (select id from users where email = 'cat@gmail.com'), (select id from categories where name = 'Programming')),
       ('744381d1-fd2a-4dfd-b649-0155e762ad45','Стэйк.jpg', 9, 'public', null, (select id from users where email = 'cat@gmail.com'), (select id from categories where name = 'Food')),
       ('b9938b65-8b73-40b6-a635-ef74c7da3447','Торт.jpg', 12, 'public', null, (select id from users where email = 'cat@gmail.com'), (select id from categories where name = 'Food')),
       ('1e677f6d-6653-4213-9385-4d54fb0dc858','Котлеты.jpg', 0, 'public', null, (select id from users where email = 'cat@gmail.com'), (select id from categories where name = 'Food')),
       ('a0a5c6f3-fae1-439c-90e7-ddb7a34a35f1','Истинная красота.jpg', 1, 'public', null, (select id from users where email = 'sedep@gmail.com'), (select id from categories where name = 'KDrama')),
       ('3070ea09-6dab-4047-ae0d-b34d13a30e7e','Python.jpg', 6, 'public', null, (select id from users where email = 'cat@gmail.com'), (select id from categories where name = 'Programming')),
       ('f95f036e-63b2-4e34-9c79-869d4a7a965c','JavaScript.jpg', 6, 'private', 'qwerty', (select id from users where email = 'cat@gmail.com'), (select id from categories where name = 'Programming')),
       ('435a7086-bfae-462f-9fff-35c4723f6540','C#.jpg', 64, 'private', 'qwerty', (select id from users where email = 'cat@gmail.com'), (select id from categories where name = 'Programming'));