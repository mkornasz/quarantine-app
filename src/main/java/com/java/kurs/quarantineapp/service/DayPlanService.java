package com.java.kurs.quarantineapp.service;

import com.java.kurs.quarantineapp.model.Courier;
import com.java.kurs.quarantineapp.model.DayPlan;
import com.java.kurs.quarantineapp.repository.DayPlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DayPlanService {
    private final Logger logger = LoggerFactory.getLogger(DayPlanService.class);

    private DayPlanRepository repository;

    DayPlanService(DayPlanRepository repository) {
        this.repository = repository;
    }

    public List<DayPlan> findPlansByCourier(Integer courierId) {
        return repository.findByCourierId(courierId);
    }

    public DayPlan updateCourierDayPlan(Courier courier, LocalDate date, Integer routeLength) {
        logger.info("Got updateCourierDayPlan request for courier " + courier.getId()
                + " ,route length " + routeLength + " and day " + date);
        var dayPlan = repository.findByCourierIdAndDate(courier.getId(), date)
                .orElseGet(() -> addNewDayPlan(courier, date));
        dayPlan.setRemainingCapacity(dayPlan.getRemainingCapacity() - routeLength);
        return dayPlan;
    }

    public DayPlan addNewDayPlan(Courier courier, LocalDate date) {
        logger.info("Got addNewDayPlan request for courier " + courier.getId() + " and day " + date);
        return repository.save(new DayPlan().toBuilder()
                .date(date)
                .courier(courier)
                .remainingCapacity(courier.getCapacity())
                .build());
    }
}