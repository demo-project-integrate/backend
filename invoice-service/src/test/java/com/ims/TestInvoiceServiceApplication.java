package com.ims;

import com.ims.config.TestcontainersConfiguration;
import org.springframework.boot.SpringApplication;

public class TestInvoiceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(InvoiceServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
