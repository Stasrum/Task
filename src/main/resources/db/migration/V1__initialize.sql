drop table if exists users cascade;
create table users (id bigserial, name varchar(255), login varchar(255), password varchar(255), primary key(id));
insert into users
(name, login, password) values
('name1', 'login1', '123'),
('name2', 'login2', '123'),
('name3', 'login3', '123');


drop table if exists roles cascade;
create table roles (id bigserial, name varchar(255), primary key(id));
insert into roles
(name) values
('ROLE_ADMIN'),
('ROLE_USER');

drop table if exists users_roles;
create table users_roles (
  user_id               INT NOT NULL,
  role_id               INT NOT NULL,
  primary key (user_id, role_id),
  FOREIGN KEY (user_id)
  REFERENCES users (id),
  FOREIGN KEY (role_id)
  REFERENCES roles (id)
);

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(2, 2);