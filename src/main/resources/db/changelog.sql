--liquibase formatted sql

--init_schema
drop table if exists users;

--table:users
create table users (
    id bigserial primary key ,
    username varchar(64) not null unique ,
    password varchar(2048) not null ,
    role varchar(32) not null ,
    first_name varchar(64) not null ,
    last_name varchar(64) not null ,
    email varchar(255) not null ,
    enabled boolean not null default false ,
    created timestamp ,
    updated timestamp
);

--insert data
insert into users(id, username, password, role, first_name, last_name, email)
values (1,demidov,123456,admin,Denis,demidov,demidov-denis@mail.ru);