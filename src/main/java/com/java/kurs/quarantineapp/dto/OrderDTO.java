package com.java.kurs.quarantineapp.dto;

import com.java.kurs.quarantineapp.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String clientPhone;
    private Integer courierId;


    public OrderDTO(Order order){
        this.routeLength = order.getRouteLength();
        this.orderDate = order.getOrderDate();
        this.deliveryDate = order.getDeliveryDate();
        this.clientName = order.getClientName();
        this.clientPhone = order.getClientPhone();
        this.courierId = order.getCourierId();
    }
}