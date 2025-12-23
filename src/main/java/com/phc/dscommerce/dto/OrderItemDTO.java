package com.phc.dscommerce.dto;

import com.phc.dscommerce.entities.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemDTO {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;

    public OrderItemDTO(Long productId, String name, Double price, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItemDTO(OrderItem entity) {
        this.productId = entity.getId().getProduct().getId();
        this.name = entity.getId().getProduct().getName();
        this.price = entity.getPrice();
        this.quantity = entity.getQuantity();
    }

    public double getSubTotal() {
        return price * quantity;
    }
}
