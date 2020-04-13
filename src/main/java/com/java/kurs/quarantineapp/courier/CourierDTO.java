package com.java.kurs.quarantineapp.courier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "couriers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierDTO {
    private Integer id;
    private Integer capacity;
    private String name;
    private String surname;
    private String phone;

    CourierDTO(Courier c) {
        this.id = c.getId();
        this.capacity = c.getCapacity();
        this.name = c.getName();
        this.surname = c.getSurname();
        this.phone = c.getPhone();
    }
}
