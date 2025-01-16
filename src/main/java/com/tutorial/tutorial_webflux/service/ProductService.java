package com.tutorial.tutorial_webflux.service;

import com.tutorial.tutorial_webflux.dto.ProductDto;
import com.tutorial.tutorial_webflux.entity.Product;
import com.tutorial.tutorial_webflux.exception.CustomException;
import com.tutorial.tutorial_webflux.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Product> getAll() {
        return productRepository.findAll();
    }

    public Mono<Product> getById(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Product not found")));
    }

    public Mono<Product> save(ProductDto dto) {
        Mono<Boolean> existsName = productRepository.findByName(dto.getName()).hasElement();
        return existsName.flatMap(exists -> exists
                ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "product name already in use"))
                : productRepository.save(Product
                .builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .build()));
    }

    public Mono<Void> delete(Long id) {
        Mono<Boolean> existId = productRepository.findById(id).hasElement();
        return existId.flatMap(exists -> exists
                ? productRepository.deleteById(id)
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, "product id is not found")));
    }

    public Mono<Product> update(Long id, ProductDto dto) {
        Mono<Boolean> existId = productRepository.findById(id).hasElement();
        Mono<Boolean> productRepeatName = productRepository.repeatedName(id, dto.name).hasElement();

        return existId.flatMap(
                exist -> exist
                        ? productRepeatName.flatMap(
                        existName -> existName
                                ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "product name already in use"))
                                : productRepository.save(Product
                                .builder()
                                .id(id)
                                .name(dto.getName())
                                .price(dto.getPrice())
                                .build()))
                        : Mono.error(new CustomException(HttpStatus.NOT_FOUND, "product id is not found"))
        );
    }
}
