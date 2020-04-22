package com.java.kurs.quarantineapp.model;

import com.java.kurs.quarantineapp.dto.CourierDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "couriers")
@Builder(toBuilder=true)
@Data
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "courierId")
    private List<DayPlan> dayPlans;

    public Optional<DayPlan> getDayPlan(LocalDate day){
        return dayPlans.stream()
                .filter(p->p.getDate().equals(day))
                .findFirst();
    }

    public Courier(CourierDTO courier) {
        BeanUtils.copyProperties(courier, this);
    }
}