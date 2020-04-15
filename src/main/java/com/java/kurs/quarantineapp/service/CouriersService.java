package com.java.kurs.quarantineapp.service;

import com.java.kurs.quarantineapp.model.Courier;
import com.java.kurs.quarantineapp.dto.CourierDTO;
import com.java.kurs.quarantineapp.repository.CourierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CouriersService {
    private final Logger logger = LoggerFactory.getLogger(CouriersService.class);

    private CourierRepository repository;

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

    public Courier addNewCourier(Courier courier) {
        return repository.save(courier);
    }

    public Optional<Integer> assignCourier(LocalDate deliveryDate, Integer routeLength){
        Random rand = new Random();
        var couriersCapacity = repository.findAll();
        couriersCapacity.forEach(c->{
            int sum = c.getOrders().stream().
                    filter(j->j.getDeliveryDate().equals(deliveryDate)).
                    mapToInt(j->j.getRouteLength()).sum();
            c.setCapacity(c.getCapacity()-sum);
        });

// Always assign in the same order:
//        var courier = couriersCapacity.stream().max(Comparator.comparing(c->c.getCapacity())).get();
//        return courier.getCapacity()-routeLength>=0 ? Optional.of(courier.getId()): Optional.empty();

        var maxCapacity = couriersCapacity.stream().max(Comparator.comparing(c->c.getCapacity()));
        if(maxCapacity.isEmpty() || maxCapacity.get().getCapacity() -routeLength<=0)
            return Optional.empty();

        var couriers = couriersCapacity.stream().
                filter(c -> c.getCapacity().equals(maxCapacity.get().getCapacity())).collect(Collectors.toList());
        return  Optional.of(couriers.get(rand.nextInt(couriers.size())).getId());
    }
}