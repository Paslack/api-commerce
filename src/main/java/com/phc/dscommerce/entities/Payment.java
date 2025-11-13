package com.phc.dscommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "tb_payment")
@Getter
@Setter
public class Payment {
    @Id
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    @OneToOne
    @MapsId
    private Order order;

    public Payment() {
    }

    public Payment(Instant moment, Order order) {
        this.moment = moment;
        this.order = order;
    }
}
