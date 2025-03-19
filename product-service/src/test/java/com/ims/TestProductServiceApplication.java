package com.ims;

import org.springframework.boot.SpringApplication;
import com.ims.ProductServiceApplication;  // Ensure this import path is correct


public class TestProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
