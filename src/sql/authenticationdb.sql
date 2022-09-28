-- create user 'login_agent'@'localhost' identified by 'password'; 

create database authentication;

-- grant all privileges on authentication.* to 'login_agent'@'%' identified by 'password';
-- grant all privileges on authentication.* to 'login_agent'@localhost identified by 'password';

use authentication;

create table user(
    id int primary key auto_increment,
    username varchar(12) not null unique,
    salt varchar(20) not null,
    pass_hash varchar(64) not null
);

create table attempt(
    id int primary key auto_increment,
    user_id int not null,
    start datetime not null,
    foreign key (user_id) references user(id)
);