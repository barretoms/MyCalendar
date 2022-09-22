create database calendar;

use calendar;

create table entry_type (
    id int primary key,
    type varchar(20) not null unique,
    description varchar(255)
);

create table entry (
    id int primary key auto_increment,
    dt_created datetime default current_timestamp,
    dt_modified timestamp default current_timestamp,
    type_id int not null,
    user_id int default 0,
    foreign key (type_id) references entry_type(id)
);

create table episode (
    id int primary key auto_increment,
    label varchar(128) not null,
    dt_episode datetime not null,
    description varchar(255),
    entry_id int not null,
    foreign key (entry_id) references entry(id) on delete cascade
);

create table event (
    id int primary key auto_increment,
    label varchar(128) not null,
    dt_start datetime not null,
    dt_finish datetime not null,
    description varchar(255),
    entry_id int not null,
    foreign key (entry_id) references entry(id) on delete cascade
);

create table reminder (

);

create table activity (

);

create table log (
    
);

insert into entry_type values (1, 'event', 'Activity that will happen in the future that has a start an a finish.');
insert into entry_type values (2, 'episode', 'Activity that will happen in the future at a specific time (there''s no dimension of time).');
insert into entry_type values (3, 'reminder', 'Reminder to keep in mind during a specific time.');
insert into entry_type values (4, 'activity', 'Ongoing activity that has a start time (will become a log, once it''s finished).');
insert into entry_type values (5, 'log', '');