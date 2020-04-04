create table couriers (
    id int primary key auto_increment,
    name varchar(50),
    surname varchar(50),
    phone varchar (13),
    capacity int
);

insert into couriers (name, surname, phone, capacity) values ('Zenon', 'Truk', '48629384192', 10);
insert into couriers (name, surname, phone, capacity) values ('Jan', 'Lotek', '48603928147', 10);
insert into couriers (name, surname, phone, capacity) values ('Henryk', 'Rejt', '48504828122', 8);
insert into couriers (name, surname, phone, capacity) values ('Konrad', 'Zator', '48679238123', 10);