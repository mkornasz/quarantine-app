package com.java.kurs.quarantineapp.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdersService {
    private final Logger logger = LoggerFactory.getLogger(OrdersService.class);

    private OrderRepository repository;

    OrdersService(OrderRepository repository) {
        this.repository = repository;
    }

    List<Order> findAllOrders() {
        logger.info("Got findAllOrders request");
        return repository.findAll();
    }

    Long getOrdersCount() {
        logger.info("Got getOrdersCount request");
        return repository.count();
    }

    Order addNewOrder(Order newOrder) {
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setStatus("Accepted");
        return repository.save(newOrder);
    }

}