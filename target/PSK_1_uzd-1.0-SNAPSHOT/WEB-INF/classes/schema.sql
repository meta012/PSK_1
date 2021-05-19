drop table if exists Stable_coach;
drop table if exists Horse;
drop table if exists Stable;
drop table if exists Coach;

create table Stable (
    id identity,
    address varchar(255) unique,
    name varchar(255),
    opt_lock_version int
);

create table Horse (
    id identity,
    identity_no int unique,
    name varchar(255),
    stable_id int,
    foreign key (stable_id) references Stable(id)
);

create table Coach (
    id identity,
    personal_id_no int unique,
    name varchar(255)
);

create table Stable_coach (
    stable_id int,
    coach_id int,
    primary key (stable_id, coach_id),
    foreign key (stable_id) references Stable(id),
    foreign key (coach_id) references Coach(id)
);

insert into STABLE(address, name) values ('Pajurio 15', 'Klaipedos zirgynas');


select *
from public.Stable_coach;