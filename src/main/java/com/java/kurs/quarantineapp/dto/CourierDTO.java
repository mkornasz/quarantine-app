package com.java.kurs.quarantineapp.dto;

import com.java.kurs.quarantineapp.model.Courier;
import lombok.Getter;


@Getter
public class CourierDTO {

    private Integer capacity;
    private String name;
    private String surname;
    private String phone;

    public CourierDTO(Courier courier) {
        this.capacity = courier.getCapacity();
        this.name = courier.getName();
        this.surname = courier.getSurname();
        this.phone = courier.getPhone();
    }
}
