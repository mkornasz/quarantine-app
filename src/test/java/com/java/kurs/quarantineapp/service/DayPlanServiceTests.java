package com.java.kurs.quarantineapp.service;

import com.java.kurs.quarantineapp.model.Courier;
import com.java.kurs.quarantineapp.model.DayPlan;
import com.java.kurs.quarantineapp.repository.DayPlanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class DayPlanServiceTests {

    @MockBean
    private DayPlanRepository dayPlanRepository;

    @InjectMocks
    @Autowired
    private DayPlanService dayPlanService;

    private Courier testCourier = Courier.builder().id(1).capacity(10).name("A").surname("B")
            .phone("48123456789").build();
    private DayPlan testDayPlan = DayPlan.builder().id(1).courier(testCourier).date(LocalDate.now())
            .remainingCapacity(10).orders(new ArrayList<>()).build();

    @Test
    void returnExistingUpdatedDayPlan() {
        when(dayPlanRepository.findByCourierIdAndDate(testCourier.getId(),testDayPlan.getDate()))
                .thenReturn(Optional.of(testDayPlan));

        dayPlanService = new DayPlanService(dayPlanRepository);

        var day = dayPlanService.updateCourierDayPlan(testCourier, testDayPlan.getDate(),6);
        Assertions.assertEquals(1, day.getId());
        Assertions.assertEquals(4, day.getRemainingCapacity());
        Assertions.assertEquals(LocalDate.now(), day.getDate());

    }

    @Test
    void returnNewDayPlan() {
        when(dayPlanRepository.findByCourierIdAndDate(testCourier.getId(),testDayPlan.getDate()))
                .thenReturn(Optional.empty());
        when(dayPlanRepository.save(any(DayPlan.class))).thenReturn(testDayPlan.toBuilder().id(2).build());

        dayPlanService = new DayPlanService(dayPlanRepository);

        var day = dayPlanService.updateCourierDayPlan(testCourier, testDayPlan.getDate(),7);
        Assertions.assertEquals(2, day.getId());
        Assertions.assertEquals(3, day.getRemainingCapacity());
        Assertions.assertEquals(LocalDate.now(), day.getDate());
    }
}