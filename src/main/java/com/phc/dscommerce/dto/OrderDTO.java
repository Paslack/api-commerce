package com.phc.dscommerce.dto;

import com.phc.dscommerce.entities.Order;
import com.phc.dscommerce.entities.OrderItem;
import com.phc.dscommerce.entities.OrderStatus;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;

    private ClientDTO client;

    private PaymentDTO payment;

    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
        client = new ClientDTO(entity.getClient());
        payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
        for (OrderItem i: entity.getOrderItems()) {
            OrderItemDTO dto = new OrderItemDTO(i);
            items.add(dto);
        }
    }

    public double getTotal() {
        double total = 0.0;
        for (OrderItemDTO i: items) {
            total += i.getSubTotal() ;
        }
        return total;
    }
}
