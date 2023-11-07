--liquibase formatted sql

--init_schema
drop table if exists users;
drop table if exists keys_vpn;
drop table if exists roles;
drop table if exists mails_case;
drop table if exists rates;
drop table if exists subscriptions;
drop table if exists user_subscriptions;
drop table if exists notifications;



--table: users
create table users (
    id          bigserial primary key not null ,
    email       varchar(255) not null
        constraint uk_users_email unique
);

--table: keys_vpn
create table keys_vpn (
    id         bigserial primary key not null ,
    key_access varchar (128) not null unique ,
        constraint fk_keys_vpn_id foreign key (id) references users (id) on delete cascade
);

--table: roles
create table roles (
    user_id bigint not null ,
    role    smallint not null ,
        constraint uk_user_role unique (user_id, role) ,
        constraint fk_user_role foreign key (user_id) references users (id) on delete cascade
);

--table: mails_case
create table mails_case (
    id        bigserial primary key ,
    email     varchar (255) not null ,
    date_time timestamp not null
);

--table: rates
create table rates (
    id          bigserial primary key ,
    name_rates  varchar (255) not null unique ,
    description varchar (255) not null ,
    price       integer not null
);

--table: subscriptions
create table subscriptions (
    id                     bigserial primary key not null ,
    enabled_start_rate     boolean not null default false ,
    enabled_rate           boolean not null default false ,
    created                timestamp not null ,
    updated                timestamp ,
    finished               timestamp not null ,
    rates_id               bigserial not null ,
        constraint fk_rates_id foreign key (rates_id) references rates (id) on delete cascade
);

--table: user_subscriptions
create table user_subscriptions (
    id               bigserial primary key not null ,
    user_id          bigserial not null ,
    subscriptions_id bigserial not null ,
        constraint fk_user_id foreign key (user_id) references users (id) on delete cascade ,
        constraint fk_subscriptions_id foreign key (subscriptions_id) references subscriptions (id) on delete cascade
);

--table: notifications
create table notifications (
    id                    bigserial primary key not null ,
    id_user_subscriptions bigserial not null ,
    date_time             timestamp not null
);


--insert data users
insert into users (id, email)
values (1,'demidov-denis@mail.ru');

--insert data keys_vpn
insert into keys_vpn (id, key_access)
values (1,'1a2b3c4d');

--insert data roles
insert into roles (user_id, role) values (1,0);

--insert data mails_case
insert into mails_case (email, date_time)
values ('demidov-denis@mail.ru','2023-11-03 20:00:10');

--insert data rates
insert into rates (name_rates, description, price) values ('start', '7 days', 0);
insert into rates (name_rates, description, price) values ('basic', 'mount', 299);
insert into rates (name_rates, description, price) values ('special offer', 'year', 1999);

--insert data subscriptions
insert into subscriptions (id, enabled_start_rate, enabled_rate, created, finished, rates_id)
values  (1, true, false, '2023-11-03 20:00:00', '2023-11-10 20:00:00', 1);

--insert data user_subscriptions
insert into user_subscriptions (id, user_id, subscriptions_id)
values (1,1,1);

--insert data notifications
insert into notifications (id, id_user_subscriptions, date_time)
values (1,1,'2023-11-03 20:00:00');
