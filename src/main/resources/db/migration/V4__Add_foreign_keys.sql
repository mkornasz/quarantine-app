alter table orders
    add foreign key (clientId)
    references clients(id);

alter table orders
    add foreign key (courierId)
    references couriers(id);