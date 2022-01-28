package com.technova.product_cart.Product.Cart.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_db")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String prodName;

    @Column
    private String category;

    @Column
    private Double unitPrice;

    @Column
    private Integer prodQuantity;

}


