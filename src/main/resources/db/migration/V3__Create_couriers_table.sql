create table couriers (
    id int primary key auto_increment,
    name varchar(50),
    surname varchar(50),
    capacity int
);

insert into couriers (name, surname, capacity) values ('Zenon', 'Truk',15);
insert into couriers (name, surname, capacity) values ('Jan', 'Lotek',10);
insert into couriers (name, surname, capacity) values ('Karol', 'Rejt',8);
insert into couriers (name, surname, capacity) values ('Konrad', 'Zator',12);