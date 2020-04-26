package com.java.kurs.quarantineapp.service;

import com.java.kurs.quarantineapp.model.Courier;
import com.java.kurs.quarantineapp.model.DayPlan;
import com.java.kurs.quarantineapp.repository.CourierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class CouriersServiceTests {


    @MockBean
    private CourierRepository courierRepository;

    @InjectMocks
    @Autowired
    private CouriersService couriersService;

    private Courier testCourier = Courier.builder().id(1).capacity(10).name("A").surname("B")
            .phone("48123456789").dayPlans(new ArrayList<>()).build();
    private DayPlan testDayPlan = DayPlan.builder().id(1).courier(testCourier).date(LocalDate.now())
            .remainingCapacity(5).build();

    @Test
    void returnRandomCourier() {
        when(courierRepository.findAll()).thenReturn(List.of(testCourier,
                testCourier.toBuilder().id(2).build()));

        var courier = couriersService.findAvailableCourier(LocalDate.now(), 5);
        Assertions.assertTrue(courier.isPresent());
    }

    @Test
    void returnAvailableCourier() {
        when(courierRepository.findAll()).thenReturn(List.of(testCourier.toBuilder()
                .dayPlans(List.of(testDayPlan)).build(), testCourier.toBuilder().id(2).build()));

        var courier = couriersService.findAvailableCourier(LocalDate.now(), 6);
        Assertions.assertTrue(courier.isPresent());
        Assertions.assertEquals(2, courier.get().getId());
    }

    @Test
    void returnEmptyCourierForTooLongRoute() throws Exception {
        when(courierRepository.findAll()).thenReturn(List.of(testCourier,
                testCourier.toBuilder().id(2).build()));

        var courier = couriersService.findAvailableCourier(LocalDate.now(), 15);
        Assertions.assertEquals(Optional.empty(), courier);
    }

    @Test
    void returnEmptyCourierForTakenCouriers() throws Exception {
        var testCourier2 = testCourier.toBuilder().id(2).build();
        var testDayPlan2 = testDayPlan.toBuilder().id(2).courier(testCourier2).build();
        when(courierRepository.findAll()).thenReturn(List.of(testCourier.toBuilder()
                .dayPlans(List.of(testDayPlan)).build(),
                testCourier2.toBuilder().dayPlans(List.of(testDayPlan2)).build()));

        var courier = couriersService.findAvailableCourier(LocalDate.now(), 6);
        Assertions.assertEquals(Optional.empty(), courier);
    }

    @Test
    void returnEmptyCourierForEmptyRepo() throws Exception {
        when(courierRepository.findAll()).thenReturn(new ArrayList<>());

        var courier = couriersService.findAvailableCourier(LocalDate.now(), 6);
        Assertions.assertEquals(Optional.empty(), courier);
    }
}