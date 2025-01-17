package com.demo.webflux_crud_auth.router;

import com.demo.webflux_crud_auth.handler.ProductHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class ProductRouter {
    private static final String PRODUCT_PATH = "/products";

    @Bean
    RouterFunction<ServerResponse> productRoutes(ProductHandler handler) {
        return RouterFunctions.route()
                .GET(PRODUCT_PATH, handler::getAll)
                .GET(PRODUCT_PATH + "/{id}", handler::getById)
                .POST(PRODUCT_PATH, handler::save)
                .PUT(PRODUCT_PATH, handler::update)
                .DELETE(PRODUCT_PATH + "/{id}", handler::delete)
                .build();
    }
}
