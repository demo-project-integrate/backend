
package com.ims.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Value("${spring.product.port}")
    private String productPort;

    @Value("${spring.invoice.port}")
    private String invoicePort;

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product"), request -> {
                    System.out.println("Matched /api/product: " + request.requestPath());
                    return ServerResponse.ok().build();
                })
                .route(RequestPredicates.GET("/api/product").and(RequestPredicates.path("/api/product")),
                        HandlerFunctions.http("http://product-service:" + productPort))
                .route(RequestPredicates.path("/api/product/by-name"),
                        HandlerFunctions.http("http://product-service:" + productPort))
                .route(RequestPredicates.path("/api/product/{id}")
                                .and(request -> request.pathVariable("id").matches("\\d+")),
                        HandlerFunctions.http("http://product-service:" + productPort))
                .route(RequestPredicates.DELETE("/api/product/{id}")
                                .and(request -> request.pathVariable("id").matches("\\d+")),
                        HandlerFunctions.http("http://product-service:" + productPort))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> invoiceServiceRoute() {
        return GatewayRouterFunctions.route("invoice_service")
                .route(RequestPredicates.GET("/api/invoices"),
                        HandlerFunctions.http("http://invoice-service:" + invoicePort))
                .route(RequestPredicates.GET("/api/invoices/{id}")
                                .and(request -> request.pathVariable("id").matches("\\d+")),
                        HandlerFunctions.http("http://invoice-service:" + invoicePort))
                .route(RequestPredicates.GET("/api/invoices/items/{itemId}")
                                .and(request -> request.pathVariable("itemId").matches("\\d+")),
                        HandlerFunctions.http("http://invoice-service:" + invoicePort))
                .route(RequestPredicates.POST("/api/invoices"),
                        HandlerFunctions.http("http://invoice-service:" + invoicePort))
                .route(RequestPredicates.DELETE("/api/invoices/{id}")
                                .and(request -> request.pathVariable("id").matches("\\d+")),
                        HandlerFunctions.http("http://invoice-service:" + invoicePort))
                .build();
    }



}

