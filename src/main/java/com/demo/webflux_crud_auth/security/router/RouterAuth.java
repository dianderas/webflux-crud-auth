package com.demo.webflux_crud_auth.security.router;

import com.demo.webflux_crud_auth.security.handler.AuthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterAuth {
    private static final String AUTH_PATH = "/auth";

    @Bean
    RouterFunction<ServerResponse> authRouter(AuthHandler handler) {
        return RouterFunctions.route()
                .POST(AUTH_PATH + "/login", handler::login)
                .POST(AUTH_PATH + "/register", handler::register)
                .build();
    }
}
