package com.ims.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class Routes {


    @Value("${spring.product.port}")
    private String productPort;


    @Value("${spring.invoice.port}")
    private String invoicePort;


    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){
        System.out.println("in product route");
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://product-service:" + productPort))
                .route(RequestPredicates.path("/api/product/{id}"), HandlerFunctions.http("http://product-service:" + productPort))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> invoiceServiceRoute(){
        System.out.println("in service route");
        return GatewayRouterFunctions.route("invoice_service")
                .route(RequestPredicates.path("/api/invoices"), HandlerFunctions.http("http://invoice-service:" + invoicePort))
                .route(RequestPredicates.path("/api/invoices/{id}"), HandlerFunctions.http("http://invoice-service:" + invoicePort))
                .route(RequestPredicates.path("/api/invoices/items/{itemId}"), HandlerFunctions.http("http://invoice-service:" + invoicePort))
                .build();
    }
}
