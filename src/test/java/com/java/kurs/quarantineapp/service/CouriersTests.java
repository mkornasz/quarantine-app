package com.java.kurs.quarantineapp.service;

import com.java.kurs.quarantineapp.model.Courier;
import com.java.kurs.quarantineapp.model.Order;
import com.java.kurs.quarantineapp.repository.CourierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class CouriersTests {

    @MockBean(name="courierRepository")
    private CourierRepository courierRepository;

    @InjectMocks
    private CouriersService couriersService;


    @Test
    void returnRandomCourierId() {
        List<Courier> couriers = new ArrayList<>();
        couriers.add(new Courier(1,10,"A","B","48123456789", new ArrayList<>()));
        couriers.add(new Courier(2,10,"C","D","48123456789", new ArrayList<>()));
        when(courierRepository.findAll()).thenReturn(couriers);
        couriersService = new CouriersService(courierRepository);

        var courierId = couriersService.assignCourier(LocalDate.now(),5);
        Assertions.assertTrue(Optional.of(courierId).isPresent());
        Assertions.assertTrue(courierId.get().equals(1) || courierId.get().equals(2));
    }

    @Test
    void returnIdWithFreeCourier() {
        List<Courier> couriers = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1,1,5, LocalDateTime.now(),LocalDate.now(),"E","F","Accepted"));
        couriers.add(new Courier(1,10,"A","B","48123456789", orders));
        couriers.add(new Courier(2,10,"C","D","48123456789", new ArrayList<>()));
        when(courierRepository.findAll()).thenReturn(couriers);
        couriersService = new CouriersService(courierRepository);

        var courierId = couriersService.assignCourier(LocalDate.now(),6);
        Assertions.assertTrue(Optional.of(courierId).isPresent());
        Assertions.assertEquals(courierId.get(), 2);
    }

    @Test
    void returnNullForTooLongRoute() throws Exception {
        List<Courier> couriers = new ArrayList<>();
        couriers.add(new Courier(1,10,"A","B","48123456789", new ArrayList<>()));
        couriers.add(new Courier(2,10,"C","D","48123456789", new ArrayList<>()));
        when(courierRepository.findAll()).thenReturn(couriers);
        couriersService = new CouriersService(courierRepository);

        var courierId = couriersService.assignCourier(LocalDate.now(),15);
        Assertions.assertEquals(courierId,Optional.empty());
    }

    @Test
    void returnNullForTakenCouriers() throws Exception {
        List<Courier> couriers = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1,1,5,LocalDateTime.now(),LocalDate.now(),"E","F","Accepted"));
        couriers.add(new Courier(1,10,"A","B","48123456789", orders));
        couriers.add(new Courier(2,10,"C","D","48123456789", orders));
        when(courierRepository.findAll()).thenReturn(couriers);
        couriersService = new CouriersService(courierRepository);

        var courierId = couriersService.assignCourier(LocalDate.now(),6);
        Assertions.assertEquals(courierId,Optional.empty());
    }

    @Test
    void returnNullForEmptyRepo() throws Exception {
        when(courierRepository.findAll()).thenReturn(new ArrayList<>());
        couriersService = new CouriersService(courierRepository);

        var courierId = couriersService.assignCourier(LocalDate.now(),6);
        Assertions.assertEquals(courierId,Optional.empty());
    }

}
