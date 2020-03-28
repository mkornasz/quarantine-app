create table orders (
    id int primary key auto_increment,
    clientId int,
    courierId int,
    routeLength int,
    orderDate date
);