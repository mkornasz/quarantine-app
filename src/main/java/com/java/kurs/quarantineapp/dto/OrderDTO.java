package com.java.kurs.quarantineapp.dto;

import com.java.kurs.quarantineapp.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer routeLength;
    private LocalDateTime orderDate;
    private LocalDate deliveryDate;
    private String clientName ;
    private String clientSurname ;
    private String clientPhone;

    public OrderDTO(Order order){
        BeanUtils.copyProperties(order, this);
    }
}