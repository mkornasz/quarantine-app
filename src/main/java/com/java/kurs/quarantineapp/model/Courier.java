package com.java.kurs.quarantineapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "couriers")
@Builder
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
    private List<Order> orders;
}