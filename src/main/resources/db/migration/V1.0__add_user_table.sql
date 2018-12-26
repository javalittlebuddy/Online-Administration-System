CREATE SEQUENCE users_id_seq;
create table users (
   id bigint not null DEFAULT NEXTVAL('users_id_seq'),
   email varchar(255) not NULL UNIQUE,
   username varchar(255) not NULL UNIQUE,
   first_name varchar(255) not NULL,
   last_name varchar(255) not NULL,
   password varchar(255) not NULL,
   primary key (id)
);
ALTER SEQUENCE users_id_seq OWNED BY users.id;