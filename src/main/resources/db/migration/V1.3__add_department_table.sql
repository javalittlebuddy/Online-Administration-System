CREATE SEQUENCE departments_id_seq;
create table departments (
   id bigint not null DEFAULT NEXTVAL('departments_id_seq'),
   user_id varchar(255) not NULL UNIQUE,
   department_name varchar(255) not NULL,
   primary key (id)
);
ALTER SEQUENCE departments_id_seq OWNED BY departments.id;