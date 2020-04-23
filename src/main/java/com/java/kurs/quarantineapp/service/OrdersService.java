package com.java.kurs.quarantineapp.service;

import com.java.kurs.quarantineapp.dto.OrderDTO;
import com.java.kurs.quarantineapp.model.Order;
import com.java.kurs.quarantineapp.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class OrdersService {
    private final Logger logger = LoggerFactory.getLogger(OrdersService.class);

    private OrderRepository repository;

    @Autowired
    private CouriersService courierService;

    OrdersService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<OrderDTO> findAllOrders() {
        logger.info("Got findAllOrders request");
        return repository
                .findAll()
                .stream()
                .map(OrderDTO::new)
                .collect(toList());
    }

    public Long getOrdersCount() {
        logger.info("Got getOrdersCount request");
        return repository.count();
    }

    public Optional<OrderDTO> addNewOrder(OrderDTO orderDto) {
        logger.info("Got addNewOrder request");
        Order order = new Order(orderDto);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Accepted");

        var dayId = courierService.assignCourierDay(order.getDeliveryDate(), order.getRouteLength());

        logger.info("For date " + order.getDeliveryDate() + " and route length " + order.getRouteLength()
                + dayId.map(d -> " assigned dayPlan with id " + d).orElse(" there is no available day"));

        if (dayId.isEmpty()) return Optional.empty();
        order.setDayPlanId(dayId.get());
        return Optional.of(new OrderDTO(repository.save(order)));
    }
}