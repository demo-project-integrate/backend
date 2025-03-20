package com.ims.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    private final String productPort;
    private final String invoicePort;


    @Autowired
    public Routes(Environment env) {
        this.productPort = env.getProperty("spring.product.port", "5001");
        this.invoicePort = env.getProperty("spring.invoice.port", "5002");
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        System.out.println("in product route, using port: " + productPort);
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://product-service:" + productPort))
                .route(RequestPredicates.path("/api/product/{id}"), HandlerFunctions.http("http://product-service:" + productPort))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> invoiceServiceRoute() {
        System.out.println("in invoice route, using port: " + invoicePort);
        return GatewayRouterFunctions.route("invoice_service")
                .route(RequestPredicates.path("/api/invoices"), HandlerFunctions.http("http://invoice-service:" + invoicePort))
                .route(RequestPredicates.path("/api/invoices/{id}"), HandlerFunctions.http("http://invoice-service:" + invoicePort))
                .route(RequestPredicates.path("/api/invoices/items/{itemId}"), HandlerFunctions.http("http://invoice-service:" + invoicePort))
                .build();
    }
}

