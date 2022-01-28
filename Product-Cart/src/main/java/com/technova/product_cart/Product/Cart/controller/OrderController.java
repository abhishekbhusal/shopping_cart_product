package com.technova.product_cart.Product.Cart.controller;

import com.technova.product_cart.Product.Cart.dto.*;
import com.technova.product_cart.Product.Cart.model.Order;
import com.technova.product_cart.Product.Cart.model.Product;
import com.technova.product_cart.Product.Cart.service.OrderService;
import com.technova.product_cart.Product.Cart.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/orders")
    public ResponseEntity<Object> getAllOrder(@RequestParam(name="orderCategory",required = false)String orderCategory){
        if (orderCategory != null) {
            Optional<Order> order = orderService.getOrderByOrderCategory(orderCategory);
            if (order.isPresent()) {

                return ApiResponse.generateResponse(HttpStatus.OK.value(),"Order with orderCategory:" + orderCategory + "fetched successfully", order, null);
            }
            return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Order with orderCategory" + orderCategory + "not found in our database", null, "Order not found");

        } else {
            List<Order> orders = orderService.getAllOrders();
            if (orders.size() > 0) {
                return ApiResponse.generateResponse(HttpStatus.OK.value(), orders.size() + "orders available", orders, null);
            }
            return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "No orders available", null, "Product not found");

        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Object> getOrderById (@PathVariable Long orderId){
        Optional<Order> existingOrder = orderService.getOrderById(orderId);
        if (existingOrder.isPresent()) {
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Order fetch successfully", existingOrder, null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Order with id" + orderId + "not found in our database", null, "Order not found");
    }

    @PostMapping("/orders")
    public ResponseEntity<Object> createOrder (@RequestBody OrderRequest orderRequest){
        Optional<Order> order = orderService.getOrderByOrderCategory(orderRequest.getOrderCategory());
        if (order.isPresent()){
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Order already exist with same category "+orderRequest.getOrderCategory(),null,null);
        }


        Order savedOrder = orderService.createOrder(orderRequest);
        return ApiResponse.generateResponse(HttpStatus.OK.value(), "Order created successfully", savedOrder, null);
    }
    @PutMapping("/orders/{id}")
    public ResponseEntity<Object> updateOrder (@PathVariable Long id, @RequestBody OrderRequest orderRequest){
        Optional<Order> existingOrder = orderService.getOrderById(id);
        if (existingOrder.isPresent()) {
            orderService.updateOrder(existingOrder.get(), orderRequest);
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Order updated", existingOrder.get(), null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Sorry Order with id " + id + " not found",null, "order not found");
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Object> deleteOrder (@PathVariable Long id){
        Optional<Order> existingProduct = orderService.getOrderById(id);
        if (existingProduct.isPresent()) {
            orderService.deleteOrder(id);
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Order deleted successfully with id" + id, null, null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Order with id" + id + "not found in our database", null, "Order not found");

    }
}
