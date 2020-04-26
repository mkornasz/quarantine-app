package com.java.kurs.quarantineapp.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "day_plan")
@Builder(toBuilder=true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DayPlan {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    private Integer remainingCapacity;
    private LocalDate date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy ="dayPlan")
    private List<Order> orders;
    @ManyToOne
    private Courier courier;
}