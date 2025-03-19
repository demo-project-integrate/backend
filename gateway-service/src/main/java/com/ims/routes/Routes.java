package com.ims.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){
        System.out.println("in product route");
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> invoiceServiceRoute(){
        System.out.println("in service route");
        return GatewayRouterFunctions.route("invoice_service")
                .route(RequestPredicates.path("/api/invoices"), HandlerFunctions.http("http://localhost:8082"))
                .build();
    }
}
