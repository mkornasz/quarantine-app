package com.java.kurs.quarantineapp.dto;

import com.java.kurs.quarantineapp.model.Order;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class OrderDTO {

    private Integer routeLength;
    private LocalDateTime orderDate;
    private LocalDate deliveryDate;
    private String clientName;
    private String clientSurname;
    private String clientPhone;

    public OrderDTO(Order order) {
        this.routeLength = order.getRouteLength();
        this.orderDate = order.getOrderDate();
        this.deliveryDate = order.getDeliveryDate();
        this.clientName = order.getClientName();
        this.clientSurname = order.getClientSurname();
        this.clientPhone = order.getClientPhone();
    }
}