alter table orders
    add foreign key (courier_id)
    references couriers(id);