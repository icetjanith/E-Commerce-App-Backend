package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Order;
import org.example.entity.OrderDetailsEntity;
import org.example.entity.OrderEntity;
import org.example.repository.OrderDetailsRepository;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ModelMapper modelMapper;
    @Override
    public Order saveOrder(Order order) {
        try{
            OrderEntity mapped = modelMapper.map(order, OrderEntity.class);
            OrderEntity orderEntity = orderRepository.save(mapped);
            orderEntity.getOrderDetailsList().forEach(orderDetailsEntity -> {
                orderDetailsEntity.setOrder(orderEntity);
            });
            for (OrderDetailsEntity orderDetailsEntity : orderEntity.getOrderDetailsList()) {
                orderDetailsRepository.save(orderDetailsEntity);
            }
            return modelMapper.map(orderEntity,Order.class);
        } catch (Exception e) {
            return null;
        }

    }
}
