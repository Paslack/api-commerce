package com.phc.dscommerce.repositories;

import com.phc.dscommerce.entities.Order;
import com.phc.dscommerce.entities.OrderItem;
import com.phc.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
