package com.demo.webflux_crud_auth.handler;

import com.demo.webflux_crud_auth.dto.ProductDto;
import com.demo.webflux_crud_auth.entity.Product;
import com.demo.webflux_crud_auth.service.ProductService;
import com.demo.webflux_crud_auth.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductService productService;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<Product> products = productService.getAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(products, Product.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<Product> product = productService.getById(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(product, Product.class);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<ProductDto> product = request.bodyToMono(ProductDto.class)
                .doOnNext(objectValidator::validate);
        return product.flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.save(p), Product.class));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Mono<ServerResponse> update(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<ProductDto> product = request.bodyToMono(ProductDto.class)
                .doOnNext(objectValidator::validate);;
        return product.flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.update(id, p), Product.class));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Mono<ServerResponse> delete(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.delete(id), Void.class);
    }
}
