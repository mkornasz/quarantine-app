alter table orders
    add foreign key (day_plan_id)
    references day_plan(id);

alter table day_plan
    add foreign key (courier_id)
    references couriers(id);