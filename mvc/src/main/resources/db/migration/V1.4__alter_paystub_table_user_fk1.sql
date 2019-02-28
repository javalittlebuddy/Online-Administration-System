alter table paystubs drop user_id;
alter table paystubs add user_id bigint DEFAULT NULL;
alter table paystubs add FOREIGN KEY (user_id)
                          REFERENCES users (id)
                          ON DELETE NO ACTION
                          ON UPDATE NO ACTION