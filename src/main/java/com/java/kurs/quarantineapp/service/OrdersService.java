package com.java.kurs.quarantineapp.service;

import com.java.kurs.quarantineapp.dto.OrderDTO;
import com.java.kurs.quarantineapp.model.Order;
import com.java.kurs.quarantineapp.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class OrdersService {
    private final Logger logger = LoggerFactory.getLogger(OrdersService.class);

    private OrderRepository repository;

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

    public Order addNewOrder(Order newOrder) {
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setStatus("Accepted");
        return repository.save(newOrder);
    }

}