package com.java.kurs.quarantineapp.courier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CouriersService {
    private final Logger logger = LoggerFactory.getLogger(CouriersService.class);

    private CourierRepository repository;

    CouriersService(CourierRepository repository) {
        this.repository = repository;
    }

    List<Courier> findAllCouriers() {
        logger.info("Got findAllCouriers request");
        return repository.findAll();
    }

    Long getCouriersCount() {
        logger.info("Got getCouriersCount request");
        return repository.count();
    }

    Courier addNewCourier(Courier courier) {
        return repository.save(courier);
    }

    Optional<Integer> assignCourier(LocalDate deliveryDate, Integer routeLength){
        var couriersCapacity = repository.findAll();
        couriersCapacity.forEach(c->{
            int sum = c.getOrders().stream().
                    filter(j->j.getDeliveryDate().equals(deliveryDate)).
                    mapToInt(j->j.getRouteLength()).sum();
            c.setCapacity(c.getCapacity()-sum);
        });
        var courier = couriersCapacity.stream().max(Comparator.comparing(c->c.getCapacity())).get();
        return courier.getCapacity()-routeLength>=0 ? Optional.of(courier.getId()): Optional.empty();
    }
}