CREATE SEQUENCE paystubs_id_seq;
create table paystubs (
   id bigint not null DEFAULT NEXTVAL('paystubs_id_seq'),
   user_id varchar(255) not NULL UNIQUE,
   gross_wage varchar(255) not NULL,
   net_pay varchar(255) not NULL,
   primary key (id)
);
ALTER SEQUENCE paystubs_id_seq OWNED BY paystubs.id;