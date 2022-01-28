package com.technova.product_cart.Product.Cart.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String prodName;
    private String category;
    private Double unitPrice;
    private Integer prodQuantity;
}
