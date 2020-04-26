package com.java.kurs.quarantineapp.service;

import com.java.kurs.quarantineapp.dto.CourierDTO;
import com.java.kurs.quarantineapp.dto.DayPlanDTO;
import com.java.kurs.quarantineapp.model.Courier;
import com.java.kurs.quarantineapp.model.DayPlan;
import com.java.kurs.quarantineapp.repository.CourierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CouriersService {
    private final Logger logger = LoggerFactory.getLogger(CouriersService.class);

    private CourierRepository repository;

    @Autowired
    private DayPlanService dayPlanService;

    CouriersService(CourierRepository repository) {
        this.repository = repository;
    }


    public List<CourierDTO> findAllCouriers() {
        logger.info("Got findAllCouriers request");
        return repository
                .findAll()
                .stream()
                .map(CourierDTO::new)
                .collect(toList());
    }

    public Long getCouriersCount() {
        logger.info("Got getCouriersCount request");
        return repository.count();
    }

    public void addNewCourier(CourierDTO courierDto) {
        repository.save(transformDtoToCourier(courierDto));
    }

    public Optional<DayPlanDTO> findCourierDayPlan(Integer courierId, LocalDate day) {
        logger.info("Got findCourierDayPlan request for id " + courierId + " and day " + day);
        return repository.findById(courierId)
                .flatMap(c -> c.getDayPlan(day))
                .map(DayPlanDTO::new);
    }

    public List<DayPlanDTO> findCourierPlans(Integer courierId) {
        return dayPlanService.findPlansByCourier(courierId)
                .stream()
                .map(DayPlanDTO::new)
                .collect(toList());
    }

    public Optional<Courier> findAvailableCourier(LocalDate deliveryDate, int routeLength) {
        Random rand = new Random();
        var couriers = repository.findAll().stream().filter(c ->
                (c.getDayPlan(deliveryDate).isPresent() ?
                        c.getDayPlan(deliveryDate).get().getRemainingCapacity() : c.getCapacity()) >= routeLength)
                .collect(Collectors.toList());

        logger.info("Find " + couriers.size() + " available couriers for route length " + routeLength + " and day " + deliveryDate);
        return couriers.size() == 0 ? Optional.empty() :
                Optional.of(couriers.get(rand.nextInt(couriers.size())));
    }

    public Optional<DayPlan> assignCourierDay(LocalDate deliveryDate, int routeLength) {
        logger.info("Got assignCourierDay request for route length " + routeLength + " and day " + deliveryDate);
        return findAvailableCourier(deliveryDate, routeLength)
                .map(c -> dayPlanService.updateCourierDayPlan(c, deliveryDate, routeLength));
    }

    private Courier transformDtoToCourier(CourierDTO courierDTO){
        return new Courier().toBuilder()
                .name(courierDTO.getName())
                .surname(courierDTO.getSurname())
                .phone(courierDTO.getPhone())
                .capacity(courierDTO.getCapacity())
                .build();
    }
}