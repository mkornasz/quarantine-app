package com.java.kurs.quarantineapp.servlet;

import com.java.kurs.quarantineapp.dto.CourierDTO;
import com.java.kurs.quarantineapp.dto.DayPlanDTO;
import com.java.kurs.quarantineapp.model.DayPlan;
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

    @GetMapping("/count")
    ResponseEntity<Long> getCouriersCount() {
        logger.info("Got getCouriersCount request");
        return ResponseEntity.ok(service.getCouriersCount());
    }

    @GetMapping("/{id}/dayPlan")
    ResponseEntity<DayPlanDTO> findCourierDayPlan(@PathVariable Integer id, @RequestParam(value = "date", required = true) String date) {
        logger.info("Got " + id + "/plan?&dayPlan=" + date + " for request");
        var dayPlan = service.findCourierDayPlan(id, LocalDate.parse(date));
        return dayPlan.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/plans")
    ResponseEntity<List<DayPlanDTO>> findCourierDayPlan(@PathVariable Integer id) {
        logger.info("Got " + id + "/plans for request");
        return ResponseEntity.ok(service.findCourierPlans(id));
    }

    @PostMapping
    ResponseEntity saveCourier(@RequestBody CourierDTO courier) {
        logger.info("Got saveCourier request");
        service.addNewCourier(courier);
        return ResponseEntity.ok().build();
    }
}