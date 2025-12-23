package com.phc.dscommerce.services;

import com.phc.dscommerce.dto.OrderDTO;
import com.phc.dscommerce.dto.OrderItemDTO;
import com.phc.dscommerce.entities.*;
import com.phc.dscommerce.exceptions.ResourceNotFoundException;
import com.phc.dscommerce.repositories.OrderItemRepository;
import com.phc.dscommerce.repositories.OrderRepository;
import com.phc.dscommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;


    public OrderService(OrderRepository orderRepository, UserService userService, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for(OrderItemDTO itemDTO: dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), itemDTO.getPrice());
            order.getOrderItems().add(item);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getOrderItems());

        return new OrderDTO(order);
    }
}
