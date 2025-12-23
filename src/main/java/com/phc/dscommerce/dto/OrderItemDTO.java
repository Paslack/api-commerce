package com.phc.dscommerce.dto;

import com.phc.dscommerce.entities.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemDTO {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
    private String imgUrl;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long productId, String name, Double price, Integer quantity, String imgUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public OrderItemDTO(OrderItem entity) {
        productId = entity.getId().getProduct().getId();
        name = entity.getId().getProduct().getName();
        price = entity.getId().getProduct().getPrice();
        quantity = entity.getQuantity();
        imgUrl = entity.getId().getProduct().getImgUrl();
    }

    public double getSubTotal() {
        return price * quantity;
    }
}
