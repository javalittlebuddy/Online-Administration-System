alter table users drop username;
alter table users add username varchar(255) not NULL UNIQUE;