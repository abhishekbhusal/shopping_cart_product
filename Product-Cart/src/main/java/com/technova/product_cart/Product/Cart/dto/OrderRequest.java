package com.technova.product_cart.Product.Cart.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long orderId;
    private String orderCategory;
    private String orderDate;

}
