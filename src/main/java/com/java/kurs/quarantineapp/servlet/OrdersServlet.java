package com.java.kurs.quarantineapp.servlet;

import com.java.kurs.quarantineapp.dto.OrderDTO;
import com.java.kurs.quarantineapp.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersServlet {
    private final Logger logger = LoggerFactory.getLogger(OrdersServlet.class);

    private OrdersService service;

    OrdersServlet(OrdersService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<OrderDTO>> findAllOrders() {
        logger.info("Got findAllOrders request");
        return ResponseEntity.ok(service.findAllOrders());
    }

    @GetMapping("/count")
    ResponseEntity<Long> getOrdersCount() {
        logger.info("Got getOrdersCount request");
        return ResponseEntity.ok(service.getOrdersCount());
    }


    @PostMapping
    ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO order) {
        logger.info("Got saveOrder request");
        return service.addNewOrder(order).map(ResponseEntity::ok).
                orElse(ResponseEntity.unprocessableEntity().build());
    }
}