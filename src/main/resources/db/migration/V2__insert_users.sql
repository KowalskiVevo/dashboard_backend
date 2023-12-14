insert into ${flyway:defaultSchema}.users (tag, lastname, firstname, middlename, birthdate, photo, about, id_role)
values ('test1', 'Тест1', 'Тест1', 'Тест1', '2000-12-31', null, null, 'ROLE_USER'),
       ('test2', 'Тест2', 'Тест2', 'Тест2', '2000-12-30', null, null, 'ROLE_USER'),
       ('test3', 'Тест3', 'Тест3', 'Тест3', '2000-12-29', null, null, 'ROLE_USER'),
       ('test4', 'Тест4', 'Тест4', 'Тест4', '2000-12-28', null, null, 'ROLE_USER'),
       ('test5', 'Тест5', 'Тест5', 'Тест5', '2000-12-16', null, null, 'ROLE_USER');