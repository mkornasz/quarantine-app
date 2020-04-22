package com.java.kurs.quarantineapp.model;

import com.java.kurs.quarantineapp.dto.OrderDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "orders")
@Builder(toBuilder=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    private Integer dayPlanId;
    private Integer routeLength;
    private LocalDateTime orderDate;
    private LocalDate deliveryDate;
    private String clientName ;
    private String clientSurname ;
    private String clientPhone;
    private String status;

    public Order(OrderDTO order){
        BeanUtils.copyProperties(order, this);
    }
}