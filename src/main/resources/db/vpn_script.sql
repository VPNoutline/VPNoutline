--liquibase formatted sql

--init_schema
drop table if exists users;
drop table if exists rates;
drop table if exists subscriptions;
drop table if exists user_subscriptions;
drop table if exists notifications;



--table: users
create table users (
    id    bigserial primary key ,
    email varchar(255) not null unique
);

--table: rates
create table rates (
    id          bigserial primary key ,
    name_rates  varchar (255) not null ,
    description varchar (255) not null ,
    price       integer not null
);

--table: subscriptions
create table subscriptions (
    id                 bigserial primary key ,
    key_access         varchar (255) not null unique ,
    enabled_rate       boolean not null default false ,
    created            timestamp not null ,
    finished           timestamp not null ,
    rates_id           bigserial not null ,
        constraint fk_rates_id foreign key (rates_id) references rates (id) on delete cascade
);

--table: user_subscriptions
create table user_subscriptions (
    user_id          bigserial not null ,
    subscription_id  bigserial not null ,
        constraint fk_user_id foreign key (user_id) references users (id) on delete cascade ,
        constraint fk_subscription_id foreign key (subscription_id) references subscriptions (id) on delete cascade
);

--table: notifications
create table notifications (
    id              bigserial primary key ,
    user_id         bigserial not null ,
    subscription_id bigserial not null ,
    message         varchar(255) not null ,
    sent_date_time  timestamp not null ,
        constraint fk_user_id foreign key (user_id) references users (id) on delete cascade ,
        constraint fk_subscription_id foreign key (subscription_id) references subscriptions (id) on delete cascade
);


--insert data users
insert into users (email)
values  ('demidov-denis@mail.ru'),
        ('demidov@gmail.com');

--insert data roles

--insert data rates
insert into rates (name_rates, description, price) values ('start', '7 days', 0);
insert into rates (name_rates, description, price) values ('basic', 'mount', 299);
insert into rates (name_rates, description, price) values ('special offer', 'year', 1999);

--insert data subscriptions
insert into subscriptions (key_access, enabled_rate, created, finished, rates_id)
values  ('1a2b3c4d', false, '2023-11-03 20:00:00', '2023-11-10 20:00:00', 1),
        ('1234', false, '2023-11-03 20:00:00', '2023-11-10 20:00:00', 1);

--insert data user_subscriptions
insert into user_subscriptions (user_id, subscription_id)
values  (1,1),
        (2,2);

--insert data notifications
insert into notifications (user_id, subscription_id, message, sent_date_time)
values  (1,1, 'Hello User', '2023-11-03 20:00:00'),
        (2,2, 'Hello User1', '2023-11-03 20:00:00');
