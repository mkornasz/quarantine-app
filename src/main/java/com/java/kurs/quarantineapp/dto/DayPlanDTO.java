package com.java.kurs.quarantineapp.dto;

import com.java.kurs.quarantineapp.model.DayPlan;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayPlanDTO {
    private LocalDate date;
    private List<OrderDTO> orders;

    public DayPlanDTO(DayPlan dayPlan) {
        this.date = dayPlan.getDate();
        this.orders = dayPlan.getOrders().stream().map(OrderDTO::new).collect(Collectors.toList());
    }
}