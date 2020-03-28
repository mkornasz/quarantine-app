create table clients (
    id int primary key auto_increment,
    name varchar(50),
    surname varchar(50)
);

insert into clients (name, surname) values ('Jan', 'Nowak');
insert into clients (name, surname) values ('Anna', 'Kowal');
insert into clients (name, surname) values ('Magda', 'Porak');
insert into clients (name, surname) values ('Henryk', 'Krzak');