create schema if not exists ${flyway:defaultSchema};

create table if not exists role
(
    role_name   varchar(255)  not null,
    alias varchar(1000) null,
    CONSTRAINT pk_role_name PRIMARY KEY (role_name)
);

COMMENT ON TABLE ${flyway:defaultSchema}.role IS 'Роль';
COMMENT ON COLUMN ${flyway:defaultSchema}.role.role_name IS 'Наименование роли';
COMMENT ON COLUMN ${flyway:defaultSchema}.role.alias IS 'Алиас роли';

create table if not exists users
(
    id         bigserial     not null,
    tag        varchar(50)   not null unique,
    lastname   varchar(50)   not null,
    firstname  varchar(50)   not null,
    middlename varchar(50)   not null,
    birthdate  date          NOT NULL,
    photo      bytea         NULL,
    about      varchar(1000) null,
    id_role    varchar(255)  not null default 'ROLE_USER',
    CONSTRAINT pk_user_id PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (id_role) REFERENCES ${flyway:defaultSchema}.role (role_name)
);

COMMENT ON TABLE ${flyway:defaultSchema}.users IS 'Информация о пользователе';
COMMENT ON COLUMN ${flyway:defaultSchema}.users.id IS 'Идентификатор пользователя';
COMMENT ON COLUMN ${flyway:defaultSchema}.users.tag IS 'Тег для отправки сообщения в системе';
COMMENT ON COLUMN ${flyway:defaultSchema}.users.lastname IS 'Фамилия';
COMMENT ON COLUMN ${flyway:defaultSchema}.users.firstname IS 'Имя';
COMMENT ON COLUMN ${flyway:defaultSchema}.users.middlename IS 'Отчество';
COMMENT ON COLUMN ${flyway:defaultSchema}.users.birthdate IS 'Дата рождения';
COMMENT ON COLUMN ${flyway:defaultSchema}.users.photo IS 'Фотография пользователя';
COMMENT ON COLUMN ${flyway:defaultSchema}.users.about IS 'О себе';

create table if not exists message
(
    id        bigserial    not null,
    user_from int8         not null,
    user_to   int8         null,
    theme     varchar(200) null,
    content   varchar      null,
    CONSTRAINT fk_user_from FOREIGN KEY (user_from) REFERENCES ${flyway:defaultSchema}.users (id),
    CONSTRAINT fk_user_to FOREIGN KEY (user_to) REFERENCES ${flyway:defaultSchema}.users (id),
    CONSTRAINT pk_message_id PRIMARY KEY (id)
);

COMMENT ON TABLE ${flyway:defaultSchema}.message IS 'Письма';
COMMENT ON COLUMN ${flyway:defaultSchema}.message.id IS 'Идентификатор письма';
COMMENT ON COLUMN ${flyway:defaultSchema}.message.user_from IS 'От кого';
COMMENT ON COLUMN ${flyway:defaultSchema}.message.user_to IS 'Кому';
COMMENT ON COLUMN ${flyway:defaultSchema}.message.theme IS 'Тема';
COMMENT ON COLUMN ${flyway:defaultSchema}.message.content IS 'Содержание';

create table if not exists groups
(
    id          bigserial     not null,
    group_name  varchar(50)   not null unique,
    description varchar(1000) null,
    CONSTRAINT pk_groups_id PRIMARY KEY (id)
);

COMMENT ON TABLE ${flyway:defaultSchema}.groups IS 'Группа';
COMMENT ON COLUMN ${flyway:defaultSchema}.groups.id IS 'Идентификатор группы';
COMMENT ON COLUMN ${flyway:defaultSchema}.groups.group_name IS 'Наименование';
COMMENT ON COLUMN ${flyway:defaultSchema}.groups.description IS 'Описание';

create table if not exists user_group
(
    id       bigserial not null,
    user_id  int8      NOT NULL,
    group_id int8      NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES ${flyway:defaultSchema}.users (id),
    CONSTRAINT fk_group_id FOREIGN KEY (group_id) REFERENCES ${flyway:defaultSchema}.groups (id),
    CONSTRAINT pk_user_group_id PRIMARY KEY (id)
);

COMMENT ON TABLE ${flyway:defaultSchema}.user_group IS 'Группа пользователей';
COMMENT ON COLUMN ${flyway:defaultSchema}.user_group.id IS 'Идентификатор группы пользователей';
COMMENT ON COLUMN ${flyway:defaultSchema}.user_group.user_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN ${flyway:defaultSchema}.user_group.group_id IS 'Идентификатор группы';

create table if not exists notification
(
    id         bigserial not null,
    user_id    int8      NOT NULL,
    message_id int8      NOT NULL,
    is_active  bool      NOT NULL,
    CONSTRAINT fk_notif_user_id FOREIGN KEY (user_id) REFERENCES ${flyway:defaultSchema}.users (id),
    CONSTRAINT fk_notif_message_id FOREIGN KEY (message_id) REFERENCES ${flyway:defaultSchema}.message (id),
    CONSTRAINT pk_notification_id PRIMARY KEY (id)
);

COMMENT ON TABLE ${flyway:defaultSchema}.notification IS 'Уведомления';
COMMENT ON COLUMN ${flyway:defaultSchema}.notification.id IS 'Идентификатор уведомлений';
COMMENT ON COLUMN ${flyway:defaultSchema}.notification.user_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN ${flyway:defaultSchema}.notification.message_id IS 'Идентификатор группы';
COMMENT ON COLUMN ${flyway:defaultSchema}.notification.is_active IS 'Показатель прочтения пользователем письма';
