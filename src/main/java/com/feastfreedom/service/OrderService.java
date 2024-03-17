package com.feastfreedom.service;

import com.feastfreedom.dto.request.OrderItemRequest;
import com.feastfreedom.dto.request.OrderRequest;
import com.feastfreedom.entity.Order;
import com.feastfreedom.entity.OrderItem;
import com.feastfreedom.entity.User;
import com.feastfreedom.repository.MenuRepository;
import com.feastfreedom.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;


    public void saveOrder(OrderRequest orderRequest, User user) {
        Order order = mapToOrder(orderRequest, user);
        orderRepository.save(order);
    }

    private Order mapToOrder(OrderRequest orderRequest, User user) {
        Order order = new Order();
        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setMenu(menuRepository.findById(itemRequest.getId()).orElseThrow(() -> new IllegalArgumentException("Menu not found")));
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setOrder(order);
            orderItem.setCost(itemRequest.getCost());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        return order;
    }
}
