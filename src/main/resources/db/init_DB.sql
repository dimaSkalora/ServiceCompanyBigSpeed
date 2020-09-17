DROP TABLE IF EXISTS users CASCADE;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

---------------users---------------1
CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  phone            VARCHAR                 NOT NULL,
  registered       date DEFAULT now()      NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email); --email каждого пользователя должен быть уникальным
CREATE UNIQUE INDEX users_unique_phone_idx ON users (phone); --phone каждого пользователя должен быть уникальным
COMMENT ON TABLE users
    IS 'Пользователи';
COMMENT ON COLUMN users.id
    IS 'ID пользователя';
COMMENT ON COLUMN users.name
    IS 'Найменование пользователя';
COMMENT ON COLUMN users.email
    IS 'Почта пользователя';
COMMENT ON COLUMN users.password
    IS 'Пароль пользователя';
COMMENT ON COLUMN users.phone
    IS 'Телефон пользователя';
COMMENT ON COLUMN users.registered
    IS 'Дата регистраиции пользователя';
COMMENT ON COLUMN users.enabled
    IS 'true - активный, false - не активный';

---------------role_type---------------2
CREATE TABLE role_type
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                 NOT NULL
);
COMMENT ON TABLE role_type
    IS 'Тип ролей';
COMMENT ON COLUMN role_type.id
    IS 'ID';
COMMENT ON COLUMN role_type.name
    IS 'Найменование(документооборот, веб...)';

---------------roles---------------3
CREATE TABLE roles
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                 NOT NULL,
    description      VARCHAR                 NOT NULL,
    id_role_type     INTEGER                 NOT NULL
);
CONSTRAINT roles_role_type_idx UNIQUE (name, id_role_type);
FOREIGN KEY (id_role_type) REFERENCES role_type (id);
COMMENT ON TABLE roles
    IS 'Роли';
COMMENT ON COLUMN roles.id
    IS 'ID';
COMMENT ON COLUMN roles.name
    IS 'Найменование';
COMMENT ON COLUMN roles.description
    IS 'Описание';
COMMENT ON COLUMN roles.id_role_type
    IS 'Тип роли';
