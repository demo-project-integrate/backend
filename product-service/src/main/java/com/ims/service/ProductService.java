package com.ims.service;

import com.ims.dto.ProductRequest;
import com.ims.dto.ProductResponse;
import com.ims.model.Product;
import com.ims.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .hsnCode((productRequest.hsnCode()))
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product created ");
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getHsnCode(),product.getPrice());

    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getHsnCode(),product.getPrice()))
                .toList();
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getHsnCode(),
                product.getPrice()
        );
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }


}
