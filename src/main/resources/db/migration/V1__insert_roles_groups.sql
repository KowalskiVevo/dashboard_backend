insert into ${flyway:defaultSchema}.role (role_name, alias)
values ('ROLE_ADMIN','Администратор'),
       ('ROLE_USER','Пользователь');

insert into ${flyway:defaultSchema}.groups (group_name, description)
values ('admin','Администратор'),
       ('developer','Пользователь'),
       ('manager','Менеджер'),
       ('analyst','Аналитик'),
       ('all', 'Штат')