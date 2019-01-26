CREATE SEQUENCE authorities_id_seq;
create table authorities (
   id bigint not null DEFAULT NEXTVAL('authorities_id_seq'),
   role_name varchar(255) not NULL,
   user_id bigint not NULL,
   primary key (id),
   FOREIGN KEY (user_id) REFERENCES users (id)
);
ALTER SEQUENCE authorities_id_seq OWNED BY authorities.id;

