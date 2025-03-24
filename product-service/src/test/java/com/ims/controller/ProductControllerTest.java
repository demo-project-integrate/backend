package com.ims.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ims.dto.ProductRequest;
import com.ims.dto.ProductResponse;
import com.ims.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductRequest productRequest;
    private ProductResponse productResponse;

    @BeforeEach
    void setUp() {
        productRequest = new ProductRequest(null, "Maggie Masala", "Tasty noodles", "654321", BigDecimal.valueOf(12));
        productResponse = new ProductResponse(1L, "Maggie Masala", "Tasty noodles", "654321", BigDecimal.valueOf(12));
    }

    @Test
    void testCreateProduct() throws Exception {
        when(productService.createProduct(any(ProductRequest.class))).thenReturn(productResponse);

        mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Maggie Masala"))
                .andExpect(jsonPath("$.price").value(12));
    }

    @Test
    void testGetProductById() throws Exception {
        when(productService.getProductById(1L)).thenReturn(productResponse);

        mockMvc.perform(get("/api/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Maggie Masala"));
    }

    @Test
    void testGetAllProducts() throws Exception {
        List<ProductResponse> productList = Arrays.asList(productResponse);
        when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(get("/api/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testGetProductsByName() throws Exception {
        List<ProductResponse> productList = Arrays.asList(productResponse);
        when(productService.getProductsByName("Maggie Masala")).thenReturn(productList);

        mockMvc.perform(get("/api/product/by-name")
                        .param("name", "Maggie Masala"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/product/1"))
                .andExpect(status().isNoContent());
    }
}
