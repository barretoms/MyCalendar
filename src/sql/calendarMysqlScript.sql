create database calendar;

use calendar;

create table entry_type (
    id int primary key,
    type varchar(20) not null unique,
    description varchar(255)
);

create table entries (
    id int primary key auto_increment,
    dt_created datetime default current_timestamp,
    dt_modified datetime default current_timestamp,
    dt_start datetime default current_timestamp,
    dt_finish datetime default current_timestamp,
    type varchar(20) references entry_type(type),
    user_id int default 0
);

create table episodes (
    id int primary key auto_increment,
    name varchar(128) not null,
    description varchar(255),
    entry_id int not null,
    foreign key (entry_id) references entries(id) on delete cascade
);

create table events (
    id int primary key auto_increment,
    name varchar(128) not null,
    description varchar(255),
    entry_id int not null,
    foreign key (entry_id) references entries(id) on delete cascade
);

/*
create table reminder (

);

create table activity (

);

create table log (
    
);
*/

-- Insert an entry_type and an 
insert into entry_type values (1, 'Event', 'Activity that will happen in the future that has a start an a finish.');
insert into entry_type values (2, 'Episode', 'Activity that will happen in the future at a specific time (there''s no dimension of time).');
insert into entry_type values (3, 'Reminder', 'Reminder to keep in mind during a specific time.');
insert into entry_type values (4, 'Activity', 'Ongoing activity that has a start time (will become a log, once it''s finished).');
insert into entry_type values (5, 'Log', '');

-- Test values for selection

insert into entries(dt_start, dt_finish, type) 
values ('2022-11-21 12:00:00', '2022-11-21 14:00:00', "Event");
insert into entries(dt_start, dt_finish, type) 
values ('2022-11-22 14:00:00', '2022-11-22 14:00:00', "Episode");
insert into entries(dt_start, dt_finish, type) 
values ('2022-11-21 14:00:00', '2022-11-22 14:00:00', "Event");
insert into entries(dt_start, dt_finish, type) 
values ('2022-11-20 14:00:00', '2022-11-21 14:00:00', "Event");
insert into entries(dt_start, dt_finish, type) 
values ('2020-11-21 14:00:00', '2022-11-22 14:00:00', "Event");

-- Test query for selection

select id, dt_start, dt_finish from entries where 
    ('2022-11-21 00:00:00' between dt_start and dt_finish) or 
    ('2022-11-21 23:59:59' between dt_start and dt_finish) or
    (dt_start between '2022-11-21 00:00:00' and '2022-11-21 23:59:59') or
    (dt_finish between '2022-11-21 00:00:00' and '2022-11-21 23:59:59');


-- Test query to select Events

