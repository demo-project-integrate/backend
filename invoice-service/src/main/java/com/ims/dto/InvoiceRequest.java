package com.ims.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceRequest {
    private UserDetailRequest user;
    private List<ProductRequest> products;
}

