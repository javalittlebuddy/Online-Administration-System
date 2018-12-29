alter table users drop username;
alter table users add user_name varchar(255) not NULL UNIQUE;