package com.java.kurs.quarantineapp.dto;

import com.java.kurs.quarantineapp.model.Courier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierDTO {

    private Integer capacity;
    private String name;
    private String surname;
    private String phone;

    public CourierDTO(Courier courier) {
        BeanUtils.copyProperties(courier, this);
    }
}
