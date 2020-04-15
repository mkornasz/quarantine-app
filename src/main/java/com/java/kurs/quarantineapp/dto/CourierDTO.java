package com.java.kurs.quarantineapp.dto;

import com.java.kurs.quarantineapp.model.Courier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "couriers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierDTO {

    private Integer capacity;
    private String name;
    private String surname;
    private String phone;

    public CourierDTO(Courier c) {
        this.capacity = c.getCapacity();
        this.name = c.getName();
        this.surname = c.getSurname();
        this.phone = c.getPhone();
    }
}
