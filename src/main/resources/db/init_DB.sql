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
    role_type_id     INTEGER                 NOT NULL,
    CONSTRAINT roles_role_type_idx UNIQUE (name, role_type_id),
    FOREIGN KEY (role_type_id) REFERENCES role_type (id)
);
COMMENT ON TABLE roles
    IS 'Роли';
COMMENT ON COLUMN roles.id
    IS 'ID';
COMMENT ON COLUMN roles.name
    IS 'Найменование';
COMMENT ON COLUMN roles.description
    IS 'Описание';
COMMENT ON COLUMN roles.role_type_id
    IS 'Тип роли';

---------------user_roles---------------4
CREATE TABLE user_roles
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id          INTEGER NOT NULL,
    role_id          INTEGER NOT NULL,
    date_time        TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);
CREATE UNIQUE INDEX ur_udid_rid_unique_idx ON user_roles (user_id,role_id)
COMMENT ON TABLE user_roles
    IS 'Пользователь и Роли';
COMMENT ON COLUMN user_roles.user_id
    IS 'Пользователь';
COMMENT ON COLUMN user_roles.role_id
    IS 'Роль';
COMMENT ON COLUMN user_roles.date_time
    IS 'Дата добавление пользователя и роли';

---------------wf_package_status---------------5
CREATE TABLE wf_package_status
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                 NOT NULL
);
COMMENT ON TABLE wf_package_status
    IS 'Статус пакета';
COMMENT ON COLUMN wf_package_status.id
    IS 'ID';
COMMENT ON COLUMN wf_package_status.name
    IS 'Найменование(В работе, Завершен, Архив, Ожидание, Удалён)';

---------------wf_service---------------6
CREATE TABLE wf_service
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                 NOT NULL
);
COMMENT ON TABLE wf_service
    IS 'Услуга';
COMMENT ON COLUMN wf_service.id
    IS 'ID';
COMMENT ON COLUMN wf_service.name
    IS 'Найменование(Ремонт, Подключение...)';

---------------wf_package---------------7
CREATE TABLE wf_package (
  id                    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name                  VARCHAR(500),
  date_registration     DATE NOT NULL,
  customer_name         VARCHAR(500),
  customer_address      VARCHAR(500),
  customer_address_jur  VARCHAR(500),
  customer_phone        VARCHAR,
  customer_email        VARCHAR(50),
  contract_number       VARCHAR(150),
  description           VARCHAR,
  user_add              VARCHAR(50),
  date_add              TIMESTAMP(6) WITHOUT TIME ZONE,
  user_edit             VARCHAR(50),
  date_edit             TIMESTAMP(6) WITHOUT TIME ZONE,
  package_service_id    INTEGER NOT NULL,
  package_status_id     INTEGER NOT NULL,

  FOREIGN KEY (package_status_id)   REFERENCES wf_package_status(id),
  FOREIGN KEY (package_service_id)  REFERENCES wf_service(id)
)
COMMENT ON TABLE wf_package
    IS 'Пакет документов';
COMMENT ON COLUMN wf_package.id
    IS 'ID';
COMMENT ON COLUMN wf_package.name
    IS 'Наименование пакета';
COMMENT ON COLUMN wf_package.date_registration
    IS 'Дата регистрации';
COMMENT ON COLUMN wf_package.customer_name
    IS 'Наименование заказчика';
COMMENT ON COLUMN wf_package.customer_address
    IS 'Адреса замовника';
COMMENT ON COLUMN wf_package.customer_address_jur
    IS 'Юридична адреса замовника';
COMMENT ON COLUMN wf_package.customer_phone
    IS 'Номер телефона заказчика';
COMMENT ON COLUMN wf_package.contract_number
    IS 'Номер договора';
COMMENT ON COLUMN wf_package.description
    IS 'Краткое описание';
COMMENT ON COLUMN wf_package.user_add
    IS 'Користувач, який створив запис';
COMMENT ON COLUMN wf_package.date_add
    IS 'Дата створення';
COMMENT ON COLUMN wf_package.user_edit
    IS 'Користувач, який вніс зміни';
COMMENT ON COLUMN wf_package.date_edit
    IS 'Дата зміни';
COMMENT ON COLUMN wf_package.package_service_id
    IS 'Услуга';
COMMENT ON COLUMN wf_package.package_status_id
    IS 'Статус пакета';

---------------wf_base_process_type---------------8
CREATE TABLE wf_base_process_type (
  id    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name  VARCHAR(20) NOT NULL
)
COMMENT ON TABLE wf_base_process_type
    IS 'Типы базовых процессов';
COMMENT ON COLUMN wf_base_process_type.id
    IS 'ID';
COMMENT ON COLUMN wf_base_process_type.name
    IS 'Наименование(Стартовый, Обычный)';

---------------wf_base_process---------------9
CREATE TABLE wf_base_process (
  id                    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name                  VARCHAR(500) NOT NULL,
  description           VARCHAR(500),
  package_service_id    INTEGER NOT NULL,
  base_process_type_id  INTEGER NOT NULL,
  FOREIGN KEY (package_service_id)  REFERENCES wf_service(id),
  FOREIGN KEY (base_process_type_id)  REFERENCES wf_base_process_type(id)
)
COMMENT ON TABLE workflow.wfbaseprocess
    IS 'Базовый процесс';
COMMENT ON COLUMN workflow.wfbaseprocess.code
    IS 'код';
COMMENT ON COLUMN workflow.wfbaseprocess.name
    IS 'Наименование процесса';
COMMENT ON COLUMN workflow.wfbaseprocess.description
    IS 'Описание процесса';
COMMENT ON COLUMN workflow.wfbaseprocess.package_service_id
    IS 'Услуга';
COMMENT ON COLUMN workflow.wfbaseprocess.base_process_type_id
    IS 'Типы базовых процессов';

