package com.java.kurs.quarantineapp.servlet;

import com.java.kurs.quarantineapp.dto.CourierDTO;
import com.java.kurs.quarantineapp.model.Courier;
import com.java.kurs.quarantineapp.service.CouriersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/couriers")
public class CouriersServlet {
    private final Logger logger = LoggerFactory.getLogger(CouriersServlet.class);

    private CouriersService service;

    CouriersServlet(CouriersService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<CourierDTO>> findAllCouriers() {
        logger.info("Got findAllCouriers request");
        return ResponseEntity.ok(service.findAllCouriers());
    }

    @GetMapping("/maxCapacity")
    ResponseEntity<Integer> findCourier(@RequestParam(value = "routeLength", required = true) Integer routeLength, @RequestParam(value = "deliveryDate", required = true) String deliveryDate) {
        logger.info("Got maxCapacity?routeLength=" + routeLength + "&deliveryDate=" + deliveryDate + " for request");
        var courier = service.assignCourier(LocalDate.parse(deliveryDate), routeLength);
        return courier.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    ResponseEntity<Long> getCouriersCount() {
        logger.info("Got getCouriersCount request");
        return ResponseEntity.ok(service.getCouriersCount());
    }

    @PostMapping
    ResponseEntity<Courier> saveCourier(@RequestBody Courier Courier) {
        return ResponseEntity.ok(service.addNewCourier(Courier));
    }
}