create table entry_type (
    id int primary key,
    type varchar(20) not null unique,
    description varchar(255)
);

create table entries (
    id int primary key autoincrement,
    dt_created datetime default current_timestamp,
    dt_modified timestamp default current_timestamp,
    type varchar(20) references entry_type(type),
    user_id int default 0
);

create table episodes (
    id int primary key autoincrement,
    label varchar(128) not null,
    dt_episode datetime not null,
    description varchar(255),
    entry_id int not null,
    foreign key (entry_id) references entries(id) on delete cascade
);

create table events (
    id int primary key autoincrement,
    label varchar(128) not null,
    dt_start datetime not null,
    dt_finish datetime not null,
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