package com.java.kurs.quarantineapp.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "couriers")
@Builder(toBuilder=true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Courier {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    private Integer capacity;
    private String name;
    private String surname;
    private String phone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courier")
    private List<DayPlan> dayPlans;

    public Optional<DayPlan> getDayPlan(LocalDate day){
        return dayPlans.stream()
                .filter(p->p.getDate().equals(day))
                .findFirst();
    }
}