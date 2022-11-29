create table service_user
(
    id       serial primary key,
    email    varchar(255) not null,
    password varchar(255) not null
);

create table account
(
    id      serial primary key,
    name    varchar(255) not null,
    amount  numeric      not null,
    user_id int references service_user (id)
);

create table transaction
(
    id              serial primary key,
    amount          numeric   not null,
    t_date          timestamp not null,
    from_account_id int references account (id),
    to_account_id   int references account (id)
);

create table category
(
    id   int primary key,
    name varchar(255) not null
);

create table category_transaction
(
    transaction_id int references transaction (id),
    category_id    int references category (id)
);

insert into service_user (email, password)
values ('richard@gmail.com', 'rich123987');
insert into service_user (email, password)
values ('john@gmail.com', 'qwerty');
insert into service_user (email, password)
values ('larissa@gmail.com', '0147852369');
insert into service_user (email, password)
values ('helen@gmail.com', 'heleonora');

insert into account(name, amount, user_id)
values ('rich_bank', '45000', 1);
insert into account(name, amount, user_id)
values ('helen_bank', '55000', 4);
insert into account(name, amount, user_id)
values ('larisa_bank2', '785000', 3);
insert into account(name, amount, user_id)
values ('john_bank3', '4000', 2);
insert into account(name, amount, user_id)
values ('helen_bank4', '65000', 4);
insert into account(name, amount, user_id)
values ('larisa_money', '200', 3);
insert into account(name, amount, user_id)
values ('rich_cash', '45000', 1);
insert into account(name, amount, user_id)
values ('john_bank2', '1240', 2);

insert into transaction(amount, t_date, from_account_id, to_account_id)
values ('790', '2022-08-12', 1, 2);
insert into transaction(amount, t_date, from_account_id, to_account_id)
values ('10000', '2022-03-20', 3, 1);
insert into transaction(amount, t_date, from_account_id, to_account_id)
values ('80000', '2022-07-24', 4, 3);
insert into transaction(amount, t_date, from_account_id, to_account_id)
values ('5400', '2022-09-20', 4, 2);
insert into transaction(amount, t_date, from_account_id, to_account_id)
values ('1000', '2022-02-10', 3, 4);
insert into transaction(amount, t_date, from_account_id, to_account_id)
values ('200', '2022-08-02', 4, 1);
insert into transaction(amount, t_date, from_account_id, to_account_id)
values ('470', '2022-05-14', 1, 4);
insert into transaction(amount, t_date, from_account_id, to_account_id)
values ('7850', '2022-03-27', 3, 2);

insert into category(name)
values ('medicine');
insert into category(name)
values ('shopping');
insert into category(name)
values ('kindergarten');
insert into category(name)
values ('university');
insert into category(name)
values ('car');
insert into category(name)
values ('salary');
insert into category(name)
values ('traveling');
insert into category(name)
values ('rest');


insert into category_transaction(transaction_id, category_id)
values (1, 1);
insert into category_transaction(transaction_id, category_id)
values (2, 2);
insert into category_transaction(transaction_id, category_id)
values (3, 3);
insert into category_transaction(transaction_id, category_id)
values (4, 4);
insert into category_transaction(transaction_id, category_id)
values (5, 5);
insert into category_transaction(transaction_id, category_id)
values (6, 6);
insert into category_transaction(transaction_id, category_id)
values (7, 7);
insert into category_transaction(transaction_id, category_id)
values (8, 8);

select a.name
from account a
where user_id = 1;

select t.amount, su.email, a.name
from transaction t
join account a on a.id = t.from_account_id
join service_user su on a.user_id = su.id;

select sum(amount)
from account;