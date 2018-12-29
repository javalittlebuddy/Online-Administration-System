alter table paystubs drop gross_wage;
alter table paystubs drop net_pay;
alter table paystubs add gross_wage numeric DEFAULT NULL;