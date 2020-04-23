package com.java.kurs.quarantineapp.dto;

import com.java.kurs.quarantineapp.model.DayPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayPlanDTO {
    private LocalDate date;
    private List<OrderDTO> orders;

    public DayPlanDTO(DayPlan dayPlan) {
        date = dayPlan.getDate();
        orders = dayPlan.getOrders().stream().map(OrderDTO::new).collect(Collectors.toList());
    }
}