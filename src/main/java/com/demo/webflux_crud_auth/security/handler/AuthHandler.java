package com.demo.webflux_crud_auth.security.handler;

import com.demo.webflux_crud_auth.security.dto.LoginDto;
import com.demo.webflux_crud_auth.security.dto.RegisterDto;
import com.demo.webflux_crud_auth.security.dto.TokenDto;
import com.demo.webflux_crud_auth.security.entity.User;
import com.demo.webflux_crud_auth.security.service.UserService;
import com.demo.webflux_crud_auth.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthHandler {
    private final UserService userService;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<LoginDto> dtoMono = request.bodyToMono(LoginDto.class)
                .doOnNext(objectValidator::validate);
        return dtoMono.flatMap(dto -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.login(dto), TokenDto.class));
    }

    public Mono<ServerResponse> register(ServerRequest request) {
        Mono<RegisterDto> dtoMono = request.bodyToMono(RegisterDto.class)
                .doOnNext(objectValidator::validate);
        return dtoMono.flatMap(dto -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.registerUser(dto), User.class));
    }
}
