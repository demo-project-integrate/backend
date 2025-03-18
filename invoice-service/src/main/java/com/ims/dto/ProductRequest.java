package com.ims.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
}

