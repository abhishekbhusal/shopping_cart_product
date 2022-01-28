package com.technova.product_cart.Product.Cart.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;


@Entity
@Table(name = "order_db")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column
    private String orderCategory;

    @Column
    private String orderDate;

}
