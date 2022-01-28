package com.technova.product_cart.Product.Cart.service;

import com.technova.product_cart.Product.Cart.dto.OrderRequest;
import com.technova.product_cart.Product.Cart.dto.ProductRequest;
import com.technova.product_cart.Product.Cart.model.Order;
import com.technova.product_cart.Product.Cart.model.Product;
import com.technova.product_cart.Product.Cart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderByOrderCategory(String orderCategory) {
        return orderRepository.findByOrderCategory(orderCategory);
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public Order createOrder(OrderRequest orderRequest) {
        Order newOrder = new Order();
        newOrder.setOrderCategory(orderRequest.getOrderCategory());
        newOrder.setOrderDate(orderRequest.getOrderDate());
        return orderRepository.save(newOrder);
    }
    public Order updateOrder(Order order, OrderRequest orderRequest) {

        order.setOrderCategory(orderRequest.getOrderCategory());
        order.setOrderDate(orderRequest.getOrderDate());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }






}
