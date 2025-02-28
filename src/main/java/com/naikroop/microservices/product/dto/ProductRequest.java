package com.naikroop.microservices.product.dto;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

public record ProductRequest(String id,String name,String description,BigDecimal price) {
}
