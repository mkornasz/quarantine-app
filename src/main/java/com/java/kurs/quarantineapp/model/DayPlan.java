package com.java.kurs.quarantineapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "day_plan")
@Builder(toBuilder=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayPlan {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    private Integer courierId;
    private Integer remainingCapacity;
    private LocalDate date;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dayPlanId")
    private List<Order> orders;
}