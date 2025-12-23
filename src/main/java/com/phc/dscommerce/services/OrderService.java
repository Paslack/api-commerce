package com.phc.dscommerce.services;

import com.phc.dscommerce.dto.OrderDTO;
import com.phc.dscommerce.entities.Order;
import com.phc.dscommerce.exceptions.ResourceNotFoundException;
import com.phc.dscommerce.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new OrderDTO(order);
    }
}
