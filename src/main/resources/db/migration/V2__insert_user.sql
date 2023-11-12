insert into ${flyway:defaultSchema}.users(tag, lastname, firstname, middlename, birthdate, id_role)
values ('admin', 'Test', 'Test', 'Test', now(), 'ROLE_ADMIN'),
       ('user', 'Test', 'Test', 'Test', now(), 'ROLE_USER');