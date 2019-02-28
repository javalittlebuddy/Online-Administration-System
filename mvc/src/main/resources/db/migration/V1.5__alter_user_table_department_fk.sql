alter table departments drop user_id;
alter table users add department_id bigint DEFAULT NULL;
alter table users add FOREIGN KEY (department_id)
                          REFERENCES departments (id)
                          ON DELETE NO ACTION
                          ON UPDATE NO ACTION