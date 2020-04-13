create table orders (
    id int primary key auto_increment,
    status varchar(20),
    order_date timestamp,
    delivery_date date,
    route_length int,
    client_name varchar(40),
    client_phone varchar(40),
    courier_id int
);