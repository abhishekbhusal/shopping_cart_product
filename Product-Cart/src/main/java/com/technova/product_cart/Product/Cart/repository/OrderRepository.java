package com.technova.product_cart.Product.Cart.repository;

import com.technova.product_cart.Product.Cart.model.Order;
import com.technova.product_cart.Product.Cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findByOrderCategory(String orderCategory);
}

