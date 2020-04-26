package com.java.kurs.quarantineapp.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Builder(toBuilder=true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    private Integer routeLength;
    private LocalDateTime orderDate;
    private LocalDate deliveryDate;
    private String clientName ;
    private String clientSurname ;
    private String clientPhone;
    private String status;
    @ManyToOne
    private DayPlan dayPlan;
}